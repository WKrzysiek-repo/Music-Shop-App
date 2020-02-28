/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import model.AP;
import model.APDAO;
import model.Author;
import model.AuthorDAO;
import model.Plyta;
import model.PlytaDAO;
import org.controlsfx.control.textfield.AutoCompletionBinding;


/**s
 *
 * @author Wojciech
 * @version 1.0
 * @see ShopController
 */
public class ShopController {
    
    @FXML
    private TextField txtNazwa;
    
    @FXML
    private TextField txtCena;
    
    @FXML
    private TextField txtIlosc;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtYear;
 

    @FXML
    private TextField searchPlytaID;
    
    @FXML
    private TextField searchPlytacena;
    
    @FXML
    private TextField searchPlytaname;
    
    @FXML
    private TextField searchPlytailosc;

    @FXML
    private TextField searchAuthorID;
    
    @FXML
    private TextField searchAuthorNazwa;
    
    @FXML
    private TextField searchAuthorRok;
    
    
    @FXML
    private TextField autorNazwa;
    
    @FXML
    private TextField autorID;
    
    @FXML
    private TextField autorRok;    
    
    
    @FXML
    private TextField rSRecordName;
    
    @FXML
    private TextField rSRecordPrice;
    
    @FXML
    private TextField rSRecordQuantity; 
    
    @FXML
    private TextField rSAuthorName;
    
    @FXML
    private TextField rSAuthorYear; 
    
   @FXML
   private TableColumn<AP, Integer> colPlytyID;
   @FXML
   private TableColumn<AP, String> colPlytyNazwa;
   @FXML
   private TableColumn<AP, Float> colPlytyCena;
   @FXML
   private TableColumn<AP, Integer> colPlytyIlosc;
   @FXML
   private TableColumn<AP, String> colAutorImie;
   @FXML
   private TableColumn<AP, Integer> colAutorNazwa;
   @FXML
   private TableView PlytyTable;
    
   @FXML
   private TableColumn<Author, Integer> colAuthorsID;
   @FXML
   private TableColumn<Author, String> colAuthorsName;
   @FXML
   private TableColumn<Author, Integer> colAuthorsYear;
 
   @FXML
   private TableView AuthorsTable;


    @FXML
    private TextField txtAutorSearch;
    
   

    AutoCompletionBinding autorSearch;
    List<String> terms = new ArrayList<>();
    SuggestionProvider<String> provider = SuggestionProvider.create(terms);
    

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private void insertPlyta() throws ClassNotFoundException, SQLException{

        Author author_check = new Author("None",0);
        
        int id;
        float cena, ilosc;
        
        
        try{
            id = Integer.parseInt(autorID.getText());
        } catch (NumberFormatException ex){
            showError("Incompatible type in id field");
            return;
        }
        
        try{
            cena = Float.parseFloat(txtCena.getText());
        } catch (NumberFormatException ex){
            showError("Incompatible type in price field");
            return;
        }
           
        try{
            ilosc = Float.parseFloat(txtIlosc.getText());
        } catch (NumberFormatException ex){
            showError("Incompatible type in quantity field");
            return;
        }
        
        try{
           
            AuthorDAO autor_manager = new AuthorDAO();

	    author_check = autor_manager.getAuthorByID(id);
            
        }
        catch (NumberFormatException a){}

            PlytaDAO plyty_manager = new PlytaDAO();
          
            plyty_manager.insertPlyta(txtNazwa.getText(),cena, ilosc);
            
            plyty_manager = new PlytaDAO();    
            Plyta a1 = plyty_manager.getPlytaByName(txtNazwa.getText());
            
            
            APDAO ap_manager = new APDAO();
            
            ap_manager.insertAP(a1.getId(), id);

           showSuccess("Success, added record!");
            

        this.fillTable();    
        ClearFieldsAddRecord(); 
                
    }
    
    /**
     * Function that pops message dialog box up with information about error action
     * 
     * @param message an error message
     */

    public static void showError(String message){
        JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Function that pops message dialog box up with information about successful action
     * @param message an success message
     */
    public static void showSuccess(String message){
        JOptionPane.showMessageDialog(null,message);
    }
    
     /**
     * Function that pops message dialog box up with information about warning action
     * @param message an warning message
     */

    public static void showWarning(String message){
        JOptionPane.showMessageDialog(null,message,"Warning",JOptionPane.WARNING_MESSAGE);
    }
    
    
    @FXML
    private void updatePlyty() throws ClassNotFoundException, SQLException{
        
        PlytaDAO plyty_manager = new PlytaDAO();
        int id;
        float cena, ilosc;
        try{
            id = Integer.parseInt(searchPlytaID.getText());
        } catch (NumberFormatException ex){
            showError("Incompatible type in id field");
            return;
        }

        try{
            cena = Float.parseFloat(searchPlytacena.getText());
        } catch (NumberFormatException ex){
            showError("Incompatible type in price field");
            return;
        }
        
        try{
            ilosc = Float.parseFloat(searchPlytailosc.getText());
        } catch (NumberFormatException ex){
            showError("Incompatible type in quantity field");
            return;
        }

        if(plyty_manager.getPlyta(id) == null){
            showWarning("Cannot find record by given id");
            return;
        }

        plyty_manager.updatePlyta(id,searchPlytaname.getText(), cena, ilosc); 
        this.fillTable();
        searchPlytaID.setText(null);
        searchPlytaname.setText(null);
        searchPlytacena.setText(null);
        searchPlytailosc.setText(null);
        Component frame = null;
        JOptionPane.showMessageDialog(frame,"Updated record!");

    }
    
    
    @FXML
    private void autoCompleteImie(String oldValue, String newValue) {
        
        try {

        AuthorDAO autor_manager = new AuthorDAO();
        List<Author> autor_all = autor_manager.getAllAuthor();
        
        terms = new ArrayList<>();

        for (int i = 0; i < autor_all.size(); i++) {
            
            String imie_tmp = autor_all.get(i).getName()+ " " + autor_all.get(i).getId() + " " + autor_all.get(i).getYear();

            if(imie_tmp.startsWith(newValue)){

                terms.add(imie_tmp);


                if(terms.size() == 1){
                    autorNazwa.setText(autor_all.get(i).getName());
                    autorID.setText(autor_all.get(i).getId().toString());
                    autorRok.setText(autor_all.get(i).getYear().toString());
                }else{
                    autorNazwa.setText("Nazwa zespołu");
                    autorID.setText("ID zespołu");
                    autorRok.setText("Rok powstania22");
                }    
                
              }               
        }
        provider.clearSuggestions();
        provider.addPossibleSuggestions(terms);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   @FXML
    private void search() throws Exception{
        APDAO ap_manager = new APDAO();
        List<AP> my_all_filterted = ap_manager.getPlytaAutorFilter(rSRecordName.getText(),rSRecordPrice.getText(),rSRecordQuantity.getText(),rSAuthorName.getText(),rSAuthorYear.getText());
                
        PlytyTable.getItems().clear();
        ObservableList<AP> dane = FXCollections.observableArrayList(my_all_filterted);
        
        PlytyTable.itemsProperty().setValue(dane);
         
        rSAuthorName.setText(null);
        rSAuthorYear.setText(null);
        rSRecordQuantity.setText(null);
        rSRecordPrice.setText(null);
        rSRecordName.setText(null);

    }
    
   @FXML
    private void initalize() throws Exception{

    
       txtAutorSearch.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
           autoCompleteImie(oldValue, newValue);
       });
        AutoCompletionTextFieldBinding<String> autoCompletionTextFieldBinding = new AutoCompletionTextFieldBinding<>(txtAutorSearch, provider);

       this.fillTable();

    }
    
    /**
     *
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */
    public void fillTable() throws SQLException, ClassNotFoundException{
        
        APDAO ap_manager = new APDAO();
        
        List<AP> my_all = ap_manager.getPlytaAutorALL();
        
        
        ObservableList<AP> dane = FXCollections.observableArrayList(my_all);
        
        PlytyTable.itemsProperty().setValue(dane);
         
        colPlytyID.setCellValueFactory(
                new PropertyValueFactory<>("plyta_id")
        );
        
        colPlytyNazwa.setCellValueFactory(
                new PropertyValueFactory<>("nazwa")
        );
        
        colPlytyCena.setCellValueFactory(
                new PropertyValueFactory<>("cena")
        );        

        colPlytyIlosc.setCellValueFactory(
                new PropertyValueFactory<>("ilosc")
        );  
        
        colAutorImie.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        
        colAutorNazwa.setCellValueFactory(
                new PropertyValueFactory<>("year")
        );  

    }
   
    @FXML
    private void insertAuthor() throws ClassNotFoundException, SQLException{
  
        AuthorDAO author_manager = new AuthorDAO();
            
        int rok;
   
        try{
            rok = Integer.parseInt(txtYear.getText());
            
        } catch (NumberFormatException ex){
            txtName.setText(null);
            txtYear.setText(null);
            showError("Incompatible type in year field");
            return;
        }
    
            author_manager.insertAuthor(txtName.getText(),rok);
            txtName.setText(null);
            txtYear.setText(null);
            List<Author> moi_autorzy = author_manager.getAllAuthor();
            ObservableList<Author> dane = FXCollections.observableArrayList(moi_autorzy);
            populateTable2(dane);
            showSuccess("Added author!");
    }
    
    @FXML
    private void populateTable2 (ObservableList<Author> dane) {
        AuthorsTable.setItems(dane);
    }
    
    
    @FXML
    private void updateAuthor() throws ClassNotFoundException, SQLException{

        AuthorDAO author_manager = new AuthorDAO();
        int rok,id;

        try{
            rok = Integer.parseInt(searchAuthorRok.getText());
            
        } catch (NumberFormatException ex){
            showError("Incompatible type in year field");
            return;
        }

        try{
            id = Integer.parseInt(searchAuthorID.getText());
            
        } catch (NumberFormatException ex){
            showError("Incompatible type in id field");
            return;
        }
        
        if(author_manager.getAuthorByID(id) == null){
            showWarning("Cannot find record by given id");
            return;
        }
        
            author_manager.updateAuthor(id, searchAuthorNazwa.getText(),rok);
            searchAuthorID.setText(null);
            searchAuthorNazwa.setText(null);
            searchAuthorRok.setText(null);
            List<Author> moi_autorzy = author_manager.getAllAuthor();
            ObservableList<Author> dane = FXCollections.observableArrayList(moi_autorzy);
            populateTable2(dane);
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Updated author!");
    }
    
    @FXML
    private void initalize2() throws Exception{
        AuthorDAO Authors_manager = new AuthorDAO();
        
        List<Author> moi_autorzy = Authors_manager.getAllAuthor();
        
        
        ObservableList<Author> dane = FXCollections.observableArrayList(
            moi_autorzy
        );

        AuthorsTable.itemsProperty().setValue(dane);

        colAuthorsID.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        
        colAuthorsName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        
        colAuthorsYear.setCellValueFactory(
                new PropertyValueFactory<>("year")
        );        

    }

    
    public void ClearFieldsAddRecord(){
        txtNazwa.setText(null);
        txtCena.setText(null);
        txtIlosc.setText(null);
        autorID.setText(null);
        autorNazwa.setText(null);
        autorRok.setText(null);
        txtAutorSearch.setText("");
      
    }
    
}

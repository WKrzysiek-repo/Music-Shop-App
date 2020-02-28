/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author Wojciech
 * @version 1.0
 * @see MainApplication
 * @see javafx.stage.Stage
 * @see javafx.scene.Scene
 * @see javafx.scene.Parent
 * @see javafx.fxml.FXMLLoader
 * @see javafx.application.Application
 * @see java.sql.SQLException
 */
public class MainApplication extends Application{

    /**
     *
     * @param args args 
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     * @throws SQLException an exception that provides information on a database access error
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException{

        launch(args);
        
    }
    
    
    
        public void start(Stage primaryStage) throws Exception{
        
        Parent root=FXMLLoader.load(getClass().getResource("/controller/ShopView.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
}

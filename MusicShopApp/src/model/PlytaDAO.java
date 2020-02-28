/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DButil;

/**
 *
 * @author Wojciech
 * @version 1.0
 * @see PlytaDAO
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 */
public class PlytaDAO {
  
    
    /**   
     * This method gets record in database
     * @param  plyty_id  identifier of requested record
     * @return Plyta object if id exists in database or null if doesn't
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
    */

    public Plyta getPlyta(int plyty_id) throws SQLException, ClassNotFoundException{
        Plyta tmp = null;
        ResultSet rsSet = null;
        DButil.dbConnect();
        try {
            rsSet = DButil.dbExecute("SELECT * FROM plyty WHERE plyty.id=" + plyty_id);
            while(rsSet.next()) {
               
                tmp = new Plyta(rsSet.getString("nazwa"),
                                rsSet.getFloat("cena"),
                                rsSet.getInt("ilosc"));
                
                tmp.setId(rsSet.getInt("id")); 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            rsSet.close(); 
            DButil.dbDisconnect();

        }
        return tmp;
        
    }

    /**
     * This method gets record in database, based on names
     * @param plyty_name  name of requested record
     * @return Plyta object if name exists in database or null if doesn't
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public Plyta getPlytaByName(String plyty_name) throws SQLException, ClassNotFoundException{
        Plyta tmp = null;
        ResultSet rsSet = null;
        DButil.dbConnect();
        try {
            rsSet = DButil.dbExecute("SELECT * FROM plyty WHERE plyty.nazwa='" + plyty_name + "'");
            while(rsSet.next()) {
               
                tmp = new Plyta(rsSet.getString("nazwa"),
                                rsSet.getFloat("cena"),
                                rsSet.getInt("ilosc"));
                
                tmp.setId(rsSet.getInt("id")); 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            rsSet.close();    
            DButil.dbDisconnect();
        }
        return tmp;
        
    } 

    /**
     * This method inserts the data of records in the database
     * @param name_ name of requested record
     * @param price_  price of requested record   
     * @param quantity_ quantity of requested record 
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     * @throws SQLException an exception that provides information on a database access error
     */

    public  void insertPlyta(String name_, float price_, float quantity_) throws ClassNotFoundException, SQLException{
        String sql="insert into plyty(nazwa,cena,ilosc) values('"+name_+"', '"+price_+"', '"+quantity_+"')";
         DButil.dbConnect();      
        try{
            DButil.dbExcecuteQuery(sql);
            
        }catch(SQLException e){
            System.out.println("error");
            e.printStackTrace();
            throw e;
        }finally{
            DButil.dbDisconnect();
        }
    }
      
    /**
     * This method updates the data of records in the database
     * @param id    identifier of requested record
     * @param name_ name of requested record
     * @param price_  price of requested record  
     * @param quantity_ quantity of requested record 
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public  void updatePlyta(int id,String name_, float price_, float quantity_) throws SQLException, ClassNotFoundException{
        String sql="update plyty set cena='"+price_+"', ilosc='"+quantity_+"',nazwa='"+name_+"' where id='"+id+"' ";
        DButil.dbConnect();
        try{
            DButil.dbExcecuteQuery(sql);
        }catch(SQLException e){
            System.out.println("error");
            e.printStackTrace();
            throw e;
        }finally{
            DButil.dbDisconnect();
        }
}
}
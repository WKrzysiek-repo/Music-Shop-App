/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import util.DButil;

/**
 *
 * @author Wojciech
 * @version 1.0
 * @see APDAO
 * @see java.sql.ResultSet 
 * @see java.sql.SQLException
 * @see java.util.List
 * @see java.util.LinkedList
 */
public class APDAO {
    
    /**
     * This method gets all records and authors in database, based on author id
     * @param autor_id identifier of requested record
     * @return AP object if id exists in database or null if doesn't
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public AP getPlytaByAutorID(int autor_id) throws SQLException, ClassNotFoundException{
        AP tmp = null;
        ResultSet rsSet = null;
        DButil.dbConnect();
        try {
            String sql_query = "SELECT plyty.id, plyty.nazwa, plyty.cena, plyty.ilosc, autorzy.id, autorzy.name, autorzy.year FROM plyty " +
                                   "LEFT OUTER JOIN plyty_autorzy " +
                                   "ON plyty.id = plyty_autorzy.plyta " +
                                   "LEFT OUTER JOIN autorzy " +
                                   "ON plyty_autorzy.autor = autorzy.id " +
                                   "WHERE plyty.id = " + autor_id;
            

            rsSet = DButil.dbExecute(sql_query);


            while(rsSet.next()) {
                tmp = new AP(rsSet.getString(2),
                                rsSet.getFloat(3),
                                rsSet.getInt(4),
                                rsSet.getString(6),
                                rsSet.getInt(7));
                              
                tmp.setPlyta_id(rsSet.getInt(1)); 
                tmp.setAutor_id(rsSet.getInt(5)); 
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
     * This method displays all records and authors in database
     * @return  Author and Plyta list of requested records
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public List<AP> getPlytaAutorALL() throws SQLException, ClassNotFoundException{
        AP tmp = null;
        ResultSet rsSet = null;
        
        List<AP> ap_list= new LinkedList<>();
        DButil.dbConnect();
        try {
            String sql_query = "SELECT plyty.id, plyty.nazwa, plyty.cena, plyty.ilosc, autorzy.id, autorzy.name, autorzy.year FROM plyty " +
                                   "LEFT OUTER JOIN plyty_autorzy " +
                                   "ON plyty.id = plyty_autorzy.plyta " +
                                   "LEFT OUTER JOIN autorzy " +
                                   "ON plyty_autorzy.autor = autorzy.id ";


            rsSet = DButil.dbExecute(sql_query);


            while(rsSet.next()) {
                tmp = new AP(rsSet.getString(2),
                                rsSet.getFloat(3),
                                rsSet.getInt(4),
                                rsSet.getString(6),
                                rsSet.getInt(7));
                              
                tmp.setPlyta_id(rsSet.getInt(1)); 
                tmp.setAutor_id(rsSet.getInt(5));
                
                ap_list.add(tmp);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            rsSet.close();
            DButil.dbDisconnect();
            
        }
        return ap_list;  
    } 

   /**
     * This method allows to search records and authors based on existing preferences
     * @param recordName name identifier of requested record
     * @param recordPrice price of requested record
     * @param recordQuantity quantity of requested record
     * @param authorName name of requested author
     * @param authorYear year of requested author
     * @return authors and recors list
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */
    public List<AP> getPlytaAutorFilter(String  recordName, String recordPrice, String recordQuantity, String authorName, String authorYear) throws SQLException, ClassNotFoundException{
        AP tmp = null;
        ResultSet rsSet = null;
        int counterWhere = 0;
        
        List<AP> ap_list= new LinkedList<>();
        DButil.dbConnect();
        try {
            String sql_query = "SELECT plyty.id, plyty.nazwa, plyty.cena, plyty.ilosc, autorzy.id, autorzy.name, autorzy.year FROM plyty_autorzy " +
                                   "JOIN plyty " +
                                   "ON plyty_autorzy.plyta = plyty.id " +
                                   "JOIN autorzy " +
                                   "ON plyty_autorzy.autor = autorzy.id ";
            if(recordName != null && !recordName.trim().isEmpty()){
                if( counterWhere != 0 ){
                    sql_query += "AND ";
                }else{
                    sql_query += "WHERE ";
                }
                sql_query += "plyty.nazwa LIKE \'" + recordName  + "%\' ";
                counterWhere++;
            }
             if(recordPrice != null && !recordPrice.trim().isEmpty()){
                if( counterWhere != 0 ){
                    sql_query += "AND ";
                }else{
                    sql_query += "WHERE ";
                }
                sql_query += "plyty.cena LIKE \'" + recordPrice  + "%\' ";
                counterWhere++;
            }
               if(recordQuantity != null && !recordQuantity.trim().isEmpty()){
                if( counterWhere != 0 ){
                    sql_query += "AND ";
                }else{
                    sql_query += "WHERE ";
                }
                sql_query += "plyty.ilosc LIKE \'" + recordQuantity  + "%\' ";
                counterWhere++;
            }
         
            if(authorName != null && !authorName.trim().isEmpty()){
                if( counterWhere != 0 ){
                    sql_query += "AND ";
                }else{
                    sql_query += "WHERE ";
                }
                sql_query += "autorzy.name LIKE \'" + authorName  + "%\' ";
                counterWhere++;
            }
            
                        if(authorYear != null && !authorYear.trim().isEmpty()){
                if( counterWhere != 0 ){
                    sql_query += "AND ";
                }else{
                    sql_query += "WHERE ";
                }
                sql_query += "autorzy.year LIKE \'" + authorYear  + "%\' ";
                counterWhere++;
            }
            
            rsSet = DButil.dbExecute(sql_query);


            while(rsSet.next()) {
                tmp = new AP(rsSet.getString(2),
                                rsSet.getFloat(3),
                                rsSet.getInt(4),
                                rsSet.getString(6),
                                rsSet.getInt(7));
                              
                tmp.setPlyta_id(rsSet.getInt(1)); 
                tmp.setAutor_id(rsSet.getInt(5));
                ap_list.add(tmp);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            rsSet.close();
            DButil.dbDisconnect();
            
        }
        return ap_list;  
    } 
    
    /**
     * This method creates relationships between the records and authors
     * @param PID identifier of requested record
     * @param AID identifier of requested author
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     * @throws SQLException an exception that provides information on a database access error
     */

    public void insertAP(Integer PID, Integer AID) throws ClassNotFoundException, SQLException{
        String sql="insert into plyty_autorzy(plyta, autor) values('"+PID+"', '"+AID+"')";
        DButil.dbConnect();         
        try{
            DButil.dbExcecuteQuery(sql);
            
        }catch(SQLException e){
            System.out.println("Exception"+e);
            e.printStackTrace();
            throw e;
        } finally{
            DButil.dbDisconnect();
            
        }
    }
}

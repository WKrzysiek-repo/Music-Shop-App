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
 * @see AuthorDAO
 * @see java.sql.ResultSet 
 * @see java.sql.SQLException
 * @see java.util.List
 * @see java.util.LinkedList
 */
public class AuthorDAO {
    
    /**
     * This method gets authors in database, based on id
     * @param author_id identifier of requested author
     * @return Author object if id exists in database or null if doesn't
     * @throws SQLException an exception that provides information on a database access error 
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public Author getAuthorByID(int author_id) throws SQLException, ClassNotFoundException{
        Author tmp = null;
        ResultSet rsSet = null;
         DButil.dbConnect();
        try {
            rsSet = DButil.dbExecute("SELECT * FROM autorzy WHERE autorzy.id=" + author_id);
            while(rsSet.next()) {
                
                tmp = new Author(rsSet.getString("name"),
                                rsSet.getInt("year"));
                              
                
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
     * This method gets authors in database, based on names
     * @param author_name name of requested author
     * @return Author object if name exists in database or null if doesn't
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public Author getAuthorByName(int author_name) throws SQLException, ClassNotFoundException{
        Author tmp = null;
        ResultSet rsSet = null;
        DButil.dbConnect();
        try {
            rsSet = DButil.dbExecute("SELECT * autorzy WHERE autorzy.name=" + author_name);
            while(rsSet.next()) {
                
                tmp = new Author(rsSet.getString("name"),
                                rsSet.getInt("year"));
                              
                
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
     * This method displays authors in database
     * @return Author list
     * @throws SQLException an exception that provides information on a database access error 
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public List<Author> getAllAuthor() throws SQLException, ClassNotFoundException{
        Author tmp = null;
        ResultSet rsSet = null;
        
        List<Author> Authors= new LinkedList<>();
        
        DButil.dbConnect();
        try {
            rsSet = DButil.dbExecute("SELECT * FROM autorzy");
            while(rsSet.next()) {
                
                tmp = new Author(rsSet.getString("name"),
                                rsSet.getInt("year"));
                
                tmp.setId(rsSet.getInt("id"));
                Authors.add(tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            rsSet.close();   
            DButil.dbDisconnect();   
        }
        
        return Authors;
        
    }     
    
     /**
     * This method inserts the data of authors in the database
     * @param name_ name of author to insert
     * @param year_ author's year of creation
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     * @throws SQLException an exception that provides information on a database access error
     */

    public  void insertAuthor(String name_, Integer year_) throws ClassNotFoundException, SQLException{
        String sql="insert into autorzy(name, year) values('"+name_+"', '"+year_+"')";
               
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
     * This method updates the data of authors in the database
     * @param id  identifier of author to update  
     * @param name_ name of requested author
     * @param year_ autor's year of creation
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public  void updateAuthor(int id, String name_, Integer year_) throws SQLException, ClassNotFoundException{
        String sql="update autorzy set name='"+name_+"', year='"+year_+"' where id='"+id+"' ";
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

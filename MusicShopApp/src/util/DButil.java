/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wojciech
 * @see DButil
 */
public class DButil {
    
    /**
     *
     */
    public static Connection conn = null;

    /**
     * This method connects the program to the database
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public static void dbConnect() throws SQLException, ClassNotFoundException{
     
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_shop?serverTimezone=UTC","wojtek","wojtek");
        } catch(SQLException e){
            System.out.println("Zerwano połączenie z bazą danych!");
            throw e;
            }
        }
            
     /**
     * This method disconnects the program from the database
     * @throws SQLException an exception that provides information on a database access error
     */

    public static void dbDisconnect() throws SQLException{
        
        try{
            if(conn !=null && !conn.isClosed()){
                conn.close();
            }
        }
        catch(SQLException e){
            throw e;
        }
    }
            
    /**
     **
     * This metod provides to interact with database
     * @param sqlStmt set up to consolidate MySQL queries and issues
     * @throws SQLException an exception that provides information on a database access error
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     */

    public static void dbExcecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try{
            dbConnect();
            stmt=conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
        catch(SQLException e){
            System.out.println("Zerwano połączenie z bazą danych!");
            throw e;
        }
        finally{
            if(stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
    }
    
    /**
     * This metod provides  to interact with  database
     * @param sqlQuery set up to consolidate MySQL queries and issues
     * @return result set
     * @throws ClassNotFoundException thrown when an application tries to load in a class through its string name using,but no definition for the class with the specified name could be found
     * @throws SQLException an exception that provides information on a database access error
     */

    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException,SQLException, Exception{
        Statement stmt=null;
        ResultSet rs=null;

        
        try
        {
            
            if(conn == null || conn.isClosed()){
                throw new Exception("Zerwano połączenie z bazą danych!");
            }
        }
        catch(Exception e){
            throw e;
        }
            
        
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sqlQuery);
        }
        catch(Exception e){
            System.out.println("Zerwano połączenie z bazą danych!");
            throw e;
        }

        return rs;
    }


  }

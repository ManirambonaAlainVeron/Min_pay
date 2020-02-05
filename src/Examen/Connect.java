/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Connect {
    
            private static Connection con=null;

    public static int modifierDB(String sql){
        
            int result=-1;
            try {
                
            if(con==null)
                if(!createConnection())
                    return result;
                            
            Statement st=con.createStatement();
            result=st.executeUpdate(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /*
    select
    */
    public static ResultSet extraireData(String requete)
    {
             ResultSet result=null;

                try {
                    if(con==null)
                        if(!createConnection())
                            return result;
                    Statement st=con.createStatement();
                    result=st.executeQuery(requete);
                } catch (SQLException ex) {
                    Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                }
               return result;
    }
    
    static boolean createConnection(){
        boolean status=false;
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/minerval","root", "");
            status=true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }

    static String createConnection(String select_curdate) {
        return ""+Examen.Connect.extraireData(select_curdate);
    }
    
  
    
}

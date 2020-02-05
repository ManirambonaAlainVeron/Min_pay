/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Generalites {
    
        
      public static void Donner_info_User(String msg){
    
             JOptionPane.showMessageDialog(null, msg, "Boite d'information", JOptionPane.INFORMATION_MESSAGE);
       
    }
    public static void Centrer_Fenetres(JFrame win){
        
        Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();    
        win.setLocation(dim.width/2 - win.getWidth()/2, dim.height/2 - win.getHeight ()/2);
    }
    
    public static int getResultSetRowCount(ResultSet resultSet) {
    int size = 0;
    try {
        resultSet.last();
        size = resultSet.getRow();
        resultSet.beforeFirst();
    }
    catch(Exception ex) {
        return 0;
    }
    return size;
}
    
}

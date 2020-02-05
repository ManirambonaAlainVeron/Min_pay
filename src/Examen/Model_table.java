/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class Model_table extends AbstractTableModel {
    
    private String[] titles_;
    private Object [][] data;

    @Override
    public int getRowCount() {
        if(data==null)
           return -1;
        return data.length;
    }

    @Override
    public int getColumnCount() {
         if(this.titles_==null)
            return -1;
        return titles_.length;
    }
    public Model_table(Object[][] t,String [] tit){
    
        this.data=t;
        this.titles_=tit;
    }
    public String getColumnName(int col) {
        return this.titles_[col];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
                return data[rowIndex][columnIndex];

    }
    public boolean isCellEditable(int row, int col){

	//On appelle la méthode getValueAt qui retourne la valeur d'une cellule
	//Et on fait un traitement spécifique si c'est un JButton
	if(getValueAt(0, col) instanceof JButton)
		return false;
	return true; 
}
    
}

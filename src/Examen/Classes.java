/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class Classes {
    private int clId;
    private String nom;
    private int minerval;
    
    public Classes(){}
    public Classes(String no,int min){
        nom=no;
        minerval=min;
    }
   
    public Classes(String no)
    {
        nom=no;
    }
    public int getClId() {
        return clId;
    }

    public void setClId(int clId) {
        this.clId = clId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMinerval() {
        return minerval;
    }

    public void setMinerval(int minerval) {
        this.minerval = minerval;
    }
    public int enregistre_classe()
    {
       return Examen.Connect.modifierDB("insert into classe(nom_cl,minos)values('"+this.nom+"',"+this.minerval+")");
    }
    public int supprimer_classe()
    {
        return Examen.Connect.modifierDB("delete from classe where clId="+this.clId);
    }
     public int modifier_classe()
   {
    return Examen.Connect.modifierDB("update classe set nom_cl='"+this.nom+"',minos= "+this.minerval+" where clId="+this.clId+";");
   }
    
     public void charger_Etudiant_classe(JComboBox <Etudiant> cbx)
     {
         Etudiant et=null;
         ResultSet resu=Examen.Connect.extraireData("select *from etudiant inner join classe_etudiant on etudiant.etId=classe_etudiant.etudiant where classe_etudiant.classe="+this.clId+"&& classe_etudiant.terminer LIKE 'N%'");
          cbx.removeAllItems();
          cbx.addItem(new Etudiant());
          if(resu!=null)
          {
             try {
                 while(resu.next())
                 {
                     et=new Etudiant();
                
                     et.setEtId(resu.getInt("etId"));
                     et.setNom(resu.getString("nom"));
                     et.setPrenom(resu.getString("prenom"));
                     et.setCarte(resu.getString("num_carte"));
                     cbx.addItem(et);
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
             }
          }

     }
    public String toString()
    {
        if(clId==0)
            return"";
        //if(minerval!=0)
        //return this.nom+" "+this.minerval;
        else
            return this.nom+"";
        
    }
    
}

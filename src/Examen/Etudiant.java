/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

/**
 *
 * @author DELL
 */
public class Etudiant {
   private int etId;
   private String nom,prenom,carte;
   public Etudiant(){}
   public Etudiant(String no,String pre,String car){
   nom=no;
   prenom=pre;
   carte=car;
   
   }

   
    public int getEtId() {
        return etId;
    }

    public void setEtId(int etId) {
        this.etId = etId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String carte) {
        this.carte = carte;
    }
   
   public String toString(){
   if(etId==0)
       return"";
   return this.nom+" "+this.prenom+" "+this.carte;
   }
   public int Enregistre_etudiant()
   {
    return Examen.Connect.modifierDB("insert into etudiant(nom,prenom,num_carte)values('"+this.nom+"','"+this.prenom+"','"+this.carte+"')");
   }
   public int modifier_etudiant()
   {
    return Examen.Connect.modifierDB("update etudiant set nom='"+this.nom+"',prenom='"+this.prenom+"',num_carte='"+this.carte+"'where etId="+this.etId+"");
   }
   public int supprime_etudiant()
   {
       return Examen.Connect.modifierDB("delete from etudiant where etId="+this.etId);
   }
    
}

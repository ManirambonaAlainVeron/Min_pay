/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Payement {
    int NumP,Montant,somme;
    String nom,prenom,classe,carte,Tranche;
    String date;
    
    public Payement(){}
    public Payement(int mo,String no,String pre,String c,String tra,String car,String dat)
    {
        Montant=mo;
        nom=no;
        prenom=pre;
        classe=c;
        carte=car;
        Tranche=tra;
        date=dat;
        
    }
    
    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
    public String getTranche() {
        return Tranche;
    }

    public void setTranche(String Tranche) {
        this.Tranche = Tranche;
    }

    public int getNumP() {
        return NumP;
    }

    public void setNumP(int NumP) {
        this.NumP = NumP;
    }

    public int getMontant() {
        return Montant;
    }

    public void setMontant(int Montant) {
        this.Montant = Montant;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String carte) {
        this.carte = carte;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public int Enregistre_payement()
    {
        return  Examen.Connect.modifierDB("insert into payement(nom,prenom,carte,classe,Montant,Tranche,date)values('"+this.nom+"','"+this.prenom+"','"+this.carte+"','"+this.classe+"',"+this.Montant+",'"+this.Tranche+"','"+this.date+"')");
    }
    public int Supprimer_payement()
    {
        return Examen.Connect.modifierDB("Delete from payement where NumP="+this.NumP+"");
    }
    public String toString()
    {
        return this.nom+""+this.prenom+""+this.classe+""+this.carte+""+this.Montant+""+this.date;
    }
}

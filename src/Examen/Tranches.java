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
public class Tranches {
    private int trId;
    private String nom;
    
    public Tranches(){}
    public Tranches(String no)
    {
        nom=no;
    }

    public int getTrId() {
        return trId;
    }

    public void setTrId(int trId) {
        this.trId = trId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String toString()
    {
        if(trId==0)
            return"";
        return this.nom;
    }
    public int enregistre_tranche()
    {
        return Examen.Connect.modifierDB("insert into tranche(nom_tra)values('"+this.nom+"')");
    }
    public int supprime_tranche()
    {
        return Examen.Connect.modifierDB("delete from tranche where trId="+this.trId);
    }
    
}

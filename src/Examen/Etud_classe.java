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
public class Etud_classe {
    private int cletId;
    private int classe;
    private int etudiant;
    private String terminer;
    
    public Etud_classe(){}
    public Etud_classe(int cl,int et,String term)
    {
        classe=cl;
        etudiant=et;
        terminer=term;
    }

    public int getCletId() {
        return cletId;
    }

    public void setCletId(int cletId) {
        this.cletId = cletId;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public int getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(int etudiant) {
        this.etudiant = etudiant;
    }
    

    public String getTerminer() {
        return terminer;
    }

    public void setTerminer(String terminer) {
        this.terminer = terminer;
    }
    public String toString()
    {
       if(cletId==0)
           return"";
       return this.classe+""+this.etudiant+""+this.terminer;
           
    }
    public int enregistre_classe_etudiant()
    {
        return Examen.Connect.modifierDB("insert into classe_etudiant(classe,etudiant,terminer)values('"+this.classe+"','"+this.etudiant+"','"+this.terminer+"')");
    }
    public int terminer()
    {
        String n="OUI";
        
        return Examen.Connect.modifierDB("Update classe_etudiant set terminer='"+n+"'where cletId="+this.cletId+"");
    }
    public int supprimer_classe_etudiant()
    {
        return Examen.Connect.modifierDB("Delete from classe_etudiant where cletId="+this.cletId+"");
    }
    
}

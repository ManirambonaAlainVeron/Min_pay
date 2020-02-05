/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author DELL
 */
public class UI_etud_class extends javax.swing.JFrame {

    /**
     * Creates new form UI_etud_class
     */
    public UI_etud_class() {
        initComponents();
        Examen.Generalites.Centrer_Fenetres(this);
        charger_combo_classe();
        charger_combo_etudiant();
        charger_tab_cl_et(tab_et_cl);
        Deplacer_element();
        jPanel1.setFocusable(true);
        this.setTitle("Application de Gestion du Paiement de Minerval");
    }
     void charger_combo_classe()
    {
       ResultSet resu=Examen.Connect.extraireData("select *from classe");
       comb_cl.addItem(new Classes());
       Classes cl=null;
        if(resu!=null)
        {
           try {
               while(resu.next())
               {
                   cl=new Classes(resu.getString("nom_cl"),resu.getInt("minos"));
                   cl.setClId(resu.getInt("clId"));
                   comb_cl.addItem(cl);
               }
           } catch (SQLException ex) {
               Logger.getLogger(UI_etud_class.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
     void charger_combo_etudiant()
     {
         ResultSet reso=Examen.Connect.extraireData("select *from etudiant");
         comb_et.addItem(new Etudiant());
         Etudiant et=null;
         if(reso!=null)
         {
             try {
                 while(reso.next())
                 {
                     et=new Etudiant(reso.getString("nom"),reso.getString("prenom"),reso.getString("num_carte"));
                     et.setEtId(reso.getInt("etId"));
                     comb_et.addItem(et);
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(UI_etud_class.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
     static Etud_classe clet=null;
     public void Deplacer_element()
     {
         tab_et_cl.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 int index_line=-1;
                 index_line=tab_et_cl.getSelectedRow();
                 if(index_line==-1)
                     return;
                if(clet==null)
         clet=new Etud_classe();
                clet.setCletId(Integer.parseInt(tab_et_cl.getValueAt(index_line,0).toString()));
             }
         });
     }
     public void charger_tab_cl_et(JTable tab)
     {
         ResultSet resulta=Examen.Connect.extraireData("select cletId,nom_cl,nom,prenom,num_carte,terminer from classe inner join classe_etudiant inner join etudiant where clId=classe and etudiant=etId");
         int index,i=0;
         index=Examen.Generalites.getResultSetRowCount(resulta);
         String[]titres={"cletID","Classe","Nom","Prenom","carte","Terminer"};
         String[][]data=new String[index][titres.length];
         if(resulta!=null)
         {
             try {
                 while(resulta.next())
                 {
                     data[i][0]=""+resulta.getString("cletId");
                     data[i][1]=resulta.getString("nom_cl");
                     data[i][2]=resulta.getString("nom");
                     data[i][3]=resulta.getString("prenom");
                     data[i][4]=resulta.getString("num_carte");
                     data[i][5]=resulta.getString("terminer");
                     i++;
                             }
                 tab.setModel(new Model_table(data,titres));
             } catch (SQLException ex) {
                 Logger.getLogger(UI_etud_class.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         
         
     }
     public void charger_tab_recherche(JTable tab)
     {
         ResultSet resulta=Examen.Connect.extraireData("select cletId,nom_cl,nom,prenom,num_carte,terminer from classe inner join classe_etudiant inner join etudiant where clId=classe and etudiant=etId and num_carte='"+txt_recherche.getText()+"'");
         int index,i=0;
         index=Examen.Generalites.getResultSetRowCount(resulta);
         String[]titres={"cletID","Classe","Nom","Prenom","carte","Terminer"};
         String[][]data=new String[index][titres.length];
         if(resulta!=null)
         {
             try {
                 while(resulta.next())
                 {
                     data[i][0]=""+resulta.getString("cletId");
                     data[i][1]=resulta.getString("nom_cl");
                     data[i][2]=resulta.getString("nom");
                     data[i][3]=resulta.getString("prenom");
                     data[i][4]=resulta.getString("num_carte");
                     data[i][5]=resulta.getString("terminer");
                     i++;
                             }
                 tab.setModel(new Model_table(data,titres));
             } catch (SQLException ex) {
                 Logger.getLogger(UI_etud_class.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         
         
     }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comb_et = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comb_cl = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comb_te = new javax.swing.JComboBox<>();
        bt_enregistre = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_et_cl = new javax.swing.JTable();
        bt_termine = new javax.swing.JButton();
        bt_recherche = new javax.swing.JButton();
        bt_actualiser = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_recherche = new javax.swing.JTextField();
        bt_supprimer = new javax.swing.JButton();
        bt_print = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Item_etudiant = new javax.swing.JMenuItem();
        Item_classe = new javax.swing.JMenuItem();
        Item_tranche = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Item_login = new javax.swing.JMenuItem();
        Item_fermer = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Enregistrement  Etudiant -> Classe  ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Etudiant:");

        comb_et.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_etActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Classe:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Terminer:");

        comb_te.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        comb_te.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NON", "OUI" }));
        comb_te.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_teActionPerformed(evt);
            }
        });

        bt_enregistre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_enregistre.setText("Enregistrer");
        bt_enregistre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enregistreActionPerformed(evt);
            }
        });

        tab_et_cl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tab_et_cl);

        bt_termine.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_termine.setText("Terminer");
        bt_termine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_termineActionPerformed(evt);
            }
        });

        bt_recherche.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_recherche.setIcon(new javax.swing.ImageIcon("E:\\Logos\\recher.JPG")); // NOI18N
        bt_recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_rechercheActionPerformed(evt);
            }
        });

        bt_actualiser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_actualiser.setText("Actualiser");
        bt_actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actualiserActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Rechercher par numero carte:");

        txt_recherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_recherche.setForeground(new java.awt.Color(102, 102, 102));
        txt_recherche.setText("Tapez numero carte ici");
        txt_recherche.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rechercheFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rechercheFocusLost(evt);
            }
        });

        bt_supprimer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_supprimer.setText("Supprimer");
        bt_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_supprimerActionPerformed(evt);
            }
        });

        bt_print.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_print.setText("Imprimer");
        bt_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_printActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("E:\\Logos\\ult.jfif")); // NOI18N
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_enregistre, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_termine, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_print, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comb_te, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comb_cl, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comb_et, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comb_cl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(comb_et, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(comb_te, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(bt_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_enregistre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bt_termine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_print, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Enregistrer");

        Item_etudiant.setText("Etudiant");
        Item_etudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_etudiantActionPerformed(evt);
            }
        });
        jMenu1.add(Item_etudiant);

        Item_classe.setText("Classe");
        Item_classe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_classeActionPerformed(evt);
            }
        });
        jMenu1.add(Item_classe);

        Item_tranche.setText("Tranche");
        Item_tranche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_trancheActionPerformed(evt);
            }
        });
        jMenu1.add(Item_tranche);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Retourner");

        Item_login.setText("Vers Login");
        Item_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_loginActionPerformed(evt);
            }
        });
        jMenu2.add(Item_login);

        Item_fermer.setText("Fermer");
        Item_fermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_fermerActionPerformed(evt);
            }
        });
        jMenu2.add(Item_fermer);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Historique");

        jMenuItem2.setText("Etudiant");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comb_teActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_teActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_teActionPerformed

    private void bt_enregistreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enregistreActionPerformed
        // TODO add your handling code here:
        Classes cl=(Classes) comb_cl.getSelectedItem();
        Etudiant et=(Etudiant) comb_et.getSelectedItem();
        if(cl.getClId()==0)
        {
            Examen.Generalites.Donner_info_User("Choisissez d'abord la classe!");
            return;

        }
        if(et.getEtId()==0)
        {
            Examen.Generalites.Donner_info_User("Choisissez l'etudiant!");
            return;

        }
        Etud_classe etcl=new Etud_classe();
        etcl.setClasse(cl.getClId());
        etcl.setEtudiant(et.getEtId());
        etcl.setTerminer((String) comb_te.getSelectedItem());
        
        if(etcl.enregistre_classe_etudiant()>0)
        {
            charger_tab_cl_et(tab_et_cl);

            Examen.Generalites.Donner_info_User("L'enregistrement est reussie!");
            comb_cl.removeAllItems();
            comb_et.removeAllItems();
            charger_combo_classe();
            charger_combo_etudiant();
            
        }
        else
        {
            Examen.Generalites.Donner_info_User("Echec de l'enregistrement");
        }
        
        
    }//GEN-LAST:event_bt_enregistreActionPerformed

    private void bt_termineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_termineActionPerformed
        // TODO add your handling code here:
        try{
        if(clet.terminer()>0)
        {
            Examen.Generalites.Donner_info_User("OK,l'etudiant n'est plus dans la classe!");
            charger_tab_cl_et(tab_et_cl);
             comb_cl.removeAllItems();
            comb_et.removeAllItems();
            charger_combo_classe();
            charger_combo_etudiant();
            return;
        }
        }catch(NullPointerException e){
            Examen.Generalites.Donner_info_User("Selectionnez d'abord dans la table l'etudiant terminé!");
        }
        
        
    }//GEN-LAST:event_bt_termineActionPerformed

    private void bt_actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualiserActionPerformed
        // TODO add your handling code here:
        charger_tab_cl_et(tab_et_cl);
        txt_recherche.setForeground(new Color(102,102,102));
        txt_recherche.setText("Tapez numero carte ici");
        
    }//GEN-LAST:event_bt_actualiserActionPerformed

    private void bt_rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_rechercheActionPerformed
        // TODO add your handling code here:
        if(txt_recherche.getText().trim().equalsIgnoreCase("")||txt_recherche.getText().trim().equalsIgnoreCase("Tapez numero carte ici"))
        {
            Examen.Generalites.Donner_info_User("Saisissez d'abord le numero a chercher dans la zonne de saisie!");
            return;
        }
        else{
        charger_tab_recherche(tab_et_cl);
        }
        
    }//GEN-LAST:event_bt_rechercheActionPerformed

    private void bt_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_supprimerActionPerformed
        // TODO add your handling code here:
       
        try{
             if(clet.supprimer_classe_etudiant()>0)
        {
            Examen.Generalites.Donner_info_User("La suppression est reussit!");
            charger_tab_recherche(tab_et_cl);
             comb_cl.removeAllItems();
            comb_et.removeAllItems();
            charger_combo_classe();
            charger_combo_etudiant();
        }
        else
        {
            Examen.Generalites.Donner_info_User("Echec de la suppression");
        }
            
        }catch(NullPointerException e){
          Examen.Generalites.Donner_info_User("Selectionner dans le tableau la ligne a supprimer!");

        }
       
        
    }//GEN-LAST:event_bt_supprimerActionPerformed

    private void bt_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_printActionPerformed
        // TODO add your handling code here:
                try {
            // TODO add your handling code here:
            MessageFormat header=new MessageFormat("Liste Etudiant -> Classe à universite du lac Tanganyika(ULT)");
            MessageFormat footer=new MessageFormat("");
            Boolean a=tab_et_cl.print(JTable.PrintMode.NORMAL, header, footer);
            
            if(a)
            {
                Examen.Generalites.Donner_info_User("OK,l'Impression est finie");
                
            }
            else
            {
                Examen.Generalites.Donner_info_User("Impression est annulé.......");
            }} catch (PrinterException ex) {
            Logger.getLogger(UI_direction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_printActionPerformed

    private void Item_etudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_etudiantActionPerformed
        // TODO add your handling code here:
        UI_etudiant et=new UI_etudiant();
        et.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Item_etudiantActionPerformed

    private void Item_classeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_classeActionPerformed
        // TODO add your handling code here:
        UI_classe cl=new UI_classe();
        cl.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Item_classeActionPerformed

    private void Item_trancheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_trancheActionPerformed
        // TODO add your handling code here:
        UI_tranche tra=new UI_tranche();
        tra.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Item_trancheActionPerformed

    private void Item_fermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_fermerActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_Item_fermerActionPerformed

    private void Item_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_loginActionPerformed
        // TODO add your handling code here:
        UI_authentifier d=new UI_authentifier ();
        d.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Item_loginActionPerformed

    private void comb_etActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_etActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_etActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
         UI_historique h=new UI_historique();
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txt_rechercheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rechercheFocusGained
        // TODO add your handling code here:
                  if(txt_recherche.getText().equals("Tapez numero carte ici"))
        {
            txt_recherche.setText("");
            txt_recherche.setForeground(new Color(0,0,0));
        }
        
    }//GEN-LAST:event_txt_rechercheFocusGained

    private void txt_rechercheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rechercheFocusLost
        // TODO add your handling code here:
            if(txt_recherche.getText().equals(""))
        {
            txt_recherche.setText("Tapez numero carte ici");
            txt_recherche.setForeground(new Color(102,102,102));
        }
         else if(txt_recherche.getText()!=""&&txt_recherche.getText()!="Tapez numero carte ici")
         {
             txt_recherche.setForeground(new Color(0,0,0));
         }
    }//GEN-LAST:event_txt_rechercheFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI_etud_class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_etud_class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_etud_class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_etud_class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_etud_class().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Item_classe;
    private javax.swing.JMenuItem Item_etudiant;
    private javax.swing.JMenuItem Item_fermer;
    private javax.swing.JMenuItem Item_login;
    private javax.swing.JMenuItem Item_tranche;
    private javax.swing.JButton bt_actualiser;
    private javax.swing.JButton bt_enregistre;
    private javax.swing.JButton bt_print;
    private javax.swing.JButton bt_recherche;
    private javax.swing.JButton bt_supprimer;
    private javax.swing.JButton bt_termine;
    private javax.swing.JComboBox<Classes> comb_cl;
    private javax.swing.JComboBox<Etudiant> comb_et;
    private javax.swing.JComboBox<String> comb_te;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tab_et_cl;
    private javax.swing.JTextField txt_recherche;
    // End of variables declaration//GEN-END:variables
}

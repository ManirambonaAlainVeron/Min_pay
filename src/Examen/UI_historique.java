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
import javax.swing.event.TableModelListener;

/**
 *
 * @author DELL
 */
public class UI_historique extends javax.swing.JFrame {

    /**
     * Creates new form UI_historique
     */
    public UI_historique() {
        initComponents();
        Examen.Generalites.Centrer_Fenetres(this);
        charge_table(tab_hist);
        jPanel1.setFocusable(true);
        this.setTitle("Application de Gestion du Paiement de Minerval");
    }
    
    public static  void charge_table(JTable tab)
    {
        int i=0,index=0;
        ResultSet resu=Examen.Connect.extraireData("select *from historique");
        index=Examen.Generalites.getResultSetRowCount(resu);
        String titres[]={"Identifiant","nom","prenom","numero carte"};
        String data[][]=new String[index][titres.length];
        
        if(resu!=null)
        {
            try {
                while(resu.next())
                {
                    data[i][0]=""+resu.getString("histId");
                    data[i][1]=resu.getString("nom");
                    data[i][2]=resu.getString("prenom");
                    data[i][3]=resu.getString("num_carte");
                    
                    i++;
                }
                tab.setModel(new Model_table(data,titres));
            } catch (SQLException ex) {
                Logger.getLogger(UI_historique.class.getName()).log(Level.SEVERE, null, ex);
                
                
            }
        }
    }
    
    public void rechercher_par_carte(JTable tab)
    {
         int i=0,index=0;
        ResultSet resu=Examen.Connect.extraireData("select *from historique where num_carte='"+txt_rechercher.getText()+"'");
        index=Examen.Generalites.getResultSetRowCount(resu);
        String titres[]={"Identifiant","nom","prenom","numero carte"};
        String data[][]=new String[index][titres.length];
        
        if(resu!=null)
        {
            try {
                while(resu.next())
                {
                    data[i][0]=""+resu.getString("histId");
                    data[i][1]=resu.getString("nom");
                    data[i][2]=resu.getString("prenom");
                    data[i][3]=resu.getString("num_carte");
                    
                    i++;
                }
                tab.setModel(new Model_table(data,titres));
            } catch (SQLException ex) {
                Logger.getLogger(UI_historique.class.getName()).log(Level.SEVERE, null, ex);
                
                
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_hist = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bt_impr = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_rechercher = new javax.swing.JTextField();
        bt_rechercher = new javax.swing.JButton();
        bt_act = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        tab_hist.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tab_hist);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Histortique des Etudiants");

        jLabel2.setIcon(new javax.swing.ImageIcon("E:\\Logos\\ult.jfif")); // NOI18N
        jLabel2.setText("jLabel2");

        bt_impr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bt_impr.setText("Imprimer");
        bt_impr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Rechercher par carte:");

        txt_rechercher.setForeground(new java.awt.Color(102, 102, 102));
        txt_rechercher.setText("Tapez numero du carte ici");
        txt_rechercher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rechercherFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rechercherFocusLost(evt);
            }
        });

        bt_rechercher.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_rechercher.setIcon(new javax.swing.ImageIcon("E:\\Logos\\recher.JPG")); // NOI18N
        bt_rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_rechercherActionPerformed(evt);
            }
        });

        bt_act.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bt_act.setText("Actualiser");
        bt_act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(bt_impr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_act, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(199, 199, 199))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_impr, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(bt_act, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txt_rechercher, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jMenu1.setText("Retourner");

        jMenuItem1.setText("Vers Login");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Fermer");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        UI_authentifier a=new UI_authentifier();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void bt_rechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_rechercherActionPerformed
        // TODO add your handling code here:
        if(txt_rechercher.getText().trim().equalsIgnoreCase("")||txt_rechercher.getText().trim().equalsIgnoreCase("Tapez numero du carte ici"))
        {
            Examen.Generalites.Donner_info_User("Saisissez le numero du carte d'etudiant à chercher!");
            return;
        }
        else{
        rechercher_par_carte(tab_hist);
        }
        
    }//GEN-LAST:event_bt_rechercherActionPerformed

    private void bt_actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actActionPerformed
        // TODO add your handling code here:
       charge_table(tab_hist);
       txt_rechercher.setForeground(new Color(102,102,102));

       txt_rechercher.setText("Tapez numero du carte ici");
    }//GEN-LAST:event_bt_actActionPerformed

    private void bt_imprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprActionPerformed
        // TODO add your handling code here:
          try {
            // TODO add your handling code here:
            MessageFormat header=new MessageFormat("Liste des etudiants qui sont passes a l' ULT");
            MessageFormat footer=new MessageFormat("");
            Boolean a=tab_hist.print(JTable.PrintMode.NORMAL, header, footer);
            
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
    }//GEN-LAST:event_bt_imprActionPerformed

    private void txt_rechercherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rechercherFocusGained
        // TODO add your handling code here:
         if(txt_rechercher.getText().equals("Tapez numero du carte ici"))
        {
            txt_rechercher.setText("");
            txt_rechercher.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_rechercherFocusGained

    private void txt_rechercherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rechercherFocusLost
        // TODO add your handling code here:
           if(txt_rechercher.getText().equals(""))
        {
            txt_rechercher.setText("Tapez numero du carte ici");
            txt_rechercher.setForeground(new Color(102,102,102));
        }
         else if(txt_rechercher.getText()!=""&&txt_rechercher.getText()!="Tapez numero du carte ici")
         {
             txt_rechercher.setForeground(new Color(0,0,0));
         }
    }//GEN-LAST:event_txt_rechercherFocusLost

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
            java.util.logging.Logger.getLogger(UI_historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_historique().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_act;
    private javax.swing.JButton bt_impr;
    private javax.swing.JButton bt_rechercher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tab_hist;
    private javax.swing.JTextField txt_rechercher;
    // End of variables declaration//GEN-END:variables
}

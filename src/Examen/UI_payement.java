/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import static Examen.UI_etud_class.clet;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.LocalDate.now;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author DELL
 */
public class UI_payement extends javax.swing.JFrame {

    /**
     * Creates new form UI_payement
     */
    public UI_payement() {
        initComponents();
        Examen.Generalites.Centrer_Fenetres(this);
        charger_comb_classe();
        charge_combo_tranche();
        Charger_table(tab_payement);
        deplacer_paiement();
        jPanel1.setFocusable(true);
        this.setTitle("Application de Gestion du Paiement de Minerval");
    }
    void charger_comb_classe()
    {
        ResultSet resul=Examen.Connect.extraireData("select *from classe where clId IN(select classe from classe_etudiant)");
        comb_cl.addItem(new Classes());
        Classes cl=null;
        if(resul!=null)
        {
            try {
                while(resul.next())
                {
                    cl=new Classes(resul.getString("nom_cl"));
                    cl.setMinerval(Integer.parseInt(resul.getString("minos")));
                    cl.setClId(resul.getInt("clId"));
                    comb_cl.addItem(cl);
                            }
            } catch (SQLException ex) {
                Logger.getLogger(UI_payement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    void charge_combo_tranche()
    {
        ResultSet result=Examen.Connect.extraireData("select *from tranche");
        combo_tra.addItem(new Tranches());
        Tranches t=null;
        if(result!=null)
        {
            try {
                while(result.next())
                {
                    t=new Tranches(result.getString("nom_tra"));
                    t.setTrId(result.getInt("trId"));
                    combo_tra.addItem(t);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(UI_payement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void Charger_table(JTable tab)
    {
        ResultSet resu=Examen.Connect.extraireData("Select *from payement");
        int index,i=0;
        index=Examen.Generalites.getResultSetRowCount(resu);
        String[] titres={"NumP","Nom","Prenom","Carte","Classe","date","Tranche","Montant"};
        String[][]data=new String[index][titres.length];
        if(resu!=null)
        {
            try {
                while(resu.next())
                {
                    data[i][0]=""+resu.getString("NumP");
                    data[i][1]=resu.getString("nom");
                    data[i][2]=resu.getString("Prenom");
                    data[i][3]=resu.getString("carte");
                    data[i][4]=resu.getString("Classe");
                    data[i][5]=resu.getString("date");
                    data[i][6]=resu.getString("Tranche");
                    data[i][7]=resu.getString("Montant");
                    i++;
                    
                }
                tab.setModel(new Model_table(data,titres));
            } catch (SQLException ex) {
                Logger.getLogger(UI_payement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void charge_tab_recherche(JTable tab)
    {
        ResultSet resul=Examen.Connect.extraireData("select *from payement where carte='"+txt_cherche.getText()+"'");
                int index,i=0;
        index=Examen.Generalites.getResultSetRowCount(resul);
        String[] titres={"NumP","Nom","Prenom","Carte","Classe","date","Tranche","Montant"};
        String[][]data=new String[index][titres.length];
        if(resul!=null)
        {
            try {
                while(resul.next())
                {
                    data[i][0]=""+resul.getString("NumP");
                    data[i][1]=resul.getString("nom");
                    data[i][2]=resul.getString("Prenom");
                    data[i][3]=resul.getString("carte");
                    data[i][4]=resul.getString("Classe");
                    data[i][5]=resul.getString("date");
                    data[i][6]=resul.getString("Tranche");
                    data[i][7]=resul.getString("Montant");
                    i++;
                    
                }
                tab.setModel(new Model_table(data,titres));
            } catch (SQLException ex) {
                Logger.getLogger(UI_payement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public void charge_tab_checrh_classe(JTable tab)
    {
               ResultSet resu=Examen.Connect.extraireData("select *from payement where Classe='"+txt_cherch_cl.getText()+"'and carte not in(select num_carte from etudiant inner join classe_etudiant on etID=etudiant where terminer='OUI')");
        int index,i=0;
        index=Examen.Generalites.getResultSetRowCount(resu);
        String[] titres={"NumP","Nom","Prenom","Carte","Classe","date","Tranche","Montant"};
        String[][]data=new String[index][titres.length];
        if(resu!=null)
        {
            try {
                while(resu.next())
                {
                    data[i][0]=""+resu.getString("NumP");
                    data[i][1]=resu.getString("nom");
                    data[i][2]=resu.getString("Prenom");
                    data[i][3]=resu.getString("carte");
                    data[i][4]=resu.getString("Classe");
                    data[i][5]=resu.getString("date");
                    data[i][6]=resu.getString("Tranche");
                    data[i][7]=resu.getString("Montant");
                    i++;
                }
                tab.setModel(new Model_table(data,titres));
            } catch (SQLException ex) {
                Logger.getLogger(UI_direction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
      static Payement pay=null;
      public void deplacer_paiement()
      {
             tab_payement.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 int index_line=-1;
                 index_line=tab_payement.getSelectedRow();
                 if(index_line==-1)
                     return;
                if(pay==null)
         pay=new Payement();
                pay.setNumP(Integer.parseInt(tab_payement.getValueAt(index_line,0).toString()));
             }
         });
             
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comb_cl = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        combo_et = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        combo_tra = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_montant = new javax.swing.JTextField();
        bt_payer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_date = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_payement = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_paye = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_a_paye = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_reste = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_cherche = new javax.swing.JTextField();
        bt_cherche = new javax.swing.JButton();
        bt_actualiser = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_cherch_cl = new javax.swing.JTextField();
        bt_cherch_classe = new javax.swing.JButton();
        bt_supprimer = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Payement de Minerval ULT");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Classe:");

        comb_cl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comb_clItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Etudiant:");

        combo_et.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_etActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tranche:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Montant:");

        txt_montant.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_montant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_montantActionPerformed(evt);
            }
        });
        txt_montant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_montantKeyPressed(evt);
            }
        });

        bt_payer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bt_payer.setText("Payer");
        bt_payer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_payerActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Date:");

        txt_date.setEditable(false);
        txt_date.setBackground(new java.awt.Color(153, 153, 255));
        txt_date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dateActionPerformed(evt);
            }
        });

        tab_payement.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tab_payement);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Montant payé:");

        txt_paye.setEditable(false);
        txt_paye.setBackground(new java.awt.Color(204, 204, 255));
        txt_paye.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Montant à  payer:");

        txt_a_paye.setEditable(false);
        txt_a_paye.setBackground(new java.awt.Color(204, 204, 255));
        txt_a_paye.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Reste:");

        txt_reste.setEditable(false);
        txt_reste.setBackground(new java.awt.Color(204, 204, 255));
        txt_reste.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_reste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_resteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Rechercher par carte:");

        txt_cherche.setForeground(new java.awt.Color(102, 102, 102));
        txt_cherche.setText("Tapez numero de carte ici");
        txt_cherche.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_chercheFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_chercheFocusLost(evt);
            }
        });
        txt_cherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chercheActionPerformed(evt);
            }
        });

        bt_cherche.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        bt_cherche.setIcon(new javax.swing.ImageIcon("E:\\Logos\\recher.JPG")); // NOI18N
        bt_cherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_chercheActionPerformed(evt);
            }
        });

        bt_actualiser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bt_actualiser.setText("Actualiser");
        bt_actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actualiserActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Rechercher par Classe:");

        txt_cherch_cl.setForeground(new java.awt.Color(102, 102, 102));
        txt_cherch_cl.setText("Tapez le nom de la classe ici");
        txt_cherch_cl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cherch_clFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cherch_clFocusLost(evt);
            }
        });

        bt_cherch_classe.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        bt_cherch_classe.setIcon(new javax.swing.ImageIcon("E:\\Logos\\recher.JPG")); // NOI18N
        bt_cherch_classe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cherch_classeActionPerformed(evt);
            }
        });

        bt_supprimer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bt_supprimer.setText("Supprimer");
        bt_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_supprimerActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon("E:\\Logos\\ult.jfif")); // NOI18N
        jLabel12.setText("jLabel12");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_a_paye, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_paye, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 96, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_payer, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(bt_supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(combo_tra, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txt_montant, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(comb_cl, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_et, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(72, 72, 72)
                .addComponent(txt_reste, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cherch_cl, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cherche, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_cherche, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cherch_classe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comb_cl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(combo_et, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_montant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_tra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_payer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_a_paye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_paye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_cherche, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txt_cherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txt_reste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_cherch_classe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_cherch_cl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu3.setText("Retourner");

        jMenuItem1.setText("vers Login");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Fermer");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Historique");

        jMenuItem3.setText("Etudiant");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

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

    private void combo_etActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_etActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_etActionPerformed

    private void comb_clItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comb_clItemStateChanged
        // TODO add your handling code here:
        Classes cl=null;
        cl=(Classes) evt.getItem();
        if(cl.getClId()!=0)
        {
                
                
                cl.charger_Etudiant_classe(combo_et);
                txt_date.setText(""+now());
                txt_a_paye.setText(""+cl.getMinerval());
             

        }
    }//GEN-LAST:event_comb_clItemStateChanged

    private void bt_payerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_payerActionPerformed
        // TODO add your handling code here:
        
        
        try{   
        Etudiant et=(Etudiant) combo_et.getSelectedItem();
        Classes c=(Classes) comb_cl.getSelectedItem();
        Tranches tra=(Tranches) combo_tra.getSelectedItem();
        if(txt_montant.getText().trim().equalsIgnoreCase(""))
        {
            Examen.Generalites.Donner_info_User("Tapez le montant!");
        }
        Payement p=new Payement();
        p.setClasse(c.getNom());
        p.setNom(et.getNom());
        p.setPrenom(et.getPrenom());
        p.setCarte(et.getCarte());
        p.setTranche(tra.getNom());
        p.setMontant(Integer.parseInt(txt_montant.getText()));
        p.setDate(txt_date.getText());
       
        if(p.Enregistre_payement()>0)
        {
            try {
                Examen.Generalites.Donner_info_User("Le Payement est reussi!");
                Charger_table(tab_payement);
                ResultSet re=Examen.Connect.extraireData("select sum(Montant)as somme from payement where Carte='"+et.getCarte()+"'and Classe not In(select Nom_cl from classe inner join classe_etudiant on clID=Classe where terminer='OUI')");
                re.next();
               
                txt_paye.setText((re.getString("somme")));
                txt_reste.setText(""+(Integer.parseInt(txt_a_paye.getText())-Integer.parseInt(txt_paye.getText())));
                combo_et.removeAllItems();
                comb_cl.removeAllItems();
                combo_tra.removeAllItems();
                charger_comb_classe();
                charge_combo_tranche();
                
            } catch (SQLException ex) {
                Logger.getLogger(UI_payement.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else
        {
            Examen.Generalites.Donner_info_User("Echec de payement!");
        }
        }catch(NullPointerException e){
            Examen.Generalites.Donner_info_User("Selectionnez aussi la classe,l'etudiant et le tranche!");
        }
        
        
        
        
    }//GEN-LAST:event_bt_payerActionPerformed

    private void txt_montantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_montantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_montantActionPerformed

    private void txt_resteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_resteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_resteActionPerformed

    private void txt_chercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chercheActionPerformed

    private void bt_actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualiserActionPerformed
        // TODO add your handling code here:
       Charger_table(tab_payement);
       txt_montant.setText("");
       txt_paye.setText("");
       txt_cherche.setForeground(new Color(102,102,102));
       txt_cherche.setText("Tapez numero de carte ici");
       txt_a_paye.setText("");
       txt_reste.setText("");
       txt_cherch_cl.setText("Tapez le nom de la classe ici");
       combo_et.removeAllItems();
       comb_cl.removeAllItems();
       combo_tra.removeAllItems();
       charger_comb_classe();
       charge_combo_tranche();
       
       
    }//GEN-LAST:event_bt_actualiserActionPerformed

    private void bt_chercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_chercheActionPerformed
        // TODO add your handling code here:
        if(txt_cherche.getText().trim().equalsIgnoreCase("")||txt_cherche.getText().trim().equalsIgnoreCase("Tapez numero de carte ici"))
        {
            Examen.Generalites.Donner_info_User("Ecrit dans la zonne de rechercher le numero a chercher!");
            return;
        }
        else
        {
            charge_tab_recherche(tab_payement);
        }
    }//GEN-LAST:event_bt_chercheActionPerformed

    private void bt_cherch_classeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cherch_classeActionPerformed
        // TODO add your handling code here:
        if(txt_cherch_cl.getText().trim().equalsIgnoreCase("")||txt_cherch_cl.getText().trim().equalsIgnoreCase("Tapez le nom de la classe ici"))
        {
            Examen.Generalites.Donner_info_User("Tapez d'abord le numero a chercher dans la zonne de saisie!");
        }
        else
        {
             charge_tab_checrh_classe(tab_payement);
        }
    }//GEN-LAST:event_bt_cherch_classeActionPerformed

    private void bt_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_supprimerActionPerformed
        // TODO add your handling code here:
        try{
        if(pay.Supprimer_payement()>0)
        {
            Examen.Generalites.Donner_info_User("La suppression est reussit!");
            Charger_table(tab_payement);
            combo_et.removeAllItems();
            comb_cl.removeAllItems();
            combo_tra.removeAllItems();
            charger_comb_classe();
            charge_combo_tranche();
        }
        else
        {
            Examen.Generalites.Donner_info_User("Echec,de la suppression!");

        }}catch(NullPointerException e){
           Examen.Generalites.Donner_info_User("Selectionner dans le tableau la ligne a supprimer!");

        }
       
    }//GEN-LAST:event_bt_supprimerActionPerformed

    private void txt_montantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montantKeyPressed
       
        char car=evt.getKeyChar();
        if((car<'0'||car>'9')&&txt_montant.getText().contains(".")&&(car!=(char)KeyEvent.VK_BACK_SPACE))
        {
            evt.consume();
            Examen.Generalites.Donner_info_User("Seulement les chiffres");
            

        }
        else if((car<'0'||car>'9')&&(car!='.')&&(car!=(char)KeyEvent.VK_BACK_SPACE))
                {
                   
                    evt.consume();
                     Examen.Generalites.Donner_info_User("Tapez les Chiffres seulement !");
                     txt_montant.setText("");
                }
    }//GEN-LAST:event_txt_montantKeyPressed

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

    private void txt_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dateActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
          UI_historique h=new UI_historique();
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void txt_chercheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_chercheFocusGained
        // TODO add your handling code here:
          if(txt_cherche.getText().equals("Tapez numero de carte ici"))
        {
            txt_cherche.setText("");
            txt_cherche.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_chercheFocusGained

    private void txt_chercheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_chercheFocusLost
        // TODO add your handling code here:
          if(txt_cherche.getText().equals(""))
        {
            txt_cherche.setText("Tapez numero de carte ici");
            txt_cherche.setForeground(new Color(102,102,102));
        }
         else if(txt_cherche.getText()!=""&&txt_cherche.getText()!="Tapez numero de carte ici")
         {
             txt_cherche.setForeground(new Color(0,0,0));
         }
    }//GEN-LAST:event_txt_chercheFocusLost

    private void txt_cherch_clFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cherch_clFocusGained
        // TODO add your handling code here:
          if(txt_cherch_cl.getText().equals("Tapez le nom de la classe ici"))
        {
            txt_cherch_cl.setText("");
            txt_cherch_cl.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_cherch_clFocusGained

    private void txt_cherch_clFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cherch_clFocusLost
        // TODO add your handling code here:
           if(txt_cherch_cl.getText().equals(""))
        {
            txt_cherch_cl.setText("Tapez le nom de la classe ici");
            txt_cherch_cl.setForeground(new Color(102,102,102));
        }
         else if(txt_cherch_cl.getText()!=""&&txt_cherch_cl.getText()!="Tapez le nom de la classe ici")
         {
             txt_cherch_cl.setForeground(new Color(0,0,0));
         }
    }//GEN-LAST:event_txt_cherch_clFocusLost

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
            java.util.logging.Logger.getLogger(UI_payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_payement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_actualiser;
    private javax.swing.JButton bt_cherch_classe;
    private javax.swing.JButton bt_cherche;
    private javax.swing.JButton bt_payer;
    private javax.swing.JButton bt_supprimer;
    private javax.swing.JComboBox<Classes> comb_cl;
    private javax.swing.JComboBox<Etudiant> combo_et;
    private javax.swing.JComboBox<Tranches> combo_tra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tab_payement;
    private javax.swing.JTextField txt_a_paye;
    private javax.swing.JTextField txt_cherch_cl;
    private javax.swing.JTextField txt_cherche;
    private javax.swing.JTextField txt_date;
    private javax.swing.JTextField txt_montant;
    private javax.swing.JTextField txt_paye;
    private javax.swing.JTextField txt_reste;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import hibernate.HibernateUtil;
import hibernate.Klasa;
import hibernate.KlasaQuery;
import hibernate.Nauczyciele;
import hibernate.NauczycieleQuery;
import hibernate.Obecnosc;
import hibernate.ObecnoscQuery;
import hibernate.Oceny;
import hibernate.OcenyQuery;
import hibernate.Przedmioty;
import hibernate.PrzedmiotyQuery;
import hibernate.Uczniowie;
import hibernate.UczniowieQuery;
import hibernate.Uwagi;
import hibernate.UwagiQuery;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Natalia
 */
public class Uczen extends javax.swing.JFrame {

    private NauczycieleQuery query;
    private List<Nauczyciele> nauczy;
    private UczniowieQuery queryU;
    private List<Uczniowie> ucz;
    private OcenyQuery queryOc;
    private List<Klasa> klasy;
    private KlasaQuery queryK;
    private List<Oceny> ocenki;
    private UwagiQuery queryUw;
    private List<Uwagi> uwaga;
    private PrzedmiotyQuery queryP;
    private List<Przedmioty> przedmiot;
    private List<Obecnosc> obecnosc;
    private ObecnoscQuery queryOb;
    private UczniowieQuery uczniowieQ = new UczniowieQuery();
    private int id;

    public Uczen(int id) {
        this.id = id;
        Uczniowie u;
        u = uczniowieQ.SelectById(id);
        initComponents();
        imieUczniaLabel.setText(u.getImie());
        nazwiskoUcznia.setText(u.getNazwisko());
        PESEL.setText(u.getPesel());
        nr_legitymacji.setText(u.getNrLegitymacji());
        dataurodzenia.setText((u.getDataUrodzenia()).toString());
        miasto.setText(u.getMiasto());
        ulica.setText(u.getUlica());
        kodpocztowy.setText(u.getKodPocztowy());
        tel_do_rodzica.setText(u.getNrTelefonuDoRodzica());
        
        UwagiTableSelectAll();
        PrzedmiotyComboBox();
        PrzedmiotyTableAll();
       
    }

    public void OcenyTableSelectAll() {
        queryOc = new OcenyQuery();
        Object[] kolumna = {"Id oceny", "Id przedmiotu", " Data", "Ocena", "Opis"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumna);

        try {
            String str = (String)wybierzPrzedmiotOC.getSelectedItem();
        int id_ucznia = Integer.parseInt(str);
            ocenki = queryOc.OcenyUcznia(id);
            Object[] row = new Object[5];

            for (Oceny o : ocenki) {
                row[0] = o.getIdOceny();
                row[1] = o.getPrzedmioty().getIdPrzedmioty();
                row[2] = o.getDataWpisania().toString();
                row[3] = o.getWartosc();
                row[4] = o.getOpis();

                model.addRow(row);
            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        TableOceny.setModel(model);
    }
public void PrzedmiotyTableAll() {
        queryP = new PrzedmiotyQuery();
        Object[] kolumny = {"Id przedmiotu", "Nazwa przedmiotu"};
        DefaultTableModel modelP = new DefaultTableModel();
        modelP.setColumnIdentifiers(kolumny);

        try {
            przedmiot = queryP.PrzedmiotySelectAll();
            Object[] row = new Object[2];

            for (Przedmioty p : przedmiot) {
                row[0] = p.getIdPrzedmioty();
                row[1] = p.getNazwaPrzedmiotu();

                modelP.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
        PrzedmiotyTable.setModel(modelP);
    }

    public void PrzedmiotyComboBox() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Przedmioty.class);
            przedmiot = criteria.list();

            wybierzPrzedmiotOC.removeAllItems();

            for (Przedmioty p : przedmiot) {

                wybierzPrzedmiotOC.addItem(p.getIdPrzedmioty()+"");

            }
            session.close();
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }

    public void UwagiTableSelectAll() {
        queryUw = new UwagiQuery();
        query = new NauczycieleQuery();

        Object[] kolumny = {"Data wystawienia", "Treści uwagi"};
        DefaultTableModel modelUW = new DefaultTableModel();
        modelUW.setColumnIdentifiers(kolumny);

        try {
            uwaga = queryUw.UwagiUcznia(id);
            Object[] row = new Object[2];

            for (Uwagi uw : uwaga) {
                row[0] = uw.getDataWpisania();
                row[1] = uw.getOpis();

                modelUW.addRow(row);
            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        TabelaUwagiUcz.setModel(modelUW);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        WYLOGUJ_N = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        wybierzPrzedmiotOC = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableOceny = new javax.swing.JTable();
        wylogujOc = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaUwagiUcz = new javax.swing.JTable();
        wylogujU = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        PrzedmiotyTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        imieUczniaLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nazwiskoUcznia = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PESEL = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nr_legitymacji = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dataurodzenia = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        miasto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ulica = new javax.swing.JLabel();
        label9 = new javax.swing.JLabel();
        kodpocztowy = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tel_do_rodzica = new javax.swing.JLabel();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Data nieobecności", "Przedmiot"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        WYLOGUJ_N.setText("WYLOGUJ");
        WYLOGUJ_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WYLOGUJ_NActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(WYLOGUJ_N))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(WYLOGUJ_N)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Przedmiot");

        wybierzPrzedmiotOC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matematyka", "Język polski", "Język angielski", "Geografia", "Biologia", "Fizyka", "Chemia", "Historia", "Wychowanie fizyczne" }));

        TableOceny.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id oceny", "Data wystawienia", "Ocena", "Opis", "Wystawił"
            }
        ));
        jScrollPane1.setViewportView(TableOceny);

        wylogujOc.setText("WYLOGUJ");
        wylogujOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujOcActionPerformed(evt);
            }
        });

        jButton1.setText("Wyświetl");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addComponent(wybierzPrzedmiotOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wylogujOc))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(wylogujOc)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(wybierzPrzedmiotOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Oceny", jPanel1);

        TabelaUwagiUcz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Data wystawienia", "Treść uwagi", "Wystawił"
            }
        ));
        jScrollPane2.setViewportView(TabelaUwagiUcz);

        wylogujU.setText("WYLOGUJ");
        wylogujU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wylogujU))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(wylogujU)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Uwagi", jPanel2);

        PrzedmiotyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id przedmiotu", "Nazwa przedmiotu"
            }
        ));
        jScrollPane4.setViewportView(PrzedmiotyTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Przedmioty", jPanel4);

        jLabel2.setText("Imię:");

        imieUczniaLabel.setText("jLabel3");

        jLabel3.setText("Nazwisko:");

        nazwiskoUcznia.setText("jLabel4");

        jLabel4.setText("PESEL:");

        PESEL.setText("jLabel5");

        jLabel5.setText("Nr legitymacji:");

        nr_legitymacji.setText("jLabel6");

        jLabel6.setText("Data urodzenia:");

        dataurodzenia.setText("jLabel7");

        jLabel7.setText("Miasto:");

        miasto.setText("jLabel8");

        jLabel8.setText("Ulica:");

        ulica.setText("jLabel9");

        label9.setText("Kod pocztowy:");

        kodpocztowy.setText("jLabel9");

        jLabel9.setText("Nr telefonu do rodzica:");

        tel_do_rodzica.setText("jLabel10");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dataurodzenia, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(PESEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nazwiskoUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(27, 27, 27)
                                    .addComponent(imieUczniaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nr_legitymacji, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(miasto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ulica)))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(kodpocztowy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tel_do_rodzica, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(imieUczniaLabel)
                    .addComponent(jLabel7)
                    .addComponent(miasto))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nazwiskoUcznia)
                    .addComponent(jLabel8)
                    .addComponent(ulica))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PESEL)
                    .addComponent(label9)
                    .addComponent(kodpocztowy))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nr_legitymacji)
                    .addComponent(jLabel9)
                    .addComponent(tel_do_rodzica))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dataurodzenia))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dane osobowe", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wylogujUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujUActionPerformed
        if (evt.getSource().equals(wylogujU)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujUActionPerformed

    private void WYLOGUJ_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WYLOGUJ_NActionPerformed
        if (evt.getSource().equals(WYLOGUJ_N)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_WYLOGUJ_NActionPerformed

    private void wylogujOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujOcActionPerformed
        if (evt.getSource().equals(wylogujOc)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujOcActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        OcenyTableSelectAll();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PESEL;
    private javax.swing.JTable PrzedmiotyTable;
    private javax.swing.JTable TabelaUwagiUcz;
    private javax.swing.JTable TableOceny;
    private javax.swing.JButton WYLOGUJ_N;
    private javax.swing.JLabel dataurodzenia;
    private javax.swing.JLabel imieUczniaLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel kodpocztowy;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel miasto;
    private javax.swing.JLabel nazwiskoUcznia;
    private javax.swing.JLabel nr_legitymacji;
    private javax.swing.JLabel tel_do_rodzica;
    private javax.swing.JLabel ulica;
    private javax.swing.JComboBox<String> wybierzPrzedmiotOC;
    private javax.swing.JButton wylogujOc;
    private javax.swing.JButton wylogujU;
    // End of variables declaration//GEN-END:variables
}

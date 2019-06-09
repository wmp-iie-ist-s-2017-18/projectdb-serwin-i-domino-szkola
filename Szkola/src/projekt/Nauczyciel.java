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
import hibernate.Oceny;
import hibernate.OcenyQuery;
import hibernate.Przedmioty;
import hibernate.PrzedmiotyQuery;
import hibernate.Uczniowie;
import hibernate.UczniowieQuery;
import hibernate.Uwagi;
import hibernate.Obecnosc;
import hibernate.ObecnoscQuery;
import hibernate.UwagiQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Natalia
 */
public class Nauczyciel extends javax.swing.JFrame {

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
    private int id;

    public Nauczyciel(int id) {
        this.id = id;
        initComponents();
        NauczTableSelectAll();
        UcznTableSelectAll();
        OcenyTableSelectAll();
        UwagiTableSelectAll();
        UczniowieCombobox();
        PrzedmiotyComboBox();
        Typ_UwagiCombobox();
        PrzedmiotyTableAll();
        
    }
  

    public void wyczyscformUcz() {
        idUdodaj.setText(null);
        imieUczniatxt.setText(null);
        naziwskoUcznia.setText(null);
        peselUcznia.setText(null);
        ulicaUcznia.setText(null);
        miastoUcznia.setText(null);
        kodPocztUcznia.setText(null);
        nrTelRodzica.setText(null);
        nrLegitymacji.setText(null);
        hasloUcznia.setText(null);
    }

    public void clearFieldsUsunOc() {
        usunIdOc.setText(null);
    }

    public void UczniowieCombobox() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Uczniowie.class);
            ucz = criteria.list();

            wyborUczniaOc.removeAllItems();
            wyborUczniaOb.removeAllItems();
            uczenComboBox.removeAllItems();
            wyborUczniaOcF.removeAllItems();
            wybierzUczniaUwf.removeAllItems();

            for (Uczniowie u : ucz) {

                wyborUczniaOb.addItem(Integer.toString(u.getIdUcznia()));
                uczenComboBox.addItem(Integer.toString(u.getIdUcznia()));
                wyborUczniaOc.addItem(Integer.toString(u.getIdUcznia()));
                wyborUczniaOcF.addItem(Integer.toString(u.getIdUcznia()));
                wybierzUczniaUwf.addItem(Integer.toString(u.getIdUcznia()));
            }

            session.close();
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }


     public void PrzedmiotyComboBox() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Przedmioty.class);
            przedmiot = criteria.list();

            wybierzPrzedmiot.removeAllItems();
            wybierzPrzedmiotOc.removeAllItems();
            for (Przedmioty p : przedmiot) {

              
              wybierzPrzedmiot.addItem(Integer.toString(p.getIdPrzedmioty()));
              wybierzPrzedmiotOc.addItem(Integer.toString(p.getIdPrzedmioty()));

            }

            session.close();
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }
     
     public void Typ_UwagiCombobox(){
         Session session = null;
         
         try{
             session = HibernateUtil.getSessionFactory().openSession();
                 Criteria criteria = session.createCriteria(Uwagi.class);
           uwaga = criteria.list();
           typUwagiCombo.removeAllItems();
           
           for(Uwagi u: uwaga){
               typUwagiCombo.addItem(u.getTypUwagi());
           }
           session.close();
             
         }catch(Exception e) {
            System.out.println("Bład połączenia z bazą!");
     }
     }

    public void UwagiTableSelectAll() {
        queryUw = new UwagiQuery();
        queryU = new UczniowieQuery();
        Object[] kolumny = {"Id uwagi", "Data wpisania", "Typ uwagi", "Opis"};
        DefaultTableModel modelUW = new DefaultTableModel();
        modelUW.setColumnIdentifiers(kolumny);

        try {
            uwaga = (List<Uwagi>) queryUw.uwagiSelectAll();
            Object[] row = new Object[7];

            for (Uwagi uw : uwaga) {
                row[0] = uw.getIdUwagi();
                row[1] = uw.getDataWpisania();
                row[2] = uw.getTypUwagi();
                row[3] = uw.getOpis();

                modelUW.addRow(row);
            }

            // for (Nauczyciele n : nauczy) {
            //  row[5] = n.getNazwisko() + " " + n.getImie();
            //    modelUW.addRow(row);
            // }
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        TableUwagi.setModel(modelUW);

    }
    
    public void PrzedmiotyTableAll(){
        queryP = new PrzedmiotyQuery();
        Object[] kolumny = {"Id przedmiotu", "Nazwa przedmiotu"};
        DefaultTableModel modelP = new DefaultTableModel();
        modelP.setColumnIdentifiers(kolumny);
        
        try{
            przedmiot = queryP.PrzedmiotySelectAll();
            Object [] row = new Object [2];
            
            for(Przedmioty p: przedmiot){
                row[0] = p.getIdPrzedmioty();
                row[1] = p.getNazwaPrzedmiotu();
                
                modelP.addRow(row);
             } 
        }catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
        PrzedmiotyTable.setModel(modelP);
    }

    public void NauczTableSelectAll() {

        query = new NauczycieleQuery();
        Object[] kolumny = {"Id nauczyciela", "Imię", "Nazwisko", "E-mail", "Nr telefonu", "Wynagrodzenie"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumny);

        try {
            nauczy = query.nauczycieleSelectAll();
            Object[] row = new Object[6];

            for (Nauczyciele n : nauczy) {
                row[0] = n.getIdNauczyciela();
                row[1] = n.getImie();
                row[2] = n.getNazwisko();
                row[3] = n.getEmail();
                row[4] = n.getNrTelefonu();
                row[5] = n.getStawka();

                model.addRow(row);

            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }

        nauTable.setModel(model);

    }

    public void OcenyTableSelectAll() {
        queryOc = new OcenyQuery();
        Object[] kolumna = {"Id oceny", "Id przedmiotu",  "Data wpisania", "Ocena", "Opis"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumna);

        try {

            ocenki = queryOc.ocenySelectAll();

            Object[] row = new Object[7];

            for (Oceny o : ocenki) {
                row[0] = o.getIdOceny();
                row[1] = o.getPrzedmioty().getIdPrzedmioty();
                row[2] = o.getDataWpisania().toString();
                row[3] = o.getWartosc();
                row[4] = o.getOpis();
   

             
                model.addRow(row);
            }

            //for (Nauczyciele n : nauczy) {
            //    row[4] = n.getNazwisko() + " " + n.getImie();
            // model.addRow(row);
            //  }
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        ocenyTable.setModel(model);
    }

    public void UcznTableSelectAll() {
        queryU = new UczniowieQuery();
        Object[] kolumn = {"Id ucznia", "Imie", "Nazwisko", "Pesel", "Data urodzenia",
            "Ulica", "Miasto", "Kod pocztowy", "Nr tel.rodziców", "Nr legitymacji"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumn);

        try {
            ucz = queryU.uczniowieSelectAll();
            Object[] row = new Object[10];

            for (Uczniowie u : ucz) {
                row[0] = u.getIdUcznia();
                row[1] = u.getImie();
                row[2] = u.getNazwisko();
                row[3] = u.getPesel();
                row[4] = u.getDataUrodzenia();
                row[5] = u.getUlica();
                row[6] = u.getMiasto();
                row[7] = u.getKodPocztowy();
                row[8] = u.getNrTelefonuDoRodzica();
                row[9] = u.getNrLegitymacji();

                model.addRow(row);
            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        uczTable.setModel(model);
    }

    private void wyczyscform() {
        idNauczDodaj.setText(null);
        imieNauczyciela.setText(null);
        naziwskoNauczyciela.setText(null);
        email.setText(null);
        nrTel.setText(null);
        stawka.setText(null);
        hasloDodaj.setText(null);
    }

    private void wyczyscUsunform() {
        UsunIDNaucz.setText(null);
        usunIdOc.setText(null);
        usunUczID.setText(null);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        dodajObecnoscbtn = new javax.swing.JButton();
        jSeparator = new javax.swing.JSeparator();
        wyborUczniaOb = new javax.swing.JComboBox<String>();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableNObec = new javax.swing.JTable();
        wyswietlNieobec = new javax.swing.JButton();
        wylogujOb = new javax.swing.JButton();
        dateChooserOb = new datechooser.beans.DateChooserCombo();
        typObecnosctxt = new javax.swing.JTextField();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Uwagi = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dodajUwage = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableUwagi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        wylogujUw = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        trescUwagi = new javax.swing.JTextPane();
        DateChooserUW = new datechooser.beans.DateChooserCombo();
        jLabel12 = new javax.swing.JLabel();
        id_uwagi = new javax.swing.JTextField();
        uczenComboBox = new javax.swing.JComboBox();
        wyswietlUcznia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        typUwagiCombo = new javax.swing.JComboBox();
        wybierzUczniaUwf = new javax.swing.JComboBox();
        Oceny = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ocenyTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        wartoscOcentxt = new javax.swing.JTextField();
        dodajOcene = new javax.swing.JButton();
        usunOcenebtn = new javax.swing.JButton();
        srednia_ocenLa = new javax.swing.JLabel();
        wylogujOc = new javax.swing.JButton();
        SredniaOcenLabel = new javax.swing.JLabel();
        SredniaOcentxt = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        usunIdOc = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        opisOctxt = new javax.swing.JTextPane();
        jLabel27 = new javax.swing.JLabel();
        idOcDodajtxt = new javax.swing.JTextField();
        dateChooserOceny = new datechooser.beans.DateChooserCombo();
        usuniecOcAlert = new javax.swing.JLabel();
        przedmiotlabelOc = new javax.swing.JLabel();
        obliczS = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        wyborUczniaOc = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        sredniaOcenUczniaLa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        wybierzPrzedmiot = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        wybierzPrzedmiotOc = new javax.swing.JComboBox();
        wyborUczniaOcF = new javax.swing.JComboBox();
        nauczyciele = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        imieNauczyciela = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        naziwskoNauczyciela = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        nauTable = new javax.swing.JTable();
        dodajNauczyciela = new javax.swing.JButton();
        usunNauczyciela = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nrTel = new javax.swing.JTextField();
        stawka = new javax.swing.JTextField();
        wylogujNauc = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        idNauczDodaj = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        UsunIDNaucz = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        hasloDodaj = new javax.swing.JPasswordField();
        jLabelErrorNauczyciel = new javax.swing.JLabel();
        Uczniowie = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        uczTable = new javax.swing.JTable();
        dodajUcznia = new javax.swing.JButton();
        usunUczniabtn = new javax.swing.JButton();
        ImieLabel = new javax.swing.JLabel();
        NazwiskoLabel = new javax.swing.JLabel();
        PESELlabel = new javax.swing.JLabel();
        imieUczniatxt = new javax.swing.JTextField();
        naziwskoUcznia = new javax.swing.JTextField();
        peselUcznia = new javax.swing.JTextField();
        urodzeniaLabel = new javax.swing.JLabel();
        UlicaLabel = new javax.swing.JLabel();
        MiastoLabel = new javax.swing.JLabel();
        ulicaUcznia = new javax.swing.JTextField();
        miastoUcznia = new javax.swing.JTextField();
        kodlabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        kodPocztUcznia = new javax.swing.JTextField();
        nrTelRodzica = new javax.swing.JTextField();
        nrLegitymacji = new javax.swing.JTextField();
        wylogujUcz = new javax.swing.JButton();
        alertU = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        idUdodaj = new javax.swing.JTextField();
        dataUrodzeniaChooser = new datechooser.beans.DateChooserCombo();
        hasloUcznia = new javax.swing.JPasswordField();
        jLabel33 = new javax.swing.JLabel();
        usunUczID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        PrzedmiotyTable = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        idPrzedmiot = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        nazwaPrzedmiotu = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        Dodaj_Przedmiot = new javax.swing.JButton();
        wylogujPrzedmioty = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel16.setText("Imię");

        jLabel20.setText("Nazwisko");

        jLabel21.setText("Typ obecności");

        jLabel23.setText("Data");

        dodajObecnoscbtn.setText("Dodaj obecność");

        jSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        wyborUczniaOb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", " " }));

        TableNObec.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(TableNObec);

        wyswietlNieobec.setText("Wyświetl nieobecności");

        wylogujOb.setText("Wyloguj");
        wylogujOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujObActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20))
                        .addGap(33, 33, 33)
                        .addComponent(dateChooserOb, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(dodajObecnoscbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(typObecnosctxt, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 459, Short.MAX_VALUE)
                            .addComponent(wyswietlNieobec)
                            .addGap(110, 110, 110))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                            .addComponent(wyborUczniaOb, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(280, 280, 280)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 101, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wylogujOb))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(418, 418, 418)
                    .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(616, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wylogujOb)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wyborUczniaOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wyswietlNieobec))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(dateChooserOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(typObecnosctxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(dodajObecnoscbtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
        );

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Treść uwagi");

        dodajUwage.setText("Dodaj uwagę");
        dodajUwage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajUwageActionPerformed(evt);
            }
        });

        jLabel5.setText("Id ucznia:");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TableUwagi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id_uwagi", "Data wpisania", "Typ_uwagi", "Opis"
            }
        ));
        jScrollPane2.setViewportView(TableUwagi);

        jLabel15.setText("Typ uwagi");

        jLabel22.setText("Data wpisania");

        wylogujUw.setText("Wyloguj");
        wylogujUw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujUwActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(trescUwagi);

        jLabel12.setText("Id uwagi");

        uczenComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        uczenComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uczenComboBoxActionPerformed(evt);
            }
        });

        wyswietlUcznia.setText("Wyświetl");
        wyswietlUcznia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyswietlUczniaActionPerformed(evt);
            }
        });

        jLabel1.setText("Wybierz Ucznia po ID:");

        typUwagiCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        wybierzUczniaUwf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout UwagiLayout = new javax.swing.GroupLayout(Uwagi);
        Uwagi.setLayout(UwagiLayout);
        UwagiLayout.setHorizontalGroup(
            UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UwagiLayout.createSequentialGroup()
                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UwagiLayout.createSequentialGroup()
                                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel12))
                                .addGap(25, 25, 25)
                                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DateChooserUW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(id_uwagi, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(typUwagiCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(wybierzUczniaUwf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(dodajUwage)))
                .addGap(84, 84, 84)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(60, 60, 60)
                        .addComponent(uczenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(wyswietlUcznia)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UwagiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(wylogujUw))))
        );
        UwagiLayout.setVerticalGroup(
            UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UwagiLayout.createSequentialGroup()
                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(id_uwagi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(wybierzUczniaUwf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(DateChooserUW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(typUwagiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dodajUwage)
                        .addGap(27, 27, 27))
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addComponent(wylogujUw)
                        .addGap(3, 3, 3)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uczenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wyswietlUcznia)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Uwagi ", Uwagi);

        ocenyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id oceny", "Data", "Ocena", "Opis"
            }
        ));
        jScrollPane1.setViewportView(ocenyTable);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setText("Ocena");

        jLabel10.setText("Opis");

        jLabel11.setText("Data");

        dodajOcene.setText("Dodaj ocenę");
        dodajOcene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajOceneActionPerformed(evt);
            }
        });

        usunOcenebtn.setText("Usuń ocenę");
        usunOcenebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunOcenebtnActionPerformed(evt);
            }
        });

        wylogujOc.setText("Wyloguj");
        wylogujOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujOcActionPerformed(evt);
            }
        });

        SredniaOcenLabel.setText("Średnia ocen: ");

        jLabel26.setText("Usuń po ID:");

        jScrollPane7.setViewportView(opisOctxt);

        jLabel27.setText("Id oceny");

        obliczS.setText("Wylicz");
        obliczS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obliczSActionPerformed(evt);
            }
        });

        jLabel34.setText("Id ucznia");

        jLabel35.setText("Id przedmiotu");

        wyborUczniaOc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        wyborUczniaOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyborUczniaOcActionPerformed(evt);
            }
        });

        jButton1.setText("Wyświet");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Wybierz ucznia po ID:");

        wybierzPrzedmiot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Wybór przedmiotu po ID:");

        wybierzPrzedmiotOc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        wybierzPrzedmiotOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wybierzPrzedmiotOcActionPerformed(evt);
            }
        });

        wyborUczniaOcF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        wyborUczniaOcF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyborUczniaOcFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OcenyLayout = new javax.swing.GroupLayout(Oceny);
        Oceny.setLayout(OcenyLayout);
        OcenyLayout.setHorizontalGroup(
            OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OcenyLayout.createSequentialGroup()
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wyborUczniaOc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wybierzPrzedmiot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(przedmiotlabelOc, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jButton1))))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(usuniecOcAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OcenyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wylogujOc))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel35)
                                .addComponent(jLabel34)
                                .addComponent(jLabel11)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dodajOcene, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addComponent(usunIdOc, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(usunOcenebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(wybierzPrzedmiotOc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, OcenyLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(idOcDodajtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(wyborUczniaOcF, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateChooserOceny, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(wartoscOcentxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(OcenyLayout.createSequentialGroup()
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(srednia_ocenLa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(SredniaOcenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SredniaOcentxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(sredniaOcenUczniaLa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(obliczS)))
                .addGap(682, 682, 682))
        );
        OcenyLayout.setVerticalGroup(
            OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OcenyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addComponent(przedmiotlabelOc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wybierzPrzedmiot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wyborUczniaOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(obliczS)
                        .addGap(230, 230, 230)
                        .addComponent(srednia_ocenLa))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addComponent(SredniaOcentxt)
                                .addGap(4, 4, 4)
                                .addComponent(sredniaOcenUczniaLa))
                            .addComponent(SredniaOcenLabel))))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OcenyLayout.createSequentialGroup()
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addComponent(wylogujOc)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(idOcDodajtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(wybierzPrzedmiotOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(wyborUczniaOcF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(dateChooserOceny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(wartoscOcentxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dodajOcene)
                .addGap(18, 18, 18)
                .addComponent(usuniecOcAlert)
                .addGap(22, 22, 22)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usunIdOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(usunOcenebtn)))
                .addGap(312, 312, 312))
        );

        jTabbedPane1.addTab("Oceny", Oceny);

        jLabel17.setText("Imię");

        jLabel18.setText("Nazwisko");

        jLabel19.setText("E - mail ");

        nauTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id nauczyciela", "Imię", "Nazwisko", "E - mail", "Nr telefonu", "Wynagrodzenie"
            }
        ));
        jScrollPane3.setViewportView(nauTable);

        dodajNauczyciela.setText("Dodaj nauczyciela");
        dodajNauczyciela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajNauczycielaActionPerformed(evt);
            }
        });

        usunNauczyciela.setText("Usuń nauczyciela");
        usunNauczyciela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunNauczycielaActionPerformed(evt);
            }
        });

        jLabel7.setText("Nr telefonu");

        jLabel8.setText("Wynagrodzenie");

        wylogujNauc.setText("Wyloguj");
        wylogujNauc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujNaucActionPerformed(evt);
            }
        });

        jLabel28.setText("Id nauczyciela");

        idNauczDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idNauczDodajActionPerformed(evt);
            }
        });

        jLabel31.setText("Usuń po ID:");

        jLabel32.setText("Hasło:");

        jLabelErrorNauczyciel.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout nauczycieleLayout = new javax.swing.GroupLayout(nauczyciele);
        nauczyciele.setLayout(nauczycieleLayout);
        nauczycieleLayout.setHorizontalGroup(
            nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nauczycieleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wylogujNauc))
            .addGroup(nauczycieleLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nauczycieleLayout.createSequentialGroup()
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nauczycieleLayout.createSequentialGroup()
                                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(nauczycieleLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(idNauczDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(nauczycieleLayout.createSequentialGroup()
                                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(naziwskoNauczyciela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                            .addComponent(imieNauczyciela, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(56, 56, 56)
                                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel7)))
                            .addGroup(nauczycieleLayout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addComponent(jLabel8)))
                        .addGap(27, 27, 27)
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nrTel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stawka, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(dodajNauczyciela, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(nauczycieleLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nauczycieleLayout.createSequentialGroup()
                                .addComponent(usunNauczyciela, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(nauczycieleLayout.createSequentialGroup()
                                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UsunIDNaucz, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(nauczycieleLayout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel32)
                .addGap(36, 36, 36)
                .addComponent(hasloDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabelErrorNauczyciel, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        nauczycieleLayout.setVerticalGroup(
            nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nauczycieleLayout.createSequentialGroup()
                .addComponent(wylogujNauc)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nauczycieleLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel28)
                            .addComponent(idNauczDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imieNauczyciela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel7)
                            .addComponent(nrTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(naziwskoNauczyciela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(stawka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18)))
                    .addGroup(nauczycieleLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(dodajNauczyciela)))
                .addGap(33, 33, 33)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hasloDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabelErrorNauczyciel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(nauczycieleLayout.createSequentialGroup()
                        .addComponent(UsunIDNaucz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usunNauczyciela)
                        .addGap(92, 92, 92))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nauczyciele", nauczyciele);

        uczTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_ucznia", "Imię", "Nazwisko", "Pesel", "Data urodzenia", "Ulica", "Miasto", "Kod poczt. ", "Nr tel. rodziców", "Nr legitymacji"
            }
        ));
        jScrollPane4.setViewportView(uczTable);

        dodajUcznia.setText("Dodaj ucznia");
        dodajUcznia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajUczniaActionPerformed(evt);
            }
        });

        usunUczniabtn.setText("Usuń ucznia");
        usunUczniabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunUczniabtnActionPerformed(evt);
            }
        });

        ImieLabel.setText("Imię");

        NazwiskoLabel.setText("Nazwisko");

        PESELlabel.setText("Pesel");

        urodzeniaLabel.setText("Data urodzenia");

        UlicaLabel.setText("Ulica");

        MiastoLabel.setText("Miasto");

        kodlabel.setText("Kod pocztowy");

        jLabel29.setText("Nr tel. rodziców");

        jLabel30.setText("Nr legitymacji");

        wylogujUcz.setText("Wyloguj");
        wylogujUcz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujUczActionPerformed(evt);
            }
        });

        jLabel13.setText("Id ucznia:");

        hasloUcznia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hasloUczniaActionPerformed(evt);
            }
        });

        jLabel33.setText("Hasło");

        jLabel3.setText("Usuń ucznia po ID:");

        javax.swing.GroupLayout UczniowieLayout = new javax.swing.GroupLayout(Uczniowie);
        Uczniowie.setLayout(UczniowieLayout);
        UczniowieLayout.setHorizontalGroup(
            UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UczniowieLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UczniowieLayout.createSequentialGroup()
                            .addComponent(PESELlabel)
                            .addGap(37, 37, 37))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UczniowieLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(18, 18, 18)))
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NazwiskoLabel)
                            .addComponent(ImieLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imieUczniatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(naziwskoUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peselUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idUdodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(alertU, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usunUczID, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usunUczniabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178)
                        .addComponent(wylogujUcz))
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UlicaLabel)
                            .addComponent(urodzeniaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MiastoLabel))
                        .addGap(31, 31, 31)
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addComponent(miastoUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jLabel30))
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UczniowieLayout.createSequentialGroup()
                                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(dataUrodzeniaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(ulicaUcznia, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UczniowieLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)))
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(kodlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nrLegitymacji, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(nrTelRodzica, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kodPocztUcznia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dodajUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hasloUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addContainerGap())))
            .addGroup(UczniowieLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
                .addContainerGap())
        );
        UczniowieLayout.setVerticalGroup(
            UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UczniowieLayout.createSequentialGroup()
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UczniowieLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(usunUczniabtn)
                                            .addComponent(usunUczID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)))
                                    .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(wylogujUcz)
                                        .addComponent(alertU)))
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(idUdodaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(imieUczniatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(urodzeniaLabel)
                                    .addComponent(ImieLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(naziwskoUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NazwiskoLabel))
                                .addGap(18, 18, 18)
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PESELlabel)
                                    .addComponent(peselUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UczniowieLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(kodlabel)
                                            .addComponent(kodPocztUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(dataUrodzeniaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(UlicaLabel)
                                    .addComponent(ulicaUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)
                                    .addComponent(nrTelRodzica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nrLegitymacji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30)
                                    .addComponent(miastoUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MiastoLabel)))))
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hasloUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(dodajUcznia)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Uczniowie", Uczniowie);

        PrzedmiotyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id_przedmiotu", "Nazwa przedmiotu"
            }
        ));
        jScrollPane8.setViewportView(PrzedmiotyTable);

        jLabel14.setText("Id przedmiotu:");

        jLabel24.setText("Nazwa przedmiotu:");

        Dodaj_Przedmiot.setText("Dodaj przedmiot");
        Dodaj_Przedmiot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dodaj_PrzedmiotActionPerformed(evt);
            }
        });

        wylogujPrzedmioty.setText("Wyloguj");
        wylogujPrzedmioty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujPrzedmiotyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(nazwaPrzedmiotu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idPrzedmiot, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Dodaj_Przedmiot))
                                .addGap(112, 112, 112))
                            .addComponent(wylogujPrzedmioty, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(wylogujPrzedmioty)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(idPrzedmiot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nazwaPrzedmiotu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addGap(34, 34, 34)
                                .addComponent(Dodaj_Przedmiot)))
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Przedmioty", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hasloUczniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hasloUczniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hasloUczniaActionPerformed

    private void wylogujUczActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujUczActionPerformed
        if (evt.getSource().equals(wylogujUcz)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujUczActionPerformed

    private void usunUczniabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunUczniabtnActionPerformed
        if (evt.getSource().equals(usunUczniabtn)) {
            try {
                queryU.usunUcznia(Integer.parseInt(usunUczID.getText()));
                wyczyscUsunform();
            } catch (Exception e) {

            }

        }
        UcznTableSelectAll();
    }//GEN-LAST:event_usunUczniabtnActionPerformed

    private void dodajUczniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajUczniaActionPerformed
        int idUcznia;
        String imie, nazwisko, PESEL, nr_legitymacji, miasto, ulica, kodpocztowy, nr_telefonu_do_rodzica, haslo;

        if (evt.getSource().equals(dodajUcznia)) {
            idUcznia = Integer.parseInt(idUdodaj.getText());
            imie = imieUczniatxt.getText();
            nazwisko = naziwskoUcznia.getText();
            PESEL = peselUcznia.getText();
            nr_legitymacji = nrLegitymacji.getText();
            miasto = miastoUcznia.getText();
            ulica = ulicaUcznia.getText();
            kodpocztowy = kodPocztUcznia.getText();
            nr_telefonu_do_rodzica = nrTelRodzica.getText();
            haslo = hasloUcznia.getText();
            Date data = null;
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date = df.format(dataUrodzeniaChooser.getSelectedDate().getTime());

                data = df.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Nauczyciel.class.getName()).log(Level.SEVERE, null, ex);
            }

            Uczniowie u = new Uczniowie(idUcznia, imie, nazwisko, PESEL, nr_legitymacji, data, miasto, ulica, kodpocztowy, nr_telefonu_do_rodzica, haslo);

            queryU = new UczniowieQuery();
            try {
                queryU.DodajUcznia(idUcznia, imie, nazwisko, PESEL, nr_legitymacji, data, miasto, ulica, kodpocztowy, nr_telefonu_do_rodzica, haslo);
                wyczyscformUcz();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            UcznTableSelectAll();
        }
    }//GEN-LAST:event_dodajUczniaActionPerformed

    private void idNauczDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idNauczDodajActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idNauczDodajActionPerformed

    private void wylogujNaucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujNaucActionPerformed
        if (evt.getSource().equals(wylogujNauc)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujNaucActionPerformed

    private void usunNauczycielaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunNauczycielaActionPerformed
        jLabelErrorNauczyciel.setText("");
        if (evt.getSource().equals(usunNauczyciela)) {
            try {
                query.usunNaucz(Integer.parseInt(UsunIDNaucz.getText()));
                wyczyscUsunform();
            } catch (Exception e) {
                jLabelErrorNauczyciel.setText("Błąd!");
            }

        }

        NauczTableSelectAll();
    }//GEN-LAST:event_usunNauczycielaActionPerformed

    private void dodajNauczycielaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajNauczycielaActionPerformed

        jLabelErrorNauczyciel.setText("");
        int idNaucz;
        String imie, nazwisko, mail, nr_telefonu, wynagrodzenie, haslo;

        if (evt.getSource().equals(dodajNauczyciela)) {

            idNaucz = Integer.parseInt(idNauczDodaj.getText());
            imie = imieNauczyciela.getText();
            nazwisko = naziwskoNauczyciela.getText();
            mail = email.getText();
            nr_telefonu = nrTel.getText();
            wynagrodzenie = stawka.getText();
            char [] passy = hasloDodaj.getPassword();
            haslo = new String(passy);
                 
            try {
                query.dodajNauczyciela(idNaucz, imie, nazwisko, mail, nr_telefonu, wynagrodzenie, haslo);
                wyczyscform();
            } catch (Exception e) {
                jLabelErrorNauczyciel.setText("Błąd!");
            }
            NauczTableSelectAll();
        }

    }//GEN-LAST:event_dodajNauczycielaActionPerformed

    private void wyborUczniaOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyborUczniaOcActionPerformed

    }//GEN-LAST:event_wyborUczniaOcActionPerformed

    private void obliczSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obliczSActionPerformed
        int id_przedmioty= Integer.valueOf((String)wybierzPrzedmiot.getSelectedItem());
        int id_ucznia= Integer.valueOf((String)wyborUczniaOc.getSelectedItem());
        
        double srednia = queryOc.srednia(id_ucznia, id_przedmioty);
      sredniaOcenUczniaLa.setText(Double.toString(srednia));
    }//GEN-LAST:event_obliczSActionPerformed

    private void wylogujOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujOcActionPerformed
        if (evt.getSource().equals(wylogujOc)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujOcActionPerformed

    private void usunOcenebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunOcenebtnActionPerformed
        usuniecOcAlert.setText("");
        if (evt.getSource().equals(usunOcenebtn)) {
            try {
                queryOc.usunOcene(Integer.parseInt(usunIdOc.getText()));
                wyczyscUsunform();
            } catch (Exception e) {
                usuniecOcAlert.setText("Ocena o takim id została już usunięta");
            }

            OcenyTableSelectAll();
        }
    }//GEN-LAST:event_usunOcenebtnActionPerformed

    private void dodajOceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajOceneActionPerformed
        int id_oceny, id_przed, id_ucz, id_naucz = id;
        int wartoscOc;
        String opis;

        if (evt.getSource().equals(dodajOcene)) {
            id_oceny = Integer.parseInt(idOcDodajtxt.getText());
            id_przed = Integer.valueOf((String)wybierzPrzedmiotOc.getSelectedItem());
            id_ucz = Integer.valueOf((String)wyborUczniaOcF.getSelectedItem());
            opis = opisOctxt.getText();
            wartoscOc = Integer.parseInt(wartoscOcentxt.getText());
            opis = opisOctxt.getText();

            Date data = null;

            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date = df.format(dateChooserOceny.getSelectedDate().getTime());

                data = df.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Nauczyciel.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                queryOc.DodajOcene(id_oceny, data, opis, wartoscOc, id_przed, id_ucz, id_naucz);

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            OcenyTableSelectAll();

        }
    }//GEN-LAST:event_dodajOceneActionPerformed

    private void wylogujObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujObActionPerformed
        if (evt.getSource().equals(wylogujOb)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujObActionPerformed

    private void wyswietlUczniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyswietlUczniaActionPerformed

    }//GEN-LAST:event_wyswietlUczniaActionPerformed

    private void uczenComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uczenComboBoxActionPerformed

    }//GEN-LAST:event_uczenComboBoxActionPerformed

    private void wylogujUwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujUwActionPerformed
        if (evt.getSource().equals(wylogujUw)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujUwActionPerformed

    private void dodajUwageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajUwageActionPerformed

        int id_uwaga, id_ucz, id_naucz = id;
        String imie, nazwisko, opis, typ;

        if (evt.getSource().equals(dodajUwage)) {
            id_uwaga = Integer.parseInt(id_uwagi.getText());
            id_ucz = Integer.valueOf((String)wyborUczniaOcF.getSelectedItem());
            typ = (String)typUwagiCombo.getSelectedItem();
            opis = trescUwagi.getText();

            Date datawpisania = null;

            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date = df.format(DateChooserUW.getSelectedDate().getTime());
                datawpisania = df.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Nauczyciel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {

                queryUw.DodajUwage(id_uwaga, opis, datawpisania, typ, id_ucz, id_naucz);

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            UwagiTableSelectAll();
        }
    }//GEN-LAST:event_dodajUwageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 int id_przedmioty= Integer.valueOf((String)wybierzPrzedmiot.getSelectedItem());
        int id_ucznia= Integer.valueOf((String)wyborUczniaOc.getSelectedItem());
        
        List OcenySelectAllOnID = queryOc.OcenySelectAllOnID(id_ucznia, id_przedmioty);

      OcenyTableSelectAll();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void wybierzPrzedmiotOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wybierzPrzedmiotOcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wybierzPrzedmiotOcActionPerformed

    private void wyborUczniaOcFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyborUczniaOcFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wyborUczniaOcFActionPerformed

    private void Dodaj_PrzedmiotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dodaj_PrzedmiotActionPerformed
        
        if (evt.getSource().equals(Dodaj_Przedmiot)) {

            int id_przedmiotu;
            String nazwa;
            
            id_przedmiotu = Integer.parseInt(idPrzedmiot.getText());
            nazwa = nazwaPrzedmiotu.getText();
            
                 Przedmioty p = new Przedmioty(id_przedmiotu, nazwa);
                 queryP = new PrzedmiotyQuery();
            try {
                queryP.PrzedmiotyAdd(p);
               
            } catch (Exception e) {
                ;
            }
           PrzedmiotyTableAll();
        }
    }//GEN-LAST:event_Dodaj_PrzedmiotActionPerformed

    private void wylogujPrzedmiotyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujPrzedmiotyActionPerformed
        if (evt.getSource().equals(wylogujPrzedmioty)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujPrzedmiotyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo DateChooserUW;
    private javax.swing.JButton Dodaj_Przedmiot;
    private javax.swing.JLabel ImieLabel;
    private javax.swing.JLabel MiastoLabel;
    private javax.swing.JLabel NazwiskoLabel;
    private javax.swing.JPanel Oceny;
    private javax.swing.JLabel PESELlabel;
    private javax.swing.JTable PrzedmiotyTable;
    private javax.swing.JLabel SredniaOcenLabel;
    private javax.swing.JLabel SredniaOcentxt;
    private javax.swing.JTable TableNObec;
    private javax.swing.JTable TableUwagi;
    private javax.swing.JPanel Uczniowie;
    private javax.swing.JLabel UlicaLabel;
    private javax.swing.JTextField UsunIDNaucz;
    private javax.swing.JPanel Uwagi;
    private javax.swing.JLabel alertU;
    private datechooser.beans.DateChooserCombo dataUrodzeniaChooser;
    private datechooser.beans.DateChooserCombo dateChooserOb;
    private datechooser.beans.DateChooserCombo dateChooserOceny;
    private javax.swing.JButton dodajNauczyciela;
    private javax.swing.JButton dodajObecnoscbtn;
    private javax.swing.JButton dodajOcene;
    private javax.swing.JButton dodajUcznia;
    private javax.swing.JButton dodajUwage;
    private javax.swing.JTextField email;
    private javax.swing.JPasswordField hasloDodaj;
    private javax.swing.JPasswordField hasloUcznia;
    private javax.swing.JTextField idNauczDodaj;
    private javax.swing.JTextField idOcDodajtxt;
    private javax.swing.JTextField idPrzedmiot;
    private javax.swing.JTextField idUdodaj;
    private javax.swing.JTextField id_uwagi;
    private javax.swing.JTextField imieNauczyciela;
    private javax.swing.JTextField imieUczniatxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelErrorNauczyciel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField kodPocztUcznia;
    private javax.swing.JLabel kodlabel;
    private javax.swing.JTextField miastoUcznia;
    private javax.swing.JTable nauTable;
    private javax.swing.JPanel nauczyciele;
    private javax.swing.JTextField naziwskoNauczyciela;
    private javax.swing.JTextField naziwskoUcznia;
    private javax.swing.JTextField nazwaPrzedmiotu;
    private javax.swing.JTextField nrLegitymacji;
    private javax.swing.JTextField nrTel;
    private javax.swing.JTextField nrTelRodzica;
    private javax.swing.JButton obliczS;
    private javax.swing.JTable ocenyTable;
    private javax.swing.JTextPane opisOctxt;
    private javax.swing.JTextField peselUcznia;
    private javax.swing.JLabel przedmiotlabelOc;
    private javax.swing.JLabel sredniaOcenUczniaLa;
    private javax.swing.JLabel srednia_ocenLa;
    private javax.swing.JTextField stawka;
    private javax.swing.JTextPane trescUwagi;
    private javax.swing.JTextField typObecnosctxt;
    private javax.swing.JComboBox typUwagiCombo;
    private javax.swing.JTable uczTable;
    private javax.swing.JComboBox uczenComboBox;
    private javax.swing.JTextField ulicaUcznia;
    private javax.swing.JLabel urodzeniaLabel;
    private javax.swing.JTextField usunIdOc;
    private javax.swing.JButton usunNauczyciela;
    private javax.swing.JButton usunOcenebtn;
    private javax.swing.JTextField usunUczID;
    private javax.swing.JButton usunUczniabtn;
    private javax.swing.JLabel usuniecOcAlert;
    private javax.swing.JTextField wartoscOcentxt;
    private javax.swing.JComboBox wybierzPrzedmiot;
    private javax.swing.JComboBox wybierzPrzedmiotOc;
    private javax.swing.JComboBox wybierzUczniaUwf;
    private javax.swing.JComboBox<String> wyborUczniaOb;
    private javax.swing.JComboBox wyborUczniaOc;
    private javax.swing.JComboBox wyborUczniaOcF;
    private javax.swing.JButton wylogujNauc;
    private javax.swing.JButton wylogujOb;
    private javax.swing.JButton wylogujOc;
    private javax.swing.JButton wylogujPrzedmioty;
    private javax.swing.JButton wylogujUcz;
    private javax.swing.JButton wylogujUw;
    private javax.swing.JButton wyswietlNieobec;
    private javax.swing.JButton wyswietlUcznia;
    // End of variables declaration//GEN-END:variables

}

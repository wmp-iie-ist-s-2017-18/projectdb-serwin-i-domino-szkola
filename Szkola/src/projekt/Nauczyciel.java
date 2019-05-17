/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import hibernate.HibernateUtil;
import hibernate.Klasa;
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
import hibernate.ObecnosciQuery;
import hibernate.UwagiQuery;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
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
    private List<Oceny> ocenki;
    private UwagiQuery queryUw;
    private List<Uwagi> uwaga;
    private PrzedmiotyQuery queryP;
    private List<Przedmioty> przedmiot;
    private List <Obecnosc> obecnosc;
    private ObecnosciQuery queryOb;

    public Nauczyciel() {
        initComponents();
        NauczTableSelectAll();
        UcznTableSelectAll();
        OcenyTableSelectAll();
        UwagiTableSelectAll();
        KlasaComboBox();
        UczniowieCombobox();
        PrzedmiotyComboBox();
        TypUwagiComboBox();

    }
    
  
    public void TypUwagiComboBox(){
         Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Uwagi.class);
            uwaga  = criteria.list();

            typ_uwagiComBox.removeAllItems();

            for (Uwagi uw : uwaga) {
               typ_uwagiComBox.addItem(uw.getTypUwagi());
            }
            session.close();
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }
    
      public void PrzedmiotyComboBox(){
         Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Przedmioty.class);
            przedmiot  = criteria.list();

            wybierzPrzedmiotOc.removeAllItems();
            wybierzPrzedmiotOb.removeAllItems();
            

            for (Przedmioty p : przedmiot) {
             
               wybierzPrzedmiotOc.addItem(p.getNazwaPrzedmiotu());
               wybierzPrzedmiotOb.addItem(p.getNazwaPrzedmiotu());
            }
            session.close();
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }
    
    

    public void UczniowieCombobox() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Uczniowie.class);
            ucz = criteria.list();

            wyborUcznia.removeAllItems();
            wyborUcznia_Oc.removeAllItems();
            wyborUczniaOb.removeAllItems();

            for (Uczniowie u : ucz) {
                wyborUcznia.addItem(u.getImie() + " " + u.getNazwisko());
                wyborUcznia_Oc.addItem(u.getImie() + " " + u.getNazwisko());
                wyborUczniaOb.addItem(u.getImie() + " " + u.getNazwisko());
            }
            session.close();
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }

    public void KlasaComboBox() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Klasa.class);
            klasy = criteria.list();

            wybierzKlaseOb.removeAllItems();
            wybierzKlaseOb2.removeAllItems();

            wybierzKlaseU.removeAllItems();
            wybierzKlaseU2.removeAllItems();

            wybierzKlase.removeAllItems();
            wybierzKlaseUcz.removeAllItems();

            for (Klasa k : klasy) {
                wybierzKlaseOb.addItem(k.getRocznik() + k.getOddzial());
                wybierzKlaseOb2.addItem(k.getRocznik() + k.getOddzial());

                wybierzKlaseU.addItem(k.getRocznik() + k.getOddzial());
                wybierzKlaseU2.addItem(k.getRocznik() + k.getOddzial());

                wybierzKlaseUcz.addItem(k.getRocznik() + k.getOddzial());
                wybierzKlase.addItem(k.getRocznik() + k.getOddzial());

            }
            session.close();

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }
    }

    public void UwagiTableSelectAll() {
        queryUw = new UwagiQuery();
        query = new NauczycieleQuery();
        Object[] kolumny = {"Data wpisania", "Typ uwagi", "Opis", "Wpisał"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumny);

        try {
            nauczy = query.nauczycieleSelectAll();
            uwaga = queryUw.uwagiSelectAll();
            Object[] row = new Object[3];

            for (Uwagi uw : uwaga) {
                row[0] = uw.getDataWpisania();
                row[1] = uw.getTypUwagi();
                row[2] = uw.getOpis();

                model.addRow(row);
            }

            for (Nauczyciele n : nauczy) {
                row[3] = n.getImie() + " " + n.getNazwisko();
                model.addRow(row);
            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        TableUwagi.setModel(model);

    }

    public void NauczTableSelectAll() {

        query = new NauczycieleQuery();
        Object[] kolumny = {"Imię", "Nazwisko", "E-mail", "Nr telefonu", "Wynagrodzenie"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumny);

        try {
            nauczy = query.nauczycieleSelectAll();
            Object[] row = new Object[5];

            for (Nauczyciele n : nauczy) {
                row[0] = n.getImie();
                row[1] = n.getNazwisko();
                row[2] = n.getEmail();
                row[3] = n.getNrTelefonu();
                row[4] = n.getStawka();

                model.addRow(row);

            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");
        }

        nauTable.setModel(model);

    }

    public void OcenyTableSelectAll() {
        queryOc = new OcenyQuery();
        query = new NauczycieleQuery();
        Object[] kolumna = {"Data", "Ocena", "Opis", "Wystawił:"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumna);

        try {
            nauczy = query.nauczycieleSelectAll();
            ocenki = queryOc.ocenySelectAll();
            Object[] row = new Object[4];

            for (Oceny o : ocenki) {
                row[0] = o.getDataWpisania();
                row[1] = o.getWartosc();
                row[2] = o.getOpis();

                model.addRow(row);
            }

            for (Nauczyciele n : nauczy) {
                row[3] = n.getNazwisko() + " " + n.getImie();

                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        ocenyTable.setModel(model);
    }

    public void UcznTableSelectAll() {
        queryU = new UczniowieQuery();
        Object[] kolumn = {"Imie", "Nazwisko", "Pesel", "Data urodzenia",
            "Ulica", "Miasto", "Kod pocztowy", "Nr tel.rodziców", "Nr legitymacji"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumn);

        try {
            ucz = queryU.uczniowieSelectAll();
            Object[] row = new Object[9];

            for (Uczniowie u : ucz) {
                row[0] = u.getImie();
                row[1] = u.getNazwisko();
                row[2] = u.getPesel();
                row[3] = u.getDataUrodzenia();
                row[4] = u.getUlica();
                row[5] = u.getMiasto();
                row[6] = u.getKodPocztowy();
                row[7] = u.getNrTelefonuDoRodzica();
                row[8] = u.getNrLegitymacji();

                model.addRow(row);
            }

        } catch (Exception e) {
            System.out.println("Bład połączenia z bazą!");

        }
        uczTable.setModel(model);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Oceny = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        wybierzKlase = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        wybierzPrzedmiotOc = new javax.swing.JComboBox<String>();
        wybierz = new javax.swing.JButton();
        wyborUcznia_Oc = new javax.swing.JComboBox<String>();
        wyswietl = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ocenyTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ocena = new javax.swing.JTextField();
        opis = new javax.swing.JTextField();
        data = new javax.swing.JTextField();
        dodajOcene = new javax.swing.JButton();
        poprawOcene = new javax.swing.JButton();
        usunOcene = new javax.swing.JButton();
        srednia_ocenLa = new javax.swing.JLabel();
        wylogujOc = new javax.swing.JButton();
        SredniaOcenLabel = new javax.swing.JLabel();
        SredniaOcentxt = new javax.swing.JLabel();
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
        zaktualizujInfoN = new javax.swing.JButton();
        usunNauczyciela = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nrTel = new javax.swing.JTextField();
        stawka = new javax.swing.JTextField();
        wylogujNauc = new javax.swing.JButton();
        Uwagi = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        wybierzKlaseU = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        trescUwagi = new javax.swing.JTextField();
        dodajUwage = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        imieUwagi = new javax.swing.JTextField();
        nazwiskoUwagitxt = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        wybierzKlaseU2 = new javax.swing.JComboBox<String>();
        wyborUcznia = new javax.swing.JComboBox<String>();
        wyswietlUwagi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableUwagi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        typ_uwagiComBox = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        dataWpistxt = new javax.swing.JTextField();
        wylogujUw = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        wybierzKlaseOb = new javax.swing.JComboBox<String>();
        jLabel16 = new javax.swing.JLabel();
        imieObtxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        nazwiskoObtxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        dataObe = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        wybierzPrzedmiotOb = new javax.swing.JComboBox<String>();
        typObec = new javax.swing.JComboBox();
        dodajObecnoscbtn = new javax.swing.JButton();
        jSeparator = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        wybierzKlaseOb2 = new javax.swing.JComboBox<String>();
        wyborUczniaOb = new javax.swing.JComboBox<String>();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableNObec = new javax.swing.JTable();
        wyswietlNieobec = new javax.swing.JButton();
        wylogujOb = new javax.swing.JButton();
        wybierzP = new javax.swing.JButton();
        Uczniowie = new javax.swing.JPanel();
        KlasaLabel = new javax.swing.JLabel();
        wybierzKlaseUcz = new javax.swing.JComboBox<String>();
        jScrollPane4 = new javax.swing.JScrollPane();
        uczTable = new javax.swing.JTable();
        dodajUcznia = new javax.swing.JButton();
        zaktualizujInfoU = new javax.swing.JButton();
        usunUczniabtn = new javax.swing.JButton();
        ImieLabel = new javax.swing.JLabel();
        NazwiskoLabel = new javax.swing.JLabel();
        PESELlabel = new javax.swing.JLabel();
        imieUczniatxt = new javax.swing.JTextField();
        naziwskoUcznia = new javax.swing.JTextField();
        pesel = new javax.swing.JTextField();
        urodzeniaLabel = new javax.swing.JLabel();
        UlicaLabel = new javax.swing.JLabel();
        MiastoLabel = new javax.swing.JLabel();
        dataUrodzenia = new javax.swing.JTextField();
        ulica = new javax.swing.JTextField();
        miasto = new javax.swing.JTextField();
        kodlabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        kodPoczt = new javax.swing.JTextField();
        nrTelRodzica = new javax.swing.JTextField();
        nrLegitymacji = new javax.swing.JTextField();
        wylogujUcz = new javax.swing.JButton();
        alertU = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        idUtxt = new javax.swing.JTextField();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Klasa");

        wybierzKlase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", " " }));

        jLabel2.setText("Przedmiot");

        wybierzPrzedmiotOc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matematyka", "Język polski", "Biologia", "Geografia", "Fizyka", "Historia", "Wychowanie fizyczne", "Język angielski", "Jezyk niemiecki" }));
        wybierzPrzedmiotOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wybierzPrzedmiotOcActionPerformed(evt);
            }
        });

        wybierz.setText("Wybierz");

        wyborUcznia_Oc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", " ", " " }));

        wyswietl.setText("Wyświetl");

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
                "Data", "Ocena", "Opis", "Wystawił"
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

        poprawOcene.setText("Skoryguj");

        usunOcene.setText("Usuń ocenę");
        usunOcene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunOceneActionPerformed(evt);
            }
        });

        wylogujOc.setText("Wyloguj");
        wylogujOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujOcActionPerformed(evt);
            }
        });

        SredniaOcenLabel.setText("Średnia ocen: ");

        javax.swing.GroupLayout OcenyLayout = new javax.swing.GroupLayout(Oceny);
        Oceny.setLayout(OcenyLayout);
        OcenyLayout.setHorizontalGroup(
            OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OcenyLayout.createSequentialGroup()
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addComponent(wyborUcznia_Oc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(wyswietl))
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wybierzKlase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wybierzPrzedmiotOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(wybierz))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addComponent(SredniaOcenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SredniaOcentxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(srednia_ocenLa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(42, 42, 42)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(27, 27, 27)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opis, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ocena, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(197, Short.MAX_VALUE))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(poprawOcene, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dodajOcene, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usunOcene, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OcenyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wylogujOc))))
        );
        OcenyLayout.setVerticalGroup(
            OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OcenyLayout.createSequentialGroup()
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(OcenyLayout.createSequentialGroup()
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wybierzPrzedmiotOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(wybierz))
                                .addGap(18, 18, 18)
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(wybierzKlase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wyborUcznia_Oc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wyswietl))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(OcenyLayout.createSequentialGroup()
                        .addComponent(wylogujOc)
                        .addGap(13, 13, 13)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ocena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(29, 29, 29)
                        .addComponent(dodajOcene)
                        .addGap(18, 18, 18)
                        .addComponent(poprawOcene)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usunOcene)))
                .addGap(18, 18, 18)
                .addGroup(OcenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SredniaOcenLabel)
                    .addComponent(SredniaOcentxt))
                .addGap(227, 227, 227)
                .addComponent(srednia_ocenLa)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Oceny", Oceny);

        jLabel17.setText("Imię");

        jLabel18.setText("Naziwsko");

        jLabel19.setText("E - mail ");

        nauTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                "Imię", "Nazwisko", "E - mail", "Nr telefonu", "Wynagrodzenie"
            }
        ));
        jScrollPane3.setViewportView(nauTable);

        dodajNauczyciela.setText("Dodaj nauczyciela");

        zaktualizujInfoN.setText("Zaktualizuj informacje");

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

        javax.swing.GroupLayout nauczycieleLayout = new javax.swing.GroupLayout(nauczyciele);
        nauczyciele.setLayout(nauczycieleLayout);
        nauczycieleLayout.setHorizontalGroup(
            nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nauczycieleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wylogujNauc))
            .addGroup(nauczycieleLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nauczycieleLayout.createSequentialGroup()
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(38, 38, 38)
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imieNauczyciela)
                            .addComponent(naziwskoNauczyciela)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nauczycieleLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(dodajNauczyciela)
                                .addGap(18, 18, 18)
                                .addComponent(zaktualizujInfoN)
                                .addGap(18, 18, 18)
                                .addComponent(usunNauczyciela))
                            .addGroup(nauczycieleLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(35, 35, 35)
                                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nrTel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stawka, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        nauczycieleLayout.setVerticalGroup(
            nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nauczycieleLayout.createSequentialGroup()
                .addComponent(wylogujNauc)
                .addGap(5, 5, 5)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(imieNauczyciela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(nrTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(naziwskoNauczyciela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(stawka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(nauczycieleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dodajNauczyciela)
                    .addComponent(zaktualizujInfoN)
                    .addComponent(usunNauczyciela))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nauczyciele", nauczyciele);

        jLabel3.setText("Klasa");

        wybierzKlaseU.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel4.setText("Treść uwagi");

        dodajUwage.setText("Dodaj uwagę");
        dodajUwage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajUwageActionPerformed(evt);
            }
        });

        jLabel5.setText("Imię");

        jLabel6.setText("Nazwisko");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setText("Klasa");

        wybierzKlaseU2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        wyborUcznia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", "Imię i nazwisko", " " }));

        wyswietlUwagi.setText("Wyświetl uwagi");

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
                "Data wpisania", "Typ_uwagi", "Opis", "Wpisał"
            }
        ));
        jScrollPane2.setViewportView(TableUwagi);

        jLabel15.setText("Typ uwagi");

        typ_uwagiComBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Data wpisania");

        wylogujUw.setText("Wyloguj");
        wylogujUw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujUwActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UwagiLayout = new javax.swing.GroupLayout(Uwagi);
        Uwagi.setLayout(UwagiLayout);
        UwagiLayout.setHorizontalGroup(
            UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UwagiLayout.createSequentialGroup()
                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trescUwagi, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(UwagiLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(67, 67, 67)
                                .addComponent(wybierzKlaseU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UwagiLayout.createSequentialGroup()
                                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel4))
                                .addGap(25, 25, 25)
                                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dataWpistxt)
                                    .addComponent(nazwiskoUwagitxt)
                                    .addComponent(imieUwagi)
                                    .addComponent(typ_uwagiComBox, 0, 135, Short.MAX_VALUE)))))
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(dodajUwage)))
                .addGap(47, 47, 47)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(wybierzKlaseU2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wylogujUw))
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UwagiLayout.createSequentialGroup()
                                .addComponent(wyborUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(wyswietlUwagi, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        UwagiLayout.setVerticalGroup(
            UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UwagiLayout.createSequentialGroup()
                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(wybierzKlaseU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imieUwagi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(nazwiskoUwagitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(dataWpistxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(typ_uwagiComBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trescUwagi, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(dodajUwage))
                    .addGroup(UwagiLayout.createSequentialGroup()
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UwagiLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(wybierzKlaseU2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(wylogujUw))
                        .addGap(27, 27, 27)
                        .addGroup(UwagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wyborUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wyswietlUwagi))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Uwagi ", Uwagi);

        jLabel14.setText("Klasa");

        wybierzKlaseOb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel16.setText("Imię");

        jLabel20.setText("Nazwisko");

        jLabel21.setText("Typ obecności");

        jLabel23.setText("Data");

        jLabel24.setText("Przedmiot");

        wybierzPrzedmiotOb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matematyka", "Język polski", "Biologia", "Geografia", "Fizyka", "Historia", "Wychowanie fizyczne", "Język angielski", "Jezyk niemiecki" }));

        typObec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dodajObecnoscbtn.setText("Dodaj obecność");

        jSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel25.setText("Klasa");

        wybierzKlaseOb2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

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
                "Data ", "Przedmiot"
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

        wybierzP.setText("Wybierz");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel24))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(wybierzPrzedmiotOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(wybierzP))
                                    .addComponent(wybierzKlaseOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(dataObe, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                        .addComponent(nazwiskoObtxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(imieObtxt, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(typObec, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(dodajObecnoscbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addGap(18, 18, 18)
                                    .addComponent(wybierzKlaseOb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(188, 188, 188)
                                    .addComponent(wyswietlNieobec)
                                    .addGap(110, 110, 110)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(wyborUczniaOb, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wylogujOb))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(418, 418, 418)
                    .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(535, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(wybierzKlaseOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(wylogujOb))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(wybierzPrzedmiotOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wybierzP)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wybierzKlaseOb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wyborUczniaOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wyswietlNieobec))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imieObtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(nazwiskoObtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(dataObe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(typObec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(dodajObecnoscbtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(278, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Obecności", jPanel2);

        KlasaLabel.setText("Klasa");

        wybierzKlaseUcz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

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

        zaktualizujInfoU.setText("Zaktualizuj informacje");
        zaktualizujInfoU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zaktualizujInfoUActionPerformed(evt);
            }
        });

        usunUczniabtn.setText("Usuń ucznia");
        usunUczniabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunUczniabtnActionPerformed(evt);
            }
        });

        ImieLabel.setText("Imię");

        NazwiskoLabel.setText("Naziwsko");

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

        javax.swing.GroupLayout UczniowieLayout = new javax.swing.GroupLayout(Uczniowie);
        Uczniowie.setLayout(UczniowieLayout);
        UczniowieLayout.setHorizontalGroup(
            UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UczniowieLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImieLabel)
                    .addComponent(NazwiskoLabel)
                    .addComponent(PESELlabel)
                    .addComponent(KlasaLabel)
                    .addComponent(jLabel13))
                .addGap(134, 134, 134)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imieUczniatxt)
                            .addComponent(naziwskoUcznia)
                            .addComponent(pesel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(idUtxt))
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UczniowieLayout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(UlicaLabel)
                                            .addComponent(MiastoLabel)))
                                    .addGroup(UczniowieLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(urodzeniaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dataUrodzenia)
                                    .addComponent(ulica)
                                    .addComponent(miasto, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(UczniowieLayout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(jLabel29)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UczniowieLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(kodlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nrTelRodzica, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kodPoczt)
                                    .addComponent(nrLegitymacji))
                                .addGap(27, 27, 27))
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dodajUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(zaktualizujInfoU)
                                .addGap(18, 18, 18)
                                .addComponent(usunUczniabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146))))
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(alertU, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(wybierzKlaseUcz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wylogujUcz))))
            .addGroup(UczniowieLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                .addContainerGap())
        );
        UczniowieLayout.setVerticalGroup(
            UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UczniowieLayout.createSequentialGroup()
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wylogujUcz)
                            .addComponent(alertU))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dodajUcznia)
                            .addComponent(zaktualizujInfoU)
                            .addComponent(usunUczniabtn)))
                    .addGroup(UczniowieLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UczniowieLayout.createSequentialGroup()
                                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wybierzKlaseUcz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(KlasaLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idUtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UczniowieLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(4, 4, 4)))))
                .addGap(8, 8, 8)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(imieUczniatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(urodzeniaLabel)
                        .addComponent(dataUrodzenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kodlabel)
                        .addComponent(kodPoczt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ImieLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(naziwskoUcznia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(UlicaLabel)
                        .addComponent(ulica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29)
                        .addComponent(nrTelRodzica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NazwiskoLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(UczniowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PESELlabel)
                    .addComponent(pesel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MiastoLabel)
                    .addComponent(miasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(nrLegitymacji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Uczniowie", Uczniowie);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zaktualizujInfoUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zaktualizujInfoUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zaktualizujInfoUActionPerformed

    private void dodajUczniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajUczniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dodajUczniaActionPerformed

    private void dodajUwageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajUwageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dodajUwageActionPerformed

    private void wylogujNaucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujNaucActionPerformed
        if (evt.getSource().equals(wylogujNauc)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujNaucActionPerformed

    private void wylogujUczActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujUczActionPerformed
        if (evt.getSource().equals(wylogujUcz)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujUczActionPerformed

    private void wylogujObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujObActionPerformed
        if (evt.getSource().equals(wylogujOb)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujObActionPerformed

    private void wylogujUwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujUwActionPerformed
        if (evt.getSource().equals(wylogujUw)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujUwActionPerformed

    private void usunNauczycielaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunNauczycielaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usunNauczycielaActionPerformed

    private void usunUczniabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunUczniabtnActionPerformed


    }//GEN-LAST:event_usunUczniabtnActionPerformed

    private void wylogujOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujOcActionPerformed
        if (evt.getSource().equals(wylogujOc)) {
            this.setVisible(false);
            Logowanie loginIn = new Logowanie();
            loginIn.setVisible(true);
        }
    }//GEN-LAST:event_wylogujOcActionPerformed

    private void usunOceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunOceneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usunOceneActionPerformed

    private void dodajOceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajOceneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dodajOceneActionPerformed

    private void wybierzPrzedmiotOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wybierzPrzedmiotOcActionPerformed

    }//GEN-LAST:event_wybierzPrzedmiotOcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImieLabel;
    private javax.swing.JLabel KlasaLabel;
    private javax.swing.JLabel MiastoLabel;
    private javax.swing.JLabel NazwiskoLabel;
    private javax.swing.JPanel Oceny;
    private javax.swing.JLabel PESELlabel;
    private javax.swing.JLabel SredniaOcenLabel;
    private javax.swing.JLabel SredniaOcentxt;
    private javax.swing.JTable TableNObec;
    private javax.swing.JTable TableUwagi;
    private javax.swing.JPanel Uczniowie;
    private javax.swing.JLabel UlicaLabel;
    private javax.swing.JPanel Uwagi;
    private javax.swing.JLabel alertU;
    private javax.swing.JTextField data;
    private javax.swing.JTextField dataObe;
    private javax.swing.JTextField dataUrodzenia;
    private javax.swing.JTextField dataWpistxt;
    private javax.swing.JButton dodajNauczyciela;
    private javax.swing.JButton dodajObecnoscbtn;
    private javax.swing.JButton dodajOcene;
    private javax.swing.JButton dodajUcznia;
    private javax.swing.JButton dodajUwage;
    private javax.swing.JTextField email;
    private javax.swing.JTextField idUtxt;
    private javax.swing.JTextField imieNauczyciela;
    private javax.swing.JTextField imieObtxt;
    private javax.swing.JTextField imieUczniatxt;
    private javax.swing.JTextField imieUwagi;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField kodPoczt;
    private javax.swing.JLabel kodlabel;
    private javax.swing.JTextField miasto;
    private javax.swing.JTable nauTable;
    private javax.swing.JPanel nauczyciele;
    private javax.swing.JTextField naziwskoNauczyciela;
    private javax.swing.JTextField naziwskoUcznia;
    private javax.swing.JTextField nazwiskoObtxt;
    private javax.swing.JTextField nazwiskoUwagitxt;
    private javax.swing.JTextField nrLegitymacji;
    private javax.swing.JTextField nrTel;
    private javax.swing.JTextField nrTelRodzica;
    private javax.swing.JTextField ocena;
    private javax.swing.JTable ocenyTable;
    private javax.swing.JTextField opis;
    private javax.swing.JTextField pesel;
    private javax.swing.JButton poprawOcene;
    private javax.swing.JLabel srednia_ocenLa;
    private javax.swing.JTextField stawka;
    private javax.swing.JTextField trescUwagi;
    private javax.swing.JComboBox typObec;
    private javax.swing.JComboBox typ_uwagiComBox;
    private javax.swing.JTable uczTable;
    private javax.swing.JTextField ulica;
    private javax.swing.JLabel urodzeniaLabel;
    private javax.swing.JButton usunNauczyciela;
    private javax.swing.JButton usunOcene;
    private javax.swing.JButton usunUczniabtn;
    private javax.swing.JButton wybierz;
    private javax.swing.JComboBox<String> wybierzKlase;
    private javax.swing.JComboBox<String> wybierzKlaseOb;
    private javax.swing.JComboBox<String> wybierzKlaseOb2;
    private javax.swing.JComboBox<String> wybierzKlaseU;
    private javax.swing.JComboBox<String> wybierzKlaseU2;
    private javax.swing.JComboBox<String> wybierzKlaseUcz;
    private javax.swing.JButton wybierzP;
    private javax.swing.JComboBox<String> wybierzPrzedmiotOb;
    private javax.swing.JComboBox<String> wybierzPrzedmiotOc;
    private javax.swing.JComboBox<String> wyborUcznia;
    private javax.swing.JComboBox<String> wyborUczniaOb;
    private javax.swing.JComboBox<String> wyborUcznia_Oc;
    private javax.swing.JButton wylogujNauc;
    private javax.swing.JButton wylogujOb;
    private javax.swing.JButton wylogujOc;
    private javax.swing.JButton wylogujUcz;
    private javax.swing.JButton wylogujUw;
    private javax.swing.JButton wyswietl;
    private javax.swing.JButton wyswietlNieobec;
    private javax.swing.JButton wyswietlUwagi;
    private javax.swing.JButton zaktualizujInfoN;
    private javax.swing.JButton zaktualizujInfoU;
    // End of variables declaration//GEN-END:variables

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;

/**
 *
 * @author monika
 */
public class OcenyQuery {

    Session session = null;
    Query queryOc = null;
    Criteria criteria = null;
     private List<Oceny> ocenki;

    public List<Oceny> ocenySelectAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Oceny.class);
        List<Oceny> ocenki = criteria.list();
        session.close();
        return ocenki;
    }

    public void usunOcene(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Oceny where id_oceny=" + id);
        Oceny o = (Oceny) query.uniqueResult();
        Transaction t = session.beginTransaction();
        session.delete(o);
        t.commit();
        session.close();

    }

    public Oceny selectById(int id_ucznia, int id_przedmiotu) {
        Oceny o = null;
        session = HibernateUtil.getSessionFactory().openSession();
       String hql = "from Oceny where id_ucznia = " +id_ucznia+ " and id_przedmioty = " +id_przedmiotu;
        queryOc = session.createQuery(hql);
        o = (Oceny) queryOc.uniqueResult();
        session.close();
        return o;
    }

    public List OcenySelectAllOnID(int idUcznia, int id_przedmiotu) {
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Oceny.class);
         ocenki =  criteria.list();
        session.close();
        int i = 0;
        
        for (Oceny o : ocenki) {
            if (o.getUczniowie().getIdUcznia() != idUcznia && o.getPrzedmioty().getIdPrzedmioty() != id_przedmiotu) {
                ocenki.remove(o);
            }
            i++;
        }
        return ocenki;
    }

    public double srednia(int id_ucznia, int id_przedmioty) {
        double srednia = 0.0;
        session = HibernateUtil.getSessionFactory().openSession();

        //session.doWork(new Work(){
        ReturningWork<Double> sredniaRW = new ReturningWork<Double>() {

            @Override
            public Double execute(Connection cnctn) throws SQLException {
                CallableStatement funkcja = cnctn.prepareCall("{? = call srednia(?,?) }");
                funkcja.setInt(2, id_ucznia);
                funkcja.setInt(3, id_przedmioty);
                funkcja.registerOutParameter(1, Types.DOUBLE);
                funkcja.execute();
                double sredniaOc = 0.0;
                sredniaOc = funkcja.getDouble(1);
                System.out.println("(Funkcaj ReturningWork) Srednia = " + sredniaOc);
                return sredniaOc;
            }

        };
        srednia = session.doReturningWork(sredniaRW);
        session.close();
        return srednia;
    }

    public void DodajOcene(int idOceny, Date datawpisania, String opis, int wartosc, int id_przedmiotu, int idUcznia, int idNauczyciela) {
        session = HibernateUtil.getSessionFactory().openSession();
        Uczniowie u = new UczniowieQuery().SelectById(idUcznia);
        Nauczyciele n = new NauczycieleQuery().SelectById(idNauczyciela);
        Przedmioty p = new PrzedmiotyQuery().PrzedmiotySelectById(id_przedmiotu);
        Oceny o = new Oceny(idOceny, n, p, u, opis, datawpisania, wartosc);
        Transaction t = session.beginTransaction();
        session.save(o);
        t.commit();
        session.close();
    }
}

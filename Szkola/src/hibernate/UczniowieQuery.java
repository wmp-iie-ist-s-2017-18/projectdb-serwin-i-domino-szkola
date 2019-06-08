/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

/**
 *
 * @author monika
 */
public class UczniowieQuery {
    
Session session = null;
    Query queryUcz = null;
     Criteria criteria = null;   

    public boolean selecyByIDandPassword(String id, String password) {
        Uczniowie ucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Uczniowie where id = '" + id + "' and haslo = '" + password + "'";
        queryUcz = session.createQuery(hql);
        ucz = (Uczniowie) queryUcz.uniqueResult();
        session.close();
        if (ucz != null) {
            return true;
        }
        return false;
    }
    
    public Uczniowie selectByIDandPassword(String id, String password){
        Uczniowie ucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
         String hql = "from Uczniowie where id = '" + id + "' and haslo = '" + password + "'";
        queryUcz = session.createQuery(hql);
        ucz = (Uczniowie) queryUcz.uniqueResult();
        session.close();
        return ucz;
        
    }
    
    public List <Uczniowie> uczniowieSelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Uczniowie.class);
        List <Uczniowie> uczniowie = criteria.list();
        session.close();
        return uczniowie;
    } 
    
     
public Uczniowie SelectById(int id){
          Uczniowie u = null;
          session = HibernateUtil.getSessionFactory().openSession();
          String hql = "from Uczniowie where id_ucznia = " +id;
          queryUcz = session.createQuery(hql);
          u = (Uczniowie)queryUcz.uniqueResult();
          session.close();
          return u;
      }

    public void DodajUcznia(int idUcz, String imie, String nazwisko, String PESEL, String nr_legitymacji, Date dataurodzenia,
            String miasto, String ulica, String kod_pocztowy, String nr_telefonu, String haslo){
        session = HibernateUtil.getSessionFactory().openSession();
        Uczniowie ucz = new Uczniowie(idUcz, imie, nazwisko, PESEL, nr_legitymacji, dataurodzenia, miasto, ulica, kod_pocztowy, nr_telefonu, haslo);
        Transaction t = session.beginTransaction();
        session.save(ucz);
        t.commit();
        session.close();
    }

    
    
     public void usunUcznia(int id_ucznia){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Uczniowie where id_ucznia=" + id_ucznia);
        Uczniowie ucz = (Uczniowie) query.uniqueResult();
        Transaction t = session.beginTransaction();
        session.delete(ucz);
        t.commit();
        session.close();
        
        
    }
     public void dodajUczniaa(int idUcznia, String imie, String nazwisko, String PESEL, String nr_legitymacji, Date data_urodzenia, String miasto, String ulica, String kod_pocztowy, String nr_telefonu_do_rodzica, String haslo){
          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = session.beginTransaction();
          session.doWork(new Work() {

              @Override
              public void execute(Connection cnctn) throws SQLException {
                  CallableStatement cs = cnctn.prepareCall("{call dodaj_ucznia(?,?,?,?,?,?,?,?,?,?,?)}");
                  cs.setInt(1,idUcznia);
                  cs.setString(2, imie);
                  cs.setString(3, nazwisko);
                  cs.setString(4, PESEL);
                  cs.setString(5, nr_legitymacji);
                  cs.setDate(6, (java.sql.Date) data_urodzenia);
                  cs.setString(7, miasto);
                  cs.setString(8, ulica);
                  cs.setString(9, kod_pocztowy);
                  cs.setString(10, nr_telefonu_do_rodzica);
                  cs.setString(11, haslo);
                  cs.execute();
       
          
      } 
          });
           transaction.commit();
           session.close();
     }
}


    
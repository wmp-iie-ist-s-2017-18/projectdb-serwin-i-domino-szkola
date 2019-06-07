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
public class NauczycieleQuery {

    Session session = null;
    Query query = null;
    Criteria criteria = null;

    public boolean selecyByIDandPassword(int id, String password) {
        Nauczyciele naucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Nauczyciele where id = '" + id + "' and haslo = '" + password + "'";
        query = session.createQuery(hql);
        naucz = (Nauczyciele) query.uniqueResult();
        session.close();
        if (naucz != null) {
            return true;
        }
        return false;
    }

    public Nauczyciele selectByIDandPassword(int id, String password) {
        Nauczyciele naucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Nauczyciele where id = '" + id + "' and haslo = '" + password + "'";
        query = session.createQuery(hql);
        naucz = (Nauczyciele) query.uniqueResult();
        session.close();
        return naucz;
    }

    public List<Nauczyciele> nauczycieleSelectAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Nauczyciele.class);
        List<Nauczyciele> nauczyciele = criteria.list();
        session.close();
        return nauczyciele;
    }
    
    
      public void NauczycieleAdd(Nauczyciele n){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(n);
        t.commit();
        session.close();
    }
      
      public void zmienDaneNaucz (int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Nauczyciele n= null;
        query = session.createQuery("from Nauczyciele where id=" + id);
        n = (Nauczyciele) query.uniqueResult();
        Transaction t = session.beginTransaction();
        session.save(n);
        t.commit();
        session.close();
      }
      
      public void usunNaucz (int id ){
          session = HibernateUtil.getSessionFactory().openSession();
          query = session.createQuery("from Nauczyciele where id= " + id);
          Nauczyciele n = (Nauczyciele)query.uniqueResult();
          Transaction t = session.beginTransaction();
          session.delete(n);
          t.commit();
          session.close();
          
          
      }
      
      public Nauczyciele SelectById(int id){
          Nauczyciele n = null;
          session = HibernateUtil.getSessionFactory().openSession();
          String hql = "from Nauczyciele where id = " +id;
          query = session.createQuery(hql);
          n = (Nauczyciele)query.uniqueResult();
          session.close();
          return n;
      }
      
     public void dodajNauczyciela(int idNauczyciela, String imie, String nazwisko, String email, String nrTelefonu, String stawka, String haslo){
          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = session.beginTransaction();
          session.doWork(new Work() {

              @Override
              public void execute(Connection cnctn) throws SQLException {
                  CallableStatement cs = cnctn.prepareCall("{call dodaj_nauczyciela(?,?,?,?,?,?,?)}");
                  cs.setInt(1,idNauczyciela);
                  cs.setString(2, imie);
                  cs.setString(3, nazwisko);
                  cs.setString(4, email);
                  cs.setString(5, nrTelefonu);
                  cs.setString(6, stawka);
                  cs.setString(7, haslo);
                  cs.execute();
       
          
      } 
          });
           transaction.commit();
           session.close();
     }
}
          
              
      
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author monika
 */
public class PrzedmiotyQuery {
    
Session session = null;
    Query query = null;
    Criteria criteria = null;
    
      public List <Przedmioty> PrzedmiotySelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Przedmioty.class);
        List <Przedmioty> przedmioty = criteria.list();
        session.close();
        return przedmioty;
        
        
    } 
      
       public Przedmioty PrzedmiotySelectById(int id){
          Przedmioty p = null;
          session = HibernateUtil.getSessionFactory().openSession();
          String hql = "from Przedmioty where id_przedmioty = " +id;
          query = session.createQuery(hql);
          p = (Przedmioty)query.uniqueResult();
          session.close();
          return p;
      }
       
         public void PrzedmiotyAdd(Przedmioty p){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(p);
        t.commit();
        session.close();
    }
          public void usunPrzedmiot (int id_przedmioty){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Przedmioty where id_przedmioty=" + id_przedmioty);
        Przedmioty p = (Przedmioty) query.uniqueResult();
        Transaction t = session.beginTransaction();
        session.delete(p);
        t.commit();
        session.close();
        
      
}
}

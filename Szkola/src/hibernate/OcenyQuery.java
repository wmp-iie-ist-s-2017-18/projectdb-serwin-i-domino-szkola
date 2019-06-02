/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author monika
 */
public class OcenyQuery {
    
Session session = null;
    Query queryOc = null;
     Criteria criteria = null; 
     
       public List <Oceny> ocenySelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Oceny.class);
        List <Oceny> ocenki = criteria.list();
        session.close();
        return ocenki;
    } 
       
        public void usunOcene(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Oceny where id=" + id);
        Oceny o = (Oceny) query.uniqueResult();
        Transaction t = session.beginTransaction();
        session.delete(o);
        t.commit();
        session.close();
        
    }
        
        
        
}



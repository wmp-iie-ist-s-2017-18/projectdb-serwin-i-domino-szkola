/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author monika
 */
public class UczniowieQuery {
    Session session = null;
    Query query= null;
    
   public boolean SelectByIdAndPassword(int id, String password){
        Uczniowie u = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Uczniowie where id = " + id + "and haslo = " + password + ",";
        query = session.createQuery(hql);
        u = (Uczniowie)query.uniqueResult();
        session.close();
        if(u != null){
            return true;
        }
        return false;
   }
   public Uczniowie SelectByIdAndPasswordUczniowie(int id, String password){
       Uczniowie u = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Uczniowie where id = " + id + "and haslo = " + password + ",";
        query = session.createQuery(hql);
        u = (Uczniowie)query.uniqueResult();
        session.close();
        return u;
        
        
       
   }
}


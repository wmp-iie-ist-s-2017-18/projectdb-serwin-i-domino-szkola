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
    Query query = null;

    public boolean selecyByIDandPassword(String id, String password) {
        Uczniowie ucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Uczniowie where id = '" + id + "' and haslo = '" + password + "'";
        query = session.createQuery(hql);
        ucz = (Uczniowie) query.uniqueResult();
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
        query = session.createQuery(hql);
        ucz = (Uczniowie) query.uniqueResult();
        session.close();
        return ucz;
        
    }

}

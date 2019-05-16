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
    
     

}

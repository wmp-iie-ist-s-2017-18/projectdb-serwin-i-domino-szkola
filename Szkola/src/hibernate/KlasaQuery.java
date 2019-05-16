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

/**
 *
 * @author monika
 */
public class KlasaQuery {
    
     Session session = null;
    Query queryUcz = null;
     Criteria criteria = null; 
    
    public List <Klasa> klasaSelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Klasa.class);
        List <Klasa> klasy = criteria.list();
        session.close();
        return klasy;
    } 
}

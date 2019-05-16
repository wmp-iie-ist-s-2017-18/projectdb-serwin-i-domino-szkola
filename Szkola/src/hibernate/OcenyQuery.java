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
}

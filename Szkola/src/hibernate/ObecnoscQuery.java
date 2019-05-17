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
public class ObecnoscQuery {
      Session session = null;
    Query query = null;
    Criteria criteria = null;
    
   public List <Obecnosc> obecnoscSelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Obecnosc.class);
        List <Obecnosc> obecny = criteria.list();
        session.close();
        return obecny;
    } 

}


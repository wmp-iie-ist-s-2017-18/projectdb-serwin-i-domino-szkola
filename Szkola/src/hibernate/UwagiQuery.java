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
import hibernate.Nauczyciele;
import org.hibernate.Transaction;

/**
 *
 * @author monika
 */
public class UwagiQuery {
    Session session = null;
    Query query = null;
    Criteria criteria = null;
     private UwagiQuery queryUw;
     private UczniowieQuery queryU;
    
     public List <Uwagi> uwagiSelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Uwagi.class);
        List <Uwagi> uwag = criteria.list();
        session.close();
        return uwag;
    }
     
     public void DodajUwage(int idUwagi, String opis, Date datawpisania, String typUwagi, int idUcznia, int idNauczyciela){
         session = HibernateUtil.getSessionFactory().openSession();
        Uczniowie u = new UczniowieQuery().SelectById(idUcznia);
        Nauczyciele n = new NauczycieleQuery().SelectById(idNauczyciela);
        Uwagi uw = new Uwagi(idUwagi, n, u, opis, datawpisania, typUwagi);
         Transaction t = session.beginTransaction();
         session.save(uw);
         t.commit();
         session.close();
         
     }
     
     
}


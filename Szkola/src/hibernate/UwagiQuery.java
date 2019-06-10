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
       public Uwagi selectByIdUcznia(int id){
        Uwagi uw = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Uwagi where id_ucznia=" + id;
        query = session.createQuery(hql);
        uw = (Uwagi) query.uniqueResult();
        session.close();
        return uw;
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
     
     public List <Uwagi> UwagiUcznia (int id){
         session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Uwagi.class);
        List <Uwagi> uwaga = criteria.list();
        session.close();
        int i = 0;
        for(Uwagi uw: uwaga){
            if(uw.getUczniowie().getIdUcznia()!= id ){
                uwaga.remove(i);
            }
            i++;
        }
        return uwaga;
     }

 public void usunUwage (int id_uwagi){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Uwagi where id_uwagi=" + id_uwagi);
        Uwagi u = (Uwagi) query.uniqueResult();
        Transaction t = session.beginTransaction();
        session.delete(u);
        t.commit();
        session.close();
     
}
}

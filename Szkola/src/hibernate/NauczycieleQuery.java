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
public class NauczycieleQuery {
    
 Session session = null;
    Query query = null;
    Criteria criteria = null;

    public boolean selecyByIDandPassword(int id, String password) {
        Nauczyciele naucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Nauczyciele where id = '" + id + "' and haslo = '" + password + "'";
        query = session.createQuery(hql);
        naucz = (Nauczyciele) query.uniqueResult();
        session.close();
        if (naucz != null) {
            return true;
        }
        return false;
    }
    
    public Nauczyciele selectByIDandPassword(int id, String password){
        Nauczyciele naucz = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Nauczyciele where id = '" + id + "' and haslo = '" + password + "'";
        query = session.createQuery(hql);
        naucz = (Nauczyciele) query.uniqueResult();
        session.close();
        return naucz;
    }
    
    
 
    public List <Nauczyciele> nauczycieleSelectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        criteria = session.createCriteria(Nauczyciele.class);
        List <Nauczyciele> nauczyciele = criteria.list();
        session.close();
        return nauczyciele;
    }
}

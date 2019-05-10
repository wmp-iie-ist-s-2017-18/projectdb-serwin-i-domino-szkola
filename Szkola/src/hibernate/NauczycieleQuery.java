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
public class NauczycieleQuery {

    Session session = null;
    Query query = null;

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
}

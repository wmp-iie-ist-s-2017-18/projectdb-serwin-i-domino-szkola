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
    
    public boolean SelectByIdAndPassword(int id, String password){
        Nauczyciele n = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Nauczyciele where id = " + id + "and haslo = " + password + ",";
        query = session.createQuery(hql);
        n = (Nauczyciele)query.uniqueResult();
        session.close();
        if(n != null){
            return true;
        }
        return false;
    
}
    public Nauczyciele SelectByIdAndPasswordNauczyciele (int id, String password){
        Nauczyciele n = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Nauczyciele where id='" + id + "' and haslo='" + password + "'";
        query = session.createQuery(hql);
        n = (Nauczyciele) query.uniqueResult();
        session.close();
        return n;
    }
}

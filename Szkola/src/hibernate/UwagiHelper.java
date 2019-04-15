/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.Session;

/**
 *
 * @author monika
 */
public class UwagiHelper {
    
    Session session = null;
    
    public UwagiHelper(){
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}

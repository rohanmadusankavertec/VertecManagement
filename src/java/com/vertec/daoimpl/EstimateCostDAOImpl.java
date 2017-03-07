/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.hibe.model.Employee;
import com.vertec.hibe.model.EstimateCost;
import com.vertec.hibe.model.Features;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class EstimateCostDAOImpl {
    
    public List<Employee> loadAllEmployees(){
    
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
//                Query query = session.getNamedQuery("Employee.findAll");
                Query query = session.createQuery("SELECT e FROM Employee e WHERE e.isValid = :isValid");
                query.setParameter("isValid", true);

                List<Employee> prList = query.list();
                return prList;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
    
        
        return null;
    
    }
    
    public String saveEstimateCost(EstimateCost ec) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(ec);
                session.flush();
                transaction.commit();
                return VertecConstants.SUCCESS;

            } catch (Exception e) {
                e.printStackTrace();
                return VertecConstants.ERROR;
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        return null;
    }
    
    public List<EstimateCost> loadAllEstimateCost(){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT e FROM EstimateCost e  ");
                
                List<EstimateCost> esList = query.list();
                
                return esList;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        
        
        return null;
    }
    
}

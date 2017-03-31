/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;


import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.NominalCode;
import com.vertec.hibe.model.State;
import com.vertec.util.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Rohan Madusanka @Contact 071 - 9085504 @E-mail
 * rohanmadusanka72@gmail.com
 */
public class AccountReportDAOImpl {
    public List<State> getListOfState() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM State c WHERE c.isvalid=:isValid");
                query.setParameter("isValid", true);
                List<State> csList = query.list();
                
                return csList;

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
    public List<FunctionData> getListOfFunctionData() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM FunctionData c WHERE c.isvalid=:isValid");
                query.setParameter("isValid", true);
                List<FunctionData> csList = query.list();
                
                return csList;

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
    public List<CostCenter> getListOfCostCenter() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM CostCenter c WHERE c.isvalid=:isValid");
                query.setParameter("isValid", true);
                List<CostCenter> csList = query.list();
                
                return csList;

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
    public List<NominalCode> getListOfNominalCode() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM NominalCode c WHERE c.isvalid=:isValid");
                query.setParameter("isValid", true);
                List<NominalCode> csList = query.list();
                
                return csList;

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.hibe.model.PackageHasFeatures;
import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectExpenses;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class ExpensesDAOImpl {
    
    public String addExpenses(ProjectExpenses expenses){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
        
                try {
                    session.save(expenses);
                session.flush();

                transaction.commit();
                return VertecConstants.SUCCESS;
                
            } catch (Exception e) {
                e.printStackTrace();
                return VertecConstants.ERROR;
                
            }
            finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        
        return  null;
    }
    
    public ProjectDetails getProjectDetailsbyID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectDetails.findById");
                query.setParameter("id", id);
                ProjectDetails prList = (ProjectDetails) query.uniqueResult();
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
    
    
    public List<ProjectExpenses> loadAllExpenses(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT e FROM ProjectExpenses e WHERE e.projectDetailsId = :id");
                query.setParameter("id", getProjectDetailsbyID(id));
                List<ProjectExpenses> prList = query.list();
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
    public String updateProjectExpenses(ProjectExpenses  exp){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update project_expenses set description=:des,amount=:amt where id=:eId");

                query.setParameter("eId", exp.getId());
                query.setParameter("des", exp.getDescription());
                query.setParameter("amt", exp.getAmount());

                query.executeUpdate();

                transaction.commit();
                return VertecConstants.UPDATED;

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
            
   
    public ProjectExpenses viewExpenses(int expensesId){
        
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectExpenses.findById");
                query.setParameter("id", expensesId);

                ProjectExpenses user = (ProjectExpenses) query.uniqueResult();
                return user;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        
        return  null;
    }
    
}

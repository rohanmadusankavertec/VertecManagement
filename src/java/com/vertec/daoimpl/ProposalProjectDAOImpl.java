/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectProposal;
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
public class ProposalProjectDAOImpl {
    
    public List<ProjectProposal> loadAllProject(){
    
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectProposal.findAll");

                List<ProjectProposal> prList = query.list();
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
     public List<ProjectProposal> loadUpdateProject(){
    
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM ProjectProposal p where p.id IN (SELECT pd.projectProposalId.id FROM ProjectDetails pd)");

                List<ProjectProposal> prList = query.list();
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
     public List<ProjectProposal> loadProjectHasNotDetails(){
    
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM ProjectProposal p where p.id NOT IN (SELECT pd.projectProposalId.id FROM ProjectDetails pd)");

                List<ProjectProposal> prList = query.list();
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
     public List<ProjectDetails> loadAllProjectDetails(){
    
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM ProjectDetails p");

                List<ProjectDetails> prList = query.list();
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
    public String saveProjectProposal(ProjectProposal pp){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
        
                try {
                    session.save(pp);
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
    
    
}

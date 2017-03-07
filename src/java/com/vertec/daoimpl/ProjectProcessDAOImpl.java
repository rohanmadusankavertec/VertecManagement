/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectProcess;
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
public class ProjectProcessDAOImpl {
    
    public String saveProjectProcess(ProjectProcess projectProcess)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(projectProcess);
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
    
    
    public List<ProjectProcess> loadAllProcessByProjectName(int id){
        
        
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
//                Query query = session.createQuery("SELECT p FROM PackageHasFeatures p WHERE p.packageId = :id");
//                query.setParameter("id", getPackagebyID(packageID));
//                List<PackageHasFeatures> prList = query.list();
//                return prList;
                
                
                
//                Query query = session.getNamedQuery("ProjectProcess.findAll");

                Query query = session.createQuery("SELECT p FROM ProjectProcess p WHERE p.projectDetailsId = :id");
                query.setParameter("id",getProjectDetailsbyID(id));
                List<ProjectProcess> prList = query.list();
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
    
    public String DeleteProcessById(int id){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
            try {
                SQLQuery query = session.createSQLQuery("DELETE FROM project_process WHERE id =:pId ");

                query.setParameter("pId", id);
                

                query.executeUpdate();

                transaction.commit();
                return VertecConstants.UPDATED;
                
            } catch (Exception e) {
                e.printStackTrace();
                return VertecConstants.ERROR;
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        return null;
    }
}

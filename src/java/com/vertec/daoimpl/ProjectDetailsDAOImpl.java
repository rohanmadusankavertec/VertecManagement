/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectHasEmployee;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author User
 */
public class ProjectDetailsDAOImpl {
    
    public String saveProjectDetails(ProjectDetails projectdetails){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(projectdetails);
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
        
        return  null;
    }
    
    public String saveProjectHasEmployee(ProjectHasEmployee phe){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(phe);
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
        
        return  null;
    }
    
    
    public List<ProjectDetails> loadAllProjectDetails(){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectDetails.findAll");

                List<ProjectDetails> pdList = query.list();
                return pdList;

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
    //----------------------------------------------------------------------
    public ProjectProposal getProjectProposalbyID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectProposal.findById");
                query.setParameter("id", id);
                ProjectProposal prList = (ProjectProposal) query.uniqueResult();
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
    //----------------------------------------------------------------------
    
    public ProjectDetails viewProjectDetails(int id){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT e FROM ProjectDetails e WHERE e.projectProposalId.id = :id");
                query.setParameter("id", id);
                ProjectDetails user = (ProjectDetails) query.uniqueResult();
                
                return user;

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
    
    public List<ProjectHasEmployee> viewEmployeeOfProject(int id){
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT e FROM ProjectHasEmployee e WHERE e.projectDetailsId.projectProposalId.id = :id");
                query.setParameter("id", id);
                List<ProjectHasEmployee> pdList = (List<ProjectHasEmployee>) query.list();
                
                return pdList;

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


    
    public String updateProjectDetails(ProjectDetails  pd){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update project_details set from_date=:fdate,to_date=:tdate,status_id=:staId,remark=:remk where project_proposal_id=:ppId");

                query.setParameter("fdate", pd.getFromDate());
                query.setParameter("tdate", pd.getToDate());
                query.setParameter("remk", pd.getRemark());
                query.setParameter("staId", pd.getStatusId().getId());
                query.setParameter("ppId", pd.getProjectProposalId().getId());
//                query.setParameter("addBy", pd.getAddedBy());

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
    
    public JSONObject loadEmployee(int id) {
        System.out.println(id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM ProjectHasEmployee p WHERE p.projectDetailsId.projectProposalId.id=:id");
                query.setParameter("id", id);
                List<ProjectHasEmployee> prList = query.list();
                
                JSONObject jOB = new JSONObject();
                JSONArray jar1 = new JSONArray();
                JSONObject job1 = null;
                for (ProjectHasEmployee e : prList) {
                    
                    job1 = new JSONObject();
                    job1.put("id", e.getEmployeeId().getId());
                    job1.put("fdate", e.getFromDate().toString());
                    job1.put("todate", e.getToDate().toString());
                    job1.put("firstName", e.getEmployeeId().getFirstName());
                    job1.put("lastName", e.getEmployeeId().getLastName());
                    job1.put("eID", e.getEmployeeId().getId());
                    jar1.add(job1);
                }
                jOB.put("employee", jar1);
                
                return jOB;

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
    public String DeleteEmployeeByDetailsId(int id){
        int detailsId = viewPdIdByProposalId(id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String result = "done";
        if(session != null){
            try {
                SQLQuery query = session.createSQLQuery("DELETE FROM project_has_employee WHERE project_details_id =:pId ");

                query.setParameter("pId", detailsId);
                

                query.executeUpdate();

                transaction.commit();
                return result;
                
            } catch (Exception e) {
                e.printStackTrace();
                
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        return null;
    }
    public int viewPdIdByProposalId(int id){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT e.id FROM ProjectDetails e WHERE e.projectProposalId.id = :id");
                query.setParameter("id", id);
                int user =(Integer)  query.uniqueResult();
                
                return user;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        
        
        return 0;
    }
    
}

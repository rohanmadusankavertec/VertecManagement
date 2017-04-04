/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.BudgetPlan;
import com.vertec.hibe.model.UpdateLog;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class BudgetDAOImpl {

    public List<BudgetPlan> getListOfBudgetPlan() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM BudgetPlan c");
                List<BudgetPlan> csList = query.list();

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

    public BudgetPlan getBudgetPlanById(int ncid,String year, String month) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM BudgetPlan c WHERE c.nominalCodeId.id=:ncid AND c.year=:year AND c.month=:month");
                query.setParameter("ncid", ncid);
                query.setParameter("year", year);
                query.setParameter("month", month);
                BudgetPlan csList =(BudgetPlan) query.uniqueResult();

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
    public BudgetPlan getBudgetPlanById2(int bpid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM BudgetPlan c WHERE c.id=:bpid");
                query.setParameter("bpid", bpid);
                BudgetPlan csList =(BudgetPlan) query.uniqueResult();

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
     public List<UpdateLog> getUpdateLog(BudgetPlan bpid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM UpdateLog c WHERE c.budgetPlanId=:bpid");
                query.setParameter("bpid", bpid);
                List<UpdateLog> csList =(List<UpdateLog>) query.list();

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
    public List<BudgetPlan> getListOfYearlyBudgetPlan(int ncid, String year) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM BudgetPlan c WHERE c.nominalCodeId.id=:ncid AND c.year=:year");
                query.setParameter("ncid", ncid);
                query.setParameter("year", year);
                List<BudgetPlan> csList = query.list();
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

    public boolean isUpdated(BudgetPlan bp) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT b FROM UpdateLog b WHERE b.budgetPlanId=:bp ");
                query.setParameter("bp", bp);

                List<BudgetPlan> bp1 = (List<BudgetPlan>) query.list();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+bp1);
                if (bp1 == null || bp1.isEmpty()) {
                    return false;
                } else {
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        return true;
    }

    
    public String saveBudgetPlan(BudgetPlan bp) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
        
                try {
                    session.save(bp);
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
        
        return null;
        
        
    }
    public String saveUpdateLog(UpdateLog ul) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
        
                try {
                    session.save(ul);
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
        
        return null;
        
        
    }
    
    public String updateBudget(BudgetPlan bp) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update budget_plan set amount=:amount,date=:date,sys_user_id=:user where nominal_code_id=:ncid and year=:year and month=:month");
                System.out.println("GOT QUERY....");
                query.setParameter("amount", bp.getAmount());
                query.setParameter("date", new Date());
                query.setParameter("user", bp.getSysUserId());
                query.setParameter("ncid", bp.getNominalCodeId());
                query.setParameter("year", bp.getYear());
                query.setParameter("month", bp.getMonth());

                query.executeUpdate();

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
}

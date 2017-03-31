/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;


import com.vertec.hibe.model.ActualCost;
import com.vertec.hibe.model.BudgetPlan;
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
    
    
    public State getStateById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("State.findById");
                query.setParameter("id", id);
                State csList = (State)query.uniqueResult();
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
    public FunctionData getFunctionById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("FunctionData.findById");
                query.setParameter("id", id);
                FunctionData csList = (FunctionData)query.uniqueResult();
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
    public CostCenter getCostCenterById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("CostCenter.findById");
                query.setParameter("id", id);
                CostCenter csList = (CostCenter)query.uniqueResult();
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
    public NominalCode getNominalCodeById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("NominalCode.findById");
                query.setParameter("id", id);
                NominalCode csList = (NominalCode)query.uniqueResult();
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
    public List<String> getListOfBudgetYears() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT b.year FROM BudgetPlan b GROUP BY b.year");
                List<String> csList = query.list();
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
    public List<FunctionData> getListOfFunctionDataBystate(int id) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM FunctionData c WHERE c.isvalid=:isValid AND c.stateId.id=:id");
                query.setParameter("isValid", true);
                query.setParameter("id", id);
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
    public List<CostCenter> getListOfCostCenterByFunctionData(int id) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM CostCenter c WHERE c.isvalid=:isValid AND c.functionId.id=:id");
                query.setParameter("isValid", true);
                query.setParameter("id", id);
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
    public List<NominalCode> getListOfNominalbyCostCenter(int id) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM NominalCode c WHERE c.isvalid=:isValid AND c.costCenterId.id=:id");
                query.setParameter("isValid", true);
                query.setParameter("id", id);
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
    public List<BudgetPlan> getListOfYearlyBudgetPlans(int ncid,String year) {

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
    public List<ActualCost> getListOfYearlyActualCost(int ncid,String year) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM ActualCost c WHERE c.nominalCodeId.id=:ncid AND c.year=:year");
                
                query.setParameter("ncid", ncid);
                query.setParameter("year", year);
                List<ActualCost> csList = query.list();
                
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;


import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.State;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ruchira
 */
public class CostCenterDAOImpl {
    public String saveCostCenter(CostCenter cc) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(cc);
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
    
    public List<CostCenter> loadAllCostCenter() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT s FROM CostCenter s WHERE s.isvalid = :isvalid");
                query.setParameter("isvalid", true);
                List<CostCenter> prList = query.list();
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
    public List<FunctionData> getFunctionDataBySateId(int sid) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT f FROM FunctionData f WHERE f.isvalid = :isvalid AND f.stateId.id=:sid");
                query.setParameter("isvalid", true);
                query.setParameter("sid", sid);
                List<FunctionData> prList = query.list();
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
    
    public String updateCostCenter(CostCenter c) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

//                Query query = session.createSQLQuery("Update customer set first_name=:firstname,last_name=:lastname,company_name=:companyName,mobile_no=:mobileNo,offiice_no=:officeNo,address=:address,fax=:fax,email=:email where id=:cusId");
                Query query = session.createQuery("UPDATE CostCenter s SET s.name=:name,s.code=:code WHERE s.id=:sid");
//                Query query = session.createQuery("UPDATE CctvItems c SET c.name=:name,c.price=:price,c.cctvCategoryId=:cate WHERE c.id=:id");
                System.out.println("GOT QUERY....");
                query.setParameter("name", c.getName() );
                query.setParameter("code", c.getCode() );
                query.setParameter("sid", c.getId());

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
    
    public CostCenter viewCostCenterById(int ccid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("CostCenter.findById");
                query.setParameter("id", ccid);

                CostCenter cc = (CostCenter) query.uniqueResult();
                return cc;

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
    public String deleteCostCenter(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE CostCenter s SET s.isvalid=:valid WHERE s.id=:sid");
//                System.out.println("GOT QUERY....");
                query.setParameter("valid", false );
                query.setParameter("sid", id);

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

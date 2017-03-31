/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;


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
public class StateDAOImpl {
    public String saveState(State s) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(s);
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
    
    public List<State> loadAllState() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT s FROM State s WHERE s.isvalid = :isvalid");
                query.setParameter("isvalid", true);
                List<State> prList = query.list();
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
    
    public String updateState(State s) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

//                Query query = session.createSQLQuery("Update customer set first_name=:firstname,last_name=:lastname,company_name=:companyName,mobile_no=:mobileNo,offiice_no=:officeNo,address=:address,fax=:fax,email=:email where id=:cusId");
                Query query = session.createQuery("UPDATE State s SET s.name=:name WHERE s.id=:sid");
//                Query query = session.createQuery("UPDATE CctvItems c SET c.name=:name,c.price=:price,c.cctvCategoryId=:cate WHERE c.id=:id");
                System.out.println("GOT QUERY....");
                query.setParameter("name", s.getName() );
                query.setParameter("sid", s.getId());

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
    
    public State viewStateById(int sid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("State.findById");
                query.setParameter("id", sid);

                State state = (State) query.uniqueResult();
                return state;

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
    public String deleteState(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE State s SET s.isvalid=:valid WHERE s.id=:sid");
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

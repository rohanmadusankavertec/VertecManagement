/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.Agreement;
import com.vertec.hibe.model.CctvCategory;
import com.vertec.hibe.model.CctvItems;
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
public class CctvDAOImpl {
    
    public String saveCCTVItem(CctvItems c) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(c);
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
    
    public List<CctvCategory> loadItemCategory() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("CctvCategory.findAll");

                List<CctvCategory> prList = query.list();
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
    
    public List<CctvItems> loadAllItems() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("CctvItems.findAll");

                List<CctvItems> prList = query.list();
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
    
    public String updateItem(CctvItems c) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

//                Query query = session.createSQLQuery("Update customer set first_name=:firstname,last_name=:lastname,company_name=:companyName,mobile_no=:mobileNo,offiice_no=:officeNo,address=:address,fax=:fax,email=:email where id=:cusId");
                Query query = session.createQuery("UPDATE CctvItems c SET c.name=:name,c.price=:price,c.cctvCategoryId=:cate WHERE c.id=:id");
                System.out.println("GOT QUERY....");
                query.setParameter("name", c.getName() );
                query.setParameter("price", c.getPrice() );
                query.setParameter("id", c.getId());
                query.setParameter("cate", c.getCctvCategoryId());
                

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
    public CctvItems viewItemById(int cid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("CctvItems.findById");
                query.setParameter("id", cid);

                CctvItems user = (CctvItems) query.uniqueResult();
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
    public List<CctvItems> viewItemByCategoryId(int cid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM CctvItems c WHERE c.cctvCategoryId.id=:id");
                query.setParameter("id", cid);

                List<CctvItems> items = (List<CctvItems>) query.list();
                return items;

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

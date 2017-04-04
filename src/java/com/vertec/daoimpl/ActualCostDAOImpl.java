/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.ActualCost;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
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
public class ActualCostDAOImpl {
    
    public List<ActualCost> getActualCostList(String year,String month,int noId) {
        System.out.println("////////////////");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT a FROM ActualCost a WHERE a.year = :year AND a.nominalCodeId.id =:nominalId AND a.month=:month");
                query.setParameter("year", year);
                query.setParameter("nominalId", noId);
                query.setParameter("month", month);
                List<ActualCost> prList = query.list();
                for (ActualCost a : prList) {
                    System.out.println("..data get from db..."+a.getAmount());
                }
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
    
    public String saveActualCost(ActualCost ac) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(ac);
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
    
    public String updateActualCost(int id,double amt,String des,String ref) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE ActualCost a SET a.amount=:amt,a.description=:des,a.referenceNo=:ref WHERE a.id =:aid");
//                System.out.println("GOT QUERY....");
                query.setParameter("amt", amt );
                query.setParameter("des", des );
                query.setParameter("ref", ref );
                query.setParameter("aid", id);

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

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
//    public List<CostCenter> getCostCenterByFunctionId(int fid) {
//        Session session = NewHibernateUtil.getSessionFactory().openSession();
//        if (session != null) {
//            try {
//                Query query = session.createQuery("SELECT c FROM CostCenter c WHERE c.isvalid = :isvalid AND c.functionId.id=:fid");
//                query.setParameter("isvalid", true);
//                query.setParameter("fid", fid);
//                List<CostCenter> prList = query.list();
//                return prList;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (session != null && session.isOpen()) {
//                    session.close();
//                }
//            }
//        }
//        return null;
//    }
    
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.NominalCode;
import com.vertec.hibe.model.Service;
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
public class NominalCodeDAOImpl {

    public String saveNominalCode(NominalCode nc) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {

            try {
                session.save(nc);
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


    public String updateNominalCode(NominalCode nc) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                SQLQuery query = session.createSQLQuery("Update nominal_code set name=:name,cost_center_id=:ccId,code=:code where id=:Id");
                query.setParameter("name", nc.getName());
                query.setParameter("ccId", nc.getCostCenterId());
                query.setParameter("Id", nc.getId());
                query.setParameter("code", nc.getCode());
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

    
    public String removeNominalCode(int funcId) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update nominal_code set isvalid=:is_valid where id=:ncId");
                query.setParameter("ncId", funcId);
                query.setParameter("is_valid", false);
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

}

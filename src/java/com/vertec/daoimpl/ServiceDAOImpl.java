/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

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
public class ServiceDAOImpl {

    public String saveService(Service service) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {

            try {
                session.save(service);
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

    public List<Service> getListOfService() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM Service c WHERE c.isValid=:isValid");
                query.setParameter("isValid", true);
                List<Service> csList = query.list();

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

    public Service viewService(int customerId) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Service.findById");
                query.setParameter("id", customerId);

                Service user = (Service) query.uniqueResult();
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

    public String updateService(Service service) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                SQLQuery query = session.createSQLQuery("Update service set service_name=:name where id=:Id");
                query.setParameter("name", service.getServiceName());
                query.setParameter("Id", service.getId());
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

    
    public String removeService(int serviceId) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update service set is_valid=:is_valid where id=:serId");

                query.setParameter("serId", serviceId);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.Issues;
import com.vertec.hibe.model.Service;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Imansa
 */
public class IssuesDAO {

    public static boolean addIssue(Issues issues) {
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            session.save(issues);
            session.flush();
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Issues> getListOfIssues() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT i FROM Issues i where i.isFixed=:isFixed");
                query.setParameter("isFixed", false);

                List<Issues> issues = query.list();

                return issues;

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

    public String setComplete(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                Query query = session.createQuery("Update Issues i set i.isFixed=:isFixed where i.id=:id");
                System.out.println("GOT QUERY....");
                query.setParameter("isFixed", true);
                query.setParameter("id", id);
                System.out.println(query.getQueryString());
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
        return VertecConstants.ERROR;
    }

    public boolean updateIssue(Issues issue) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                Query query = session.createQuery("Update Issues i set i.issue=:issue where i.id=:id");
                System.out.println("GOT QUERY....");
                query.setParameter("issue", issue.getIssue());
                query.setParameter("id", issue.getId());
                System.out.println(query.getQueryString());
                query.executeUpdate();

                transaction.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        return false;
    }
    
    public Issues getSelectedIssue(int id){
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Issues.findById");
                query.setParameter("id", id);

                Issues issue = (Issues) query.uniqueResult();
                return issue;

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

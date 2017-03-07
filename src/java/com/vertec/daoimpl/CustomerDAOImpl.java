/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.dao.CustomerDAO;
import com.vertec.hibe.model.Customer;
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
public class CustomerDAOImpl implements CustomerDAO{

  

    @Override
    public String saveCustomer(Customer customer) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
        
                try {
                    session.save(customer);
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

    @Override
    public List<Customer> getListOfCustomer() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM Customer c WHERE c.isValid=:isValid");
                query.setParameter("isValid", true);
                List<Customer> csList = query.list();
                
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

    @Override
    public Customer viewCustomer(int customerId) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Customer.findById");
                query.setParameter("id", customerId);

                Customer user = (Customer) query.uniqueResult();
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

    @Override
    public String updateCustomer(Customer customer) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update customer set first_name=:firstname,last_name=:lastname,company_name=:companyName,mobile_no=:mobileNo,offiice_no=:officeNo,address=:address,fax=:fax,email=:email where id=:cusId");
                System.out.println("GOT QUERY....");
                query.setParameter("firstname", customer.getFirstName() );
                query.setParameter("lastname", customer.getLastName() );
                query.setParameter("companyName", customer.getCompanyName());
                query.setParameter("mobileNo", customer.getMobileNo());
                query.setParameter("officeNo", customer.getOffiiceNo());
                query.setParameter("address", customer.getAddress());
                query.setParameter("fax", customer.getFax());
                query.setParameter("email", customer.getEmail());
                query.setParameter("cusId", customer.getId());

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

    @Override
    public String removeCustomer(int customerId) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update customer set is_valid=:is_valid where id=:cuId");

                query.setParameter("cuId", customerId);
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

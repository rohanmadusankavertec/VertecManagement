/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.daoimpl;

import com.vertec.dao.EmployeeDAO;
import com.vertec.hibe.model.Employee;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import com.vertec.daoimpl.EmployeeImpl;

/**
 *
 * @author User
 */
public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public String saveEmployee(Employee employee) {
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if(session != null){
        
                try {
                    session.save(employee);
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
    public List<Employee> getListOfEmployee() {
       
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT e FROM Employee e WHERE e.isValid=:isValid");
                query.setParameter("isValid", true);
                List<Employee> emList = query.list();
                
                return emList;

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
    public Employee viewEmployee(int employeeId) {
   
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Employee.findById");
                query.setParameter("id", employeeId);

                Employee user = (Employee) query.uniqueResult();
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
    public String updateEmployee(Employee employee) {
       
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update employee set first_name=:firstname,last_name=:lastname,salary=:salary,nic=:nic where id=:empId");
                System.out.println("GOT QUERY....");
                query.setParameter("firstname", employee.getFirstName() );
                query.setParameter("lastname", employee.getLastName() );
                query.setParameter("salary", employee.getSalary());
                query.setParameter("nic", employee.getNic());
                query.setParameter("empId", employee.getId() );

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
    public String removeEmployee(int empId) {
    
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

                SQLQuery query = session.createSQLQuery("Update employee set is_valid=:is_valid where id=:employeeId");

                query.setParameter("employeeId", empId);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.Agreement;
import com.vertec.hibe.model.AgreementTemplate;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Installment;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Quotation;
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
 * @author vertec-r
 */
public class AgreementDAOImpl {

    public List<Agreement> loadAllAgreement() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("Agreement.findAll");

                List<Agreement> prList = query.list();
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

    public List<AgreementTemplate> loadAllAgreementTemplate() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("AgreementTemplate.findAll");

                List<AgreementTemplate> prList = query.list();
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

    public List<Customer> loadAllCustomer() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("Customer.findAll");
                List<Customer> prList = query.list();
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

    public List<ProjectProposal> loadAllProjectProposal() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectProposal.findAll");

                List<ProjectProposal> prList = query.list();
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

    public List<Quotation> loadAllQuotation() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("ProjectProposal.findAll");

                List<Quotation> prList = query.list();
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

    public List<Service> loadAllServises() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT s FROM Service s WHERE s.isValid = :isValid");
                query.setParameter("isValid", true);
                List<Service> prList = query.list();
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

    public String saveAgreement(Agreement agreement) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(agreement);
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

    public Agreement viewAgreement(int agId) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Agreement.findById");
                query.setParameter("id", agId);

                Agreement user = (Agreement) query.uniqueResult();
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
    public List<Installment> viewInstallmentByQuotationID(int id) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT i FROM Installment i WHERE i.quotationId.id=:id");
                query.setParameter("id", id);
                query.setMaxResults(3);
                query.setFirstResult(0);
                List<Installment> user = query.list();
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

    public String deleteAgreementTemplate(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("DELETE FROM AgreementTemplate a WHERE a.id=:id");
                query.setParameter("id", id);
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

    
    public String updateAgreement(Agreement agree) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {

//                SQLQuery query = session.createSQLQuery("Update customer set first_name=:firstname,last_name=:lastname,company_name=:companyName,mobile_no=:mobileNo,offiice_no=:officeNo,address=:address,fax=:fax,email=:email where id=:cusId");
                Query query = session.createQuery("Update Agreement a set  a.fromDate=:fromDate,a.toDate=:toDate,a.requirement=:requi,a.amount=:amt,a.signedDate=:sDate where a.id=:agId");
                
                
                query.setParameter("fromDate", agree.getFromDate());
                query.setParameter("toDate", agree.getToDate() );
                query.setParameter("requi", agree.getRequirement());
                query.setParameter("amt", agree.getAmount());
//                query.setParameter("createdDate", agree.getCreatedDate());
                query.setParameter("sDate", agree.getSignedDate());
//                query.setParameter("proposalId", agree.getProjectProposalId().getId());
//                query.setParameter("quot", agree.getQuotationId().getId());
                query.setParameter("agId", agree.getId());

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
    
    
    
    
    
//    public String updateAgreement(Agreement agree) {
//
//        Session session = NewHibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//        if (session != null) {
//            try {
//
////                SQLQuery query = session.createSQLQuery("Update customer set first_name=:firstname,last_name=:lastname,company_name=:companyName,mobile_no=:mobileNo,offiice_no=:officeNo,address=:address,fax=:fax,email=:email where id=:cusId");
//                Query query = session.createQuery("Update Agreement set FromDate=:fromDate,ToDate=:toDate,Requirement=:requi,Amount=:amt,CreatedDate=:createdDate,SignedDate=:sDate,ProjectProposalId.id=:proposalId,QuotationId.id=:quot where id=:agId");
//
//                query.setParameter("fromDate", agree.getFromDate());
//                query.setParameter("toDate", agree.getToDate());
//                query.setParameter("requi", agree.getRequirement());
//                query.setParameter("amt", agree.getAmount());
//                query.setParameter("createdDate", agree.getCreatedDate());
//                query.setParameter("sDate", agree.getSignedDate());
//                query.setParameter("proposalId", agree.getProjectProposalId().getId());
//                query.setParameter("quot", agree.getQuotationId().getId());
//                query.setParameter("agId", agree.getId());
//
//                query.executeUpdate();
//
//                transaction.commit();
//                return VertecConstants.UPDATED;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return VertecConstants.ERROR;
//            } finally {
//                if (session != null && session.isOpen()) {
//                    session.close();
//                }
//            }
//        }
//
//        return null;
//    }

}

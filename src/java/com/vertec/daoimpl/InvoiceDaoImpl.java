/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Installment;
import com.vertec.hibe.model.Invoice;
import com.vertec.hibe.model.InvoiceItem;
import com.vertec.hibe.model.Payment;
import com.vertec.hibe.model.PaymentDetails;
import com.vertec.hibe.model.PaymentType;
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
public class InvoiceDaoImpl {

    public List<Object[]> getListOfInstallments(String project) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createSQLQuery("SELECT i.id,i.description,i.amount FROM quotation q INNER JOIN installment i ON i.quotation_id=q.id WHERE i.outstanding>0 AND q.project_proposal_id='" + project + "'");

                List<Object[]> csList = query.list();

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

    public List<Invoice> getListOutInvoices() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT i FROM Invoice i WHERE i.outstanding>0");

                List<Invoice> csList = query.list();

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

    public List<PaymentType> getPaymentTypes() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT i FROM PaymentType i");

                List<PaymentType> csList = query.list();

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

    public Customer getCustomerByInstallemnt(int iid) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT c FROM Installment c WHERE c.id=:cid");
                query.setParameter("cid", iid);
                Installment csList = (Installment) query.uniqueResult();

                return csList.getQuotationId().getProjectProposalId().getCustomerId();

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

    public String loadItems(int invoice) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM InvoiceItem p WHERE p.invoiceId=:invoice");
                query.setParameter("invoice", getInvoicebyID(invoice));
                List<InvoiceItem> prList = query.list();
                String html = "";
                for (InvoiceItem ii : prList) {
                    html += "<tr><td>" + ii.getDescription() + "</td><td>" + ii.getQty() + "</td><td>" + ii.getPrice() + "</td><td>" + ii.getTotal() + "</td></tr>";
                }
                return html;

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

    public String UpdateInvoiceOutstanding(int invoice, double payment) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE Invoice i SET i.outstanding=i.outstanding-:payment WHERE i.id=:invoice");
                query.setParameter("invoice", invoice);
                query.setParameter("payment", payment);
                query.executeUpdate();
                tr.commit();
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

    public String UpdateClearCheque(int pdid) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE PaymentDetails p SET p.isValid=:isValid WHERE p.id=:id");
                query.setParameter("id", pdid);
                query.setParameter("isValid", true);
                query.executeUpdate();
                tr.commit();
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

    public String UpdateInvoiceOutstanding(int pdid) {
        Session session2 = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session2.beginTransaction();
        if (session2 != null) {
            try {
                Query query2 = session2.createSQLQuery("SELECT p.amount,i.id FROM invoice i INNER JOIN payment p ON p.invoice_id=i.id WHERE p.payment_details_id='" + pdid + "'");
//                Query query = session.createQuery("SELECT in FROM Invoice in WHERE in.paymentCollection.paymentDetailsId.id=:pdid");
//                query.setParameter("pdid", pdid);
                List<Object[]> prList = query2.list();
                for (Object[] ii : prList) {
                    System.out.println(ii[0] + "_________________" + ii[1]);
                    
                    
                    
                    
                    
                    Query query = session2.createQuery("UPDATE Invoice i SET i.outstanding=i.outstanding-:amount WHERE i.id=:id");
                query.setParameter("id", Integer.parseInt(ii[1].toString()));
                query.setParameter("amount", Double.parseDouble(ii[0].toString()));
                query.executeUpdate();
                
                
                    
                    
                    
                    
                    
                }
                tr.commit();
                return VertecConstants.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return VertecConstants.ERROR;
            } finally {
                if (session2 != null && session2.isOpen()) {
                    session2.close();
                }
            }
        }
        return null;
    }

    
      public String UpdateInvoiceOutstanding2(int pdid) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                System.out.println(">>>>>>>>>>>>>>>>>>>>");
                System.out.println(pdid);
                Query query = session.createQuery("UPDATE Payment p SET p.paymentDetailsId.isValid WHERE p.paymentDetailsId.id=:id");
                query.setParameter("id", pdid);
                query.setParameter("isValid", true);
                query.executeUpdate();
                tr.commit();
                return VertecConstants.SUCCESS;

            } catch (Exception e) {
                e.printStackTrace();
                return VertecConstants.ERROR;
            } finally {
                session.close();
                
            }
        }
        return null;
    }
    
    
    
    
    public String UpdateReturnCheque(int pdid) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE PaymentDetails p SET p.isValid=:isValid WHERE p.id=:id");
                query.setParameter("id", pdid);
                query.setParameter("isValid", false);
                query.executeUpdate();
                tr.commit();
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

    public Invoice getInvoicebyID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Invoice.findById");
                query.setParameter("id", id);
                Invoice prList = (Invoice) query.uniqueResult();
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
    
    public List<Payment> getPayments() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Payment.findAll");
                List<Payment> prList = (List<Payment>) query.list();
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
    public Invoice getInvoiceById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("Invoice.findById");
                query.setParameter("id", id);
                Invoice prList = (Invoice) query.uniqueResult();
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
    
    
     public Payment getPaymentById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("Payment.findById");
                query.setParameter("id", id);
                Payment prList = (Payment) query.uniqueResult();
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
    
    
    
    public List<InvoiceItem> getInvoiceItemById(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT ii FROM InvoiceItem ii WHERE ii.invoiceId.id=:id ");
                query.setParameter("id", id);
                List<InvoiceItem> prList = (List<InvoiceItem>) query.list();
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
    
    
    
    
    
    

    public String saveInvoice(Invoice invoice) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(invoice);
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

    public String savePaymentDetails(PaymentDetails pd) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(pd);
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

    public String savePayment(Payment pay) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(pay);
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

    public String saveInvoiceItem(InvoiceItem invoiceitem) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(invoiceitem);
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

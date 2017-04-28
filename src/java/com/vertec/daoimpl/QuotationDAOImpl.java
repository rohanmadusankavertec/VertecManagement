/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.CctvQuotationInfo;
import com.vertec.hibe.model.CctvQuotationItems;
import com.vertec.hibe.model.CctvWarranty;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Features;
import com.vertec.hibe.model.HardwareItem;
import com.vertec.hibe.model.HardwareQuotation;
import com.vertec.hibe.model.Installment;
import com.vertec.hibe.model.Invoice;
import com.vertec.hibe.model.PackageHasFeatures;
import com.vertec.hibe.model.PaymentDetails;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Quotation;
import com.vertec.hibe.model.QuotationHasFeatures;
import com.vertec.hibe.model.QuotationHasPackages;
import com.vertec.hibe.model.Service;
import com.vertec.hibe.model.SoftwareQuotation;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vertec-r
 */
public class QuotationDAOImpl {

    public List<Features> loadAllFeatures() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Features.findAll");

                List<Features> prList = query.list();
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
    public List<com.vertec.hibe.model.Package> loadAllPackages() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Package.findAll");

                List<com.vertec.hibe.model.Package> prList = query.list();
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
                Query query = session.getNamedQuery("Quotation.findAll");

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
    public List<Quotation> loadAllQuotationByServiceId(int sid) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT q FROM Quotation q WHERE q.projectProposalId.serviceId.id=:id");
                query.setParameter("id", sid);

                List<Quotation> prList = query.list();
                for (Quotation q : prList) {
                    System.out.println(".......quotation id.."+q.getId());
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
    public List<Quotation> loadAllQuotationByProjectID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT q FROM Quotation q WHERE q.projectProposalId.id=:id");
                query.setParameter("id", id);
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
    
    
    public List<com.vertec.hibe.model.Package> loadPackagesForProject(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM Package p WHERE p.serviceId.id IN (SELECT pr.serviceId.id FROM ProjectProposal pr WHERE pr.id=:id) ");
                query.setParameter("id", id);
                List<com.vertec.hibe.model.Package> prList = query.list();
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
    
    
    
    
    
    public List<PackageHasFeatures> loadPackageFeatures(int packageID) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM PackageHasFeatures p WHERE p.packageId = :id");
                query.setParameter("id", getPackagebyID(packageID));
                List<PackageHasFeatures> prList = query.list();
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
    
//    public String DeletePackageFeatures(int packageID) {
//        Session session = NewHibernateUtil.getSessionFactory().openSession();
//         
//        if (session != null) {
//            try {
//                Query query = session.createQuery("DELETE FROM PackageHasFeatures p WHERE p.id =:id");
//                query.setParameter("id", packageID);
//                query.executeUpdate();
//                System.out.println("//////////////////");
//                return VertecConstants.SUCCESS;
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
//        return null;
//    }
    
    public String DeletePackageFeatures(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("DELETE FROM PackageHasFeatures p WHERE p.id =:id");
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
    
    
     public List<ProjectProposal> loadProjectProposals() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM ProjectProposal p WHERE p.isValid = :valid");
                query.setParameter("valid", true);
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
     public List<ProjectProposal> loadProjectProposalsByServiceId(int sid) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM ProjectProposal p WHERE p.isValid = :valid AND p.serviceId.id=:sid");
                query.setParameter("valid", true);
                query.setParameter("sid", sid);
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
     public List<Invoice> loadinvoice() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM Invoice p ");
                List<Invoice> prList = query.list();
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
     
     public List<PaymentDetails> loadcheques() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM PaymentDetails p ");
                List<PaymentDetails> prList = query.list();
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
     
    public Service getServicebyID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Service.findById");
                query.setParameter("id", id);
                Service prList = (Service) query.uniqueResult();
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

    public com.vertec.hibe.model.Package getPackagebyID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.getNamedQuery("Package.findById");
                query.setParameter("id", id);
                com.vertec.hibe.model.Package prList = (com.vertec.hibe.model.Package) query.uniqueResult();
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

    public String saveFeature(Features feature) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(feature);
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

    public String savePackage(com.vertec.hibe.model.Package p) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(p);
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
    public String saveQuotation(Quotation q) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(q);
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

    public String savePackageHasFeature(PackageHasFeatures p) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(p);
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
public String saveQuotationHasPackage(QuotationHasPackages p) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(p);
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
public String saveQuotationHasFeature(QuotationHasFeatures p) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(p);
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


public String saveInstallments(Installment p) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(p);
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
public String saveSoftwareQuotation(SoftwareQuotation s) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session != null) {
            try {
                session.save(s);
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
    public List<com.vertec.hibe.model.Package> loadPackage(String type, int service) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                String hql = "";
                if (type.equals("0")) {
                    hql = "SELECT p FROM Package p WHERE p.isValid=:isValid ";
                } else {
                    hql = "SELECT p FROM Package p WHERE p.isValid=:isValid AND p.serviceId=:service";
                }
                Query query = session.createQuery(hql);
                query.setParameter("isValid", true);
                if (!type.equals("0")) {
                    query.setParameter("service", getServicebyID(service));
                }
                List<com.vertec.hibe.model.Package> prList = query.list();
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

    public String loadPackages(int pkg) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                Query query = session.createQuery("SELECT p FROM PackageHasFeatures p WHERE p.packageId=:package");
                query.setParameter("package", getPackagebyID(pkg));
                List<PackageHasFeatures> prList = query.list();
                String html = "";
                for (PackageHasFeatures phf : prList) {
                    html += "<tr><td>" + phf.getFeaturesId().getId() + "</td><td>" + phf.getFeaturesId().getFeature() + "</td></tr>";
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

    public String DeletePackage(com.vertec.hibe.model.Package pkg) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE Package p SET p.isValid=:isValid WHERE p.id=:id");
                query.setParameter("isValid", false);
                query.setParameter("id", pkg.getId());
                query.executeUpdate();
                tr.commit();
                return VertecConstants.SUCCESS;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }

        return VertecConstants.ERROR;
    }
    
    
    public String UpdatePackage(com.vertec.hibe.model.Package pkg) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.createQuery("UPDATE Package p SET p.name=:name,p.price=:price,p.serviceId=:serviceId WHERE p.id=:id");
                query.setParameter("name", pkg.getName());
                query.setParameter("price", pkg.getPrice());
                query.setParameter("serviceId", pkg.getServiceId());
                query.setParameter("id", pkg.getId());
                query.executeUpdate();
                tr.commit();
                return VertecConstants.SUCCESS;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }

        return VertecConstants.ERROR;
    }
    
    
    
    

    public com.vertec.hibe.model.Package LoadPackageByID(int pkg) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("Package.findById");
                query.setParameter("id", pkg);
                com.vertec.hibe.model.Package prList = (com.vertec.hibe.model.Package) query.uniqueResult();
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
    
    //----------------------------------print quotation -------------------------------------------
    
    public List<QuotationHasFeatures> getWebsite(int id){
//        System.out.println("wadaaaaaaaaaaaa"+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId.id=:phf");
                query.setParameter("phf", id);
//                System.out.println("hello");
                List<QuotationHasFeatures> prList = query.list();
//                System.out.println(prList);
//                for (QuotationHasFeatures q : prList) {
//                    System.out.println(q.getDescription());
//                }
                
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
    
    public List<Installment> getInstallment(int id){
//        System.out.println("........."+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT i FROM Installment i WHERE i.quotationId.id=:ins");
                query.setParameter("ins", id);
                
                List<Installment> prList = query.list();
                
                
                
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
    
    public Quotation LoadProjectByQuotation(int qid) {
//        System.out.println("qqqqqqqqqqqq"+qid);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                
                Query query = session.createQuery("SELECT q FROM Quotation q WHERE q.id=:qid");
                query.setParameter("qid", qid);
                Quotation prList = (Quotation) query.uniqueResult();
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
    public List<Service> getServices(){
//        System.out.println("........."+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT s FROM Service s");
                
                
                List<Service> seList = query.list();
                
                
                
                return seList;
                
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
    public List<Service> NewgetServices(){
//        System.out.println("........."+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT s FROM Service s WHERE s.isValid =:valid");
                query.setParameter("valid", true);
                
                List<Service> seList = query.list();
                
                
                
                return seList;
                
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
    
    public List<PackageHasFeatures> getfeatures(int id){
//        System.out.println("........."+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT f FROM PackageHasFeatures f WHERE f.packageId.serviceId.id=:sid");
                query.setParameter("sid", id);
                
                List<PackageHasFeatures> seList = query.list();
                
                
                
                return seList;
                
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
    public List<SoftwareQuotation> getSoftware(int id){
//        System.out.println("........."+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT s FROM SoftwareQuotation s WHERE s.quotationId.id=:qid");
                query.setParameter("qid", id);
                
                List<SoftwareQuotation> seList = query.list();
                
                
                
                return seList;
                
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
    public int getpackHasFeatureId(int pid,int fid) {
//        System.out.println("qqqqqqqqqqqq"+qid);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                
                Query query = session.createQuery("SELECT p.id FROM PackageHasFeatures p WHERE p.packageId.id=:pkid AND p.featuresId.id=:feaId");
                query.setParameter("pkid", pid);
                query.setParameter("feaId", fid);
                int id = (int) query.uniqueResult();
                return id;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
        return 0;
    }
    public List<QuotationHasPackages> getSoftwarepackageDetails(int id){
//        System.out.println("wadaaaaaaaaaaaa"+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT p FROM QuotationHasPackages p WHERE p.quotationId.id=:phf GROUP BY p.packageHasFeaturesId.packageId ");
                query.setParameter("phf", id);
//                System.out.println("hello");
                List<QuotationHasPackages> prList = query.list();
//                System.out.println(prList);
//                for (QuotationHasFeatures q : prList) {
//                    System.out.println(q.getDescription());
//                }
                
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
    public List<QuotationHasPackages> getSoftwarepackageDetails1(int id){
//        System.out.println("wadaaaaaaaaaaaa"+id);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                
//                Query query = session.createQuery("SELECT p FROM QuotationHasFeatures p WHERE p.quotationId=:phf");
                Query query = session.createQuery("SELECT p FROM QuotationHasPackages p WHERE p.quotationId.id=:phf ");
                query.setParameter("phf", id);
//                System.out.println("hello");
                List<QuotationHasPackages> prList = query.list();
//                System.out.println(prList);
//                for (QuotationHasFeatures q : prList) {
//                    System.out.println(q.getDescription());
//                }
                
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
//    public String getTotAmount(int pid) {
////        System.out.println("qqqqqqqqqqqq"+qid);
//        Session session = NewHibernateUtil.getSessionFactory().openSession();
//        Transaction tr = session.beginTransaction();
//        if (session != null) {
//            try {
//                
//                Query query = session.createQuery("SELECT SUM(s.price) FROM SoftwareQuotation s WHERE s.id =:pkid");
//                query.setParameter("pkid", pid);
//                System.out.println("................");
//                double amout = (double) query.uniqueResult();
//                System.out.println(amout);
//                System.out.println(amout+"llllllllllll");
//                return amout+"";
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
    public String getTotAmount(int pid) {
        System.out.println("qqqqqqqqqqqq"+pid);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                
                Query query = session.createQuery("SELECT SUM(s.price*s.qty) FROM SoftwareQuotation s WHERE s.quotationId.id =:pkid");
                query.setParameter("pkid", pid);
                System.out.println("................"+query.uniqueResult());
                double amout = (double) query.uniqueResult();
                System.out.println(amout);
                System.out.println(amout+"llllllllllll");
                return amout+"";
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
//   ----------------------------------Hardware--------------------------------------------
    public List<Customer> loadCustomer(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                Query query = session.createQuery("SELECT c FROM Customer c WHERE c.isValid=:valid");
                query.setParameter("valid", true);
                List<Customer> cuList =(List<Customer>) query.list();
                return cuList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return null;
    }
    
    
    public String saveHardwareItem(HardwareItem hi) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(hi);
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
    
    
    
    public String saveHardwareQuotation(HardwareQuotation hq) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(hq);
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
    public List<HardwareQuotation> loadHardwareQuotation(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                System.out.println("calling");
                Query query = session.createQuery("SELECT h FROM HardwareQuotation h ORDER BY h.id DESC");
                List<HardwareQuotation> cuList =(List<HardwareQuotation>) query.list();
                return cuList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return null;
    }
    public List<HardwareItem> loadHardwareItem(int id){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                Query query = session.createQuery("SELECT h  FROM HardwareItem h WHERE h.hardwareId.id=:hid");
                query.setParameter("hid", id);
                List<HardwareItem> cuList =(List<HardwareItem>) query.list();
                
                return cuList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return null;
    }
    
    public HardwareQuotation loadHardwareQuotation(int qid) {
//        System.out.println("qqqqqqqqqqqq"+qid);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                
                Query query = session.createQuery("SELECT h FROM HardwareQuotation h WHERE h.id=:qid");
                query.setParameter("qid", qid);
                HardwareQuotation prList = (HardwareQuotation) query.uniqueResult();
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
//--------------------CCTV-----------------------------------------------------------
    
    public String saveCCTV(CctvQuotationInfo cctv) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(cctv);
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
    public CctvWarranty loadCCTVWarrenty(int qid) {
//        System.out.println("qqqqqqqqqqqq"+qid);
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                
                Query query = session.createQuery("SELECT c FROM CctvWarranty c WHERE c.cctvQuotationInfoId.id =:qid");
                query.setParameter("qid", qid);
                CctvWarranty prList = (CctvWarranty) query.uniqueResult();
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
    
    public String saveCCTVquotation(CctvQuotationItems cctv) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(cctv);
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
    
    public List<CctvQuotationInfo> loadCctvQuotation(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                Query query = session.createQuery("SELECT c  FROM CctvQuotationInfo c ");
//                query.setParameter("hid", id);
                List<CctvQuotationInfo> ccList =(List<CctvQuotationInfo>) query.list();
                
                return ccList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return null;
    }
    
    public List<CctvQuotationItems> loadCctvQuotationItems(int id){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                Query query = session.createQuery("SELECT c  FROM CctvQuotationItems c WHERE c.cctvQuotationInfoId.id=:cid");
                query.setParameter("cid", id);
                List<CctvQuotationItems> cuList =(List<CctvQuotationItems>) query.list();
                
                return cuList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return null;
    }
    
    public CctvQuotationInfo viewCctvQuotation(int id){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                Query query = session.createQuery("SELECT c  FROM CctvQuotationInfo c WHERE c.id=:cid");
                query.setParameter("cid", id);
                CctvQuotationInfo cuList =(CctvQuotationInfo) query.uniqueResult();
                
                return cuList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return null;
    }
    
    public double getCctvItemPrice(int id){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(session !=null){
            try {
                Query query = session.createQuery("SELECT c  FROM CctvItems c WHERE c.id=:cid");
                query.setParameter("cid", id);
                double cuList =(double) query.uniqueResult();
                
                return cuList;
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        
        }
        
        
        return 0.0;
    }
    
    public String saveCCTVWarrenty(CctvWarranty cw) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (session != null) {
            try {
                session.save(cw);
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
    
    public String changeStatus(int qid,int type) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (session != null) {
            try {
                String hql = "";
                if(type==1){
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
                    hql="UPDATE Quotation q SET q.quotationStatusId.id =:approved WHERE q.id=:id";
                }else{
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
                    hql="UPDATE Quotation q SET q.quotationStatusId.id =:canceld WHERE q.id=:id";
                }
                Query query = session.createQuery(hql);
                if(type == 1){
                    query.setParameter("approved", 2);
                }else{
                    query.setParameter("canceld", 3);
                }
                
                query.setParameter("id", qid);
                query.executeUpdate();
                tr.commit();
                return VertecConstants.SUCCESS;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }

        return VertecConstants.ERROR;
    }
   
}

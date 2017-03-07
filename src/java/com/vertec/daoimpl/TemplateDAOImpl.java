/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.daoimpl;

import com.vertec.hibe.model.AgreementTemplate;
import com.vertec.hibe.model.Customer;
import com.vertec.util.NewHibernateUtil;
import com.vertec.util.VertecConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vertec-r
 */
public class TemplateDAOImpl {

    public String saveasText(String path, String content) {
        try {
            File file = new File(path);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // write in file
            bw.write(content);
            // close connection
            bw.close();
            return VertecConstants.SUCCESS;
        } catch (Exception e) {
            System.out.println(e);
            return VertecConstants.ERROR;
        }
    }

    public String ReadText(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String sCurrentLine;
            String textfile = "";
            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
                textfile += sCurrentLine;
            }
            return textfile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String DeleteFile(String path) {
        try {
            File file = new File(path);
            if (file.delete()) {
                return VertecConstants.SUCCESS;
            } else {
                System.out.println("File Not Deleted");
                return VertecConstants.ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return VertecConstants.ERROR;
        }

    }

    public AgreementTemplate loadAgreementTemplateByID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                Query query = session.getNamedQuery("AgreementTemplate.findById");
                query.setParameter("id", id);
                AgreementTemplate prList = (AgreementTemplate) query.uniqueResult();
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

    public List<AgreementTemplate> loadAllAgreementTemplates() {
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

    public String saveAgreementTemplate(AgreementTemplate agreement) {
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
}

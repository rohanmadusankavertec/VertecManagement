/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AgreementDAOImpl;
import com.vertec.daoimpl.QuotationDAOImpl;
import com.vertec.hibe.model.Agreement;
import com.vertec.hibe.model.AgreementTemplate;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Quotation;
import com.vertec.hibe.model.Service;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author vertec-r
 */
@WebServlet(name = "AgreementController", urlPatterns = {"/AgreementController"})
public class AgreementController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final AgreementDAOImpl AgreementDAO = new AgreementDAOImpl();

    private final QuotationDAOImpl quotationDAO = new QuotationDAOImpl();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {
                // open add agreement page
                case "AddAgreement": {
                    List<ProjectProposal> c = AgreementDAO.loadAllApprovedProject();
                    request.setAttribute("projects", c);
                    requestDispatcher = request.getRequestDispatcher("app/agreement/addAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view agreement
                case "ViewAgreement": {
                    List<Agreement> a = AgreementDAO.loadAllAgreement();
                    request.setAttribute("agreement", a);
                    requestDispatcher = request.getRequestDispatcher("app/agreement/ViewAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // save agreement
                case "SaveAgreement": {
                    String project = request.getParameter("project").trim();
                    String quotation = request.getParameter("quotation").trim();
                    String from = request.getParameter("from").trim();
                    String to = request.getParameter("to").trim();
                    String requirement = request.getParameter("requirement").trim();
                    String amount = request.getParameter("amount").trim();
                    String asdate = request.getParameter("asdate").trim();
                    Date date = new Date();
                    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    Agreement ag = new Agreement();

                    try {
                        ag.setProjectProposalId(new ProjectProposal(Integer.parseInt(project)));
                        ag.setFromDate(DateFormat.parse(from));
                        ag.setToDate(DateFormat.parse(to));
                        if(!asdate.equals("")){
                        ag.setSignedDate(DateFormat.parse(asdate));
                        }
                        ag.setAddedBy(user1);
                        ag.setRequirement(requirement);
                        ag.setCreatedDate(date);
                        ag.setAmount(Double.parseDouble(amount));
                        ag.setQuotationId(new Quotation(Integer.parseInt(quotation)));
                    } catch (ParseException ex) {
                        Logger.getLogger(AgreementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String result = AgreementDAO.saveAgreement(ag);
                    if (result.equals(VertecConstants.SUCCESS)) {
                        request.getSession().setAttribute("Success_Message", "Successfully Added");
                        response.sendRedirect("Agreement?action=AddAgreement");
                    } else {
                        request.getSession().setAttribute("Error_Message", "Not Added,Please Try again");
                        response.sendRedirect("Agreement?action=AddAgreement");
                    }
                    break;
                }
                // get quotation
                case "getQuotations": {
                    String id = request.getParameter("project");
                    List<Quotation> q = quotationDAO.loadAllQuotationByProjectID(Integer.parseInt(id));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (Quotation d : q) {
                        job1 = new JSONObject();
                        job1.put("id", d.getId());
                        job1.put("date", d.getDate().toString().replace("-", "/"));
                        job1.put("name", d.getProjectProposalId().getProposalName());
                        job1.put("amount", d.getAmount());
                        jar1.add(job1);
                    }
                    jOB.put("quotation", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                // view agreement
                case"loadAgreement":{
                    String agId = request.getParameter("id").trim();
                    Agreement agreement = AgreementDAO.viewAgreement(Integer.parseInt(agId));
                    request.setAttribute("agree", agreement);
                    requestDispatcher = request.getRequestDispatcher("app/agreement/ViewAgreementTemplates.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                    
                }
                // print agreement
                case "ToPrintAgreement": {
                    List<Agreement> a = AgreementDAO.loadAllAgreement();
                    request.setAttribute("agreement", a);
                    List<AgreementTemplate> b= AgreementDAO.loadAllAgreementTemplate();
                    request.setAttribute("template", b);
                    requestDispatcher = request.getRequestDispatcher("app/agreement/toPrintAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view agreement to update
                case"loadAgreementtoUpdate":{
                    String agId = request.getParameter("hiddemId").trim();
                    Agreement agreement = AgreementDAO.viewAgreement(Integer.parseInt(agId));
                    request.setAttribute("agree", agreement);
                    List<ProjectProposal> c = AgreementDAO.loadAllProjectProposal();
                    request.setAttribute("projects", c);
                    List<Quotation> q = quotationDAO.loadAllQuotation();
                    request.setAttribute("quot", q);
                    
                    requestDispatcher = request.getRequestDispatcher("app/agreement/LoadAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                    
                }
                // update agreement
                case"UpdateAgreemet":{
                    
                    System.out.println("CALLING UPDATE FUNCTION");
                    String fdate = request.getParameter("from").trim();
                    String todate = request.getParameter("to").trim();
                    String req = request.getParameter("requirement").trim();
                    String amt = request.getParameter("amount").trim();
                    String sdate = request.getParameter("asdate").trim();
                    String id = request.getParameter("hiddemId").trim();
                    
                    
                    
                    try {
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
                    
                        Agreement a =new  Agreement();
                            a.setFromDate(sd.parse(fdate));
                            a.setToDate(sd.parse(todate));
                            a.setRequirement(req);
                            a.setAmount(Double.parseDouble(amt));
                            if(!sdate.equals("")){
                                a.setSignedDate(sd.parse(sdate));
                            }else{
                                a.setSignedDate(null);
                            }
                           
                            a.setId(Integer.parseInt(id));
                        String result = AgreementDAO.updateAgreement(a);

                        if (result.equals(VertecConstants.UPDATED)) {
                            request.getSession().removeAttribute("Success_Message");

                            request.getSession().setAttribute("Success_Message", "Successfully Updated");
                            response.sendRedirect("Agreement?action=ViewAgreement");
                        } else {
                            request.getSession().removeAttribute("Error_Message");

                            request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                            response.sendRedirect("Agreement?action=ViewAgreement");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    break;
                    
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

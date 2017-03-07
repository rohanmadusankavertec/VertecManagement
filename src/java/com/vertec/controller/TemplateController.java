/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AgreementDAOImpl;
import com.vertec.daoimpl.TemplateDAOImpl;
import com.vertec.hibe.model.Agreement;
import com.vertec.hibe.model.AgreementTemplate;
import com.vertec.hibe.model.Installment;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vertec-r
 */
@WebServlet(name = "TemplateController", urlPatterns = {"/TemplateController"})
public class TemplateController extends HttpServlet {

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

    private final TemplateDAOImpl TemplateDAO = new TemplateDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {
                // view thw design of agreement
                case "AddAgreementTemplate": {
                    requestDispatcher = request.getRequestDispatcher("app/agreement/DesignAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                //load the agreement page
                case "ViewAgreementTemplate": {
                    List<AgreementTemplate> c = TemplateDAO.loadAllAgreementTemplates();
                    request.setAttribute("agreement", c);
                    requestDispatcher = request.getRequestDispatcher("app/agreement/ViewAgreementTemplates.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view the parameters to create agrement
                case "ViewParameters": {
                    requestDispatcher = request.getRequestDispatcher("app/agreement/AgreementParameters.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // print the agreement template
                case "PrintAgreementTemplate": {
                    String id = request.getParameter("id");
                    AgreementTemplate at = TemplateDAO.loadAgreementTemplateByID(Integer.parseInt(id));
                    request.setAttribute("data", TemplateDAO.ReadText(at.getFilePath()));
                    requestDispatcher = request.getRequestDispatcher("app/agreement/PrintAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // save agreement
                case "SaveAgreementTemplate": {
                    String data = request.getParameter("data").trim();
                    String name = request.getParameter("name").trim();
                    String sctm = System.currentTimeMillis() + "";

                    String directoryPath = getServletContext().getRealPath("/var/lib/vertec/agreement/" + sctm + "_" + name + ".RHN");

                    String result = TemplateDAO.saveasText(directoryPath, data);

                    if (result.equals(VertecConstants.SUCCESS)) {
                        AgreementTemplate at = new AgreementTemplate();
                        at.setName(name);
                        at.setFilePath(directoryPath);
                        String result2 = TemplateDAO.saveAgreementTemplate(at);

                        if (!result2.equals(VertecConstants.SUCCESS)) {
                            result = VertecConstants.ERROR;
                        }
                    }
                    if (result.equals(VertecConstants.SUCCESS)) {
                        request.getSession().setAttribute("Success_Message", "Successfully Added");
                        response.sendRedirect("Template?action=AddAgreementTemplate");
                    } else {
                        request.getSession().setAttribute("Error_Message", "Not Added,Please Try again");
                        response.sendRedirect("Template?action=AddAgreementTemplate");
                    }
                    break;
                }
                // print the agreement
                case "PrintAgreement": {
                    System.out.println("CALLING>>>>>>>>>>>>>>>>>>>>>");
                    String aid = request.getParameter("agreement").trim();
                    String tid = request.getParameter("template").trim();
                    System.out.println("<><><><><" + aid);
                    System.out.println("<><><><><" + tid);
                    AgreementTemplate at = TemplateDAO.loadAgreementTemplateByID(Integer.parseInt(tid));
                    Agreement agreement = AgreementDAO.viewAgreement(Integer.parseInt(aid));
                    String content = TemplateDAO.ReadText(at.getFilePath());
                    Calendar cal = Calendar.getInstance();
                    content = content.replaceAll("P_CDAY", new SimpleDateFormat("dd").format(cal.getTime()));
                    content = content.replaceAll("P_CMON", new SimpleDateFormat("MM").format(cal.getTime()));
                    content = content.replaceAll("P_CYEAR", new SimpleDateFormat("yyyy").format(cal.getTime()));
                    content = content.replaceAll("P_CN", agreement.getQuotationId().getProjectProposalId().getCustomerId().getCompanyName());
                    content = content.replaceAll("P_CA", agreement.getQuotationId().getProjectProposalId().getCustomerId().getAddress());
                    content = content.replaceAll("P_TPA", agreement.getAmount().toString());

                    List<Installment> inList = AgreementDAO.viewInstallmentByQuotationID(agreement.getQuotationId().getId());

                    int i = 0;
                    double fp = 0.0;
                    double sp = 0.0;
                    double tp = 0.0;
                    for (Installment installment : inList) {

                        if (i == 0) {
                            fp = installment.getAmount();
                        } else if (i == 1) {
                            sp = installment.getAmount();
                        } else if (i == 2) {
                            tp = installment.getAmount();
                        } else {
                            break;
                        }

                    }
                    content = content.replaceAll("P_FIA", fp + "");
                    content = content.replaceAll("p_SIA", sp + "");
                    content = content.replaceAll("P_TIA", tp + "");

                    cal.setTime(agreement.getFromDate());

                    content = content.replaceAll("P_PSD", cal.get(Calendar.DATE) + "");
                    content = content.replaceAll("P_PSM", cal.get(Calendar.MONTH) + "");
                    content = content.replaceAll("P_PSY", cal.get(Calendar.YEAR) + "");
                    cal.setTime(agreement.getToDate());
                    content = content.replaceAll("P_PED", cal.get(Calendar.DATE) + "");
                    content = content.replaceAll("P_PEM", cal.get(Calendar.MONTH) + "");
                    content = content.replaceAll("P_PEY", cal.get(Calendar.YEAR) + "");

                    request.setAttribute("data", content);
                    requestDispatcher = request.getRequestDispatcher("app/agreement/PrintAgreement.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // remove the template
                case "deleteTemplate": {
                    String id = request.getParameter("id");
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>???????????????????" + id);
                    AgreementTemplate at = TemplateDAO.loadAgreementTemplateByID(Integer.parseInt(id));
                    System.out.println(at.getName());
                    String result2 = result2 = TemplateDAO.DeleteFile(at.getFilePath());
                    String result = AgreementDAO.deleteAgreementTemplate(Integer.parseInt(id));
                    if(result.equals(VertecConstants.SUCCESS)&&result2.equals(VertecConstants.SUCCESS)){
                    out.write(VertecConstants.SUCCESS);
                    }else{
                    out.write(VertecConstants.ERROR);
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

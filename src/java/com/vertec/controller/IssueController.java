/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.IssuesDAO;
import com.vertec.daoimpl.ProposalProjectDAOImpl;
import com.vertec.hibe.model.Issues;
import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.SysUser;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Imansa
 */
@WebServlet(name = "IssueController", urlPatterns = {"/IssueController"})
public class IssueController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;
            boolean isValidated = true;

            switch (action) {

                // save Issue
                case "AddIssue": {
                    System.out.println("In add new issues");
                    String desc = request.getParameter("textArea");
                    String projectName = request.getParameter("comboBox");
                    
                    System.out.println(projectName);
                    Issues issues = new Issues();
                    issues.setAddedBy(user1);
                    issues.setIsFixed(false);
                    issues.setIssue(desc);
                    issues.setProjectDetailsId(new ProjectDetails(Integer.parseInt(projectName)));
                    boolean add = IssuesDAO.addIssue(issues);
                    if (add) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Added");
                        response.sendRedirect("Issue?action=viewAddIssue");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Added,Please Try again");
                        response.sendRedirect("Issue?action=viewAddIssue");
                    }
                    break;
                }
                // view Issue
                case "viewAddIssue": {
                    System.out.println("Calling");
                    List<ProjectDetails> a = new ProposalProjectDAOImpl().loadAllProjectDetails();
                    request.setAttribute("projects", a);
                    List<Issues> i = new IssuesDAO().getListOfIssues();
                    request.setAttribute("issues", i);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/AddIssues.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }

                // change the status os issue
                case "completeIssue": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String result = new IssuesDAO().setComplete(id);

                    out.write(result);

                    break;
                }
                // view the issues to updte
                case "selectIssueForUpdate": {
                    int id = Integer.parseInt(request.getParameter("issue").trim());
                    Issues selectedIssue = new IssuesDAO().getSelectedIssue(id);
                    request.setAttribute("issueOb", selectedIssue);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/UpdateIssue.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // update changers of issues
                case "updateIssue": {
                    String issue = request.getParameter("textArea");
                    int id = Integer.parseInt(request.getParameter("id"));
                    Issues issues = new Issues();
                    issues.setIssue(issue);
                    issues.setId(id);
                    boolean updateIssue = new IssuesDAO().updateIssue(issues);
                    if (updateIssue) {
                        
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Updated");
                        requestDispatcher = request.getRequestDispatcher("Issue?action=viewAddIssue");
                        requestDispatcher.forward(request, response);
                    }else{
                        
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                        requestDispatcher = request.getRequestDispatcher("app/projectDetails/UpdateIssue.jsp");
                        requestDispatcher.forward(request, response);
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

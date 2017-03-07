/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.ExpensesDAOImpl;
import com.vertec.daoimpl.ProjectDetailsDAOImpl;
import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectExpenses;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
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
 * @author User
 */
@WebServlet(name = "ExpensesController", urlPatterns = {"/ExpensesController"})
public class ExpensesController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public final ExpensesDAOImpl expensesDAO = new ExpensesDAOImpl();
    public final ProjectDetailsDAOImpl projectDetailsDAO = new ProjectDetailsDAOImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;
            
            switch(action){
                
                // load expenses page
                case "ViewProjectName" :{
                    List<ProjectDetails> prodetails = projectDetailsDAO.loadAllProjectDetails();
                    request.setAttribute("projectDetails", prodetails);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/AddProjectExpenses.jsp");
                    requestDispatcher.forward(request, response);
                    
                }
                // save expenses
                case "AddExpenses" :{
                    
                    String description = request.getParameter("description").trim();
                    System.out.println(".........."+description);
                    String amount = request.getParameter("amount").trim();
                    String detailsId = request.getParameter("projectName").trim();
                    
                    ProjectExpenses pe = new ProjectExpenses();
                    pe.setDescription(description);
                    pe.setAmount(Double.parseDouble(amount));
                    pe.setProjectDetailsId(new ProjectDetails(Integer.parseInt(detailsId)));
                    
                    String result = expensesDAO.addExpenses(pe);
                    
                    if (result.equals(VertecConstants.SUCCESS)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Added");
                    response.sendRedirect("dashboard.jsp");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                        response.sendRedirect("app/projectDetails/AddProjectExpenses.jsp");
                    }
                    
                    break;
                }
                
                // view expenses 
                case "ViewExpenses": {
//                    List<ProjectExpenses> e = expensesDAO.loadAllExpenses(id);
//                    request.setAttribute("quotation", e);
                    System.out.println("ffff");
                    String para=request.getParameter("detailsId");
                    if(para!=null){
                    String pid = para.trim();
                    System.out.println(pid + " "+"add");
                    List<ProjectExpenses> essList = expensesDAO.loadAllExpenses(Integer.parseInt(pid));
                    request.setAttribute("expensesList", essList);
                    }


                    System.out.println("ffff");
                    List<ProjectDetails> prodetails = projectDetailsDAO.loadAllProjectDetails();
                    System.out.println("3");
                    request.setAttribute("projectDetails", prodetails);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/ExpensesDetails.jsp");
                    requestDispatcher.forward(request, response);

                    break;
                }
                
                // update expenses
                case "UpdateExpenses":{
                    String dcpt = request.getParameter("description").trim();
                    String amt = request.getParameter("amount").trim();
                    String expid = request.getParameter("exId").trim();
                    
                    ProjectExpenses pe = new ProjectExpenses();
                    pe.setDescription(dcpt);
                    pe.setAmount(Double.parseDouble(amt));
                    pe.setId(Integer.parseInt(expid));
               
                    String result = expensesDAO.updateProjectExpenses(pe);
                    
                    if (result.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Updated");
                    response.sendRedirect("Expenses?action=ViewExpenses");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Updated,Please Tri again");
                        response.sendRedirect("Expenses?action=ViewExpenses");
                    }
                    break;
                }
                // view expenses to update
                case "toUpdateExpenses": {
                String expId = request.getParameter("eId").trim();
                    System.out.println(expId);
                ProjectExpenses expenses = expensesDAO.viewExpenses(Integer.parseInt(expId));
                System.out.println(expenses);
                request.setAttribute("expe", expenses);
                requestDispatcher = request.getRequestDispatcher("app/projectDetails/ViewExpenses.jsp");
                requestDispatcher.forward(request, response);
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

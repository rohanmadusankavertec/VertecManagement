/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.CustomerDAOImpl;
import com.vertec.daoimpl.ProposalProjectDAOImpl;
import com.vertec.daoimpl.ServiceDAOImpl;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Service;
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
@WebServlet(name = "ProposalProjectController", urlPatterns = {"/ProposalProjectController"})
public class ProposalProjectController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private final ProposalProjectDAOImpl proposalDAO = new ProposalProjectDAOImpl();
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private final ServiceDAOImpl serviceDAO = new ServiceDAOImpl();
    
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
        
            // save  new project proposal
            
            case "AddNewProject":{
//                System.out.println("In Add new Project Proposal");
                
                String ppName = request.getParameter("pproposalName").trim();
                String cusId = request.getParameter("customerName").trim();
                String sId = request.getParameter("serviceName").trim();

               int c =Integer.parseInt(cusId); 
                
                  ProjectProposal pp = new ProjectProposal();
                  pp.setProposalName(ppName);
                  pp.setCustomerId(new Customer(c));
                  pp.setServiceId(new Service(Integer.parseInt(sId)));
                  pp.setIsValid(isValidated);

                  String result = proposalDAO.saveProjectProposal(pp);
                
                if (result.equals(VertecConstants.SUCCESS)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Added");
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                    response.sendRedirect("app/projectProposal/addProjectProposal.jsp");
                }

                break;
            }
            //load project details to create new project
            case "getProjectDetails": {
                
                    List<ProjectProposal> p = proposalDAO.loadAllProject();
                    request.setAttribute("proposal", p);
                    List<Customer> c = customerDAO.getListOfCustomer();
                request.setAttribute("customerP", c);
                List<Service> s = serviceDAO.getListOfService();
                request.setAttribute("serviceP", s);
                    
                    requestDispatcher = request.getRequestDispatcher("app/projectProposal/addProjectProposal.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                
            }
            
            //view project proposal
            case "ViewProjectProposal": {
                List<ProjectProposal> p = proposalDAO.loadAllProject();
                    request.setAttribute("proposal", p);
                List<Customer> c = customerDAO.getListOfCustomer();
                request.setAttribute("customerP", c);
                List<Service> s = serviceDAO.getListOfService();
                request.setAttribute("serviceP", s);
                requestDispatcher = request.getRequestDispatcher("app/projectProposal/ProjectProposalDetails.jsp");
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

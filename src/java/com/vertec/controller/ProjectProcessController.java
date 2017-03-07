/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.ProjectDetailsDAOImpl;
import com.vertec.daoimpl.ProjectProcessDAOImpl;
import com.vertec.daoimpl.ProposalProjectDAOImpl;
import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectProcess;
import com.vertec.hibe.model.ProjectProposal;
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
@WebServlet(name = "ProjectProcessController", urlPatterns = {"/ProjectProcessController"})
public class ProjectProcessController extends HttpServlet {

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
        private final ProjectDetailsDAOImpl projectDeDAO = new ProjectDetailsDAOImpl();
    private final ProjectProcessDAOImpl projectPssDAO = new ProjectProcessDAOImpl();
    
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
            // load add projrct details page 
            case "getProjectDetails": {
                
                List<ProjectProposal> p = proposalDAO.loadAllProject();
                request.setAttribute("proposal", p);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/AddProjectProcess.jsp");
                    requestDispatcher.forward(request, response);
                    break;
            }
            
            // view process page
            case "getProjectName": {
                
                List<ProjectDetails> p = projectDeDAO.loadAllProjectDetails();
                request.setAttribute("projectDetails", p);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/AddProjectProcess.jsp");
                    requestDispatcher.forward(request, response);
                    break;
            }
            
            // save process
            case "SaveProcess" :{
                    String processList = request.getParameter("processList").trim();
                    System.out.println(processList);
                    String detailsId = request.getParameter("proDId").trim();
                    System.out.println(detailsId);
                    String result ="";
                    
                    String[] pArr = processList.split(",");
                    for (int i = 0; i < pArr.length; i++) {
                        ProjectProcess pros = new ProjectProcess();
                        pros.setProcess(pArr[i]);
                        pros.setDescription(pArr[i+1]);
                        pros.setDuration(Integer.parseInt(pArr[i+2]));
                        pros.setAddedBy(user1);
                        pros.setIsComplete(true);
                        pros.setProjectDetailsId(new ProjectDetails(Integer.parseInt(detailsId)));
                        i+=2;
                        result = projectPssDAO.saveProjectProcess(pros);
                    }
                    if (result.equals(VertecConstants.SUCCESS)) {
                        out.write(VertecConstants.SUCCESS);
                    } else {
                        out.write(VertecConstants.ERROR);
                    }
                    
                    break;
            }
            
            // view process
            case "ViewProcess" :{
                String para=request.getParameter("detailsId");
                if(para!=null){
                String pid = para.trim();
//                System.out.println(pid);
                List<ProjectProcess> processList = projectPssDAO.loadAllProcessByProjectName(Integer.parseInt(pid));
                request.setAttribute("processL", processList);
                }
                List<ProjectDetails> prodetails = projectDeDAO.loadAllProjectDetails();
                request.setAttribute("projectDetails", prodetails);
                requestDispatcher = request.getRequestDispatcher("app/projectDetails/ProjectProcessDetails.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            
            // remove process
            case "DeleteProcess" :{
                String processId = request.getParameter("processId").trim();
                String detailsId = request.getParameter("detailsId").trim();
                
                String result = projectPssDAO.DeleteProcessById(Integer.parseInt(processId));
                
                if (result.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Deleted");
                    response.sendRedirect("ProjectProcess?action=ViewProcess&detailsId="+detailsId);
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Deleted,Please Try again");
                    response.sendRedirect("ProjectProcess?action=ViewProcess&detailsId="+detailsId);
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

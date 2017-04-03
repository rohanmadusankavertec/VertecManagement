/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.CostCenterDAOImpl;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.State;
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
 * @author Ruchira
 */
@WebServlet(name = "CostCenterController", urlPatterns = {"/CostCenterController"})
public class CostCenterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final CostCenterDAOImpl costcenterdao = new CostCenterDAOImpl();
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;
            switch(action){
                
                case "CostCenterPage": {
                    List<CostCenter> cList = costcenterdao.loadAllCostCenter();
                    request.setAttribute("costcenter", cList);
                    requestDispatcher = request.getRequestDispatcher("app/account/costCenter/costCenterManage.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "saveCostCenter": {
                    String state = request.getParameter("name").trim();
                    String code = request.getParameter("code").trim();
                    CostCenter c = new CostCenter();
                    c.setName(state);
                    c.setCode(code);
                    c.setIsvalid(true);
                    
                    String result = costcenterdao.saveCostCenter(c);
                    if (result.equals(VertecConstants.SUCCESS)) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Added");
                        response.sendRedirect("CostCenter?action=CostCenterPage");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                        response.sendRedirect("CostCenter?action=CostCenterPage");
                    }
                    break;
                }
                
                case "loadCostCenter": {
                    String ccId = request.getParameter("sId");
                    System.out.println("..ccid..."+ccId);
                    CostCenter CostCenter = costcenterdao.viewCostCenterById(Integer.parseInt(ccId));
                    request.setAttribute("CostCenter", CostCenter);
                    requestDispatcher = request.getRequestDispatcher("app/account/costCenter/costCenterUpdate.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                    
                }
                
                case "updateCostCenter": {
                    String ccname = request.getParameter("name").trim();
                    String code = request.getParameter("code").trim();
                    String id = request.getParameter("sId").trim();
                    System.out.println("......"+ccname);
                    CostCenter cc = new CostCenter();
                    cc.setName(ccname);
                    cc.setCode(code);
                    cc.setId(Integer.parseInt(id));
                    
                    
                    
                    String result = costcenterdao.updateCostCenter(cc);
                    if (result.equals(VertecConstants.UPDATED)) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Updated");
                        response.sendRedirect("CostCenter?action=CostCenterPage");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Updated,Please Tri again");
                        response.sendRedirect("CostCenter?action=CostCenterPage");
                    }
                    break;
                }
                
                
                case "deleteCostCenter": {
                    String id = request.getParameter("sId").trim();
                    String result = costcenterdao.deleteCostCenter(Integer.parseInt(id));
                    if (result.equals(VertecConstants.SUCCESS)) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Deleted");
                        response.sendRedirect("CostCenter?action=CostCenterPage");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Deleted,Please Tri again");
                        response.sendRedirect("CostCenter?action=CostCenterPage");
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

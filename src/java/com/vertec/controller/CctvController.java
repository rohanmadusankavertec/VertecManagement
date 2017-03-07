/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.CctvDAOImpl;
import com.vertec.hibe.model.CctvCategory;
import com.vertec.hibe.model.CctvItems;
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
 * @author Ruchira
 */
@WebServlet(name = "CctvController", urlPatterns = {"/CctvController"})
public class CctvController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final CctvDAOImpl cctvDAO = new CctvDAOImpl();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {
                // load add quation page
                case "viewCCTV": {
                    List<CctvCategory> category = cctvDAO.loadItemCategory();
                    request.setAttribute("category", category);
                    List<CctvItems> itemList = cctvDAO.loadAllItems();
                    request.setAttribute("itemList", itemList);
                    requestDispatcher = request.getRequestDispatcher("app/cctv/cctvManage.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view quoattion
                case "loadItem": {
                    String itemId = request.getParameter("cId").trim();
                    List<CctvCategory> category = cctvDAO.loadItemCategory();
                    request.setAttribute("category", category);
                    CctvItems item = cctvDAO.viewItemById(Integer.parseInt(itemId));
                    request.setAttribute("item", item);
                    requestDispatcher = request.getRequestDispatcher("app/cctv/viewItem.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                    
                }
                // save quotation
                case "savecctv":{
                    String categoryid = request.getParameter("itemcategory").trim();
                    String name = request.getParameter("itemname").trim();
                    String price = request.getParameter("price").trim();
                    
                    CctvItems c = new CctvItems();
                    c.setName(name);
                    c.setPrice(Double.parseDouble(price));
                    c.setCctvCategoryId(new CctvCategory(Integer.parseInt(categoryid)));
                    
                    String result = cctvDAO.saveCCTVItem(c);
                    if (result.equals(VertecConstants.SUCCESS)) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Added");
                        response.sendRedirect("CCTV?action=viewCCTV");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                        response.sendRedirect("CCTV?action=viewCCTV");
                    }

                    break;
                    
                }
                // update quotation
                case "updateItem": {
                    String categoryId = request.getParameter("itemcategory").trim();
                    String name = request.getParameter("itemname").trim();
                    String price = request.getParameter("price").trim();
                    String id = request.getParameter("cId").trim();
                    
                    CctvItems c = new  CctvItems();
                    c.setName(name);
                    c.setPrice(Double.parseDouble(price));
                    c.setCctvCategoryId(new CctvCategory(Integer.parseInt(categoryId)));
                    c.setId(Integer.parseInt(id));
                    String result = cctvDAO.updateItem(c);
                    
                    if (result.equals(VertecConstants.UPDATED)) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Updated");
                        response.sendRedirect("CCTV?action=viewCCTV");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                        response.sendRedirect("CCTV?action=viewCCTV");
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

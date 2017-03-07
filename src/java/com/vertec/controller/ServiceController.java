/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.CustomerDAOImpl;
import com.vertec.daoimpl.ServiceDAOImpl;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Service;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
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
@WebServlet(name = "ServiceController", urlPatterns = {"/ServiceController"})
public class ServiceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // private  final EmployeeDAOImpl employeedao = new EmployeeDAOImpl();
    private final ServiceDAOImpl servicedao = new ServiceDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        HttpSession httpSession = request.getSession();
        SysUser user1 = (SysUser) httpSession.getAttribute("user");
        RequestDispatcher requestDispatcher;

        switch (action) {

            // save new service
            case "Register": {
                System.out.println("In Customer Registration");

                String Name = request.getParameter("Name").trim();

                Service c = new Service();
                c.setServiceName(Name);
                c.setIsValid(true);
                //String result = employeedao.saveEmployee(e);
                String result = servicedao.saveService(c);

                if (result.equals(VertecConstants.SUCCESS)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Added");
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                    response.sendRedirect("app/service/registerService.jsp");
                }

                break;
            }

            // get the services 
            case "ViewService": {
                List<Service> cuList = servicedao.getListOfService();
                request.setAttribute("service", cuList);
                requestDispatcher = request.getRequestDispatcher("app/service/registerService.jsp");
                requestDispatcher.forward(request, response);
                break;
            }

            // view the service details to update
            case "UpdateService": {
                String cId = request.getParameter("serviceId").trim();
                int cuId = Integer.parseInt(cId);
                Service customer = servicedao.viewService(cuId);
                request.setAttribute("service", customer);
                requestDispatcher = request.getRequestDispatcher("app/service/viewService.jsp");
                requestDispatcher.forward(request, response);
                break;
            }

            // update service
            case "UpSer": {
                System.out.println("CALLING UPDATE FUNCTION");
                String sId = request.getParameter("serId").trim();
                String Name = request.getParameter("Name").trim();

                Service c = new Service();
                c.setId(Integer.parseInt(sId));
                c.setServiceName(Name);

                String result = servicedao.updateService(c);

                if (result.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Updated");
                    response.sendRedirect("Service?action=ViewService");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                    response.sendRedirect("Service?action=ViewService");
                }

                break;
            }

            //remove the service
            case "RemoveService": {
                String userId = request.getParameter("serviceId").trim();
                int csId = Integer.parseInt(userId);
                String status = servicedao.removeService(csId);

                if (status.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Deleted");
                    response.sendRedirect("Service?action=ViewService");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Deleted,Please Try again");
                    response.sendRedirect("Service?action=ViewService");
                }

                break;
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

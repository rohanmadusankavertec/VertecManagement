/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.CustomerDAOImpl;
import com.vertec.daoimpl.DashboardDAOImpl;
import com.vertec.daoimpl.EmployeeDAOImpl;
import com.vertec.daoimpl.QuotationDAOImpl;
import com.vertec.daoimpl.ReportDAOImpl;
import com.vertec.daoimpl.ServiceDAOImpl;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Service;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author vertec-r
 */
@WebServlet(name = "ReportController", urlPatterns = {"/ReportController"})
public class ReportController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private final ReportDAOImpl ReportDAO = new ReportDAOImpl();
    private final CustomerDAOImpl CustomerDAO = new CustomerDAOImpl();
    private final ServiceDAOImpl ServiceDAO = new ServiceDAOImpl();
    private final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    private final DashboardDAOImpl dashboardDAOImpl = new DashboardDAOImpl();
    private final QuotationDAOImpl quotationDAOImpl = new QuotationDAOImpl();

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;
            
            switch (action) {
                // get the dashboard details to view
                case "dashboard": {
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = new JSONObject();
                    job1.put("employee", dashboardDAOImpl.getEmployees());
                    job1.put("customer", dashboardDAOImpl.getCustomers());
                    job1.put("package", dashboardDAOImpl.getPackages());
                    job1.put("service", dashboardDAOImpl.getServices());
                    job1.put("completed", dashboardDAOImpl.getCompletedProject());
                    job1.put("incompleted", dashboardDAOImpl.getincompletedProject());
                    job1.put("hold", dashboardDAOImpl.getHoldProject());
                    job1.put("ongoing", dashboardDAOImpl.getOngoingProject());
                    
                    jar1.add(job1);
                    jOB.put("des", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                // print customer report
                case "printCustomer": {
                    List<Customer> c = CustomerDAO.getListOfCustomer();
                    request.setAttribute("customer", c);
                    requestDispatcher = request.getRequestDispatcher("app/report/customer.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // print service report
                case "printService": {
                    List<Service> c = ServiceDAO.getListOfService();
                    request.setAttribute("service", c);
                    requestDispatcher = request.getRequestDispatcher("app/report/service.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // print package report
                case "printPackage": {
                    List<com.vertec.hibe.model.Package> c = quotationDAOImpl.loadAllPackages();
                    request.setAttribute("package", c);
                    requestDispatcher = request.getRequestDispatcher("app/report/packages.jsp");
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

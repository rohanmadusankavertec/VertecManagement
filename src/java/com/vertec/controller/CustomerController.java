/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.CustomerDAOImpl;
import com.vertec.hibe.model.Customer;
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
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

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
    private  final CustomerDAOImpl customerdao = new CustomerDAOImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        HttpSession httpSession = request.getSession();
        SysUser user1 = (SysUser) httpSession.getAttribute("user");
        RequestDispatcher requestDispatcher;
        
        switch (action) {
        
            // customer registration
            case "Register":{
                System.out.println("In Customer Registration");
                
                String firstName = request.getParameter("cusFName").trim();
                String lastName = request.getParameter("cusLName").trim();
                String cumpanyName = request.getParameter("companyName").trim();
                String mobileNo = request.getParameter("mobileNo").trim();
                String officeNo = request.getParameter("officeNo").trim();
                String address = request.getParameter("address").trim();
                String faxNo = request.getParameter("faxNo").trim();
                String email = request.getParameter("email").trim();
                       
                              
                Customer c = new Customer();
                c.setFirstName(firstName);
                c.setLastName(lastName);
                c.setCompanyName(cumpanyName);
                c.setMobileNo(mobileNo);
                c.setOffiiceNo(officeNo);
                c.setEmail(email);
                c.setAddress(address);
                c.setFax(faxNo);
                c.setAddedBy(user1);
                c.setIsValid(true);
                //String result = employeedao.saveEmployee(e);
                String result = customerdao.saveCustomer(c);
               
                
                if (result.equals(VertecConstants.SUCCESS)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Added");
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                    response.sendRedirect("app/customer/registerCustomer.jsp");
                }

                break;
            }
            
            // view customer
            case "ViewCustomer": {
                List<Customer> cuList = customerdao.getListOfCustomer();
                request.setAttribute("c", cuList);
                requestDispatcher = request.getRequestDispatcher("app/customer/customerDetails.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            
            // update customer
            case "UpdateCustomer": {
                String cId = request.getParameter("customerId").trim();
                int cuId = Integer.parseInt(cId);
                Customer customer = customerdao.viewCustomer(cuId);
                request.setAttribute("cus", customer);
                requestDispatcher = request.getRequestDispatcher("app/customer/viewCustomer.jsp");
                requestDispatcher.forward(request, response);
                break;
             } 
            
            // update customer details
            case "UpCus": {
                System.out.println("CALLING UPDATE FUNCTION");
                String cId = request.getParameter("cuId").trim();
                String fName = request.getParameter("customerFName").trim();
                String lName = request.getParameter("customerLName").trim();
                String comName = request.getParameter("companyName").trim();
                String mobile = request.getParameter("mobileNo").trim();
                String office = request.getParameter("officeNo").trim();
                String address = request.getParameter("address").trim();
                String fx = request.getParameter("faxNo").trim();
                String eml = request.getParameter("email").trim();
                
                
                int cu = Integer.parseInt(cId);
                
               // System.out.println(suId+"  "+supplierName+"  "+companyName+"  "+address+"  "+fax+"  "+land+"  "+mobile+"  "+email+"  "+isValidated+"  "+user1);
               // Supplier supplier = new Supplier(suId,supplierName, companyName, address, fax, land, mobile, email, isValidated, user1,type1);
                
               
                Customer c =new  Customer();
                    c.setFirstName(fName);
                    c.setLastName(lName);
                    c.setCompanyName(comName);
                    c.setMobileNo(mobile);
                    c.setOffiiceNo(office);
                    c.setAddress(address);
                    c.setEmail(eml);
                    c.setFax(fx);
                    c.setId(cu);
                    c.setIsValid(true);
                    c.setAddedBy(user1);
                    
                
                String result = customerdao.updateCustomer(c);

                if (result.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Updated");
                    response.sendRedirect("Customer?action=ViewCustomer");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                    response.sendRedirect("Customer?action=ViewCustomer");
                }

                break;
            }
            
            // remove customer
            case "RemoveCustomer": {
                String userId = request.getParameter("customerId").trim();
                int csId = Integer.parseInt(userId);
                String status = customerdao.removeCustomer(csId);
                
                if (status.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Deleted");
                    response.sendRedirect("Customer?action=ViewCustomer");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Deleted,Please Try again");
                    response.sendRedirect("Customer?action=ViewCustomer");
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

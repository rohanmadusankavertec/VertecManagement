/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.EmployeeDAOImpl;
import com.vertec.hibe.model.Employee;
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
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private  final EmployeeDAOImpl employeedao = new EmployeeDAOImpl();
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession httpSession = request.getSession();
        
        RequestDispatcher requestDispatcher;
        boolean isValidated = true;
        
        
        
            
        switch (action) {
        
            //  Add employee
            case "Register":{
                System.out.println("In Employee Registration");
                
                String firstName = request.getParameter("employeeFName").trim();
                String lastName = request.getParameter("employeeLName").trim();
                String salary = request.getParameter("salary").trim();
                String nic = request.getParameter("nic").trim();
                 
                double sal=Double.parseDouble(salary);
                
                System.out.println(firstName+" "+ lastName+" "+ sal+" "+ nic);
                //Supplier supplier = new Supplier(supplierName, companyName, address, fax, land, mobile, email, date, isValidated, user1,type1);
                //Employee employee=new Employee(firstName, lastName, sal, nic);
                
                Employee e= new Employee();
                e.setFirstName(firstName);
                e.setLastName(lastName);
                e.setSalary(sal);
                e.setNic(nic);
                e.setIsValid(true);
                
                String result = employeedao.saveEmployee(e);
                
                if (result.equals(VertecConstants.SUCCESS)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Added");
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                    response.sendRedirect("registerEmployee.jsp");
                }

                break;
            }
            
            // view employee 
            case "ViewEmployee": {
                List<Employee> emList = employeedao.getListOfEmployee();
                request.setAttribute("e", emList);
                requestDispatcher = request.getRequestDispatcher("app/employee/employeeDetails.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            
            // view employee details to update 
            case "UpdateEmployee": {
                String eId = request.getParameter("employeeId").trim();
                int emId = Integer.parseInt(eId);
                Employee employee = employeedao.viewEmployee(emId);
                request.setAttribute("emp", employee);
                requestDispatcher = request.getRequestDispatcher("app/employee/viewEmployee.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            //update employee
            case "UpEmp": {
                System.out.println("CALLING UPDATE FUNCTION");
                String eId = request.getParameter("emId").trim();
                String fName = request.getParameter("employeeFName").trim();
                String lName = request.getParameter("employeeLName").trim();
                String salary = request.getParameter("salary").trim();
                String nic = request.getParameter("nic").trim();
                
                
                int em = Integer.parseInt(eId);
                double  sal=Double.parseDouble(salary);
                
               // System.out.println(suId+"  "+supplierName+"  "+companyName+"  "+address+"  "+fax+"  "+land+"  "+mobile+"  "+email+"  "+isValidated+"  "+user1);
               // Supplier supplier = new Supplier(suId,supplierName, companyName, address, fax, land, mobile, email, isValidated, user1,type1);
                
                Employee e = new Employee();
                    
                    e.setFirstName(fName);
                    e.setLastName(lName);
                    e.setSalary(sal);
                    e.setNic(nic);
                    e.setId(em);
                    
                
                String result = employeedao.updateEmployee(e);

                if (result.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Updated");
                    response.sendRedirect("Employee?action=ViewEmployee");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                    response.sendRedirect("Employee?action=ViewEmployee");
                }

                break;
            }
            
            // remove employee
            case "RemoveEmployee": {
                String userId = request.getParameter("employeeId").trim();
                int emId = Integer.parseInt(userId);
                String status = employeedao.removeEmployee(emId);
                
                if (status.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Updated");
                    response.sendRedirect("Employee?action=ViewEmployee");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                    response.sendRedirect("Employee?action=ViewEmployee");
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

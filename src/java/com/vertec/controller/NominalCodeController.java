/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AccountReportDAOImpl;
import com.vertec.daoimpl.NominalCodeDAOImpl;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.NominalCode;
import com.vertec.hibe.model.State;
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
@WebServlet(name = "NominalCodeController", urlPatterns = {"/NominalCodeController"})
public class NominalCodeController extends HttpServlet {

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
    private final NominalCodeDAOImpl ncdao = new NominalCodeDAOImpl();
    private final AccountReportDAOImpl ardao = new AccountReportDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        HttpSession httpSession = request.getSession();
        SysUser user1 = (SysUser) httpSession.getAttribute("user");
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "ManageNominalCode": {
                System.out.println("Calling");
                List<State> cuList = ardao.getListOfState();
                request.setAttribute("state", cuList);
                List<FunctionData> fd = ardao.getListOfFunctionData();
                request.setAttribute("fd", fd);
                List<CostCenter> cc = ardao.getListOfCostCenter();
                request.setAttribute("costcenter", cc);
                List<NominalCode> nc = ardao.getListOfNominalCode();
                request.setAttribute("nominalcode", nc);
                requestDispatcher = request.getRequestDispatcher("app/account/NominalCode/registerNominalCode.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            // save new service
            case "Register": {
                System.out.println("In Nominal Code Registration");

                String Name = request.getParameter("nc").trim();
                String code = request.getParameter("code").trim();
                String ccid = request.getParameter("ccid").trim();

                NominalCode nc =new NominalCode();
                nc.setCode(code);
                nc.setName(Name);
                nc.setIsvalid(true);
                
                String result = ncdao.saveNominalCode(nc);

                if (result.equals(VertecConstants.SUCCESS)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Added");
                    response.sendRedirect("NominalCode?action=ManageNominalCode");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                    response.sendRedirect("NominalCode?action=ManageNominalCode");
                }

                break;
            }

            case "UpdateNominalCode": {
                String cId = request.getParameter("ncId").trim();
                int cuId = Integer.parseInt(cId);

                List<State> state = ardao.getListOfState();
                request.setAttribute("state", state);
                List<FunctionData> fd = ardao.getListOfFunctionData();
                request.setAttribute("fd", fd);
                List<CostCenter> cs = ardao.getListOfCostCenter();
                request.setAttribute("costcenter", cs);

                
                NominalCode nc = ardao.getNominalCodeById(cuId);
                request.setAttribute("nc", nc);
                requestDispatcher = request.getRequestDispatcher("app/account/NominalCode/viewNominalCode.jsp");
                requestDispatcher.forward(request, response);
                break;
            }

            // update service
            case "UpNC": {
                String sId = request.getParameter("ncId").trim();
                String Name = request.getParameter("Name").trim();
//                String stateid = request.getParameter("stateid").trim();
//                String fid = request.getParameter("fid").trim();
                String ccid = request.getParameter("ccid").trim();
                String code = request.getParameter("nc").trim();

                
                
                NominalCode nc = new NominalCode();
                nc.setCode(code);
                nc.setId(Integer.parseInt(sId));
                nc.setName(Name);
                
                
                
                
                
                String result = ncdao.updateNominalCode(nc);

                if (result.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");

                    request.getSession().setAttribute("Success_Message", "Successfully Updated");
                    response.sendRedirect("NominalCode?action=ManageNominalCode");
                } else {
                    request.getSession().removeAttribute("Error_Message");

                    request.getSession().setAttribute("Error_Message", "Not Updated,Please Try again");
                    response.sendRedirect("NominalCode?action=ManageNominalCode");
                }

                break;
            }

            //remove the service
            case "RemoveNominalCode": {
                String ncId = request.getParameter("ncId").trim();
                int nId = Integer.parseInt(ncId);
                String status = ncdao.removeNominalCode(nId);

                if (status.equals(VertecConstants.UPDATED)) {
                    request.getSession().removeAttribute("Success_Message");
                    request.getSession().setAttribute("Success_Message", "Successfully Deleted");
                    response.sendRedirect("NominalCode?action=ManageNominalCode");
                } else {
                    request.getSession().removeAttribute("Error_Message");
                    request.getSession().setAttribute("Error_Message", "Not Deleted,Please Try again");
                    response.sendRedirect("NominalCode?action=ManageNominalCode");
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

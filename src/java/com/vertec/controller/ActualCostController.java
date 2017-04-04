/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AccountReportDAOImpl;
import com.vertec.daoimpl.ActualCostDAOImpl;
import com.vertec.daoimpl.CostCenterDAOImpl;
import com.vertec.daoimpl.StateDAOImpl;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.NominalCode;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Ruchira
 */
@WebServlet(name = "ActualCostController", urlPatterns = {"/ActualCostController"})
public class ActualCostController extends HttpServlet {

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
    private final StateDAOImpl statedao = new StateDAOImpl();
    private final ActualCostDAOImpl actualcostdao = new ActualCostDAOImpl();
    private final AccountReportDAOImpl accountReportdao = new AccountReportDAOImpl();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;
            
            switch(action){
                
                case "ActualCostPage": {
//                    System.out.println("....calling ..............");
                    List<State> sList = statedao.loadAllState();
                    
                    request.setAttribute("state", sList);
                    requestDispatcher = request.getRequestDispatcher("app/account/actualCost/addActualCost.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "getCostCenter": {
                    String cid = request.getParameter("ccid").trim();
//                    System.out.println(".........."+cid);
                    List<CostCenter> cc = accountReportdao.getListOfCostCenterByFunctionData(Integer.parseInt(cid));
                     
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (CostCenter c : cc) {
//                        System.out.println(c.getName());
                        job1 = new JSONObject();
                        job1.put("id", c.getId());
                        job1.put("cname", c.getName());
                        
                        
                        jar1.add(job1);
                    }
                    jOB.put("CostCenter", jar1);
                    response.getWriter().write(jOB.toString());
                    
                    break;
                }
                case "getNominalCode": {
                    String cid = request.getParameter("ccid").trim();
//                    System.out.println(".........."+cid);
                    List<NominalCode> nc = accountReportdao.getListOfNominalbyCostCenter(Integer.parseInt(cid));
                     
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (NominalCode n : nc) {
                        System.out.println("........nominal........"+n.getName());
                        job1 = new JSONObject();
                        job1.put("id", n.getId());
                        job1.put("ncname", n.getName());
                        
                        
                        jar1.add(job1);
                    }
                    jOB.put("NominalCode", jar1);
                    response.getWriter().write(jOB.toString());
                    
                    break;
                }
                case "saveActualCost": {
                    String state = request.getParameter("stateid").trim();
                    String code = request.getParameter("fdata").trim();
                    String ccenter = request.getParameter("ccenter").trim();
                    String nominal = request.getParameter("nominalCode").trim();
                    String nominal1 = request.getParameter("nominalCode").trim();
                    CostCenter c = new CostCenter();
                    c.setName(state);
                    c.setCode(code);
                    c.setIsvalid(true);
                    c.setFunctionId(new FunctionData(Integer.parseInt(code)));
                    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AccountReportDAOImpl;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.NominalCode;
import com.vertec.hibe.model.State;
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
@WebServlet(name = "AccountReportController", urlPatterns = {"/AccountReportController"})
public class AccountReportController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final AccountReportDAOImpl AccountReportDAO = new AccountReportDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {

                // print customer report
                case "SearchBudgetPlan": {
                    List<State> s = AccountReportDAO.getListOfState();
                    request.setAttribute("state", s);
                    List<FunctionData> f = AccountReportDAO.getListOfFunctionData();
                    request.setAttribute("functionData", f);
                    List<CostCenter> c = AccountReportDAO.getListOfCostCenter();
                    request.setAttribute("costcenter", c);
                    List<NominalCode> n = AccountReportDAO.getListOfNominalCode();
                    request.setAttribute("nominalCode", n);
                    List<String> y = AccountReportDAO.getListOfBudgetYears();
                    request.setAttribute("years", y);
                    requestDispatcher = request.getRequestDispatcher("app/account/reports/SearchBudgetPlan.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "getFunctionByState": {
                    String stateid = request.getParameter("stateid").trim();

                    List<FunctionData> fdList = AccountReportDAO.getListOfFunctionDataBystate(Integer.parseInt(stateid));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;

                    for (FunctionData p : fdList) {
                        job1 = new JSONObject();
                        job1.put("id", p.getId());
                        job1.put("name", p.getName());
                        jar1.add(job1);
                    }
                    jOB.put("func", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                case "getCostCenterbyFunction": {
                    String functionid = request.getParameter("functionid").trim();

                    List<CostCenter> fdList = AccountReportDAO.getListOfCostCenterByFunctionData(Integer.parseInt(functionid));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;

                    for (CostCenter p : fdList) {
                        job1 = new JSONObject();
                        job1.put("id", p.getId());
                        job1.put("name", p.getName());
                        jar1.add(job1);
                    }
                    jOB.put("cc", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                case "getNominalByCostCenter": {
                    String ccid = request.getParameter("ccid").trim();

                    List<NominalCode> fdList = AccountReportDAO.getListOfNominalbyCostCenter(Integer.parseInt(ccid));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;

                    for (NominalCode p : fdList) {
                        job1 = new JSONObject();
                        job1.put("id", p.getId());
                        job1.put("name", p.getName());
                        jar1.add(job1);
                    }
                    jOB.put("cc", jar1);
                    response.getWriter().write(jOB.toString());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AccountReportDAOImpl;
import com.vertec.daoimpl.BudgetDAOImpl;
import com.vertec.daoimpl.ServiceDAOImpl;
import com.vertec.hibe.model.BudgetPlan;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.NominalCode;
import com.vertec.hibe.model.Service;
import com.vertec.hibe.model.State;
import com.vertec.hibe.model.SysUser;
import com.vertec.hibe.model.UpdateLog;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.util.Date;
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
 * @author User
 */
@WebServlet(name = "BudgetController", urlPatterns = {"/BudgetController"})
public class BudgetController extends HttpServlet {

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
    private final BudgetDAOImpl budgetdao = new BudgetDAOImpl();
    private final AccountReportDAOImpl reportdao = new AccountReportDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        HttpSession httpSession = request.getSession();
        SysUser user1 = (SysUser) httpSession.getAttribute("user");
        RequestDispatcher requestDispatcher;

        switch (action) {

            case "ViewBudgetPlan": {
                List<BudgetPlan> cuList = budgetdao.getListOfBudgetPlan();
                request.setAttribute("budget", cuList);

                List<State> state = reportdao.getListOfState();
                request.setAttribute("state", state);
                List<FunctionData> fd = reportdao.getListOfFunctionData();
                request.setAttribute("fd", fd);
                List<CostCenter> cs = reportdao.getListOfCostCenter();
                request.setAttribute("costcenter", cs);
                List<NominalCode> nc = reportdao.getListOfNominalCode();
                request.setAttribute("nominalcode", nc);
                requestDispatcher = request.getRequestDispatcher("app/account/BudgetPlan/BudgetPlan.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "getYearlyBudget": {
                String ncid = request.getParameter("ncid").trim();
                String year = request.getParameter("year").trim();

                List<BudgetPlan> fdList = budgetdao.getListOfYearlyBudgetPlan(Integer.parseInt(ncid), year);
                JSONObject jOB = new JSONObject();
                JSONArray jar1 = new JSONArray();
                JSONObject job1 = null;

                boolean bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("1")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("2")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("3")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("4")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("5")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("6")) {
                        System.out.println("Came IN **************************");
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("7")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("8")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("9")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("10")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("11")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                ////////////////////////////////////////////////////////////
                bool = true;
                job1 = new JSONObject();
                for (BudgetPlan p : fdList) {
                    if (p.getMonth().equals("12")) {
                        job1.put("amount", p.getAmount());
                        job1.put("isChanged", budgetdao.isUpdated(p));
                        job1.put("bpid", p.getId());
                        bool = false;
                    }
                }
                if (bool) {
                    job1.put("isChanged", false);
                    job1.put("amount", "");
                }
                jar1.add(job1);
                
                
                jOB.put("arr", jar1);
                response.getWriter().write(jOB.toString());
                break;
            }
            case "SaveBudgetPlan": {
                String ncid = request.getParameter("ncid").trim();
                String year = request.getParameter("year").trim();
                String amount = request.getParameter("amount").trim();
                String month = request.getParameter("month").trim();
                
                BudgetPlan bp = new BudgetPlan();
                bp.setAmount(Double.parseDouble(amount));
                bp.setNominalCodeId(new NominalCode(Integer.parseInt(ncid)));
                bp.setYear(year);
                bp.setDate(new Date());
                bp.setSysUserId(user1);
                bp.setMonth(month);
                String result=budgetdao.saveBudgetPlan(bp);
                response.getWriter().write(result);
                break;
            }
            case "UpdateBudgetPlan": {
                String ncid = request.getParameter("ncid").trim();
                String year = request.getParameter("year").trim();
                String amount = request.getParameter("amount").trim();
                String month = request.getParameter("month").trim();
                
                
                BudgetPlan old = budgetdao.getBudgetPlanById(Integer.parseInt(ncid), year, month);
                
                
                UpdateLog u = new UpdateLog();
                u.setDate(new Date());
                u.setBeforeUser(old.getSysUserId());
                u.setAfterUser(user1);
                u.setBeforeAmount(old.getAmount());
                u.setAfterAmount(Double.parseDouble(amount));
                u.setBudgetPlanId(old);
                String result1=budgetdao.saveUpdateLog(u);
                
                
                BudgetPlan bp = new BudgetPlan();
                bp.setAmount(Double.parseDouble(amount));
                bp.setNominalCodeId(new NominalCode(Integer.parseInt(ncid)));
                bp.setYear(year);
                bp.setDate(new Date());
                bp.setSysUserId(user1);
                bp.setMonth(month);
                String result=budgetdao.updateBudget(bp);
                
                
                response.getWriter().write(result);
                break;
            }
            
            
            
            case "ViewUpdateLog": {
                String bpid = request.getParameter("bpid").trim();
                BudgetPlan bp = budgetdao.getBudgetPlanById2(Integer.parseInt(bpid));
                
                
                request.setAttribute("bp", bp);
                
                
                List<UpdateLog> ul = budgetdao.getUpdateLog(bp);
                request.setAttribute("ul", ul);
                requestDispatcher = request.getRequestDispatcher("app/account/BudgetPlan/UpdateLog.jsp");
                requestDispatcher.forward(request, response);
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

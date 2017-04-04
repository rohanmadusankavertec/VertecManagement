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
import com.vertec.hibe.model.ActualCost;
import com.vertec.hibe.model.CostCenter;
import com.vertec.hibe.model.FunctionData;
import com.vertec.hibe.model.NominalCode;
import com.vertec.hibe.model.State;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
//                    List <Integer> yearList = new ArrayList<Integer>();
//                    for(int i=1990;)

                    List<Integer> ylist = new ArrayList<Integer>();
                    int nr= Calendar.getInstance().get(Calendar.YEAR);
//                    System.out.println("......current..."+nr);
                    for (int i = 2000; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
                        ylist.add(i);
                    }
                    
                    List<State> sList = statedao.loadAllState();
                    
                    request.setAttribute("state", sList);
                    request.setAttribute("year", ylist);
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
                    try {
                    String nominal = request.getParameter("nominalCode").trim();
                    String year = request.getParameter("year").trim();
                    String month = request.getParameter("month").trim();
                    String amt = request.getParameter("amount").trim();
                    String date = request.getParameter("date").trim();
                    String refere = request.getParameter("reference").trim();
                    String descrip = request.getParameter("descrip").trim();
//                    String nominal21 = request.getParameter("date").trim();
                    
                    ActualCost a = new ActualCost();
                    a.setYear(year);
                    a.setMonth(month);
                    a.setAmount(Double.parseDouble(amt));
                    
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        Date ndate = df.parse(date);
                        a.setDate(ndate);
                    
                    
                    a.setReferenceNo(refere);
                    a.setDescription(descrip);
                    a.setNominalCodeId(new NominalCode(Integer.parseInt(nominal)));
                    a.setSysUserId(user1);
                    
                    String result = actualcostdao.saveActualCost(a);
                    if (result.equals(VertecConstants.SUCCESS)) {
                        request.getSession().removeAttribute("Success_Message");

                        request.getSession().setAttribute("Success_Message", "Successfully Added");
                        response.sendRedirect("ActualCost?action=ActualCostPage");
                    } else {
                        request.getSession().removeAttribute("Error_Message");

                        request.getSession().setAttribute("Error_Message", "Not Added,Please Tri again");
                        response.sendRedirect("ActualCost?action=ActualCostPage");
                    }
                    } catch (Exception e) {
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

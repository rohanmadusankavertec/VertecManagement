/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.controller;

import com.vertec.daoimpl.EstimateCostDAOImpl;
import com.vertec.daoimpl.ProposalProjectDAOImpl;
import com.vertec.hibe.model.Employee;
import com.vertec.hibe.model.EstimateCost;
import com.vertec.hibe.model.ProjectProposal;
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
@WebServlet(name = "EstimateCostController", urlPatterns = {"/EstimateCostController"})
public class EstimateCostController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final EstimateCostDAOImpl estimatecostDAO = new EstimateCostDAOImpl();
    private final ProposalProjectDAOImpl proposalDAO = new ProposalProjectDAOImpl();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;
        
        switch (action) {
            
            // get employee
            case "getEmployees": {
                    //System.out.println("hhhhhhhhhhhhhhhhhhhhh");
                    List<Employee> e = estimatecostDAO.loadAllEmployees();
                     System.out.println("hhhhhhhhhhhhhhhhhhhhh");
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (Employee d : e) {
                        System.out.println(d.getFirstName());
                        job1 = new JSONObject();
                        job1.put("id", d.getId());
                        job1.put("firstName", d.getFirstName());
                        job1.put("lastName", d.getLastName());
                        job1.put("salary", d.getSalary());
                        
                        jar1.add(job1);
                    }
                    jOB.put("employee", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
            // save estimate cost
            case "saveEstimateCost": {
                    String ec = request.getParameter("ecost");
                    String mc = request.getParameter("mcost");
                    String othc = request.getParameter("ocost");
                    String tdc = request.getParameter("totdc");
                    String tec = request.getParameter("totec");
                    String pid = request.getParameter("ppid");

//                    String[] featurearr = feature.split(",");

                    EstimateCost e = new EstimateCost();
                    e.setElectricityCost(Double.parseDouble(ec));
                    e.setManagementCost(Double.parseDouble(mc));
                    e.setOtherCost(Double.parseDouble(othc));
                    e.setEstimateCost(Double.parseDouble(tec));
                    e.setDeveloperCost(Double.parseDouble(tdc));
                    e.setCalculatedBy(user1);
                    e.setProjectProposalId(new ProjectProposal(Integer.parseInt(pid)));
                    
                    String result = estimatecostDAO.saveEstimateCost(e);
                    
//                    for (String string : featurearr) {
//                        PackageHasFeatures phf = new PackageHasFeatures();
//                        phf.setPackageId(p);
//                        phf.setFeaturesId(new Features(Integer.parseInt(string)));
//                        quotationDAO.savePackageHasFeature(phf);
//                    }
                    out.write(result);
                    

              
                    break;
                }
            
            // load add estimete cost page
            case "addestimatecost": {
                    List<Employee> e = estimatecostDAO.loadAllEmployees();
                    request.setAttribute("employee", e);
                    List<ProjectProposal> p = proposalDAO.loadAllProject();
                    request.setAttribute("proposal", p);
                    
                    requestDispatcher = request.getRequestDispatcher("app/estimateCost/AddEstimateCost.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
            // view all estimate cost
            case "loadEstimatecost": {
                List<EstimateCost> esList = estimatecostDAO.loadAllEstimateCost();
                request.setAttribute("estimate", esList);
                requestDispatcher = request.getRequestDispatcher("app/estimateCost/EstimateCostDetails.jsp");
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

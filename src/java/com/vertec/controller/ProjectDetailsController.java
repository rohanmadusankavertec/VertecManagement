/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.EmployeeDAOImpl;
import com.vertec.daoimpl.ProjectDetailsDAOImpl;
import com.vertec.daoimpl.ProposalProjectDAOImpl;
import com.vertec.daoimpl.StatusDAOImpl;
import com.vertec.hibe.model.Employee;
import com.vertec.hibe.model.ProjectDetails;
import com.vertec.hibe.model.ProjectHasEmployee;
import com.vertec.hibe.model.ProjectProcess;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Status;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "ProjectDetailsController", urlPatterns = {"/ProjectDetailsController"})
public class ProjectDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    private final ProposalProjectDAOImpl ProjectProposalDAO = new ProposalProjectDAOImpl();
    private final StatusDAOImpl statusDAO = new StatusDAOImpl();
    private final ProjectDetailsDAOImpl ProjectDetailsDAO = new ProjectDetailsDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {

                case "loadProjectName": {
                    List<ProjectProposal> nameList = ProjectProposalDAO.loadAllProject();
                    request.setAttribute("pNameList", nameList);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/UpdateProjectDetails.jsp");
                    requestDispatcher.forward(request, response);

                    break;

                }
                
                // load project details 
                case "loadEmpStatusProjectName": {
//                    System.out.println("1");
                    List<Employee> empList = employeeDAO.getListOfEmployee();
                    request.setAttribute("empList", empList);
//                    System.out.println("2");
                    List<ProjectProposal> proposalList = ProjectProposalDAO.loadProjectHasNotDetails();
                    request.setAttribute("proposalList", proposalList);
//                    System.out.println("3");
                    List<Status> statusList = statusDAO.loadAllStatus();
                    request.setAttribute("statusList", statusList);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/AddProjectDetails.jsp");
                    requestDispatcher.forward(request, response);

                    break;

                }

                // load employee details
                case "loadEmployeeTB": {

                    System.out.println("hhhhhhhhhhhhhhhhhhhhh");
                    List<Employee> empList = employeeDAO.getListOfEmployee();
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (Employee d : empList) {
                        System.out.println(d.getFirstName());
                        job1 = new JSONObject();
                        job1.put("id", d.getId());
                        job1.put("firstName", d.getFirstName());
                        job1.put("lastName", d.getLastName());

                        jar1.add(job1);
                    }
                    jOB.put("employee", jar1);
                    response.getWriter().write(jOB.toString());

                }

                // save details
                case "saveDetails": {

                    String employeeList = request.getParameter("EmpList").trim();

                    String proposalId = request.getParameter("proDId").trim();

                    String fromD = request.getParameter("fdate").trim();

                    String toD = request.getParameter("tdate").trim();

                    String status = request.getParameter("status").trim();

                    String remark = request.getParameter("remark").trim();

                    String result = "";
                    String result1 = "";
                    try {
                        ProjectDetails pd = new ProjectDetails();
                        pd.setAddedBy(user1);
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

                        pd.setFromDate(sd.parse(fromD));
                        pd.setToDate(sd.parse(toD));
                        pd.setProjectProposalId(new ProjectProposal(Integer.parseInt(proposalId)));
                        pd.setStatusId(new Status(Integer.parseInt(status)));
                        pd.setRemark(remark);

                        result = ProjectDetailsDAO.saveProjectDetails(pd);
                        String[] pArr = employeeList.split(",");
                        for (int i = 0; i < pArr.length; i++) {
                            ProjectProcess pros = new ProjectProcess();
                            ProjectHasEmployee ph = new ProjectHasEmployee();
                            ph.setFromDate(sd.parse(pArr[i]));
                            ph.setToDate(sd.parse(pArr[i + 1]));
                            ph.setEmployeeId(new Employee(Integer.parseInt(pArr[i + 2])));
                            ph.setProjectDetailsId(pd);
                            i += 2;
                            result1 = ProjectDetailsDAO.saveProjectHasEmployee(ph);
                        }
                        if (result.equals(VertecConstants.SUCCESS) && result1.equals(VertecConstants.SUCCESS)) {
                            out.write(VertecConstants.SUCCESS);
                        } else {
                            out.write(VertecConstants.ERROR);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                }

                // view project details
                case "ViewProDetails": {

                    List<Employee> empList = employeeDAO.getListOfEmployee();
                    request.setAttribute("empList", empList);

                    List<Status> statusList = statusDAO.loadAllStatus();
                    request.setAttribute("statusList", statusList);
                    List<ProjectProposal> proposalList = ProjectProposalDAO.loadUpdateProject();
                    request.setAttribute("proposalList", proposalList);
                    requestDispatcher = request.getRequestDispatcher("app/projectDetails/UpdateProjectDetails.jsp");
                    requestDispatcher.forward(request, response);
//                            List<ProjectDetails> prodetails = projectDeDAO.loadAllProjectDetails();
//                            request.setAttribute("projectDetails", prodetails);
//                            requestDispatcher = request.getRequestDispatcher("app/projectDetails/ProjectProcessDetails.jsp");
//                            requestDispatcher.forward(request, response);

                    break;
                }
                
                // load project details to update
                case "loadEmpStatus": {
                    String para = request.getParameter("proNameId").trim();
                    ProjectDetails d = ProjectDetailsDAO.viewProjectDetails(Integer.parseInt(para));

                    JSONObject jOB = new JSONObject();

                    if (d != null) {

                        jOB.put("fmdate", d.getFromDate().toString());
                        jOB.put("todate", d.getToDate().toString());
                        jOB.put("rmk", d.getRemark());
                        jOB.put("stt", d.getStatusId().getId());

                    }

                    response.getWriter().write(jOB.toString());

                    break;
                }
                // load employee to table
                case "LoadEmpTable": {
                    String para = request.getParameter("proNameId").trim();

                    List<ProjectHasEmployee> eList = ProjectDetailsDAO.viewEmployeeOfProject(Integer.parseInt(para));
//                    System.out.println("llllllll");
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (ProjectHasEmployee d : eList) {
                        System.out.println("22222");
                        System.out.println(d.getFromDate());
                        job1 = new JSONObject();
                        job1.put("id", d.getId());
                        job1.put("fdate", d.getFromDate());
                        job1.put("todate", d.getToDate());
                        job1.put("firstName", d.getEmployeeId().getFirstName());
                        job1.put("lastName", d.getEmployeeId().getLastName());

                        jar1.add(job1);
                    }
                    jOB.put("employee", jar1);
                    response.getWriter().write(jOB.toString());
                    break;

                }

                // update details
                case "UpdateProjectDetails": {

                    String proposalId = request.getParameter("proDId").trim();

                    String fromD = request.getParameter("fdate").trim();

                    String toD = request.getParameter("tdate").trim();

                    String status = request.getParameter("status").trim();

                    String remark = request.getParameter("remark").trim();

                    String employeeList = request.getParameter("EmpList").trim();

                    String result = "";

                    try {
                        ProjectDetails pd = new ProjectDetails();

                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

                        pd.setFromDate(sd.parse(fromD));
                        pd.setToDate(sd.parse(toD));
                        pd.setProjectProposalId(new ProjectProposal(Integer.parseInt(proposalId)));
                        pd.setStatusId(new Status(Integer.parseInt(status)));
                        pd.setRemark(remark);

                        result = ProjectDetailsDAO.updateProjectDetails(pd);
//                        
                        if (result.equals(VertecConstants.UPDATED)) {
//                            out.write(VertecConstants.SUCCESS);

//    ------------------------------------------------------------
//                        String pDetailsId = request.getParameter("proNameId").trim();
                            System.out.println(employeeList);
                            System.out.println(proposalId);

                            String[] pArr = employeeList.split(",");
//                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                            String result1 = "";

                            String s = ProjectDetailsDAO.DeleteEmployeeByDetailsId(Integer.parseInt(proposalId));
                            System.out.println(s);
                            if (s != null) {
                                try {
                                    for (int i = 0; i < pArr.length; i++) {

                                        ProjectHasEmployee ph = new ProjectHasEmployee();

                                        System.out.println("From Date : " + pArr[i + 1]);
                                        System.out.println("To Date : " + pArr[i + 2]);
                                        System.out.println("EID : " + pArr[i]);

                                        ph.setFromDate(sd.parse(pArr[i + 1]));
                                        ph.setToDate(sd.parse(pArr[i + 2]));
                                        ph.setEmployeeId(new Employee(Integer.parseInt(pArr[i])));
                                        ph.setProjectDetailsId(new ProjectDetails(ProjectDetailsDAO.viewPdIdByProposalId(Integer.parseInt(proposalId))));
                                        i += 2;
                                        result1 = ProjectDetailsDAO.saveProjectHasEmployee(ph);
                                    }
                                    if (result.equals(VertecConstants.SUCCESS)) {
//                                    out.write(VertecConstants.SUCCESS);
                                    } else {
//                                    out.write(VertecConstants.ERROR);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
//    ------------------------------------------------------------

                            if (result.equals(VertecConstants.SUCCESS) & result1.equals(VertecConstants.SUCCESS)) {
                                out.write(VertecConstants.SUCCESS);
                            } else {
                                out.write(VertecConstants.ERROR);
                            }

                        } else {
                            out.write(VertecConstants.ERROR);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                }

                // load employee according to the project
                case "GetEmployees": {
//                    System.out.println("5");
                    String p = request.getParameter("proNameId").trim();
//                    System.out.println(p + "hhhhhhhh");
                    JSONObject job = ProjectDetailsDAO.loadEmployee(Integer.parseInt(p));
                    response.getWriter().write(job.toString());
                    break;
                }

                // update employee details
                case "updateEmployee": {
                    String employeeList = request.getParameter("EmpList").trim();
                    String pDetailsId = request.getParameter("proNameId").trim();

                    System.out.println(employeeList);
                    System.out.println(pDetailsId);

                    String[] pArr = employeeList.split(",");
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                    String result = "";

                    String s = ProjectDetailsDAO.DeleteEmployeeByDetailsId(Integer.parseInt(pDetailsId));
                    if (s != null) {
                        try {
                            for (int i = 0; i < pArr.length; i++) {

                                ProjectHasEmployee ph = new ProjectHasEmployee();
                                ph.setFromDate(sd.parse(pArr[i + 1]));
                                ph.setToDate(sd.parse(pArr[i + 2]));
                                ph.setEmployeeId(new Employee(Integer.parseInt(pArr[i + 3])));
                                ph.setProjectDetailsId(new ProjectDetails(Integer.parseInt(pDetailsId)));
                                i += 2;
                                result = ProjectDetailsDAO.saveProjectHasEmployee(ph);
                            }
                            if (result.equals(VertecConstants.SUCCESS)) {
                                out.write(VertecConstants.SUCCESS);
                            } else {
                                out.write(VertecConstants.ERROR);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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

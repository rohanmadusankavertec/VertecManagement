/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AgreementDAOImpl;
import com.vertec.daoimpl.CctvDAOImpl;
import com.vertec.daoimpl.CustomerDAOImpl;
import com.vertec.daoimpl.QuotationDAOImpl;
import com.vertec.hibe.model.CctvCategory;
import com.vertec.hibe.model.CctvItems;
import com.vertec.hibe.model.CctvQuotationInfo;
import com.vertec.hibe.model.CctvQuotationItems;
import com.vertec.hibe.model.CctvWarranty;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Features;
import com.vertec.hibe.model.HardwareItem;
import com.vertec.hibe.model.HardwareQuotation;
import com.vertec.hibe.model.Installment;
import com.vertec.hibe.model.PackageHasFeatures;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Quotation;
import com.vertec.hibe.model.QuotationHasFeatures;
import com.vertec.hibe.model.QuotationHasPackages;
import com.vertec.hibe.model.QuotationStatus;
import com.vertec.hibe.model.Service;
import com.vertec.hibe.model.SoftwareAdvanceDetails;
import com.vertec.hibe.model.SoftwareQuotation;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author vertec-r
 */
@WebServlet(name = "QuotationController", urlPatterns = {"/QuotationController"})
public class QuotationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final AgreementDAOImpl AgreementDAO = new AgreementDAOImpl();
    private final QuotationDAOImpl quotationDAO = new QuotationDAOImpl();
    private final CustomerDAOImpl customerdao = new CustomerDAOImpl();
    private final CctvDAOImpl cctvDAO = new CctvDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {
                case "addpackages": {// load add packge page
                    List<Service> s = AgreementDAO.loadAllServises();
                    request.setAttribute("service", s);
                    List<Features> f = quotationDAO.loadAllFeatures();
                    request.setAttribute("feature", f);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/addPackage.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // get all features
                case "getFeatures": {
                    List<Features> f = quotationDAO.loadAllFeatures();
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (Features d : f) {
                        job1 = new JSONObject();
                        job1.put("id", d.getId());
                        job1.put("feature", d.getFeature());
                        jar1.add(job1);
                    }
                    jOB.put("feature", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                // get the features according to the package
                case "getPackageFeatures": {
                    String pkg = request.getParameter("package").trim();

                    List<PackageHasFeatures> f = quotationDAO.loadPackageFeatures(Integer.parseInt(pkg));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (PackageHasFeatures d : f) {
                        job1 = new JSONObject();
                        job1.put("id", d.getFeaturesId().getId());
                        job1.put("feature", d.getFeaturesId().getFeature());
                        jar1.add(job1);
                    }
                    jOB.put("feature", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                //add new a feature
                case "addFeature": {
                    String feature = request.getParameter("name");

                    Features f = new Features();
                    f.setFeature(feature);

                    String result = quotationDAO.saveFeature(f);

                    out.write(result);

                    break;
                }
                // save new package
                case "savePackage": {
                    String service = request.getParameter("service");
                    String name = request.getParameter("package");
                    String price = request.getParameter("price");
                    String feature = request.getParameter("features");

                    String[] featurearr = feature.split(",");

                    com.vertec.hibe.model.Package p = new com.vertec.hibe.model.Package();
                    p.setName(name);
                    p.setPrice(Double.parseDouble(price));
                    p.setServiceId(new Service(Integer.parseInt(service)));
                    p.setIsValid(true);
                    String result = quotationDAO.savePackage(p);
                    for (String string : featurearr) {
                        PackageHasFeatures phf = new PackageHasFeatures();
                        phf.setPackageId(p);
                        phf.setFeaturesId(new Features(Integer.parseInt(string)));
                        quotationDAO.savePackageHasFeature(phf);
                    }
                    out.write(result);
                    break;
                }
                // search package
                case "SearchPackage": {
                    List<Service> s = AgreementDAO.loadAllServises();
                    request.setAttribute("service", s);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/SearchPackages.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view packages
                case "ViewPackage": {
                    String type = request.getParameter("type").trim();
                    String service = request.getParameter("service").trim();

                    int ser = 0;
                    if (!service.equals("")) {
                        ser = Integer.parseInt(service);
                    }

                    List<com.vertec.hibe.model.Package> s = quotationDAO.loadPackage(type, ser);
                    request.setAttribute("package", s);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/ViewPackages.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                //get features according the package
                case "GetPkgFeaturess": {
                    String p = request.getParameter("package").trim();
                    String result = quotationDAO.loadPackages(Integer.parseInt(p));
                    out.write(result);
                    break;
                }
                //remove package
                case "deletePackage": {
                    String p = request.getParameter("id").trim();
                    com.vertec.hibe.model.Package pkg = new com.vertec.hibe.model.Package();
                    pkg.setId(Integer.parseInt(p));
                    String result = quotationDAO.DeletePackage(pkg);
                    out.write(result);
                    break;
                }
                // load packages to update
                case "toUpdatePackage": {
                    String id = request.getParameter("pid").trim();
                    System.out.println("Package ID : " + id);
                    List<Service> s = AgreementDAO.loadAllServises();
                    request.setAttribute("service", s);
                    com.vertec.hibe.model.Package p = quotationDAO.LoadPackageByID(Integer.parseInt(id));
                    request.setAttribute("package", p);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/PackageUpdate.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // update the changed data
                case "UpdatePackage": {
                    String id = request.getParameter("id");
                    String service = request.getParameter("service");
                    String name = request.getParameter("package");
                    String price = request.getParameter("price");
                    String feature = request.getParameter("features");
                    System.out.println(feature);

                    String[] featurearr = feature.split(",");

                    com.vertec.hibe.model.Package p = new com.vertec.hibe.model.Package();
                    p.setId(Integer.parseInt(id));
                    p.setName(name);
                    p.setPrice(Double.parseDouble(price));
                    p.setServiceId(new Service(Integer.parseInt(service)));
                    String result = quotationDAO.UpdatePackage(p);
                    
                    List<PackageHasFeatures> list = quotationDAO.loadPackageFeatures(Integer.parseInt(id));
//                    System.out.println(".....................");
                    for (PackageHasFeatures pf : list) {
                        System.out.println("....................."+pf.getId());
                        String result23 = quotationDAO.DeletePackageFeatures(pf.getId());
                        
                        System.out.println("...."+result23);
                    }
                    
                    
                    
                    for (String string : featurearr) {
                        PackageHasFeatures phf = new PackageHasFeatures();
                        phf.setPackageId(p);
                        phf.setFeaturesId(new Features(Integer.parseInt(string)));
                        quotationDAO.savePackageHasFeature(phf);
                    }
                    out.write(result);
                    break;
                }
                //load create quotation page
                case "createQuotation": {
//                    List<Customer> cuList = customerdao.getListOfCustomer();
//                    request.setAttribute("customer", cuList);
                    List<ProjectProposal> pp = quotationDAO.loadProjectProposals();
                    request.setAttribute("proposal", pp);
                    List<com.vertec.hibe.model.Package> p = quotationDAO.loadPackage("0", 0);
                    request.setAttribute("packages", p);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/CreateQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                
                // load software quotation page
                
                case "SoftwareQuotation": {

                    List<ProjectProposal> pp = quotationDAO.loadProjectProposals();
                    request.setAttribute("proposal", pp);
                    List<com.vertec.hibe.model.Package> p = quotationDAO.loadPackage("0", 0);
                    request.setAttribute("packages", p);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/SoftwareQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                
                // load the features in package
                case "getPackFeatures": {
                    String pkg = request.getParameter("package").trim();
                    com.vertec.hibe.model.Package p = quotationDAO.getPackagebyID(Integer.parseInt(pkg));
                    List<PackageHasFeatures> f = quotationDAO.loadPackageFeatures(Integer.parseInt(pkg));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (PackageHasFeatures d : f) {
                        job1 = new JSONObject();
                        job1.put("id", d.getFeaturesId().getId());
                        job1.put("feature", d.getFeaturesId().getFeature());
                        jar1.add(job1);
                    }
                    jOB.put("feature", jar1);
                    jOB.put("amount", p.getPrice());
                    response.getWriter().write(jOB.toString());
                    break;
                }
// -------------------------------------------------------------------------------------------------               
                case "getPackFeatures1": {
                    String pkg = request.getParameter("package").trim();
                    
                    List<PackageHasFeatures> f = quotationDAO.loadPackageFeatures(Integer.parseInt(pkg));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (PackageHasFeatures d : f) {
                        System.out.println(d.getFeaturesId().getFeature());
                        job1 = new JSONObject();
                        job1.put("id", d.getFeaturesId().getId());
                        job1.put("feature", d.getFeaturesId().getFeature());
                        jar1.add(job1);
                    }
                    jOB.put("feature", jar1);
                    
                    response.getWriter().write(jOB.toString());
                    break;
                }
// -------------------------------------------------------------------------------------------------------------               
                // save quotation
                case "SaveQuotation": {
                    String ProjectProposal = request.getParameter("customer");
                    String packagename = request.getParameter("package");
                    String price = request.getParameter("amount");
                    String feature = request.getParameter("features");
                    String des = request.getParameter("descrip");
                    String indes = request.getParameter("indes");
                    String inamount = request.getParameter("inamount");
                    System.out.println(inamount+"<<<<<<<<<<<<<<<<<<<<<,");

                    String[] featurearr = feature.split(",");
                    String[] desarr = des.split(",");
                    String[] indesarr = indes.split(",");
                    String[] inamountarr = inamount.split(",");

                    System.out.println("lenght"+featurearr.length);
                    Quotation q = new Quotation();
                    q.setProjectProposalId(new ProjectProposal(Integer.parseInt(ProjectProposal)));
                    q.setAmount(Double.parseDouble(price));
                    q.setCreatedBy(user1);
                    q.setDate(new Date());
                    q.setPackageId(new com.vertec.hibe.model.Package(Integer.parseInt(packagename)));
                    q.setQuotationStatusId(new QuotationStatus(1));
                   
                    String result = quotationDAO.saveQuotation(q);
                    System.out.println("lllll"+result);
                    for (int i =0; i<featurearr.length; i++){
                        QuotationHasFeatures qhf = new QuotationHasFeatures();
                        qhf.setQuotationId(q);
                        qhf.setFeaturesId(new Features(Integer.parseInt(featurearr[i])));
                        qhf.setDescription(desarr[i]);
                        quotationDAO.saveQuotationHasFeature(qhf);
                    }
//                    for (String string : featurearr) {
//                        QuotationHasFeatures qhf = new QuotationHasFeatures();
//                        qhf.setQuotationId(q);
//                        qhf.setFeaturesId(new Features(Integer.parseInt(string)));
//                        quotationDAO.saveQuotationHasFeature(qhf);
//                    }
                    for (int i = 0; i < indesarr.length; i++) {
                        Installment ins = new Installment();
                        System.out.println("data................."+inamountarr[i]);
                        ins.setAmount(Double.valueOf(inamountarr[i]));
                        ins.setDescription(indesarr[i]);
                        ins.setQuotationId(q);
                        quotationDAO.saveInstallments(ins);
                    }
                    out.write(result);
                    break;
                }
                //
                case "SaveQuotation1": {
                    String ProjectProposal = request.getParameter("customer");
                    String packagename = request.getParameter("package");
                    String tot = request.getParameter("amount");
                    String feature = request.getParameter("features");
                    
                    String indes = request.getParameter("indes");
                    String qt = request.getParameter("qty");
                    String inamount = request.getParameter("inamount");
                    
                    //----------------------------------------------------------
                    String ftpat = request.getParameter("fspay");
                    String sdpay = request.getParameter("sdpay");
                    String tdpay = request.getParameter("tdpay");
                    String install = request.getParameter("installment");
                    String maintenance = request.getParameter("maintenance");
                    String extra = request.getParameter("extra");
                    String prepare = request.getParameter("prepareby");
                    String design = request.getParameter("designation");
                   
                    System.out.println("....ftpat......."+ftpat);
                    System.out.println("....sdpay......."+sdpay);
                    System.out.println("....tdpay......."+tdpay);
                    System.out.println("....install......."+install);
                    System.out.println("....maintenance......."+maintenance);
                    System.out.println("....extra......."+extra);
                    System.out.println("....prepare......."+prepare);
                    System.out.println("....design......."+design);
                    
                    //----------------------------------------------------------

                    String[] featurearr = feature.split(",");
                    String[] parr = packagename.split(",");
                    
                    String[] indesarr = indes.split(",");
                    String[] inamountarr = inamount.split(",");
                    String[] qtyarr = qt.split(",");

                    System.out.println("lenght"+featurearr.length);
                    Quotation q = new Quotation();
                    q.setProjectProposalId(new ProjectProposal(Integer.parseInt(ProjectProposal)));
                    q.setAmount(1.0);
                    q.setCreatedBy(user1);
                    q.setDate(new Date());
                    q.setQuotationStatusId(new QuotationStatus(1));
//                    q.setPackageId(new com.vertec.hibe.model.Package(Integer.parseInt(packagename)));
                    
                    
                        q.setPackageId(new com.vertec.hibe.model.Package(1));
                    
                    String result = quotationDAO.saveQuotation(q);
//                    int phf = quotationDAO.getpackHasFeatureId(0, 0);
                    SoftwareAdvanceDetails s = new SoftwareAdvanceDetails();
                    s.setFirstPayment(Double.parseDouble(ftpat));
                    s.setSecondPayment(Double.parseDouble(sdpay));
                    s.setThirdPayment(Double.parseDouble(tdpay));
                    s.setInstallment(Double.parseDouble(install));
                    s.setMaintenace(Double.parseDouble(maintenance));
                    s.setPlusMaintenace(Double.parseDouble(extra));
                    s.setPreparedBy(prepare);
                    s.setDesignatioa(design);
                    s.setQuotationId(q);
                    String result1 = quotationDAO.saveSoftwareAdvanceDetails(s);
                    System.out.println("....saveSoftwareAdvanceDetails......."+result1);
                    
                    System.out.println("lllll"+result);
                    for (int i =0; i<featurearr.length; i++){
                        
                        int phf = quotationDAO.getpackHasFeatureId(Integer.parseInt(parr[i]), Integer.parseInt(featurearr[i]));
                        QuotationHasPackages qhp = new QuotationHasPackages();
                        qhp.setPackageHasFeaturesId(new PackageHasFeatures(phf));
                        qhp.setQuotationId(q);
                        
                        
                        quotationDAO.saveQuotationHasPackage(qhp);
                    }
//                    for (String string : featurearr) {
//                        QuotationHasFeatures qhf = new QuotationHasFeatures();
//                        qhf.setQuotationId(q);
//                        qhf.setFeaturesId(new Features(Integer.parseInt(string)));
//                        quotationDAO.saveQuotationHasFeature(qhf);
//                    }
                    for (int i = 0; i < indesarr.length; i++) {
                        SoftwareQuotation sq = new SoftwareQuotation();
                        sq.setDescription(indesarr[i]);
                        sq.setPrice(Double.valueOf(inamountarr[i]));
                        sq.setQty(Integer.valueOf(qtyarr[i]));
                        sq.setQuotationId(q);
                        
                        quotationDAO.saveSoftwareQuotation(sq);
                    }
                    out.write(result);
                    break;
                }
                // view all quotation
//                case "ViewQuotation": {
//                    List<Quotation> qt = quotationDAO.loadAllQuotation();
//                    request.setAttribute("quotation", qt);
//                    requestDispatcher = request.getRequestDispatcher("app/quotation/ViewQuotation.jsp");
//                    requestDispatcher.forward(request, response);
//                    break;
//                }
//                case "ViewQuotation": {
//                    List<Quotation> qt = quotationDAO.loadAllQuotation();
//                    request.setAttribute("quotation", qt);
//                    requestDispatcher = request.getRequestDispatcher("app/quotation/ViewQuotation.jsp");
//                    requestDispatcher.forward(request, response);
//                    break;
//                }
                case "Template": {
                    requestDispatcher = request.getRequestDispatcher("app/quotation/QuotationTemplateSoftware.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // get the packages of a project
                case "getPackagesForProject": {
                    String project = request.getParameter("project");
                    System.out.println("aaaaa1"+project);
                    List<com.vertec.hibe.model.Package> f = quotationDAO.loadPackagesForProject(Integer.parseInt(project));
                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (com.vertec.hibe.model.Package d : f) {
                        job1 = new JSONObject();
                        job1.put("id", d.getId());
                        job1.put("name", d.getName());
                        job1.put("price", d.getPrice());
                        jar1.add(job1);
                    }
                    jOB.put("feature", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }

//---------------------------------------- print quotation ------------------------------------------------------------------------------- 
                // view web quotation
                case "viewWebsite": {
                    String qid = request.getParameter("hidden");
                    String sID = request.getParameter("serviceId");
                    System.out.println("Service ID"+sID);
                    System.out.println("Quotation ID"+qid);
                    
                    int sid = Integer.parseInt(sID);
                    List<QuotationHasFeatures> list = quotationDAO.getWebsite(Integer.parseInt(qid));
                    request.setAttribute("qhfs", list);
                    List<Installment> inslist = quotationDAO.getInstallment(Integer.parseInt(qid));
                    request.setAttribute("ins", inslist);
                    Quotation q = quotationDAO.LoadProjectByQuotation(Integer.parseInt(qid));
                    request.setAttribute("quo", q);
                    List<Service> serlist = quotationDAO.getServices();
                    request.setAttribute("service", serlist);
                    List<SoftwareQuotation> soList = quotationDAO.getSoftware(Integer.parseInt(qid));
                    request.setAttribute("softList", soList);
                    List<QuotationHasPackages> pList = quotationDAO.getSoftwarepackageDetails(Integer.parseInt(qid));
                    request.setAttribute("pList", pList);
                    List<QuotationHasPackages> qpList = quotationDAO.getSoftwarepackageDetails1(Integer.parseInt(qid));
                    request.setAttribute("qpList", qpList);
//                    System.out.println("8");
                    
                    
                     if(sid == 1){
//                         String totAmt = quotationDAO.getTotAmount(Integer.parseInt(qid));
//                    System.out.println("9");
//                    request.setAttribute("tAmt", totAmt);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/Software.jsp");
                        requestDispatcher.forward(request, response);
                    }else if(sid == 2){
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/WebSite.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    else if(sid == 3){
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/WebSite.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    else if(sid == 4){
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/Promotion.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    else if(sid == 5){
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/WebSite.jsp");
                        requestDispatcher.forward(request, response);
                    }else if(sid == 6){
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/WebSite.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    
                    
                    
                    break;
                }
//                
                // get the features of pckages
                case "viewFeatures": {
//                    System.out.println("call to featuressssss");
                    String ins = request.getParameter("hidden");
//                    System.out.println("mmmmmmmmmm"+ins);
                    List<PackageHasFeatures> pList = quotationDAO.getfeatures(Integer.parseInt(ins));
                    
                    JSONObject JOB = new JSONObject();
                    JSONArray jar = new JSONArray();
                    JSONObject job = null;
                    
                    for(PackageHasFeatures p: pList){
                        job = new JSONObject();
                        job.put("name", p.getFeaturesId().getFeature());
                        job.put("id", p.getFeaturesId().getId());
                        
                        jar.add(job);
                        
                    }
                    JOB.put("feature", jar);
                    response.getWriter().write(JOB.toString());
                    break;
                }
                
//  ------------------Hardware quotation------------------------------------------------------------
                // save hardware quotation
                case "saveHardwareQuotation": {
                    String des = request.getParameter("dscrip");
                    String qty = request.getParameter("qtyy");
                    String price = request.getParameter("prc");
                    String cid = request.getParameter("customer");
                    
                    String[] decarr = des.split(",");
                    String[] qtyarr = qty.split(",");
                    String[] pricearr = price.split(",");
                    
                    double tot =0;
                    
                    for(int i =0; i<decarr.length; i++){
                        double c = Double.parseDouble(qtyarr[i]);
                        double p = Double.parseDouble(pricearr[i]);
                        tot += c*p;
                        
                    }
                    
                    
                    
                    String result = "";
                    HardwareQuotation hq = new HardwareQuotation();
                    hq.setDate(new Date());
                    hq.setAmount(tot);
                    hq.setProjectProposalId(new ProjectProposal(Integer.parseInt(cid)));
                    hq.setQuotationStatusId(new QuotationStatus(1));
                    String result1 = quotationDAO.saveHardwareQuotation(hq);
                    System.out.println("......save hardware..."+result1);
                    
                    for(int i =0; i<decarr.length; i++){
                        HardwareItem hi = new HardwareItem();
                        hi.setDescription(decarr[i]);
                        hi.setQty(Integer.parseInt(qtyarr[i]));
                        hi.setPrice(Double.parseDouble(pricearr[i]));
                        
                        hi.setHardwareId(hq);
                        
                        result = quotationDAO.saveHardwareItem(hi);
                        
                    }
                    
                    out.write(result1);
                    break;
                }
                
                // load hardware quotation page
//                case "viewHardware": {
//                    List<Customer> customer = quotationDAO.loadCustomer();
//                    request.setAttribute("cus", customer);
//                    requestDispatcher = request.getRequestDispatcher("app/quotation/HardwareQuotation.jsp");
//                    requestDispatcher.forward(request, response);
//                    break;
//                }
                // load all hardware quotation
//                case "viewHardwareQuotation": {
//                    List<HardwareQuotation> hqList = quotationDAO.loadHardwareQuotation();
//                    request.setAttribute("hqList", hqList);
//                    requestDispatcher = request.getRequestDispatcher("app/quotation/viewHardware.jsp");
//                    requestDispatcher.forward(request, response);
//                    break;
//                }
                // print the quotation
                case "viewHardwareItem": {
                    String hitem = request.getParameter("hidden");
                    List<HardwareItem> hiList = quotationDAO.loadHardwareItem(Integer.parseInt(hitem));
                    request.setAttribute("hiList", hiList);
                    HardwareQuotation hQuotation = quotationDAO.loadHardwareQuotation(Integer.parseInt(hitem));
                    request.setAttribute("hquotation", hQuotation);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/design/Hardware.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                
//---------------------------cctv------------------------------------------------------------------------
                // load cctv quotation page
                case "loadCctvPage": {
                    List<CctvCategory> category = cctvDAO.loadItemCategory();
                    request.setAttribute("category", category);
                    List<Customer> customer = quotationDAO.loadCustomer();
                    request.setAttribute("customer", customer);
                    
                    requestDispatcher = request.getRequestDispatcher("app/quotation/cctvQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                    
                }
                // load cctv items in catogery
                case "loadItemBycategory": {
                    String hitem = request.getParameter("categoryId");
                    
                    List<CctvItems> itemList = cctvDAO.viewItemByCategoryId(Integer.parseInt(hitem));
                    
                    JSONObject JOB = new JSONObject();
                    JSONArray jar = new JSONArray();
                    JSONObject job = null;
                    
                    for (CctvItems c : itemList) {
                        job = new JSONObject();
                        
                        job.put("id", c.getId());
                        job.put("name", c.getName());
                        job.put("price", c.getPrice());
                        job.put("category", c.getCctvCategoryId().getCategory());
                        
                        jar.add(job);
                    }
                    JOB.put("items", jar);
                    response.getWriter().write(JOB.toString());
                    break;
                }
                
                // sava cctv quotation
                case "saveCctvQuotation": {
                    
                    String item = request.getParameter("items");
                    String qty = request.getParameter("qty");
                    String tot = request.getParameter("qty");
                    String cid = request.getParameter("customer");
                    String grand = request.getParameter("grand");
// ---------------------------------------------------------------------------------  
                    String camera = request.getParameter("camera");
                    String dvr = request.getParameter("dvr");
                    String disk = request.getParameter("hardDisk");
                    String monitor = request.getParameter("monitor");
                    String install = request.getParameter("installation");
                    String cable = request.getParameter("cable");
                    String fst = request.getParameter("fst");
                    String snd = request.getParameter("snd");
                    String trd = request.getParameter("trd");
                    String prepare = request.getParameter("prepare");
                    String designation = request.getParameter("designation");
//                    System.out.println("cameara..."+camera);
//                    System.out.println("dvr..."+dvr);
//                    System.out.println("disk..."+disk);
//                    System.out.println("moniter..."+monitor);
//                    System.out.println("install..."+install);
//                    System.out.println("cable..."+cable);
//                    System.out.println("1st..."+fst);
//                    System.out.println("2nd..."+snd);
//                    System.out.println("3rd..."+trd);
//                    System.out.println("per..."+prepare);
//                    System.out.println("desg..."+designation);
// ---------------------------------------------------------------------------------                   
                    

                    System.out.println(item);
                    System.out.println(qty);
                    String[] itemarr = item.split(",");
                    String[] qtyarr = qty.split(",");
                    
                    CctvQuotationInfo q = new CctvQuotationInfo();
                    q.setDate(new Date());
                    q.setSysUserSysuserId(user1);
//                    q.setTotal(Double.parseDouble(tot));
                    q.setTotal(Double.parseDouble(grand));
                    q.setProjectProposalId(new ProjectProposal(Integer.parseInt(cid)));
                    q.setQuotationStatusId(new QuotationStatus(1));
                    String result = VertecConstants.SUCCESS;
                    result = quotationDAO.saveCCTV(q);
                    System.out.println("......."+result);
                    
//                    double amut =0.0;
                    
                    for (int i = 0; i < itemarr.length; i++) {
                        CctvQuotationItems c = new CctvQuotationItems();
                        c.setCctvItemsId(new CctvItems(Integer.parseInt(itemarr[i])));
                        c.setCctvQuotationInfoId(q);
                        c.setQty(Integer.parseInt(qtyarr[i]));
                        
//                        double price = quotationDAO.getCctvItemPrice(Integer.parseInt(itemarr[i]));
//                        
//                        amut += price*Double.parseDouble(qtyarr[i]);
                        result = quotationDAO.saveCCTVquotation(c);
                    }
                    
                    CctvWarranty w = new CctvWarranty();
                    w.setCamera(Double.parseDouble(camera));
                    w.setDvr(Double.parseDouble(dvr));
                    w.setHardDisk(Double.parseDouble(disk));
                    if(!monitor.equals("")){
                    w.setMonitor(Double.parseDouble(monitor));
                    }
                    w.setCable(Double.parseDouble(cable));
                    w.setInstallation(Double.parseDouble(install));
                    w.setStPayment(Double.parseDouble(fst));
                    w.setNdPayment(Double.parseDouble(snd));
                    w.setRdPayment(Double.parseDouble(trd));
                    w.setPreparedBy(prepare);
                    w.setDesignation(designation);
                    w.setCctvQuotationInfoId(q);
                    
                    String resu = quotationDAO.saveCCTVWarrenty(w);
                    System.out.println("..........warrenty.."+resu);
                    if(result.equals(VertecConstants.SUCCESS) && resu.equals(VertecConstants.SUCCESS)){
                       out.write(result);
                    }else{
                        out.write(VertecConstants.ERROR);
                    }
                    break;
                    
                }
                // view all cctv quotation
//                case "loadcctvQuotation": {
//                    List<CctvQuotationInfo> info = quotationDAO.loadCctvQuotation();
//                    request.setAttribute("info", info);
//                    
//                    requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllCctv.jsp");
//                    requestDispatcher.forward(request, response);
//                    break;
//                }
                // print cctv quotaion
                case "viewCctvItem": {
                    String hitem = request.getParameter("hidden");
                    System.out.println("......qid......"+hitem);
                    List<CctvQuotationItems> info = quotationDAO.loadCctvQuotationItems(Integer.parseInt(hitem));
                    for (CctvQuotationItems c : info) {
                        System.out.println("..CctvQuotationItems...."+c.getId());
                    }
                    request.setAttribute("info", info);
                    CctvQuotationInfo cq = quotationDAO.viewCctvQuotation(Integer.parseInt(hitem));
                    System.out.println("...CctvQuotationInfo..."+cq.getTotal());
                    request.setAttribute("cq", cq);
                    CctvWarranty cc = quotationDAO.loadCCTVWarrenty(Integer.parseInt(hitem));
                    System.out.println("...CctvWarranty..."+cc.getCamera());
                    request.setAttribute("ccwarrenty", cc);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/design/Cctv.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "NewcreateQuotation": {
                    List<Service> serList = quotationDAO.NewgetServices();
                    request.setAttribute("serList", serList);
                    
                    requestDispatcher = request.getRequestDispatcher("app/quotation/NewCreateQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "ViewcreateQuotation": {
                    String sid = request.getParameter("service").trim();
//                    System.out.println(">>>>>>>>>>>>>>> "+sid);
                    int serviceId = Integer.parseInt(sid);
                    if(serviceId == 1){
//                        System.out.println("..............Software................"+serviceId);
                        List<ProjectProposal> pp = quotationDAO.loadProjectProposalsByServiceId(serviceId);
                        request.setAttribute("proposal", pp);
                        List<com.vertec.hibe.model.Package> p = quotationDAO.loadPackage("1", serviceId);
                        request.setAttribute("packages", p);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/SoftwareQuotation.jsp");
                        requestDispatcher.forward(request, response);
                    }else if(serviceId == 2){
//                        System.out.println("..............Web Site................"+serviceId);
                        List<ProjectProposal> pp = quotationDAO.loadProjectProposalsByServiceId(serviceId);
                        request.setAttribute("proposal", pp);
                        List<com.vertec.hibe.model.Package> p = quotationDAO.loadPackage("1", serviceId);
                        request.setAttribute("packages", p);
                        String serviceid = sid;
                        request.setAttribute("serviceid", serviceid);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/CreateQuotation.jsp");
                        requestDispatcher.forward(request, response);
                        
                    }else if(serviceId == 3){
//                        System.out.println("..............CCTV................"+serviceId);
                        List<CctvCategory> category = cctvDAO.loadItemCategory();
                        request.setAttribute("category", category);
                        List<ProjectProposal> project = quotationDAO.loadCCTVProject();
                        request.setAttribute("project", project);
                    
                        requestDispatcher = request.getRequestDispatcher("app/quotation/cctvQuotation.jsp");
                        requestDispatcher.forward(request, response);
                        
                    }else if(serviceId == 4){
//                        System.out.println("..............Promotion................"+serviceId);
                        List<ProjectProposal> pp = quotationDAO.loadProjectProposalsByServiceId(serviceId);
                        request.setAttribute("proposal", pp);
                        List<com.vertec.hibe.model.Package> p = quotationDAO.loadPackage("1", serviceId);
                        request.setAttribute("packages", p);
                        String serviceid = sid;
                        request.setAttribute("serviceid", serviceid);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/CreateQuotation.jsp");
                        requestDispatcher.forward(request, response);
                        
                    }else if(serviceId == 5){
//                        System.out.println("..............Hardware................"+serviceId);
                        List<ProjectProposal> pp = quotationDAO.loadProjectProposalsByServiceId(serviceId);
                        request.setAttribute("proposal", pp);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/HardwareQuotation.jsp");
                        requestDispatcher.forward(request, response);
                    }else if(serviceId == 6){
                        System.out.println("..............Graphic................"+serviceId);
                    }
                    
                    break;
                }
                case "NewloadPageQuotation": {
                    List<Service> serList = quotationDAO.NewgetServices();
                    request.setAttribute("serList", serList);
                    
                    requestDispatcher = request.getRequestDispatcher("app/quotation/NewViewQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "ViewQuotation": {
                    System.out.println("...calling.....");
                    String sid = request.getParameter("service");
                    System.out.println(">>>>>>>>>>>>>>> "+sid);
                    int serviceId = Integer.parseInt(sid);
                    
                    if(serviceId == 1){
                        List<Quotation> Qlist = quotationDAO.loadAllQuotationByServiceId(serviceId);
                        request.setAttribute("Qlist", Qlist);
                    
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllSoftware.jsp");
                        requestDispatcher.forward(request, response);
                        
                    }else if(serviceId == 2){
                        List<Quotation> Qlist = quotationDAO.loadAllQuotationByServiceId(serviceId);
                        request.setAttribute("Qlist", Qlist);
                    
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllWebsite.jsp");
                        requestDispatcher.forward(request, response);
                    }else if(serviceId == 3){
                        List<CctvQuotationInfo> info = quotationDAO.loadCctvQuotation();
                        request.setAttribute("info", info);
                    
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllCctv.jsp");
                        requestDispatcher.forward(request, response);
                    
                    }else if(serviceId == 4){
                        List<Quotation> Qlist = quotationDAO.loadAllQuotationByServiceId(serviceId);
                        request.setAttribute("Qlist", Qlist);
                    
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllPromotion.jsp");
                        requestDispatcher.forward(request, response);
                    
                    }else if(serviceId == 5){
                        List<HardwareQuotation> hqList = quotationDAO.loadHardwareQuotation();
                        request.setAttribute("hqList", hqList);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewHardware.jsp");
                        requestDispatcher.forward(request, response);
                    
                    }else if(serviceId == 6){
                    
                    }
                    break;
                }
                
                case "NewviewSoftware": {
                    String qid = request.getParameter("hidden");
                    
//                    System.out.println("Quotation ID.........."+qid);
                    
                   
                    List<QuotationHasFeatures> list = quotationDAO.getWebsite(Integer.parseInt(qid));
                    request.setAttribute("qhfs", list);
                    List<Installment> inslist = quotationDAO.getInstallment(Integer.parseInt(qid));
                    request.setAttribute("ins", inslist);
                    Quotation q = quotationDAO.LoadProjectByQuotation(Integer.parseInt(qid));
                    request.setAttribute("quo", q);
                    
                    List<SoftwareQuotation> soList = quotationDAO.getSoftware(Integer.parseInt(qid));
                    request.setAttribute("softList", soList);
                    List<QuotationHasPackages> pList = quotationDAO.getSoftwarepackageDetails(Integer.parseInt(qid));
                    request.setAttribute("pList", pList);
                    List<QuotationHasPackages> qpList = quotationDAO.getSoftwarepackageDetails1(Integer.parseInt(qid));
                    request.setAttribute("qpList", qpList);
                    String totAmt = quotationDAO.getTotAmount(Integer.parseInt(qid));
//                    double totAmt = quotationDAO.getTotAmount(Integer.parseInt(qid));
                    
                    request.setAttribute("tAmt", totAmt);
                    SoftwareAdvanceDetails sList = quotationDAO.getSoftwareAdvanceDetails(Integer.parseInt(qid));
                    request.setAttribute("softAdvanceList", sList);
                    
                        requestDispatcher = request.getRequestDispatcher("app/quotation/design/Software.jsp");
                        requestDispatcher.forward(request, response);
                    break;
                }
                case "NewviewWebsite": {
                    String qid = request.getParameter("hidden");
                    
                    System.out.println("Quotation ID.........."+qid);
                    
                   
                    List<QuotationHasFeatures> list = quotationDAO.getWebsite(Integer.parseInt(qid));
                    request.setAttribute("qhfs", list);
                    List<Installment> inslist = quotationDAO.getInstallment(Integer.parseInt(qid));
                    request.setAttribute("ins", inslist);
                    Quotation q = quotationDAO.LoadProjectByQuotation(Integer.parseInt(qid));
                    request.setAttribute("quo", q);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/design/WebSite.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "NewviewPromotion": {
                    String qid = request.getParameter("hidden");
                    
                    System.out.println("Quotation ID.........."+qid);
                    
                   
                    List<QuotationHasFeatures> list = quotationDAO.getWebsite(Integer.parseInt(qid));
                    request.setAttribute("qhfs", list);
                    List<Installment> inslist = quotationDAO.getInstallment(Integer.parseInt(qid));
                    request.setAttribute("ins", inslist);
                    Quotation q = quotationDAO.LoadProjectByQuotation(Integer.parseInt(qid));
                    request.setAttribute("quo", q);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/design/Promotion.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "changeStatus": {
                    System.out.println("...............change status...............................");
                    String qid = request.getParameter("hidden").trim();
                    String type = request.getParameter("type").trim();
                    String serviceid = request.getParameter("service").trim();
                    String proposal = request.getParameter("proposal").trim();
                    
                    System.out.println("...........serviceid.."+serviceid);
                    System.out.println("...........type.."+type);
                    if(serviceid.equals("1")|| serviceid.equals("2")||serviceid.equals("4")){
                        boolean check = false;
                        if(type.equals("1")){
                            String re = quotationDAO.ApproveProject(Integer.parseInt(proposal));
                            System.out.println("...........approved project.."+re);
                            String result = quotationDAO.changeStatus(Integer.parseInt(qid), Integer.parseInt(type));
                            if(re.equals(VertecConstants.SUCCESS) && result.equals(VertecConstants.SUCCESS)){
                                check=true;
                            }
                        }else{
                            String result = quotationDAO.changeStatus(Integer.parseInt(qid), Integer.parseInt(type));
                            
                            if(result.equals(VertecConstants.SUCCESS)){
                                check=true;
                            }
                        }
                        
//                        System.out.println("........result..."+result);    
                        if (check) {
                                request.getSession().removeAttribute("Success_Message");

                                request.getSession().setAttribute("Success_Message", "Successfully Changed");
                                if(serviceid.equals("1")){
                                    response.sendRedirect("Quotation?action=ViewQuotation&service=1");
                                }else if(serviceid.equals("2")){
                                    response.sendRedirect("Quotation?action=ViewQuotation&service=2");
                                }else if(serviceid.equals("4")){
                                    response.sendRedirect("Quotation?action=ViewQuotation&service=4");
                                }
                            } else {
                                request.getSession().removeAttribute("Error_Message");

                                request.getSession().setAttribute("Error_Message", "Not Changed,Please Tri again");
                                if(serviceid.equals("1")){
                                    response.sendRedirect("Quotation?action=ViewQuotation&service=1");
                                }else if(serviceid.equals("2")){
                                    response.sendRedirect("Quotation?action=ViewQuotation&service=2");
                                }else if(serviceid.equals("4")){
                                    response.sendRedirect("Quotation?action=ViewQuotation&service=4");
                                }
                            }
                    }else if(serviceid.equals("3")){
                        System.out.println("............calling CCTV.............");
                        String result  = quotationDAO.changeStatusOfCctv(Integer.parseInt(qid),Integer.parseInt(type));
                        if (result.equals(VertecConstants.SUCCESS)) {
                                request.getSession().removeAttribute("Success_Message");

                                request.getSession().setAttribute("Success_Message", "Successfully Changed");
                                response.sendRedirect("Quotation?action=ViewQuotation&service=3");
                            } else {
                                request.getSession().removeAttribute("Error_Message");

                                request.getSession().setAttribute("Error_Message", "Not Changed,Please Tri again");
                                response.sendRedirect("Quotation?action=ViewQuotation&service=3");
                                
                        }
                        
                    }else if(serviceid.equals("5")){
                        System.out.println("............calling Hardware.............");
                        String result = quotationDAO.changeStatusOfHardware(Integer.parseInt(qid),Integer.parseInt(type));
                        if (result.equals(VertecConstants.SUCCESS)) {
                                request.getSession().removeAttribute("Success_Message");

                                request.getSession().setAttribute("Success_Message", "Successfully Changed");
                                response.sendRedirect("Quotation?action=ViewQuotation&service=5");
                            } else {
                                request.getSession().removeAttribute("Error_Message");

                                request.getSession().setAttribute("Error_Message", "Not Changed,Please Tri again");
                                response.sendRedirect("Quotation?action=ViewQuotation&service=5");
                                
                        }
                    }
                    
                    break;
                }
                
                case "viewMarkedQuatation": {
                    List<Service> serList = quotationDAO.NewgetServices();
                    request.setAttribute("serList", serList);
                    
                    requestDispatcher = request.getRequestDispatcher("app/quotation/ViewStatusedQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    
                    
                    break;
                }
                
                case "ViewAllMarkedQuatation": {
                    System.out.println("....view calli...");
                    String serviceId = request.getParameter("service");
                    String status = request.getParameter("status");
//                    System.out.println("......."+serviceId);
//                    System.out.println("......."+status);
                    if(serviceId.equals("1") || serviceId.equals("2") || serviceId.equals("4")){
                        List<Quotation> quoList = quotationDAO.viewAllMarkedQuotation(Integer.parseInt(serviceId),Integer.parseInt(status));
                        request.setAttribute("quoList", quoList);

                        Service service = quotationDAO.getServicebyID(Integer.parseInt(serviceId));
                        request.setAttribute("service", service);
                        request.setAttribute("status", status);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllMarkedQuotation.jsp");
                        requestDispatcher.forward(request, response);
                    }else if(serviceId.equals("3")){
                        List<CctvQuotationInfo> obj = quotationDAO.viewAllMarkedCCTV(Integer.parseInt(status));
                        request.setAttribute("quoList", obj);
                        
                        request.setAttribute("status", status);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllMarkedCctv.jsp");
                        requestDispatcher.forward(request, response);
                    }else{
                        List<HardwareQuotation> obj = quotationDAO.viewAllMarkedHardware(Integer.parseInt(status));
                        request.setAttribute("quoList", obj);
                        
                        request.setAttribute("status", status);
                        requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllMarkedHardware.jsp");
                        requestDispatcher.forward(request, response);
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

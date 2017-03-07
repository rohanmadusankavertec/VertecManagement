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
import com.vertec.hibe.model.Service;
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
                    
//                    q.setPackageId(new com.vertec.hibe.model.Package(Integer.parseInt(packagename)));
                    
                    
                        q.setPackageId(new com.vertec.hibe.model.Package(1));
                    
                    String result = quotationDAO.saveQuotation(q);
//                    int phf = quotationDAO.getpackHasFeatureId(0, 0);
                    
                    
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
                case "ViewQuotation": {
                    List<Quotation> qt = quotationDAO.loadAllQuotation();
                    request.setAttribute("quotation", qt);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/ViewQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
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
//                    System.out.println("1");
                    List<QuotationHasFeatures> list = quotationDAO.getWebsite(Integer.parseInt(qid));
                    request.setAttribute("qhfs", list);
//                    System.out.println("2");
                    List<Installment> inslist = quotationDAO.getInstallment(Integer.parseInt(qid));
                    request.setAttribute("ins", inslist);
//                    System.out.println("3");
                    Quotation q = quotationDAO.LoadProjectByQuotation(Integer.parseInt(qid));
                    request.setAttribute("quo", q);
//                    System.out.println("4");
                    List<Service> serlist = quotationDAO.getServices();
                    request.setAttribute("service", serlist);
//                    System.out.println("5");
                    List<SoftwareQuotation> soList = quotationDAO.getSoftware(Integer.parseInt(qid));
                    request.setAttribute("softList", soList);
//                    System.out.println("6");
                    List<QuotationHasPackages> pList = quotationDAO.getSoftwarepackageDetails(Integer.parseInt(qid));
                    request.setAttribute("pList", pList);
//                    System.out.println("7");
                    List<QuotationHasPackages> qpList = quotationDAO.getSoftwarepackageDetails1(Integer.parseInt(qid));
                    request.setAttribute("qpList", qpList);
//                    System.out.println("8");
                    
                    
                     if(sid == 1){
                         String totAmt = quotationDAO.getTotAmount(Integer.parseInt(qid));
//                    System.out.println("9");
                    request.setAttribute("tAmt", totAmt);
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
                    hq.setCustomerId(new Customer(Integer.parseInt(cid)));
                    String result1 = quotationDAO.saveHardwareQuotation(hq);
                    
                    
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
                case "viewHardware": {
                    List<Customer> customer = quotationDAO.loadCustomer();
                    request.setAttribute("cus", customer);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/HardwareQuotation.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // load all hardware quotation
                case "viewHardwareQuotation": {
                    List<HardwareQuotation> hqList = quotationDAO.loadHardwareQuotation();
                    request.setAttribute("hqList", hqList);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/viewHardware.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
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
                    System.out.println(item);
                    System.out.println(qty);
                    String[] itemarr = item.split(",");
                    String[] qtyarr = qty.split(",");
                    
                    CctvQuotationInfo q = new CctvQuotationInfo();
                    q.setDate(new Date());
                    q.setSysUserSysuserId(user1);
//                    q.setTotal(Double.parseDouble(tot));
                    q.setTotal(Double.parseDouble(grand));
                    q.setCustomerId(new Customer(Integer.parseInt(cid)));
                    
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
                    
                    
                    
                    out.write(result);
                    break;
                    
                }
                // view all cctv quotation
                case "loadcctvQuotation": {
                    List<CctvQuotationInfo> info = quotationDAO.loadCctvQuotation();
                    request.setAttribute("info", info);
                    
                    requestDispatcher = request.getRequestDispatcher("app/quotation/viewAllCctv.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // print cctv quotaion
                case "viewCctvItem": {
                    String hitem = request.getParameter("hidden");
                    List<CctvQuotationItems> info = quotationDAO.loadCctvQuotationItems(Integer.parseInt(hitem));
                    request.setAttribute("info", info);
                    CctvQuotationInfo cq = quotationDAO.viewCctvQuotation(Integer.parseInt(hitem));
                    request.setAttribute("cq", cq);
                    requestDispatcher = request.getRequestDispatcher("app/quotation/design/Cctv.jsp");
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

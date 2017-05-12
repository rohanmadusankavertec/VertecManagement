/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.controller;

import com.vertec.daoimpl.AgreementDAOImpl;
import com.vertec.daoimpl.CustomerDAOImpl;
import com.vertec.daoimpl.InvoiceDaoImpl;
import com.vertec.daoimpl.QuotationDAOImpl;
import com.vertec.hibe.model.Business;
import com.vertec.hibe.model.Customer;
import com.vertec.hibe.model.Installment;
import com.vertec.hibe.model.Invoice;
import com.vertec.hibe.model.InvoiceItem;
import com.vertec.hibe.model.Payment;
import com.vertec.hibe.model.PaymentDetails;
import com.vertec.hibe.model.PaymentType;
import com.vertec.hibe.model.ProjectProposal;
import com.vertec.hibe.model.Service;
import com.vertec.hibe.model.SysUser;
import com.vertec.util.VertecConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "InvoiceController", urlPatterns = {"/InvoiceController"})
public class InvoiceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final CustomerDAOImpl CustomerDAO = new CustomerDAOImpl();
    private final InvoiceDaoImpl InvoiceDAO = new InvoiceDaoImpl();
    private final QuotationDAOImpl quotationDAO = new QuotationDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession httpSession = request.getSession();
           
            SysUser user1 = (SysUser) httpSession.getAttribute("user");
            RequestDispatcher requestDispatcher;

            switch (action) {
                // load invoice page to create invoice
                case "createInvoice": {
                    List<ProjectProposal> s = quotationDAO.loadAllApprovedProject();
                    request.setAttribute("project", s);
                    List<Customer> c = CustomerDAO.getListOfCustomer();
                    request.setAttribute("customer", c);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/toCreateInvoice.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view invoice
                case "viewInvoice": {
                    List<Invoice> s = quotationDAO.loadinvoice();
                    request.setAttribute("invoice", s);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/ViewInvoice.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view receipt
                case "viewReceipt": {
                    List<Invoice> s = InvoiceDAO.getListOutInvoices();
                    request.setAttribute("invoice", s);
                    List<PaymentType> pt = InvoiceDAO.getPaymentTypes();
                    request.setAttribute("paymenttype", pt);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/Receipt.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // view installment
                case "LoadInstallments": {
                    String project = request.getParameter("project").trim();
                    List<Object[]> o = InvoiceDAO.getListOfInstallments(project);

                    JSONObject jOB = new JSONObject();
                    JSONArray jar1 = new JSONArray();
                    JSONObject job1 = null;
                    for (Object[] object : o) {
                        job1 = new JSONObject();
                        job1.put("id", object[0].toString());
                        job1.put("description", object[1].toString());
                        job1.put("amount", object[2].toString());
                        jar1.add(job1);
                    }
                    jOB.put("installments", jar1);
                    response.getWriter().write(jOB.toString());
                    break;
                }
                // get the details to create invoice
                case "ToInvoice": {
                    System.out.println("Calling");
                    String business = request.getParameter("business").trim();
                    String project = request.getParameter("project").trim();
                    String installment = request.getParameter("installment").trim();
                    String customer = request.getParameter("customer").trim();

                    request.setAttribute("business", business);
                    request.setAttribute("project", project);
                    request.setAttribute("installment", installment);
                    request.setAttribute("customer", customer);

                    requestDispatcher = request.getRequestDispatcher("app/invoice/Invoice.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // save invoice
                case "SaveInvoice": {
                    String items = request.getParameter("items").trim();
                    String subtotal = request.getParameter("subtotal").trim();
                    String total = request.getParameter("total").trim();
                    String dtype = request.getParameter("dtype").trim();
                    String discount = request.getParameter("discount").trim();
                    String install = request.getParameter("install").trim();
                    String customer = request.getParameter("customer").trim();
                    String business = request.getParameter("business").trim();
                    boolean isAmount = true;
                    if (dtype.equals("false")) {
                        isAmount = false;
                    }
                    Invoice in = new Invoice();
                    in.setAddedBy(user1);
                    in.setDate(new Date());
                    if (discount.equals("")) {
                        in.setDiscount(0.0);
                    } else {
                        in.setDiscount(Double.parseDouble(discount));
                    }
                    in.setIsAmount(isAmount);
                    in.setTotAfterDiscount(Double.parseDouble(total));
                    in.setTotal(Double.parseDouble(subtotal));
                    in.setBusinessId(new Business(Integer.parseInt(business)));
                    in.setOutstanding(Double.parseDouble(total));
                    if (install.equals("")) {
                        in.setInstallmentId(null);
                        in.setCustomerId(new Customer(Integer.parseInt(customer)));
                    } else {
                        in.setInstallmentId(new Installment(Integer.parseInt(install)));
                        in.setCustomerId(InvoiceDAO.getCustomerByInstallemnt(Integer.parseInt(install)));
                    }
                    in.setInvoiceNo("0");
                    String result = InvoiceDAO.saveInvoice(in);
                    String[] arr = items.split(",");
                    String result2 = "";
                    for (int i = 0; i < arr.length; i++) {
                        InvoiceItem item = new InvoiceItem();
                        item.setDescription(arr[i]);
                        item.setInvoiceId(in);
                        item.setQty(Integer.parseInt(arr[i + 1]));
                        item.setPrice(Double.parseDouble(arr[i + 2]));
                        item.setTotal(Double.parseDouble(arr[i + 3]));
                        i += 3;
                        result2 = InvoiceDAO.saveInvoiceItem(item);
                    }
                    if (result.equals(VertecConstants.SUCCESS) && result2.equals(VertecConstants.SUCCESS)) {
                        out.write(VertecConstants.SUCCESS+"~"+in.getId());
                    } else {
                        out.write(VertecConstants.ERROR+"~"+0);
                    }
                    break;
                }
                case "GetInvoiceItem": {
                    String p = request.getParameter("invoice").trim();
                    String result = InvoiceDAO.loadItems(Integer.parseInt(p));
                    out.write(result);
                    break;
                }
                // get the description to complete invoice
                case "LoadInvoiceDescription": {
                    String invoice = request.getParameter("invoice").trim();
                    Invoice o = InvoiceDAO.getInvoicebyID(Integer.parseInt(invoice));

                    JSONObject jOB = new JSONObject();
                    if (o.getInstallmentId() == null) {
                        jOB.put("project", "-");
                    } else {
                        jOB.put("project", o.getInstallmentId().getQuotationId().getProjectProposalId().getProposalName());
                    }
                    jOB.put("customer", o.getCustomerId().getCompanyName() + "~" + o.getCustomerId().getFirstName() + " " + o.getCustomerId().getLastName());
                    jOB.put("total", o.getTotAfterDiscount());
                    System.out.println("Total " + o.getTotAfterDiscount());
                    System.out.println("Outstanding " + o.getOutstanding());
                    jOB.put("paid", o.getTotAfterDiscount() - o.getOutstanding());
                    jOB.put("outstanding", o.getOutstanding());
                    jOB.put("date", o.getDate().toString().replace("-", "/"));
                    jOB.put("invoiceid", o.getId());
                    response.getWriter().write(jOB.toString());
                    break;
                }

                // save payment
                case "SavePayment": {
                    String payarr = request.getParameter("payments").trim();
                    String pt = request.getParameter("pt").trim();
                    String bn = request.getParameter("bn").trim();
                    String cn = request.getParameter("cn").trim();
                    String cd = request.getParameter("cd").trim();
                    String an = request.getParameter("an").trim();

                    String arr[] = payarr.split(",");

                    PaymentDetails paydetails = null;
                    String result = VertecConstants.SUCCESS;
                    if (pt.equals("2")) {
                        paydetails = new PaymentDetails();
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
                        try {
                            paydetails.setChequeDate(sm.parse(cd.replace("/", "-")));
                        } catch (ParseException ex) {
                            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        paydetails.setChequeNo(cn);
                        paydetails.setIsValid(null);
                        paydetails.setBankName(bn);
                        result = InvoiceDAO.savePaymentDetails(paydetails);
                    } else if (pt.equals("3")) {
                        paydetails = new PaymentDetails();
                        paydetails.setIsValid(true);
                        paydetails.setBankName(bn);
                        paydetails.setAccountNo(an);
                        result = InvoiceDAO.savePaymentDetails(paydetails);
                    }
                    String result2 = VertecConstants.SUCCESS;
                    String result3 = VertecConstants.SUCCESS;
                    for (int i = 0; i < arr.length; i++) {
                        Payment p = new Payment();
                        p.setDate(new Date());
                        p.setAmount(Double.parseDouble(arr[i + 3]));
                        p.setReceivedBy(user1);
                        p.setInvoiceId(new Invoice(Integer.parseInt(arr[i + 5])));
                        p.setPaymentDetailsId(paydetails);
                        p.setPaymentTypeId(new PaymentType(Integer.parseInt(pt)));
                        p.setIsValid(true);
                        result2 = InvoiceDAO.savePayment(p);
                        if (!pt.equals("2")) {
                            result3 = InvoiceDAO.UpdateInvoiceOutstanding(Integer.parseInt(arr[i + 5]), Double.parseDouble(arr[i + 3]));
                        }
                        i += 5;

                    }
                    if (result.equals(VertecConstants.SUCCESS) && result2.equals(VertecConstants.SUCCESS) && result3.equals(VertecConstants.SUCCESS)) {
                        out.write(VertecConstants.SUCCESS);
                    } else {
                        out.write(VertecConstants.ERROR);
                    }
                    break;
                }
                // view cheque details
                case "viewcheques": {
                    List<PaymentDetails> s = quotationDAO.loadcheques();
                    request.setAttribute("paymentDetails", s);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/ChequeStatus.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // to clear cheque
                case "ClearCheque": {
                    String id = request.getParameter("pdid");
                    InvoiceDAO.UpdateInvoiceOutstanding(Integer.parseInt(id));
                    InvoiceDAO.UpdateClearCheque(Integer.parseInt(id));
                    List<PaymentDetails> s = quotationDAO.loadcheques();
                    request.setAttribute("paymentDetails", s);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/ChequeStatus.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // returen cheque
                case "ReturnCheque": {

                    String id = request.getParameter("pdid");

                    InvoiceDAO.UpdateReturnCheque(Integer.parseInt(id));

                    List<PaymentDetails> s = quotationDAO.loadcheques();
                    request.setAttribute("paymentDetails", s);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/ChequeStatus.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                //view payment
                case "ViewPayments": {
                    List<Payment> p = InvoiceDAO.getPayments();
                    request.setAttribute("payments", p);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/ViewPayments.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // invoice print
                case "ViewInvoicePrint": {
                    int id= Integer.parseInt(request.getParameter("id"));
                    Invoice i = InvoiceDAO.getInvoiceById(id);
                    request.setAttribute("invoice", i);
                    List<InvoiceItem> ii = InvoiceDAO.getInvoiceItemById(id);
                    request.setAttribute("invoiceitem", ii);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/invoicePrint.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                // receipt print
                case "ViewReceiptPrint": {
                    int id= Integer.parseInt(request.getParameter("id"));
                    Payment i = InvoiceDAO.getPaymentById(id);
                    request.setAttribute("payment", i);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+i.getId());
                    List<InvoiceItem> ii = InvoiceDAO.getInvoiceItemById(i.getInvoiceId().getId());
                    request.setAttribute("invoiceitem", ii);
                    requestDispatcher = request.getRequestDispatcher("app/invoice/receiptPrint.jsp");
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

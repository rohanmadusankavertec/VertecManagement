<%-- 
    Document   : invoicePrint
    Created on : Nov 3, 2016, 10:39:19 AM
    Author     : vertec-r
--%>

<%@page import="com.vertec.hibe.model.Payment"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.vertec.util.NewHibernateUtil"%>
<%@page import="com.vertec.hibe.model.InvoiceItem"%>
<%@page import="com.vertec.hibe.model.Invoice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/bootstrap-dialog.min.css" rel="stylesheet">
        <link href="resources/fonts/css/font-awesome.min.css" rel="stylesheet">
        <link href="resources/css/animate.min.css" rel="stylesheet">

        <link href="resources/css/custom.css" rel="stylesheet">
        <link href="resources/css/floatexamples.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">

    </head>
    <body onload="window.print()" style="background-color: white;">
        <%
            Payment payment = (Payment) request.getAttribute("payment");
            List<InvoiceItem> invoiceitem = (List<InvoiceItem>) request.getAttribute("invoiceitem");

        %>
        <div>

<div class="clearfix"></div>
                    <center>
                        <table style="width: 70%;">
                            <tr>
                                <td>
                                    <div style="float: left;">
                                    <%                                        if (payment.getInvoiceId().getBusinessId().getName().equals("Vertec IT Solutions")) {
                                    %>
                                    <img src="resources/images/logo.png" width="200" height="50" />
                                    <%
                                    } else if (payment.getInvoiceId().getBusinessId().getName().equals("Vertec International")) {
                                    %>
                                    <img src="resources/images/int_logo.png" width="200" height="100"/>
                                    <%
                                        }
                                    %>
                                    </div>
                                    <div class="clearfix"></div>
                                    
                                    
                                    <small style="text-align: right;">
                                        Registered Address : No 11/B, Mawala Junction, Wadduwa,Sri Lanka. (12560)<br>
                                        Telephone : (+94) 38 22 96 305 /  (+94) 38 22 85 601<br>
                                        Hotline : (+94) 71 81 57 57 5
                                        Email : vertecitsolutions@gmail.com
                                    </small>     



                                </td>
                                <td> <img src="resources/images/vglogo.png" width="80" height="80" style="float: right;"> 
                                </td>
                            </tr>
                        </table>
                    </center>

<div class="clearfix"></div>






            <div class="clearfix"></div>

            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">

                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">

                            <section class="content invoice">
                                <!-- title row -->
                                <div class="row">
                                    <div class="col-xs-12 invoice-header">
                                        <h1>
                                            <i class="fa fa-globe"></i> Receipt.
                                            <small class="pull-right"><%=payment.getDate()%><span id="datetime"></span></small>
                                        </h1>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- info row -->
                                <div class="row invoice-info">
                                    <div class="col-sm-12 col-lg-6 col-md-6 invoice-col">
                                        
                                        Receipt No : RCPT<%=payment.getId() %><br>
                                        Issued By :

                                        <address>
                                            <strong style="margin-left: 20px;"><%= payment.getReceivedBy().getFirstName() + " " + payment.getReceivedBy().getLastName()%></strong><br/>

                                        </address>
                                        <div class="clearfix"></div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-sm-12 col-lg-6 col-md-6 invoice-col" style="margin-left: 200px;">
                                        To
                                        <input type="hidden" id="customerId" name="customerId" required="required" class="form-control col-md-7 col-xs-12" value="">

                                        <address>
                                            <strong><%= payment.getInvoiceId().getCustomerId().getFirstName() + " " + payment.getInvoiceId().getCustomerId().getLastName()%></strong><br/>
                                            <%= payment.getInvoiceId().getCustomerId().getCompanyName() %><br>
                                            <%= payment.getInvoiceId().getCustomerId().getAddress().toString().replace(",", ",</br>")%><br>

                                        </address>
                                    </div>
                                </div>
                                <div class="row" style="padding-top: 20px;">
                                    <div class="col-xs-12 table">
                                        <table class="table table-striped" id="invoiceItemTable">
                                            <thead>
                                                <tr>
                                                    <th>Description</th>
                                                    <th>Qty</th>
                                                    <th>Unit Price</th>
                                                    <th>Total </th>
                                                </tr>
                                            </thead>
                                            <tbody id="invoiceItemBody">
                                                <%
                                                    for (InvoiceItem ii : invoiceitem) {
                                                %>
                                                <tr>
                                                    <td> <%=ii.getDescription()%></td>
                                                    <td> <%=ii.getQty()%></td>
                                                    <td> <%=ii.getPrice()%></td>
                                                    <td> <%=ii.getTotal()%></td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->

                                <div class="item form-group" id="tottableDiv">
                                    <div class="col-xs-6">

                                    </div>
                                    <div class="col-xs-6" >
                                        <div class="table-responsive">
                                            <table class="table">
                                                <tbody>
                                                    <tr>
                                                        <th style="width:50%">Subtotal:</th>
                                                        <td id="subtot"><%=payment.getInvoiceId().getTotal()%></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Discount</th>
                                                        <td><%=payment.getInvoiceId().getDiscount()%> 
                                                            <%

                                                                if (payment.getInvoiceId().getIsAmount()) {
                                                                    out.write("(LKR)");
                                                                } else {
                                                                    out.write("(%)");
                                                                }
                                                            %>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>Grand Total</th>
                                                        <td id="totaftdis"><%=payment.getInvoiceId().getTotAfterDiscount()%></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Receipt Amount</th>
                                                        <td id="totaftdis"><%=payment.getAmount() %></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Payment Type</th>
                                                        <td id="totaftdis"><%=payment.getPaymentTypeId().getType() %></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Outstanding</th>
                                                        <td id="totaftdis"><%=payment.getInvoiceId().getOutstanding() %></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                                   
                                                    <center>
                                                        <table style="padding-top: 100px;">
                                                        <tr>
                                                            <td>
                                                                
                                                        <center>
                                                            
                                                            Prepared By<br><br><br>....................................<br> Lahiru Wipulaguna <br>(Manager Operations)
                                                            
                                                            
                                                        </center>
                                                                
                                                                
                                                                
                                                            </td>
                                                            <td style="width: 300px;"></td>
                                                            <td>
                                                                
                                                        <center>
                                                            
                                                            Approved By<br><br><br>....................................<br> RAVI EGODAWITHARANA <br>(C.E.O.)
                                                            
                                                            
                                                        </center>
                                                                
                                                                
                                                                
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    </center>
                                                    

                            </section>
                        </div>
                    </div>
                </div>
                <center>
                    This is a computer generated document
                </center>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document   : employeeDetails
    Created on : Oct 19, 2016, 8:45:06 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>


<script type="text/javascript">
    function OpenPrintView(id){ // send data to print
        window.open("Invoice?action=ViewReceiptPrint&id="+id, "_blank");
    }
</script>


<div class="">
    
    <div class="clearfix"></div>
    <%List<Payment> pList = (List<Payment>) request.getAttribute("payments");%>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Payments <small>up to now</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="table-responsive">
                        <table id="example" class="table table-striped responsive-utilities jambo_table">
                            <thead>
                                <tr class="headings">
                                    <th>Invoice ID </th>
                                    <th>Invoice Date </th>
                                    <th>Invoice Total </th>
                                    <th>Payment Date</th>
                                    <th>Payment Amount</th>
                                    <th>Payment Type</th>
                                    <th>Received By</th>
                                    <th>Print</th>
                                </tr>
                            </thead>

                            <tbody>

                                <% for (Payment p : pList) {

                                %>
                                <tr>
                                    <td><%=p.getInvoiceId().getId() %></td>
                                    <td><%=p.getInvoiceId().getDate() %></td>
                                    <td><%=p.getInvoiceId().getTotAfterDiscount() %></td>
                                    <td><%=p.getDate() %></td>
                                    <td><%=p.getAmount() %></td>
                                    <td><%=p.getPaymentTypeId().getType() %></td>
                                    <td><%=p.getReceivedBy().getFirstName() %></td>
                                    <td><button  onclick="OpenPrintView(<%=p.getId()%>)" type="submit" class="glyphicon glyphicon-print"></button> </td>
                                 </tr>
                                <%}%>
                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </div>

        <br />
        <br />
        <br />

    </div>
</div>
                            
<%@include file="../../template/footer.jsp"%>
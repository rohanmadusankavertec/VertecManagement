<%-- 
    Document   : FilterPO
    Created on : Aug 23, 2016, 12:31:21 PM
    Author     : vertec-r
--%>

<%@page import="com.vertec.hibe.model.PaymentType"%>
<%@page import="com.vertec.hibe.model.Invoice"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Employee"%>
<%@page import="com.vertec.util.NewHibernateUtil"%>
<%@page import="org.hibernate.SQLQuery"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<script src="app/js/notAlert.js"></script>
<script type="text/javascript">



    function loadInvoiceDetails() { //load invoice info by selected invoice
        var invoice = document.getElementById("invoiceId").value;
        $.ajax({
            type: "POST",
            url: "Invoice?action=LoadInvoiceDescription&invoice=" + invoice,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                document.getElementById('divpnt').innerHTML = reply.project;
                document.getElementById('divcnt').innerHTML = reply.customer;
                document.getElementById('divitt').innerHTML = reply.total;
                document.getElementById('divpaidt').innerHTML = reply.paid;
                document.getElementById('divoutt').innerHTML = reply.outstanding;
                document.getElementById('invoicedate').value = reply.date;
                document.getElementById('invoicetotal').value = reply.total;
                document.getElementById('invoicepaid').value = reply.paid;
                document.getElementById('invoiceno').value = reply.invoiceid;
                ViewInvoiceDescription();
            }
        });
    }

    function ViewInvoiceDescription() { // show hidden feilds
        document.getElementById('divpn').className = 'item form-group';
        document.getElementById('divcn').className = 'item form-group';
        document.getElementById('divit').className = 'item form-group';
        document.getElementById('divpaid').className = 'item form-group';
        document.getElementById('divout').className = 'item form-group';
    }
function HideInvoiceDescription() { //hide the feilds
        document.getElementById('divpn').className = 'hidden';
        document.getElementById('divcn').className = 'hidden';
        document.getElementById('divit').className = 'hidden';
        document.getElementById('divpaid').className = 'hidden';
        document.getElementById('divout').className = 'hidden';
    }

    function payType() { // show and hide the feilds according to the payment type
        var pt = document.getElementById("paymentType").value;
        if (pt === "1") {
            document.getElementById('divbn').className = 'hidden';
            document.getElementById('divan').className = 'hidden';
            document.getElementById('divchn').className = 'hidden';
            document.getElementById('divchd').className = 'hidden';
        } else if (pt === "2") {
            document.getElementById('divbn').className = 'item form-group';
            document.getElementById('divan').className = 'hidden';
            document.getElementById('divchn').className = 'item form-group';
            document.getElementById('divchd').className = 'item form-group';
        } else if (pt === "3") {
            document.getElementById('divbn').className = 'item form-group';
            document.getElementById('divan').className = 'item form-group';
            document.getElementById('divchn').className = 'hidden';
            document.getElementById('divchd').className = 'hidden';
        }

    }

    function copyamount() {
        document.getElementById('InvoicePayment').value = document.getElementById('totalpayment').value;
    }
    var tblarr = [];
    function AddToTable() { // load to invoice details to table
        var date = document.getElementById('invoicedate').value;
        var total = document.getElementById('invoicetotal').value;
        var paid = document.getElementById('invoicepaid').value;
        var payment = document.getElementById('InvoicePayment').value;
        var inno = document.getElementById('invoiceno').value;
        
        
        
        var tbl = document.getElementById('paymentItemBody');

        var rowarr = [];
        rowarr.push(date);
        rowarr.push(total);
        rowarr.push(paid);
        rowarr.push(payment);
        rowarr.push((parseFloat(total) - parseFloat(paid) - parseFloat(payment)));
        rowarr.push(inno);
        tblarr.push(rowarr);
        var innerhtml = "";
        tbl.innerHTML = "";
        for (var i = 0; tblarr.length > i; i++) {
            innerhtml += "<tr><td>" + tblarr[i][0] + "</td><td>" + tblarr[i][1] + "</td><td>" + tblarr[i][2] + "</td><td>" + tblarr[i][3] + "</td><td>" + tblarr[i][4] + "</td></tr>";
        }
        tbl.innerHTML = innerhtml;
        HideInvoiceDescription();
    }
    
    
    function savePayment() { //  save  receipt 
    var pt = document.getElementById('paymentType').value;
    var bn = document.getElementById('bankname').value;
    var an = document.getElementById('accountno').value;
    var cn = document.getElementById('chequeno').value;
    var cd = document.getElementById('chequedate').value;
    BootstrapDialog.show({
        message: 'Do you want to Save this Payment ?',
        closable: false,
        buttons: [{
                label: 'Yes',
                action: function (dialogRef) {
                    dialogRef.close();
                    var xmlHttp = getAjaxObject();
                    xmlHttp.onreadystatechange = function ()
                    {
                        if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                        {
                            var reply = xmlHttp.responseText;

                            if (reply === "Success") {
                                nom_Success("Successfully Saved");
                                setTimeout("location.href = 'Invoice?action=viewReceipt';", 1500);
                            } else {
                                sm_warning("Payment is Not Added, Please Try again");
                            }
                        }
                    };
                    xmlHttp.open("POST", "Invoice?action=SavePayment&payments=" + tblarr+"&bn="+bn+"&cn="+cn+"&cd="+cd+"&an="+an+"&pt="+pt, true);
                    xmlHttp.send();
                }
            }, {
                label: 'No',
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }]
    });
}
</script>
<%
    List<Invoice> invoice = (List<Invoice>) request.getAttribute("invoice");
    List<PaymentType> pt = (List<PaymentType>) request.getAttribute("paymenttype");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Receipt
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form action="#" method="post">
                        <div class="item form-group" id="cust">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Payment Type</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" id="paymentType" required="required" onchange="payType()">
                                    <option selected="true" disabled value="">Select Payment Type</option>
                                    <% for (PaymentType p : pt) {%>
                                    <option value="<%=p.getId()%>"><%=p.getType()%></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div>
                        <div class="hidden" style="margin-top: 20px;" id="divbn">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="email">Bank Name
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="bankname" required="required" placeholder="Enter Bank Name"  class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="hidden" style="margin-top: 20px;" id="divan">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="email">Account No
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="accountno" required="required" placeholder="Enter Account Number"  class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="hidden" style="margin-top: 20px;" id="divchn">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="email">Cheque No
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="chequeno" required="required" placeholder="Enter Cheque Number"  class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="hidden" style="margin-top: 20px;" id="divchd">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="email">Cheque date
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="date" id="chequedate" required="required" placeholder="Enter Cheque Date"  class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="item form-group" style="margin-top: 20px;">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="email">Total Payment
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="number" onkeyup="copyamount()" id="totalpayment" required="required" placeholder="Enter Total Payment"  class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="ln_solid"></div>
                        <div class="item form-group" style="padding-top: 50px;" id="cust">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Invoice</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="invoice" id="invoiceId" onchange="loadInvoiceDetails();" required="required">
                                    <option selected="true" disabled value="">Select Invoice</option>
                                    <% for (Invoice p : invoice) {%>
                                    <option value="<%=p.getId()%>"><%=p.getId() + "~" + p.getTotAfterDiscount() + "~" + p.getCustomerId().getCompanyName()%></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <input type="hidden" value="" id="invoicedate"/>
                        <input type="hidden" value="" id="invoicetotal"/>
                        <input type="hidden" value="" id="invoicepaid"/>
                        <input type="hidden" value="" id="invoiceno"/>
                        <div class="hidden" style="padding-top: 50px;" id="divpn">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Project Name </label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="name" id="divpnt">Project Name</label>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="divcn">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Customer </label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="name" id="divcnt">Company Name </label>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="divit">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Invoice Total</label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="name" id="divitt">Rs : 0000.00</label>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="divpaid">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Paid </label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="name" id="divpaidt">Rs : 0000.00</label>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="divout">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Outstanding </label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="name" id="divoutt">Rs : 0000.00</label>
                        </div>
                        <div class="item form-group" style="margin-top: 20px;">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="email">Invoice Payment
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="number" id="InvoicePayment"required="required" placeholder="Enter Invoice Payment"  class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="item form-group" style="padding-top: 100px; float: right;">
                            <div class="col-xs-12 col-lg-offset-3">
                                <button class="btn btn-success" onclick="AddToTable()" type="button">Add Payment</button>
                            </div>
                        </div>





                        <div class="clearfix"></div>




                        <div class="row" style="padding-top: 40px;">
                            <center>
                                <div class="col-xs-12 table">
                                    <table class="table table-striped" id="featureTable">
                                        <thead>
                                            <tr>
                                                <th>Issued Date</th>
                                                <th>Total (LKR)</th>
                                                <th>Paid(LKR)</th>
                                                <th>Payment(LKR)</th>
                                                <th>Outstanding(LKR)</th>
                                            </tr>
                                        </thead>
                                        <tbody id="paymentItemBody">


                                        </tbody>
                                    </table>
                                </div>
                            </center>
                        </div>    










                        <div class="item form-group" style="padding-top: 150px;">
                            <div class="col-xs-12 col-lg-offset-3">
                                <button class="btn btn-success" onclick="savePayment()" type="button">Save Payment</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- footer content -->
<%@include file="../../template/footer.jsp"%>

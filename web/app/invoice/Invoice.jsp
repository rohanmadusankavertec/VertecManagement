<%-- 
    Document   : FilterPO
    Created on : Aug 23, 2016, 12:31:21 PM
    Author     : vertec-r
--%>

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
    function sm_warning(text) {
        BootstrapDialog.show({
            title: 'Warning',
            type: BootstrapDialog.TYPE_WARNING,
            message: text,
            size: BootstrapDialog.SIZE_SMALL
        });
    }
    function nom_Success(text) {
        BootstrapDialog.show({
            title: 'Notification',
            type: BootstrapDialog.TYPE_SUCCESS,
            message: text,
            size: BootstrapDialog.SIZE_NORMAL
        });
    }
    
    var tblarr = [];

    function AddToTable() { // add invoice details to table
        var des = document.getElementById('des').value;
        var qty = document.getElementById('qty').value;
        var amount = document.getElementById('amount').value;
        var tbl = document.getElementById('invoicetable');

        if (des !== "" & qty !== "" & amount !== "") {

            var rowarr = [];
            rowarr.push(des);
            rowarr.push(qty);
            rowarr.push(amount);
            rowarr.push((parseFloat(qty) * parseFloat(amount)));
            tblarr.push(rowarr);
            var innerhtml = "";
            tbl.innerHTML = "";
            for (var i = 0; tblarr.length > i; i++) {
                innerhtml += "<tr><td>" + tblarr[i][0] + "</td><td>" + tblarr[i][1] + "</td><td>" + tblarr[i][2] + "</td><td>" + tblarr[i][3] + "</td></tr>";
            }
            tbl.innerHTML = innerhtml;
            getTotal();

        } else {
            sm_warning("Please fill all Fields..");
        }
    }


    function getTotal() { // invoice total calculation 
        var subtotal = 0.0;
        for (var i = 0; tblarr.length > i; i++) {
            subtotal += parseFloat(tblarr[i][3]);
        }
        document.getElementById('subtotalval').innerHTML = "Rs " + subtotal;

        var discount = document.getElementById('disvalue').value;

        var discountamount = 0;

        if (discount !== "") {

            var ptype = document.getElementById("amountdis");
            if (ptype.checked) {
                discountamount = parseFloat(discount);
            } else {
                discountamount = subtotal * (discount / 100);
            }
        }
        var tot = subtotal - discountamount;
        document.getElementById('totalval').innerHTML = "Rs " + tot;

    }

    function SaveInvoice() { // send invoce details data to controller class to save 
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply1 = xmlHttp.responseText;
                var reply=reply1.split("~");
                
                if (reply[0] === "Success") {
                    nom_Success("Invoice Saved Successfully ");
                     setTimeout("location.href = 'Invoice?action=ViewInvoicePrint&id="+reply[1]+"';", 1500);
                } else {
                    sm_warning("Invoice is not saved, Please Try again.");
                }
            }
        };
        
        var subtotal = 0.0;
        for (var i = 0; tblarr.length > i; i++) {
            subtotal += parseFloat(tblarr[i][3]);
        }
        var discount = document.getElementById('disvalue').value;

        var discountamount = 0;

        var distype=true;
        if (discount !== "") {
            var ptype = document.getElementById("amountdis");
            if (ptype.checked) {
                discountamount = parseFloat(discount);
            } else {
                distype=false;
                discountamount = subtotal * (discount / 100);
            }
        }
        var tot = subtotal - discountamount;
        
        var install=0;
        if(document.getElementById("install")!==null){
            install = document.getElementById("install").value;
        }
        var customer=0;
        if(document.getElementById("customer")!==null){
            customer = document.getElementById("customer").value;
        }
        var business=0;
        if(document.getElementById("business")!==null){
            business = document.getElementById("business").value;
        }
        xmlHttp.open("POST", "Invoice?action=SaveInvoice&items=" + tblarr + "&subtotal=" + subtotal+ "&total=" + tot+ "&dtype=" + distype+ "&discount=" + discount+ "&install=" + install+ "&customer=" + customer+ "&business=" + business, true);
        xmlHttp.send();

    }

</script>
<%
    String business = request.getParameter("business");
    String project = request.getParameter("project");
    String installment = request.getParameter("installment");
    String customer = request.getParameter("customer");

%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Invoice
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


                    <%                        
                    if (business.equals("1")) {
                    %>
                    <img src="resources/images/logo.png" width="250" height="70"/>
                    <%
                    } else if (business.equals("2")) {
                    %>
                    <img src="resources/images/int_logo.png" width="250" height="150"/>
                    <%
                        }
                    %>

                    <form action="#" method="post" class="form-horizontal form-label-left" style="padding-top: 50px; padding-bottom: 50px;">
                        <input type="hidden" value="<%=business%>" id="business"/>
                        <input type="hidden" value="<%=installment%>" id="install"/>
                        <input type="hidden" value="<%=customer %>" id="customer"/>
                        <div class="clearfix"></div>
                        <input id="des" style="width: 450px;" class="form-control col-md-2 col-xs-2" data-validate-words="1" placeholder="Enter Description" type="text">
                        <input id="qty" style="width: 150px; margin-left: 10px;" class="form-control col-md-2 col-xs-2" data-validate-words="1" placeholder="Enter Qty" type="number">
                        <input id="amount" style="width: 150px; margin-left: 10px;" class="form-control col-md-2 col-xs-2" data-validate-words="1" placeholder="Enter Amount" type="number">
                        <div class="item form-group" style="float: right; margin-right: 50px;">
                            <div class="col-xs-12 col-lg-offset-3">
                                <button class="btn btn-success" onclick="AddToTable();" type="button">Add to Invoice</button>
                            </div>
                        </div>
                    </form>


                    <div class="x_content">
                        <div class="table-responsive">
                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                <thead>
                                    <tr class="headings">

                                        <th>Description</th>
                                        <th>Qty</th>
                                        <th>Amount</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody id="invoicetable"> 

                                </tbody>

                            </table>
                        </div>
                    </div>


                    <div class="x_content">
                        <div class="table-responsive">
                            <table class="table table-striped responsive-utilities jambo_table" style="width: 500px;">
                                <tr>
                                    <th>Sub Total :</th>
                                    <th id="subtotalval">            
                                        Rs : 0000.00
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                <tr>

                                    <th>Discount :</th>
                                    <th>            
                                        <input id="disvalue" style="width: 150px; margin-left: 10px;" class="form-control col-md-2 col-xs-2" data-validate-words="1" placeholder="Enter Discount" onkeyup="getTotal()" type="number">
                                    </th>
                                    <th>
                                        <input type="radio" id="amountdis" value="rs" onclick="getTotal()" name="option" checked/> Rs
                                        <input type="radio" id="percentagedis" value="%" onclick="getTotal()" name="option"/> % 
                                    </th>
                                </tr>
                                <tr style="font-size: 20px;"> 
                                    <th>Invoice Total :</th>
                                    <th  id="totalval">            
                                        Rs 0000.00
                                    </th>
                                    <th>
                                    </th>
                                </tr>

                            </table>
                        </div>
                    </div>


                    <div class="item form-group" style="float: right; margin-right: 50px;">
                        <div class="col-xs-12 col-lg-offset-3">
                            <button class="btn btn-success" onclick="SaveInvoice()" type="button">Save Invoice</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>

<!-- footer content -->
<%@include file="../../template/footer.jsp"%>

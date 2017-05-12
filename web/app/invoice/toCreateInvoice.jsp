<%-- 
    Document   : FilterPO
    Created on : Aug 23, 2016, 12:31:21 PM
    Author     : vertec-r
--%>

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
    function ChangeFilter() { // show and hide the feild according to payment type
//        alert("hello");
        var reportType = document.getElementById('business').value;
//        alert("........"+reportType);
        if (reportType === "2") {
            document.getElementById('ser').className = 'hidden';
            document.getElementById('installment').className = 'hidden';
            document.getElementById('cust').className = 'item form-group';
        } else if (reportType === "1") {
            document.getElementById('ser').className = 'item form-group';
            document.getElementById('installment').className = 'item form-group';
            document.getElementById('cust').className = 'hidden';
        }
    }

    function SendData() { //send data to controller calss
        var business = document.getElementById('business').value;
        var project = document.getElementById('project').value;
        var installment = document.getElementById('instal').value;
        var customer = document.getElementById('customer').value;
        window.location = 'Invoice?action=ToInvoice&business=' + business + "&project=" + project+ "&installment=" + installment+ "&customer=" + customer;
    }
    
    
    function loadInstallments() { //load installment to select elemet
        alert("..........");
        $("#instal").empty();
        var project = document.getElementById("project").value;
        $.ajax({
            type: "POST",
            url: "Invoice?action=LoadInstallments&project="+project,
            success: function(msg) {
                alert(msg);
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.installments;
                alert(arrLn1);

                var feature = document.getElementById("instal");
                var ihtml = "";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "'>" + arrLn1[f].description+" Rs:" + arrLn1[f].amount + "</option>"
                }
                feature.innerHTML = ihtml;

//                for (var f = 0; f < arrLn1.length; f++) {
//                    var t = document.createElement("option");
//                    var val = arrLn1[f].id;
//                    t.value = val + "~"+arrLn1[f].feature;
//                    t.innerHTML = arrLn1[f].feature;
//                    feature.appendChild(t);
//                }
            }
        });
    }
</script>
<%
    List<ProjectProposal> pp = (List<ProjectProposal>) request.getAttribute("project");
    List<Customer> customer = (List<Customer>) request.getAttribute("customer");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Create Invoice
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
                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Business </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" onchange="ChangeFilter()" id="business"  required="required">
                                    <option selected="true" disabled value="">Select Business</option>
                                    <option value="1">Vertec IT Solutions</option>
                                    <option value="2">Vertec International</option>
                                </select>                              
                            </div>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="cust">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Customer </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="customer" id="customer" required="required">
                                    <option selected="true" disabled value="">Select Customer</option>
                                    <% for (Customer p : customer) {%>
                                    <option value="<%=p.getId()%>"><%=p.getCompanyName()+"~"+p.getFirstName()+" "+p.getLastName() %></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="ser">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Project </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="project" id="project" onchange="loadInstallments()" required="required">
                                    <option selected="true" disabled value="">Select Project</option>
                                    <% for (ProjectProposal p : pp) {%>
                                    <option value="<%=p.getId() %>"><%=p.getProposalName()+"~"+p.getCustomerId().getCompanyName()+"~"+p.getCustomerId().getFirstName() %></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div>
                         <div class="hidden" style="padding-top: 50px;" id="installment">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Installment </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="installment" id="instal"  required="required">
                                    <option selected="true" disabled value="">Select Installment</option>
                                </select>                              
                            </div>
                        </div>      
                        <div class="item form-group" style="padding-top: 150px;">
                            <div class="col-xs-12 col-lg-offset-3">
                                <button class="btn btn-success" onclick="SendData()" type="button">Create Invoice</button>
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

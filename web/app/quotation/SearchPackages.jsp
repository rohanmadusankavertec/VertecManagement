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
    
    function ChangeFilter() {
        var reportType = document.getElementById('type').value;
        if (reportType === "0") {
            document.getElementById('ser').className = 'hidden';

        } else if (reportType === "1") {
            document.getElementById('ser').className = 'item form-group';
        }
    }

    function SendData() { // send data to controller class to sava
        
        var type = document.getElementById('type').value;
        
        var service = document.getElementById('service').value;
        if(type !== ""){
        window.location = 'Quotation?action=ViewPackage&type=' + type + "&service=" + service;
        }
        else{
            sm_warning("Select the type, Please Try again.");
        }
    }
</script>
<%
    List<Service> s = (List<Service>) request.getAttribute("service");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                View Vertec Packages
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
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Type </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="type" onchange="ChangeFilter()" id="type"  required="required">
                                    <option selected="true" disabled value="">Select Search Type</option>
                                    <option value="0" value="">ALL Packages</option>
                                    <option value="1" value="">Service Wise</option>
                                </select>                              
                            </div>
                        </div>
                        <div class="hidden" style="padding-top: 50px;" id="ser">
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Service </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="service" id="service"  required="required">
                                    <option selected="true" disabled value="">Select Service</option>
                                    <% for (Service ser : s) {%>
                                    <option value="<%=ser.getId()%>"><%=ser.getServiceName() %></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group" style="padding-top: 150px;">
                            <div class="col-xs-12 col-lg-offset-3">
                                <button class="btn btn-success" onclick="SendData()" type="button">View Packages</button>
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

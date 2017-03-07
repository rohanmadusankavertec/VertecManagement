<%-- 
    Document   : registerCustomer
    Created on : Oct 20, 2016, 8:40:14 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%if (ca.checkUserAuth("VIEW_SERVICE", group) != null) {%>

<div class="">

    <div class="clearfix"></div>
<%if (ca.checkUserAuth("ADD_SERVICE", group) != null) {%>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>

                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="Service?action=Register" method="post" class="form-horizontal form-label-left" validate>

                        <span class="section">Service Registration</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Service Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="Name" placeholder="Enter Service Name" required="required" type="text">
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%
}
        List<Service> ServiceList = (List<Service>) request.getAttribute("service");

    %>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Service <small>up to now</small></h2>
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
                                    <th>#</th>
                                    <th>Service Name</th>
                                    <th class=" no-link last"><span class="nobr">Update</span>
                                    <th class=" no-link last"><span class="nobr">Delete</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%                                    for (Service c : ServiceList) {
                                %>
                                <tr>
                                    <td class=" "><%=c.getId()%></td>
                                    <td class=" "><%=c.getServiceName()%></td>
                                    <td class=" last"> 
                                        <%if (ca.checkUserAuth("UPDATE_SERVICE", group) != null) {%>
                                        <form name="form1" method="post" action="Service?action=UpdateService"><input type="hidden" name="serviceId" value="<%=c.getId()%>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">
                                            </button>
                                        </form>
                                            <%}%>
                                    </td>
                                    <td class=" last"> 
                                         <%if (ca.checkUserAuth("DELETE_SERVICE", group) != null) {%>
                                        
                                        <form name="form1" method="post" action="Service?action=RemoveService"><input type="hidden" name="serviceId" value="<%=c.getId()%>"/>
                                            <button id="send" type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                            <%}%>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<%} else {%>
<script type="text/javascript">
    window.location = 'error403.jsp';
</script>
<%}%>
<%@include file="../../template/footer.jsp"%>
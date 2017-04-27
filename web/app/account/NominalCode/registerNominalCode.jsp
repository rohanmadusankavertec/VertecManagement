<%-- 
    Document   : registerCustomer
    Created on : Oct 20, 2016, 8:40:14 AM
    Author     : User

--%>

<%@page import="com.vertec.hibe.model.NominalCode"%>
<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%
//    List<FunctionData> fdList = (List<FunctionData>) request.getAttribute("fd");
//    List<State> StateList = (List<State>) request.getAttribute("state");
//    List<CostCenter> ccList = (List<CostCenter>) request.getAttribute("costcenter");
    List<NominalCode> ncList = (List<NominalCode>) request.getAttribute("nominalcode");

%>



<div class="">

    <div class="clearfix"></div>
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

                    <form action="NominalCode?action=Register" method="post" class="form-horizontal form-label-left" validate>
                        <span class="section">Nominal Code Registration</span>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Nominal Code<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="code" placeholder="Enter Nominal Code" required="required" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Nominal Code Name<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="nc" placeholder="Enter Nominal Code Name" required="required" type="text">
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


    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Nominal Code <small>up to now</small></h2>
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
                                    <th>Nominal Code</th>
                                    <th>Nominal Code Name</th>
                                    <th class=" no-link last"><span class="nobr">Update</span>
                                    <th class=" no-link last"><span class="nobr">Delete</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%                                    for (NominalCode c : ncList) {
                                %>
                                <tr>
                                    <td class=" "><%=c.getId()%></td>
                                    <td class=" "><%=c.getCode()%></td>
                                    <td class=" "><%=c.getName() %></td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="NominalCode?action=UpdateNominalCode">
                                            <input type="hidden" name="ncId" value="<%=c.getId()%>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">
                                            </button>
                                        </form>
                                    </td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="NominalCode?action=RemoveNominalCode">
                                            <input type="hidden" name="ncId" value="<%=c.getId()%>"/>
                                            <button id="send" type="submit" class="btn btn-danger">Delete</button>
                                        </form>
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

<%@include file="../../../template/footer.jsp"%>
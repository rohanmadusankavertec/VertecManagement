<%-- 
    Document   : registerCustomer
    Created on : Oct 20, 2016, 8:40:14 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.UpdateLog"%>
<%@page import="com.vertec.hibe.model.BudgetPlan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%
    BudgetPlan bp = (BudgetPlan) request.getAttribute("bp");
    List<UpdateLog> ulList = (List<UpdateLog>) request.getAttribute("ul");

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

                        <span class="section">Update Log</span>
                        <div class="item form-group" >
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">State
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label>
                                    <%=bp.getNominalCodeId().getCostCenterId().getFunctionId().getStateId().getName()%>                          
                                </label>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Function
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label>
                                    <%=bp.getNominalCodeId().getCostCenterId().getFunctionId().getName()%>   
                                </label>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Cost Center
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label>
                                    <%=bp.getNominalCodeId().getCostCenterId().getCode() + " " + bp.getNominalCodeId().getCostCenterId().getName()%>                           
                                </label>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Nominal Code
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label>
                                    <%=bp.getNominalCodeId().getCode() + " " + bp.getNominalCodeId().getName()%>   
                                </label>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Year
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label>
                                    <%=bp.getYear()%>   
                                </label>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Month
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label>
                                    <%

                                        if (bp.getMonth().equals("1") || bp.getMonth().equals("01")) {
                                            out.write("January");
                                        } else if (bp.getMonth().equals("2") || bp.getMonth().equals("02")) {
                                            out.write("February");
                                        } else if (bp.getMonth().equals("3") || bp.getMonth().equals("03")) {
                                            out.write("March");
                                        } else if (bp.getMonth().equals("4") || bp.getMonth().equals("04")) {
                                            out.write("April");
                                        } else if (bp.getMonth().equals("5") || bp.getMonth().equals("05")) {
                                            out.write("May");
                                        } else if (bp.getMonth().equals("6") || bp.getMonth().equals("06")) {
                                            out.write("June");
                                        } else if (bp.getMonth().equals("7") || bp.getMonth().equals("07")) {
                                            out.write("July");
                                        } else if (bp.getMonth().equals("8") || bp.getMonth().equals("08")) {
                                            out.write("August");
                                        } else if (bp.getMonth().equals("9") || bp.getMonth().equals("09")) {
                                            out.write("September");
                                        } else if (bp.getMonth().equals("10")) {
                                            out.write("October");
                                        } else if (bp.getMonth().equals("11")) {
                                            out.write("November");
                                        } else if (bp.getMonth().equals("12")) {
                                            out.write("December");
                                        }


                                    %>
                                </label>                  
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
                    <h2>Update Log <small>up to now</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <center>
                    <%                                        for (UpdateLog s : ulList) {
                    %>
                    <strong> <%=s.getAfterUser().getFirstName()%> changed <%=s.getBeforeUser().getFirstName()%>'s amount (Rs. <%=s.getBeforeAmount()%>) to Rs. <%=s.getAfterAmount()%> at <%=s.getDate()%><br></strong>
                    <%
                        }
                    %>
                    </center>
                </div>
            </div>
        </div>


    </div>
</div>

<%@include file="../../../template/footer.jsp"%>
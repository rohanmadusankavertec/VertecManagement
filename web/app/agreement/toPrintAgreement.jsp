<%-- 
    Document   : FilterPO
    Created on : Aug 23, 2016, 12:31:21 PM
    Author     : vertec-r
--%>

<%@page import="com.vertec.hibe.model.AgreementTemplate"%>
<%@page import="org.hibernate.sql.Template"%>
<%@page import="com.vertec.hibe.model.Agreement"%>
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

<%
    List<Agreement> agreement = (List<Agreement>) request.getAttribute("agreement");
    List<AgreementTemplate> template = (List<AgreementTemplate>) request.getAttribute("template");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Print Agreement
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
                    <form action="Template?action=PrintAgreement" method="post">
                        <div class="item form-group" style="padding-top: 50px;" >
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Agreement </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="agreement" id="agreement" required="required">
                                    <option selected="true" disabled value="">Select Agreement</option>
                                    <% for (Agreement a : agreement) {%>
                                    <option value="<%=a.getId()%>"><%=a.getQuotationId().getProjectProposalId().getCustomerId().getCompanyName()+"~"+a.getAmount()%></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group" style="padding-top: 50px;" >
                            <label class="control-label col-md-4 col-sm-12 col-xs-12" for="name">Select Template </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="template" id="template" required="required">
                                    <option selected="true" disabled value="">Select Template</option>
                                    <% for (AgreementTemplate a : template) {%>
                                    <option value="<%=a.getId()%>"><%=a.getName() %></option>
                                    <%}%>
                                </select>                              
                            </div>
                        </div> 
                        <div class="item form-group" style="padding-top: 150px;">
                            <div class="col-xs-12 col-lg-offset-3">
                                <button class="btn btn-success" type="submit">Print Agreement</button>
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

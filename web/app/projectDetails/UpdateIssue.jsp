<%-- 
    Document   : UpdateIssue
    Created on : 28-Oct-2016, 13:20:38
    Author     : Imansa
--%>

<%@page import="com.vertec.hibe.model.Issues"%>
<%@page import="com.vertec.hibe.model.ProjectDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>


<div class="col-md-12 col-sm-12 col-xs-12">
    <%
        Issues issues = (Issues) request.getAttribute("issueOb");
    %>
    <div class="x_panel">
        <div class="x_title">
            <h2>Update Issue<small></small></h2>

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

            <form action="Issue?action=updateIssue" method="post" class="form-horizontal form-label-left" validate>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project:
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="hidden" id="serviceID" name="id" value="<%=issues.getId()%>">
                        <input id="projectName" class="form-control col-md-7 col-xs-12" required="required" name="textField" type="text" value="<%=issues.getProjectDetailsId().getProjectProposalId().getProposalName()%>"/>
                    </div>
                </div>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Describe Issue
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <textarea class="form-control col-md-7 col-xs-12" name = "textArea"><%=issues.getIssue()%></textarea>
                    </div>
                </div>

                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-md-offset-3">
                        <button id="send" type="submit"  class="btn btn-success">Submit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>





    <%@include file="../../template/footer.jsp"%>
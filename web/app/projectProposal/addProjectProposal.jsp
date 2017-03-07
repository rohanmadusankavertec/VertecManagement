<%-- 
    Document   : addProjectProposal
    Created on : Oct 25, 2016, 9:15:09 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>




<%
    List<ProjectProposal> proposal = (List<ProjectProposal>) request.getAttribute("proposal");
%>

<%
    List<Customer> customerList = (List<Customer>) request.getAttribute("customerP");
%>
<%
    List<Service> serviceList = (List<Service>) request.getAttribute("serviceP");
%>

<div class="">
    <!--    <div class="page-title">
            <div class="title_left">
                <h3>
                    Customer Registration
                </h3>
            </div>
    
    
        </div>-->
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

                    <form action="ProjectProposal?action=AddNewProject" method="post" class="form-horizontal form-label-left" validate>

                        </p>
                        <span class="section">Add Project Proposal</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Name: <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="pproposalName" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="pproposalName" pattern="[A-z a-z]{2,}" placeholder="Enter Project Name" required="required" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Customer Name: <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="customerName" id="customerName" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected='true' disabled="true" value="" >Select Customer Name:</option>
                                <% for (Customer c : customerList) {%>
                                <option value="<%=c.getId()%>"><%=c.getFirstName()+" "+c.getLastName()+"-"+c.getCompanyName() %></option>
                                    <%}%>
                                </select>     



                            </div>
                        </div>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Service Name:<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="serviceName" id="serviceName" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected="true" disabled="true" value="" >Select Project</option>
                                <% for (Service p : serviceList) {%>
                                    <option value="<%=p.getId() %>"><%=p.getServiceName() %></option>
                                    <%}%>
                                </select>     



                            </div>
                        </div>
                  

                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../template/footer.jsp"%>
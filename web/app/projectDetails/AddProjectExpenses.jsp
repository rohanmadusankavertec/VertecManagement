<%-- 
    Document   : ProjectExpenses
    Created on : Oct 28, 2016, 10:34:16 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<%
    List<ProjectDetails> pdList = (List<ProjectDetails>) request.getAttribute("projectDetails");
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

                    <form action="Expenses?action=AddExpenses" method="post" class="form-horizontal form-label-left" validate>

                        </p>
                        <span class="section">Add Project Expenses</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Name: <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="projectName" id="projectName" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected='true' disabled="true" value="" >Select Project Name</option>
                                    <% for (ProjectDetails c : pdList) {%>
                                    <option value="<%=c.getId() %>"><%=c.getProjectProposalId().getProposalName()+"-"+c.getProjectProposalId().getCustomerId().getCompanyName() %></option>
                                    <%}%>
                                </select>     



                            </div>
                        </div>
                                
                        <div class="ln_solid"></div>
                        
                        <div class="item form-group" style="margin-top: 25px">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <!--<input id="description" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="description" pattern="[A-z a-z]{2,}" placeholder="Enter Project Name" required="required" type="text" >-->
                                <textarea id="description" name="description" class="form-control col-md-7 col-xs-12" placeholder="Enter Project Name" required="required" ></textarea>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Amount  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="amount" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="amount"  placeholder="Enter Amount" required="required" type="number" >
                            </div>
                        </div>
                        
                
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="submit"  class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>



<%@include file="../../template/footer.jsp"%>
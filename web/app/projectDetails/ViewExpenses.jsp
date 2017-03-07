<%-- 
    Document   : ViewExpenses
    Created on : Oct 29, 2016, 8:59:58 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectExpenses"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>



<% ProjectExpenses ex = (ProjectExpenses) request.getAttribute("expe");
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

                    <form action="Expenses?action=UpdateExpenses" method="post" class="form-horizontal form-label-left" validate>

                        </p>
                        <span class="section">Update Project Expenses</span>

                        <div class="item form-group">
                            
                        </div>
                                
                        <div class="ln_solid"></div>
                        
                        <div class="item form-group" style="margin-top: 25px">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <!--<input id="description" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="description" pattern="[A-z a-z]{2,}" placeholder="Enter Project Name" required="required" type="text" >-->
                                <textarea id="description" name="description" class="form-control col-md-7 col-xs-12" placeholder="Enter Project Name" required="required"  ><%=ex.getDescription() %></textarea>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Amount  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="amount" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="amount"  placeholder="Enter Amount" required="required" type="number" value="<%=ex.getAmount()  %>" >
                            </div>
                        </div>
                        
                
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <input id="exId" name="exId" required="required" type="hidden" value="<%=ex.getId() %>">
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

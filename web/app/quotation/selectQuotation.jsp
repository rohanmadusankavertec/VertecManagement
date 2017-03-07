<%-- 
    Document   : selectQuotation
    Created on : Dec 20, 2016, 10:16:11 AM
    Author     : Java-Dev-Ruchira
--%>

<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>


<%
    String qid = (String) request.getAttribute("qid");

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

                    <form action="Quotation?action=viewWebsite" method="post" class="form-horizontal form-label-left" validate>

                        <span class="section">Select Quotation</span>

                        
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Service</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="service" id="service" required="required" >
                                    <option selected="true" disabled="true">Select Service</option>
                                    
                                    <option value="1">Web Designing</option>
                                    <option value="2">Software Developing</option>
                                    <option value="3">Graphic Designing</option>
                                    <option value="4">CCTV security Solution</option>
                                    <option value="5">Business Promotion</option>
                                    
                                </select>                            
                            </div>
                        </div>
                        <input type="hidden" name="hidden" value="<%=qid%>"/>
                        
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                                <button id="send" type="submit" onclick="" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                        
                  

                        <div class="ln_solid"></div>
                        
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="../../template/footer.jsp"%>

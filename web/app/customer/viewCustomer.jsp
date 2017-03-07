<%-- 
    Document   : viewCustomer
    Created on : Oct 20, 2016, 1:14:40 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%@page import="com.vertec.hibe.model.Customer"%>


<div class="">
   

    <%Customer cus = (Customer) request.getAttribute("cus");%>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small></small></h2>
   
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="Customer?action=UpCus" method="post" class="form-horizontal form-label-left" novalidate >

                        </p>
                        <span class="section">Customer Update</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name"> First Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="customerFName" class="form-control col-md-7 col-xs-12"  name="customerFName" pattern="[A-Za-z]{2,}" data-validate-words="2" required="required" type="text" value="<%=cus.getFirstName()  %>">
                                <input id="cuId" name="cuId" required="required" type="hidden" value="<%=cus.getId() %>"> 
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Last Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="customerLName" name="customerLName" pattern="[A-Za-z]{2,}" required="required" value="<%=cus.getLastName() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Company Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="companyName" name="companyName" pattern="[A-Za-z]{2,}" required="required" value="<%=cus.getCompanyName()  %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Mobile No.<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="mobileNo" name="mobileNo" pattern="0[0-9]{9}"  required="required" value="<%=cus.getMobileNo()  %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                            
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Office No <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="officeNo" name="officeNo" pattern="0[0-9]{9}" required="required" value="<%=cus.getOffiiceNo() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Address <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="address" name="address" pattern="[A-Za-z]{2,}" required="required" value="<%=cus.getAddress() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>    
                            
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Fax No <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="faxNo" name="faxNo" pattern="0[0-9]{9}" required="required" value="<%=cus.getFax() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
              
                         <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">E mail <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="email" name="email" required="required" value="<%=cus.getEmail() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>   


                        <!--                        <div class="item form-group">
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="button" onclick="emailValid();">
                                                    </div>
                                                </div>-->

                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="submit" class="btn btn-success">Update</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
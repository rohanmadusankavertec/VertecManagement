<%-- 
    Document   : viewEmployee
    Created on : Oct 19, 2016, 10:08:02 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%@page import="com.vertec.hibe.model.Employee"%>

<div class="">
   

    <%Employee em = (Employee) request.getAttribute("emp");%>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small></small></h2>
   
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="Employee?action=UpEmp" method="post" class="form-horizontal form-label-left" novalidate >

                        </p>
                        <span class="section">Employee Update</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name"> First Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="employeeFName" class="form-control col-md-7 col-xs-12"  name="employeeFName" pattern="[A-Za-z]{2,}" data-validate-words="2" required="required" type="text" value="<%=em.getFirstName() %>">
                                <input id="emId" name="emId" required="required" type="hidden" value="<%=em.getId() %>"> 
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Last Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="employeeLName" name="employeeLName" pattern="[A-Za-z]{2,}" required="required" value="<%=em.getLastName() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Salary <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="number" id="salary" name="salary" required="required" value="<%=em.getSalary() %>" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">NIC No.<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="nic" name="nic" pattern="[0-9]{9}[v]"  required="required" value="<%=em.getNic() %>" class="form-control col-md-7 col-xs-12">
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
                            
                                <%@include file="../../template/footer.jsp"%>
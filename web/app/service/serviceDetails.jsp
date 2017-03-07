<%-- 
    Document   : customerDetails
    Created on : Oct 20, 2016, 11:17:04 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<div class="">
    
    <div class="clearfix"></div>
    <%List<Service> CustomerList = (List<Service>) request.getAttribute("s");%>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Service <small>up to now</small></h2>
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
                                    <th>Service Name</th>
                                    
                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>
                                </tr>
                            </thead>

                            <tbody>

                                <% for (Service c : CustomerList) {

                                %>
                                <tr>

                                    <td class=" "><%=c.getId() %></td>
                                    <td class=" "><%=c.getServiceName() %></td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="Service?action=UpdateService"><input type="hidden" name="customerId" value="<%=c.getId() %>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">

                                            </button></form>

                                        
                                    </td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="Customer?action=RemoveCustomer"><input type="hidden" name="customerId" value="<%=c.getId() %>"/>
                                          <!--  <button type="submit" class="glyphicon glyphicon-edit">

                                            </button> -->
                                           <!-- <div class="ln_solid"></div> -->
                                               <!-- <div class="form-group"> -->
                                                  <!--  <div class="col-md-6 col-md-offset-3"> -->
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                                    <button id="send" type="submit" class="btn btn-danger">Delete</button>
                                                   <!-- </div> -->
                                         <!--       </div> -->
                                        
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

        <br />
        <br />
        <br />

    </div>
</div>
                            
<%@include file="../../template/footer.jsp"%>
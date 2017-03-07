<%-- 
    Document   : customer
    Created on : Nov 2, 2016, 11:39:00 AM
    Author     : vertec-r
--%>

<%@page import="com.vertec.hibe.model.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<!DOCTYPE html>
<script type="text/javascript">
    window.print();
</script>
        <%
    List<Customer> cus = (List<Customer>) request.getAttribute("customer");
        %>
        
        <div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Customers
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_content">
                    <div>
                            <div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Customer Name</th>
                                            <th>Company</th>
                                            <th>Address</th>
                                            <th>Mobile No</th>
                                            <th>Office No</th>
                                            <th>Fax</th>
                                            <th>Email</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <%
                                        for(Customer e : cus){
                                        %>
                                        <tr>
                                            <td><%=e.getId() %></td>
                                            <td><%=e.getFirstName()%> <%=e.getLastName()%></td>
                                            <td><%=e.getCompanyName()%></td>
                                            <td><%=e.getAddress()%></td>
                                            <td><%=e.getMobileNo()%></td>
                                            <td><%=e.getOffiiceNo()%></td>
                                            <td><%=e.getFax()%></td>
                                            <td><%=e.getEmail() %></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

 <%@include file="../../template/footer.jsp"%>
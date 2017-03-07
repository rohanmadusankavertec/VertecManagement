<%-- 
    Document   : packages
    Created on : Nov 2, 2016, 11:40:53 AM
    Author     : vertec-r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <%@include file="../../template/header.jsp"%>
        <%@include file="../../template/sidebar.jsp"%>
    <script type="text/javascript">
        window.print();
    </script>
    <%
        List<com.vertec.hibe.model.Package> pack = (List<com.vertec.hibe.model.Package>) request.getAttribute("package");
    %>

    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>
                    Packages
                </h3>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_content">
                        <div >
                            <div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Service</th>
                                            <th>Package</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (com.vertec.hibe.model.Package e : pack) {
                                        %>
                                        <tr>
                                            <td><%=e.getId()%></td>
                                            <td><%=e.getServiceId().getServiceName() %></td>
                                            <td><%=e.getName() %></td>
                                            <td><%=e.getPrice() %></td>
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

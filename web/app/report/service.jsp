<%-- 
    Document   : service
    Created on : Nov 2, 2016, 11:39:11 AM
    Author     : vertec-r
--%>

<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@include file="../../template/header.jsp"%>
        <%@include file="../../template/sidebar.jsp"%>
    <script type="text/javascript">
        window.print();
    </script>
    <%
        List<Service> cus = (List<Service>) request.getAttribute("service");
    %>

    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>
                    Services
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
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (Service e : cus) {
                                        %>
                                        <tr>
                                            <td><%=e.getId()%></td>
                                            <td><%=e.getServiceName()%></td>
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


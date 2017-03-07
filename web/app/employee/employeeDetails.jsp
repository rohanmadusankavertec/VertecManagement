<%-- 
    Document   : employeeDetails
    Created on : Oct 19, 2016, 8:45:06 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.vertec.hibe.model.Employee"%>
<%@page import="java.util.List"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%if (ca.checkUserAuth("VIEW_EMPLOYEE", group) != null) {%>
<div class="">

    <div class="clearfix"></div>
    <%List<Employee> EmployeeList = (List<Employee>) request.getAttribute("e");%>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Employee <small>up to now</small></h2>
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

                                    <th>First Name </th>
                                    <th>Last Name </th>
                                    <th>Salary </th>
                                    <th>NIC </th>

                                    <th class=" no-link last"><span class="nobr">Update</span>
                                    </th>
                                    <th class=" no-link last"><span class="nobr">Delete</span>
                                    </th>
                                </tr>
                            </thead>

                            <tbody>

                                <% for (Employee em : EmployeeList) {

                                %>
                                <tr>

                                    <td class=" "><%=em.getFirstName()%></td>
                                    <td class=" "><%=em.getLastName()%></td>
                                    <td class=" "><%=em.getSalary()%></td>
                                    <td class=" "><%=em.getNic()%></td>

                                    <td class=" last">
                                        <%if (ca.checkUserAuth("UPDATE_EMPLOYEE", group) != null) {%>
                                        <form name="form1" method="post" action="Employee?action=UpdateEmployee"><input type="hidden" name="employeeId" value="<%=em.getId()%>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">

                                            </button></form>

                                        <%}%>
                                    </td>
                                    <td class=" last"> 
                                        <%if (ca.checkUserAuth("DELETE_EMPLOYEE", group) != null) {%>
                                        <form name="form1" method="post" action="Employee?action=RemoveEmployee"><input type="hidden" name="employeeId" value="<%=em.getId()%>"/>
                                            <button id="send" type="submit" class="btn btn-danger">Delete</button>
                                        </form>

                                        <%}%>
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
<%} else {%>
<script type="text/javascript">
    window.location = 'error403.jsp';
</script>
<%}%>                           
<%@include file="../../template/footer.jsp"%>
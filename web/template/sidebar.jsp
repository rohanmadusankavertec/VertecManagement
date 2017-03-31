<%-- 
    Document   : sidebar
    Created on : Mar 21, 2016, 2:25:42 PM
    Author     : User
--%>

<%@page import="com.vertec.util.CheckAuth"%>
<%@page import="com.vertec.hibe.model.UserGroupPrivilegeItem"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="com.vertec.hibe.model.SysUser"%>


<c:set var="context" value="${pageContext.request.contextPath}" />

<%    SysUser user = (SysUser) session.getAttribute("user");
    CheckAuth ca = new CheckAuth();
    int group = user.getUserGroupId().getUserGroupId();
%>

<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="${context}/dashboard.jsp" class="site_title"><img src="${context}/resources/images/logo.png" style="width: 200px; height: 50px;"/></i> <span></span></a>
        </div>
        <div class="clearfix"></div>
        <br/>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

            <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                    <li><a href="${context}/dashboard.jsp"><i class="fa fa-home"></i> DashBoard </a>

                    </li>
                    <%if (ca.checkUserAuth("ADD_CUSTOMER", group) != null & ca.checkUserAuth("VIEW_CUSTOMER", group) != null) {%>
                    <li><a><i class="fa fa-user-plus"></i>Customer<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_CUSTOMER", group) != null) {%>
                            <li><a href="${context}/app/customer/registerCustomer.jsp">Add Customers</a>
                            </li>
                            <%}%>
                            <%if (ca.checkUserAuth("VIEW_CUSTOMER", group) != null) {%>
                            <li><a href="${context}/Customer?action=ViewCustomer">View Customers</a>
                            </li>
                            <%}%>
                        </ul>
                    </li>
                    <%}%>
                    <%if (ca.checkUserAuth("VIEW_SERVICE", group) != null) {%>
                    <li><a><i class="fa fa-cogs"></i>Services<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="${context}/Service?action=ViewService">Manage Service</a>
                            </li>
                        </ul>
                    </li>
                    <%}%>
                    
                    <%if (ca.checkUserAuth("VIEW_SERVICE", group) != null) {%>
                    <li><a><i class="fa fa-cogs"></i>CCTV<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="${context}/CCTV?action=viewCCTV">Manage CCTV</a>
                            </li>
                        </ul>
                    </li>
                    <%}%>
                    
                    
                    <%if (ca.checkUserAuth("ADD_EMPLOYEE", group) != null & ca.checkUserAuth("VIEW_EMPLOYEE", group) != null) {%>

                    <li><a><i class="fa fa-user-md"></i>Employees<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_EMPLOYEE", group) != null) {%>
                            <li><a href="${context}/app/employee/registerEmployee.jsp">Add Employee</a>
                            </li>
                            <%}%>
                            <%if (ca.checkUserAuth("VIEW_EMPLOYEE", group) != null) {%>
                            <li><a href="${context}/Employee?action=ViewEmployee">View Employee</a>
                            </li>
                            <%}%>
                        </ul>
                    </li>
                    <%}%>

                    <%if (ca.checkUserAuth("ADD_PROJECT", group) != null & ca.checkUserAuth("VIEW_PROJECT", group) != null) {%>

                    <li><a><i class="fa fa-user-plus"></i>Project Proposal<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_PROJECT", group) != null) {%>
                            <li><a href="${context}/ProjectProposal?action=getProjectDetails">Add New Project</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_PROJECT", group) != null) {%>
                            <li><a href="${context}/ProjectProposal?action=ViewProjectProposal">View Project Details</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}%>
                    <li><a><i class="fa fa-file-pdf-o"></i>Quotation<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_PACKAGE", group) != null) {%>
                            <li><a href="${context}/Quotation?action=addpackages">Add Packages</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_PACKAGE", group) != null) {%>
                            <li><a href="${context}/Quotation?action=SearchPackage">View Packages</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("ADD_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=createQuotation">Create Quotation</a></li>
                                <%}%>
                            <%if (ca.checkUserAuth("ADD_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=SoftwareQuotation">Create software Quotation</a></li>
                                <%}%>
                            <%if (ca.checkUserAuth("ADD_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=viewHardware">Create Hardware Quotation</a></li>
                                <%}%>
                            <%if (ca.checkUserAuth("ADD_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=loadCctvPage">Create CCTV Quotation</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=ViewQuotation">View Quotation</a></li>
                                <%}%>
                            <%if (ca.checkUserAuth("VIEW_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=viewHardwareQuotation">View Hardware Quotation</a></li>
                                <%}%>
                            <%if (ca.checkUserAuth("VIEW_QUOTATION", group) != null) {%>
                            <li><a href="${context}/Quotation?action=loadcctvQuotation">View CCTV Quotation</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%if (ca.checkUserAuth("CALCULATE_ESTIMATE_COST", group) != null & ca.checkUserAuth("VIEW_ESTIMATE_COST", group) != null) {%>

                    <li><a><i class="fa fa-user-plus"></i>Estimate Cost<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("CALCULATE_ESTIMATE_COST", group) != null) {%>
                            <li><a href="${context}/Estimatecost?action=addestimatecost">Calculate Estimate cost</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_ESTIMATE_COST", group) != null) {%>
                            <li><a href="${context}/Estimatecost?action=loadEstimatecost">View Estimate Cost Details</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}%>
                    <%if (ca.checkUserAuth("ADD_AGREEMENT", group) != null & ca.checkUserAuth("VIEW_AGREEMENT", group) != null) {%>

                    <li><a><i class="fa fa-file"></i>Agreement<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_AGREEMENT", group) != null) {%>
                            <li><a href="${context}/Agreement?action=AddAgreement">Add Agreement</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_AGREEMENT", group) != null) {%>
                            <li><a href="${context}/Agreement?action=ViewAgreement">View Agreement</a></li>
                                <%}%>
                            <li><a href="${context}/Template?action=AddAgreementTemplate">Design Agreement Template</a></li>
                            <li><a href="${context}/Template?action=ViewAgreementTemplate">View Agreement Template</a></li>
                            <li><a href="${context}/Agreement?action=ToPrintAgreement">Print Agreement</a></li>
                        </ul>
                    </li>
                    <%}%>
                    <li><a><i class="fa fa-user-plus"></i>Project Details<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="${context}/ProjectDetails?action=loadEmpStatusProjectName">Add Project Details</a></li>
                            <li><a href="${context}/ProjectDetails?action=ViewProDetails">Change Project Details</a></li>

                            <li><a href="${context}/ProjectProcess?action=getProjectName">Add Project Process</a></li>
                            <li><a href="${context}/ProjectProcess?action=ViewProcess">View Process Details</a></li>

                            <li><a href="${context}/Expenses?action=ViewProjectName">Add Project Expenses</a></li>

                            <li><a href="${context}/Expenses?action=ViewExpenses">View Project Expenses</a></li>



                            <li><a href="${context}/Issue?action=viewAddIssue">Issue Manage</a></li>

                        </ul>
                    </li>
                    <%if (ca.checkUserAuth("ADD_INVOICE", group) != null & ca.checkUserAuth("VIEW_INVOICE", group) != null) {%>

                    <li><a><i class="fa fa-file-word-o"></i>Invoice<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_INVOICE", group) != null) {%>
                            <li><a href="${context}/Invoice?action=createInvoice">Create Invoice</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_INVOICE", group) != null) {%>
                            <li><a href="${context}/Invoice?action=viewInvoice">View Invoice</a></li>
                                <%}%>
                        </ul>
                    </li>
                    <%}%>
                    <li><a><i class="fa fa-file-text"></i>Receipt<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_PAYMENT", group) != null) {%>
                            <li><a href="${context}/Invoice?action=viewReceipt">Payment</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_PAYMENT", group) != null) {%>
                            <li><a href="${context}/Invoice?action=ViewPayments">View Payment</a></li>
                                <%}%>
                                <%if (ca.checkUserAuth("VIEW_CHEQUE", group) != null) {%>
                            <li><a href="${context}/Invoice?action=viewcheques">Cheques</a></li>
                                <%}%>

                        </ul>
                    </li>
                    <li><a><i class="fa fa-file-text"></i>Account Plan<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="${context}/State?action=viewStatePage">Manage State</a></li>
                            <li><a href="${context}/FunctionData?action=ManageFunctionData">Manage Function</a></li>
                        </ul>
                    </li>
                    
                    
                    <li><a><i class="fa fa-file-text"></i>Reports<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="${context}/Report?action=printCustomer">Customers</a>
                            <li><a href="${context}/Report?action=printService">Services</a>
                            <li><a href="${context}/Report?action=printPackage">Packages</a>
                            <li><a href="${context}/Report?action=printCustomer">Project</a>
                            </li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-file-text"></i>Account Reports<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="${context}/AccountReport?action=SearchBudgetPlan">BudgetPlan Report</a></li>
                            <li><a href="${context}/AccountReport?action=SearchActualCost">Actual Cost Report</a></li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-user-secret"></i>User Management<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_USER", group) != null) {%>
                            <li><a href="${context}/app/users/userRegister.jsp">Add New User</a>
                            </li>
                            <%}%>
                            <%if (ca.checkUserAuth("VIEW_USERS", group) != null) {%>
                            <li><a href="${context}/User?action=ViewUsers">Manage Users</a>
                            </li>
                            <%}%>
                            <%if (ca.checkUserAuth("ADD_USER_GROUP", group) != null) {%>
                            <li><a href="${context}/app/users/createUserGroup.jsp">Create User Group</a>
                            </li>
                            <%}%>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-check"></i>Privilege Management<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <%if (ca.checkUserAuth("ADD_PRIVILEGE", group) != null) {%>
                            <li><a href="${context}/Privilege?action=ViewPrivilege">Manage Privilege Groups</a>
                            </li>
                            <%}%>
                            <%if (ca.checkUserAuth("ADD_PRIVILEGE_ITEM", group) != null) {%>
                            <li><a href="${context}/Privilege?action=ForPrivilegeItem">Manage Privileges</a>
                            </li>
                            <%}%>
                            <%if (ca.checkUserAuth("SET_PRIVILEGE_ITEM", group) != null) {%>
                            <li><a href="${context}/Privilege?action=LoadUserGroupsForPI">Manage User Group Privileges</a>
                            </li>
                            <%}%>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
        <!-- /sidebar menu -->

        <!-- /menu footer buttons -->
        <div class="sidebar-footer hidden-small">

            <a data-toggle="tooltip" data-placement="top" title="Logout" href="${context}/Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
        </div>
        <!-- /menu footer buttons -->
    </div>
</div>


<div class="top_nav">

    <div class="nav_menu">
        <nav class="" role="navigation">
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>
            <div style="" class="nav navbar-nav">
                <!-- Button -->
                <%
                    String success_message = (String) request.getAttribute("Success_Message");
                    String error_message = (String) request.getAttribute("Error_Message");
                    if (success_message == null) {
                        success_message = (String) session.getAttribute("Success_Message");
                    }
                    if (error_message == null) {
                        error_message = (String) session.getAttribute("Error_Message");
                    }
                    request.getSession().removeAttribute("Error_Message");

                %>
                <div class="" id="mydiv">
                    <strong><font color="green">
                        <% if (success_message != null) {
                                out.println(success_message);
                            }%>
                        </font>
                    </strong> 
                    <strong><font color="red">
                        <% if (error_message != null) {
                                out.println(error_message);
                            }%>
                        </font>
                    </strong> 
                </div>
            </div>
            <%
                request.getSession().removeAttribute("Error_Message");
                request.getSession().removeAttribute("Success_Message");

            %>
            <ul class="nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <%out.print(user.getFirstName() + " " + user.getLastName());%>
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                        <li><a href="${context}/app/users/editOwn.jsp">  Update Profile</a>
                        </li>
                        <li><a href="${context}/app/users/changePassword.jsp">  Change Password</a>
                        </li>

                        <li><a href="${context}/Logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                        </li>
                    </ul>
                </li>



            </ul>
        </nav>
    </div>

</div>

<!-- page content -->
<div class="right_col" role="main">
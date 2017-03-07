<%-- 
    Document   : ProjectProposalDetails
    Created on : Oct 25, 2016, 1:14:39 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<%List<ProjectProposal> projectPList = (List<ProjectProposal>) request.getAttribute("proposal"); %>
    

<div class="">
    
    <div class="clearfix"></div>
    
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Project Proposal Details <small>up to now</small></h2>
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

                                    <th>Project</th>
                                    <th>Customer Name </th>
                                    <th>Company Name </th>
                                    <th>Service </th>
                                    
                                    
<!--                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>
                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>-->
                                </tr>
                            </thead>

                            <tbody>

                                <% for (ProjectProposal pp : projectPList) {

                                %>
                                <tr>
                     
                                    <td class=" "><%=pp.getProposalName()  %></td>
                                    <td class=" "><%=pp.getCustomerId().getFirstName()+" "+ pp.getCustomerId().getLastName()  %></td>
                                    <td class=" "><%=pp.getCustomerId().getCompanyName() %></td>
                                    <td class=" "><%=pp.getServiceId().getServiceName() %></td>
                                                                                                  
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


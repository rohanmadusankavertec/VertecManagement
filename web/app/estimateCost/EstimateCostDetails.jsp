<%-- 
    Document   : ViewEstimateCost
    Created on : Oct 24, 2016, 3:18:13 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%@page import="com.vertec.hibe.model.EstimateCost"%>



<%List<EstimateCost> EstimatecostList= (List<EstimateCost>) request.getAttribute("estimate"); %>
    

<div class="">
    
    <div class="clearfix"></div>
    
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Calculated Estimate Cost <small>up to now</small></h2>
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
                                    <th>Electricity Cost </th>
                                    <th>Management Cost </th>
                                    <th>Other Cost </th>
                                    <th>Developer Cost</th>
                                    <th>Estimate Cost</th>
                                    
<!--                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>
                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>-->
                                </tr>
                            </thead>

                            <tbody>

                                <% for (EstimateCost es : EstimatecostList) {

                                %>
                                <tr>

                                    <td class=" "><%=es.getProjectProposalId().getProposalName() %></td>
                                    <td class=" "><%=es.getElectricityCost()  %></td>
                                    <td class=" "><%=es.getManagementCost() %></td>
                                    <td class=" "><%=es.getOtherCost() %></td>
                                    <td class=" "><%=es.getDeveloperCost() %></td>
                                    <td class=" "><%=es.getEstimateCost() %></td>
                                    
                                    
                                    
                                    
                                    
                              
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
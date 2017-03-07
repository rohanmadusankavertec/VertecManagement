<%-- 
    Document   : ExpensesDetails
    Created on : Oct 28, 2016, 2:37:37 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectDetails"%>
<%@page import="com.vertec.hibe.model.ProjectExpenses"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<script>
    
    function viewExpensesByProjectName(){
      
        var detailsId = document.getElementById('projectName').value;
//        alert(detailsId);
        
        window.location = 'Expenses?action=ViewExpenses&detailsId=' + detailsId ;
    }
     
    
    
</script>


<%
    List<ProjectExpenses> pex = (List<ProjectExpenses>) request.getAttribute("expenses");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Expenses
            </h3>
        </div>
    </div>

    <div class="clearfix"></div>
    <% List<ProjectDetails> porjectDList = (List<ProjectDetails>) request.getAttribute("projectDetails"); 
        String detailsId=request.getParameter("detailsId");
   
    %>

<% List<ProjectExpenses> expensesList = (List<ProjectExpenses>) request.getAttribute("expensesList"); 
%>

<div class="">
    
    <div class="clearfix"></div>
    
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Project Expenses Details <small>up to now</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Name: <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="projectName" onchange="viewExpensesByProjectName()" id="projectName" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected='true' disabled="true" value="" >Select Project Name</option>
                                    <% for (ProjectDetails c : porjectDList) {%>
                                <option value="<%=c.getId() %>"><%=c.getProjectProposalId().getProposalName()+"-"+c.getProjectProposalId().getCustomerId().getCompanyName() %></option>
                                    <%}%>
                                </select>     
                            </div>
                    </div>
                     
                    <div class="clearfix"></div>            
                    <div class="ln_solid"></div>         
                                
                             <br>
                             <br>
                             
                              
                <%
                if(expensesList!=null){
                %>
                <div class="x_content">
                    <div class="table-responsive">
                        <table id="example" class="table table-striped responsive-utilities jambo_table">
                          
                            <thead>
                                <tr class="headings">
                                    <th>Description</th>
                                    <th>Amount</th>
                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>
                                </tr>
                            </thead> 
                            <tbody
                                <tr>
                                <% for (ProjectExpenses es : expensesList) {

                                %>
                                    <td class=" "><%=es.getDescription() %></td>
                                    <td class=" "><%=es.getAmount()  %></td>
                                    
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="Expenses">
                                            <input type="hidden" name="action" value="toUpdateExpenses"/>
                                            <input type="hidden" name="eId" value="<%=es.getId() %>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">

                                            </button></form>

                                        
                                    </td>

                                        
                                    </td>
                                                                
                                </tr>
                                <%}%>
                            </tbody>

                        </table>
                    </div>
                </div>
                            
                            <%
                }
                %>
            </div>
        </div>

        <br />
        <br />
        <br />

    </div>
</div>
    
    
    
</div>



<%@include file="../../template/footer.jsp"%>
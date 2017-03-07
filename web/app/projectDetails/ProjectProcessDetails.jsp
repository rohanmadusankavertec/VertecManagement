<%-- 
    Document   : ProjectProcessDetails
    Created on : Oct 27, 2016, 12:40:43 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectProcess"%>
<%@page import="com.vertec.hibe.model.ProjectDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<script>
    
    function viewProcessByProjectName(){
      
        var detailsId = document.getElementById('projectName').value;
       
        window.location = 'ProjectProcess?action=ViewProcess&detailsId=' + detailsId ;
    }
     
    
    
</script>




<% List<ProjectDetails> porjectDList = (List<ProjectDetails>) request.getAttribute("projectDetails"); 
String detailsId=request.getParameter("detailsId");
        

%>

<% List<ProjectProcess> porjectPssList = (List<ProjectProcess>) request.getAttribute("processL"); 
%>

<div class="">
    
    <div class="clearfix"></div>
    
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Project Process Details <small>up to now</small></h2>
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
                                <select class="form-control" name="projectName" onchange="viewProcessByProjectName()" id="projectName" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected='true' disabled="true" value="" >Select Project Name</option>
                                    <% for (ProjectDetails c : porjectDList) {%>
                                <option value="<%=c.getId() %>"><%=c.getProjectProposalId().getProposalName() %></option>
                                    <%}%>
                                </select>     
                            </div>
                    </div>
                     
                    <div class="clearfix"></div>            
                    <div class="ln_solid"></div>         
                                
                             <br>
                             <br>
                             
                              
                <%
                if(porjectPssList!=null){
                %>
                <div class="x_content">
                    <div class="table-responsive">
                        <table id="example" class="table table-striped responsive-utilities jambo_table">
                          
                            <thead>
                                <tr class="headings">

                                    <th>Process</th>
                                    <th>Description</th>
                                    <th>Duration </th>
                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>
                                 
                                </tr>
                            </thead> 
                            <tbody
                                <tr>
                                <% for (ProjectProcess es : porjectPssList) {

                                %>
                                    <td class=" "><%=es.getProcess() %></td>
                                    <td class=" "><%=es.getDescription()  %></td>
                                    <td class=" "><%=es.getDuration() %></td>
                                    
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="ProjectProcess?action=DeleteProcess">
                                            <input type="hidden" name="processId" value="<%=es.getId() %>"/>
                                            <input type="hidden" name="detailsId" value="<%=detailsId %>"/>
                 
                                                    <button id="send" type="submit" class="btn btn-danger">Delete</button>
                                                                                   
                                        </form>

                                        
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



<%@include file="../../template/footer.jsp"%>
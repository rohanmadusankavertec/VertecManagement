<%-- 
    Document   : registerCustomer
    Created on : Oct 20, 2016, 8:40:14 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.State"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%

        
        List<State> StateList = (List<State>) request.getAttribute("state");

%>
<script type="text/javascript">
    
    
    function loadFunctionData(){
//        $("").empty();
        var sid = document.getElementById("itemState").value;
        alert(sid);
        $.ajax({
            type: "POST",
            url: "CostCenter?action=getFunctionData&sid="+sid,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.functionData;


                var fdata = document.getElementById("fdata");
                var ihtml = "";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "'>" + arrLn1[f].fname+"</option>"
                }
                fdata.innerHTML = ihtml;


            }
        });
        
    }
    
</script>


<div class="">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small>Actual Cost</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>

                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="FunctionData?action=Register" method="post" class="form-horizontal form-label-left" validate>

                        <span class="section">Add Actual Cost</span>
                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select State <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="stateid" id="stateid" onchange="loadFunctionData()">
                                    <option selected="true" disabled value="">Select State</option>
                                    <%                                        
                                        for (State s : StateList) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Function Data  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="fdata" id="fdata"  required="required" >
                                    <option disabled value="">Select Function Data</option>
                                    
                                    
                                </select>                              
                            </div>
                        </div>        
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Cost Center  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccenter" id="ccenter"  required="required" >
                                    <option disabled value="">Select Cost Center </option>
                                    
                                    
                                </select>                              
                            </div>
                        </div>
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Nominal Code  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="nominalCode" id="nominalCode"  required="required" >
                                    <option selected="true" disabled value="">Select Nominal Code </option>
                                    
                                    
                                </select>                              
                            </div>
                        </div>
                                
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Function Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="fname" placeholder="Enter Function Name" required="required" type="text">
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Functions <small>up to now</small></h2>
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
                                    <th>State Name</th>
                                    <th>Function Name</th>
                                    <th class=" no-link last"><span class="nobr">Update</span>
                                    <th class=" no-link last"><span class="nobr">Delete</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%                                    for (FunctionData c : fdList) {
                                %>
                                <tr>
                                    <td class=" "><%=c.getId()%></td>
                                    <td class=" "><%=c.getStateId().getName() %></td>
                                    <td class=" "><%=c.getName()%></td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="FunctionData?action=UpdateFunction"><input type="hidden" name="functionId" value="<%=c.getId()%>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">
                                            </button>
                                        </form>
                                    </td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="FunctionData?action=RemoveFunction"><input type="hidden" name="functionId" value="<%=c.getId()%>"/>
                                            <button id="send" type="submit" class="btn btn-danger">Delete</button>
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


    </div>
</div>

<%@include file="../../../template/footer.jsp"%>
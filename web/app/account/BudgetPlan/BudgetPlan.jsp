<%-- 
    Document   : registerCustomer
    Created on : Oct 20, 2016, 8:40:14 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.BudgetPlan"%>
<%@page import="com.vertec.hibe.model.NominalCode"%>
<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%
    List<FunctionData> fdList = (List<FunctionData>) request.getAttribute("fd");
    List<State> StateList = (List<State>) request.getAttribute("state");
    List<CostCenter> ccList = (List<CostCenter>) request.getAttribute("costcenter");
    List<NominalCode> ncList = (List<NominalCode>) request.getAttribute("nominalcode");
    List<BudgetPlan> bp = (List<BudgetPlan>) request.getAttribute("budget");

%>

<script type="text/javascript">

    function loadFunctionData() {
        $("#funcid").empty();
        var s1 = document.getElementById('funcid');
        var t1 = document.createElement("option");

        t1.value = "";
        t1.innerHTML = "Select Function";
        s1.appendChild(t1);
        var stateId = document.getElementById('stateid').value;
        $.ajax({
            type: "POST",
            url: "AccountReport?action=getFunctionByState&stateid=" + stateId,
            success: function (msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.func;

                for (var f = 0; f < arrLn1.length; f++) {
                    var t = document.createElement("option");
                    t.value = arrLn1[f].id;
                    t.innerHTML = arrLn1[f].name;
                    s1.appendChild(t);
                }


            }

        });
    }

    function loadCostCenter() {
        $("#ccid").empty();
        var s1 = document.getElementById('ccid');
        var t1 = document.createElement("option");

        t1.value = "";
        t1.innerHTML = "Select Cost Center";
        s1.appendChild(t1);
        var functionId = document.getElementById('funcid').value;
        $.ajax({
            type: "POST",
            url: "AccountReport?action=getCostCenterbyFunction&functionid=" + functionId,
            success: function (msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.cc;

                for (var f = 0; f < arrLn1.length; f++) {
                    var t = document.createElement("option");
                    t.value = arrLn1[f].id;
                    t.innerHTML = arrLn1[f].name;
                    s1.appendChild(t);
                }
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
                    <h2><small></small></h2>
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

                    <form action="NominalCode?action=Register" method="post" class="form-horizontal form-label-left" validate>

                        <span class="section">Nominal Code Registration</span>
                        <div class="item form-group" >
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select State <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="stateid" id="stateid" onchange="loadFunctionData()">
                                    <option selected="true" disabled value="">Select State</option>
                                    <%                                        for (State s : StateList) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Function <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="funcid" id="funcid" onchange="loadCostCenter()">
                                    <option selected="true" disabled value="">Select Function</option>
                                    <%                                        for (FunctionData s : fdList) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Cost Center <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccid" id="ccid">
                                    <option selected="true" disabled value="">Select Cost Center</option>
                                    <%
                                        for (CostCenter s : ccList) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Nominal Code <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccid" id="ccid">
                                    <option selected="true" disabled value="">Select Nominal Code</option>
                                    <%
                                        for (NominalCode s : ncList) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Year <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="year" id="year">
                                    <option selected="true" disabled value="">Select Year</option>
                                    <option value="2017" >2017</option>
                                    <option value="2018" >2018</option>
                                    <option value="2019" >2019</option>
                                    <option value="2020" >2020</option>
                                    <option value="2021" >2021</option>

                                </select>                              
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
                    <h2>Budget Plan <small>up to now</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">


                    <table>
                        <thead>
                            <tr>
                                <th>Month</th>
                                <th>Budget</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>



                            <tr>
                                <td>January</td>
                                <td>
                                    <input class="form-control col-md-7 col-xs-12"  name="1amount" placeholder="Enter Amount" required="required" type="number">
                           
                                    
                                </td>
                                <td>
                                    <button id="send" type="button" class="btn btn-success">Save</button>
                                    <button id="send" type="button" class="btn btn-success">Update</button>
                                    
                                </td>
                            </tr>



                        </tbody>
                    </table>




                </div>
            </div>
        </div>


    </div>
</div>

<%@include file="../../../template/footer.jsp"%>
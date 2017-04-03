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

    function loadNominal() {
        $("#ncid").empty();
        var s1 = document.getElementById('ncid');
        var t1 = document.createElement("option");

        t1.value = "";
        t1.innerHTML = "Select Nominal Code";
        s1.appendChild(t1);
        var ccId = document.getElementById('ccid').value;
        $.ajax({
            type: "POST",
            url: "AccountReport?action=getNominalByCostCenter&ccid=" + ccId,
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


    function loadData() {
        document.getElementById('budgetdetails').className="hidden";
        
        var ncid = document.getElementById('ncid').value;
        var year = document.getElementById('year').value;
        $.ajax({
            type: "POST",
            url: "Budget?action=getYearlyBudget&ncid=" + ncid + "&year=" + year,
            success: function (msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.arr;

                for (var f = 1; f < arrLn1.length+1; f++) {
//                    alert(f+"amount "+arrLn1[f-1].amount+"   "+arrLn1[f-1].isChanged);
                    if(arrLn1[f-1].amount===""){
                        
                        document.getElementById(f+"amount").value="";
                        document.getElementById(f+"save").className="btn btn-success";
                        document.getElementById(f+"update").className="hidden";
                        
                    }else{
                        document.getElementById(f+"amount").value=arrLn1[f-1].amount;
                        document.getElementById(f+"update").className="btn btn-warning";
                        document.getElementById(f+"save").className="hidden";
                    }
                    
                    if((arrLn1[f-1].isChanged)===true){
                        document.getElementById(f+"changed").className="";
                        
                    }else{
                        document.getElementById(f+"changed").className="hidden";
                    }
                    
                    
                }
                document.getElementById('budgetdetails').className="";
            }
        });
    }
    
    
    
    
    function SaveBudgetPlan(id) {
        var year = document.getElementById('year').value;
        var ncid = document.createElement("ncid").value;
        var amount = document.getElementById(id+"amount").value;
        $.ajax({
            type: "POST",
            url: "Budget?action=SaveBudgetPlan&ncid=" + ncid+"&amount="+amount+"&year="+year+"&month="+id,
            success: function (msg) {
                alert(msg);
                


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

                        <span class="section">Budget Plan</span>
                        <div class="item form-group" >
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select State
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Function
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Cost Center
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccid" id="ccid" onchange="loadNominal()">
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Nominal Code
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ncid" id="ncid">
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Year
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="year" id="year" onchange="loadData()">
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
                    <div id="budgetdetails" class="hidden">
                    <center>
                        <table style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 30%;">Month</th>
                                    <th style="width: 30%;">Budget</th>
                                    <th style="width: 30%;">Action</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        January
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="1amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="1save" type="button" onclick="SaveBudgetPlan(1)" class="btn btn-success">Save</button>
                                        <button id="1update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="1id" />
                                    </td>
                                    <td><a href="#" id="1changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        February
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="2amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="2save" type="button" onclick="SaveBudgetPlan(2)" class="btn btn-success">Save</button>
                                        <button id="2update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="2id" />
                                    </td>
                                    <td><a href="#" id="2changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        March
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="3amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="3save" type="button" onclick="SaveBudgetPlan(3)" class="btn btn-success">Save</button>
                                        <button id="3update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="3id" />
                                    </td>
                                    <td><a href="#" id="3changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        April
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="4amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="4save" type="button" onclick="SaveBudgetPlan(4)" class="btn btn-success">Save</button>
                                        <button id="4update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="4id" />
                                    </td>
                                    <td><a href="#" id="4changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        May
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="5amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="5save" type="button" onclick="SaveBudgetPlan(5)" class="btn btn-success">Save</button>
                                        <button id="5update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="5id" />
                                    </td>
                                    <td><a href="#" id="5changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        June
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="6amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="6save" type="button" onclick="SaveBudgetPlan(6)" class="btn btn-success">Save</button>
                                        <button id="6update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="6id" />
                                    </td>
                                    <td><a href="#" id="6changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        July
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="7amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="7save" type="button" onclick="SaveBudgetPlan(7)" class="btn btn-success">Save</button>
                                        <button id="7update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="7id" />
                                    </td>
                                    <td><a href="#" id="7changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        August
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="8amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="8save" type="button" onclick="SaveBudgetPlan(8)" class="btn btn-success">Save</button>
                                        <button id="8update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="8id" />
                                    </td>
                                    <td><a href="#" id="8changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        September
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="9amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="9save" type="button" onclick="SaveBudgetPlan(9)" class="btn btn-success">Save</button>
                                        <button id="9update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="9id" />
                                    </td>
                                    <td><a href="#" id="9changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        October
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="10amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="10save" type="button" onclick="SaveBudgetPlan(10)" class="btn btn-success">Save</button>
                                        <button id="10update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="10id" />
                                    </td>
                                    <td><a href="#" id="10changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        November
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="11amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="11save" type="button" onclick="SaveBudgetPlan(11)" class="btn btn-success">Save</button>
                                        <button id="11update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="11id" />
                                    </td>
                                    <td><a href="#" id="11changed">Changed</a></td>
                                </tr>
                                <tr>
                                    <td>
                                        December
                                    </td>
                                    <td>
                                        <input class="form-control col-md-7 col-xs-12" id="12amount" placeholder="Enter Amount" style="width: 80%;" type="number">
                                    </td>
                                    <td>
                                        <button id="12save" type="button" onclick="SaveBudgetPlan(12)" class="btn btn-success">Save</button>
                                        <button id="12update" type="button" class="btn btn-warning">Update</button>
                                        <input type="hidden" value="" id="12id" />
                                    </td>
                                    <td><a href="#" id="12changed">Changed</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </center>

                    </div>

                </div>
            </div>
        </div>


    </div>
</div>

<%@include file="../../../template/footer.jsp"%>
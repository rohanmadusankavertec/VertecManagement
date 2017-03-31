
<%@page import="com.vertec.hibe.model.NominalCode"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%
    List<State> state = (List<State>) request.getAttribute("state");
    List<FunctionData> fd = (List<FunctionData>) request.getAttribute("functionData");
    List<CostCenter> cs = (List<CostCenter>) request.getAttribute("costcenter");
    List<NominalCode> nc = (List<NominalCode>) request.getAttribute("nominalCode");
    List<String> by = (List<String>) request.getAttribute("years");


%>


<script type="text/javascript">
    function loadFunctionData() {
        $("#functionid").empty();
        var s1 = document.getElementById('functionid');
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
        $("#ccId").empty();
        var s1 = document.getElementById('ccId');
        var t1 = document.createElement("option");

        t1.value = "";
        t1.innerHTML = "Select Cost Center";
        s1.appendChild(t1);
        var functionId = document.getElementById('functionid').value;
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
        $("#nomiId").empty();
        var s1 = document.getElementById('nomiId');
        var t1 = document.createElement("option");

        t1.value = "";
        t1.innerHTML = "Select Nominal Code";
        s1.appendChild(t1);
        var ccId = document.getElementById('ccId').value;
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
</script>




<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Budget Plan Report
                <small>

                </small>
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>


    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>

                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="AccountReport?action=BudgetPlan" method="POST">

                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select State <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="stateid" id="stateid" onchange="loadFunctionData();">
                                    <option selected="true" disabled value="">Select State</option>
                                    <%                                        
                                        for (State s : state) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Function <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="functionid" id="functionid" onchange="loadCostCenter();">
                                    <option selected="true" disabled value="">Select Function</option>
                                    <%
                                        for (FunctionData s : fd) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>

                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Cost Center <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccId" id="ccId" onchange="loadNominal();">
                                    <option selected="true" disabled value="">Select Cost Center</option>
                                    <%
                                        for (CostCenter s : cs) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getCode() + " - " + s.getName()%></option>
                                    <%
                                        }
                                    %>

                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Nominal Code <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="nomiId" id="nomiId" >
                                    <option selected="true" disabled value="">Select Nominal Code</option>
                                    <%
                                        for (NominalCode s : nc) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getCode()+ " - " + s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group" style="padding-top: 50px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Budget Years <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="byear" id="byear" >
                                    <option selected="true" disabled value="">Select Budget Years</option>
                                    <%
                                        for (String s : by) {
                                    %>
                                    <option value="<%=s%>"><%=s%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" style="float: right;" class="btn btn-success">Submit</button>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>





    <center>

    </center>
    <div class="clearfix"></div>
    <div class="clearfix"></div>
</div>




<%@include file="../../../template/footer.jsp"%>
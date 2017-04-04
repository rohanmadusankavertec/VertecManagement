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
        List<Integer> yList = (List<Integer>) request.getAttribute("year");

%>
<script type="text/javascript">
    
    
    function sm_warning(text) {
        BootstrapDialog.show({
            title: 'Warning',
            type: BootstrapDialog.TYPE_WARNING,
            message: text,
            size: BootstrapDialog.SIZE_SMALL
        });
    }


    function nom_Success(text) {
        BootstrapDialog.show({
            title: 'Notification',
            type: BootstrapDialog.TYPE_SUCCESS,
            message: text,
            size: BootstrapDialog.SIZE_NORMAL
        });
    }
    
    
    function loadFunctionData(){
//        $("").empty();
        var sid = document.getElementById("stateid").value;
//        alert(sid);
        $.ajax({
            type: "POST",
            url: "CostCenter?action=getFunctionData&sid="+sid,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.functionData;


                var fdata = document.getElementById("fdata");
                var ihtml = "<option disabled  selected='true'>Select Cost Center</option>";
                
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "'>" + arrLn1[f].fname+"</option>"
                }
                fdata.innerHTML = ihtml;


            }
        });
        
    }
    
    function loadCostCenter(){
//        $("").empty();
//alert("check..");
        var ccid = document.getElementById("fdata").value;
//        alert(ccid);
        $.ajax({
            type: "POST",
            url: "ActualCost?action=getCostCenter&ccid="+ccid,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.CostCenter;


                var cdata = document.getElementById("ccenter");
                var ihtml = "<option disabled  selected='true'>Select Cost Center</option>";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "'>" + arrLn1[f].cname+"</option>"
                }
                cdata.innerHTML = ihtml;


            }
        });
        
    }
    function loadNominalCode(){
//        $("").empty();
//alert("check..");
        var ccid = document.getElementById("ccenter").value;
        
        $.ajax({
            type: "POST",
            url: "ActualCost?action=getNominalCode&ccid="+ccid,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.NominalCode;


                var cdata = document.getElementById("nominalCode");
                var ihtml = "<option disabled  selected='true'>Select nominal code</option>";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "'>" + arrLn1[f].ncname+"</option>"
                }
                cdata.innerHTML = ihtml;


            }
        });
        
    }
    function loadTable(){
        
        
        var year = document.getElementById('year').value;
        var month = document.getElementById('month').value;
        var nomoid = document.getElementById('nominalCode').value;
//        alert(year+" "+month+" "+nomoid);
            $.ajax({
            type: "POST",
            url: "ActualCost?action=getActualCostTable&nominalCode="+nomoid+"&year="+year+"&month="+month,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arr = reply.actualTable;
                var tb = document.getElementById("actualcostItem");
                var data="";
                for (var f = 0; f < arr.length; f++) {
                    
                    data =data+ "<tr id='" + arr[f].id + "'><td><input style='width: 80%;' type='text' id='ref"+arr[f].id+"' value='"+arr[f].refere+"'/></td><td><input style='width: 90%;' type='text' id='des"+arr[f].id+"' value='"+arr[f].descrip+"'/></td><td><input style='width: 60%;' type='number' id='amt"+arr[f].id+"' value='"+arr[f].amount+"'/></td><td><button id='' type='button' onclick='updateActualCost("+arr[f].id+")' class='btn btn-warning'>Update</button></td></tr>";
                    tb.innerHTML = data;
                }    
            }
        });
        
        document.getElementById('loadTable').className="";
    }
    
    function updateActualCost(rowId){
//        alert("calling");
        var ref = document.getElementById("ref"+rowId).value; 
        var des = document.getElementById("des"+rowId).value; 
        var amt = document.getElementById("amt"+rowId).value; 
        var rowid =rowId;
//        alert(ref+"-r "+des+"-d "+amt+"-a "+rowid+"-row");
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;

                if (reply === "Success") {
                    
                    nom_Success("Actual Cost is Updated Successfully ");
                    setTimeout("location.href = 'ActualCost?action=ActualCostManage';", 1500);
                } else {
                    sm_warning("Actual Cost is not Updated, Please Try again.");
                }
            }
        };
        xmlHttp.open("POST", "ActualCost?action=UpdateActualCost&id=" + rowid + "&ref=" + ref + "&des=" + des + "&amt=" + amt, true);
        xmlHttp.send();
        
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
                            <a href="#"  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>

                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="ActualCost?action=saveActualCost" method="post" class="form-horizontal form-label-left" validate>

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
                                <select class="form-control" name="fdata" id="fdata"  required="required" onchange="loadCostCenter()">
                                    <option selected="true"  disabled value="">Select Function Data</option>
                                    
                                    
                                </select>                              
                            </div>
                        </div>        
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Cost Center  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccenter" id="ccenter"  required="required" onchange="loadNominalCode()" >
                                    <option selected="true"  value="">Select Cost Center </option>
                                    
                                    
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
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Year  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="year" id="year"  required="required" >
                                    <option selected="true" disabled value="">Select year </option>
                                    <%                                        
                                        for (Integer i : yList) {
                                    %>
                                    <option value="<%=i%>" ><%=i%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Month  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="month" id="month"  required="required" onchange="loadTable()" >
                                    <option selected="true" disabled value="">Select month </option>
                                    <option   value="1">January </option>
                                    <option   value="2">February </option>
                                    <option   value="3">March </option>
                                    <option   value="4">April </option>
                                    <option   value="5">May </option>
                                    <option   value="6">June </option>
                                    <option   value="7">July </option>
                                    <option   value="8">August </option>
                                    <option   value="9">September </option>
                                    <option   value="10">October </option>
                                    <option   value="11">November </option>
                                    <option   value="12">December </option>
                                    
                                    
                                </select>                              
                            </div>
                        </div>        
                                
<!--                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Amount <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="amount" placeholder="Enter Amount" required="required" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Date<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="date" placeholder="Select the date" required="required" type="date">
                            </div>
                        </div> 
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Reference no<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="reference" placeholder="Enter the Number" required="required" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="descrip" placeholder="Select the date" required="required" type="text">
                            </div>
                        </div>         -->



                        <div class="x_content">
                            <div id="loadTable" class="hidden">     
                    <div class="table-responsive">
                        
                        <table style="width: 100%;margin-top: 10px;">
                            <thead>
                                <tr class="headings">

                                    
                                    
                                    <th> Reference No </th>
                                    <th>Description </th>
                                    <th> Amount</th>
                                    
                                    
                                    <th class=" no-link last"><span class="nobr">Action</span></th>
                                    

                                </tr>
                            </thead>

                            <tbody id="actualcostItem">
                                
                            </tbody>

                        </table>
                    </div>
                            </div>
                </div>
                        <div class="ln_solid"></div>
<!--                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>-->
                    </form>
                </div>
            </div>
        </div>
    </div>

    
    
</div>

<%@include file="../../../template/footer.jsp"%>
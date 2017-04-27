<%-- 
    Document   : AddEstimateCost
    Created on : Oct 21, 2016, 10:24:55 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>


<script type="text/javascript">
    
//    -------------------------------------------------------
    
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
    
//  ------------------------------------------------------------  
//    loadEmployee();
    function loadEmployee() { // load employee to select element
        $("#employee").empty();
        $.ajax({
            type: "POST",
            url: "../../Estimatecost?action=getEmployees",
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.employee;


                var emp = document.getElementById("employee");
                var ihtml = "";
                 ihtml += "<option selected='true'>Select Employee</option>";
                for (var f = 0; f < arrLn1.length; f++) {
                    
                    ihtml += "<option value='" + arrLn1[f].id + "~" +arrLn1[f].firstName+ "~" +arrLn1[f].lastName+ "~" +arrLn1[f].salary+ "'>" + arrLn1[f].firstName +  " "+ arrLn1[f].lastName +"</option>";
                }
                emp.innerHTML = ihtml;

//                for (var f = 0; f < arrLn1.length; f++) {
//                    var t = document.createElement("option");
//                    var val = arrLn1[f].id;
//                    t.value = val + "~"+arrLn1[f].feature;
//                    t.innerHTML = arrLn1[f].feature;
//                    feature.appendChild(t);
//                }
            }
        });
    }
    
    var pkgf = [];
    function addEmployeeDetails() { // add employee details to tbale
        var featureid = document.getElementById("employee").value;
        if(featureid!=="Select Employee"){
        
        var fea = featureid.split("~");


        var tbl = document.getElementById("employeeItemBody");

        var data = tbl.innerHTML;
        var bool = true;

        for (var i = 0; pkgf.length > i; i++) {
            if (pkgf[i] === fea[0]) {
                bool = false;
                sm_warning("This Customer already exists.");
            }
        }

        if (bool) {
            pkgf.push(fea[0]);
            var data = data + "<tr id='featable" + fea[0] + "'><td>" + fea[1] + " " + fea[2] + "</td><td id='sal"+fea[0]+"'>" + fea[3] + "</td><td><input type='date' id='df"+fea[0]+"' onchange='getValues()'/></td><td><input type='date' id='dt"+fea[0]+"' onchange='getValues()'/></td><td><a href='#' id='deleteUser' onclick='DeleteEmployee(" + fea[0] + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
            tbl.innerHTML = data;
        }
        }else{
            sm_warning("Select an Employee...");
        }
    }
    function sm_warning(text) {
        BootstrapDialog.show({
            title: 'Warning',
            type: BootstrapDialog.TYPE_WARNING,
            message: text,
            size: BootstrapDialog.SIZE_SMALL
        });
    }
    function DeleteEmployee(id) { // delete employee from table

        var elem = document.getElementById("featable" + id);
        elem.parentElement.removeChild(elem);
        for (var i = 0; pkgf.length > i; i++) {
            if (pkgf[i].indexOf(id) > -1) {
                pkgf.shift(pkgf[i]);
                pkgf.splice(i, 1);
            }
        }
        getValues();
    }

function getValues(){ // calculation of cost  for a project
    var arr=[];
    
    
    var total=0;
    for (var i = 0; pkgf.length > i; i++) {
        var salary = document.getElementById("sal"+pkgf[i]).innerHTML;
        var df = document.getElementById("df"+pkgf[i]).value;
        var dt = document.getElementById("dt"+pkgf[i]).value;
        var dff=new Date(df);
        var dtt=new Date(dt);
            var timeDiff = Math.abs(dtt.getTime() - dff.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
            
            if(diffDays > 0){
                var sal=(salary*diffDays)/30; 
//                arr.push(sal);
                total+=parseInt(sal);
            }
            
        //alert("Employee ID : "+pkgf[i]+" Salary :"+salary+" "+diffDays+ " "+sal);
        
        
        
    }
    
//    alert(arr.toString());
    document.getElementById("tdc").innerHTML=total;
    
    calculateCost();
}

function getDiff(){ // get time defference  
    for (var i = 0; pkgf.length > i; i++) {
        var df = document.getElementById("df"+pkgf[i]).value;
        var dt = document.getElementById("dt"+pkgf[i]).value;
        var dff=new Date(df);
        var dtt=new Date(dt);
            var timeDiff = Math.abs(dtt.getTime() - dff.getTime());
            var dd = Math.ceil(timeDiff / (1000 * 3600 * 24));
            
       return dd;      
    }
}

   
    function calculateCost(){ // get total extimate cost 
        
        var ec= document.getElementById("ecost").value;
        var mc= document.getElementById("mcost").value;
        var oc= document.getElementById("ocost").value;
        var tdc= document.getElementById("tdc").innerHTML;
        if(ec === ""){
            ec=0;
        }
        if(mc === ""){
            mc=0;
        }
        if(oc === ""){
            oc=0;
        }
        if(tdc === ""){
            tdc=0;
        }
        var escost= parseFloat(ec)+parseFloat(mc)+parseFloat(oc)+parseFloat(tdc);
        
        document.getElementById("tesc").innerHTML=escost;
        document.getElementById("tdc").innerHTML=tdc;
    }
    
    function SaveEstimateCost() { // sent estimate cost details to controller class to save

        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;

                if (reply === "Success") {
                    
                    nom_Success("Estimate Cost is added Successfully ");
                    setTimeout("location.href = 'Estimatecost?action=addestimatecost';", 1500);
                } else {
                    sm_warning("Estimate Cost is not added, Please Try again.");
                }
            }
        };
        var ecost = document.getElementById("ecost").value;
        var mgcost = document.getElementById("mcost").value;
        var othcost = document.getElementById("ocost").value;
        var tdcost = document.getElementById("tdc").innerHTML;
        var tescost = document.getElementById("tesc").innerHTML;
        var pid = document.getElementById("proposal").value;
//          alert(ecost+" "+mgcost+" "+othcost+" "+tdcost+" "+tescost+ "");
        var bool =true;
        for (var i = 0; pkgf.length > i; i++) {
                var df = document.getElementById("df"+pkgf[i]).value;
                
                var dt = document.getElementById("dt"+pkgf[i]).value;
                var dff=new Date(df);
                var dtt=new Date(dt);
                
                var s=dtt.getTime()- dff.getTime();
                
                    if(s<0){
                        bool=false;
                    }
               
        }
        
        if(bool){
            xmlHttp.open("POST", "Estimatecost?action=saveEstimateCost&ecost=" + ecost + "&mcost=" + mgcost + "&ocost=" + othcost + "&totdc=" + tdcost + "&totec=" + tescost +"&ppid=" +pid , true);
            xmlHttp.send();
            
        }
        else{
            sm_warning("Please Chack the dates and Try again.");
        }        
    }
    
</script>
<%
    List<Employee> employee = (List<Employee>) request.getAttribute("employee");
  %>
  
<%
    List<ProjectProposal> proposal = (List<ProjectProposal>) request.getAttribute("proposal");
  %>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Estimate Cost
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New Estimate Cost<small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                            
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                
                
                
                
                <div class="x_content">
                    <form  method="post" class="form-horizontal form-label-left" validate>
                        <span class="section"></span>
                        
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12"> Proposal Project</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="proposal" id="proposal" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                <option selected='true'>Select Project</option>
                                <% for (ProjectProposal p : proposal) {%>
                                    <option value="<%=p.getId()%>"><%=p.getProposalName()+"-"+p.getCustomerId().getCompanyName()%></option>
                                    <%}%>
                                </select>     
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Utility Cost:
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="ecost" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="ecost" onkeyup="getValues()" placeholder="Enter Electricity Cost" required="required" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Management Cost:
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="mcost" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="mcost"  onkeyup="getValues()" placeholder="Enter Management Cost" required="required" type="number">
                            </div>
                        </div> 
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Other Cost:
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="ocost" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="ocost"  onkeyup="getValues()" placeholder="Enter Other Cost" required="required" type="number">
                            </div>
                        </div>
                        
                        <div class="ln_solid"></div>
                         
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12"> Employee</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="employee" id="employee" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                <option selected='true'>Select Employee</option>
                                    <% for (Employee s : employee) {%>
                                    <option value="<%=s.getId()+"~"+s.getFirstName()+"~"+s.getLastName()+"~"+s.getSalary() %>"><%=s.getFirstName()+" "+s.getLastName() %></option>
                                    <%}%>
                                </select>     



                            </div>
                            <input type="button" value="Add" class="btn btn-success" onclick="addEmployeeDetails()"/>           
                        </div>



                        <div class="row" style="padding-top: 20px;">
                            <center>
                                <div class="col-xs-12 table">
                                    <table class="table table-striped" id="featureTable" style="width: 500px;">
                                        <thead>
                                            <tr>
                                                
                                                <th>Employee Name</th>
                                                <th>Salary</th>
                                                <th>From Date</th>
                                                <th>To Date</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody id="employeeItemBody">


                                        </tbody>
                                    </table>
                                    
                                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Total Developers Cost:
                            </label>
                             <label class="control-label col-md-6 col-sm-6 col-xs-12" id="tdc" name="totdc" for="name" style="text-align: left;color: #843534;font-size: 15px;">0000.00
                            </label>
                        </div>
                                    
                                </div>
                            </center>
                        </div>    

                            <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Total Estimate Cost:
                            </label>
                                <label class="control-label col-md-6 col-sm-6 col-xs-12" id="tesc" name="totec" for="name" style="text-align: left;color: #843534;font-size: 15px;">0000.00
                            </label>
<!--                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="price" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="price"  placeholder="Enter Price" required="required" type="number">
                            </div>-->
                            </div>


                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                                <button id="send" type="button" onclick="SaveEstimateCost()" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>

</div>






<%@include file="../../template/footer.jsp"%>
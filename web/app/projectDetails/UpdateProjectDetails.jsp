<%-- 
    Document   : UpdateRpojectDetails
    Created on : Nov 1, 2016, 2:40:40 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Status"%>
<%@page import="com.vertec.hibe.model.Employee"%>
<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<script>
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
    
    //-------------------------------------------------------------------------------------
    function viewPDetailsByProjectName(){ // view project details according to project
      
        var proNameId = document.getElementById('projectName').value;
        
        
        window.location = 'ProjectDetails?action=loadEmpStatus&proNameId=' + proNameId ;
    }
     
    function viewDiv(){ //show hidden feilds
        var ch = document.getElementById('projectName').value;
        if(ch !== null){
            document.getElementById('ser').className = '';
        }
    }
    
    function loadDetails() { // view project details 
        
        var proNameId = document.getElementById('projectName').value;
        
        $.ajax({
            type: "POST",
            url: "ProjectDetails?action=loadEmpStatus&proNameId=" + proNameId,
            
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                 
                document.getElementById("fromDate").value=reply.fmdate;
               document.getElementById("toDate").value=reply.todate;
               
               if(reply.stt==null){
                    document.getElementById("status").value="";
               }else{
                    document.getElementById("status").value=reply.stt;
               }
               
               if(reply.rmk==null){
                    document.getElementById("remark").value="";
               }else{
                    document.getElementById("remark").value=reply.rmk;
               }
               loadEmployee();
            }
        });
        
    }
    var arr = [];
    var firstArr = [];
    function loadEmployee(){ // load emploee of project to table
        var proNameId = document.getElementById('projectName').value;
       
        $.ajax({
            type: "POST",
            url: "ProjectDetails?action=GetEmployees&proNameId=" + proNameId,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.employee;

                var emp = document.getElementById("employeeItemBody");
                var ihtml = "";
                 
                for (var f = 0; f < arrLn1.length; f++) {

                    firstArr.push(arrLn1[f].id);
                            ihtml += "<tr id='emptable" + arrLn1[f].id + "'><td>"+arrLn1[f].firstName+" "+ arrLn1[f].lastName+"</td><td><input id='fromDate"+arrLn1[f].id+"' type='date' value='"+arrLn1[f].fdate+"'></td><td><input id='toDate"+arrLn1[f].id+"' type='date' value='"+arrLn1[f].todate+"'></td><td><a href='#' id='"+arrLn1[f].id+"' onclick='DeleteEmployee(" + arrLn1[f].id + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                    }
                
                emp.innerHTML = ihtml;
                
            }
        });
    }

    function updateProjectDetails(){ // send changed data of project details to controller to update
        
        for(var i=0; i<firstArr.length;i++){
            
            var fdate=document.getElementById("fromDate"+firstArr[i]).value;
            var tdate=document.getElementById("toDate"+firstArr[i]).value;
            
            
            var arr2=[];
                    arr2.push(firstArr[i]);
                    arr2.push(fdate);
                    arr2.push(tdate);
                    arr.push(arr2);
            
        }
        
        
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;
                if (reply === "Success") {
                    nom_Success("Process Updated Successfully ");
                     setTimeout("location.href = 'ProjectDetails?action=ViewProDetails';", 1500);
                } else {
                    sm_warning("Process is not Updated, Please Try again.");
                }
            }
        };
        
 
        var pDId = document.getElementById('projectName').value;
        var fDate = document.getElementById('fromDate').value;
        var toDate = document.getElementById('toDate').value;
        var st = document.getElementById('status').value;
        var rm = document.getElementById('remark').value;
        
        
        
        if(pDId !== "" && fDate !=="" && toDate !=="" && st !=="" && rm !=="" && arr !=null){
            
            xmlHttp.open("POST", "ProjectDetails?action=UpdateProjectDetails&proDId=" +pDId + "&fdate=" +fDate + "&tdate=" +toDate + "&status=" +st + "&remark=" +rm+"&EmpList="+arr , true);
            xmlHttp.send();
        }
        else{
            sm_warning("Please Fill All Fields...");
        }
        
        
    }
    function DeleteEmployee(id) { // delete employee from table

        var elem = document.getElementById("emptable" + id);
        elem.parentElement.removeChild(elem);
        for (var i = 0; firstArr.length > i; i++) {
            if (firstArr[i]===id) {
                //pkgf.shift(pkgf[i]);
                firstArr.splice(i, 1);
            }
        }
    }
    function addEmployeeTbl() { // add employee details to table
        var featureid = document.getElementById("employee").value;
        if(featureid!=="Select Employee"){
        
        var fea = featureid.split("~");

        var tbl = document.getElementById("employeeItemBody");

        var data = tbl.innerHTML;
        var bool = true;

        for (var i = 0; firstArr.length > i; i++) {
            if (firstArr[i] === fea[0]) {
                bool = false;
                sm_warning("This Feature is already exist.");
            }
        }

        if (bool) {
            firstArr.push(fea[0]);
            var data = data + "<tr id='emptable" + fea[0] + "'><td>" + fea[1] + " " + fea[2] + "</td><td><input  type='date' id='fromDate"+fea[0]+"' /></td><td><input type='date' id='toDate"+fea[0]+"' /></td><td><a href='#' id='deleteUser' onclick='DeleteEmployee(" + fea[0] + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
            tbl.innerHTML = data;
            
        }
        }else{
            sm_warning("Select an Employee...");
        }
    }
    
</script>




<%
    List<ProjectProposal> proposalList = (List<ProjectProposal>) request.getAttribute("proposalList");

    List<Employee> empList = (List<Employee>) request.getAttribute("empList");

    List<Status> statusList = (List<Status>) request.getAttribute("statusList");
%>
<div class="">
    
    <div class="page-title">
        <div class="title_left">
            <h3>
                Project Details
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
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>

                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="Expenses?action=AddExpenses" method="post" class="form-horizontal form-label-left" validate>

                        </p>
                        <span class="section">Update Project Details</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Name: <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="projectName" id="projectName" onchange="viewDiv();loadDetails()" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected='true' disabled="true" value="" >Select Project Name</option>
                                    <% for (ProjectProposal p : proposalList) {%>
                                    <option value="<%=p.getId()%>"><%=p.getProposalName()+"-"+p.getCustomerId().getCompanyName()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                                 
                        <div class="ln_solid"></div>
                        <!--<div class="hidden" style="padding-top: 50px;" id="ser">-->
                        <div class="hidden" style="padding-top: 10px;" id="ser">
                            
                        <div class="item form-group" style="">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Duration  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="fromDate" class="form-control col-md-3 col-xs-6"  data-validate-words="1" name="fromDate"  placeholder="Start Date" required="required" type="date" style="width: 150px" ><input id="toDate" class="form-control col-md-3 col-xs-6"  data-validate-words="1" name="toDate"  placeholder="End Date" required="required" type="date" style="width: 150px;margin-left: 25px" >
                                <!--<textarea id="description" name="description" class="form-control col-md-7 col-xs-12" placeholder="Enter Project Name" required="required" ></textarea>-->
                            </div>
                        
                        </div>  
                        
                        <div class="ln_solid"></div>
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Allocate Employee</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="employee" id="employee" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                <option selected='true'>Select Employee</option>
                                
                                    <% for (Employee e : empList) {
                                    
                                    
                                    %>
                                    
                                    <option value="<%=e.getId()+"~"+e.getFirstName()+"~"+e.getLastName()%>"><%=e.getFirstName()+" "+e.getLastName() %></option>
                                    <%}%> 
                                </select> 
                            </div>
                                <input type="button" value="Add" class="btn btn-success" onclick="addEmployeeTbl()"/>           
                        </div>
                               
                        <div class="row" style="padding-top: 20px;">
                            <center>
                                <div class="col-xs-12 table">
                                    <table class="table table-striped" id="featureTable" style="width: 500px;">
                                        <thead>
                                            <tr>
                                                
                                                <th>Employee Name</th>
                                                <th>From Date</th>
                                                <th>To Date</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody id="employeeItemBody">


                                        </tbody>
                                    </table>
                                    
                                    <div class="item form-group">
                            
                        </div>
                                    
                                </div>
                            </center>
                        </div>            
                          
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12"> Status</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="status" id="status" required="required" >
                                 <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                 <option selected="true" disabled="true" value="">Select Status</option>
                                  <% for (Status s : statusList) {%>
                                 <option value="<%=s.getId()%>"><%=s.getStatus()%></option>
                                    <%}%>
                                </select> 
                            </div>
                            <!--<input type="button" value="Add" class="btn btn-success" onclick="addEmployeeDetails()"/>-->           
                        </div>
                                
                                
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Remark  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="remark" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="remark"  placeholder="Enter Remark" required="required" type="text" >
                            </div>
                        </div>
                        
                
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="button" onclick="updateProjectDetails()" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="../../template/footer.jsp"%>
<%-- 
    Document   : AddProjectProcess
    Created on : Oct 25, 2016, 4:28:03 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectDetails"%>
<%@page import="org.hibernate.criterion.ProjectionList"%>
<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

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

    //-------------------------------------------------------------------

    var tbArr = [];
    var id = 0;
    function loadProcessToTable() { // load process details to table

        var tbl = document.getElementById("processItemBody");

        var pss = document.getElementById("processName").value;
        var dsc = document.getElementById("description").value;
        var du = document.getElementById("duration").value;

        var data = "";
        if (pss !== "" & dsc !== "" & du !== "") {

            var rowArr = [];
            rowArr.push(pss);
            rowArr.push(dsc);
            rowArr.push(du);
            rowArr.push(tbArr.length);
            tbArr.push(rowArr);

            for (var i = 0; i < tbArr.length; i++) {
                tbArr[i][3] = i;
                var data = data + "<tr id='tbl" + i + "'><td>" + tbArr[i][0] + "</td><td>" + tbArr[i][1] + "</td><td>" + tbArr[i][2] + "</td><td><a href='#' id='deleteUser' onclick='deleteTableRow(" + i + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                //tbl.innerHTML = data;
            }

            tbl.innerHTML = data;

            document.getElementById("processName").value = "";
            document.getElementById("description").value = "";
            document.getElementById("duration").value = "";
        } else {
            sm_warning("Please fill all Fields..");
        }


    }

    function deleteTableRow(rowId) {// delete process details from table
        for (var i = 0; tbArr.length > i; i++) {
            var v1 = parseInt(tbArr[i][3]);
            var v2 = parseInt(rowId);

            if (v1 === v2) {
                tbArr.splice(i, 1);
            }
        }
        document.getElementById("tbl" + rowId).remove();
    }
    //-------------------------------------------------------------------------

    function saveProjectProcess() {// save process details into database

        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function ()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;
                if (reply === "Success") {
                    nom_Success("Process Saved Successfully ");
                    setTimeout("location.href = 'ProjectProcess?action=getProjectName';", 1500);
                } else {
                    sm_warning("Process is not saved, Please Try again.");
                }
            }
        };

        var pDId = document.getElementById('projectName').value;
        //alert(pDId);
        if (pDId !== "") {

            xmlHttp.open("POST", "ProjectProcess?action=SaveProcess&processList=" + tbArr + "&proDId=" + pDId, true);
            xmlHttp.send();
        } else {
            sm_warning("Please select the project name..");
        }
    }





</script>


<% List<ProjectDetails> porjectDList = (List<ProjectDetails>) request.getAttribute("projectDetails"); %>

<div class="">
    <!--    <div class="page-title">
            <div class="title_left">
                <h3>
                    Customer Registration
                </h3>
            </div>
    
    
        </div>-->
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

                    <form action="#" method="post" class="form-horizontal form-label-left" validate>

                        </p>
                        <span class="section">Add Project Process Details</span>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Name:
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="projectName" id="projectName" required="required" >
                                    <!-- <option selected="true" disabled="true">Select Employee</option> -->
                                    <!--<option selected="true">Select Feature</option>-->
                                    <option selected='true' disabled="true" value="" >Select Project Name</option>
                                    <% for (ProjectDetails c : porjectDList) {%>
                                    <option value="<%=c.getId()%>"><%=c.getProjectProposalId().getProposalName()%></option>
                                    <%}%>
                                </select>     



                            </div>
                        </div>

                        <div class="ln_solid"></div>

                        <div class="item form-group" style="margin-top: 25px">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Process  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="processName" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="processName" pattern="[A-z a-z]{2,}" placeholder="Enter Project Process" required="required" type="text" >
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description  <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <textarea id="description" name="description" class="form-control col-md-7 col-xs-12" ></textarea>
                                <!--<input id="pproposalName" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="pproposalName" pattern="[A-z a-z]{2,}" placeholder="Enter Project Name" required="required" type="text">-->
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Duration: <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="duration" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="duration"  placeholder="Enter Duration" required="required" type="number" style="size: 100px">
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="button" class="btn btn-success" onclick="loadProcessToTable()">Add</button>
                            </div>
                        </div>


                        <div class="row" style="padding-top: 20px;">
                            <center>
                                <div class="col-xs-12 table">
                                    <table class="table table-striped" id="featureTable" style="width: 500px;">
                                        <thead>
                                            <tr>

                                                <th>Process</th>
                                                <th>Description</th>
                                                <th>Duration</th>
                                                <td>Delete</td>

                                            </tr>
                                        </thead>
                                        <tbody id="processItemBody">


                                        </tbody>
                                    </table>

                                    <div class="item form-group">

                                    </div>

                                </div>
                            </center>
                        </div>  




                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="button" onclick="saveProjectProcess()" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>



<%@include file="../../template/footer.jsp"%>
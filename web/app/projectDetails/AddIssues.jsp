<%-- 
    Document   : AddIssues
    Created on : 27-Oct-2016, 10:07:50
    Author     : Imansa
--%>

<%@page import="com.vertec.hibe.model.Issues"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.ProjectDetails"%>
<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>


<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Add Issue<small></small></h2>
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
                function CompleteIssue(id) { //update state of issue
                    BootstrapDialog.show({
                        message: 'Do you want to set this issue as complete?',
                        closable: false,
                        buttons: [{
                                label: 'Yes',
                                action: function (dialogRef) {
                                    dialogRef.close();
                                    var xmlHttp = getAjaxObject();
                                    xmlHttp.onreadystatechange = function ()
                                    {
                                        if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                                        {
                                            var reply = xmlHttp.responseText;
                                            console.log(reply);
                                            if (reply === "Success") {
                                                nom_Success("Issue successfully completed");
                                                setTimeout("location.href = 'Issue?action=viewAddIssue';", 1500);
                                            } else {
                                                sm_warning("Unable to complete issue, Please Try again");
                                            }
                                        }
                                    };
                                    xmlHttp.open("POST", "Issue?action=completeIssue&id=" + id, true);
                                    xmlHttp.send();

                                }
                            }, {
                                label: 'No',
                                action: function (dialogRef) {
                                    dialogRef.close();
                                }
                            }]
                    });

                }
            </script>
            <%
                List<ProjectDetails> proposal = (List<ProjectDetails>) request.getAttribute("projects");
            %>
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

            <form action="Issue?action=AddIssue" method="post" class="form-horizontal form-label-left" validate>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project:
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <select class="form-control" name="comboBox" id="id" required="required" >

                            <option selected='true'>Select Project</option>
                            <% for (ProjectDetails p : proposal) {%>
                            <option value="<%=p.getId()%>"><%=p.getProjectProposalId().getProposalName()%></option>
                            <%}%>
                        </select> 
                    </div>
                </div>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Describe Issue
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <textarea class="form-control col-md-7 col-xs-12" name = "textArea"></textarea>
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
    <%
        List<Issues> issueList = (List<Issues>) request.getAttribute("issues");
    %>                   
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Service <small>up to now</small></h2>
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
                                    <th>Project Name</th>
                                    <th>Issue</th>
                                    <th class=" no-link last"><span class="nobr">Update</span>
                                    <th class=" no-link last"><span class="nobr">Set as Solved</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%                                    for (Issues i : issueList) {
                                %>
                                <tr>
                                    <td class=" "><%=i.getProjectDetailsId().getProjectProposalId().getProposalName()%></td>
                                    <td class=" "><%=i.getIssue()%></td>
                                    <td class=" last"> 
                                        <form name="form1" method="post" action="Issue?action=selectIssueForUpdate"><input type="hidden" name="issue" value="<%=i.getId()%>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">
                                            </button>
                                        </form>
                                    </td>
                                    <td class=" last"> 
                                        <button id="send" onclick="CompleteIssue(<%=i.getId()%>);" type="button" class="btn btn-success">Complete</button>
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



<script>
    // initialize the validator function
    validator.message['date'] = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
    $('form')
            .on('blur', 'input[required], input.optional, select.required', validator.checkField)
            .on('change', 'select.required', validator.checkField)
            .on('keypress', 'input[required][pattern]', validator.keypress);

    $('.multi.required')
            .on('keyup blur', 'input', function () {
                validator.checkField.apply($(this).siblings().last()[0]);
            });

    // bind the validation to the form submit event
    //$('#send').click('submit');//.prop('disabled', true);

    $('form').submit(function (e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }

        if (submit)
            this.submit();
        return false;
    });

    /* FOR DEMO ONLY */
    $('#vfields').change(function () {
        $('form').toggleClass('mode2');
    }).prop('checked', false);

    $('#alerts').change(function () {
        validator.defaults.alerts = (this.checked) ? false : true;
        if (this.checked)
            $('form .alert').remove();
    }).prop('checked', false);
</script>
<script>
    $(document).ready(function () {
        $('input.tableflat').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
    });

    var asInitVals = new Array();
    $(document).ready(function () {
        var oTable = $('#example').dataTable({
            "oLanguage": {
                "sSearch": "Search all columns:"
            },
            "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                } //disables sorting for column one
            ],
            'iDisplayLength': 12,
            "sPaginationType": "full_numbers",
            "dom": 'T<"clear">lfrtip',
            "tableTools": {
                "sSwfPath": "${context}/resources/js/datatables/tools/swf/copy_csv_xls_pdf.swf"
            }
        });
        $("tfoot input").keyup(function () {
            /* Filter on the column based on the index of this element's parent <th> */
            oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
        });
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });
        $("tfoot input").focus(function () {
            if (this.className == "search_init") {
                this.className = "";
                this.value = "";
            }
        });
        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
    });
</script>


<%@include file="../../template/footer.jsp"%>

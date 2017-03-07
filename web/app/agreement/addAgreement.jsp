<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Quotation"%>
<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Agreement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<script type="text/javascript">
    function loadQuotation() {
        document.getElementById("quotation").innerHTML="";
        var project = document.getElementById("project").value;
        $.ajax({
            type: "POST",
            url: "Agreement?action=getQuotations&project="+project,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.quotation;
                var quotation = document.getElementById("quotation");
                for (var f = 0; f < arrLn1.length; f++) {
                    var t = document.createElement("option");
                    var val = arrLn1[f].id;
                    t.value = val ;
                    t.innerHTML = arrLn1[f].name+"~"+arrLn1[f].date+"~"+arrLn1[f].amount;
                    quotation.appendChild(t);
                }

            }
        });
    }
</script>



<%
    List<ProjectProposal> project = (List<ProjectProposal>) request.getAttribute("projects");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Agreement
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New Agreement<small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form action="Agreement?action=SaveAgreement" method="post" class="form-horizontal form-label-left" validate>
                        <span class="section"></span>
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Project</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" id="project" name="project" required="required" onchange="loadQuotation()">
                                    <option selected="true" disabled="true">Select Project</option>
                                    <% for (ProjectProposal c : project) {%>
                                    <option value="<%=c.getId()%>"><%=c.getProposalName() + " " + c.getCustomerId().getCompanyName()%></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Quotation</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" id="quotation" name="quotation" required="required" >
                                    <option selected="true" disabled="true">Select Quotation</option>
                                </select>                            
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">From Date
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="from" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="from" placeholder="Enter Branch Name" required="required" type="date">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">To Date
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="to" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="to"  required="required" type="date">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Requirement
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <textarea rows="4" cols="50" class="form-control col-md-7 col-xs-12" name="requirement" id="address"></textarea>                
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Project Amount
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="to" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="amount"  required="required" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Agreement Signed
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="to" class="form-control col-md-7 col-xs-12" name="asdate"  type="date">
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

</div>





<!-- footer content -->
<%@include file="../../template/footer.jsp"%>
<script>
    // initialize the validator function
    validator.message['date'] = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
    $('form')
            .on('blur', 'input[required], input.optional, select.required', validator.checkField)
            .on('change', 'select.required', validator.checkField)
            .on('keypress', 'input[required][pattern]', validator.keypress);

    $('.multi.required')
            .on('keyup blur', 'input', function() {
                validator.checkField.apply($(this).siblings().last()[0]);
            });

    // bind the validation to the form submit event
    //$('#send').click('submit');//.prop('disabled', true);

    $('form').submit(function(e) {
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
    $('#vfields').change(function() {
        $('form').toggleClass('mode2');
    }).prop('checked', false);

    $('#alerts').change(function() {
        validator.defaults.alerts = (this.checked) ? false : true;
        if (this.checked)
            $('form .alert').remove();
    }).prop('checked', false);
</script>
<script>
    $(document).ready(function() {
        $('input.tableflat').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
    });

    var asInitVals = new Array();
    $(document).ready(function() {
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
        $("tfoot input").keyup(function() {
            /* Filter on the column based on the index of this element's parent <th> */
            oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
        });
        $("tfoot input").each(function(i) {
            asInitVals[i] = this.value;
        });
        $("tfoot input").focus(function() {
            if (this.className == "search_init") {
                this.className = "";
                this.value = "";
            }
        });
        $("tfoot input").blur(function(i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
    });
</script>
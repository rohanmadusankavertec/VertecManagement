<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Agreement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<script src="../js/branch.js"></script>
<script src="../js/notAlert.js"></script>



<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Current Agreements
            </h3>
        </div>


    </div>
    <div class="clearfix"></div>
    
    <div class="row">
        <%
            List<Agreement> agreement = (List<Agreement>) request.getAttribute("agreement");
        %>
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
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

                                    <th>Agreement ID </th>
                                    <th>Customer Name</th>
                                    <th>Service</th>
                                    <th>Project Name</th>
                                    <th>From Date</th>
                                    <th>To Date</th>
                                    <th>Requirement</th>
                                    <th>Agreement Created</th>
                                    <th>Agreement signed</th>
                                    <th>Price</th>
                                    <th class=" no-link last"><span class="nobr">Action</span></th>

                                </tr>
                            </thead>

                            <tbody>
                                <%for (Agreement a : agreement) {%>
                                <tr>
                                    <td><%=a.getId() %></td>
                                    <td><%=a.getProjectProposalId().getCustomerId().getFirstName()+" "+a.getProjectProposalId().getCustomerId().getLastName() %></td>
                                    <td><%=a.getProjectProposalId().getServiceId().getServiceName() %></td>
                                    <td><%=a.getProjectProposalId().getProposalName() %></td>
                                    <td><%=a.getFromDate() %></td>
                                    <td><%=a.getToDate() %></td>
                                    <td><%=a.getRequirement() %></td>
                                    <td><%=a.getCreatedDate() %></td>
                                    <td><%=a.getSignedDate() %></td>
                                    <td><%=a.getAmount()%></td>
                                    <td class="last">
                                        <form action="Agreement?action=loadAgreementtoUpdate" method="post" >
                                        
                                        <input type="submit" value="Update" class="btn btn-warning" name="update" />
                                        <input type="hidden"  class="btn btn-warning" name="hiddemId" value="<%=a.getId()%>" />
                                        
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

        <br />
        <br />
        <br />

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
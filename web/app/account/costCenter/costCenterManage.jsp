<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>


<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@page import="com.vertec.hibe.model.CctvCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>
<script src="app/js/notAlert.js"></script>

<%
    List<CostCenter> ccList = (List<CostCenter>) request.getAttribute("costcenter");
    List<State> sList = (List<State>) request.getAttribute("state");

%>
<script type="text/javascript">


    function loadFunctionData() {
//        $("").empty();
        var sid = document.getElementById("itemState").value;
        $.ajax({
            type: "POST",
            url: "CostCenter?action=getFunctionData&sid=" + sid,
            success: function (msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.functionData;


                var fdata = document.getElementById("fdata");
                var ihtml = "";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "'>" + arrLn1[f].fname + "</option>"
                }
                fdata.innerHTML = ihtml;


            }
        });

    }

</script>


<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Cost Center  Management
            </h3>
        </div>

    </div>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New Cost Center Item<small></small></h2>
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

                    <form action="CostCenter?action=saveCostCenter" method="post" class="form-horizontal form-label-left" novalidate>

                        </p>
                        <span class="section"></span>


                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">State  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="itemState" id="itemState"  required="required" onchange="loadFunctionData()" >
                                    <option selected="true" disabled  value="">Select State Item</option>
                                    <% for (State s : sList) {%>
                                    <option value="<%=s.getId()%>"><%=s.getName()%></option>
                                    <%}%>

                                </select>                              
                            </div>
                        </div>
                        <div style="" class="item form-group">
                            <label class="control-label col-md-3 col-sm-12 col-xs-12" for="name">Function Data  
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="fdata" id="fdata"  required="required" >
                                    <option selected="true" disabled value="" >Select Function Data</option>


                                </select>                              
                            </div>
                        </div>            
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Cost Center code<span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="code" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="code"  required="required" type="text">
                            </div>
                        </div>  
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Cost Center name<span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="name" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="name"  required="required" type="text">
                            </div>
                        </div>





                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                                <!--                                <button type="submit" class="btn btn-primary">Cancel</button>-->
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>

                    <div class="ln_solid"></div>



                </div>
            </div>
        </div>
    </div>

    <div class="row">

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

                                    <th> ID </th>
                                    <th> Function Data </th>
                                    <th>Code </th>
                                    <th> Name </th>


                                    <th class=" no-link last"><span class="nobr">Action</span></th>
                                    <th class=" no-link last"><span class="nobr">Action</span></th>

                                </tr>
                            </thead>

                            <tbody>
                                <%for (CostCenter c : ccList) {%>
                                <tr>

                                    <td class=" "><%=c.getId() %></td>
                                    <td class=" "><%=c.getFunctionId().getName() %></td>
                                    <td class=" "><%=c.getCode() %></td>
                                    <td class=" "><%=c.getName() %></td>

                                    <td class=" last">
                                        <form action="CostCenter?action=loadCostCenter" method="POST">
                                            <input type="hidden" name="sId" value="<%=c.getId()%>"/>
                                            <input type="submit" value="Update" class="btn btn-warning" name="update" />
                                        </form>
                                    </td>
                                    <td class=" last">
                                        <form action="CostCenter?action=deleteCostCenter" method="POST">
                                            <input type="hidden" name="sId" value="<%=c.getId()%>"/>
                                            <input type="submit" value="Delete" class="btn btn-danger" name="delete" />
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
<%@include file="../../../template/footer.jsp"%>
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
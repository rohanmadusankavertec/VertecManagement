<%-- 
    Document   : customerDetails
    Created on : Oct 20, 2016, 11:17:04 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%if (ca.checkUserAuth("VIEW_CUSTOMER", group) != null) {%>
<div class="">

    <div class="clearfix"></div>
    <%List<Customer> CustomerList = (List<Customer>) request.getAttribute("c");%>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Customer <small>up to now</small></h2>
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

                                    <th>First Name </th>
                                    <th>Last Name </th>
                                    <th>Company Name </th>
                                    <th>Mobile No </th>
                                    <th>Office No </th>
                                    <th>Address </th>
                                    <th>Fax No </th>
                                    <th>E mail </th>

                                    <th class=" no-link last"><span class="nobr">Update</span>
                                    </th>
                                    <th class=" no-link last"><span class="nobr">Delete</span>
                                    </th>
                                </tr>
                            </thead>

                            <tbody>

                                <% for (Customer c : CustomerList) {

                                %>
                                <tr>

                                    <td class=" "><%=c.getFirstName()%></td>
                                    <td class=" "><%=c.getLastName()%></td>
                                    <td class=" "><%=c.getCompanyName()%></td>
                                    <td class=" "><%=c.getMobileNo()%></td>
                                    <td class=" "><%=c.getOffiiceNo()%></td>
                                    <td class=" "><%=c.getAddress()%></td>
                                    <td class=" "><%=c.getFax()%></td>
                                    <td class=" "><%=c.getEmail()%></td>

                                    <td class=" last">
                                        <%if (ca.checkUserAuth("UPDATE_CUSTOMER", group) != null) {%>
                                        <form name="form1" method="post" action="Customer?action=UpdateCustomer"><input type="hidden" name="customerId" value="<%=c.getId()%>"/>
                                            <button type="submit" class="glyphicon glyphicon-edit">
                                            </button>
                                        </form>
                                        <%}%>
                                    </td>
                                    <td class=" last"> 
                                        <%if (ca.checkUserAuth("DELETE_CUSTOMER", group) != null) {%>
                                        <form name="form1" method="post" action="Customer?action=RemoveCustomer"><input type="hidden" name="customerId" value="<%=c.getId()%>"/>
                                            <button id="send" type="submit" class="btn btn-danger">Delete</button>
                                        </form>

                                        <%}%>
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
<%} else {%>
<script type="text/javascript">
    window.location = 'error403.jsp';
    
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
<%}%>                            
<%@include file="../../template/footer.jsp"%>
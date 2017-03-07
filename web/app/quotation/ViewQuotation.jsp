<%-- 
    Document   : ViewPO
    Created on : Aug 23, 2016, 1:20:15 PM
    Author     : vertec-r
--%>

<%@page import="com.vertec.hibe.model.Quotation"%>
<%@page import="org.hibernate.SQLQuery"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.vertec.util.NewHibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<script type="text/javascript" src="app/js/po.js"></script>

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

function linkpage(){
    
}

</script>
<%
    List<Quotation> quo = (List<Quotation>) request.getAttribute("quotation");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Quotations
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


                    <section class="content invoice">
                        <!-- title row -->
                        <div class="row">

                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Quotations<small>up to now</small></h2>
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
                                                        <th>#</th>
                                                        <th>Customer Name</th>
                                                        <th>Service Name</th>
                                                        <th>Package Name</th>
                                                        <th>Price</th>
                                                        <th>Created By</th>
                                                        <th>Print</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Quotation qtt : quo) {
                                                    %>
                                                    <tr>
                                                         <td><%=qtt.getId() %></td>
                                                        <td><%=qtt.getProjectProposalId().getCustomerId().getCompanyName() + " ~ " +qtt.getProjectProposalId().getCustomerId().getFirstName() + " " + qtt.getProjectProposalId().getCustomerId().getLastName()%></td>
                                                        <td><%=qtt.getPackageId().getServiceId().getServiceName() %></td>
                                                        <td><%=qtt.getPackageId().getName() %></td>
                                                        <td><%=qtt.getAmount()%></td>
                                                        <td><%=qtt.getCreatedBy().getFirstName() %></td>
                                                        <td>
                                                            <form method="POST" target="_blank" action="Quotation?action=viewWebsite">
                                                                <input type="hidden" name="hidden" value="<%=qtt.getId() %>"/>
                                                                <input type="hidden" name="serviceId" value="<%=qtt.getPackageId().getServiceId().getId() %>"/>
                                                                <button  onclick="" type="submit"  class="glyphicon glyphicon-print"></button> 
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

                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

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
            'iDisplayLength': 10,
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






<%@include file="../../template/footer.jsp"%>
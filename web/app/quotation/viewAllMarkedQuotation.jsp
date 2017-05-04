<%-- 
    Document   : viewHardware
    Created on : Dec 26, 2016, 2:06:35 PM
    Author     : Java-Dev-Ruchira
--%>

<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Quotation"%>
<%@page import="com.vertec.hibe.model.HardwareQuotation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>


    <%
        List<Quotation> quoList = (List<Quotation>) request.getAttribute("quoList");
        Service service = (Service) request.getAttribute("service");
        String status = (String)request.getAttribute("status");
//        System.out.println("....service..............."+service);
//        System.out.println("....status............"+status);
    %>
    <div class="">

    <div class="clearfix"></div>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <%if(status.equals("2")) {%>
                        <h2><%=service.getServiceName()%> Quotation Marked As Approved <small>up to now</small></h2>
                    <%} else {%>
                        <h2><%=service.getServiceName() %> Quotation Marked As Canceled <small>up to now</small></h2>
                    <%}%>    
                    
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><li class="fa fa-chevron-up"></li></a>
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

                                    <th>Project Name </th>
                                    <th>Customer Name </th>
                                    <th>Date </th>
                                    <th>Created By </th>
                                    <th>Amount </th>
                                    <th>Print </th>
                                    
                                </tr>
                            </thead>

                            <tbody>

                                <% for (Quotation p : quoList) {

                                %>
                                <tr>

                                    <td class=" "><%=p.getProjectProposalId().getProposalName() %></td>
                                    <td class=" "><%=p.getProjectProposalId().getCustomerId().getFirstName()+" "+p.getProjectProposalId().getCustomerId().getLastName() %></td>
                                    <td class=" "><%=p.getDate() %></td>
                                    <td class=" "><%=p.getCreatedBy().getFirstName() %></td>
                                    <td class=" "><%=p.getAmount() %></td>
                                    <td><form method="POST" target="_blank" action="Quotation?action=NewviewWebsite">
                                            <input type="hidden" name="hidden" value="<%=p.getId()%>"/>
                                            <button  onclick="" type="submit" class="glyphicon glyphicon-print"></button> 
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
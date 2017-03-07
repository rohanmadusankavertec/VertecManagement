<%-- 
    Document   : ViewPO
    Created on : Aug 23, 2016, 1:20:15 PM
    Author     : vertec-r
--%>

<%@page import="org.hibernate.SQLQuery"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.vertec.util.NewHibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<!--<script type="text/javascript" src="app/js/po.js"></script>-->

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
    
    function getItem(id) { // load features according to packge 
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                document.getElementById("featuredetails").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.open("POST", "Quotation?action=GetPkgFeaturess&package=" + id, true);
        xmlHttp.send();
    }
    function deletePackage(id) { // delete package by selescted package
    BootstrapDialog.show({
        message: 'Do you want delete this Package ?',
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
                                nom_Success("Successfully Deleted");
                                setTimeout("location.href = 'Quotation?action=ViewPackage&type=0&service=';", 1500);
                            } else {
                                sm_warning("Package is Not Deleted, Please Try again");
                            }
                        }
                    };
                    xmlHttp.open("POST", "Quotation?action=deletePackage&id=" + id, true);
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
    List<com.vertec.hibe.model.Package> pkg = (List<com.vertec.hibe.model.Package>) request.getAttribute("package");


%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Packages
                <small>
                </small>
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
                                        <h2>Packages<small>up to now</small></h2>
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
                                                        <th>Service Name</th>
                                                        <th>Package Name</th>
                                                        <th>Price</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%                                                        for (com.vertec.hibe.model.Package pa : pkg) {
                                                    %>
                                                    <tr onclick="getItem(<%=pa.getId()%>)">
                                                        <td><%=pa.getId()%></td>
                                                        <td><%=pa.getServiceId().getServiceName()%></td>
                                                        <td><%=pa.getName()%></td>
                                                        <td><%=pa.getPrice()%></td>
                                                        <td>
                                                            <form method="post" action="Quotation?action=toUpdatePackage"><input type="hidden" name="pid" value="<%=pa.getId()%>"/>
                                                                <button type="submit" class="glyphicon glyphicon-edit">
                                                                </button></form> 
                                                            <a href="#" id="deleteUser" onclick="deletePackage(<%=pa.getId()%>);" class="glyphicon glyphicon-remove"></a>
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>

                                            </table>








                                            <div class="table-responsive" style="margin-top: 100px;">
                                                <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                    <thead>
                                                        <tr class="headings">
                                                            <th>#</th>
                                                            <th>Feature</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="featuredetails">

                                                    </tbody>
                                                </table>




                                            </div>



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
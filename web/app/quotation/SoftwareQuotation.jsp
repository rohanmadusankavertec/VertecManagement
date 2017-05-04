<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.Features"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Agreement"%>
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




    function AddFeature() { // add feature

        var name = document.getElementById("newfeature").value;
        if (name === "") {

            sm_warning("Please Enter the feature name");
        } else {

            var xmlHttp = getAjaxObject();
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                {
                    var reply = xmlHttp.responseText;

                    if (reply === "Success") {
                        loadFeatures();
                        nom_Success("Feature added Successfully ");
                    } else {
                        sm_warning("Quotation is not added, Please Try again.");
                    }
                }
            };

            xmlHttp.open("POST", "Quotation?action=addFeature&name=" + name, true);
            xmlHttp.send();
        }

    }
//------------------------------------------------------------------------------
    var pkgf = [];
    var pck = [];

    function addPackageFeature() { //  add features  to tble
        
        var featureid = document.getElementById("pFeature").value;


        if (featureid !== "Select Feature") {
            var packageid = document.getElementById("package").value;

            var fea = featureid.split("~");
            var pac = packageid.split("~");

            var tbl = document.getElementById("featureItemBody");

            var data = tbl.innerHTML;
            var bool = true;
            for (var i = 0; i < pkgf.length; i++) {
                var v1 = parseInt(pkgf[i]);
                var v2 = parseInt(fea[0]);

                if (v1 === v2) {
                    bool = false;
                    sm_warning("This Feature already exist.");
                }
            }
            if (bool) {
                pkgf.push(fea[0]);
                pck.push(pac[0]);
                var data = data + "<tr id='featable" + fea[0] + "'><td>" + fea[0] + "</td><td>" + pac[1] + "</td><td>" + fea[1] + "</td><td><a href='#' id='deleteUser' onclick='DeleteFeature(" + fea[0] + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                tbl.innerHTML = data;
            }
        }
    }

    function DeleteFeature(id) { // delete feature from table
        var elem = document.getElementById("featable" + id);
        elem.parentElement.removeChild(elem);
        for (var i = 0; pkgf.length > i; i++) {
            var v1 = parseInt(pkgf[i]);
            var v2 = parseInt(id);
            if (v1 === v2) {
                pkgf.splice(i, 1);
                pck.splice(i, 1);
            }
        }
    }


//------------------------------------------------------------------------------ 



//-----------------------------------------------------------------------------------    
    function LoadPackageFeatures1() { //load features to select element

        var pkg = document.getElementById("package").value;
        var pck = pkg.split("~");
        $.ajax({
            type: "POST",
            url: "Quotation?action=getPackFeatures1&package=" + pck[0],
            success: function (msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;
//                alert(arrLn1);
                var fe = document.getElementById("pFeature")
                var ihtml = "";
                ihtml += "<option selected='true' disabled='true'>Select Feature</option>";


                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "~" + arrLn1[f].feature + "'>" + arrLn1[f].feature + "</option>";

                }
                fe.innerHTML = ihtml;

            }
        });
    }
//---------------------------------------------------------------------------------------    

    var indes = [];
    var inamount = [];
    var inqty = [];

    function addInstallment() { // add installment to table
        var des = document.getElementById("idescription").value;
        var qty = document.getElementById("qty").value;
        var price = document.getElementById("price").value;
        var tr = document.getElementById("installmentItemBody");
        var html = tr.innerHTML;

        indes.push(des);
        inamount.push(price);
        inqty.push(qty);


//        html += "<tr id='tblrow'><td>" + des + "</td><td>" + amo + "</td><td><a href='#' id='deleteUser' onclick='DeleteInstallment("+des+");' class='glyphicon glyphicon-remove'></a></td></tr>";
        html += "<tr id='tblrow'><td>" + des + "</td><td>" + qty + "</td><td>" + price + "</td></tr>";
        tr.innerHTML = html;

        document.getElementById("idescription").value = "";
        document.getElementById("qty").value = "";
        document.getElementById("price").value = "";
    }

    function DeleteInstallment(des) { // delete installment from table
//        alert(des);

        var table = document.getElementById('installmentItemBody');
        for (var r = 0, n = table.rows.length; r < n; r++) {
            for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
                alert(table.rows[r].cells[c].innerHTML);
            }
        }

    }


    var desarr = [];
    function AddQuotation() { // save quotation
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function ()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;

                if (reply === "Success") {
                    nom_Success("Quotation is added Successfully ");
                    setTimeout("location.href = 'Quotation?action=ViewcreateQuotation&service=1';", 1000);
                } else {
                    sm_warning("Quotation is not added, Please Try again.");
                }
            }
        };
//            alert(pkgf);
//            for(var i =0; i<pkgf.length; i++){
//                var des = document.getElementById("des"+pkgf[i]).value;
////                alert(des);
//                desarr.push(des);
//                
//            }

        var customer = document.getElementById("customer").value;
        var ftpat = document.getElementById("fspay").value;
        var sdpay = document.getElementById("sdpay").value;
        var tdpay = document.getElementById("tdpay").value;
        var install = document.getElementById("installment").value;
        var maintenance = document.getElementById("maintenance").value;
        var extra = document.getElementById("extra").value;
        var prepare = document.getElementById("prepare").value;
        var design = document.getElementById("designation").value;
        
        
        xmlHttp.open("POST", "Quotation?action=SaveQuotation1&customer=" + customer + "&package=" + pck + "&features=" + pkgf + "&indes=" + indes + "&inamount=" + inamount + "&qty=" + inqty + "&fspay=" + ftpat + "&sdpay=" + sdpay + "&tdpay=" + tdpay + "&installment=" + install + "&maintenance=" + maintenance + "&extra=" + extra + "&prepareby=" + prepare + "&designation=" + design, true);
        xmlHttp.send();
    }

    //------------------------------------------------------------------
    function LoadPackageForProjects() {// load packge to select element....
        document.getElementById("package").innerHTML = "";
        var projects = document.getElementById("customer").value;
        $.ajax({
            type: "POST",
            url: "Quotation?action=getPackagesForProject&project=" + projects,
            success: function (msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;
                var ihtml = "";
                ihtml += "<option selected='true' disabled='true'>Select Package</option>";
                var tbl = document.getElementById("package");
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "~" + arrLn1[f].name + "'>" + arrLn1[f].name + " " + arrLn1[f].price + " LKR</option>";
                }
                tbl.innerHTML = ihtml;
            }
        });
    }
    //------------------------------------------------------------------















    var tbArr = [];
    function loadCustomizingToTable() { // add details to tble

        var tbl = document.getElementById("installmentItemBody");

        var desc = document.getElementById("idescription").value;
        var qty = document.getElementById("qty").value;
        var price = document.getElementById("price").value;

        var data = "";
        if (qty !== "" & desc !== "" & price !== "") {

            var rowArr = [];
            rowArr.push(desc);
            rowArr.push(qty);
            rowArr.push(price);
            rowArr.push(tbArr.length);
            tbArr.push(rowArr);
            indes.push(desc);
            inamount.push(price);
            inqty.push(qty);
            for (var i = 0; i < tbArr.length; i++) {
                tbArr[i][3] = i;
                var data = data + "<tr id='tbl" + i + "'><td>" + tbArr[i][0] + "</td><td>" + tbArr[i][1] + "</td><td>" + tbArr[i][2] + "</td><td><a href='#' id='deleteUser' onclick='deleteTableRow(" + i + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                //tbl.innerHTML = data;
            }

            tbl.innerHTML = data;

            document.getElementById("idescription").value = "";
            document.getElementById("qty").value = "";
            document.getElementById("price").value = "";
        } else {
            sm_warning("Please fill all Fields..");
        }


    }

    function deleteTableRow(rowId) { // delete tble row from table
        for (var i = 0; tbArr.length > i; i++) {
            var v1 = parseInt(tbArr[i][3]);
            var v2 = parseInt(rowId);
            if (v1 === v2) {
                tbArr.splice(i, 1);
                indes.splice(i, 1);
                inamount.splice(i, 1);
                inqty.splice(i, 1);
            }
        }
        document.getElementById("tbl" + rowId).remove();
    }











</script>




<%
    List<ProjectProposal> pp = (List<ProjectProposal>) request.getAttribute("proposal");
    List<com.vertec.hibe.model.Package> pkg = (List<com.vertec.hibe.model.Package>) request.getAttribute("packages");
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Create Software Quotation
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New Software Quotation<small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form action="Quotation?action=SaveQuotation" method="post" class="form-horizontal form-label-left" validate>
                        <span class="section"></span>







                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Project</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="customer" id="customer" required="required" onchange="LoadPackageForProjects();">
                                    <option selected="true" disabled="true">Select Project</option>
                                    <% for (ProjectProposal s : pp) {%>
                                    <option value="<%=s.getId()%>"><%=s.getProposalName() + "~" + s.getCustomerId().getCompanyName() + " " + s.getCustomerId().getFirstName()%></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>
                        <div class="ln_solid"></div>           
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Package</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="package" id="package" required="required" onchange="LoadPackageFeatures1()">
                                    <option selected="true" disabled="true">Select Package</option>

                                </select>                            
                            </div>
                        </div>


                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12"> Features</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="pFeature" id="pFeature" required="required" >

                                    <!--<option selected="true">Select Feature</option>-->
                                </select>     



                            </div>
                            <input type="button" value="Add" class="btn btn-success" onclick="addPackageFeature()"/>       
                        </div>


                        <!--           <div class="item form-group">
                                       <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description
                                       </label>
                                       <div class="col-md-6 col-sm-6 col-xs-12">
                                           <input id="fdescrip"  class="form-control col-md-7 col-xs-12" placeholder="Enter Description" type="text">
                                       </div>
                                       <input type="button" value="Add" onclick="addPackageFeature()"/> 
                                   </div> -->

                        <div class="row" style="padding-top: 20px;">
                            <center>
                                <div class="col-xs-12 table">
                                    <table class="table table-striped" id="featureTable" style="width: 500px;">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Package</th>
                                                <th>Feature</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody id="featureItemBody">


                                        </tbody>
                                    </table>
                                </div>
                            </center>
                        </div> 



                        <!--       <div class="item form-group">
                                   <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Amount
                                   </label>
                                   <div class="col-md-6 col-sm-6 col-xs-12">
                                       <input id="amount" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="amount" placeholder="Enter Package Price" required="required" type="text">
                                   </div>
                               </div> -->
                    </form>






                    <!--                    <div class="container" style="margin-top: -100px;">
                                            <a id="modal_trigger" href="#modal" class="btn btn-success pull-right" style="width: 200px;">Add New Feature</a>
                    
                                            <div id="modal" class="popupContainer" style="display:none;">
                                                <header class="popupHeader">
                                                    <span class="header_title">Add new Feature</span>
                                                    <span class="modal_close"><i class="fa fa-times"></i></span>
                                                </header>
                                                <section class="popupBody">
                                                     Register Form 
                                                    <div class="user_register col-md-12 col-sm-12 col-xs-12">
                                                        <form action="Quotation?action=SaveFeature" method="post">
                                                            <div class="item form-group">
                                                                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Enter Feature Name<span class="required"></span>
                                                                </label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input type="text" id="newfeature" name="newfeature" required="required" class="form-control col-md-7 col-xs-12">
                    
                                                                </div>
                                                            </div>   
                                                            <br/>
                                                            <br/>
                                                            <div class="action_btns">
                                                                <div class="one_half last col-md-offset-3"><button type="button" onclick="AddFeature()" class="btn btn-success pull-right"><i class="fa fa-briefcase"></i>Save Feature</button></div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </section>
                                            </div>
                                        </div> -->




                    <div class="ln_solid" style="margin-top: -70px;"></div>

                    <form  method="post" class="form-horizontal form-label-left" novalidate>
                        <div class="x_title">
                            <h2 style="margin-left: 100px;">Software Customization<small></small></h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="idescription" class="form-control col-md-7 col-xs-12" placeholder="Enter Software Description" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Qty
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="qty" class="form-control col-md-7 col-xs-12" placeholder="Enter Quantity" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Price
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="price" class="form-control col-md-7 col-xs-12" placeholder="Enter price" type="number">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-7 col-lg-offset-7">
                                <button id="send" type="button" onclick="loadCustomizingToTable()" class="btn btn-success">Add Customization</button>
                            </div>
                        </div>
                    </form>
                    <div class="row" style="padding-top: 20px;">
                        <center>
                            <div class="col-xs-12 table">
                                <table class="table table-striped" id="installmentTable" style="width: 500px;">
                                    <thead>
                                        <tr>
                                            <th>Description</th>
                                            <th>Qty</th>
                                            <th>Price</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody id="installmentItemBody">


                                    </tbody>
                                </table>
                            </div>
                        </center>
                    </div> 
                    <div class="ln_solid"></div>
                    <form  method="post" class="form-horizontal form-label-left" novalidate>
                        <div class="x_title">
                            <h2 style="margin-left: 100px;">Quotation Advance Details<small></small></h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">First Payment
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="fspay" class="form-control col-md-7 col-xs-12" placeholder="Enter First Payment" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Second Payment
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="sdpay" class="form-control col-md-7 col-xs-12" placeholder="Enter Second Payment" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Third Payment
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="tdpay" class="form-control col-md-7 col-xs-12" placeholder="Enter third Payment" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Software Installment
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="installment" class="form-control col-md-7 col-xs-12" placeholder="Enter Installment Payment" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Maintenance
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="maintenance" class="form-control col-md-7 col-xs-12" placeholder="Enter Maintenance Payment" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Extra Maintenance(1km)
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="extra" class="form-control col-md-7 col-xs-12" placeholder="Enter Extra Maintenance Payment" type="number">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Prepared By
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="prepare" class="form-control col-md-7 col-xs-12" placeholder="Enter name" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Designation
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="designation" class="form-control col-md-7 col-xs-12" placeholder="Enter Designation" type="text">
                            </div>
                        </div>
                        
                        
                    </form>

                    <div class="ln_solid"></div>
                    <div class="form-group" >
                        <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                            <button id="send" type="button" onclick="AddQuotation()" class="btn btn-success">Submit Quotation</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>





<!-- footer content -->
<%@include file="../../template/footer.jsp"%>

<script type="text/javascript">

    $("#modal_trigger").leanModal({top: 200, overlay: 0.6, closeButton: ".modal_close"});

    $(function () {


        // Calling Register Form
        $("#modal_trigger").click(function () {
            $(".user_register").show();
            $(".header_title").text('Add New Feature');
            return false;
        });



    });
</script>
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
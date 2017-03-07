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
    loadFeatures();
    function loadFeatures() { // load features on select element.....
        $("#SFeature").empty();
        $.ajax({
            type: "POST",
            url: "Quotation?action=getFeatures",
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;


                var feature = document.getElementById("SFeature");
                var ihtml = "";
//                ihtml += "<option value='' selected='true' disabled='true'>Select Feature...</option>";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "~" + arrLn1[f].feature + "'>" + arrLn1[f].feature + "</option>"
                }
                feature.innerHTML = ihtml;

//                for (var f = 0; f < arrLn1.length; f++) {
//                    var t = document.createElement("option");
//                    var val = arrLn1[f].id;
//                    t.value = val + "~"+arrLn1[f].feature;
//                    t.innerHTML = arrLn1[f].feature;
//                    feature.appendChild(t);
//                }
            }
        });
    }




    function AddFeature() { // add new feature to list of features

        var name = document.getElementById("newfeature").value;
        if (name === "") {

            sm_warning("Please Enter the feature name");
        } else {

            var xmlHttp = getAjaxObject();
            xmlHttp.onreadystatechange = function()
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

    var pkgf = [];
    
    function LoadPackageFeatures() { // load features of package on the table
        
//        var pkgf2 = [];
        var pkgf2 = [];
        var pkg = document.getElementById("package").value;
        $.ajax({
            type: "POST",
            url: "Quotation?action=getPackFeatures&package=" + pkg,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;
                var ihtml = "";
//                ihtml+="<option selected='true'selected='true' disabled='true'>Select Feature</option>";
                var tbl = document.getElementById("featureItemBody");
                for (var f = 0; f < arrLn1.length; f++) {
                    pkgf2.push(arrLn1[f].id);
                    ihtml += "<tr id='featable" + arrLn1[f].id + "'><td>" + arrLn1[f].id + "</td><td>" + arrLn1[f].feature + "</td><td><input style='width:225px; ' id='des" + arrLn1[f].id + "' placeholder='Enter Description' type='text'></td><td><a href='#' id='deleteUser' onclick='DeleteFeature(" + arrLn1[f].id + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                }
                tbl.innerHTML = ihtml;
                pkgf = pkgf2;
                document.getElementById("amount").value = reply.amount;
            }
        });
    }
    
    function addPackageFeature() { // load features of package on the table
        var featureid = document.getElementById("SFeature").value;
        
        
            
        
//        var descrip = document.getElementById("fdescrip").value;
        

                var fea = featureid.split("~");

                var tbl = document.getElementById("featureItemBody");

                var data = tbl.innerHTML;
                var bool = true;


                for (var i = 0; pkgf.length > i; i++) {


                    var v1 = parseInt(pkgf[i]);
                    var v2 = parseInt(fea[0]);

                    if (v1 === v2) { // check the feature already in the table..
                        bool = false;
                        sm_warning("This Feature already exists.");
                    }
                }

                if (bool) {
                    pkgf.push(fea[0]);
                    var data = data + "<tr id='featable" + fea[0] + "'><td>" + fea[0] + "</td><td>" + fea[1] + "</td><td><input style='width:225px; ' id='des"+fea[0]+"' placeholder='Enter Description' type='text'></td><td><a href='#' id='deleteUser' onclick='DeleteFeature(" + fea[0] + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                    tbl.innerHTML = data;
                }
            
        loadFeatures();

    }


    function DeleteFeature(id) { // delete feature from table
        var elem = document.getElementById("featable" + id);
        elem.parentElement.removeChild(elem);
        for (var i = 0; pkgf.length > i; i++) {
            var v1 = parseInt(pkgf[i]);
            var v2 = parseInt(id);
            if (v1 === v2) {
                pkgf.splice(i, 1);
            }
        }
    }

    

    function SavePackage() { // send the data of packge to save 
        
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;

                if (reply === "Success") {
                    loadFeatures();
                    nom_Success("Feature added Successfully ");
                    setTimeout("location.href = 'Quotation?action=addpackages';", 1500);
                } else {
                    sm_warning("Quotation is not added, Please Try again.");
                }
            }
        };
        var service = document.getElementById("service").value;
        var package = document.getElementById("name").value;
        var price = document.getElementById("price").value;
        xmlHttp.open("POST", "Quotation?action=savePackage&service=" + service + "&package=" + package + "&price=" + price + "&features=" + pkgf, true);
        xmlHttp.send();
    }

    

    var indes = [];
    var inamount = [];

//    function addInstallment() {
//        var des = document.getElementById("idescription").value;
//        var amo = document.getElementById("iamount").value;
//        var tr = document.getElementById("installmentItemBody");
//        var html = tr.innerHTML;
//       
//       indes.push(des);
//       inamount.push(amo);
//       
//       
////        html += "<tr id='tblrow'><td>" + des + "</td><td>" + amo + "</td><td><a href='#' id='deleteUser' onclick='DeleteInstallment("+des+");' class='glyphicon glyphicon-remove'></a></td></tr>";
//        html += "<tr id='tblrow'><td>" + des + "</td><td>" + amo + "</td></tr>";
//        tr.innerHTML = html;
//        
//        document.getElementById("idescription").value="";
//        document.getElementById("iamount").value="";
//    }
//    
//    function deleteTableRow(rowId) {
//        for (var i = 0; tbArr.length > i; i++) {
//            var v1 = parseInt(tbArr[i][3]);
//            var v2 = parseInt(rowId);
//
//            if (v1 === v2) {
//                tbArr.splice(i, 1);
//            }
//        }
//        document.getElementById("tbl" + rowId).remove();
//    }
//    
    
    
    
    
    
    
    var tbArr = [];
    function loadInstallmentToTable() { // add installmet data on the table....

        var tbl = document.getElementById("installmentItemBody");

        var pss = document.getElementById("idescription").value;
        var dsc = document.getElementById("iamount").value;

        var data = "";
        if (pss !== "" & dsc !== "" ) {

            var rowArr = [];
            rowArr.push(pss);
            rowArr.push(dsc);
            rowArr.push(tbArr.length);
            tbArr.push(rowArr);
            
            indes.push(pss);
            inamount.push(dsc);

            for (var i = 0; i < tbArr.length; i++) {
                tbArr[i][3] = i;
                var data = data + "<tr id='tbl" + i + "'><td>" + tbArr[i][0] + "</td><td>" + tbArr[i][1] + "</td><td><a href='#' id='deleteUser' onclick='deleteTableRow(" + i + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                //tbl.innerHTML = data;
            }

            tbl.innerHTML = data;

            document.getElementById("idescription").value = "";
            document.getElementById("iamount").value = "";
        } else {
            sm_warning("Please fill all Fields..");
        }


    }

    function deleteTableRow(rowId) {  // remove installmet data from the table....
        for (var i = 0; tbArr.length > i; i++) {
            var v1 = parseInt(tbArr[i][2]);
            var v2 = parseInt(rowId);
            if (v1 === v2) {
                tbArr.splice(i, 1);
                indes.splice(i, 1);
            inamount.splice(i, 1);

            }
        }
        document.getElementById("tbl" + rowId).remove();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    function DeleteInstallment(des) {
//        
//        var table = document.getElementById('installmentItemBody');
//        for (var r = 0, n = table.rows.length; r < n; r++) {
//            for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
//                alert(table.rows[r].cells[c].innerHTML);
//            }
//        }
//        
//    }
    
    
    var desarr = [];
     function AddQuotation() { // send data of quotation to controller class
            var xmlHttp = getAjaxObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                {
                    var reply = xmlHttp.responseText;

                    if (reply === "Success") {
                        nom_Success("Quotation added Successfully ");
//                        setTimeout("location.href = 'Estimatecost?action=addestimatecost';", 1500);
                        setTimeout("location.href = 'Quotation?action=createQuotation';", 1500);
                    } else {
                        sm_warning("Quotation is not added, Please Try again.");
                    }
                }
            };
//            alert(pkgf+"ggggggggggggg");
            for(var i =0; i<pkgf.length; i++){
                var des = document.getElementById("des"+pkgf[i]).value;
//                alert(des);
                desarr.push(des);
                
            }
            
            var customer = document.getElementById("customer").value;
//            alert(customer);
            var package = document.getElementById("package").value;
            var amount = document.getElementById("amount").value;
            
            xmlHttp.open("POST", "Quotation?action=SaveQuotation&customer=" + customer+"&package="+package+"&amount="+amount+"&features="+pkgf+"&indes="+indes+"&inamount="+inamount+"&descrip="+desarr, true);
            xmlHttp.send();
    }
    
    
    function LoadPackageForProjects() {  // load packages according to project on select element
        document.getElementById("package").innerHTML="";
        var projects = document.getElementById("customer").value;
//        alert(projects);
        $.ajax({
            type: "POST",
            url: "Quotation?action=getPackagesForProject&project=" + projects,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;
                var ihtml = "";
                ihtml+="<option selected='true' disabled='true'>Select Package</option>";
                var tbl = document.getElementById("package");
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='"+arrLn1[f].id+"'>"+arrLn1[f].name+" "+arrLn1[f].price+" LKR</option>";
                }
                tbl.innerHTML = ihtml;
            }
        });
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
                Create Quotation
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New Quotation<small></small></h2>
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
                                    <option selected="true" disabled="true" value="">Select Project</option>
                                    <% for (ProjectProposal s : pp) {%>
                                    <option value="<%=s.getId()%>"><%=s.getProposalName() + "~" + s.getCustomerId().getCompanyName()+ " " + s.getCustomerId().getFirstName() %></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>
                                
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Package</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="package" id="package" required="required" onchange="LoadPackageFeatures()">
                                    <option selected="true" disabled="true">Select Package</option>
                                    
                                </select>                            
                            </div>
                        </div>
                        <div class="ln_solid"></div>        

                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12"> Features</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="SFeature" id="SFeature" required="required" >
                                    <option selected="true" disabled="true">Select Feature</option>
                                    <!--<option selected="true">Select Feature</option>-->
                                </select>     

                                     

                            </div>
                              <input type="button" value="Add" class="btn btn-success" onclick="addPackageFeature()"/>      
                        </div>
<!--                        <div class="item form-group">
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
                                                <th>Feature</th>
                                                <th>Description</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody id="featureItemBody">


                                        </tbody>
                                    </table>
                                </div>
                            </center>
                        </div> 



                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Amount
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="amount" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="amount" placeholder="Enter Package Price" required="required" type="text">
                            </div>
                        </div>
                    </form>






                    <div class="container" style="margin-top: -100px;">
                        <a id="modal_trigger" href="#modal" class="btn btn-success pull-right" style="width: 200px;">Add New Feature</a>

                        <div id="modal" class="popupContainer" style="display:none;">
                            <header class="popupHeader">
                                <span class="header_title">Add new Feature</span>
                                <span class="modal_close"><i class="fa fa-times"></i></span>
                            </header>
                            <section class="popupBody">
                                <!-- Register Form -->
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
                    </div>  




                    <div class="ln_solid" style="margin-top: -70px;"></div>

                    <form  method="post" class="form-horizontal form-label-left" novalidate>
                        <div class="x_title">
                            <h2 style="margin-left: 100px;">Installments<small></small></h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="idescription" class="form-control col-md-7 col-xs-12" placeholder="Enter Installment Description" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Amount
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="iamount" class="form-control col-md-7 col-xs-12" placeholder="Enter Installment Amount" type="number">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-7 col-lg-offset-7">
                                <button id="send" type="button" onclick="loadInstallmentToTable()" class="btn btn-success">Add Installments</button>
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
                                            <th>Amount</th>
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

    $(function() {


        // Calling Register Form
        $("#modal_trigger").click(function() {
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
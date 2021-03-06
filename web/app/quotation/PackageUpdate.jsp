<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Features"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Agreement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<%
    List<Service> service = (List<Service>) request.getAttribute("service");
    com.vertec.hibe.model.Package pack = (com.vertec.hibe.model.Package) request.getAttribute("package");
%>


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

    function loadFeatures() { //load features on select elemenet.....
        
        $("#SFeature").empty();
        $.ajax({
            type: "POST",
            url: "Quotation?action=getFeatures",
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;


                var feature = document.getElementById("SFeature");
                var ihtml = "";
                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "~" + arrLn1[f].feature + "'>" + arrLn1[f].feature + "</option>"
                }
                feature.innerHTML = ihtml;

            }
        });
    }




    function AddFeature() { // add new feature to controller class

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
//                        nom_Success("Feature added Successfully ");
                    } else {
                        sm_warning("Quotation is not added, Please Try again.");
                    }
                }
            };

            xmlHttp.open("POST", "Quotation?action=addFeature&name=" + name, true);
            xmlHttp.send();
        }

        document.getElementById("newfeature").value="";
    }

    var pkgf = [];
    function addPackageFeature() { // add features according to packge to the table
        
        var featureid = document.getElementById("SFeature").value;
        var fea = featureid.split("~");
        var tbl = document.getElementById("featureItemBody");
        var data = tbl.innerHTML;
        var bool = true;
        for (var i = 0; pkgf.length > i; i++) {
            if (pkgf[i] === fea[0]) {
                bool = false;
                sm_warning("This Feature is already exist.");
            }
        }            
        if (bool) {
            pkgf.push(fea[0]);
            var data = data + "<tr id='featable" + fea[0] + "'><td>" + fea[0] + "</td><td>" + fea[1] + "</td><td><a href='#' id='deleteUser' onclick='DeleteFeature(" + fea[0] + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
            tbl.innerHTML = data;
        }

    }
    function DeleteFeature(id) { // remove feature data form table
        
        var elem = document.getElementById("featable" + id);
//        alert(elem);
        elem.parentElement.removeChild(elem);
        for (var i = 0; pkgf.length > i; i++) {
//            if (pkgf[i].indexOf(id) > -1) {
//                pkgf.shift(pkgf[i]);
//                pkgf.splice(i, 1);
//            }
            
            var v1 = parseInt(pkgf[i]);
            var v2 = parseInt(id);
            if (v1 === v2) {
                pkgf.shift(v1);
                pkgf.splice(i, 1);
            }
        }
    }

    function UpdatePackage() { // send the changed data to controller class....
        var xmlHttp = getAjaxObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            {
                var reply = xmlHttp.responseText;

                if (reply === "Success") {
                    loadFeatures();
                    nom_Success("Package is updated Successfully ");
                    setTimeout("location.href = 'Quotation?action=addpackages';", 1500);
                } else {
                    sm_warning("Package is not updated, Please Try again.");
                }
            }
        };
        var id = document.getElementById("packageid").value;
        var service = document.getElementById("service").value;
        var package = document.getElementById("name").value;
        var price = document.getElementById("price").value;
        xmlHttp.open("POST", "Quotation?action=UpdatePackage&service=" + service + "&package=" + package + "&price=" + price + "&features=" + pkgf+ "&id=" + id, true);
        xmlHttp.send();
    }

    var pid =<%=pack.getId()%>;
    var Service =<%=pack.getServiceId().getId()%>;
    LoadPackageData();
    function LoadPackageData() { // add features according to pachage

        $.ajax({
            type: "POST",
            url: "Quotation?action=getPackageFeatures&package=" + pid,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.feature;
                var ihtml = "";
                var tbl = document.getElementById("featureItemBody");
                for (var f = 0; f < arrLn1.length; f++) {
                    pkgf.push(arrLn1[f].id);
                    ihtml += "<tr id='featable" + arrLn1[f].id + "'><td>" + arrLn1[f].id + "</td><td>" + arrLn1[f].feature + "</td><td><a href='#' id='deleteUser' onclick='DeleteFeature(" + arrLn1[f].id + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                }
                tbl.innerHTML = ihtml;
                document.getElementById("service").value=Service;
            }
        });
    }



</script>





<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Package
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Update Package<small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <input type="hidden" value="<%=pack.getId()%>" id="packageid"/>
                    <form  method="post" class="form-horizontal form-label-left" validate>
                        <span class="section"></span>
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Service</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="service" id="service" required="required" >
                                    <option selected="true" disabled="true">Select Service</option>
                                    <% for (Service s : service) {%>
                                    <option value="<%=s.getId()%>"><%=s.getServiceName()%></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Package Name
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="name" class="form-control col-md-7 col-xs-12" value="<%=pack.getName()%>" data-validate-words="1" name="name" placeholder="Enter Package Name" required="required" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Price
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="price" class="form-control col-md-7 col-xs-12"  data-validate-words="1" name="price" value="<%=pack.getPrice()%>"placeholder="Enter Price" required="required" type="number">
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <br>  
                        <br>  
                        <br>  
                        <br>  
                        <div class="item form-group">
                            <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12"> Features</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="SFeature" id="SFeature" required="required" >
                                    <option selected="true" disabled="true">Select Feature</option>
                                    <!--<option selected="true">Select Feature</option>-->
                                </select>     
                            </div>
                            <input type="button" class="btn btn-success" value="Add" onclick="addPackageFeature()"/>           
                        </div>
                        <div class="row" style="padding-top: 20px;">
                            <center>
                                <div class="col-xs-12 table">
                                    <table class="table table-striped" id="featureTable" style="width: 500px;">
                                        <thead>
                                            <tr>
                                                <th>#</th>
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

                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                                <button id="send" type="button" onclick="UpdatePackage();" class="btn btn-success">Update Package</button>
                            </div>
                        </div>
                    </form>
                    <div class="container">
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
                                        <div class="action_btns" style="padding-top: 30px;">
                                            <div class="one_half last col-md-offset-3"><button type="button" onclick="AddFeature()" class="btn btn-success pull-right"><i class="fa fa-briefcase"></i>Save Feature</button></div>
                                        </div>
                                    </form>
                                </div>
                            </section>
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
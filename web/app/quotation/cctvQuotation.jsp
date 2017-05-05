<%-- 
    Document   : HardwareQuotation
    Created on : Dec 26, 2016, 10:27:15 AM
    Author     : Java-Dev-Ruchira
--%>

<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.CctvCategory"%>
<%@page import="com.vertec.hibe.model.Customer"%>
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
    
    
    function loadItems() { // load cctv item to select element
        $("#item").empty();
        var cateId = document.getElementById("itemcategory").value;
        $.ajax({
            type: "POST",
            url: "Quotation?action=loadItemBycategory&categoryId="+cateId,
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.items;


                var item = document.getElementById("item");
                var ihtml = "";

                for (var f = 0; f < arrLn1.length; f++) {
                    ihtml += "<option value='" + arrLn1[f].id + "-" + arrLn1[f].name + "-" + arrLn1[f].price + "'>" + arrLn1[f].name + "-" + arrLn1[f].price + "</option>"
                }
                item.innerHTML = ihtml;

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
    
    
    
    
    var tbArr = [];
    var qty = [];
    var price = [];
    
   
    function sendData(){ // save quotation
        var customer = document.getElementById("customer").value;
        var camera = document.getElementById("camera").value;
        var dvr = document.getElementById("dvr").value;
        var disk = document.getElementById("disk").value;
        var moniter = document.getElementById("monitor").value;
        var intall = document.getElementById("install").value;
        var cable = document.getElementById("cable").value;
        var fst = document.getElementById("1st").value;
        var snd = document.getElementById("2nd").value;
        var trd = document.getElementById("3rd").value;
        var prepare = document.getElementById("prepare").value;
        var designation = document.getElementById("desgnation").value;
//        alert(camera);
//        alert(dvr);
//        alert(disk);
//        alert(moniter);
        alert(intall);
//        alert(cable);
//        alert(prepare);
//        alert(designation);
        var xmlHttp = getAjaxObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                {
                    var reply = xmlHttp.responseText;

                    if (reply === "Success") {
                        nom_Success("Quotation added Successfully ");
                        setTimeout("location.href = 'Quotation?action=ViewcreateQuotation&service=3';", 1000);
                    } else {
                        sm_warning("Quotation is not added, Please Try again.");
                    }
                }
            };
                xmlHttp.open("POST", "Quotation?action=saveCctvQuotation&items="+tbArr+"&qty="+qty+"&customer="+customer+"&grand="+grand+"&camera="+camera+"&dvr="+dvr+"&hardDisk="+disk+"&monitor="+moniter+"&installation="+intall+"&cable="+cable+"&fst="+fst+"&snd="+snd+"&trd="+trd+"&prepare="+prepare+"&designation="+designation, true);
                xmlHttp.send();
            
            

    }
    
    var camera =0;
    
    function loaditemToTable() { // load items to table
//        alert(qty);
//        alert(price);
        var tbl = document.getElementById("cctvItemBody");

        var item = document.getElementById("item").value;
        
        var qty1 = document.getElementById("qty").value;
        var category = document.getElementById("itemcategory").value;
        
        if(category === "1"){
            camera= camera+parseInt(qty1);
        }
        
//        alert("...1.."+camera);

        var data = tbl.innerHTML;
        if (qty1 !== "" & item !== "" ) {
            
            qty.push(qty1);
            
            var items = item.split("-");
            
            var bool = true;

            for (var i = 0; i < tbArr.length; i++) {
               if(tbArr[i] === items[0]){
                bool = false;
                sm_warning("This Item already exists.");
               }
            }
            if(bool){
               tbArr.push(items[0]);
               price.push(items[2]);
               var data = data + "<tr id='" + items[0] + "'><td id='category"+items[0]+"'>"+category+"</td><td>" + items[1] + "</td><td>" + items[2] + "</td><td id='qty"+items[0]+"'>"+qty1+"</td><td><a href='#' id='deleteUser' onclick='DeleteEmployee(" + items[0] + ")' class='glyphicon glyphicon-remove'></a></td></tr>";
                tbl.innerHTML = data;
            }
            document.getElementById("qty").value = "";
            
        } else {
            sm_warning("Please fill all Fields..");
        }
        calculation1();
        
        
        
    }

    function DeleteEmployee(id) { // delete item from table
        
        var cat = document.getElementById("category"+id).innerHTML;
        var qty2 = document.getElementById("qty"+id).innerHTML;
        if(cat === "1"){
            camera = camera-qty2;
        }
        
        var elem = document.getElementById(id);
        elem.parentElement.removeChild(elem);
        for (var i = 0; tbArr.length > i; i++) {
            if (tbArr[i].indexOf(id) > -1) {
                tbArr.shift(tbArr[i]);
                tbArr.splice(i, 1);
                price.splice(i, 1);
                qty.splice(i, 1);
                
            }
        }
        
//        alert(camera);
calculation1();
    }
    
    var grand = 0;
    function calculation1(){ // calculation 
       
       
       var sub=0;
        for(var i=0; i< qty.length; i++ ){
//            alert("lenght"+i);
//            alert("--qty--"+qty[i]);
//            alert("--price--"+price[i]);
            sub += parseInt(qty[i])*parseInt(price[i]);
//            alert("---sub----"+sub);
        }
        
        var cal = (sub*19.19/100)+sub;
        grand = (2500*camera)+cal;
        
        document.getElementById("subtotal").innerHTML=sub;
        document.getElementById("grantotal").innerHTML=grand;
    }
    
    
    
  
  
    
</script>

<%
    List<CctvCategory> cc = (List<CctvCategory>) request.getAttribute("category");
    List<ProjectProposal> cu = (List<ProjectProposal>) request.getAttribute("project");
    
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Create CCTV Quotation
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New CCTV Quotation<small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    
                        <!--<span class="section"></span>-->
                        <form action="#" method="post" class="form-horizontal form-label-left" validate>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Project Proposal </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="customer" id="customer" required="required" >
                                    <option selected="true" disabled="true">Select Project</option>
                                    <% for (ProjectProposal c : cu) {%>
                                    <option value="<%=c.getId()%>"><%=c.getProposalName()+"- "+ c.getCustomerId().getFirstName()+" "+c.getCustomerId().getLastName() %></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>    
                            
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Category </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="itemcategory" id="itemcategory" required="required" onchange="loadItems();">
                                    <option selected="true" disabled="true">Select Category</option>
                                    <% for (CctvCategory c : cc) {%>
                                    <option value="<%=c.getId()%>"><%=c.getCategory() %></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>
                                <!--<div class="clearfix"></div>-->        
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Item</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="item" id="item" required="required">
                                    <option selected="true" disabled="true">Select cctv item</option>
                                    
                                    
                                </select>                            
                            </div>
                        </div>
                                
                                <!--<div class="clearfix"></div>-->  
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Qty
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="qty" name="qty" class="form-control col-md-7 col-xs-12" placeholder="Enter Quantity" type="number">
                            </div>
                        </div> 
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-7 col-lg-offset-7">
                                <button id="send" type="button" onclick="loaditemToTable()" class="btn btn-success">Add </button>
                            </div>
                        </div>   
                                

                    

                    <!--<div class="ln_solid" style="margin-top: -70px;"></div>-->

                    
                    
                    <div class="row" style="padding-top: 20px;">
                        <center>
                            <div class="col-xs-12 table">
                                <table class="table table-striped" id="installmentTable" style="width: 500px;">
                                    <thead>
                                        <tr>
                                            <th>Category Id</th>
                                            <th>Item Name</th>
                                            <th>Price</th>
                                            <th>Qty</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody id="cctvItemBody">


                                    </tbody>
                                </table>
                            </div>
                        </center>
                    </div> 
                    <div class="clearfix"></div>
                    <div class="x_title">
                        <h2>Add CCTV Additional Details & Warronty <small></small></h2>
                        
                        <div class="clearfix"></div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Camera</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="camera" name="camera" class="form-control col-md-7 col-xs-12" placeholder="Enter warronty" type="number">
                            </div>
                    </div> 
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">DVR(Digital Video Recorder)</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="dvr" name="dvr" class="form-control col-md-7 col-xs-12" placeholder="Enter warronty" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Hard Disk</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="disk" name="disk" class="form-control col-md-7 col-xs-12" placeholder="Enter warronty" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Monitor</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="monitor" name="monitor" class="form-control col-md-7 col-xs-12" placeholder="Enter warronty" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Installation</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="install" name="install" class="form-control col-md-7 col-xs-12" placeholder="Enter warronty" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Additional cable(per meter)</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="cable" name="cable" class="form-control col-md-7 col-xs-12" placeholder="Enter Price" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">1 st Payment(%)</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="1st" name="1st" class="form-control col-md-7 col-xs-12" placeholder="Enter payment percentage" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">2 st Payment(%)</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="2nd" name="2nd" class="form-control col-md-7 col-xs-12" placeholder="Enter payment percentage" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">3 rd Payment(%)</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="3rd" name="3rd" class="form-control col-md-7 col-xs-12" placeholder="Enter payment percentage" type="number">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Prepared By</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="prepare" name="prepare" class="form-control col-md-7 col-xs-12" placeholder="Enter name" type="text">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Designation</label>
                            
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="desgnation" name="desgnation" class="form-control col-md-7 col-xs-12" placeholder="Enter Designation" type="text">
                            </div>
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Sub Total :Rs
                            </label>
                        <label style="float: left;" class="control-label col-md-3 col-sm-3 col-xs-12"id="subtotal" for="name">0000.00
                            </label>
                            
                    </div>
                    <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Grand Total :Rs
                            </label>
                        <label style="float: left;"class="control-label col-md-3 col-sm-3 col-xs-12"id="grantotal" for="name">0000.00
                            </label>
                            
                    </div>

                    <div class="ln_solid"></div>
                    <div class="form-group" >
                        <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                            <button id="send" type="button" onclick="sendData()" class="btn btn-success">Submit Quotation</button>
                        </div>
                    </div>
                
                    </form>
                </div>
                                
            </div>
        </div>
    </div>

</div>
                                
<%@include file="../../template/footer.jsp"%>
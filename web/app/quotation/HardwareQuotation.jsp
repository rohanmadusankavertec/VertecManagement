<%-- 
    Document   : HardwareQuotation
    Created on : Dec 26, 2016, 10:27:15 AM
    Author     : Java-Dev-Ruchira
--%>

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
    
    
    var dscri = [];
    
    var qtty = [];
    var pric = [];
    
    var tbArr = [];
   
    function sendData(){   //send Data to controller class
        var xmlHttp = getAjaxObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                {
                    var reply = xmlHttp.responseText;

                    if (reply === "Success") {
                        nom_Success("Quotation added Successfully ");
                        setTimeout("location.href = 'Quotation?action=viewHardware';", 1000);
                    } else {
                        sm_warning("Quotation is not added, Please Try again.");
                    }
                }
            };
            
            var customer = document.getElementById("customer").value;
            
            if(customer !== ""){
                xmlHttp.open("POST", "Quotation?action=saveHardwareQuotation&customer=" + customer+"&dscrip="+dscri+"&prc="+pric+"&qtyy="+qtty, true);
                xmlHttp.send();
            }
//            alert();
            else{
                sm_warning("Please select the Customer...");
            }

    }
    
    
    
    
    
    
    
    
    var tbArr = [];
    function loadCustomizingToTable() { // details load on the table

        var tbl = document.getElementById("hardwareItemBody");

        var desc = document.getElementById("idescription").value;
        var qty = document.getElementById("qty").value;
        var price = document.getElementById("price").value;

        var data = "";
        if (qty !== "" & desc !== "" & price !== "" ) {
            dscri.push(desc);
            qtty.push(qty);
            pric.push(price);
            
            var rowArr = [];
            rowArr.push(desc);
            rowArr.push(qty);
            rowArr.push(price);
            rowArr.push(tbArr.length);
            tbArr.push(rowArr);

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

    function deleteTableRow(rowId) { // delete the row 
        for (var i = 0; tbArr.length > i; i++) {
            var v1 = parseInt(tbArr[i][3]);
            var v2 = parseInt(rowId);
            if (v1 === v2) {
                tbArr.splice(i, 1);
                dscri.splice(i, 1);
            qtty.splice(i, 1);
            pric.splice(i, 1);
            }
        }
        document.getElementById("tbl" + rowId).remove();
    }
    
  
  
    
</script>

<%
    List<Customer> cus = (List<Customer>) request.getAttribute("cus");
    
%>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Create Hardware Quotation
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add New Hardware Quotation<small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form action="#" method="post" class="form-horizontal form-label-left" validate>
                        <!--<span class="section"></span>-->
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Customer</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="customer" id="customer" required="required" onchange="LoadPackageForProjects();">
                                    <option selected="true" disabled="true">Select Customer</option>
                                    <% for (Customer c : cus) {%>
                                    <option value="<%=c.getId()%>"><%=c.getFirstName()+" "+c.getLastName() %></option>
                                    <%}%>
                                </select>                            
                            </div>
                        </div>

                    </form>

                    <!--<div class="ln_solid" style="margin-top: -70px;"></div>-->

                    <div class="ln_solid"></div>
                    <form  method="post" class="form-horizontal form-label-left" novalidate>
                        <div class="x_title">
                            <h2 style="margin-left: 100px;">Hardware Customization<small></small></h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Description
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="idescription" class="form-control col-md-7 col-xs-12" placeholder="Enter Hardware Description" type="text">
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
                                    <tbody id="hardwareItemBody">


                                    </tbody>
                                </table>
                            </div>
                        </center>
                    </div> 



                    <div class="ln_solid"></div>
                    <div class="form-group" >
                        <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                            <button id="send" type="button" onclick="sendData()" class="btn btn-success">Submit Quotation</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
                                
<%@include file="../../template/footer.jsp"%>
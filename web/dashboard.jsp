<%-- 
    Document   : dashboard
    Created on : Nov 02, 2016, 12:31:21 AM
    Author     : Rohan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp"%>
<%@include file="template/sidebar.jsp"%>
<script src="app/js/notAlert.js"></script>

<script type="text/javascript">
    loadData();
    function loadData() {
        $.ajax({
            type: "POST",
            url: "Report?action=dashboard",
            success: function(msg) {
                var reply = eval('(' + msg + ')');
                var arrLn1 = reply.des;
                for (var f = 0; f < arrLn1.length; f++) {
                    document.getElementById("employee").innerHTML=arrLn1[f].employee;
                    document.getElementById("customer").innerHTML=arrLn1[f].customer;
                    document.getElementById("package").innerHTML=arrLn1[f].package;
                    document.getElementById("service").innerHTML=arrLn1[f].service;
                    document.getElementById("completed").innerHTML=arrLn1[f].completed;
                    document.getElementById("incompleted").innerHTML=arrLn1[f].incompleted;
                    document.getElementById("hold").innerHTML=arrLn1[f].hold;
                    document.getElementById("ongoing").innerHTML=arrLn1[f].ongoing;
                }
            }
        });
    }
</script>

<div class="">
    <div class="row top_tiles">
        
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-user"></i>
                </div>
                <div class="count" id="employee">0</div>
                <h3>Employees</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-users"></i>
                </div>
                <div class="count" id="customer">0</div>
                <h3>Customers</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-list"></i>
                </div>
                <div class="count" id="package">0</div>
                <h3>Packages</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-recycle"></i>
                </div>
                <div class="count" id="service">0</div>
                <h3>Services</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-stop-circle"></i>
                </div>
                <div class="count" id="completed">0</div>
                <h3>Completed Projects</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-edit"></i>
                </div>
                <div class="count" id="incompleted">0</div>
                <h3>incomplete Projects</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-pause-circle"></i>
                </div>
                <div class="count" id="hold">0</div>
                <h3>Hold Projects</h3>
                <p></p>
            </div>
        </div>
        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="tile-stats">
                <div class="icon"><i class="fa fa-play-circle"></i>
                </div>
                <div class="count" id="ongoing">0</div>
                <h3>Ongoing Projects</h3>
                <p></p>
            </div>
        </div>
    </div>




</div>

<script src="resources/js/echart/echarts-all.js"></script>
<script src="resources/js/echart/green.js"></script>
<script src="app/js/dashboard.js"></script>

<!-- footer content -->
<%@include file="template/footer.jsp"%>

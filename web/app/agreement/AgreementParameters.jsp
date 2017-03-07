<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Agreement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>
<script src="../js/branch.js"></script>
<script src="../js/notAlert.js"></script>



<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Agreement Parameters
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    
    <div class="row">
       
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
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

                                    <th>Parameter</th>
                                    <th>Parameter Name</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <td>P_CDAY</td>
                                    <td>to add current day</td>
                                </tr> 
                                <tr>
                                    <td>P_CMON</td>
                                    <td>to add current month</td>
                                </tr> 
                                <tr>
                                    <td>P_CYEAR</td>
                                    <td>to add current year</td>
                                </tr>  
                                <tr>
                                    <td>P_CN</td>
                                    <td>to add company name</td>
                                </tr>
                                <tr>
                                    <td>P_CA</td>
                                    <td>to add company address</td>
                                </tr>
                                <tr>
                                    <td>P_TPA</td>
                                    <td>to add Total project amount</td>
                                </tr>
                                <tr>
                                    <td>P_FIA</td>
                                    <td>to add first installment amount</td>
                                </tr>
                                <tr>
                                    <td>P_SIA</td>
                                    <td>to add second installment amount</td>
                                </tr>
                                <tr>
                                    <td>P_TIA</td>
                                    <td>to add third installment amount</td>
                                </tr>
                                
                                
                                <tr>
                                    <td>P_PSD</td>
                                    <td>to add project start day</td>
                                </tr>
                                <tr>
                                    <td>P_PSM</td>
                                    <td>to add project start month</td>
                                </tr>
                                <tr>
                                    <td>P_PSY</td>
                                    <td>to add project start year</td>
                                </tr>
                                <tr>
                                    <td>P_PED</td>
                                    <td>to add project end day</td>
                                </tr>
                                <tr>
                                    <td>P_PEM</td>
                                    <td>to add project end month</td>
                                </tr>
                                <tr>
                                    <td>P_PEY</td>
                                    <td>to add project end year</td>
                                </tr>
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





<!-- footer content -->
<%@include file="../../template/footer.jsp"%>

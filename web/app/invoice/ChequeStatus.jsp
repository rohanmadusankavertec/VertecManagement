<%-- 
    Document   : customerDetails
    Created on : Oct 20, 2016, 11:17:04 AM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.PaymentDetails"%>
<%@page import="com.vertec.hibe.model.Payment"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<div class="">

    <div class="clearfix"></div>
    <%List<PaymentDetails> payList = (List<PaymentDetails>) request.getAttribute("paymentDetails");%>
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Cheques <small>up to now</small></h2>
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
                                    <th>Cheque No</th>
                                    <th>Bank Name</th>
                                    <th>Cheque Date</th>
                                    <th>Status</th>
                                    <th class=" no-link last"><span class="nobr">Action</span>
                                    </th>
                                </tr>
                            </thead>

                            <tbody>
                                <%
                                    for (PaymentDetails p : payList) {
                                %>
                                <tr>
                                    <td class=" "><%=p.getChequeNo()%></td>
                                    <td class=" "><%=p.getBankName()%></td>
                                    <td class=" "><%=p.getChequeDate()%></td>
                                    <td class=" "><%

                                        if (p.getIsValid() == null) {
                                            out.write("Pending");
                                        } else if (p.getIsValid() == true) {
                                            out.write("Clear");
                                        } else if (p.getIsValid() == false) {
                                            out.write("Returned");
                                        }

                                        %></td>
                                    <td>
                                        <%                                            if (p.getIsValid() == null) {
                                        %>
                                        <form action="Invoice">
                                            <input type="hidden" value="ClearCheque" name="action"/>
                                            <input type="hidden" name="pdid" value="<%=p.getId()%>"/>
                                            <input type="submit" value="Clear"/>
                                        </form>

                                        <form action="Invoice">
                                            <input type="hidden" value="ReturnCheque" name="action"/>
                                            <input type="hidden" name="pdid" value="<%=p.getId()%>"/>
                                            <input type="submit" value="Return"/>
                                        </form>

                                        <%
                                            }
                                        %>
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

<%@include file="../../template/footer.jsp"%>
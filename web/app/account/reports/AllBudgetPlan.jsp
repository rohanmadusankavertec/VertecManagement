
<%@page import="com.vertec.hibe.model.BudgetPlan"%>
<%@page import="com.vertec.hibe.model.NominalCode"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%
//    String st = (String) request.getAttribute("state");
//    String fd = (String) request.getAttribute("functionData");
//    String cc = (String) request.getAttribute("costcenter");
//    String nc = (String) request.getAttribute("nominalCode");
//    String by = (String) request.getAttribute("years");
//    List<BudgetPlan> bp = (List<BudgetPlan>) request.getAttribute("bp");

    double tot = 0;
%>





<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Budget Plan Report
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

                </div>
                <div class="x_content">

                    <form>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="name">State <span class="required"></span></label>
                            <label class="control-label col-md-4 col-sm-4 col-xs-4" for="name"><%=st%> <span class="required"></span></label>


                        </div>
                        <div class="clearfix"></div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="name">Function <span class="required"></span></label>
                            <label class="control-label col-md-4 col-sm-4 col-xs-4" for="name"><%=fd%> <span class="required"></span></label>


                        </div>
                        <div class="clearfix"></div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="name">Cost Center <span class="required"></span></label>
                            <label class="control-label col-md-4 col-sm-4 col-xs-4" for="name"><%=cc%> <span class="required"></span></label>


                        </div>
                        <div class="clearfix"></div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="name">Nominal Code <span class="required"></span></label>
                            <label class="control-label col-md-4 col-sm-4 col-xs-4" for="name"><%=nc%> <span class="required"></span></label>


                        </div>
                        <div class="clearfix"></div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="name">Year <span class="required"></span></label>
                            <label class="control-label col-md-4 col-sm-4 col-xs-4" for="name"><%=by%> <span class="required"></span></label>


                        </div>
                        <div class="clearfix"></div>
                        <div class="ln_solid"></div>





                        <center>
                            <table style="width: 80%;border:1px solid #000 ;border-collapse: collapse;">
                                <thead>
                                    <tr style="border: 1px solid black; background-color: #bfc9ff;">
                                        <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Month</th>
                                        <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">January</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">
                                            <%
                                                boolean bool = true;
                                                for (BudgetPlan b : bp) {
                                                    if (Integer.parseInt(b.getMonth()) == 1) {
                                                        out.write(b.getAmount() + "");
                                                        tot += b.getAmount();
                                                        bool = false;
                                                    }
                                                }
                                                if (bool) {
                                                    out.write("0000.00");
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">February</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 2) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">March</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 3) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">April</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 4) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">May</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 5) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">June</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 6) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">July</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 7) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">August</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 8) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">September</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 9) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">October</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 10) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">November</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 11) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }

                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;">December</td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%                                            bool = true;
                                            for (BudgetPlan b : bp) {
                                                if (Integer.parseInt(b.getMonth()) == 12) {
                                                    out.write(b.getAmount() + "");
                                                    tot += b.getAmount();
                                                    bool = false;
                                                }
                                            }
                                            if (bool) {
                                                out.write("0000.00");
                                            }
                                            %></td>
                                    </tr>
                                    <tr style="border: 1px solid black;">
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><strong>Total Budget</strong></td>
                                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=tot%></td>
                                    </tr>
                                </tbody>
                            </table>
                        </center>











                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" onclick="window.print();" type="button" style="float: right;" class="btn btn-success">Print Report</button>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>





    <center>

    </center>
    <div class="clearfix"></div>
    <div class="clearfix"></div>
</div>




<%@include file="../../../template/footer.jsp"%>
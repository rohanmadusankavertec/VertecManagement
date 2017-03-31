
<%@page import="com.vertec.hibe.model.ActualCost"%>
<%@page import="com.vertec.hibe.model.BudgetPlan"%>
<%@page import="com.vertec.hibe.model.NominalCode"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>


<%
    String st = (String) request.getAttribute("state");
    String fd = (String) request.getAttribute("functionData");
    String cc = (String) request.getAttribute("costcenter");
    String nc = (String) request.getAttribute("nominalCode");
    String by = (String) request.getAttribute("years");
    List<ActualCost> ac = (List<ActualCost>) request.getAttribute("ac");
    List<BudgetPlan> bp = (List<BudgetPlan>) request.getAttribute("bp");

    double tot = 0;
    double tot2=0;
%>





<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Actual Cost Report
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
                            <table style="width: 100%;">
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>January</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 1) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>February</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 2) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>March</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 3) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>April</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 4) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                 bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                    
                                    <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>May</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 5) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                    
                                    <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>June</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 6) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>July</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 7) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>August</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 8) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>September</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 9) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>October</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 10) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                 bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                    
                                    <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>November</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 11) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                    
                                    <tr style="width: 100%;">
                                    <td style="width: 100%;">
                                        <h4>December</h4>


                                        <table style="margin-left: 10%; width: 100%" >
                                            <%
                                                tot2=0;
                                                for (ActualCost b : ac) {
                                                    if (Integer.parseInt(b.getMonth()) == 12) {
                                                        tot2+=b.getAmount();
                                            %>

                                            <tr>
                                                <td style="width: 20%">
                                                    <%=b.getReferenceNo()%>
                                                </td>
                                                <td style="width: 60%">
                                                    <%=b.getDescription()%>
                                                </td>
                                                <td style="width: 20%">
                                                    <%=b.getAmount()%>
                                                </td>
                                            </tr>

                                            <%
                                                    }
                                                }

                                            %>
                                            
                                            <tr>
                                                <td style="width: 10%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Budget Amount</strong></div>
                                                </td>
                                                <td style="width: 30%">
                                                    <strong>  <%
                                                bool = true;
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
                                            %></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 20%">
                                                </td>
                                                <td style="width: 60%">
                                                    <div style="float: right; margin-right: 10px;"><strong>Actual Cost</strong></div>
                                                
                                                </td>
                                                <td style="width: 20%">
                                                    <strong>  <%=tot2%></strong>
                                                </td>
                                            </tr>
                                        </table>


                                    </td>
                                </tr>
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                </tr>
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
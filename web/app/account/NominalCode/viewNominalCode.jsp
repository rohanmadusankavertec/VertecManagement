<%-- 
    Document   : viewCustomer
    Created on : Oct 20, 2016, 1:14:40 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.CostCenter"%>
<%@page import="com.vertec.hibe.model.NominalCode"%>
<%@page import="com.vertec.hibe.model.State"%>
<%@page import="com.vertec.hibe.model.FunctionData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%
    NominalCode nc = (NominalCode) request.getAttribute("nc");
    List<State> StateList = (List<State>) request.getAttribute("state");
    List<FunctionData> fd = (List<FunctionData>) request.getAttribute("fd");
    List<CostCenter> cs = (List<CostCenter>) request.getAttribute("costcenter");
%>

<script type="text/javascript">
    
    setTimeout("SetData()","500");
    
    function SetData(){
        document.getElementById('stateid').value=<%=nc.getCostCenterId().getFunctionId().getStateId().getId() %>;
        document.getElementById('fid').value=<%=nc.getCostCenterId().getFunctionId().getId()%>;
        document.getElementById('ccid').value=<%=nc.getCostCenterId().getId()%>;
        
        
    }
</script>



<div class="">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2><small></small></h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form action="NominalCode?action=UpNC" method="post" class="form-horizontal form-label-left" novalidate >
                        <span class="section">Function Update</span>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select State <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="stateid" id="stateid">
                                    <option selected="true" disabled value="">Select State</option>
                                    <%                                        
                                        for (State s : StateList) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                                <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Function <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="fid" id="fid">
                                    <option selected="true" disabled value="">Select Function</option>
                                    <%                                        
                                        for (FunctionData s : fd) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                                <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Select Cost Center <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="ccid" id="ccid">
                                    <option selected="true" disabled value="">Select Cost Center</option>
                                    <%                                        
                                        for (CostCenter s : cs) {
                                    %>
                                    <option value="<%=s.getId()%>" ><%=s.getCode()+" "+ s.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>                              
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name"> Nominal Code <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  name="nc" data-validate-words="2" required="required" type="text" value="<%=nc.getCode()%>">
                                <input name="ncId" required="required" type="hidden" value="<%=nc.getId()%>"> 
                            </div>
                        </div>
                            <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name"> Nominal Code Name<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12"  name="Name" data-validate-words="2" required="required" type="text" value="<%=nc.getName()%>">
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success">Update</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../template/footer.jsp"%>

<%@include file="../../../template/header.jsp"%>
<%@include file="../../../template/sidebar.jsp"%>




<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Sales Report
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
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <form action="AccountReport">

                        </p>
                        <div style="padding-top: 10px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select State <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="catId" id="catId" >
                                    <option selected="true" disabled value="">Select State</option>
                                    <option value="">All</option>

                                </select>                              
                            </div>
                        </div>
                        <div style="padding-top: 10px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Function <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="catId" id="catId" >
                                    <option selected="true" disabled value="">Select Function</option>
                                    <option value="">All</option>

                                </select>                              
                            </div>
                        </div>
                        <div style="padding-top: 10px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Cost Center <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="catId" id="catId" >
                                    <option selected="true" disabled value="">Select Cost Center</option>
                                    <option value="">All</option>

                                </select>                              
                            </div>
                        </div>
                        <div style="padding-top: 10px;">
                            <label class="control-label col-md-4 col-sm-4 col-xs-12" for="name">Select Nominal Code <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="catId" id="catId" >
                                    <option selected="true" disabled value="">Select Nominal Code</option>
                                    <option value="">All</option>

                                </select>                              
                            </div>
                        </div>

                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success">Submit</button>
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
<%-- 
    Document   : formValidation
    Created on : Mar 21, 2016, 4:09:08 PM
    Author     : User
--%>

<%@page import="com.vertec.hibe.model.Quotation"%>
<%@page import="com.vertec.hibe.model.ProjectProposal"%>
<%@page import="com.vertec.hibe.model.Customer"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="com.vertec.hibe.model.Agreement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../template/header.jsp"%>
<%@include file="../../template/sidebar.jsp"%>

<script src="TextEditor/ckeditor.js"></script>
<script src="TextEditor/samples/js/sample.js"></script>
<link rel="stylesheet" href="TextEditor/samples/css/samples.css">
<link rel="stylesheet" href="TextEditor/samples/toolbarconfigurator/lib/codemirror/neo.css">
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
    
    function SaveTemplate() {
        var name = document.getElementById("templatename").value;
        var textdata = document.getElementById("editor").innerHTML;
        if (name === "") {
            sm_warning("Please Enter the Template name");
        } else {

            var xmlHttp = getAjaxObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                {
                    var reply = xmlHttp.responseText;
                    if (reply === "Success") {
                        loadFeatures();
                        nom_Success("Template added Successfully ");
                    } else {
                        sm_warning("Template is not added, Please Try again.");
                    }
                }
            };
            xmlHttp.open("POST", "Template?action=SaveAgreementTemplate&name=" + name+"&data=" + textdata, true);
            xmlHttp.send();
        }
    }
</script>



<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Design Agreement
            </h3>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <form action="Template?action=SaveAgreementTemplate" method="POST">

                 <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Template Name
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="templatename" name="name" class="form-control col-md-7 col-xs-12"  type="text">
                            </div>
                        </div>
                <div class="clearfix"></div>

                <div class="adjoined-bottom" style="padding-top: 50px;">
                    <div class="grid-container">
                        <div class="grid-width-100">
                            <!--<div id="editor">-->
                            <textarea id="editor" name="data">
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                <div>

            <div class="clearfix"><img src="resources/images/logo.png" style="height:50px; width:200px" /></div>

<div class="clearfix">&nbsp;</div>

<table align="center" style="width:90%">
	<tbody>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div class="clearfix">&nbsp;</div>
			<small>Registered Address : No 11/B, Mawala Junction, Wadduwa,Sri Lanka. (12560)<br />
			Telephone : (+94) 38 22 96 305 / (+94) 38 22 85 601&nbsp;<br />
			Hotline : (+94) 71 81 57 57 5 Email : vertecitsolutions@gmail.com </small></td>
			<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
			<td><img src="resources/images/vglogo.png" style="float:right; height:80px; width:80px" /></td>
		</tr>
	</tbody>
</table>

<div class="clearfix">&nbsp;</div>

<div class="clearfix">&nbsp;</div>

<div class="row">
<div class="col-md-12 col-sm-12 col-xs-12">
<div class="x_panel">
<div class="x_title">
<div class="clearfix">&nbsp;</div>

<div class="clearfix">
<h1 style="text-align: center;"><strong><u>SERVICE AGREEMENT</u></strong></h1>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p style="margin-left:5.0pt">This service agreement made and entered in to at &hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;(District) on this &hellip;&hellip;&hellip;&hellip;day of &hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;&hellip;(M/Y) by and between Vertec IT solutions (Private) Ltd, a company duly in cooperated in the Republic of Sri Lanka. having the registered office at No.11/B, Mawala Junction, Wadduwa. (Herein after referred to as &quot;<strong>COMPANY</strong>&quot;) &nbsp;&nbsp;and G.R.T. Property Developments (Private) Ltd. a company duly in cooperated in the Republic of Sri Lanka. having the registered office at No.112 Rukgaha Roadd, cross junction, Alubomulla, Panadura (Herein after referred to as &quot;<strong>CLIENT</strong>&quot;)</p>

<p>&nbsp;</p>

<h2 style="margin-left: 5pt; text-align: center;"><strong>WITNESSETH</strong></h2>

<p style="margin-left:5.0pt"><strong>WHEREAS COMPANY is duly</strong> in cooperated in the Republic of Sri Lanka and is in the business of providing IT Solutions and the CLIENT (Property Developers) is interested in getting the services of the <strong>COMPANY.</strong></p>

<p style="margin-left:5.0pt"><strong>AND WHEREAS the COMPANY is interested in providing its services to the CLIENT and the CLIENT is interested in getting the services of the COMPANY subject to the terms and conditions hereinafter set out in this agreement. </strong></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p style="margin-left:5.0pt"><strong>Terms and Conditions of Daily Operating System with Billing, Payroll Accounting Software of the &ldquo;CLIENT&rdquo;</strong></p>

<p>&nbsp;</p>

<h3 style="margin-left: 5pt;"><strong>GENERAL TERMS AND CONDITIONS</strong></h3>

<ol>
	<li>The effective date of the agreement is the &hellip;&hellip;&hellip;&hellip;&hellip;. &nbsp;day of &hellip;&hellip;&hellip;&hellip;&hellip;, 20&hellip;&hellip;..</li>
	<li>The completed project will be handed over within 04 Month/s of the above date.</li>
	<li>Project training of staff members will be given in the client&rsquo;s office.</li>
	<li>Delay of the project without any sound reason shall be exposed to Liquidated Damages of LKR. 500.00 per day maximum up to 20% of quoted value.(Both Party)</li>
	<li>The CLIENT must submit the required information without any delay.</li>
	<li>The COMPANY must submit the domain name. (Online Link)</li>
	<li>The COMPANY must provide hosting and domain facility.</li>
	<li>The hosting and domain charges must be informed to the client with the next year renewal date.</li>
	<li>The COMPANY must inform the renewal date before one month and the CLIENT must make the payment within seven days of the invoice.</li>
	<li>If CLIENT requires &nbsp;further modification or development while developing or after handing over the project is based on quotation.</li>
</ol>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<h4><strong><u>Development Outcome:</u></strong></h4>

<p>The Parties shall confirm the following outcome indicated:</p>

<p>&nbsp;</p>

<p>The COMPANY agreed to develop a <strong>Daily Operating System with Billing, Payroll Accounting Software</strong> such that its features shall confirm to the specifications set out in the Technical Specifications as follows.</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong><u>Key Features of the Billing System</u></strong></p>

<p>Billing, Credits Sales, Customer Discount, Good Receive Note (GRN), Good Delivery note (GDN),</p>

<p>Purchase Order, Purchase Return, Wastage / Damage Entry, Petty Cash, Expenses, Payment, Fast Moving items, Slow Moving Items, Non Moving Items.</p>

<p>&nbsp;</p>

<p><strong><u>Key Features of the Payroll System</u></strong></p>

<p><strong>Periodical Reports</strong> - Pay Slips, Pay Sheets, Pay Summary, Minus Net Salary Report, Attendance Summary, Loan Deduction Detail, increments, ETF/EPF contributions, EPF e-Return System (Central Bank of Sri Lanka).Gratuity calculation, stop Payments,. etc</p>

<p>&nbsp;</p>

<p><strong>Other Reports</strong> - Bonus, Bonus to Bank Report, Loan Data, Leave Balance, etc</p>

<p>&nbsp;</p>

<p><strong><u>Key Features of the Accounting System/Reports &amp; Summaries </u></strong></p>

<p><strong>General Ledger Management</strong> - This module enhances visibility into virtually all standard financial transactions such as accounts payroll (more), receivable, payable and sales orders. Additionally, accounting software packages allow finance professionals to quickly and easily conduct consolidations by dynamically combining multiple ledgers(such as those from different departments or business units) into a single, complete financial statement.</p>

<p>&nbsp;</p>

<p><strong>Controlling and Budget Management</strong> - With an accounting software solution, financial teams can achieve and maintain tighter control over departmental and corporate budgets, and conduct more rapid and accurate internal and time accounting, cost accounting, product</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong><u>Timeline:</u></strong></p>

<p>The <strong>COMPANY</strong> agrees to make all reasonable efforts to deliver the functionality specified project on time.</p>

<p>&nbsp;</p>

<p>Delay without any sound reason shall be exposed to Liquidated Damages of LKR. 500/- per day maximum up to 20% of quoted value.(Both Party)</p>

<p>&nbsp;</p>

<p>CONDITIONS</p>

<ul>
	<li>Project development delay on or before said delivery date.</li>
	<li>Delay of project &nbsp;training of staff member, after the development of the project (within four working days)</li>
	<li>If the COMPANY requested content information, the CLIENT has to hand them over within four working days.</li>
	<li>If the COMPANY has completed the agreed level of the project the CLIENT must make the payment as per the quotation, before four working days. (invoice will be submitted)</li>
</ul>

<p>&nbsp;</p>

<p><strong><u>Training:</u></strong></p>

<p>A Free of charge training will be given to operate backend and use the system smoothly. The training sessions will be carried out in the customer location.</p>

<p>&nbsp;</p>

<p><strong><u>Cost:</u></strong></p>

<p>The COMPANY agrees to deliver all functionality specified in the Technical Specifications Document for a Total Cost <strong>Rs.</strong> 120,000.00 as per the quotation and any further modification or development while the developing or after handing over the project is based on quotation.</p>

<p>&nbsp;</p>

<p style="margin-left:5.0pt">Payments are as follows.</p>

<ul>
	<li>The advance payment should be made on the date hereof written in this agreement.(10% of the total cost Rs 12,000.00)</li>
	<li>The second &nbsp;payment (50% of the total cost Rs 60,000.00) should &nbsp;be &nbsp;made &nbsp;on &nbsp;the &nbsp;date &nbsp;of &nbsp;final &nbsp;project &nbsp;structure Confirmation. (Structure means : &nbsp;standard version to understand basic client requirements)</li>
	<li>Final payment (Rs 50,000.00) should be made on the date of the completed project confirmation by the CLIENT. (before handing over final username, &nbsp;password along with the final &nbsp;project)</li>
</ul>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p style="margin-left:5.0pt"><strong><u>Maintenance Support (Troubleshooting)</u></strong></p>

<p>&nbsp;</p>

<ul>
	<li>1 st year free of charge online troubleshooting.</li>
	<li>Visit for any further Modification or Development (if required) after handing over the project troubleshooting charges <strong>(Colombo, Kaluthara Rs.2,000.00) + Rs:60/= Per 1 km.</strong></li>
</ul>

<p>&nbsp;</p>

<p style="margin-left:8.0pt"><strong><u>TERMS:</u></strong></p>

<p>&nbsp;</p>

<p style="margin-left:8.0pt">The term of each Service Agreement shall begin as of the Effective Date of such Service Agreement and continue for the term specified on such Service Agreement. The term of each Service Agreement shall automatically extend for the Renewal of domain and hosting. Term specified on such Service Agreement, unless such Service Agreement is terminated by either party by written notice of termination at least thirty (30) days prior to the expiration of such term then in effect, or until otherwise terminated pursuant to the provisions of such Service Agreement or these General Terms and Conditions.</p>

<p>&nbsp;</p>
</div>

<div class="clearfix">&nbsp;</div>
</div>

<div class="x_content">
<div class="row"><!-- /.col --></div>
<!-- info row -->

<div class="row invoice-info">
<div class="col-sm-12 col-lg-6 col-md-6 invoice-col">
<address>&nbsp;</address>

<div class="clearfix">&nbsp;</div>
</div>
<!-- /.col -->

<div class="col-sm-12 col-lg-6 col-md-6 invoice-col" style="margin-left: 200px;">
<address>&nbsp;</address>
</div>
</div>
<!-- /.row -->

<table align="center" style="padding-top:100px">
	<tbody>
		<tr>
			<td>
			<p>Prepared By<br />
			<br />
			<br />
			....................................<br />
			Lahiru Wipulaguna<br />
			(Manager Operations)</p>
			</td>
			<td style="width:300px">&nbsp;</td>
			<td>
			<p>Approved By<br />
			<br />
			<br />
			....................................<br />
			RAVI EGODAWITHARANA<br />
			(C.E.O.)</p>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>

<p style="text-align: center;">This is a computer generated document</p>
</div></div>
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                            </textarea>
                            <!--</div>-->
                        </div>
                    </div>
                </div>
                <script>
                    initSample();
                </script>


                <div class="form-group" style="float: left; margin-left: 100px; padding-top: 50px;">
                    <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                        <button id="send" type="button" onclick="window.open('Template?action=ViewParameters', '_blank');" class="btn btn-dark">Show Parameters</button>
                    </div>
                </div>

                <div class="form-group" style="float: right; margin-right: 100px; padding-top: 50px;">
                    <div class="col-md-6 col-md-offset-4 col-lg-offset-4">
                        <button id="send" type="submit"  class="btn btn-success">Save Template</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>

</div>





<!-- footer content -->
<%@include file="../../template/footer.jsp"%>

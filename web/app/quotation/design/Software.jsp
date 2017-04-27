<%-- 
    Document   : WebSite
    Created on : Dec 8, 2016, 11:16:13 AM
    Author     : Java-Dev-Ruchira
--%>

<%@page import="com.vertec.hibe.model.QuotationHasPackages"%>
<%@page import="com.vertec.hibe.model.SoftwareQuotation"%>
<%@page import="com.vertec.hibe.model.Service"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.vertec.hibe.model.Quotation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vertec.hibe.model.Installment"%>
<%@page import="com.vertec.hibe.model.QuotationHasFeatures"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Business Promotion</title>
        <script type="text/javascript">


            function getAjaxObject() {
                var xmlHttp;

                if (window.XMLHttpRequest) {

                    xmlHttp = new XMLHttpRequest();

                } else
                {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }

                return xmlHttp;

            }
            function LoadPackageFeatures2() {
                var sid = document.getElementById("service").value;
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
                    {
                        var msg = xmlHttp.responseText;
                        var reply = eval('(' + msg + ')');
                        var arrLn1 = reply.feature;
                        var ihtml = "";
                        var fea = document.getElementById("feature");
                        var ihtml = "";
                        ihtml += "<option selected='true'>Select feature</option>";
                        for (var f = 0; f < arrLn1.length; f++) {

                            ihtml += "<option value='" + arrLn1[f].id + "~" + arrLn1[f].name + "'>" + arrLn1[f].name + "</option>";
                        }
                        fea.innerHTML = ihtml;
                    }
                };
                xmlHttp.open("POST", "Quotation?action=viewFeatures&hidden=" + sid, true);
                xmlHttp.send();
            }

            var arr = [];
            function addFeatures() {

                var featureId = document.getElementById("feature").value;
                var headid = document.getElementById("service").value;
                var fea = featureId.split("~");
                var tb = document.getElementById("featurteBody");
                var data = tb.innerHTML;
                var bool = true;
                if (featureId !== "Select feature") {

                    for (var i = 0; i < arr.length; i++) {
                        if (arr[i] === fea[0]) {
                            bool = false;
                            alert("exist");

                        }
                    }
                    if (bool) {
                        data += "<tr><td>" + fea[1] + "</td></tr>";
                        tb.innerHTML = data;
                        arr.push(fea[0]);
                    }
                }
            }
        </script>


    </head>
    <%
        List<QuotationHasFeatures> list = (List<QuotationHasFeatures>) request.getAttribute("qhfs");
        List<Installment> Inslist = (List<Installment>) request.getAttribute("ins");
        Quotation q = (Quotation) request.getAttribute("quo");
        
        List<SoftwareQuotation> sL = (List<SoftwareQuotation>) request.getAttribute("softList");
        List<QuotationHasPackages> pL = (List<QuotationHasPackages>) request.getAttribute("pList");
        List<QuotationHasPackages> qpL = (List<QuotationHasPackages>) request.getAttribute("qpList");
        String sAmot = (String) request.getAttribute("tAmt");
        System.out.println("....print amount.."+sAmot);

    %>


    <body onload="window.print();">

    <center>
        <img src="resources/images/Quotation-Header.jpg" width="95%" height="150px;"/>
    </center>
    <br>

<table style="width: 100%;">
        <tr style="height: 20px;width: 80%;">
            <td style="width: 40%"> TO :<%=q.getProjectProposalId().getCustomerId().getCompanyName()%></td>
            <td style="width: 40%;"></td>
            <td style="width: 20%"> Date :<%
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                out.write(sdf.format(date));
                %>
            </td>
        </tr>
        <tr>
            <td>    Att :<%=q.getProjectProposalId().getCustomerId().getFirstName() + " " + q.getProjectProposalId().getCustomerId().getLastName()%></td>
            <td></td>
            <td>Quotation#:<%=q.getId()%>   </td>
        </tr>
        <tr>
            <td> Contact No :<%=q.getProjectProposalId().getCustomerId().getMobileNo()%></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>    E-mail :<%=q.getProjectProposalId().getCustomerId().getEmail()%></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <div class="clearfix">&nbsp;</div>
    <div class="clearfix">&nbsp;</div>
    <h3 style="text-align: center;">Quotation for Software</h3>
    <center>
        <table style="width: 500px;border:1px solid #000 ;border-collapse: collapse;">
            <thead>
                <tr style="border: 1px solid black; background-color: #bfc9ff;">
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Description</th>
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Qty</th>
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Unit Price</th>
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Amount</th>
                </tr>
            </thead>
            <tbody>
                <%for (SoftwareQuotation s : sL) {%>
                <tr style="border: 1px solid black;">
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=s.getDescription()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=s.getQty()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=s.getPrice()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=s.getPrice() * s.getQty()%></td>
                </tr>
                <%}%>
                <tr>
                    <td></td>
                    <td></td>
                    <td><b>Total Amount</b></td>
                    <td style="border: 1px solid black;height: 8px;padding: 10px;"><%=sAmot%></td>
                </tr>
            </tbody>
        </table>
    </center>
    <div class="clearfix">&nbsp;</div>

    <h3 style="text-align: center;">Features</h3>





    <center>
        <table  id="featureTable" style="width: 500px;border-collapse: collapse;">

            <%for (QuotationHasPackages qp : pL) {%>
            <tr style=""><th style="text-align: left;padding: 10px;">Key Feature of The <%=qp.getPackageHasFeaturesId().getPackageId().getName()%></th>
            </tr>
            <%for (QuotationHasPackages qp1 : qpL) {
                    if (qp.getPackageHasFeaturesId().getPackageId().getId() == qp1.getPackageHasFeaturesId().getPackageId().getId()) {
            %>
            <tr><td style="text-align: left;height: 25px;border: 1px solid #000;padding: 10px;border-collapse: collapse;"><%=qp1.getPackageHasFeaturesId().getFeaturesId().getFeature()%></td></tr>
                <%}

                    }%>
            <tr><th><br></th></tr>
                    <%}%>

        </table>

    </center>


    <p><strong>Reinstallations</strong></p>


    <p>Reinstallations in a hardware failure will be done free of charge during the warranty period. (if the hardware is taken through Vertec Software Solutions Only). If not the company will charge Rs:4500/= per machine to reinstall and setups.  								

    </p>

    <p><strong>Advance payment</strong></p>
    <p>Advance payment of 40% should be made and 2nd payment of 40% should be made at the Final structure Confirmation. Balance 20% to be paid at the final installation. Testing period is One month only								
    </p>
    <p><strong>Training</strong></p>
    <p>A Free of charge training will be given to operate the system smoothly. It is necessary to arrange at least 2 operators for these training sessions. The training sessions will be carried out in the customer location.								
    </p>

    <p><strong>Maintenance Support (Troubleshooting)</strong></p>
    <p>1st year- free of charge. After testing period Online  troubleshooting- free of Charge .</p>
    <p><b>Colombo, Kaluthara : Rs.2,000.00, other + Rs:40/=  Per 1 km</b> </p>
    <p><b>Period of Validity: This Quotation is valid for 7 days only.</b></p>


    <p><b>We hope the above summarized details are clear and fulfill your requirement  and if you need any further clarification, please feel free to contact the undersigned.</b></p>


    <table align="center" style="padding-top:100px">
        <tbody>
            <tr>
                <td>
                    <p>Prepared By<br />
                        <br />
                        <br />
                        ....................................<br />
                        Hansy Kumaralal<br />
                        (Project Coordinator )</p>
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


    <p style="text-align: center;">This is a computer generated document</p>



</body>
</html>

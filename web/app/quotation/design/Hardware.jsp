<%-- 
    Document   : WebSite
    Created on : Dec 8, 2016, 11:16:13 AM
    Author     : Java-Dev-Ruchira
--%>

<%@page import="com.vertec.hibe.model.HardwareQuotation"%>
<%@page import="com.vertec.hibe.model.HardwareItem"%>
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
        <title>Hardware Quotation</title>

    </head>
    <%
        List<HardwareItem> list = (List<HardwareItem>) request.getAttribute("hiList");
        HardwareQuotation hq = (HardwareQuotation) request.getAttribute("hquotation");

    %>


    <body onload="window.print()">

    <center>
        <img src="resources/images/Quotation-Header.jpg" width="95%" height="150px;"/>
    </center>
    <br>
    <table style="width: 100%;">
        <tr style="height: 20px;width: 80%;">
            <td style="width: 40%"> TO :<%=hq.getCustomerId().getCompanyName()%></td>
            <td style="width: 40%;"></td>
            <td style="width: 20%"> Date :<%
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                out.write(sdf.format(date));
                %>
            </td>
        </tr>
        <tr>
            <td>    Att :<%=hq.getCustomerId().getFirstName() + " " + hq.getCustomerId().getLastName()%></td>
            <td></td>
            <td>Quotation#:<%=hq.getId()%>   </td>
        </tr>
        <tr>
            <td> Contact No :<%=hq.getCustomerId().getMobileNo()%></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>    E-mail :<%=hq.getCustomerId().getEmail()%></td>
            <td></td>
            <td></td>
        </tr>
    </table>

    <div class="clearfix">&nbsp;</div>
    <div class="clearfix">&nbsp;</div>
    <h3 style="text-align: center;">Quotation for Hardware Items</h3>

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
                <%for (HardwareItem h : list) {%>
                <tr style="border: 1px solid black;">
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=h.getDescription()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=h.getQty()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=h.getPrice()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=h.getPrice() * h.getQty()%></td>
                </tr>
                <%}%>
                <tr>
                    <td></td>
                    <td></td>
                    <td><b>Total Amount</b></td>
                    <td style="border: 1px solid black;height: 8px;padding: 10px;"><%=hq.getAmount()%></td>
                </tr>
            </tbody>
        </table>
    </center>










    <!--    <p><strong>Reinstallations</strong></p>
                                                                    
        
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
    
    
        <p><b>We hope the above summarized details are clear and fulfill your requirement  and if you need any further clarification, please feel free to contact the undersigned.</b></p>-->


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

<%-- 
    Document   : WebSite
    Created on : Dec 8, 2016, 11:16:13 AM
    Author     : Java-Dev-Ruchira
--%>

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
        <title>Web site development</title>
    </head>
    <%
        List<QuotationHasFeatures> list = (List<QuotationHasFeatures>) request.getAttribute("qhfs");
        List<Installment> Inslist = (List<Installment>) request.getAttribute("ins");
        Quotation q = (Quotation) request.getAttribute("quo");
        double fp = 0;
        double sp = 0;
        double tp = 0;

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
                <td style="width: 20%"> Date:<%
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    out.write(sdf.format(date));
                    %>
                </td>
            </tr>
            <tr>
                <td>    Att :<%=q.getProjectProposalId().getCustomerId().getFirstName() +" "+q.getProjectProposalId().getCustomerId().getLastName() %></td>
                <td></td>
                <td>Quotation#:<%=q.getId()%>   </td>

            </tr>
            <tr>
                <td>Contact No :<%=q.getProjectProposalId().getCustomerId().getMobileNo() %></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>E-mail : <%=q.getProjectProposalId().getCustomerId().getEmail() %></td>
                <td></td>
                <td></td>

            </tr>
        </table>
    <div class="clearfix">&nbsp;</div>
    <div class="clearfix">&nbsp;</div>
    <h3 style="text-align: center;">Customization:Web site Development</h3>

    <center>
        <table  style="width: 80%;border:1px solid #000 ;border-collapse: collapse;">
            <thead>
                <tr  style="border: 1px solid black; background-color: #bfc9ff;">
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Feature</th>
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Description</th>
                </tr>
            </thead>
            <tbody>
                <% for (QuotationHasFeatures qh : list) {%>
                <tr style="border: 1px solid black;">
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=qh.getFeaturesId().getFeature()%></td>
                    <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=qh.getDescription()%></td>
                </tr>
                <%} %>
            </tbody>
        </table>
    </center>
    <div class="clearfix">&nbsp;</div>

    <!--        <h3 style="text-align: center;">Procedure of web designing</h3>
    
            <table style="width: 500px;">
                <thead>
                    <tr>
                        <th>Procedure</th>
    
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td></td>
    
                    </tr>
    
                </tbody>
            </table>
    -->



    <h3 style="text-align: center;">Terms and Conditions</h3>




    <p><strong><u>Advance payment</u></strong></p>

    <%for (Installment i : Inslist) {
System.out.println("Quotation ID"+i.getQuotationId().getId());
System.out.println(".......f........"+fp);
System.out.println(".......s........"+sp);
System.out.println(".......t........"+tp);
            if (fp == 0) {
                System.out.println("<<<<<<<<<<<<<<<<f payment"+i.getAmount());
                fp = i.getAmount();
            } else if (sp == 0) {
                System.out.println("<<<<<<<<<<<<<<<<s payment"+i.getAmount());
                sp = i.getAmount();
            } else if (tp == 0) {
                System.out.println("<<<<<<<<<<<<<<<<t payment"+i.getAmount());
                tp = i.getAmount();
            }%>



    <%}%>
    <p>Advance Rs.<%=fp%> and 2nd payment  Rs<%=sp%>  at the Final structure Confirmation, Balance  Rs.<%=tp%>end of uploading the site .</p>




    <p><strong><u>Training</u></strong></p>

    <p>N/A</p>

    <p><strong><u>Maintenance Support (Troubleshooting) and Server  Uploading - Vertec Only</u></strong></p>



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

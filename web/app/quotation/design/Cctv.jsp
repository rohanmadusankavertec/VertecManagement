<%-- 
    Document   : WebSite
    Created on : Dec 8, 2016, 11:16:13 AM
    Author     : Java-Dev-Ruchira
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.vertec.hibe.model.CctvWarranty"%>
<%@page import="com.vertec.hibe.model.CctvQuotationItems"%>
<%@page import="com.vertec.hibe.model.CctvQuotationInfo"%>
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
        <title>CCTV Quotation</title>
        
    </head>
    <%
        List<CctvQuotationItems> list = (List<CctvQuotationItems>) request.getAttribute("info");
        CctvQuotationInfo hq =(CctvQuotationInfo) request.getAttribute("cq");
        CctvWarranty w = (CctvWarranty)request.getAttribute("ccwarrenty");
        System.out.println("..........warrenty.."+w.getDesignation());
        DecimalFormat df = new DecimalFormat("####0.00");
        String cable = df.format(w.getCable());
        double stt = w.getStPayment();
        double sec1 = w.getNdPayment();
        double trd1= w.getRdPayment()
                ;
        int fir = (int)stt;
        int sec = (int)sec1;
        int thrd = (int)trd1;
        System.out.println("..........cable.."+cable);
        System.out.println("..........st.."+fir);
    %>


    <body onload="window.print();">

    <center>
        <img src="resources/images/Quotation-Header.jpg" width="95%" height="150px;"/>
    </center>
    <br>

        <table style="width: 100%;">
            <tr style="height: 20px;width: 80%;">
                <td style="width: 40%"> TO :<%=hq.getProjectProposalId().getCustomerId().getCompanyName() %></td>
               

                <td style="width: 40%;"></td>
                <td style="width: 20%"> Date:<%
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    out.write(sdf.format(date));
                    %>
                </td>
            </tr>
            <tr>
                
                <td>    Att :<%=hq.getProjectProposalId().getCustomerId().getFirstName() +" "+hq.getProjectProposalId().getCustomerId().getLastName() %></td>
                <td></td>
                <td>Quotation#:<%=hq.getId()%></td>

            </tr>
            <tr>
                
                <td>Contact No :<%=hq.getProjectProposalId().getCustomerId().getMobileNo() %></td>
                <td></td>
                <td></td>

            </tr>
            <tr>
                
                <td>E-mail : <%=hq.getProjectProposalId().getCustomerId().getEmail() %></td>
                <td></td>
                <td></td>

            </tr>
        </table>

        

        <div class="clearfix">&nbsp;</div>
        <div class="clearfix">&nbsp;</div>
        <h3 style="text-align: center;">Quotation for CCTV Security System</h3>

    <center>
        <table style="width: 500px;border:1px solid #000 ;border-collapse: collapse;">
            <thead>
                <tr style="border: 1px solid black; background-color: #bfc9ff;">
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Product Item</th>
                    <!--<th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Unit Price</th>-->
                    <th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Qty</th>
                    <!--<th style="text-align: left;height: 25px;border: 1px solid black;padding: 10px;">Amount</th>-->
                </tr>
            </thead>
            <tbody>

                
                    <%for(CctvQuotationItems c: list){%>
                    <tr style="border: 1px solid black;">
                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=c.getCctvItemsId().getName() %></td>
                        <!--<td style="border: 1px solid black;height: 8px;padding: 10px;"><%=c.getCctvItemsId().getPrice() %></td>-->
                        <td style="border: 1px solid black;height: 8px;padding: 8px;"><%=c.getQty() %></td>
                        <!--<td style="border: 1px solid black;height: 8px;padding: 10px;"><%=c.getCctvItemsId().getPrice()*c.getQty()%></td>-->
                        </tr>
                    <%}%>
                    <tr></tr>
                    <tr></tr>
                    <tr>
                        
                        <td style="border: 1px solid black;height: 8px;padding: 10px;"><b>Total</b></td>
                        
                        <td style="border: 1px solid black;height: 8px;padding: 10px;"><%=hq.getTotal()%></td>
                    </tr>

            </tbody>
        </table>
    </center>
                    <br>
   <p><strong> * Additional Cabling Per Meter Rs.<%=cable %></strong></p>
   <br>
   <p><u><strong>TERMS AND CONDITIONS</strong></u></p>
								
    
    <p>All additional Fittings are subjected to the Quotation
    </p>
    <p>Warranty commences at the date of installation and there will be <%=w.getInstallation() %> years warranty on the implemented solution,at any time problems/issues that could arise, would be solved without any additional charge.
    </p>
    <p>We guarantee that each CCTV equipment and Solutions has been carefully tested and is in perfect operating Condition on Delivery.Warranty covers only manufacture’s Defects from the date of purchasing as follows
    </p>
    
    <ul style="list-style-type:disc">
        <li>All Camera’s	<%=w.getCamera() %> years</li>
        <li>DVR (Digital Video Recorder) <%=w.getDvr() %> years</li>
        <li>Hard Disk Drive	<%=w.getHardDisk() %> years</li>
        <%if(w.getMonitor()!= null){ %>
        <li>Monitor <%=w.getMonitor() %> years</li>
        <%}%>
    </ul>

    
    
    
    <p>This guarantee will not be effective, Damages or defects to the other causes,
        such as misused, impropriations, power fluctuations, 
        lightning or other natural disaster or accident and act of good.
    </p>
    <p>Repair or replacements necessitated by such causes not covered by the warranty are subject to charge for labor,
        time and material.
    </p>
    <p>Burn marks and physical damages are not included.
    </p>
    <p>Services will be free of charge within the period of one year and unit will be service three times by company within one year period.
    </p>
    <p>The Given Solutions will be demonstrated and training at their Location after completing installation of the project.Successful implementations Consider as Completed after 02 Weeks period and any additional installations would be charged based on standard rates.
    </p>
    
    <p><strong><b><u>Reinstallations</u></b></strong></p>  
    <p>
        Free service within 1st six months, all other maintenance chargers base on quotation.
    </p>
    
    <p><strong><b><u>Advance payment</u></b></strong></p>
    <p>
        Advance is <%=fir %> % of the cost and 2nd payment will be <%=sec %>% at the end of the caballing part,Balance <%=thrd %>% at the final day of the project.
        </p>
        
        <p><strong><b><u>Maintenance Support (Troubleshooting)</u></b></strong></p>    
   
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
                        <%=w.getPreparedBy() %><br />
                        (<%=w.getDesignation() %>)</p>
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

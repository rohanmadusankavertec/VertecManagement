<%-- 
    Document   : PrintAgreement
    Created on : Nov 4, 2016, 2:12:28 PM
    Author     : vertec-r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="window.print()">
        <%
        String data= (String) request.getAttribute("data");
        out.write(data);
        %>
    </body>
</html>

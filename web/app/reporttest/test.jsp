<%-- 
    Document   : test
    Created on : Oct 31, 2016, 3:27:36 PM
    Author     : vertec-r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style type="text/css">

/*            body {
                background:url(../../resources/images/letter-head.png) no-repeat;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            @page {
                size: A4;
                
            }
            @media print {
                body {
                    content:url(../../resources/images/letter-head.png);
                }
            }*/


#header, #footer {
     display: none;
}
@media print
{
    #header, #footer {
         position: fixed;
         display: block;
         top: 0;
    }
    #footer {
         bottom: 0;
    }
}
        </style>
    </head>
    <body>
        
        <div id="header" style="width: 100%;height: 100px;background-color: #080;">
            
        </div>
        
        <div id="footer" style="width: 100%;height: 100px;background-color: #080;">
            
        </div>
    </body>
</html>

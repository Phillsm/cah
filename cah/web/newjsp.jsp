<%-- 
    Document   : newjsp
    Created on : Nov 27, 2014, 1:24:50 PM
    Author     : Fisk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Player: </h1><br />
        Player: ${requestScope.player.userName}<br />
        password : ${requestScope.player.password}<br />
    </body>
</html>

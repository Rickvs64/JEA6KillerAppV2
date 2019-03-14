<%@ page import="Domains.Account" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rickv
  Date: 12-3-2019
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    for (Account a : accounts) {
        out.print("<br/>" + a.getId() + " " + a.getName());
    }
%>
</body>
</html>

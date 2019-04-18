<%--
  Created by IntelliJ IDEA.
  User: rickv
  Date: 18-4-2019
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Chat page</h1>

    <table>
        <tr>
            <td colspan="2">
                <input type="text" id="username" placeholder="Username"/>
                <button id="connect" type="button" onclick="connect();" >Connect</button>
            </td>
        </tr>
        <tr>
            <td>
                <textarea readonly="true" rows="10" cols="80" id="log"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" size="50" id="msg" placeholder="Message"/>
                <button id="send" type="button" disabled onclick="send();" >Send</button>
            </td>
        </tr>
    </table>
</body>

<script src="websocket.js"></script>
</html>

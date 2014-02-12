<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<g:form name="messageForm" controller="JMS" action="sendMessage">
    <input type="text" name="messageText"/>
    <g:actionSubmit value="Submit" action="sendMessage"/>
</g:form>
</body>
</html>
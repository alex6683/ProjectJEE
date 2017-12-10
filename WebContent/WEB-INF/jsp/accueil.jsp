<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 08/12/17
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<html>
<head><title>Hello :: Spring Application</title></head>
<body>
<h1>Hello - Spring Application</h1>
<p>Greetings, it is now <c:out value="${now}" default="None" /></p>
<p>Greetings, it is now <c:out value="${now1}" default="None" /></p>
<p>Greetings, it is now <c:out value="${now2}" default="None" /></p>
</body>
</html>

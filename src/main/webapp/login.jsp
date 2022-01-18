<%--
  Created by IntelliJ IDEA.
  User: svitk
  Date: 16.01.2022
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <input type="text" name="name">
    <input type="password" name="password">
    <input type="submit">
</form>
<p>${error}</p>
</body>
</html>

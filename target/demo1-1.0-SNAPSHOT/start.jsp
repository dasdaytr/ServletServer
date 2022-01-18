<%--
  Created by IntelliJ IDEA.
  User: svitk
  Date: 16.01.2022
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 15px;
        }
    </style>
</head>
<body>
<a href="/logout">Выйти</a>
<div>
    ${active}
</div>
<div>
    ${role}
</div>
<table>
    <tr><th>Название</th><th>Расширение</th><th>Действие</th></tr>
    <c:forEach items="${files}" var="item">
        <tr>
            <td>${item.getName()}</td>
            <td>${item.getExtension()}</td>
            <td>
                <a href="/download?filename=${item.getNameUnique()}">скачать</a>

            </td>

        </tr>
    </c:forEach>

</table>
</body>
</html>

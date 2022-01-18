<%--
  Created by IntelliJ IDEA.
  User: svitk
  Date: 17.01.2022
  Time: 23:54
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
<p>${role}  </p>
<p>${active}</p>
    <table>
        <tr><th>Имя</th><th>Роль</th><th>Дейсвтие</th></tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.getName()}</td>
                <td>
                    <select>
                        <c:forEach items="${roles}" var="role">
                            <option>${role}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <form action="/users/roles" method="post">
                        <input type="hidden" name="name" value="${user.getName()}">
                        <input type="hidden" name="password" value="${user.getPassword()}">
                        <input type="submit" value="сделать admin">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

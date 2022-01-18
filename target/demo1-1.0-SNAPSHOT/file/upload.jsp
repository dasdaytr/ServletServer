<%--
  Created by IntelliJ IDEA.
  User: svitk
  Date: 17.01.2022
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/file/upload" enctype="multipart/form-data">
    Choose a file: <input type="file" name="multiPartServlet" />
    <input type="submit" value="Upload" />
</form>

</body>
</html>

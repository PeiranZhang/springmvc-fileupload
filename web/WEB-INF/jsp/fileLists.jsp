<%--
  Created by IntelliJ IDEA.
  User: zhangpeiran
  Date: 2019/7/11
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>File List</title>
</head>
<body>
<table>
    <tr>
        <td>Name:</td>
        <td>${name}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${param.email}</td>
    </tr>
    <c:forEach items="${files}" var="file">
        <tr>
            <td>OriginalFileName:</td>
            <td>${file.originalFilename}</td>
        </tr>
        <tr>
            <td>Type:</td>
            <td>${file.contentType}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

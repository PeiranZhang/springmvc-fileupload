<%--
  Created by IntelliJ IDEA.
  User: zhangpeiran
  Date: 2019/7/11
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<!--需要指定enctype="multipart/form-data"，才能使用文件上传功能-->
<form action="save_file" method="post" enctype="multipart/form-data" >
    <table>
        <tr>
            <td>Select a file to upload</td>
            <td><input type="file" name="files" /></td>
        </tr>
        <tr>
            <td>Select a file to upload</td>
            <td><input type="file" name="files" /></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>

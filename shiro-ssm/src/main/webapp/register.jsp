<%--
  Created by IntelliJ IDEA.
  User: evancjj
  Date: 2018/8/5
  Time: 下午9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>注册页</title>
</head>
<body>
    <form action="/user/register" method="post">
        <input type="text" name="username" value="" placeholder="请输入用户名"><br>
        <input type="password" name="password" value="" placeholder="请输入密码"><br>
        <input type="submit" value="注册">
    </form>
</body>
</html>

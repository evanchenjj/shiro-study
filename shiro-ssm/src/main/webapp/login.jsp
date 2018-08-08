<%--
  Created by IntelliJ IDEA.
  User: evancjj
  Date: 2018/8/5
  Time: 下午8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>登录页</title>
</head>
<body>

    <form action="/user/subLogin" method="post">
        用户名:<input type="text" name="username" value=""/><br>
        密码:<input type="password" name="password" value=""/><br>
        <input type="checkbox" name="rememberMe">记住我<br>
        <input type="submit" value="登录">

    </form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/11/10
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<form name="f1" id="f1" action="login" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="bname" id="login"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="bpsd" id="password"></td>
        </tr>
        <tr>
            <td>验证码:</td>
            <td><input type="text" name="YZM"><img
                    src="/getVerify"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: WuQinglong
  Date: 2017/3/10
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 100px;">

<div class="ui grid">

    <div class="row">
        <div class="three wide column"></div>
        <div class="ten wide column">
            <div style="text-align: center; font-size: large;">
                <c:if test="${errorMsg != null}">
                    <span style="color: red;">${errorMsg}</span>
                </c:if>
                <c:if test="${errorMsg == null}">
                    账户激活成功, <a href="/user/login/login.action" class="ui button">立即登录</a>
                </c:if>
            </div>
        </div>
        <div class="three wide column"></div>
    </div>

</div>
</body>
</html>

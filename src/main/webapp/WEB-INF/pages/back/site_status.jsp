<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body>

<div class="ui card" style="width: 98%;">
    <div class="content">
        <div class="header">网站状态</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <tbody>

            <tr>
                <td colspan="6" style="text-align: center;"><h3>操作系统状态</h3></td>
            </tr>
            <tr>
                <td class="active">操作系统</td>
                <td>${osName}</td>
                <td class="active">JDK Version</td>
                <td>${javaVersion}</td>
                <td class="active">数据库</td>
                <td>${MySQLVersion}</td>
            </tr>

            <tr>
                <td colspan="6" style="text-align: center;"><h3>网站系统状态</h3></td>
            </tr>
            <tr>
                <td class="active">用户总数</td>
                <td>${userNum}</td>
                <td class="active">昨天注册的用户数</td>
                <td>${yesterdayUserNum}</td>
                <td class="active">当前在线人数</td>
                <td>${currentUserNum}</td>
            </tr>
            <tr>
                <td class="active">问题总数</td>
                <td>${questionNum}</td>
                <td class="active">昨日问题数量</td>
                <td>${yesterdayQuestionNum}</td>
                <td class="active">昨日头条数量</td>
                <td>${yesterdayHeadlineNum}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

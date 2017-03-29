<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作结果</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 5px;background-color: #fafafa;">
<div class="ui grid">

    <%@ include file="commons/header.jsp"%>

    <div class="row" style="padding: 0px;background-color: white;">
        <div class="sixteen wide column">
            <div class="ui divider" style="margin: 0px auto;"></div>
        </div>
    </div>

    <div class="row" style="margin-top: 200px;">

        <div class="five wide column"></div>
        <div class="six wide column">
            <div class="ui attached message" style="text-align: center;">
                <h1>${message}</h1>
            </div>
        </div>
        <div class="five wide column"></div>
    </div>

</div>
</body>
</html>
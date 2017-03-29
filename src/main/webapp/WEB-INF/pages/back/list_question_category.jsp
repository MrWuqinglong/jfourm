<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="header">查看问题分类</div>
    </div>
    <div class="content">
        <table class="ui table">
            <thead>
            <tr>
                <th>序号</th>
                <th>分类名称</th>
                <th>问题描述</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questionCategories}" var="item" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

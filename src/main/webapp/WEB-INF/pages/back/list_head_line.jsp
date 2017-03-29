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
        <div class="header">未审核头条</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <thead>
            <tr>
                <th>标题</th>
                <th>作者</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${headLines}" var="item">
                <tr>
                    <td><a href="${item.url}">${item.title}</a></td>
                    <td>${item.user.nickName}</td>
                    <td><fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>
                        <a class="ui label review" target="/headLine/reviewPassed.action?headLineId=${item.id}">通过</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {

        /* 审核头条 */
        $(".ui.label.review").click(function () {
            var $this = $(this);
            var target = $this.attr("target");
            $.post(target, function () {
                alert("审核成功.");
                $this.parent().parent().hide();
            });
            return false;
        });

    });
</script>
</body>
</html>
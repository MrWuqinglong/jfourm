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
        <div class="header">用户列表</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <thead>
            <tr>
                <th>昵称</th>
                <th>邮箱</th>
                <th>问题数</th>
                <th>回答数</th>
                <th>头条数</th>
                <th>积分</th>
                <th>注册时间</th>
                <th>账号状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userLogins}" var="item">
                <tr>
                    <td>${item.user.nickName}</td>
                    <td>${item.user.email}</td>
                    <td>${item.user.comments.size()}</td>
                    <td>${item.user.askedQuestions.size()}</td>
                    <td>${item.user.headLines.size()}</td>
                    <td>${item.user.score}</td>
                    <td><fmt:formatDate value="${item.user.registerTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>${item.enabled==1?'启用':'禁用'}</td>
                    <td>
                        <c:if test="${item.enabled == 1}">
                            <a class="ui label operate" target="/user/closeUser.action?id=${item.id}">禁用用户</a>
                        </c:if>
                        <c:if test="${item.enabled == 0}">
                            <a class="ui label operate" target="/user/openUser.action?id=${item.id}">启用用户</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {

        // 启用用户
        $(".ui.label.operate").click(function () {
            var $this = $(this);
            var target = $this.attr("target");
            $.post(target, function () {
                alert("操作成功.");
                window.location = "/admin/back_home.action";
            });
            return false;
        });


    });
</script>
</body>
</html>

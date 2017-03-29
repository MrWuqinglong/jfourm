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

<div class="ui card" style="width: 100%;">

    <div class="content">
        <div class="header">我关注的问题</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <thead>
            <tr>
                <th>标题</th>
                <th>回答数</th>
                <th>提出时间</th>
                <th>问题状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.list}" var="item">
                    <tr>
                        <td><a href="">${item.title}</a></td>
                        <td><span class="ui label">${item.answerNum} 回答</span></td>
                        <td><fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd" /></td>
                        <td>${item.questionStatus == 1? "开启":"关闭"}</td>
                        <td><a class="ui button delete" target="/user/unFavoriteQuestion.action" data-value="${item.id}">取消关注</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {

        /* 关闭问题 */
        $(".ui.button.delete").click(function () {
            var $this = $(this);
            var questionId = $this.attr("data-value");
            var targetVal = $this.attr("target");
            $.ajax({
                url: targetVal,
                type: "post",
                data: {"questionId":questionId},
                success: function () {
                    alert("取消关注成功");
                    $this.parent().parent().hide();
                }
            });
        });
    });
</script>
</body>
</html>

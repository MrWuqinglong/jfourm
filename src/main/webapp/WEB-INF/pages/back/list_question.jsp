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
        <div class="header">问题列表</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <thead>
            <tr>
                <th>标题</th>
                <th>回答数</th>
                <th>状态</th>
                <th>作者</th>
                <th>类别</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.list}" var="item">
                <tr>
                    <td>${item.title}</td>
                    <td>${item.answerNum}</td>
                    <td>${item.questionState}</td>
                    <td>${item.user.nickName}</td>
                    <td>${item.category.name}</td>
                    <td><fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>
                        <a class="ui label close" target="/question/closeQuestion.action?questionId=${item.id}">关闭问题</a>
                        <a class="ui label delete" target="/question/deleteQuestion.action?questionId=${item.id}">删除问题</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="7" style="text-align: center;">
                    <div style="text-align: center;">
                        <div class="ui buttons">
                            <c:if test="${not empty(page.totalPage)}">
                                <c:if test="${page.totalPage != 0}">
                                    <c:if test="${page.hasPrev()}">
                                        <a href="${href}?pageNo=1" style="color: #646465;">
                                            <button class="ui button">首页</button>
                                        </a>
                                        <a href="${href}?pageNo=${page.prevPageNo}" style="color: #646465;">
                                            <button class="ui button">上一页</button>
                                        </a>
                                    </c:if>
                                    <button class="ui button disabled" style="color: black;">当前第${page.pageNo}页</button>
                                    <c:if test="${page.hasNext()}">
                                        <a href="${href}?pageNo=${page.nextPageNo}" style="color: #646465;">
                                            <button class="ui button">下一页</button>
                                        </a>
                                        <a href="${href}?pageNo=${page.totalPage}" style="color: #646465;">
                                            <button class="ui button">末页</button>
                                        </a>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<script>
    $(function () {

        /* 关闭问题 */
        $(".ui.label.close").click(function () {
            var $this = $(this);
            var href = $this.attr("target");
            $.get(href, function () {
                $this.parent().parens().find("td").eq("2").html("关闭");
            });
            return false;
        });

        /* 删除问题 */
        $(".ui.label.delete").click(function () {
            var $this = $(this);
            var href = $this.attr("target");
            $.get(href, function () {
                $this.parent().parens().hide();
            });
            return false;
        });

    });
</script>
</body>
</html>

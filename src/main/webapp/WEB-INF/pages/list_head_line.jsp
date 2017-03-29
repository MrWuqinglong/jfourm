<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%-- TODO 修改所有页面的 title --%>
    <title>JFourm社区</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 5px;background-color: #fafafa;">

<div class="ui grid">

    <%@ include file="commons/header.jsp" %>

    <div class="row" style="padding: 0px;background-color: white;">
        <div class="sixteen wide column">
            <div class="ui divider" style="margin: 0px auto;"></div>
        </div>
    </div>

    <div class="row" style="background-color: white;">
        <div class="three wide column"></div>
        <div class="seven wide column">

            <c:if test="${not empty(page.list)}">
                <div class="ui segment">

                    <c:forEach items="${page.list}" var="item">
                        <div class="content">
                            <div class="header">
                                <a href="${item.url}" style="color: black; font-size: large;">${item.title}</a>
                            </div>
                            <div class="description">
                                <a href="">${item.user.nickName}</a>
                                <fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd"/>
                                分享 <i class="external icon"></i><a href="${item.url}">${item.url}</a>
                            </div>
                        </div>
                        <div class="ui divider"></div>
                    </c:forEach>

                    <!-- 分页: 只能选择 首页, 上一页, 下一页, 末页 -->
                    <div style="text-align: center;">
                        <div class="ui buttons">
                            <c:if test="${page.totalPage != 0}">
                                <c:if test="${page.hasPrev()}">
                                    <a href="/headLine/listLast.action?pageNo=1" style="color: #646465;">
                                        <button class="ui button">首页</button>
                                    </a>
                                    <a href="/headLine/listLast.action?pageNo=${page.prevPageNo}" style="color: #646465;">
                                        <button class="ui button">上一页</button>
                                    </a>
                                </c:if>
                                <button class="ui button disabled" style="color: black;">当前第${page.pageNo}页</button>
                                <c:if test="${page.hasNext()}">
                                    <a href="/headLine/listLast.action?pageNo=${page.nextPageNo}" style="color: #646465;">
                                        <button class="ui button">下一页</button>
                                    </a>
                                    <a href="/headLine/listLast.action?pageNo=${page.totalPage}" style="color: #646465;">
                                        <button class="ui button">末页</button>
                                    </a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="three wide column">
            <div class="ui segment" style="text-align: center;">
                <span>今天，有什么好东西要上头条吗？</span>
                <button class="ui button fluid" id="sendHeadLine" style="margin: 10px auto;">发布头条</button>
            </div>
            <div class="ui divider"></div>
            <div class="header">发帖指南</div>
            <ul class="list">
                <li>独立思考、自由探索</li>
                <li>发布的内容和互联网、编程开发、产品设计有关</li>
                <li>标题能准确描述的内容，不要发重复的内容</li>
                <li>客观投票，确保你看过这篇内容</li>
                <li>如果你遇到了具体的编程难题，请到 问答平台 提问</li>
                <li>保持友善，禁止任何形式的广告、SEO 推广</li>
                <li>遵循 用户服务条款</li>
            </ul>
        </div>
        <div class="three wide column"></div>
        <div class="three wide column"></div>
    </div>

</div>
</body>
<script>

    $(function () {

        $("#sendHeadLine").click(function () {
            $.ajax({
                url: "/site/user/isLogin.action",
                type: "post",
                success: function (data) {
                    var result = eval("(" + data + ")");
                    if (!result.status) {
                        alert("请登录之后再发布头条");
                    } else {
                        window.location = "/headLine/addHeadLine.action";
                    }
                }
            });
        });
    });

</script>
</html>

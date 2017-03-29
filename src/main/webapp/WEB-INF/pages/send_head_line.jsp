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
            <div class="ui secondary pointing menu">
                <a class="active item">发头条</a>
            </div>
            <div class="ui segment">
                <div class="ui message">
                    <p>
                        有价值的内容 + 概括性的标题 = 一篇受欢迎的头条 <br>
                        声望<1000的用户发布需要审核，一般这个过程不会超过24小时.
                    </p>
                </div>
                <div>
                    <form action="/headLine/saveHeadline.action" method="post" class="ui form">
                        <div class="field">
                            <label>网址*</label>
                            <input type="url" name="url" placeholder="请输入网址">
                        </div>
                        <div class="field">
                            <label>连接标题*</label>
                            <input type="text" name="title" placeholder="请输入标题">
                        </div>
                        <div class="field">
                            <label>推荐语或内容描述*</label>
                            <textarea name="description" rows="5" placeholder="分享亮点, 槽点, 你的观点或内容描述"></textarea>
                        </div>
                        <button class="ui button">提交</button>
                    </form>
                </div>
            </div>
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

        var typeVal = "${type}";
        if (typeVal == "last") {
            $("#lastHeadLine").addClass("active");
        } else {
            $("#hotHeadLine").addClass("active");
        }

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

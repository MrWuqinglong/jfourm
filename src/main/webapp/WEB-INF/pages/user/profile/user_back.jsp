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
<div class="ui grid">

    <%@ include file="../../commons/header.jsp"%>
    <%@ include file="../../commons/question_categroy_menu.jsp"%>

    <div class="row" style="background-color: #f6f6f6;">
        <div class="three wide column"></div>
        <div class="two wide column">
            <img class="ui large circular image" src="/static/image/user.png" style="margin-top: 10px;">
        </div>
        <div class="four wide column">
            <div style="margin-top: 40px;font-size: xx-large;">${user.nickName}</div>
            <div style="margin-top: 20px;">${user.score==0} 积分</div>
            <div style="margin-top: 10px;">${user.currentTown}</div>
            <div style="margin-top: 10px;">${user.personUrl}</div>
        </div>
        <div class="four wide column">
            <div class="ui card" style="background-color: #eeeeee;height: 200px; width: 100%; margin: 10px auto;">
                <div class="content">
                    <div class="header">个人简介</div>
                </div>
            </div>
            <div class="content">
                ${user.personSing}
            </div>
        </div>
        <div class="three wide column"></div>
    </div>

    <div class="row">
        <div class="three wide column"></div>
        <div class="two wide column">
            <div class="ui vertical secondary menu">
                <a target="/user/profile.action" class="active item">我的档案 </a>
                <a target="/question/listMyQuestion.action" class="item">我的提问</a>
                <a target="/comment/listMyComment.action" class="item">我的评论</a>
                <%--<a target="" class="item">我的消息</a>--%>
                <a target="/user/listAttentionQuestions.action" class="item">关注的问题</a>
            </div>
        </div>
        <div class="eight wide column">
            <div id="frame"></div>
        </div>
        <div class="three wide column"></div>
    </div>

</div>

</body>
<script>
    $(function () {

        /* 我的档案 */
        var target =  $(".ui.vertical.secondary.menu a").eq("0").attr("target");
        $.get(target, function (data) {
            $("#frame").html(data);
        });

        /* 菜单 */
        $(".ui.vertical.secondary.menu a").click(function () {
            var $this = $(this);
            var href = $this.attr("target");
            $.get(href, function(data){
                $("#frame").html(data);
            });

        });

    });
</script>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 10px;">
<div class="ui grid">

    <%-- 标题 --%>
    <div class="row" style="background-color: #ffffff">
        <div class="sixteen wide column">
            <div class="ui secondary menu">
                <div class="item"><h1>JFourm 后台管理系统</h1></div>
                <div class="right secondary menu">
                    <div class="item">${admin.userName}</div>
                    <a class="item">LogOut</a>
                </div>
            </div>
        </div>
    </div>
    <div class="ui divider" style="margin: 0px auto;padding: 0px auto;"></div>

    <div class="row">
        <div class="two wide column">
            <div class="ui vertical menu" style="margin-left: 10px;">
                <a target="/site/siteStatus.action" class="item">
                    网站状态
                </a>
                <a target="/question/listLastBack.action" class="item">
                    查看问题
                </a>
                <a target="/user/listUsers.action" class="item">
                    查看用户
                </a>
                <a target="/headLine/listUnReview.action" class="item">
                    审核头条
                </a>
                <a target="/questionCategory/listAllCategory.action" class="item">
                    查看问题分类
                </a>
                <a target="/questionCategory/addCategory.action" class="item">
                    添加问题分类
                </a>
                <%--<a class="item">
                    查看头条分类
                </a>
                <a class="item">
                    添加头条分类
                </a>--%>
            </div>
        </div>
        <div class="fourteen wide column">
            <div id="frame">
                <div class="ui card" style="width: 98%;">
                    <div class="content" style="text-align: center;">
                        <h1>欢迎使用 JFourm 后台管理系统</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<script>
    $(function () {

        /* 菜单 */
        $(".ui.vertical.menu a").click(function () {
            var $this = $(this);
            var href = $this.attr("target");
            $.get(href, function(data){
                $("#frame").html(data);
            });
        });

    });
</script>
</body>
</html>

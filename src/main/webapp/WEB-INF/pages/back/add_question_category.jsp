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
        <div class="header">添加问题分类</div>
    </div>
    <div class="content">

        <form action="/questionCategory/doAddCategory.action" method="post" class="ui form">
            <div class="field">
                <label>问题分类名称</label>
                <input type="text" name="name" placeholder="问题分类名" />
            </div>
            <div class="field">
                <label>问题描述</label>
                <input type="text" name="description" placeholder="问题描述" />
            </div>
            <button class="ui button" type="submit">保存</button>
            <span id="operateResult" style="font-size: large;color: #1a531b;"></span>
        </form>
    </div>

    <a href="/q"></a>
    
</div>

</body>
<script>
    $(function () {

        $("form").submit(function () {
            var target = $(this).attr("action");
            var nameVal = $(":input[name='name']").val();
            var descriptionVal = $(":input[name='description']").val();
            var data = {"name":nameVal, "description": descriptionVal};
            $.post(target, data, function () {
                alert("保存成功.");
                $("#operateResult").html("保存成功");
            });
        });

    });
</script>
</html>

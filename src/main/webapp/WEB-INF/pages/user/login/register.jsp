<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body>
<div class="ui grid">

    <%@ include file="../../commons/header.jsp"%>
    <%@ include file="../../commons/question_categroy_menu.jsp"%>

    <div class="row" style="margin-top: 50px;">

        <div class="four wide column"></div>
        <div class="two wide column"></div>
        <div class="three wide column">
            <div class="ui attached message">
                <div style="text-align: center;font-size: large;margin-bottom: 30px;">
                    注册新账号
                </div>
                <c:if test="${errorMsg != null}">
                    <div class="ui red warning message">
                            ${errorMsg}
                    </div>
                </c:if>
                <form action="/user/login/doRegister.action" method="post" class="ui form" >
                    <div class="field">
                        <label>真实姓名或昵称</label>
                        <input type="text" placeholder="Name Or NickName" name="user.nickName" required="required">
                    </div>
                    <div class="field">
                        <label>邮箱</label>
                        <input type="email" placeholder="Email" name="email" required="required">
                    </div>
                    <div class="field">
                        <label>密码</label>
                        <input type="password" placeholder="Password" name="password" required="required" minlength="6">
                    </div>
                    <div class="inline field">
                        <div class="ui toggle checkbox">
                            <input type="checkbox" class="hidden">
                            <label>同意相关协议</label>
                        </div>
                    </div>
                    <button class="ui primary button fluid" type="submit">注册</button>
                </form>
            </div>
        </div>
        <div class="three wide column"></div>
        <div class="four wide column"></div>
    </div>

</div>
</body>
<script>
    $(function () {

        $('.ui.checkbox')
            .checkbox()
        ;

        /* 表单提交前验证 */
        $(".ui.form").submit(function () {
            // TODO 检查用户是否同意协议
            return true;
        });


    });
</script>
</html>

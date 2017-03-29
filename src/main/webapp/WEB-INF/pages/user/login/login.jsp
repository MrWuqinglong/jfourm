<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
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
        <div class="two wide column">
        </div>
        <div class="three wide column">
            <div class="ui attached message">
                <div style="text-align: center;font-size: large;margin-bottom: 30px;">
                    登录
                </div>
                <c:if test="${errorMsg != null}">
                    <div class="ui red warning message">
                            ${errorMsg}
                    </div>
                </c:if>
                <form action="/user/login/doLogin.action" method="post" class="ui form">
                    <div class="field">
                        <label>邮箱</label>
                        <input type="email" placeholder="Email" name="email" required="required">
                    </div>
                    <div class="field">
                        <label>密码</label>
                        <input type="password" placeholder="Password" name="password" required="required">
                    </div>
                    <button class="ui primary button fluid" type="submit">登录</button>
                </form>
                <div style="text-align: center;margin-top: 5px;">
                    <%-- TODO 忘记密码怎么办? --%>
                    <span>忘记密码? <a href="javascript:alert('暂未实现该功能.')">立即找回</a></span>
                </div>
            </div>
        </div>
        <div class="three wide column"></div>
        <div class="four wide column"></div>
    </div>

</div>
</body>
</html>

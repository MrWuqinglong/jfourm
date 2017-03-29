<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!-- 导航栏 -->
<div class="row" style="padding-bottom: 0px;">
    <div class="three wide column"></div>
    <div class="ten wide column">
        <div class="ui secondary menu">
            <a class="item" href="/">
                <h1>JFourm&nbsp;&nbsp;</h1>
            </a>
            <%-- TODO 头条和问答的链接 --%>
            <a href="/" class="item" id="questionModel">
                <span style="font-size: large">问答</span>
            </a>
            <a href="/headLine/listLast.action" class="item" id="headLineModel">
                <span style="font-size: large">头条</span>
            </a>

            <div class="right menu">
                <c:if test="${empty(sessionScope.user)}">
                    <div class="item">
                        <div class="ui medium buttons">
                            <a href="/user/login/register.action" class="ui button">注册</a>
                            <div class="or"></div>
                            <a href="/user/login/login.action" class="ui button">登录</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty(sessionScope.user)}">
                    <div class="item">
                        <div class="ui medium buttons">
                            <a href="/question/askQuestion.action" class="ui button" id="headAskQuestion">提问题</a>
                            <div class="or"></div>
                            <a href="/headLine/addHeadLine.action" class="ui button" id="headSendHeadLine">发头条</a>
                        </div>
                    </div>
                    <div class="item">
                        <img class="ui avatar image" src="/static/image/user.png">
                        <a href="/user/user_back.action">${sessionScope.user.nickName}</a>
                        <a class="ui label" href="/user/logout.action">退出</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="three wide column"></div>
</div>

<script>
    $(function () {

        /* 提示现在在那个模块 */
        var modelType = "${modelType}";
        if (modelType == "questionModel") {
            $("#questionModel").addClass("active");
        } else {
            $("#headLineModel").addClass("active");
        }

        /* 点击提问题的时候，验证用户登录 */
        $("#headAskQuestion").click(function () {
            $.ajax({
                url: "/site/user/isLogin.action",
                type: "post",
                success: function (data) {
                    var result = eval("(" + data + ")");
                    if (!result.status) {
                        alert("请登录之后再提问");
                    } else {
                        window.location = "/question/askQuestion.action";
                    }
                }
            });
        });

        /* 点击提头条的时候，验证用户登录 */
        $("#headSendHeadLine").click(function () {
            $.ajax({
                url: "/site/user/isLogin.action",
                type: "post",
                success: function (data) {
                    var result = eval("(" + data + ")");
                    if (!result.status) {
                        alert("请登录之后再发布");
                    } else {
                        window.location = "/headLine/addHeadLine.action";
                    }
                }
            });
        });

    })
</script>
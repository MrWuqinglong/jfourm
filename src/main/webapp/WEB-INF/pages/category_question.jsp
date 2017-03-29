<%--
  Created by IntelliJ IDEA.
  User: WuQinglong
  Date: 2017/3/10
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JFourm社区</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
    <script src="/static/laypage/laypage.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 5px;">

<div class="ui grid">

    <%@ include file="commons/header.jsp"%>

    <div class="row">
        <div class="three wide column"></div>
        <div class="seven wide column">

            <div class="ui secondary pointing menu">
                <a href="javascript:void(0);" class="active item">问题详情</a>
            </div>
            <div class="ui secondary pointing menu">
                <%-- TODO 查看某一类的问题 --%>
                <a href="javascript:void(0);" class="active item">#{questionCategory.name}</a>
            </div>

            <!-- 问题列表 -->
            <c:forEach items="${requestScope.page.list}" var="item">
                <section>
                    <!-- 回答数 -->
                    <div style="width: 40px; height: 45px;text-align: center;background-color: #ff8171; color: white;float: left;margin: 5px 5px;">
                            ${item.answerNum} <br>
                        <small>回答</small>
                    </div>
                    <!-- 浏览量 -->
                    <div style="width: 40px; height: 45px;text-align: center;float: left;margin: 5px 5px;">
                            ${item.viewNum} <br>
                        <small>浏览</small>
                    </div>
                    <!-- 主要内容 -->
                    <div>
                        <!-- 提问者/回答者 -->
                        <span>${item.user.nickName}&nbsp;&nbsp;1分钟前回答</span>
                        <h5 style="margin: 10px 0px;"></h5>
                        <!-- 提问或者回答的问题 -->
                        <a href="" style="color: #0f0f10; font-size: large;">
                                ${item.title}
                        </a>
                        <!-- 问题类别 -->
                        <label class="ui label">
                            <a href="" style="color: #0f0f10">${item.category.name}</a>
                        </label>
                    </div>
                </section>
                <div class="ui divider"></div>
            </c:forEach>

            <!-- 分页: 只能选择 首页, 上一页, 下一页, 末页 -->
            <div id="pagination"></div>

        </div>

        <%-- 从 Session 中取值 --%>
        <div class="three wide column">
            <!-- 最新头条 -->
            <div class="ui attached message" style="margin-top: 20px;">
                <div class="header" style="text-align: center;">
                    最新头条
                </div>
            </div>
            <div class="ui attached fluid segment">
                <c:forEach items="${headLines}" var="item">
                    <div style="margin-bottom: 5px;">
                        <a href="${item.url}">${item.title}</a>
                    </div>
                    <div class="ui divider"></div>
                </c:forEach>
            </div>

        </div>
        <div class="three wide column"></div>
    </div>

</div>
</body>
<script>

    /* 分页 */
    laypage({
        cont: 'pagination',
        pages: ${requestScope.page.totalPage}, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是10
        curr: function () { //通过url获取当前页，也可以同上（pages）方式获取
            var page = location.search.match(/page=(\d+)/);
            return page ? page[1] : 1;
        }(),
        groups: 3, //连续显示分页数
        jump: function (e, first) { //触发分页后的回调
            if (!first) { //一定要加此判断，否则初始时会无限刷新
                location.href = '${requestScope.href}?id=${questionCategory.id}&pageNo=' + e.curr;
            }
        }
    });

</script>
</html>

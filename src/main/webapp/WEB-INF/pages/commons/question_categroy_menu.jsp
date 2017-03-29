<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!-- 问题分类栏 -->
<div class="brown row" style="padding: 0px;">
    <div class="three wide column"></div>
    <div class="ten wide column">
        <div class="ui secondary menu" style="height: 34px;">
            <a href="/" class="item" style="padding: 10px 5px; color: pink">
                <i class="home icon"></i>
                Home
            </a>
            <span style="padding: 10px 5px; color: pink">|</span>

            <c:forEach items="${sessionScope.questionCategories}" var="item">
                <a href="/question/listLastCategoryQuestion.action?categoryId=${item.id}" class="item" style="padding: 10px 5px; color: pink">
                    ${item.name}
                </a>
            </c:forEach>

            <%-- 查看更多问题分类 --%>
            <div class="right menu">
                <a href="/questionCategory/listAll.action" class="item" style="padding: 10px 5px; color: pink">定制我的分类</a>
            </div>

        </div>
    </div>
    <div class="three wide column"></div>
</div>
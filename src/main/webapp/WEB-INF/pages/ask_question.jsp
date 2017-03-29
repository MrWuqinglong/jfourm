<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <link rel="stylesheet" href="/static/editormd/css/editormd.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
    <script src="/static/editormd/editormd.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 5px;background-color: #fafafa;">
<div class="ui grid">

    <%@ include file="commons/header.jsp"%>

    <div class="row">
        <div class="three wide column"></div>
        <div class="ten wide column">

            <div class="ui card" style="width: 100%;">
                <div class="content">
                    <div class="header">提问题</div>
                </div>
                <div class="content">
                    <div>
                        <form action="/question/saveQuestion.action" method="post" class="ui form">
                            <div class="field">
                                <input type="text" id="title" placeholder="标题: 一句话说清楚问题, 用问号结尾" required="required">
                            </div>
                            <div id="editormd">
                                <textarea style="display:none;"></textarea>
                            </div>
                            <div class="field">
                                <select class="ui dropdown" id="category">
                                    <option value="">请选择分类</option>
                                    <c:forEach items="${questionCategories}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="extra content">
                                <button class="ui button">提交</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="three wide column"></div>
    </div>

</div>
</body>
<script>
    $(function () {

        $('.ui.dropdown')
            .dropdown()
        ;

        /* MarkDown 编辑器 */
        var testEditor;
        $(function () {
            testEditor = editormd({
                id: "editormd",
                width: "100%",
                height: 640,
                watch: false,
                path: "../../static/editormd/lib/",
                saveHTMLToTextarea : true,
                placeholder: "详细描述问题内容",
                toolbarIcons: function () {
                    return [
                        "undo", "redo", "|",
                        "bold", "del", "italic", "quote", "uppercase", "lowercase", "|",
                        "h1", "h2", "h3", "h4", "h5", "h6", "|",
                        "list-ul", "list-ol", "hr", "|",
                        "watch", "fullscreen"
                    ]
                }
            });
        });

        /* 表单提交 */
        $("form").submit(function () {
            var hrefVal = $(this).attr("action");
            var titleVal = $("#title").val();
            var contentVal = testEditor.getHTML();
            var categoryVal = $("#category").val();
            var data = {"title": titleVal, "content": contentVal, "category.id": categoryVal};
            if (categoryVal == "") {
                alert("请选择分类");
                return false;
            }
            $.post(hrefVal, data, function () {
                window.location = "/site/operationResult.action";
            });
            return false;
        });
        
    });
</script>
</html>
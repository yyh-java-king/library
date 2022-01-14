<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>图书详情</title>
    <%--静态包含用户的背景图片设置--%>
    <c:if test="${sessionScope.user.status == 0}">
        <%@ include file="../common/user_background.jsp" %>
    </c:if>
    <%--静态包含管理员的背景图片设置--%>
    <c:if test="${sessionScope.user.status == 1}">
        <%@ include file="../common/admin_background.jsp" %>
    </c:if>
    <style>
        .title {
            background-color: #357bb8;
            font-weight: bold;
            font-size: 20px;
            color: white;
        }

        .introduce {
            margin: 10px;
            padding-top: 15px;
        }
    </style>
</head>
<body>
<%--静态包含用户导航条--%>
<c:if test="${sessionScope.user.status == 0}">
    <%@ include file="../common/user_navigation.jsp" %>
</c:if>
<%--静态包含管理员导航条--%>
<c:if test="${sessionScope.user.status == 1}">
    <%@ include file="../common/admin_navigation.jsp" %>
</c:if>
<div style="height: 30px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                《大雪中的山庄》
            </div>
            <div class="introduce">
                <table class="table table-hover">
                    <tr>
                        <td><b>书名</b></td>
                        <td>${requestScope.book.name}</td>
                    </tr>
                    <tr>
                        <td><b>作者</b></td>
                        <td>${requestScope.book.author}</td>
                    </tr>
                    <tr>
                        <td><b>出版社</b></td>
                        <td>${requestScope.book.publish}</td>
                    </tr>
                    <tr>
                        <td><b>ISBN</b></td>
                        <td>${requestScope.book.ISBN}</td>
                    </tr>
                    <tr>
                        <td><b>简介</b></td>
                        <td>${requestScope.book.intro}</td>
                    </tr>
                    <tr>
                        <td><b>语言</b></td>
                        <td>${requestScope.book.language}</td>
                    </tr>
                    <tr>
                        <td><b>价格</b></td>
                        <td>${requestScope.book.price}</td>
                    </tr>
                    <tr>
                        <td><b>出版日期</b></td>
                        <td>${requestScope.book.pub_date}</td>
                    </tr>
                    <tr>
                        <td><b>图书类型</b></td>
                        <td>${requestScope.book.type}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<br><br><br><br><br><br><br>
<script>
    $(function () {
        /*设置表格第一个单元格的宽度*/
        $("tr td:first-child")[0].width = 100;
    })
</script>
</body>
</html>

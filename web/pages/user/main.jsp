<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <%--如果是普通用户--%>
    <c:if test="${sessionScope.user.status == 0}">
        <title>用户主页</title>
        <style>
            body {
                /*设置用户界面的背景图片并全屏显示*/
                background-image: url('static/img/user_main.jpg');
                background-size: 100% 100%;
                background-attachment: fixed;
                background-repeat: no-repeat;
            }
        </style>
    </c:if>
        <%--如果该用户是管理员--%>
    <c:if test="${sessionScope.user.status == 1}">
        <title>管理员主页</title>
        <style>
            body {
                /*设置用户界面的背景图片并全屏显示*/
                background-image: url('static/img/book2.jpg');
                background-size: 100% 100%;
                background-attachment: fixed;
                background-repeat: no-repeat;
            }
        </style>
    </c:if>

</head>
<body>
<%--如果是普通用户--%>
<c:if test="${sessionScope.user.status == 0}">
    <%--使用静态包含,包含用户界面的导航条--%>
    <%@ include file="../common/user_navigation.jsp" %>
</c:if>
<%--如果是管理员--%>
<c:if test="${sessionScope.user.status == 1}">
    <%--使用静态包含,包含管理员界面的导航条--%>
    <%@ include file="../common/admin_navigation.jsp" %>
</c:if>
</body>
</html>

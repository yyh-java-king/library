<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>借还日志</title>
    <%--静态包含管理员界面的背景--%>
    <%@ include file="../common/admin_background.jsp" %>
        <style>
            .view {
                background-color: white;
                padding-left: 50px;
                padding-top: 10px;
                padding-right: 50px;
            }

            thead {
                font-weight: bold;
            }
        </style>
</head>
<body>
<%--静态包含管理员的导航条--%>
<%@ include file="../common/admin_navigation.jsp" %>
<div style="height: 20px"></div>
<div class="container view">
    <div style="border-bottom: gainsboro 1px solid" class="text-center">
        <font face="宋体" size="7">
            借还日志
        </font>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>读者号</td>
            <td>图书号</td>
            <td>借出日期</td>
            <td>归还日期</td>
            <td>状态</td>
            <td>删除</td>
        </tr>
        </thead>
        <c:forEach items="${requestScope.lends}" var="lend">
        <tr>
            <input type="hidden" value="${lend.id}">
            <td>${lend.user_id}</td>
            <td>${lend.book_id}</td>
            <td>${lend.lend_date}</td>
            <td>${lend.back_date}</td>
            <td>${empty lend.back_date ? "未还" : "已还"}</td>
            <td>
                <button class="btn btn-danger">删除</button>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<script>
    $(function () {
        /*删除用户时提示*/
        $(".btn-danger").click(function () {
            if (confirm("是否删除该条记录?")){
                location.href = "lendServlet?action=deleteLend&id=" + $(this).parent().parent().find("input:first").val();
            }else {
                return false;
            }
        })
    })
</script>
</body>
</html>

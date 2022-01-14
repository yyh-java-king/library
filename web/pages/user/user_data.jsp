<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>读者信息</title>
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
            全部读者
        </font>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>读者号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>生日</td>
            <td>地址</td>
            <td>电话</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        </thead>
        <%int i = 0;%>
        <c:forEach items="${requestScope.userInfos}" var="userInfo">
            <tr>
                <input type="hidden" name="id" value="${userInfo.id}">
                <c:set scope="page" var="serial" value="<%=i++%>"/>
                <td>${requestScope.users[pageScope.serial].username}</td>
                <td>${userInfo.name}</td>
                <td>${userInfo.sex}</td>
                <td>${userInfo.birthday}</td>
                <td>${userInfo.address}</td>
                <td>${userInfo.phone}</td>
                <td>
                    <button class="btn btn-info">编辑</button>
                </td>
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
            if(confirm("是否删除[" + $(this).parent().parent().find("td:eq(1)").text() + "]?")){
                /*如果用户确认删除即删除该用户*/
                location.href = "userServlet?action=deleteUser&id=" + $(this).parent().parent().find("input:first").val();
            }else {
                /*如果用户取消删除则返回false*/
                return false;
            }
        })

        /*点击编辑按钮,跳转到编辑读者信息界面*/
        $(".btn-info").click(function () {
            location.href = "userServlet?action=getUser&id=" + $(this).parent().parent().find("input:first").val();
        })
    })
</script>
</body>
</html>

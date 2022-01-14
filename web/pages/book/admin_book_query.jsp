<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>全部图书</title>
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
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5 col-sm-offset-5">
            <form class="navbar-form navbar-left " action="bookServlet" role="search" align="center">
                <div class="form-group">
                    <input type="hidden" name="action" value="dimListBooks">
                    <input class="form-control " placeholder="请输入图书名" id="search" name="name">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
        </div>
    </div>
</div>
<div style="height: 50px"></div>
<div class="container view">
    <div style="border-bottom: gainsboro 1px solid" class="text-center">
        <font face="宋体" size="7">
            全部图书
        </font>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>书名</td>
            <td>作者</td>
            <td>出版社</td>
            <td>ISBN</td>
            <td>价格</td>
            <td>数量</td>
            <td>详情</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        </thead>
        <c:forEach items="${requestScope.books}" var="book">
            <tr>
                <input type="hidden" value="${book.id}">
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.publish}</td>
                <td>${book.ISBN}</td>
                <td>${book.price}</td>
                <td>${book.number}</td>
                <td>
                    <button class="btn btn-success">详情</button>
                </td>
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
        /*点击详情按钮跳转到图书详情页面*/
        $(".btn-success").click(function () {
            location.href = "bookServlet?action=getBook&id=" + $(this).parent().parent().find("input:first").val()
        })

        /*点击编辑页面,跳转到图书的编辑页面*/
        $(".btn-info").click(function () {
            location.href = "bookServlet?action=getBook&method=update&id=" + $(this).parent().parent().find("input:first").val();
        })

        /*设置删除按钮的提示框*/
        $(".btn-danger").click(function () {
            /*判断用户是否删除*/
            if (confirm("是否删除[" + $(this).parent().parent().find("td:first").text() + "]?")) {
                /*确定删除则请求服务器*/
                location.href = "bookServlet?action=deleteBook&id=" + $(this).parent().parent().find("input:first").val();
            } else {
                return false;
            }
        })
    })
</script>
</body>
</html>

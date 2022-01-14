<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>借还记录</title>
    <%--静态包含用户的背景图片设置--%>
    <%@ include file="../common/user_background.jsp" %>
    <style>
        .container {
            background-color: white;
        }

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

        thead {
            font-weight: bold;
        }
    </style>
</head>
<body>
<%--静态包含用户导航条--%>
<%@ include file="../common/user_navigation.jsp" %>
<div style="height: 70px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-12 " style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                我的借还日志
            </div>
        </div>
    </div>
    <div class="introduce">
        <table class="table table-hover">
            <thead>
            <tr>
                <td>图书号</td>
                <td>借出日期</td>
                <td>归还日期</td>
                <td>状态</td>
            </tr>
            </thead>
            <c:forEach items="${requestScope.lends}" var="lend">
                <tr>
                    <td>${lend.book_id}</td>
                    <td class="lend_date">${lend.lend_date}</td>
                    <td class="back_date">${lend.back_date}</td>
                    <td>${empty lend.back_date ? "未还" : "已还"}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    $(function () {
        var lend_date = $(".lend_date");
        //lend_date.text(/\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}/.exec(lend_date.text()))
        var back_date = $(".back_date");
        //back_date.text(/\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}/.exec(back_date.text()))
    })
</script>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>图书查询</title>
    <%--静态包含用户的背景图片设置--%>
    <%@ include file="../common/user_background.jsp" %>
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

<%--静态包含用户导航条--%>
<%@ include file="../common/user_navigation.jsp" %>
<div style="height: 20px"></div>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5 col-sm-offset-5">
            <form class="navbar-form navbar-left " action="bookServlet" role="search" align="center">
                <div class="form-group">
                    <input type="hidden" name="action" value="dimListBooks">
                    <input class="form-control" placeholder="请输入图书名" id="search" name="name">
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
            <td>剩余数量</td>
            <td>借还</td>
            <td>详情</td>
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
                    <%--下面这段最好不要再动了!!!--%>
                    <c:choose>
                        <%--判断该用户有没有未还记录--%>
                        <c:when test="${empty requestScope.unpaid}">
                            <%--如果没有执行下面语句--%>
                            <c:choose>
                                <%--当图书数量为零时,按钮为已空状态,不可点--%>
                                <c:when test="${book.number == 0}">
                                    <button class="btn btn-default" disabled="disabled">已空</button>
                                </c:when>
                                <%--其他情况就为可借阅状态--%>
                                <c:otherwise>
                                    <button class="btn btn-primary borrow">借阅</button>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <%--当用户有未归还图书记录时--%>
                        <c:otherwise>
                            <%--用来退出循环的变量--%>
                            <c:set var="flag" value="true"/>
                            <%--循环未归还图书的id--%>
                            <c:forEach items="${requestScope.unpaid}" var="bookId">
                                <%--当flag为false时不执行循环体--%>
                                <c:if test="${flag}">
                                    <c:choose>
                                        <%--当找到这本书的id与未归还的图书id相等即将按钮改为归还状态,并不执行该语句了--%>
                                        <c:when test="${bookId.equals(book.id)}">
                                            <button class="btn btn-danger borrow">归还</button>
                                            <%--当遇到需要归还的就将flag设置为false,以不执行现在的语句--%>
                                            <c:set var="flag" value="false"/>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                                <%-- <button class="btn btn-primary borrow">借阅</button>--%>
                                <%--<button class="btn btn-danger borrow">归还</button>--%>
                                <%--<button class="btn btn-default" disabled="disabled">已空</button>--%>
                            </c:forEach>
                            <%--当flag等于true说明没有该书没有被该用户借走未还--%>
                            <c:if test="${flag}">
                                <c:choose>
                                    <%--当图书数量为零时,按钮为已空状态,不可点--%>
                                    <c:when test="${book.number == 0}">
                                        <button class="btn btn-default" disabled="disabled">已空</button>
                                    </c:when>
                                    <%--其他情况就为可借阅状态--%>
                                    <c:otherwise>
                                        <button class="btn btn-primary borrow">借阅</button>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <button class="btn btn-success">详情</button>
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


        /*借阅按钮的单击事件*/
        $(".borrow").click(function () {
            var bool = /btn-primary/.test($(this).attr("class"));
            /*图书数量*/
            var number = $(this).parent().parent().find("td:eq(5)");
            if (bool) {
                /*把借阅按钮变成归还按钮*/
                $(this).removeClass("btn-primary");
                $(this).addClass("btn-danger");
                $(this).text("归还");
                /*数量减一*/
                number.text(parseInt(number.text()) - 1);
                /*ajax请求进行还书*/
                $.getJSON("lendServlet", "action=borrow&bookId=" + $(this).parent().parent().find("input:first").val(), function (date) {
                })
            } else {
                /*把归还按钮变成借阅按钮*/
                $(this).removeClass("btn-danger");
                $(this).addClass("btn-primary");
                $(this).text("借阅");
                /*数量加一*/
                number.text(parseInt(number.text()) + 1);
                /*ajax请求进行借阅图书*/
                $.getJSON("lendServlet", "action=returnBook&bookId=" + $(this).parent().parent().find("input:first").val(), function (date) {
                })
            }
        })
    })
</script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>添加图书</title>
    <%--静态包含管理员界面的背景--%>
    <%@ include file="../common/admin_background.jsp" %>
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

        .error {
            color: red;
            font-size: 30px;
        }
    </style>
</head>

<body>
<%--静态包含管理员的导航条--%>
<%@ include file="../common/admin_navigation.jsp" %>
<div style="height: 20px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                <c:if test="${empty requestScope.book}">
                    添加图书
                </c:if>
                <c:if test="${not empty requestScope.book}">
                    修改《${requestScope.book.name}》
                </c:if>
            </div>
            <div class="introduce">
                <form class="form-horizontal" method="post" action="bookServlet">
                    <%--如果request中的book为空则为添加 有值则为修改--%>
                    <c:if test="${empty requestScope.book}">
                        <input type="hidden" name="action" value="addBook">
                    </c:if>
                    <c:if test="${not empty requestScope.book}">
                        <input type="hidden" name="action" value="updateBook">
                    </c:if>
                        <%--隐藏发送图书id--%>
                        <input type="hidden" name="id" value="${requestScope.book.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图书名</label>
                        <div class="col-sm-10">
                            <input class="form-control bookName" placeholder="请输入图书名" name="name"
                                   value="${requestScope.book.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input class="form-control author" placeholder="请输入作者" name="author"
                                   value="${requestScope.book.author}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">出版社</label>
                        <div class="col-sm-10">
                            <input class="form-control publish" placeholder="请输入出版社" name="publish"
                                   value="${requestScope.book.publish}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">ISBN</label>
                        <div class="col-sm-10">
                            <input class="form-control ISBN" placeholder="请输入ISBN" name="ISBN"
                                   value="${requestScope.book.ISBN}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">简介</label>
                        <div class="col-sm-10">
                            <input class="form-control intro" placeholder="请输入简介" name="intro"
                                   value="${requestScope.book.intro}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">语言</label>
                        <div class="col-sm-10">
                            <input class="form-control language" placeholder="请输入语言" name="language"
                                   value="${requestScope.book.language}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <input class="form-control price" placeholder="请输入价格" name="price"
                                   value="${requestScope.book.price}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">出版日期</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control pub_date" placeholder="请输入出版日期"
                                   name="pub_date" value="${requestScope.book.pub_date}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图书类型</label>
                        <div class="col-sm-10">
                            <input class="form-control type" placeholder="请输入图书类型"
                                   name="type" value="${requestScope.book.type}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">数量</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control number" placeholder="请输入图书数量" name="number"
                                   value="${requestScope.book.number}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="submit" class="btn btn-success">确定</button>
                        </div>
                        <div class="col-sm-6 text-right">
                            <span class="error">${requestScope.error}</span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<br><br><br><br><br><br>
<script>
    $(function () {
        /*设置表单提交事件*/
        $("form").submit(function () {
            var error = $(".error");
            var number = $(".number");
            if ($(".name").val() === '' || $(".author").val() === '' || $(".publish").val() === '' ||
                $(".ISBN").val() === '' || $(".intro").val() === '' || $(".language").val() === '' ||
                $(".price").val() === '' || $(".pub_date").val() === '' || $(".classId").val() === '' ||
                number.val() === '' || $(".type").val() === '') {
                error.text("请填入完整图书信息！");
                return false;
            } else {
                error.text("");
            }
            /*0到多个数字*/
            var regex = /^[0-9]*$/
            if (regex.test(number.val())) {
                error.text("");
                number.parent().removeClass("has-error");
            } else {
                error.text("只能输入数字!");
                number.parent().addClass("has-error");
                return false;
            }
            return true;
        })
    })
</script>
</body>
</html>

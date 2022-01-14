<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>修改密码</title>
    <%--如果是普通用户--%>
    <c:if test="${sessionScope.user.status == 0}">
        <%--静态包含用户的背景图片设置--%>
        <%@ include file="../common/user_background.jsp" %>
    </c:if>
    <%--管理员背景--%>
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

        .error {
            color: red;
            font-size: 20px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.user.status == 0}">
    <%--静态包含用户导航条--%>
    <%@ include file="../common/user_navigation.jsp" %>
</c:if>
<%--管理员导航条--%>
<c:if test="${sessionScope.user.status == 1}">
    <%--静态包含用户导航条--%>
    <%@ include file="../common/admin_navigation.jsp" %>
</c:if>
<div style="height: 70px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2 " style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                修改密码
            </div>
            <div class="introduce">
                <form action="userServlet" method="post">
                    <div>
                        <input type="password" class="form-control" placeholder="请输入旧密码">
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="请输入新密码" name="password">
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="确定新密码">
                    </div>
                    <%--隐藏域 用户指定调用哪个方法--%>
                    <input type="hidden" name="action" value="updatePassword">
                    <%--隐藏域 用户发送用修改的id--%>
                    <input type="hidden" name="id" value="${sessionScope.user.id}">
                    <div class="row">
                        <div class="col-sm-4">
                            <input type="submit" class="btn btn-default" value="提交">
                        </div>
                        <div class="col-sm-8 text-right error">
                            ${requestScope.hint}
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        var error = $(".error");
        /*表单提交事件*/
        $("form").submit(function () {
            /*三个文本框的jquery对象*/
            var obj1 = $("form input:eq(0)");
            var obj2 = $("form input:eq(1)");
            var obj3 = $("form input:eq(2)");
            /*校验标准*/
            /*旧密码不可以输入有误*/
            var judge1 = obj1.val() == ${sessionScope.user.password};
            /*新密码不可以等于旧密码*/
            var judge2 = obj2.val() != ${sessionScope.user.password};
            /*新密码只能输入6-12位*/
            var judge3 = /^\d{6,12}$/.test(obj2.val());
            /*再次输入新密码必须和新密码一致*/
            var judge4 = obj3.val() == obj2.val();

            return verify(obj1, "旧密码输入有误", judge1) &&
                verify(obj2, "新密码不能与旧密码重复", judge2) &&
                verify(obj2, "密码必须由6-12位组成", judge3) &&
                verify(obj3, "密码不一致!", judge4);
        })

        function verify(obj, hint, judge) {
            /*input标签前面的div*/
            var div = $(obj).parent();
            if (judge) {
                error.text("")
                div.removeClass("has-error");
                div.addClass("has-success");
                return true;
            } else {
                error.text(hint);
                div.addClass("has-error");
                return false;
            }
        }
    })
</script>
</html>

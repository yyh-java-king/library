<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>${empty requestScope.user.id ? "添加读者" : "修改读者"}</title>
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
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                ${empty requestScope.user.id ? "添加读者" : "修改读者"}
            </div>
            <div class="introduce">
                <form class="form-horizontal" action="userServlet" method="post">
                    <c:if test="${empty requestScope.user.id}">
                        <input type="hidden" name="action" value="addUser">
                    </c:if>
                    <c:if test="${not empty requestScope.user.id}">
                        <input type="hidden" name="action" value="updateUser">
                        <%--发送要修改的用户id--%>
                        <input type="hidden" name="id" value="${requestScope.user.id}">
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">读者证号</label>
                        <div class="col-sm-10">
                            <input class="form-control username" placeholder="请输入读者证号" name="username" value="${requestScope.user.username}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control password" placeholder="请输入密码" name="password" value="${requestScope.user.password}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input class="form-control name" placeholder="请输入姓名" name="name" value="${requestScope.userInfo.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <input class="form-control sex" placeholder="请输入性别" name="sex" value="${requestScope.userInfo.sex}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">生日</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control birthday" name="birthday"  value="${requestScope.userInfo.birthday}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input class="form-control address" placeholder="请输入地址" name="address"  value="${requestScope.userInfo.address}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input class="form-control phone" placeholder="请输入电话" name="phone"  value="${requestScope.userInfo.phone}">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="submit" class="btn btn-success">确定</button>
                        </div>
                        <div class="col-sm-6 text-right">
                            <span class="error">${requestScope.hint}</span>
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
        var error = $(".error");
        /*表单提交事件*/
        $("form").submit(function () {
            if ($(".birthday").val() === '' || $(".address").val() === '') {
                error.text("请填入完整的用户信息!");
                return false;
            }
            error.text("");
            /*规范输入*/
            return verify($(".username").val(), /^\d{6,11}$/, "读者证号必须为6到11位!") &&
                verify($(".password").val(), /^\d{6,11}$/, "密码必须为6到11位!") &&
                verify($(".sex").val(), /^[男,女]$/, "性别必须为男或女!") &&
                verify($(".phone").val(), /^\d{11}$/, "电话必须为11位!") &&
                username_ajax($(".username")[0]);
        })

        /*正则表达式校验*/
        function verify(value, regex, error_info) {
            if (!regex.test(value)) {
                /*提示*/
                error.text(error_info)
                return false;
            } else {
                error.text("")
                return true;
            }
        }

        /*用户名文本框的ajax请求*/
        $(".username").blur(function () {
            username_ajax(this);
        })
    })

    function username_ajax(object) {
        var bool = true;
        /*获取文本框对象*/
        var obj = $(object);
        /*进行AJAX请求*/
        $.getJSON("userServlet", "action=update_add_ajax&username=" + obj.val() + "&id=${requestScope.user.id}",
            function (data) {
                bool = verify2(obj,"长度必须为6到12位!",/^.{6,12}$/.test(obj.val())) &&
                    verify2(obj,"用户名已存在!",!data);//data为true : 用户名存在 false : 用户名不存在
            })

        function verify2(obj, hint, judge) {
            /*input标签前面的div*/
            var div = $(obj).parent();
            if (judge) {
                $(".error").text("")
                div.removeClass("has-error");
                div.addClass("has-success");
                return true;
            } else {
                $(".error").text(hint);
                div.addClass("has-error");
                div.removeClass("has-success");
                return false;
            }
        }
        return bool;
    }
</script>
</body>
</html>

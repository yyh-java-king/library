<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>登入</title>
    <style>
        body {
            /*设置登入界面的背景图片并全屏显示*/
            background-image: url(static/img/login_background.jpg);
            background-size: 100% 100%;
            background-attachment: fixed;
            background-repeat: no-repeat;
        }

        form {
            background-color: white;
        }

        #login {
            border-bottom: 1px solid gainsboro;
            margin: 0px;
        }

        .padding-up-down {
            padding-bottom: 5px;
            padding-top: 5px;
        }

        .padding-left-right {
            padding-left: 40px;
            padding-right: 40px;
        }

        button {
            width: 432px;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div style="height: 50px"></div>
    <div class="text-center">
        <font face="华文行楷" color="white" size="7">
            图 书 馆
        </font>
    </div>
    <div style="height: 50px"></div>
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4">
            <form method="post" action="userServlet">
                <%--隐藏域--%>
                <input type="hidden" name="action" value="login">
                <div id="login" class="row text-center padding-up-down">
                    <font size="4" face="宋体">
                        <b>
                            请登录
                        </b>
                    </font>
                </div>
                <div class="row padding-left-right" style="margin-top: 10px">
                    <div class="form-group ">
                        <label>用户名</label>
                        <input name="username" class="form-control username"
                               placeholder="请输入用户名" value="${cookie.username.value}">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input name="password" type="password" class="form-control password"
                               placeholder="请输入密码" value="${cookie.password.value}">
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <label>
                                <input type="checkbox" name="remember" value="true" checked="checked">
                            </label>
                            <span style="font-size: 17px"><font face="黑体"> 记住密码</font></span>
                        </div>
                        <div class="col-sm-6 col-sm-offset-2 text-right" style="margin-bottom: 10px">
                            <span class="error">${requestScope.error}</span>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-default btn-primary">登入</button>
                </div>
                <div style="height: 30px"></div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        var bool = true;
        var error = $(".error");
        /*设置用户名文本框的失去焦点事件*/
        $(".username").blur(function () {
            /*获取文本框对象*/
            var obj = $(this);
            /*进行AJAX请求*/
            $.getJSON("userServlet", "action=checkUsername&username=" + obj.val(), function (data) {
                if (data) {
                    error.text("")
                    obj.parent().removeClass("has-error");
                    obj.parent().addClass("has-success");
                    bool = true;
                } else {
                    error.text("用户名不存在");
                    obj.parent().addClass("has-error");
                    bool = false;
                }
            })
        })
        /*设置表单提交事件*/
        $("form").submit(function () {
            return bool;
        })
    })
</script>
</html>

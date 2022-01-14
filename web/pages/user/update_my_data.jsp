<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>修改个人信息</title>
    <%--静态包含用户的背景图片设置--%>
    <%@ include file="../common/user_background.jsp" %>
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
<%--静态包含用户导航条--%>
<%@ include file="../common/user_navigation.jsp" %>
<div style="height: 70px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                修改个人信息
            </div>
            <div class="introduce">
                <form class="form-horizontal" action="userServlet" method="post">
                    <%--隐藏域 指定方法--%>
                    <input type="hidden" name="action" value="updateUserInfo">
                    <%--隐藏域 发送要修改的id--%>
                    <input type="hidden" name="id" value="${sessionScope.userInfo.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input class="form-control name" name="name" value="${sessionScope.userInfo.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <input class="form-control sex" name="sex" value="${sessionScope.userInfo.sex}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">生日</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control birthday" name="birthday"
                                   value="${sessionScope.userInfo.birthday}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input class="form-control address" name="address" value="${sessionScope.userInfo.address}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input class="form-control phone" name="phone" value="${sessionScope.userInfo.phone}">
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
<script>
    $(function () {
        var bool = true;
        var error = $(".error");
        /*性别文本框的失去焦点事件*/
        $(".sex").blur(function () {
            verify($(this), /^[男,女]$/, "性别只能为男或女!")
        })
        /*电话文本框的失去焦点事件*/
        $(".phone").blur(function () {
            verify($(this), /^\d{11}$/, "电话只能为11位!")
        })

        function verify(obj, regex, hint) {
            /*进行正则表达式校验*/
            if (regex.test(obj.val())) {
                obj.parent().removeClass("has-error")
                bool = true;
                error.text("");
            } else {
                obj.parent().addClass("has-error");
                bool = false;
                error.text(hint);
            }
        }

        /*表单提交事件*/
        $("form").submit(function () {
            if ($(".name").val() === '' || $(".birthday").val() === '' ||
                $(".address").val() === '') {
                error.text("请完善个人信息!");
                return false;
            }
            return bool;
        })

    })
</script>
</body>
</html>

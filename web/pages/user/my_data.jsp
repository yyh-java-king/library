<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>我的信息</title>
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
                我的信息
            </div>
            <div class="introduce">
                <table class="table table-hover">
                    <tr>
                        <td><b>读者证号</b></td>
                        <td>${sessionScope.user.username}</td>
                    </tr>
                    <tr>
                        <td><b>姓名</b></td>
                        <td>${sessionScope.userInfo.name}</td>
                    </tr>
                    <tr>
                        <td><b>性别</b></td>
                        <td>${sessionScope.userInfo.sex}</td>
                    </tr>
                    <tr>
                        <td><b>生日</b></td>
                        <td>${sessionScope.userInfo.birthday}</td>
                    </tr>
                    <tr>
                        <td><b>地址</b></td>
                        <td>${sessionScope.userInfo.address}</td>
                    </tr>
                    <tr>
                        <td><b>电话</b></td>
                        <td>${sessionScope.userInfo.phone}</td>
                    </tr>
                </table>
                <div class="text-center">
                    <button class="but btn-success btn-lg">修改个人信息</button>
                </div>
            </div>
        </div>
    </div>
</div>
<br><br><br><br><br><br><br>
<script>
    $(function () {
        /*设置表格第一个单元格的宽度*/
        $("tr td:first-child")[0].width = 100;

        /*设置按钮的单击事件*/
        $("button").click(function () {
            /*跳转到修改个人信息的页面*/
            location.href = "pages/user/update_my_data.jsp"
        })
    })
</script>
</body>
</html>

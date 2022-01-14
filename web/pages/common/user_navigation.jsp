<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/6
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--用户界面的导航条-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- 导航图标及汉堡按钮 -->
        <div class="container-fluid">
            <!--汉堡按钮-->
            <button aria-expanded="false" class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-1"
                    data-toggle="collapse" type="button">
                <!--sr-only : 阅读器专用样式-->
                <span class="sr-only">这是汉堡按钮</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- 导航的主题部分 -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <a class="navbar-brand" href="pages/user/main.jsp" style="margin-left: 100px;font-size: 40px">
                <font face="华文行楷" color="#357bb8">我的图书馆</font>
            </a>
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="bookServlet?action=list">图书查询<span class="sr-only">(current)</span></a>
                </li>
                <li><a href="pages/user/my_data.jsp">个人信息</a></li>
                <li><a href="lendServlet?action=getLandByUserId">我的借还</a></li>
                <li><a href="pages/user/update_password.jsp">密码修改</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 150px">
                <li><a href="pages/user/my_data.jsp">${sessionScope.userInfo.name},已登录</a></li>
                <li><a href="index.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

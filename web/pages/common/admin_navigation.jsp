<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--管理员界面的导航条-->
<nav class="navbar navbar-inverse">
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
                <font face="华文行楷" color="#f5f5f5">图书管理系统</font>
            </a>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">图书管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="bookServlet?action=list">全部图书</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="pages/book/add_update_book.jsp">添加图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">读者管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="userServlet?action=listUsers">全部读者</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="pages/user/add_update_user.jsp">添加读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">借还管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="lendServlet?action=list">借还日志</a></li>
                    </ul>
                </li>
                <li><a href="pages/user/update_password.jsp">密码修改</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 150px">
                <li><a>admin,已登录</a></li>
                <li><a href="index.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/5
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<meta charset="utf-8"><!--将字符集设置为utf-8-->
<meta http-equiv="X-UA-Compatible" content="IE=edge"><!--使用IE最新的渲染模式展示页面-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="static/BootStrap/css/bootstrap.min.css" rel="stylesheet">
<script src="static/jQuery/jquery-3.2.1.min.js"></script>
<script src="static/BootStrap/js/bootstrap.min.js"></script>

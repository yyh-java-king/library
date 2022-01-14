<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2021/12/21
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务器错误</title>
    <style>
        @import url("https://fonts.googleapis.com/css?family=Fira+Code&display=swap");

        * {
            margin: 0;
            padding: 0;
            font-family: "Fira Code", monospace;
        }

        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #ecf0f1;
        }

        .container {
            text-align: center;
            margin: auto;
            padding: 4em;
        }

        img {
            width: 256px;
            height: 225px;
        }

        h1 {
            margin-top: 1rem;
            font-size: 35px;
            text-align: center;
        }

        span {
            font-size: 60px;
        }


        p {
            margin-top: 1rem;
        }

        p.info {
            margin-top: 4em;
            font-size: 12px;
        }

        a {
            text-decoration: none;
            color: rgb(84, 84, 206);
        }
    </style>
    <base href="http://localhost:8080/library/">
</head>
<body>
<div class="container">
    <img src="static/img/obstacle.png" width="500px" height="500px" alt="找不到图片"/>
    <h1>
        <span>500</span> <br/>
        服务器错误
    </h1>
    <p>我们目前正在努力解决这个问题</p>
    <p>
        <a href="index.jsp">
            <font size="7">
                返回首页
            </font>
        </a>
    </p>
</div>
</body>
</html>

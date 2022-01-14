<%--
  Created by IntelliJ IDEA.
  User: yanyuhao
  Date: 2022/1/7
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--静态包含头部信息--%>
    <%@ include file="../common/head.jsp" %>
    <title>编辑图书</title>
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

        .form-control {
            width: 600px;
        }
    </style>
</head>
<body>
<%--静态包含管理员的导航条--%>
<%@ include file="../common/admin_navigation.jsp" %>
<div style="height: 20px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2 " style="background-color: white;border: #357bb8 1px solid">
            <div class="row title text-center" style="padding: 5px">
                编辑《大雪中的山庄》
            </div>
            <div class="introduce">
                <form action="#" method="get">
                    <div class="input-group">
                        <span class="input-group-addon">书名</span>
                        <input class="form-control bookName" name="bookName" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">作者</span>
                        <input class="form-control author" name="author" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">出版社</span>
                        <input class="form-control press" name="press" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">ISBN</span>
                        <input class="form-control ISBN" name="ISBN" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">简介</span>
                        <textarea class="form-control intro" rows="3" name="intro" value=""></textarea>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">语言</span>
                        <input class="form-control language" name="language" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">价格</span>
                        <input class="form-control price" name="price" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">出版日期</span>
                        <input type="date" class="form-control publicationDate" name="publicationDate" value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">图书类型</span>
                        <select class="form-control bookType" name="bookType">
                            <option>马克思主义</option>
                            <option>哲学</option>
                            <option>社会科学总论</option>
                            <option>政治法律</option>
                            <option>天文学、地球科学</option>
                            <option>生物科学</option>
                            <option>经济</option>
                            <option>军事</option>
                            <option>文化</option>
                            <option>其它</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">数量</span>
                        <input class="form-control number" name="number" value="">
                    </div>
                    <input type="submit" class="btn btn-success" value="提交">
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        /*设置表单提交事件*/
        $("form").submit(function () {
            if ($(".name").val() === '' || $(".author").val() === '' || $(".press").val() === '' ||
                $(".ISBN").val() === '' || $(".intro").val() === '' || $(".language").val() === '' ||
                $(".price").val() === '' || $(".publicationDate").val() === '' || $(".bookType").val() === '' ||
                $(".number").val() === '') {
                alert("请填入完整图书信息！");
                return false;
            }
        })
    })
</script>
</body>
</html>


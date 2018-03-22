<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/login.css" rel="stylesheet">
</head>

<body>
<div class="login">
    <h1><img src="../images/gongfanglogo.png" alt="" width="80px"></h1>
    <form id="fm" method="post" action="/admin/login">
        <input class="user" type="text" name="username" placeholder="用户名" required="required"/>
        <input class="pwd" type="password" name="password" placeholder="密码" required="required"/>
        <div class="t-box dp-n">
            <img src="../images/error.png" alt="" class="t-img">
            <p class="t-color"></p>
        </div>
        <c:choose>
            <c:when test="${retcode eq '1'}">
                <div class="t-box">
                    <img src="../images/error.png" alt="" class="t-img">
                    <p class="t-color">${message}</p>
                </div>
            </c:when>
        </c:choose>
        <button type="submit" class=" btn btn-primary btn-block btn-large" id="submit">登录</button>
        <a class="a_style" href="/admin/register">还没有账号，注册一个</a>
    </form>

</div>
<!-- 引入公共js文件 -->
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script>

    /**
     * 表单提交
     */
    $(".btn").on("click", function () {
        var user = $(".user").val();
        if (user == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("用户名不能为空！");
            return;
        }
        var pwd = $(".pwd").val();
        if (pwd == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("密码不能为空！");
            return;
        }
    });


</script>
</body>

</html>
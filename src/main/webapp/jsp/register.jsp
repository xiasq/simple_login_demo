<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link href="../css/register.css" rel="stylesheet">
</head>
<body>
<div class="register">
    <form action="/admin/doRegister" method="post" id="form" onsubmit="return check();">
        <h1>管理员注册</h1>
        账号:<input type="text" id="name" name="name" placeholder="账号" value="${admin.name}"/><br><br>
        密码:<input type="password" id="password" name="password" placeholder="密码" value="${admin.password}"/><br><br>


        <div class="form-group">
            验证码：<input id="authCode" name="authCode" type="text" placeholder="验证码" />
            <!--这里img标签的src属性的值为后台实现图片验证码方法的请求地址-->
            <label><img type="image" src="authCode.do" id="codeImage" onclick="chageCode()" title="图片看不清？点击重新得到验证码" style="cursor:pointer;"/></label>
            <label><a onclick="chageCode()">换一张</a></label>
        </div>

        <div class="t-box dp-n">
            <img src="../images/error.png" alt="" class="t-img">
            <p class="t-color"></p>
        </div>
        <c:choose>
            <c:when test="${retcode eq '1'}">
                <div class="t-box">
                    <img src="../images/error.png" alt="" class="t-img">
                    <p class="t-color1">${message}</p>
                </div>
            </c:when>
        </c:choose>
        <input type="submit" class=" btn btn-primary btn-block btn-large" value="保存"/>
    </form>

</div>
<!-- 引入公共js文件 -->
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script>

    /**
     * 表单提交
     */
    function check() {
        var name = $("#name").val();
        if (name == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("账号不能为空！");
            return false;
        }
        var pwd = $("#password").val();
        if (pwd == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("密码不能为空！");
            return false;
        }
        var authCode = $("#authCode").val();
        if (authCode == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("验证码不能为空！");
            return false;
        }
        return true;
    }

    $(document).ready(function() {
        chageCode();//进入页面就刷新生成验证码
    });

    function chageCode(){
        $('#codeImage').attr('src','/admin/getCode?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
    }

</script>
</body>
</html>
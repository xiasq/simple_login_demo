<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <link href="../css/addoredit.css" rel="stylesheet">
        <link type="text/css" href="../css/jquery.datetimepicker.css" rel="stylesheet" />
    </head>
<body >
<div class="addoredit">
    <form action="/main/save" method="post" id="form" onsubmit="return check();">
        <table>
            <tr><td><input type="hidden" name="id" value = "${user.id}"/></td></tr>
            <tr>
                <td><label>用户名: </label><input type="text" class="user" name="username" placeholder="用户名" value = "${user.username}"/></td>
            </tr>
            <tr>
                <td><label>密码: </label><input type="password" class="pwd" name="password" placeholder="密码" value = "${user.password}"/></td>
            </tr>
            <tr>
                <td><label>邮箱:</label><input type="text" class="email" name="email" placeholder="邮箱" value = "${user.email}"/> </td>
            </tr>
            <tr>
                <td><label>证件类型:</label>
                    <select id="cardType" name="cardType" class="cardType">
                        <option value="1" <c:if test="${user.cardType=='1'}">selected</c:if> >身份证</option>
                        <option value="2" <c:if test="${user.cardType=='2'}">selected</c:if> >其他</option>
                    </select>
                </td>
            </tr>
            <tr> <td><label>证件号:</label><input type="text" class="idcard" name="idcard" placeholder="证件号" value = "${user.idcard}"/></td></tr>
            <tr> <td><label>手机号:</label><input type="text" class="mobile" name="mobile" placeholder="手机号" value = "${user.mobile}"/></td></tr>

            <tr> <td><label>出生日期:</label>
                <input id="birthday" name="birthday" class="birthday" type="text" placeholder="出生日期" value = "${user.birthday }" /> </td></tr>

            <tr><td><label>性别:</label>
                <input type="radio" class="sex" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if> >男
                <input type="radio" class="sex" name="sex" value="2" <c:if test="${user.sex==2}">checked</c:if> >女
                </td></tr>

            <tr> <td><label>语言:</label>
                    <input type="checkbox" name="language" class="language" id="Chinese" value="Chinese" <c:if test="${fn:contains(user.language, 'Chinese')}">checked</c:if> >中文
                    <input type="checkbox" name="language" class="language" id="English" value="English" <c:if test="${fn:contains(user.language, 'English')}">checked</c:if> >英文
                    <input type="checkbox" name="language" class="language" id="French" value="French" <c:if test="${fn:contains(user.language, 'French')}">checked</c:if> >法语
                 </td></tr>
            <tr> <td><label>个人简介:</label><textarea name="introduction" class="introduction" cols="36" rows="4">${user.introduction}</textarea></td></tr>

        </table>

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

        <input type="submit" class=" btn btn-primary btn-block btn-large" value="保存" />
        <%--<input type="reset" class=" btn btn-primary btn-block btn-large" value="取消" />--%>
    </form>

</div>
<!-- 引入公共js文件 -->
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script type ="text/javascript" src="../js/jquery.datetimepicker.js"></script>
<script>

    /**
     * 表单提交
     */
    function check() {
        var user = $(".user").val();
        if (user == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("用户名不能为空！");
            return false;
        }
        var pwd = $(".pwd").val();
        if (pwd == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("密码不能为空！");
            return false;
        }

        var email = $(".email").val();
        if (email == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("邮箱不能为空！");
            return false;
        }
        if (!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("邮箱格式不正确！");
            return false;
        }

        var idcard = $(".idcard").val();
        if (idcard == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("证件号不能为空！");
            return false;
        }
        var cardType = $(".cardType").find("option:selected").val();
        if (cardType == "1") {
            if (!idcard.match(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/)) {
                $(".t-box").removeClass("dp-n");
                $(".t-color").html("证件号格式不正确！");
                return false;
            }
        }

        var mobile = $(".mobile").val();
        if (mobile == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("手机号不能为空！");
            return false;
        }
        if (!mobile.match(/^1[34578]\d{9}$/)) {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("手机号格式不正确！");
            return false;
        }

        var birthday = $(".birthday").val();
        if (birthday == "") {
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("出生日期不能为空！");
            return false;
        }

        if($(".language:checked").length==0){
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("请勾选语言选项！");
            return false;
        }

        if(!$(".sex").is(':checked')){
            $(".t-box").removeClass("dp-n");
            $(".t-color").html("请勾选性别！");
            return false;
        }

        return true;
    }

    $('#birthday').datetimepicker();

    $('.fileup').onclick(function () {
        alert("a");
    });
</script>
</body>

</html>
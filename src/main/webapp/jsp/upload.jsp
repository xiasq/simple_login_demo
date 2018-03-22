<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="../css/addoredit.css" rel="stylesheet">
</head>
<body>
<form action="/main/doUpload"
      method="POST" enctype="multipart/form-data">
    头像：
    <input type="file" name="file">
    <input type="hidden" name="userId" value="${userId}">
    <button type="submit" value="提交" class="btn">提交</button>

    <c:choose>
        <c:when test="${retcode eq '1'}">
            <div class="t-box">
                <img src="../images/error.png" alt="" class="t-img">
                <p class="t-color1">${message}</p>
            </div>
        </c:when>
    </c:choose>
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>菜单管理</title>
    <link href="../css/userlist.css" rel="stylesheet">

</head>

<body>
<!-- 表格显示区域 -->
<table id="sample_1" align="center" border="1">
    <div align="center"><a class="a_style" href="/main/add">添加用户</a>
        <a class="a_style" href="/main/quit">退出登录</a></div>
    <thead>
    <tr>
        <th>用户id</th>
        <th class="">用户名</th>
        <th class="">邮箱</th>
        <th class="">手机号</th>
        <th class="">证件类型</th>
        <th class="">证件号</th>
        <th class="">语言</th>
        <th class="">出生年月</th>
        <th class="">创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>

    <c:choose>
        <c:when test="${empty users}">
            <tr>
                <td colspan="17">暂时没有数据记录</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${users }" var="record" begin="0" step="1">
                <tr>
                    <td class="center"><c:out value="${record.id }"></c:out></td>
                    <td class="center"><c:out value="${record.username }"></c:out></td>
                    <td class="center"><c:out value="${record.email }"></c:out></td>
                    <td class="center"><c:out value="${record.mobile}"></c:out></td>
                    <td class="center">
                        <c:choose>
                            <c:when test="${record.cardType=='1'}">1-身份证</c:when>
                            <c:otherwise>2-其他</c:otherwise>
                        </c:choose></td>
                    <td class="center"><c:out value="${record.idcard}"></c:out></td>
                    <td class="center"><c:out value="${record.language}"></c:out></td>
                    <td class="center"><fmt:formatDate value="${record.birthday }"
                                                       pattern="yyyy-MM-dd  HH:mm:ss"/></td>
                    <td class="center"><fmt:formatDate value="${record.lastLoginTime }"
                                                       pattern="yyyy-MM-dd  HH:mm:ss"/></td>
                    <td>
                        <a class="a_style" href="/main/edit?id=${record.id}">编辑</a>
                        <a class="a_style"
                           href="/main/delete?id=${record.id}">删除</a>
                        <a class="a_style"
                           href="/main/queryDetail?id=${record.id}">明细</a>
                    </td>
                </tr>
            </c:forEach>


        </c:otherwise>
    </c:choose>

    </tbody>


</table>

<div align="center">
    <a class="a_style" href="?currentPage=1">首页</a>
    <c:forEach begin="${page.lpage}" end="${page.rpage}" var="pageNum">
        <a class="a_style" href="?currentPage=${pageNum }">${pageNum }</a>
    </c:forEach>
    <a class="a_style" href="?currentPage=${page.last }">尾页</a>  <span>共计${page.total}条</span></div>

</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <link href="../css/detail.css" rel="stylesheet">
        <link type="text/css" href="../css/jquery.datetimepicker.css" rel="stylesheet" />
    </head>
<body >
<div class="addoredit">
    <form>
        <table>

            <tr><td><table>
                <tr>
                    <td><label>头像:</label>
                        <c:choose>
                            <c:when test="${fileUrl ne null}">
                                <a href="/main/upload?id=${user.id}"><img alt="" src="${basePath}${fileUrl}"  class="img_cover"  /></a>
                            </c:when>
                            <c:otherwise>
                                <a href="/main/upload?id=${user.id}"><img alt="" src="../images/default.png" class="img_cover" /></a>
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>

            </table></td>

             <td>
         <table>
            <tr><td><input type="hidden" name="id" value = "${user.id}"/></td></tr>


            <tr>
                <td><label>用户名: </label><input type="text" readonly="readonly" value = "${user.username}"/></td>

                <td><label>邮箱:</label><input type="text" readonly="readonly" value = "${user.email}"/> </td>
            </tr>
            <tr>
                <td><label>证件类型:</label>
                    <c:choose>
                        <c:when test="${user.cardType == '1'}">
                            <input type="text" readonly="readonly" value="1-身份证" />
                        </c:when>
                        <c:otherwise>
                            <input type="text" readonly="readonly" value="2-其他" />
                        </c:otherwise>
                    </c:choose>

                </td>
                 <td>
                    <label>证件号:</label><input type="text" readonly="readonly" value = "${user.idcard}"/>
                </td>
            </tr>
            <tr>  </tr>
            <tr> <td><label>手机号:</label><input type="text" readonly="readonly" value = "${user.mobile}"/>
                </td>
                 <td><label>出生日期:</label>
                <input type="text" readonly="readonly" value = "${user.birthday }" /> </td></tr>

            <tr> <td><label>语言:</label>
                    <input type="text" readonly="readonly" value="${user.language}" />
                 </td>

                <td><label>性别:</label>
                    <c:choose>
                        <c:when test="${user.sex == 1}">
                            <input type="text" readonly="readonly" value="男" />
                        </c:when>
                        <c:when test="${user.sex == 2}">
                            <input type="text" readonly="readonly" value="女" />
                        </c:when>

                    </c:choose>



                </td>
            </tr>


            <tr> <td><label>个人简介:</label><textarea cols="36" rows="4" readonly="readonly">${user.introduction}</textarea></td></tr>

        </table>

            </td></tr>
        </table>

    </form>
</div>
</body>
</html>
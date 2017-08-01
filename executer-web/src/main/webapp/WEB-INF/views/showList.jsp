<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="homePage.jsp"/>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 2017/7/30
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;" pageEncoding="utf-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>返回数据</title>
</head>
<body>
<h1>返回列表数据：</h1>
<
<div class="table-responsive">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${retlist}" var="ret">
        <tr>
                <td>${ret.id}</td>
                <td>${ret.name}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/10/2022
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <style>

        .body-body{
            text-align: center;

        }

    </style>
</head>
<body>

<table border="1" width="600px">
    <caption><h1>Danh sách khách hàng</h1></caption>
    <thead>
        <tr>
            <th>Tên</th>
            <th>Ngày sinh</th>
            <th>Địa chỉ</th>
            <th>Ảnh</th>
        </tr>
    </thead>
    <tbody class="body-body">
    <c:forEach items="${requestScope['custommerList']}" var="item">
        <tr>
            <td>${item.getName()}</td>
            <td>${item.getBirthday()}</td>
            <td>${item.getAddress()}</td>
            <td><img style="height: 50px"; width="50px" src="${item. getImage()}" alt=""> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

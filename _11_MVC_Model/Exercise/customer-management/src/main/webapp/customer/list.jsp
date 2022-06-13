<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh Sach Khach Hang</title>
</head>
<body>
<h1>Customers</h1>
<p>
    <a href="/customers?action=create">Tao Khach Hang Moi</a>
</p>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Chinh Sua Khach Hang </th>
        <th>Xoa Khach Hang</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items='${requestScope["customers"]}' var="item">
        <tr>
            <td>${item.getId()}</td>
            <td><a href="/customers?action=view&id=${item.getId()}">${item.getName()}</a></td>
            <td>${item.getEmail()}</td>
            <td>${item.getAddress()}</td>
            <td><a href="/customers?action=edit&id=${item.getId()}">edit</a></td>
            <td><a href="/customers?action=delete&id=${item.getId()}">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
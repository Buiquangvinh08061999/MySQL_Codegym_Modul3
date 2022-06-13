<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting customer</title>
</head>
<body>
<h1>Xoa Khach Hang</h1>
<p>
    <a href="/customers">Quay Lai Danh Sach Khach Hang</a>
</p>
<form method="post">
    <h3>Ban co chac khong</h3>
    <fieldset>
        <legend>Thong Tin Khach Hang</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>${requestScope["customer"].getName()}</td>
            </tr>
            <tr>
                <td>Email: </td>
                <td>${requestScope["customer"].getEmail()}</td>
            </tr>
            <tr>
                <td>Address: </td>
                <td>${requestScope["customer"].getAddress()}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete customer"></td>
                <td><a href="/customers">Quay Lai Danh Sach Khach Hang</a></td>
            </tr>
        </table>
    </fieldset>
</body>
</html>
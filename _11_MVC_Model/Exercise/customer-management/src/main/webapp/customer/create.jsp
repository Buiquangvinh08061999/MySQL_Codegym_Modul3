<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create new customer</title>
  <style>
    .message{
      color:green;
    }
  </style>
</head>
<body>
<h1>Tao khach hang moi</h1>
<p>
  <c:if test='${requestScope["message"] != null}'>
    <span class="message"> ${requestScope["message"]} </span>
  </c:if>
</p>
<p>
  <a href="/customers">Quay Lai De Xem Khach Hang Vua Them</a>
</p>
<form method="post">
  <fieldset>
    <legend>Thong Tin Khach Hang</legend>
    <table>
      <tr>
        <td>Name: </td>
        <td><input type="text" name="name" id="name"></td>
      </tr>
      <tr>
        <td>Email: </td>
        <td><input type="email" name="email" id="email"></td>
      </tr>
      <tr>
        <td>Address: </td>
        <td><input type="text" name="address" id="address"></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Create customer"></td>
      </tr>
    </table>
  </fieldset>
</form>
</body>
</html>
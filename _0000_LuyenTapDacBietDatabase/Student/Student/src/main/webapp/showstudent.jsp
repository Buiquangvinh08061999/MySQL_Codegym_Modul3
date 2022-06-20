<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: -webkit-center ; margin-top: 100px">
<table border="1px solid black" style="text-align: -webkit-center">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Dob</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listS}" var="x">
        <tr>
            <td>${x.id}</td>
            <td>${x.name}</td>
            <td>
                <c:if test="${x.gender == 1}">
                    Male
                </c:if>
                <c:if test="${x.gender == 0}">
                    Female
                </c:if>
            </td>
            <td>${x.dob}</td>
            <td>
                <a href="update?sid=${x.id}">Update</a>
                <a href="#" onclick="showMess(${x.id})"> Delete</a>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<a href="add.jsp">Create New</a>
</body>

<script>
    function showMess(id) {
        var option = confirm("Bạn có chắc muốn xóa ID số " + id + " này không?")
        if (option === true) {
            window.location.href ='delete?sid=' + id;
        }
    }


</script>

</html>

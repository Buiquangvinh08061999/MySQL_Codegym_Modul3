
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="add" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>Gender</td>
            <td>
                <input type="radio" name="gender" value="1">Male
                <input type="radio" name="gender" value="0">FeMale
            </td>
        </tr>
        <tr>
            <td>Dob</td>
            <td>
                <input type="text" name="dob">
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
               <button type="submit">Add student</button>
            </td>
        </tr>


    </table>
</form>
</body>
</html>

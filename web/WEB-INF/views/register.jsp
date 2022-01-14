<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>

<h3>Register Page</h3>

<p style="color: red;">${errorString}</p>

<form method="post" action="${pageContext.request.contextPath}/controller?command=register">
    <h3>${failedRegisterMessage}</h3><br/>
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td>email</td>
            <td><input type="text" name="email" value= "${user.email}" /> </td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="password" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="userName" value= "${user.userName}" /> </td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="userSurname" value= "${user.userSurname}" /> </td>
        </tr>
        <tr>
            <td>phone</td>
            <td><input type="text" name="phone" value= "${user.phone}" /> </td>
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>
 <br>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <jsp:include page="common/header.jsp"/>
   </head>
<body>
<h3>Login Page</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/controller?command=log_in">
    <br/>
    <h3>${failedRegisterMessage}</h3><br/>
    <strong>${errorLoginMessage}</strong>
    <br/>
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td>email</td>
            <td><input type="text" name="email" value= "${user.email}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "${user.password}" /> </td>
              </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/controller?command=show_main_page">Cancel</a>
            </td>
        </tr>
    </table>
</form>
 <br>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
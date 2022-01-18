<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <jsp:include page="common/header.jsp"/>

</head>
<body>


<h3>Hello: ${user.userName}</h3>

User Name: <b>${user.userName}</b>
<br />
email: ${user.email } <br />
<br />
role: ${user.role }
<br />


</body>
<jsp:include page="common/footer.jsp"/>
</html>
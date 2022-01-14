<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Task</title>
</head>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<h3>User Task</h3>

<p style="color: red;">${errorString}</p>

<form method="post" action="${pageContext.request.contextPath}/controller?command=register_order">
    <h3>${failedRegisterMessage}</h3><br/>
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td>Street</td>
            <td><input type="text" name="street" value= "${order.street}" /> </td>
        </tr>
        <tr>
            <td>House number</td>
            <td><input type="text" name="houseNumber" value= "${order.houseNumber}" /> </td>
        </tr>
        <tr>
            <td>Work description</td>
            <td><input type="text" name="scopeOfWork" value= "${order.scopeOfWork}" /> </td>
        </tr>
        <tr>
            <td>Desirable time</td>
            <td><input type="date" name="desirableTimeOfWork" value= "${order.desirableTimeOfWork}" /> </td>
        </tr>
        <tr>
            Picture<br />
            <input type = "file" name = "xml" size = "50" accept="image/*" /> <br />
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/index.jsp">Cancel</a>
            </td>
        </tr>
    </table>
</form>
<br>
</body>
</html>

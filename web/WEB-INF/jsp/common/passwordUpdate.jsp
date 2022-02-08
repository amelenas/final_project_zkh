<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="updatePassword.page" var="thanks"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="enter.current" var="current"/>
<fmt:message key="enter.new.password" var="newPassword"/>
<fmt:message key="enter.repeat" var="repeat"/>
<fmt:message key="password.helper" var="helper"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <script type="text/javascript">
        window.history.forward();

        function noBack() {
            window.history.forward();
        }
    </script>
    <title>${thanks}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<header>
    <jsp:include page="header.jsp"/>
</header>
<br/>
<div align="center">${resultInfo}</div>
<div class="container" style="height: 66vh">
    <div class="row" style="justify-content: center">
        <form style="justify-content: center" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_password">
            <div class="form-group">
                <label for="exampleInputPassword3">${current}</label>
                <input type="password" class="form-control" id="exampleInputPassword3" aria-describedby="emailHelp"
                       name="oldPassword" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">${newPassword}</label>
                <input type="password" class="form-control" id="exampleInputPassword1"
                       name="newPassword"  required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
                <small id="emailHelp" class="form-text text-muted">${helper}</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword2">${repeat}</label>
                <input type="password" class="form-control" id="exampleInputPassword2"
                       name="repeatPassword" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
            </div>

            <button type="submit" class="btn btn-primary"
                    style="color: darkslategray; border-color: darkslategray">${update}</button>
        </form>
    </div>
</div>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>

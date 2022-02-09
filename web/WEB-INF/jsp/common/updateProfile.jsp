<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="update.profile.page" var="title"/>
<fmt:message key="common.page.firstName" var="name"/>
<fmt:message key="common.page.lastName" var="surname"/>
<fmt:message key="name.helper" var="nameHelper"/>
<fmt:message key="common.logIn" var="login"/>
<fmt:message key="signUp.page.phoneHelper" var="phoneHelper"/>
<fmt:message key="common.page.email" var="email"/>
<fmt:message key="update.profile.button" var="button"/>
<fmt:message key="common.page.phone" var="phone"/>
<fmt:message key="lastName.helper" var="lastNameHelper"/>

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

    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<header>
    <jsp:include page="header.jsp"/>
</header>

<div class="container" style="height: 75vh">
    <div class="row" style="justify-content: center">
        <form style="justify-content: center" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_profile">
            <div class="form-group">
                <label for="exampleInputPassword0">${name}</label>
                <input type="text" class="form-control" id="exampleInputPassword0" aria-describedby="emailHelp"
                       name="userName" value="${sessionScope.name}"
                       required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
                <small id="emailHelp1" style="color: black" class="form-text text-muted">${nameHelper}</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">${surname}</label>
                <input type="text" class="form-control" id="exampleInputPassword1"
                       name="userSurname" value="${sessionScope.userSurname}"
                       required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
                <small id="emailHelp2" style="color: black" class="form-text text-muted">${lastNameHelper}</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword2">${phone}</label>
                <input type="text" class="form-control" id="exampleInputPassword2"
                       name="phone" value="${sessionScope.phone}"
                       required pattern="(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?">
                <small id="emailHelp3" style="color: black" class="form-text text-muted">${phoneHelper}</small>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail3">${email}</label>
                <input type="text" class="form-control" id="exampleInputEmail3"
                       name="email" value="${sessionScope.email}"
                       required pattern="^(?=.{1,45}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$">
            </div>
            <c:if test="${requestScope.message != null}">
                <div class="form-group form-check" style="color: red">
                    <input type="hidden" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">
                        <fmt:setBundle basename="locale" var="rb"/>
                        <fmt:message key="${requestScope.message}" bundle="${rb}"/>
                    </label>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary"
                    style="color: darkslategray; border-color: darkslategray">${button}</button>
        </form>
    </div>
</div>
<div id="footer"><jsp:include page="footer.jsp"/></div>
</body>
</html>

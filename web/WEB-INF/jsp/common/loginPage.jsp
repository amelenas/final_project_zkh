<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="common.logIn" var="login"/>
<fmt:message key="common.page.email" var="email"/>
<fmt:message key="common.page.password" var="password"/>
<fmt:message key="common.page.cancel" var="cancel"/>
<fmt:message key="logIn.page.invalidLogin" var="invalidLogin"/>
<fmt:message key="logIn.page.invalidPassword" var="invalidPassword"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet" type="text/css">
    <title>${login}</title>

</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<header>
    <jsp:include page="header.jsp"/>
</header>
<p style="color: red;">${errorMessage}</p>
<p style="color: #1f3d2e;">${sessionScope.loginMessageRegistered}</p>
<br/>
<div class="container" style="height: inherit">
    <div class="row">
        <form action="${pageContext.request.contextPath}/controller?command=log_in" method="post">
            <input type="hidden" name="redirectId" value="${param.redirectId}">

            <div class="col-md-6 mb-3">
                <label for="validationCustom01" class="form-label">${email}</label>
                <input type="email" name="email" class="form-control" id="validationCustom01" size="50"
                       required
                       pattern="^(?=.{1,45}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$"
                       value="${requestScope.email}">
                <div class="invalid-feedback">
                    ${invalidLogin}
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="validationCustom02" class="form-label">${password}</label>
                <input type="password" name="password" class="form-control" id="validationCustom02" size="50"
                       required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}">
                <div class="invalid-feedback">
                    ${invalidPassword}
                </div>
            </div>
            <div class="row">
                <c:if test="${requestScope.loginMessage != null}">
                    <div class="form-group">
                        <div class="err-message-from-server">
                            <fmt:setBundle basename="locale" var="rb"/>
                            <fmt:message key="${requestScope.loginMessage}" bundle="${rb}"/>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="col-12">
                <button class="btn btn-primary mt-4" type="submit">${login}</button>
            </div>
            <br/>
            <div class="col-12">
                <a href="${pageContext.request.contextPath}/controller?command=show_main_page">${cancel}</a>
            </div>
            <br/>
        </form>
    </div>
</div>
</body>
</html>
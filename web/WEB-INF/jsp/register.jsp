<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="signUp.page" var="title"/>
<fmt:message key="signUp.page.phone" var="phone"/>
<fmt:message key="signUp.page.password" var="password"/>
<fmt:message key="password.helper" var="passwordHelper"/>
<fmt:message key="signUp.page.conformPassword" var="confirm"/>
<fmt:message key="signUp.page.firstName" var="name"/>
<fmt:message key="name.helper" var="nameHelper"/>
<fmt:message key="signUp.page.lastName" var="surname"/>
<fmt:message key="lastName.helper" var="lastNameHelper"/>
<fmt:message key="signUp.page.email" var="email"/>
<fmt:message key="signUp.page.phoneHelper" var="phoneHelper"/>
<fmt:message key="signUp.page.valid" var="good"/>
<fmt:message key="signUp.page.invalid" var="bad"/>
<fmt:message key="signUp.button" var="signUp"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
<jsp:include page="common/header.jsp"/>
<div class="container" style="height: 80vh">
    <div class="row">
        <form action="${pageContext.request.contextPath}/controller?command=register" method="post">
            <input type="hidden" name="redirectId" value="${param.redirectId}">

            <div class="form-row">
                <label for="validationServer05">${email}</label>
                <input type="email" name="email" class="form-control" id="validationServer05" required
                       pattern="^(?=.{1,45}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$"
                       value="${requestScope.formData['email']}">
            </div>

            <div class="form-row">
                <div class="col-md-6 mb-3">
                    <label for="validationServer01">${phone}</label>
                    <input type="text" name="phone" class="form-control" id="validationServer01"
                           pattern="(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?"
                           placeholder="+375 _ _ _ _ _ _ _ _ _ _"
                           value="${requestScope.formData['phone']}">
                    ${phoneHelper}
                </div>

                <div class="col-md-3 mb-3">
                    <label for="validationServer02">${password}</label>
                    <input type="password" name="password" class="form-control" id="validationServer02"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                           value="${requestScope.formData['password']}">
                    <small id="passwordHelpBlock" class="form-text text-muted">
                        ${passwordHelper}
                    </small>
                </div>

                <div class="col-md-3 mb-3">
                    <label for="validationServer002">${confirm}</label>
                    <input type="password" name="repeatPassword" class="form-control" id="validationServer002"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                           value="${requestScope.formData['repeatPassword']}">
                </div>
            <div class="form-row">
                <div class="col-md-6 mb-3">
                    <label for="validationServer03">${name}</label>
                    <input type="text" name="userName" class="form-control" id="validationServer03"
                           required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$"
                           value="${requestScope.formData['userName']}">
                    <small id="passwordHelpBlockF" class="form-text text-muted">
                        ${nameHelper}
                    </small>
                </div>

                <div class="col-md-3 mb-3">
                    <label for="validationServer04">${surname}</label>
                    <input type="text" name="userSurname" class="form-control"
                           id="validationServer04" required
                           pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$"
                           value="${requestScope.formData['userSurname']}">
                    <small id="passwordHelpBlockL" class="form-text text-muted">
                        ${lastNameHelper}
                    </small>
                </div>
            </div>
            <div class="row">
                <c:if test="${requestScope.errorMessage != null}">
                    <div class="form-group">
                        <div class="col-md-6 mb-3">
                            <div class="err-message-from-server">
                                <fmt:setBundle basename="locale" var="rb"/>
                                <fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>

            <button class="btn btn-primary" type="submit">${signUp}</button>
        </form>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="navigation.home" var="home"/>
<fmt:message key="navigation.title" var="thanks"/>
<fmt:message key="navigation.logOut" var="logOut"/>
<fmt:message key="common.logIn" var="logIn"/>
<fmt:message key="navigation.signUp" var="signUp"/>
<fmt:message key="navigation.language" var="language"/>
<fmt:message key="navigation.profile" var="profile"/>
<fmt:message key="client.managerTask" var="managerTask"/>
<fmt:message key="client.employeeTask" var="employeeTask"/>
<fmt:message key="client.leaveRequest" var="leaveRequest"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
             integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <meta charset="UTF-8">
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand"  href="${pageContext.request.contextPath}/controller?command=show_main_page">${thanks}</a>
               <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/controller?command=show_main_page">${home}</a>
                </li>
                    <c:if test="${sessionScope.authorization}">
                    <li class="nav-item">
                        <div style="word-spacing:10px; color: aquamarine"> ${sessionScope.name} ${sessionScope.role}&nbsp;</div>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/controller?command=user_info">${profile}</a>&nbsp;
                    </li>
                    <li class="nav-item"><hr/>
                    <c:choose>
                        <c:when test="${sessionScope.role=='ADMIN'}">
                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/controller?command=manager_task">${managerTask}</a>&nbsp;
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.role=='USER'}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=show_register_request_page">${leaveRequest}</a>&nbsp;
                            </li>

                        </c:when>
                        <c:when test="${sessionScope.role=='EMPLOYEE'}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=employee_task">${employeeTask}</a>&nbsp;
                            </li>
                        </c:when>
                    </c:choose>
                </c:if>
                <c:choose>
                    <c:when test="${sessionScope.authorization}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=log_out">${logOut}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_login">${logIn}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_register">${signUp}</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <li class="nav-item dropdown"><a
                        class="nav-link dropdown-toggle" href="#"
                        id="navbarDropdownMenuLink" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"> ${language} </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=change_locale&amp;locale=en">EN
                            (English)</a>

                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=change_locale&amp;locale=ru">RU
                            (Русский)
                        </a>
                    </div>
                </li>
            </ul>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="change.role.page" var="title"/>
<fmt:message key="order.user.id" var="userId"/>
<fmt:message key="common.page.firstName" var="name"/>
<fmt:message key="common.page.lastName" var="userSurname"/>
<fmt:message key="common.page.email" var="email"/>
<fmt:message key="common.page.phone" var="phone"/>
<fmt:message key="make.admin" var="makeAdmin"/>
<fmt:message key="make.user" var="makeUser"/>
<fmt:message key="common.page.role" var="role"/>

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
    <jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
</header>
<h3 align="center">${title}</h3>
<c:choose>
<c:when test="empty ${requestScope.users}">
<div class="container">
    <br/><br/><h4 style="color: black">${emptyList}</h4>
    </c:when>
    <c:otherwise>
    <div class="container" style="height: 69vh">
        <div class="row" align="justify">
            <table class="table table-striped" style="height: inherit">
                <thead>
                <tr>
                    <th scope="col">${userId}</th>
                    <th scope="col">${name}</th>
                    <th scope="col">${userSurname}</th>
                    <th scope="col">${email}</th>
                    <th scope="col">${phone}</th>
                    <th scope="col">${role}</th>

                    <th scope="col">${makeAdmin}</th>
                    <th scope="col">${makeUser}</th>

                    <th scope="col"></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="value" items="${requestScope.users}">
                    <tr>

                        <td>${value.userId}</td>
                        <td>${value.userName}</td>
                        <td>${value.userSurname}</td>
                        <td>${value.email}</td>
                        <td>${value.phone}</td>
                        <td>${value.role}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=add_admin&email=${value.email}&page=${requestScope.page}"
                               style="color: darkgreen">
                                    ${makeAdmin}
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=make_user&email=${value.email}&page=${requestScope.page}"
                               style="color: darkgreen">
                                    ${makeUser}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="container">
                <div class="row" style="justify-content: center">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${requestScope.page != 1}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/controller?command=show_add_admin_page&page=${requestScope.page-1}">
                                <span aria-hidden="true">&laquo;</span>
                                </c:if>
                                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                                <c:choose>
                                <c:when test="${requestScope.page eq i}">
                                <a class="page-link">${i}</a>
                                </c:when>
                                <c:otherwise>
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/controller?command=show_add_admin_page&page=${i}">${i}</a>
                                </c:otherwise>
                                </c:choose>
                                </c:forEach>

                                <c:if test="${requestScope.phone lt requestScope.noOfPages}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/controller?command=show_add_admin_page&page=${requestScope.page+1}">
                                    <span aria-hidden="true">&raquo;</span>&raquo;</a>
                                </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

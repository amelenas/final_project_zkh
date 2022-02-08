<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="order.page.title" var="title"/>
<fmt:message key="order.page.emptyList" var="emptyList"/>
<fmt:message key="assign.performer" var="assignPerformer"/>
<fmt:message key="common.page.firstName" var="userName"/>
<fmt:message key="common.page.lastName" var="lastName"/>
<fmt:message key="order.user.id" var="userID"/>
<fmt:message key="common.page.phone" var="phone"/>
<fmt:message key="common.page.email" var="email"/>


<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../common/header.jsp"/>
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


<c:choose>
    <c:when test="empty ${requestScope.orderData}">
        <div class="container" style="height: 69vh">
            <br/><br/><h4 style="color: black">${emptyList}</h4>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container" style="height: 69vh">
            <div class="row">
                <table class="table table-striped" style="height: inherit">
                    <thead>
                    <tr>
                        <th scope="col">${userID}</th>
                        <th scope="col">${userName}</th>
                        <th scope="col">${lastName}</th>
                        <th scope="col">${phone}</th>
                        <th scope="col">${email}</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="key" items="${sessionScope.performers}">
                        <tr>
                            <td>${key.userId}</td>
                            <td>${key.userName}</td>
                            <td>${key.userSurname}</td>
                            <td>${key.phone}</td>
                            <td>${key.email}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=choose_performer&userId=${key.userId}&registrationId=${requestScope.registrationId}"
                                   style="color: darkgreen">
                                        ${assignPerformer}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
<div id="footer">
    <jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</div>
</html>

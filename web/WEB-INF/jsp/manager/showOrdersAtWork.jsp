<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="orders.at.work" var="title"/>
<fmt:message key="order.page.emptyList" var="emptyList"/>
<fmt:message key="order.registrationNumberId" var="registrationId"/>
<fmt:message key="order.user.id" var="userId"/>
<fmt:message key="common.page.street" var="street"/>
<fmt:message key="common.page.house.number" var="houseNumber"/>
<fmt:message key="common.page.house.apartment" var="apartmentNumber"/>
<fmt:message key="common.page.house.work.description" var="description"/>
<fmt:message key="common.page.house.work.orderStatus" var="orderStatus"/>
<fmt:message key="assign.performer" var="assignPerformer"/>
<fmt:message key="pick.up.order" var="pickUpOrder"/>
<fmt:message key="performer.role" var="performerRole"/>
<fmt:message key="performer.email" var="performerEmail"/>
<fmt:message key="performer.phone" var="performerPhone"/>
<fmt:message key="performer.id" var="performerID"/>
<fmt:message key="common.page.cancel" var="cancel"/>
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
    <c:when test="empty ${requestScope.workersData}">
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
                        <th scope="col">${registrationId}</th>
                        <th scope="col">${userId}</th>
                        <th scope="col">${street}</th>
                        <th scope="col">${houseNumber}</th>
                        <th scope="col">${apartmentNumber}</th>
                        <th scope="col">${description}</th>
                        <th scope="col">${orderStatus}</th>
                        <th scope="col">${performerID}</th>
                        <th scope="col">${performerRole}</th>
                        <th scope="col">${performerEmail}</th>
                        <th scope="col">${performerPhone}</th>

                        <th scope="col"></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="key" items="${requestScope.workersData.keySet()}">
                    <tr>

                        <td>${key.registrationId}</td>
                        <td>${key.userId}</td>
                        <td>${key.street}</td>
                        <td>${key.houseNumber}</td>
                        <td>${key.apartment}</td>
                        <td>${key.scopeOfWork}</td>
                        <td>${key.orderStatus}</td>
                        <td>${requestScope.workersData.get(key).userId}</td>
                        <td>${requestScope.workersData.get(key).role}</td>
                        <td>${requestScope.workersData.get(key).email}</td>
                        <td>${requestScope.workersData.get(key).phone}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=CANCEL_EMPLOYEE_ORDER&registrationId=${key.registrationId}&employeeId=${requestScope.workersData.get(key).userId}"
                               style="color: darkgreen">
                                    ${cancel}
                            </a>
                        </td>
                        </c:forEach>
                    </tbody>
                </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>

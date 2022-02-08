<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="employee.profile.page" var="employee"/>
<fmt:message key="pick.up.order" var="pickOrder"/>
<fmt:message key="employee.your.orders" var="yourOrders"/>
<fmt:message key="order.page.emptyList" var="emptyList"/>
<fmt:message key="order.registrationNumberId" var="registrationId"/>
<fmt:message key="common.page.street" var="street"/>
<fmt:message key="common.page.house.number" var="houseNumber"/>
<fmt:message key="common.page.house.apartment" var="apartmentNumber"/>
<fmt:message key="common.page.house.work.desirableTime" var="desirableTimeOfWork"/>
<fmt:message key="common.page.house.work.openingDate" var="openingDate"/>
<fmt:message key="common.page.house.work.description" var="description"/>
<fmt:message key="common.page.house.work.closingDate" var="closingDate"/>
<fmt:message key="common.page.house.work.orderStatus" var="orderStatus"/>
<fmt:message key="common.page.firstName" var="name"/>
<fmt:message key="common.page.lastName" var="userSurname"/>
<fmt:message key="common.page.email" var="email"/>
<fmt:message key="common.page.phone" var="phone"/>
<fmt:message key="order.user.id" var="userId"/>
<fmt:message key="common.page.cancel" var="cancel"/>
<fmt:message key="common.page.closeOrder" var="closeOrder"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <script type="text/javascript">
        window.history.forward();

        function noBack() {
            window.history.forward();
        }
    </script>
    <title>${employee}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<jsp:include page="../common/header.jsp"/>

<div class="container">
    <div class="row">
          <dt class="col-sm-4">&#9679; <a
                        href="${pageContext.request.contextPath}/controller?command=assign_performer_page"
                        style="color: midnightblue; font-size: 18px">${pickOrder}</a></dt>

        </dd>
    </div>

<p align="center">${yourOrders}</p>
<c:choose>
    <c:when test="empty ${requestScope.ordersMap}">
        <div class="container">
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
                        <th scope="col">${street}</th>
                        <th scope="col">${houseNumber}</th>
                        <th scope="col">${apartmentNumber}</th>
                        <th scope="col">${description}</th>
                        <th scope="col">${desirableTimeOfWork}</th>
                        <th scope="col">${openingDate}</th>
                        <th scope="col">${orderStatus}</th>
                        <th scope="col">${userId}</th>
                        <th scope="col">${name}</th>
                        <th scope="col">${userSurname}</th>
                        <th scope="col">${email}</th>
                        <th scope="col">${phone}</th>
                        <th scope="col">${cancel}</th>
                        <th scope="col">${closeOrder}</th>

                        <th scope="col"></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="key" items="${requestScope.ordersMap.keySet()}">
                        <tr>
                            <td>${key.registrationId}</td>
                            <td>${key.street}</td>
                            <td>${key.houseNumber}</td>
                            <td>${key.apartment}</td>
                            <td>${key.scopeOfWork}</td>
                            <td>${key.desirableTime}</td>
                            <td>${key.openingDate}</td>
                            <td>${key.orderStatus}</td>
                            <td>${requestScope.ordersMap.get(key).userId}</td>
                            <td>${requestScope.ordersMap.get(key).userName}</td>
                            <td>${requestScope.ordersMap.get(key).userSurname}</td>
                            <td>${requestScope.ordersMap.get(key).email}</td>
                            <td>${requestScope.ordersMap.get(key).phone}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=cancel_employee_order&registrationId=${key.registrationId}"
                                   style="color: darkgreen">
                                        ${cancel}
                                </a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=close_by_employee_order&registrationId=${key.registrationId}"
                                   style="color: darkgreen">
                                        ${closeOrder}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
    </c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>

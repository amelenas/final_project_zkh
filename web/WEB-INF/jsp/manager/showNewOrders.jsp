<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.allOrders" var="title"/>
<fmt:message key="order.page.emptyList" var="emptyList"/>
<fmt:message key="order.registrationNumberId" var="registrationId"/>
<fmt:message key="order.user.id" var="userId"/>
<fmt:message key="common.page.street" var="street"/>
<fmt:message key="common.page.house.number" var="houseNumber"/>
<fmt:message key="common.page.house.apartment" var="apartmentNumber"/>
<fmt:message key="common.page.house.work.desirableTime" var="desirableTimeOfWork"/>
<fmt:message key="common.page.house.work.openingDate" var="openingDate"/>
<fmt:message key="common.page.house.work.description" var="description"/>
<fmt:message key="common.page.house.work.closingDate" var="closingDate"/>
<fmt:message key="common.page.house.work.orderStatus" var="orderStatus"/>
<fmt:message key="assign.performer" var="assignPerformer"/>
<fmt:message key="pick.up.order" var="pickUpOrder"/>
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
                        <th scope="col">${registrationId}</th>
                        <th scope="col">${userId}</th>
                        <th scope="col">${street}</th>
                        <th scope="col">${houseNumber}</th>
                        <th scope="col">${apartmentNumber}</th>
                        <th scope="col">${description}</th>
                        <th scope="col">${desirableTimeOfWork}</th>
                        <th scope="col">${openingDate}</th>
                        <th scope="col">${closingDate}</th>
                        <th scope="col">${orderStatus}</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="key" items="${requestScope.orderData}">
                        <tr>

                            <td>${key.registrationId}</td>
                            <td>${key.userId}</td>
                            <td>${key.street}</td>
                            <td>${key.houseNumber}</td>
                            <td>${key.apartment}</td>
                            <td>${key.scopeOfWork}</td>
                            <td>${key.desirableTime}</td>
                            <td>${key.openingDate}</td>
                            <td>${key.closingDate}</td>
                            <td>${key.orderStatus}</td>
                            <c:choose>
                                <c:when test="${sessionScope.role=='ADMIN'}">
                                    <td>

                                        <a href="${pageContext.request.contextPath}/controller?command=assign_performer&registrationId=${key.registrationId}"
                                           style="color: darkgreen" >
                                                ${assignPerformer}
                                        </a>
                                    </td>
                                </c:when>
                                <c:when test="${sessionScope.role=='EMPLOYEE'}">
                                    <td>

                                        <a href="${pageContext.request.contextPath}/controller?command=take_order&registrationId=${key.registrationId}"
                                           style="color: darkgreen" >
                                                ${pickUpOrder}
                                        </a>
                                    </td>
                                </c:when>
                            </c:choose>
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
                                   href="${pageContext.request.contextPath}/controller?command=assign_performer_page&page=${requestScope.page-1}">
                                    <span aria-hidden="true">&laquo;</span>
                                    </c:if>
                                    <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                                    <c:choose>
                                    <c:when test="${requestScope.page eq i}">
                                    <a class="page-link">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=assign_performer_page&page=${i}">${i}</a>
                                    </c:otherwise>
                                    </c:choose>
                                    </c:forEach>

                                    <c:if test="${requestScope.phone lt requestScope.noOfPages}">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=assign_performer_page&page=${requestScope.page+1}">
                                        <span aria-hidden="true">&raquo;</span>&raquo;</a>
                                    </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="order.page.title" var="title"/>
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
                    <c:forEach var="element" items="${requestScope.orderData}">
                        <tr>

                            <td>${element.registrationId}</td>
                            <td>${element.street}</td>
                            <td>${element.houseNumber}</td>
                            <td>${element.apartment}</td>
                            <td>${element.scopeOfWork}</td>
                            <td>${element.desirableTime}</td>
                            <td>${element.openingDate}</td>
                            <td>${element.closingDate}</td>
                            <td>${element.orderStatus}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=cancel_single_order&registrationId=${element.registrationId}"
                                   style="color: darkgreen">
                                        ${cancel}
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
<jsp:include page="../common/footer.jsp"/>
</html>

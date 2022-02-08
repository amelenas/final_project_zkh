<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="assign.performer" var="assignPerformer"/>
<fmt:message key="assign.sitesOfWork.title" var="sitesOfWorkTitle"/>
<fmt:message key="assign.typeOfWork.title" var="typeOfWorkTitle"/>
<fmt:message key="order.registrationNumberId" var="registrationId"/>
<fmt:message key="order.user.id" var="userId"/>
<fmt:message key="common.page.street" var="street"/>
<fmt:message key="common.page.house.number" var="houseNumber"/>
<fmt:message key="common.page.house.apartment" var="apartmentNumber"/>
<fmt:message key="common.page.house.work.desirableTime" var="desirableTimeOfWork"/>
<fmt:message key="common.page.house.work.openingDate" var="openingDate"/>
<fmt:message key="common.page.house.work.description" var="description"/>
<fmt:message key="common.page.house.work.closingDate" var="closingDate"/>
<fmt:message key="common.page.firstName" var="name"/>
<fmt:message key="common.page.lastName" var="userSurname"/>
<fmt:message key="common.page.email" var="email"/>
<fmt:message key="common.page.phone" var="phone"/>
<fmt:message key="common.page.house.work.orderStatus" var="orderStatus"/>

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
    <title>${assignPerformer}</title>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
</header>
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
            <tr>
                <td>${requestScope.orderData.registrationId}</td>
                <td>${requestScope.orderData.street}</td>
                <td>${requestScope.orderData.houseNumber}</td>
                <td>${requestScope.orderData.apartment}</td>
                <td>${requestScope.orderData.scopeOfWork}</td>
                <td>${requestScope.orderData.desirableTime}</td>
                <td>${requestScope.orderData.openingDate}</td>
                <td>${requestScope.orderData.closingDate}</td>
                <td>${requestScope.orderData.orderStatus}</td>
            </tr>
        </table>
        <table class="table table-striped" style="height: inherit">
            <thead>
            <tr>
                <th scope="col">${userId}</th>
                <th scope="col">${name}</th>
                <th scope="col">${userSurname}</th>
                <th scope="col">${email}</th>
                <th scope="col">${phone}</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tr>
                <td>${requestScope.userId}</td>
                <td>${requestScope.name}</td>
                <td>${requestScope.userSurname}</td>
                <td>${requestScope.email}</td>
                <td>${requestScope.phone}</td>
            </tr>
        </table>
        <form action="${pageContext.request.contextPath}/controller?command=find_performer&registrationId=${requestScope.orderData.registrationId}" method="post">
            <select name="siteOfWork">
                <c:forEach var="item" items="${requestScope.sitesOfWork}">
                    <option value="${item.idSiteOfWork}">${item.nameOfRegion}</option>
                </c:forEach>
            </select>
            <br/>
            <select name="typeOfWorks">
                <c:forEach var="item" items="${requestScope.typesOfWorks}">
                    <option value="${item.idTypeOfWork}">${item.nameOfWork}</option>
                </c:forEach>
            </select>
            <br/>
            <button class="btn btn-primary" type="submit">${assignPerformer}</button>
        </form>
    </div>
</div>
</body>
</html>
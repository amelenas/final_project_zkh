<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="common.page.role" var="roleName"/>
<fmt:message key="common.page.firstName" var="userName"/>
<fmt:message key="common.page.lastName" var="userSurname"/>
<fmt:message key="common.page.email" var="userEmail"/>

<fmt:message key="admin.updateProfile" var="updateProfile"/>
<fmt:message key="admin.updatePassword" var="updatePassword"/>
<fmt:message key="admin.allOrders" var="allOrders"/>
<fmt:message key="admin.allOrders" var="allOrders"/>

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
    <title>${profile}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<jsp:include page="../common/header.jsp"/>
<div class="container" style="height: 80vh; color: black; font-size: 18px">

    <dl class="row">
        <dt class="col-sm-3">${roleName}</dt>
        <dd class="col-sm-9" style="font-size: 20px">${sessionScope.role}</dd>

        <dt class="col-sm-3">${userName}</dt>
        <dd class="col-sm-9">${sessionScope.name}</dd>

        <dt class="col-sm-3">${userSurname}</dt>
        <dd class="col-sm-9">${sessionScope.userSurname}</dd>

        <dt class="col-sm-3">${userEmail}</dt>
        <dd class="col-sm-9">${sessionScope.email}</dd>
    </dl>
</div>
<hr/>
<div class="row">
    <div class="btn-group">
        <button class="btn"><a href="${pageContext.request.contextPath}/controller?command=go_to_update_profile"
                               style="color: midnightblue; font-size: 18px">${updateProfile}</a></button>


        <button class="btn"><a href="${pageContext.request.contextPath}/jsp/update-password.jsp"
                               style="color: midnightblue; font-size: 18px">${updatePassword}</a>
        </button>
    </div>
    <c:choose>
        <c:when test="${sessionScope.role=='USER'}">
            <dd class="col-sm-9">

                <dl class="row">
                       <a href="${pageContext.request.contextPath}/controller?command=show_all_user_orders"
                        style="color: midnightblue; font-size: 18px">${allOrders}</a>
                </dl>
            </dd>
        </c:when>
    </c:choose>
    </dl>
</div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>

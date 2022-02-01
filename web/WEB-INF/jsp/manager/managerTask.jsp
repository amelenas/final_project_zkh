<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.profile.page" var="profile"/>
<fmt:message key="admin.updateProfile" var="updateProfile"/>
<fmt:message key="admin.updatePassword" var="updatePassword"/>
<fmt:message key="admin.allUsers" var="showAllUsers"/>
<fmt:message key="admin.allOrders" var="showAllOrders"/>
<fmt:message key="admin.addAdmin" var="addAdmin"/>
<fmt:message key="assign.performer" var="assignPerformer"/>

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
    <div class="row">
        <dd class="col-sm-9">
            <dl class="row">
                <dt class="col-sm-4">&#9679; <a href="${pageContext.request.contextPath}/jsp/admin/add-admin.jsp"
                                                style="color: midnightblue; font-size: 18px">${addAdmin}</a></dt>
            </dl>
        </dd>

        <dd class="col-sm-9">
            <dl class="row">
                <dt class="col-sm-4">&#9679; <a
                        href="${pageContext.request.contextPath}/controller?command=show_all_users"
                        style="color: midnightblue; font-size: 18px">${showAllUsers}</a></dt>
            </dl>
            <dl class="row">
                <dt class="col-sm-4">&#9679; <a
                        href="${pageContext.request.contextPath}/controller?command=show_all_orders"
                        style="color: midnightblue; font-size: 18px">${showAllOrders}</a></dt>
            </dl>
            <dl class="row">
                <dt class="col-sm-4">&#9679; <a
                        href="${pageContext.request.contextPath}/controller?command=assign_performer_page"
                        style="color: midnightblue; font-size: 18px">${assignPerformer}</a></dt>
            </dl>
        </dd>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>

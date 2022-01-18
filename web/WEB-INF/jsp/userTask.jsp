<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="order.page.title" var="title"/>
<fmt:message key="order.page.uploadButton" var="upload"/>
<fmt:message key="order.page.street" var="steet"/>
<fmt:message key="order.page.house.number" var="houseNumber"/>
<fmt:message key="order.page.house.work.description" var="workDescription"/>
<fmt:message key="order.page.house.work.desirableTime" var="desirableTime"/>
<fmt:message key="order.page.house.work.picture" var="picture"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
    <jsp:include page="common/header.jsp"/>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<div class="container" style="height: 80vh">
    <div class="row">
<form method="post" enctype="multipart/form-data"
      action="${pageContext.request.contextPath}/controller?command=save_photo">
    <input type="file" name="photo" accept="image/*"/> <br/>
    <input type="submit" value="${upload}"/>
    <p style="color: red;"> ${sessionScope.photo_message}
    <br>
</form>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register_order">

    <input type="hidden" name="redirectId" value="${param.redirectId}"/>

        <tr>
            <td>${steet}</td>
            <td><label>
                <input type="text" name="street" value="${order.street}"/>
            </label></td>
        </tr>
        <tr>
            <td>${houseNumber}</td>
            <td><label>
                <input type="text" name="houseNumber" value="${order.houseNumber}"/>
            </label></td>
        </tr>
        <tr>
            <td>${workDescription}</td>
            <td><label>
                <input type="text" name="scopeOfWork" value="${order.scopeOfWork}"/>
            </label></td>
        </tr>
        <tr>
            <td>${desirableTime}</td>
            <jsp:useBean id="now" class="java.util.Date" scope="page"/>
            <td><label>
                <input type="datetime-local"
                       min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd'T'HH:mm"/>"
                       name="desirableTimeOfWork" value="${order.desirableTimeOfWork}"/>
            </label></td>
        </tr>
        <tr>
            <td>${picture}</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/controller?command=show_main_page">Cancel</a>
            </td>
        </tr>
</form>
</div>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>

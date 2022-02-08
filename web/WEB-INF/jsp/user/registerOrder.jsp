<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="order.page.title" var="thanks"/>
<fmt:message key="order.page.uploadButton" var="upload"/>
<fmt:message key="common.page.street" var="steet"/>
<fmt:message key="common.page.house.number" var="houseNumber"/>
<fmt:message key="common.page.house.apartment" var="apartment"/>
<fmt:message key="common.page.house.work.description" var="workDescription"/>
<fmt:message key="common.page.house.work.desirableTime" var="desirableTime"/>
<fmt:message key="order.page.house.work.picture" var="picture"/>
<fmt:message key="order.page.house.work.requestNotNull" var="requestNotNull"/>
<fmt:message key="order.page.house.work.send" var="send"/>

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
    <jsp:include page="../common/header.jsp"/>
    <title>${thanks}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<div class="container" style="height: 80vh">
    ${picture}
        <p style="color: red;"> ${requestScope.photo_message}
        <p style="color: red;">${requestScope.errorRegisterOrderMessage}
        <p style="color: red;">${requestScope.successRegisterOrderMessage}
    <form method="post" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/controller?command=register_order">
        <input type="file" name="photo" accept="image/*"/>

    <br/>
        <input type="hidden" name="redirectId" value="${param.redirectId}"/>

        ${steet}
        <td><label>
            <input type="text" name="street" value="${requestScope.street}"/>
        </label></td>

        <br/>
        <tr>
            <td>${houseNumber}</td>
            <td><label>
                <input type="text" name="houseNumber" value="${requestScope.houseNumber}"/>
            </label></td>
        </tr>
        <tr>
            <td>${apartment}</td>
            <td><label>
                <input type="text" name="apartment" value="${requestScope.apartment}"/>
            </label></td>
        </tr>
        <br/>
        <tr>
            <td>${workDescription}</td>
            <br/>
            <td><label>
                <textarea required name="scopeOfWork" rows="5" cols="100"></textarea>
                <small id="helpBlock2" class="form-text text-muted">
                    ${requestNotNull}
                </small>
            </label></td>
        </tr>
        <br/>
        <tr>
            <td>${desirableTime}</td>
            <jsp:useBean id="now" class="java.util.Date" scope="page"/>
            <td><label>
                <input type="datetime-local"
                       min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd'T'HH:mm"/>"
                       name="desirableTimeOfWork" value="${requestScope.desirableTimeOfWork}"/>
            </label></td>
        </tr>
        <br/>
        <tr>
            <td colspan="2">
                <button class="btn btn-primary" type="submit">${send}</button>
                <a href="${pageContext.request.contextPath}/controller?command=show_main_page">Cancel</a>
            </td>
        </tr>
    </form>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>

</html>

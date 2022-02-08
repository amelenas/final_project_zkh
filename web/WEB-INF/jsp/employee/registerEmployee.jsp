<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="signUp.page" var="title"/>
<fmt:message key="signUp.button" var="signUp"/>
<fmt:message key="register.as.employee" var="employeeRegister"/>
<fmt:message key="register.employee.site.of.work" var="chooseSiteOfWork"/>
<fmt:message key="register.employee.type.of.work" var="chooseTypeOfWork"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
<header>
    <jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
</header>
<div class="container" style="height: 80vh">
    <br/>
    <div class="row">
        <form action="${pageContext.request.contextPath}/controller?command=register_employee" method="post">
            <h3>${chooseTypeOfWork}</h3>

            <c:forEach items="${requestScope.typesOfWorks}" var="item">
                <input type="checkbox" name="typeOfWorks"
                       value="<c:out value="${item.idTypeOfWork}"/>"/>
                <c:out value="${item.nameOfWork}"/><br>
            </c:forEach>

            <h3>${chooseSiteOfWork}</h3>

            <c:forEach items="${requestScope.sitesOfWork}" var="item">
                <input type="checkbox" name="siteOfWork"
                       value="<c:out value="${item.idSiteOfWork}"/>"/>
                <c:out value="${item.nameOfRegion}"/><br>
            </c:forEach>

            <button class="btn btn-primary" type="submit">${signUp}</button>
        </form>
    </div>
</div>


</body>
<div id="footer">
    <jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</div>
</html>

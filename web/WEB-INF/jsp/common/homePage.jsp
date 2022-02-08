<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="homePage.last.left.orders" var="lastOrders"/>
<fmt:message key="navigation.home" var="title"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
            crossorigin="anonymous"></script>

    <meta charset="UTF-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>


<div class="container bgcont">
    <h3>${lastOrders}</h3>
    <div class="row background-row"></div>
    <div class="row row-cols-1 row-cols-md-2 g-4">
        <c:forEach items="${requestScope.pictures}" var="imageName">
            <div class="col">
                <div class="card" style="width: 30rem; height: 30rem;">
                    <img src="${pageContext.request.contextPath}${imageName.key}" height="400" width="480"/>
                    <div class="card-body">
                        <p class="card-text">${imageName.value}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</div>
</body>
</html>
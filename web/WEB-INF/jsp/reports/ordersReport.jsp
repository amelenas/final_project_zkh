<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="table" uri="/WEB-INF/tld/tables.tld" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="report.allOrders.title" var="thanks"/>
<fmt:message key="common.backHome" var="toHomePage"/>
<fmt:message key="admin.profile.page" var="managerPage"/>
<fmt:message key="admin.report.all.page" var="titleReport"/>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">

    <title>${thanks}</title>
</head>
<body>
<header>
    <jsp:include page="../common/header.jsp"/>

    <a href="${pageContext.request.contextPath}/controller?command=show_main_page">${toHomePage}</a>
    <br/>
    <a href="${pageContext.request.contextPath}/controller?command=manager_task">${managerPage}</a>
    <br/>
    ${titleReport}
</header>
<div align=center>
    <jsp:useBean id="oderBean" class="by.stepanovich.zkh.tablebuild.ReportSet" scope="request"/>
    <table:orderTable set="${oderBean}"/>
    <br/>
</body>
<footer>
    <jsp:include page="../common/footer.jsp"/>
</footer>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<fmt:message key="request.home" var="title"/>
<fmt:message key="request.message" var="message"/>
<fmt:message key="request.to.home.page" var="homePage"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
${message}
<td colspan ="2">
    <a href="${pageContext.request.contextPath}/controller?command=show_main_page">${homePage}</a>
</td>
</body>
</html>

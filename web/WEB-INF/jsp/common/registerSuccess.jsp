<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="login.register.success" var="title"/>
<fmt:message key="common.backHome" var="toHomePage"/>
<fmt:message key="common.logIn" var="logIn"/>

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
  <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<header><jsp:include page="header.jsp"/></header>
<br/>

<div class="container" style="height: 68vh; color: black; justify-content: center">
  <h3>${title}</h3>
<br/>
  <a href="${pageContext.request.contextPath}/controller?command=show_main_page">${toHomePage}</a>
  <br/>
  <a href="${pageContext.request.contextPath}/controller?command=log_in">${logIn}</a>
</div>
  </body>

<footer><jsp:include page="footer.jsp"/></footer>
</html>

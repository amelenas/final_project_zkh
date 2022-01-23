<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<fmt:message key="request.home" var="title"/>
<fmt:message key="request.message" var="message"/>
<fmt:message key="request.to.home.page" var="homePage"/>
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
    <title>${title}</title>

</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

${message}
<td colspan="2">
    <a href="${pageContext.request.contextPath}/controller?command=show_main_page">${homePage}</a>
</td>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>

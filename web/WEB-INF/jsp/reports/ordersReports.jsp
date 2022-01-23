<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="table" uri="/WEB-INF/tld/tariff.tld" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>TariffTable</title>
</head>
<body>

<jsp:useBean id="oderBean" class="by.stepanovich.zkh.tablebuild.TableBuilder" scope="request" />

<table:jspset set="${oderBean}" />
<br/>
</body>
</html>
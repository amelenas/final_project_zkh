<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.homePage" var="homePage"/>
<!DOCTYPE html>
<html lang="en">
<head>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">


    <title>Home Page</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>

<jsp:include page="common/header.jsp"/>

<h3>Home Page</h3>

<div class="container">
    <div class="row" style="justify-content: space-between">
        <div class="card" style="width: 15rem;">
            <img src="https://pbs.twimg.com/media/EJRy4BmXsAAV1rk.jpg:large" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://www.syzran-small.ru/images/news/20160731-lavki1.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://progorod58.ru/userfiles/images/image-12-2014/dsc6574.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://avatars.mds.yandex.net/get-zen_doc/22526/pub_604004394b4c7530a6232618_604009388f2f5b70bfde6bf1/scale_1200" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://pbs.twimg.com/media/EJRy4BmXsAAV1rk.jpg:large" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://www.syzran-small.ru/images/news/20160731-lavki1.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://progorod58.ru/userfiles/images/image-12-2014/dsc6574.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://avatars.mds.yandex.net/get-zen_doc/22526/pub_604004394b4c7530a6232618_604009388f2f5b70bfde6bf1/scale_1200" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">HERE WILL BE SOME TEXT.</p>
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
</body>
</html>
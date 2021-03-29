<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<div class="container">
    <h1>Delete Ad by Id</h1>
    <div class="container">
        <h1>Here Are all the ads!</h1>

        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6">
                <p>${ad.id}</p>
                <h2>${ad.title}</h2>
                <p>${ad.description}</p>
            </div>
        </c:forEach>
    </div>
    <form action="/ads/delete" method="post">
        <div class="form-group">
            <label for="adId">ad_ID</label>
            <input id="adId" name="adId" class="form-control" type="text">
        </div>

        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
        <h1>Here are all of your ads!</h1>
    </div>
    <div class ="card" style ="width: 18rem;">
        <div class ="card-body">
            <div class ="col-md-6 row">
                <div>
                    <h6 class ="card-subtitle mb-2"><c:out value="${userad.title}"/></h6>
                    <p class = "card-text"><c:out value="${userad.description}"/></p>
                </div>
                <form action="/updateAdServlet" method="post">
                    <div class="form-group">
                        <br>
                        <label for="Id">Update Ad</label>
                        <input id = "Id" name="Id" class="form-control" hidden type="text" value="${userad.id}">
                        <label for="title">update title</label>
                        <input id="title" name="title" class="form-control"  type="text"  value="${userad.title}">
                        <label for="descript">update description</label>
                        <input id="descript" name="descript" class="form-control"  type="text"  value="${userad.description}">
                    </div>
                </form>
            </div>
        </div>

    </div>


</body>
</html>

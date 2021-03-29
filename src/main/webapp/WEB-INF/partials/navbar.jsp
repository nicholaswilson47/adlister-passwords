<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <a class ="navbar-brand" href="/ads">Nicholas' Adlister</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${sessionScope.user == null}">
            <div class = "m-1">
                <li class="nav-item ">
                <a class ="nav-link" href="/login">Login</a>
                </li>
            </div>
            </c:if>
            <c:if test="${sessionScope.user == null}">
            <div class = "m-1">
            <li class="nav-item">
                <a class ="nav-link" href="/register">Register</a>
            </li>
            </div>
            </c:if>
            <c:if test="${sessionScope.user == null}">
            <div class = "m-1">
            <li class="nav-item">
                <a class ="nav-link" href="/ads/create">Create an Ad</a>
            </li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <div class = "m-1">
            <li class="nav-item">
                <a class ="nav-link" href="/profile">Your Profile</a>
            </li>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                    <div class = "m-1">
            <li class="nav-item">
                <a class ="nav-link" href="/edit-profile">Edit Profile</a>
            </li>
                    </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                        <div class = "m-1">
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
                        </div>
            </c:if>
        </ul>
    </div>
</nav>

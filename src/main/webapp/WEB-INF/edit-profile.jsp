<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<div class="container">
    <h2>Edit your profile information here.</h2>
    <form action="/edit-profile" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="password">Current Password</label>
            <input id="password" name="password" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="newPassword">New Password</label>
            <input id="newPassword" name="newPassword" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm New Password</label>
            <input id="confirmPassword" name="confirmPassword" class="form-control" type="password">
        </div>
        <input type="submit" class ="btn btn-primary btn-block">
    </form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Create</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
    <div class="container mt-5">
        <h1 class="text-center mb-5">Register</h1>
        <p class="text-center">
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
        <p>
            <span id="requiredField"><b>Field with * are required</b></span>
        </p>
        <form action="usercreate" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username: *</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password: *</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email: *</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
            </div>
            <div class="mb-3">
                <label class="form-label d-block">Role: *</label>
                <div class="btn-group" role="group" aria-label="Role">
                    <input class="btn-check" type="radio" name="role" id="role-admin" value="ADMIN" autocomplete="off">
                    <label class="btn btn-outline-primary" for="role-admin">Admin</label>
                    <input class="btn-check" type="radio" name="role" id="role-user" value="USER" autocomplete="off">
                    <label class="btn btn-outline-primary" for="role-user">User</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

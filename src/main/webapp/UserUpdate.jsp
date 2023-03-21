<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
    <div class="container">
        <h1 class="mt-5 mb-3">Update User</h1>
        <form action="userupdate" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">UserName</label>
                <input type="text" class="form-control" id="username" name="username" value="${fn:escapeXml(param.username)}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">New Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">New Email</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <button type="submit" class="btn btn-primary">Update User</button>
        </form>
        <div class="mt-5">
            <p>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>
        </div>
    </div>
</body>
</html>

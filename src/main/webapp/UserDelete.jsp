<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
    <jsp:include page="NavBar.jsp" />
    <div class="container mt-4">
        <h1>User Delete</h1>
        
        <table class="table">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user" >
                    <tr>
                        <td>${user.getUserName()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getRole().name()}</td>
                        <td>
                            <form action="userdelete" method="post" id="deleteUserForm">
                                <input type="hidden" name="userId" value="${user.getUserId()}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><strong>${title}</strong></p>
    </div>

    <script>
        document.getElementById("deleteUserForm").addEventListener("submit", function() {
            // Send an HTTP GET request to the current URL to refresh the page
            window.location.href = window.location.href;
        });
    </script>
</body>
</html>

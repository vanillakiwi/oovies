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
        <h1>${messages.title}</h1>
        <form action="userdelete" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="${fn:escapeXml(param.username)}">
            </div>
            <button type="submit" class="btn btn-primary" <c:if test="${messages.disableSubmit}">disabled</c:if>>Delete</button>
        </form>
        
        <h1>Users</h1>
	
        <form action="findusers" method="post">
	        <table border="1">
	            <tr>
	                <th>UserName</th>
	                <th>Role</th>      
	                <th>Delete</th>	                
	            </tr>
	            <c:forEach items="${persons}" var="person" >
	                <tr>
	                    <td><c:out value="${person.getUserName()}" /></td>
	                    <td><c:out value="${person.getRole()}" /></td>               
	                    <td><a href="userdelete?username=<c:out value="${person.getUserName()}"/>">Delete</a></td>
	                 
	                </tr>
	            </c:forEach>
	       </table>
       </form>
    </div>
</body>
</html>
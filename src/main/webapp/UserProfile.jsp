<%-- Retrieve the admin's username from the session --%>
<%
String username = (String) session.getAttribute("username");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Profile</title>
    </head>
    <body>
    	<jsp:include page="NavBar.jsp" />
    	<h1>Welcome <%= username %>!</h1>
                
        <form action="userupdate" method="post">
        	<button type="submit" class="btn btn-primary me-3">Update Profile</button>
        </form>
        
        <form action="logout" method="post">
        	<button type="submit" class="btn btn-primary mt-2">Logout</button>
        </form>
        
    </body>
</html>

<%-- Retrieve the admin's username from the session --%>
<%
String username = (String) session.getAttribute("username");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Profile</title>
    </head>
    <body>
    	<jsp:include page="NavBar.jsp" />
    	<h1>Welcome <%= username %>!</h1>
        
        <form action="usercreate" method="post">
        	<button type="submit" class="btn btn-primary me-3">Create User</button>
        </form>
                
        <form action="userupdate" method="post">
        	<button type="submit" class="btn btn-primary me-3">Update User</button>
        </form>
        
        <form action="userdelete" method="get">
        	<button type="submit" class="btn btn-primary me-3">Delete User</button>
        </form>
                
        <form action="moviecreate" method="get">
        	<button type="submit" class="btn btn-primary me-3">Create movie</button>
        </form>
                
        <form action="movieupdate" method="post">
        	<button type="submit" class="btn btn-primary me-3">Update movie</button>
        </form>

        <form action="moviedelete" method="post">
        	<button type="submit" class="btn btn-primary me-3">Delete movie</button>
        </form>
        
        <form action="logout" method="post">
        	<button type="submit" class="btn btn-primary mt-2">Logout</button>
        </form>
        
    </body>
</html>

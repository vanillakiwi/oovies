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
		<div class="container-fluid text-end">
    		<label class="ms-3 mt-3">Welcome Admin <%= username %>!</label>
    	</div>
    	<h1 class="text-center mt-2">Admin Dashboard</h1>
    	<h4 class="ms-3 mt-2">Manage Users</h4>
		<div class="d-flex">
	        <form action="usercreate" method="post">
	        	<button type="submit" class="btn btn-outline-primary me-3 ms-3 mb-3">Create User</button>
	        </form>
	                
	        <form action="userupdate" method="post">
	        	<button type="submit" class="btn btn-outline-primary me-3">Update User</button>
	        </form>
	        
	        <form action="userdelete" method="get">
	        	<button type="submit" class="btn btn-outline-primary me-3 mb-3">Delete User</button>
	        </form>
        </div> 
        
        <h4 class="ms-3 mt-2">Manage Movies</h4>    
        <div class="d-flex">
	        <form action="moviecreate" method="get">
	        	<button type="submit" class="btn btn-outline-info me-3 ms-3">Create movie</button>
	        </form>
	                
	        <form action="movieupdate" method="post">
	        	<button type="submit" class="btn btn-outline-info me-3">Update movie</button>
	        </form>
	
	        <form action="moviedelete" method="post">
	        	<button type="submit" class="btn btn-outline-info me-3 mb-3">Delete movie</button>
	        </form>
	     </div> 
	    
	    <%-- TODO --%>
	    <h4 class="ms-3 mt-2">Manage Reviews</h4>    
        <div class="d-flex">
	        <form action="reviewdelete" method="post">
	        	<button type="submit" class="btn btn-outline-secondary me-3 ms-3 mb-3">Delete review</button>
	        </form>
	     </div> 
	     
	    <h4 class="ms-3 mt-2">Manage Ratings</h4>    
        <div class="d-flex">
	
	        <form action="ratingdelete" method="post">
	        	<button type="submit" class="btn btn-outline-dark me-3 ms-3">Delete rating</button>
	        </form>
	     </div> 
    </body>
</html>

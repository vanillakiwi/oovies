<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
	    	<a class="navbar-brand" href="FindMovies.jsp">Oovies</a>
		    <ul class="nav">
				<li class="nav-item">
			    	<a class="nav-link" href="FindMovies.jsp">Home</a>
			  	</li>
		        <li class="nav-item">
				    <a class="nav-link" href="<c:if test='${sessionScope.loggedIn == true and sessionScope.role == "USER"}'>/Oovies/userprofile</c:if>
										      <c:if test='${sessionScope.loggedIn == true and sessionScope.role == "ADMIN"}'>AdminProfile.jsp</c:if>
										      <c:if test='${sessionScope.loggedIn != true}'>Login.jsp</c:if>">
					    Profile
					</a>
				</li>
		        <c:if test="${sessionScope.loggedIn != true}">
			        <li class="nav-item">
					    <a class="nav-link" href="Login.jsp">
					        Login
					    </a>
			        </li>
			        <li class="nav-item">
					    <a class="nav-link" href="UserCreate.jsp">
					        Register
					    </a>
			        </li>
		        </c:if>
		        <c:if test="${sessionScope.loggedIn == true}">
			        <li class="nav-item">
					    <form method="post" action="logout">
			    			<button type="submit" class="btn btn-outline-danger btn-sm mt-1">Logout</button>
			  			</form>
			        </li>
		        </c:if>
			</ul>
		</div>
	</nav>
</html>

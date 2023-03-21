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
			    	<a class="nav-link" href="UserCreate.jsp">User Create</a>
			  	</li>
			  	<li class="nav-item">
			    	<a class="nav-link" href="UserUpdate.jsp">User Update</a>
			  	</li>
			 	<li class="nav-item">
			    	<a class="nav-link" href="UserDelete.jsp">User Delete</a>
			  	</li>
				<li class="nav-item">
		          	<a class="nav-link" href="MovieCreate.jsp">Movie Create</a>
		        </li>
		        <li class="nav-item">
		        	<a class="nav-link" href="MovieUpdate.jsp">Movie Update</a>
		        </li>
		        <li class="nav-item">
		          	<a class="nav-link" href="MovieDelete.jsp">Movie Delete</a>
		        </li>
			</ul>
		</div>
	</nav>
</html>
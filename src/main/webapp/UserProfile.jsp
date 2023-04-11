<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%-- Retrieve the admin's username from the session --%>
<%
String username = (String) session.getAttribute("username");
%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Profile</title>
    </head>
    <body>
    	<jsp:include page="NavBar.jsp" />
		<div class="container-fluid text-end">
    		<label class="ms-3 mt-3">Welcome User <%= username %>!</label>
    	</div>
    	<h1 class="text-center mt-2">User Dashboard</h1>
    	<h4 class="ms-3 mt-2">Profile</h4>
		<div class="d-flex"> 
	        <form action="userupdate" method="post">
	        	<button type="submit" class="btn btn-outline-primary me-3 ms-3 mb-2">Update Profile</button>
	        </form>
        </div> 
	    
	    <!--    
	    <form action="userprofile" method="post">
	    <button type="submit" class="btn btn-outline-primary me-3 ms-3 mb-2">Profile</button>
	    </form>
	    -->  
	     
	    <h4 class="ms-3 mt-2">Loves</h4>    
        <table class="table ms-3 me-3 mb-3">
			<thead>
				<tr>
					<th scope="col">LoveId</th>
					<th scope="col">Movie</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${loves}" var="love" >
					<tr>
					
						<td><c:out value="${love.getLoveId()}" /></td>
						<td><a href="moviedetails?id=${love.getMovie().getMovieId()}"><c:out value="${love.getMovie().getMovieId()}" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	     
	     
	     
	    <h4 class="ms-3 mt-2">Ratings</h4>    
        <table class="table ms-3 me-3 mb-3">
			<thead>
				<tr>
					<th scope="col">RatingId</th>
					<th scope="col">Score</th>
					<th scope="col">Movie</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ratings}" var="rating" >
					<tr>
						<td><c:out value="${rating.getRatingId()}" /></td>
						<td><c:out value="${rating.getScore()}" /></td>
						<td><a href="moviedetails?id=${rating.getMovie().getMovieId()}"><c:out value="${rating.getMovie().getMovieId()}" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	     
	    <h4 class="ms-3 mt-2">Reviews</h4>    
        <table class="table ms-3 me-3 mb-3">
			<thead>
				<tr>
					<th scope="col">ReviewId</th>
					<th scope="col">Created</th>
					<th scope="col">Content</th>
					<th scope="col">Movie</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reviews}" var="review" >
					<tr>
						<td><c:out value="${review.getReviewId()}" /></td>
						<td><c:out value="${review.getCreated()}" /></td>
						<td><c:out value="${review.getContent()}" /></td>
						<td><a href="moviedetails?id=${review.getMovie().getMovieId()}"><c:out value="${review.getMovie().getMovieId()}" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

    </body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Find Movies</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="container my-5">
		<c:if test="${sessionScope.loggedIn == true}">
			<div class="container-fluid text-end">
		    	<label class="ms-3 mt-3">Welcome <%= username %></label>
		    </div>
		</c:if>

		<form action="findmovies" method="post">
			<h1 class="mb-4">Search movies</h1>
			
			<div class="row">
				<div class="col-md-3 form-group mr-2">
	      			<input type="text" name="title" class="form-control" placeholder="Search by title">
	   			</div>
	    		<div class="col-md-3 form-group mr-2">
	      			<select name="genre" class="form-control">
	        			<option value="">All genres</option>
	        			<option value="DRAMA">Drama</option>
	        			<option value="COMEDY">Comedy</option>
	        			<option value="ACTION">Action</option>
	        			<option value="FANTASY">Fantasy</option>
	        			<option value="HORROR">Horror</option>
	        			<option value="ROMANCE">Romance</option>
	        			<option value="WESTERN">Western</option>
	        			<option value="THRILLER">Thriller</option>
	        			<option value="ANIMATION">Animation</option>
	      			</select>
	    		</div>
	    		<div class="col-md-2 form-group mr-2">
	      			<input type="text" name="year" class="form-control" placeholder="Year">
	    		</div>
	   			<div class="col-md-2 form-group mr-2">
	   				<select name="rating" class="form-control">
			        	<option value="">All ratings</option>
			        	<option value="5.0">5.0</option>
			        	<option value="4.0">4.0 - 4.9</option>
			        	<option value="3.0">3.0 - 3.9</option>
			        	<option value="2.0">2.0 - 2.9</option>
			        	<option value="1.0">1.0 - 1.9</option>
			      	</select>
			    </div>
				<div class="col-md-2">
					<button type="submit" class="btn btn-primary mt-2">Submit</button>
				</div>
			</div>
		</form>
		<br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		<br/><br/>
		<h2 class="mb-2">Matching Movies</h2>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">MovieId</th>
					<th scope="col">Title</th>
					<th scope="col">ReleaseDate</th>
					<th scope="col">Rating</th>
					<th scope="col">Duration</th>
					<th scope="col">Summary</th>
					<th scope="col">Genre</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movies}" var="movie" >
					<tr>
						<td><a href="moviedetails?id=${movie.getMovieId()}"><c:out value="${movie.getMovieId()}" /></a></td>
						<td><c:out value="${movie.getTitle()}" /></td>
						<td><c:out value="${movie.getReleaseDate()}" /></td>
						<td><c:out value="${movie.getRating()}" /></td>
						<td><c:out value="${movie.getDuration()}" /></td>
						<td><c:out value="${movie.getSummary()}" /></td>
						<td><c:out value="${movie.getGenre()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Search results pagination">
		    <ul class="pagination justify-content-center">
		        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
		            <a class="page-link" href="?page=${currentPage - 1}&amp;resultsPerPage=${resultsPerPage}" tabindex="-1">Previous</a>
		        </li>
		        <c:choose>
		            <c:when test="${totalPages <= 5}">
		                <c:forEach begin="1" end="${totalPages}" var="pageNum">
		                    <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
		                        <a class="page-link" href="?page=${pageNum}&amp;resultsPerPage=${resultsPerPage}">${pageNum}</a>
		                    </li>
		                </c:forEach>
		            </c:when>
		            <c:otherwise>
		                <c:set var="startPage" value="${currentPage - 2}" />
		                <c:set var="endPage" value="${currentPage + 2}" />
		                <c:if test="${startPage < 1}">
		                    <c:set var="startPage" value="1" />
		                    <c:set var="endPage" value="${startPage + 4}" />
		                </c:if>
		                <c:if test="${endPage > totalPages}">
		                    <c:set var="endPage" value="${totalPages}" />
		                    <c:set var="startPage" value="${endPage - 4}" />
		                </c:if>
		                <c:if test="${currentPage > 3}">
		                    <li class="page-item">
		                        <a class="page-link" href="?page=1&amp;resultsPerPage=${resultsPerPage}">1</a>
		                    </li>
		                    <li class="page-item disabled">
		                        <span class="page-link">...</span>
		                    </li>
		                </c:if>
		                <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
		                    <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
		                        <a class="page-link" href="?page=${pageNum}&amp;resultsPerPage=${resultsPerPage}">${pageNum}</a>
		                    </li>
		                </c:forEach>
		                <c:if test="${currentPage <= totalPages - 3}">
		                    <li class="page-item disabled">
		                        <span class="page-link">...</span>
		                    </li>
		                    <li class="page-item">
		                        <a class="page-link" href="?page=${totalPages}&amp;resultsPerPage=${resultsPerPage}">${totalPages}</a>
		                    </li>
		                </c:if>
		            </c:otherwise>
		        </c:choose>
		        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
		            <a class="page-link" href="?page=${currentPage + 1}&amp;resultsPerPage=${resultsPerPage}">Next</a>
		        </li>
		    </ul>
		</nav>
	</div>
</body>
</html>
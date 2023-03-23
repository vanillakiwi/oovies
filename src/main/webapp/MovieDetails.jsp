<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Movie Details</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="container my-5">
		<h2 class="mb-0">Movie Details</h2>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">MovieId</th>
					<th scope="col">Title</th>
					<th scope="col">ReleaseDate</th>
					<th scope="col">Rating</th>
					<th scope="col">Duration</th>
					<th scope="col">Summary</th>
					<th scope="col">DirectorId</th>
					<th scope="col">StudioId</th>
					<th scope="col">Genre</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${movie.getMovieId()}" /></td>
						<td><c:out value="${movie.getTitle()}" /></td>
						<td><c:out value="${movie.getReleaseDate()}" /></td>
						<td><c:out value="${movie.getRating()}" /></td>
						<td><c:out value="${movie.getDuration()}" /></td>
						<td><c:out value="${movie.getSummary()}" /></td>
						<td><c:out value="${movie.getDirector().getDirectorId()}" /></td>
						<td><c:out value="${movie.getStudio().getStudioId()}" /></td>
						<td><c:out value="${movie.getGenre()}" /></td>
					</tr>
			</tbody>
		</table>
		
		<h4 class="mb-2">Casts for <c:out value="${movie.getTitle()}" /></h4>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ActorId</th>
					<th scope="col">Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${actors}" var="actor" >
					<tr>
						<td><c:out value="${actor.getActorId()}" /></td>
						<td><c:out value="${actor.getName()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h4 class="mb-2">Reviews for <c:out value="${movie.getTitle()}" /></h4>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ReviewId</th>
					<th scope="col">Created</th>
					<th scope="col">Content</th>
					<th scope="col">UserId</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reviews}" var="review" >
					<tr>
						<td><c:out value="${review.getReviewId()}" /></td>
						<td><c:out value="${review.getCreated()}" /></td>
						<td><c:out value="${review.getContent()}" /></td>
						<td><c:out value="${review.getUser().getUserId()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${sessionScope.loggedIn == true}">
		    <h4 class="mb-2">Post a new review for <c:out value="${movie.getTitle()}" /></h4>
		    <form class="p-3 border rounded" method="post" action="${pageContext.request.contextPath}/moviedetails">
		        <input type="hidden" name="userId" value="${sessionScope.user.getUserId()}" />
		        <div class="mb-3">
		            <label for="content" class="form-label">Review</label>
		            <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
		        </div>
		        <button type="submit" class="btn btn-primary">Submit</button>
		    </form>
		    <% if (request.getAttribute("error") != null) { %>
		        <div class="alert alert-danger mt-3"><%= request.getAttribute("errorMessage") %></div>
		    <% } %>
		</c:if>		
	</div>
</body>
</html>
<script>
    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
</script>

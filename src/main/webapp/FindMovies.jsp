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
	<title>Find Movies</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<div class="container my-5">
		<form action="findmovies" method="post">
			<h1 class="mb-4">Search for movies by Title</h1>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="title" name="title" value="${fn:escapeXml(param.title)}">
				<label for="title">Title</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</form>
		<br/>
		<div id="userCreate"><a href="usercreate" class="btn btn-outline-secondary">Create User</a></div>
		<br/>
		<h1 class="mb-4">Matching Movies</h1>
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
				<c:forEach items="${movies}" var="movie" >
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
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>


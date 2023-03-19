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
			<h1 class="mb-4">Search movies</h1>
			
			<div class="form-group mr-2">
      			<input type="text" name="title" class="form-control" placeholder="Search by title">
   			</div>
    		<div class="form-group mr-2">
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
    		<div class="form-group mr-2">
      			<input type="text" name="year" class="form-control" placeholder="Year">
    		</div>
   			<div class="form-group mr-2">
   				<select name="rating" class="form-control">
		        	<option value="">All ratings</option>
		        	<option value="5.0">5.0</option>
		        	<option value="4.0">4.0 - 4.9</option>
		        	<option value="3.0">3.0 - 3.9</option>
		        	<option value="2.0">2.0 - 2.9</option>
		        	<option value="1.0">1.0 - 1.9</option>
		      	</select>
		    </div>
			<button type="submit" class="btn btn-primary mt-2">Submit</button>
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


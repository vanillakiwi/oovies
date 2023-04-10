<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Top Rated Movies</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<h2 class="mt-2 mb-2 ms-2">Top Rated Movies</h2>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">MovieId</th>
				<th scope="col">Title</th>
				<th scope="col">Rating</th>
				<th scope="col">Genre</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${topRatedMovies}" var="movie" >
	    		<tr>
	    			<td><a href="moviedetails?id=${movie.getMovieId()}"><c:out value="${movie.getMovieId()}" /></a></td>
		        	<td>${movie.getTitle()}</td>
	        		<td>${movie.getRating()}</td>
	        		<td><c:out value="${movie.getGenre()}" /></td>
	    		</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
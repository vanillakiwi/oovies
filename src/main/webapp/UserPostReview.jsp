<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Create review</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
	<jsp:include page="NavBar.jsp" />
	<div class="container my-5">
		<h2 class="text-center">Post new review</h2>
		<form action="userpostreview" method="post" class="container mt-4">
			<label class="col-sm-2 col-form-label" for="username" id="username">Username: <c:out value="${username}"/></label>
			<label class="col-sm-2 col-form-label" for="movieId" id="movieId">Movie ID: <span id="movieIdSpan"></span></label>
			<label for="reviewContent" class="col-sm-2 col-form-label">Review Content</label>
			<textarea class="form-control" id="reviewContent" name="reviewContent" rows="5"
			        placeholder="Enter your review"></textarea>
			<button type="submit" class="btn btn-primary mt-2">Submit</button>
		</form>
	</div>
	<script>
	  // Get the stored movieId value from session storage
	  var movieId = sessionStorage.getItem('movieId');
	  // Set the value of the span element to the movieId
	  document.getElementById('movieIdSpan').innerHTML = movieId;
	  // Set the value of the hidden input field to the movieId
	  document.getElementById('movieId').value = movieId;
	</script>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Delete Ratings</title>
<link rel="stylesheet"
	href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="container my-5">
		<form action="ratingdelete" method="post">
			<h1 class="text-center mb-4">Delete Ratings</h1>
			<label class="form-label mt-4">Rating Id</label>
			<div class="form-group mr-2">
				<input id="ratingid" type="text" name="ratingid"
					class="form-control" placeholder="Please Enter the Rating Id">
			</div>


			<button type="submit" class="btn btn-primary mt-2">Submit</button>
		</form>
		<br /> <span id="successMessage"><b>${messages.title}</b></span> <br />
		<br />

	</div>
</body>
</html>
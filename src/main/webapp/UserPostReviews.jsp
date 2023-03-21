<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Review</title>
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
  <%-- Display any success or error messages --%>
  <% if (request.getAttribute("success") != null) { %>
    <p style="color: green"><%= request.getAttribute("success") %></p>
  <% } else if (request.getAttribute("error") != null) { %>
    <p style="color: red"><%= request.getAttribute("error") %></p>
  <% } %>

  <%-- Display the new review form --%>
  <h1>New Review</h1>
	 <form class="p-3 border rounded">
	  <h2 class="mb-3">New Review</h2>
	  <div class="mb-3">
	    <label for="movieTitle" class="form-label">Title</label>
	    <input type="text" class="form-control" id="movieTitle" name="movieTitle">
	  </div>
	  <div class="mb-3">
	    <label for="review" class="form-label">Review</label>
	    <textarea class="form-control" id="review" name="review" rows="5"></textarea>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>

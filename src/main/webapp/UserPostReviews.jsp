<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Review</title>
  	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
  <%-- Display any success or error messages --%>
  <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success"><%= request.getAttribute("success") %></div>
  <% } else if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
  <% } %>

  <%-- Display the new review form --%>
  <div class="container mt-5">
    <h1 class="text-center">New Review</h1>
	 <form class="p-3 border rounded" method="post" action="${pageContext.request.contextPath}/userpostreviews">
	  <div class="mb-3">
	    <label for="content" class="form-label">Review</label>
	    <textarea class="form-control" id="content" name="content" rows="5"></textarea>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
  </div>
</body>
</html>

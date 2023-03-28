<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">
						<h1 class="card-title text-center">Login</h1>
					</div>
					<div class="card-body">
						<form method="post" action="login">
							<div class="mb-3">
								<label for="username" class="form-label">Username:</label>
								<input type="text" id="username" name="username" class="form-control" required>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password:</label>
								<input type="password" id="password" name="password" class="form-control" required>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</form>
						<% if (request.getAttribute("errorMessage") != null) { %>
							<div class="alert alert-danger mt-3"><%= request.getAttribute("errorMessage") %></div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

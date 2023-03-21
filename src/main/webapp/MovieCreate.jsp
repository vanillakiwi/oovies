<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Movie Create</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
    <jsp:include page="NavBar.jsp" />
    <div class="container mt-5">
        <h1 class="text-center mb-5">Create Movies</h1>
        <p class="text-center">
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
        <p>
            <span id="requiredField"><b>Field with * are required</b></span>
        </p>
        <form action="moviecreate" method="post"> 
	        <div class="form-group">
			  <label class="form-label mt-4">Movie Details</label>
			  <div class="form-floating mb-3">
			    <input type="text" class="form-control" id="title" name= "title" placeholder="Toy Story" required>
			    <label for="title">Movie Title: *</label>
			  </div>
			  
	          <div class="form-floating mb-3">
	          	<input type="text" class="form-control" id="duration" name="duration" placeholder="120" required>
	          	<label for="duration" class="form-label">Duration: *</label>
	          </div>
	          
			    <div class="form-floating mb-3">
			    	<input type="text" class="form-control" id="directorId" name="directorId" placeholder="1" required>
	                <label for="directorId" class="form-label">Director ID: *</label>
	            </div>
	             <div class="form-floating mb-3">
	                <input type="text" class="form-control" id="studioId" name="studioId" placeholder="1" required>
	            	<label for="studioId" class="form-label">Studio ID: *</label>
	            </div>
	            
	            <div class="form-floating mb-3">
	                <input type="text" class="form-control" id="releasedate" name="releasedate" placeholder="yyyy-MM-dd" required>
	           		<label for="releasedate" class="form-label">Release Date (yyyy-MM-dd): *</label>
	            </div>
	            
	            <div>
			  		<label for="rating">Rating: *</label>
			  		<input type="number" id="rating" name= "rating" step="0.10" min="0.00" max="5.00" required>
			  	</div>
			  
	            <div>
	            <label for="genre" class="form-label mt-4">Genre: *</label>
			      <select class="form-select" name="genre" id="genre" required>
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
	            <div>
		            <label for="summary" class="form-label mt-4">Summary: </label>
				    <textarea class="form-control" id="summary" name="summary" rows="3"></textarea>
			    </div>
			</div>
			<button type="submit" class="btn btn-primary">Create</button>
		</form>
    </div>
	
    <script src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
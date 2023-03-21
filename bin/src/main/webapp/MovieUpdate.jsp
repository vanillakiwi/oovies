<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Update Movies</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
<jsp:include page="NavBar.jsp" />
<div class="container my-5">
    <form action="movieupdate" method="post">
        <h1 class="text-center mb-4">Update movies's summary</h1>
		<label class="form-label mt-4">Movie Id</label>
        <div class="form-group mr-2">
            <input id="movieid" type="text" name="movieid" class="form-control" placeholder="Please Enter the Movie Id">
        </div>
        <label class="form-label mt-4">New Movie Summary</label>
        <div class="form-group mr-2">
            <input id="summary" type="text" name="summary" class="form-control" placeholder="Please Enter the New Summary">
        </div>

        <button type="submit" class="btn btn-primary mt-2">Submit</button>
    </form>
    <br/>
    <span id="successMessage"><b>${messages.success}</b></span>
    <br/><br/>

</div>
</body>
</html>
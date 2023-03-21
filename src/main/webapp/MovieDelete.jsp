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
    <title>Delete Movies</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
<jsp:include page="NavBar.jsp" />
<div class="container my-5">
    <form action="moviedelete" method="post">
        <h1 class="text-center mb-4">Delete Movies</h1>
		<label class="form-label mt-4">Movie Id</label>
        <div class="form-group mr-2">
            <input id="movieid" type="text" name="movieid" class="form-control" placeholder="Please Enter the Movie Id">
        </div>


        <button type="submit" class="btn btn-primary mt-2">Submit</button>
    </form>
    <br/>
    <span id="successMessage"><b>${messages.title}</b></span>
    <br/><br/>

</div>
</body>
</html>
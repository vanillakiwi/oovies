<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String username = (String) session.getAttribute("username");


%>    

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Movie Details</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>

	<jsp:include page="NavBar.jsp" />

	
	<div class="container my-5">
		<h2 class="mb-0">Movie Details</h2>
		<form action="moviedetails" method="post">
		  <input type="hidden" name="id" value="${movie.getMovieId()}">
		  <input type="hidden" name="pageIndex" value="${pageIndex}">
		  <input type="hidden" name="username" value="${username}">
		 
		  
		    <button type="submit" name="like" value="like" >  
		    
		        <span id="like" style="color: rgb(180, 0, 0);">
		             &#10084;&nbsp;
		        </span>
		        <span id="sumLike">
		            ${likes}
		        </span>
		    </button>
		</form>

		<c:set var="maxPage" value="${maxPage}"/>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">MovieId</th>
					<th scope="col">Title</th>
					<th scope="col">ReleaseDate</th>
					<th scope="col">Rating</th>
					<th scope="col">Duration</th>
					<th scope="col">Summary</th>
					<th scope="col">Director</th>
					<th scope="col">Studio</th>
					<th scope="col">Genre</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${movie.getMovieId()}" /></td>
						<td><c:out value="${movie.getTitle()}" /></td>
						<td><c:out value="${movie.getReleaseDate()}" /></td>
						<td><c:out value="${movie.getRating()}" /></td>
						<td><c:out value="${movie.getDuration()}" /></td>
						<td><c:out value="${movie.getSummary()}" /></td>
						<td><c:out value="${movie.getDirector().getName()=='-1'?'N/A':movie.getDirector().getName()}" /></td>
						<td><c:out value="${movie.getStudio().getName()=='None'?'N/A':movie.getStudio().getName()}" /></td>
						<td><c:out value="${movie.getGenre()}" /></td>
					</tr>
			</tbody>
		</table>
		
		<h4 class="mb-2">Casts for <c:out value="${movie.getTitle()}" /></h4>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ActorId</th>
					<th scope="col">Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${actors}" var="actor" >
					<tr>
						<td><c:out value="${actor.getActorId()}" /></td>
						<td><c:out value="${actor.getName()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="row">
			<h4 class="mb-2">Reviews for <c:out value="${movie.getTitle()}" /></h4>
			<c:if test="${sessionScope.loggedIn == true}">
				<a href="userpostreview?id=${movie.getMovieId()}" onclick="setMovieId('${movie.getMovieId()}')">Post a review</a>
				<a href="userpostrating?id=${movie.getMovieId()}" onclick="setMovieId('${movie.getMovieId()}')">Post a rating</a>
			</c:if>
			<script>
				function setMovieId(movieId) {
				  sessionStorage.setItem('movieId', movieId);
				}
			</script>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ReviewId</th>
					<th scope="col">Created</th>
					<th scope="col">Content</th>
					<th scope="col">UserId</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reviews}" var="review" >
					<tr>
						<td><c:out value="${review.getReviewId()}" /></td>
						<td><c:out value="${review.getCreated()}" /></td>
						<td><c:out value="${review.getContent()}" /></td>
						<td><c:out value="${review.getUser().getUserId()}" /></td>
						
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
        <nav>
            <ul class="pagination justify-content-center">
                <li ><a class="page-link" href="<c:url value="/moviedetails?id=${movie.getMovieId()}&pageIndex=${pageIndex-1>0?pageIndex-1:1}"/>">Previous</a></li>
                
 
                <c:forEach begin="1" end="${maxPage<10&&maxPage>0?maxPage-1:maxPage<=0?0:9}" varStatus="loop">
                    <c:set var="active" value="${loop.index==pageIndex?'active':''}"/>
                    <li class="page-item" class="${active}"><a class="page-link"
                            href="<c:url value="/moviedetails?id=${movie.getMovieId()}&pageIndex=${loop.index}"/>">${loop.index}</a>
                    </li>
                </c:forEach>
<%				
				Object max = request.getAttribute("maxPage") == null ? 0: request.getAttribute("maxPage");
				int maxPage = (int)max;
                
                if (maxPage != 0) { 
%>
                <li class="page-item" class="${active}"><a class="page-link"
                            href="<c:url value="/moviedetails?id=${movie.getMovieId()}&pageIndex=${maxPage}"/>">${maxPage}</a>
                </li>
<%
      }
 %>
                
                
                
                <li><a class="page-link" href="<c:url value="/moviedetails?id=${movie.getMovieId()}&pageIndex=${pageIndex<maxPage?pageIndex+1:maxPage}"/>">Next</a></li>
            </ul>
        </nav>
    
			
	</div>
</body>
</html>

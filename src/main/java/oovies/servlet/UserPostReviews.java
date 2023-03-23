package oovies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oovies.dal.ReviewsDao;
import oovies.model.Movie;
import oovies.model.Person;
import oovies.model.Reviews;

@WebServlet("/userpostreviews")
public class UserPostReviews extends HttpServlet {

  private static final long serialVersionUID = 1L;
  protected ReviewsDao reviewsDao;

  public void init() throws ServletException {
    reviewsDao = ReviewsDao.getInstance();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Display the new review form
    request.getRequestDispatcher("/UserPostReviews.jsp").forward(request, response);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    // Create a new review object from form parameters
	    Date now = new Date();
	    String content = req.getParameter("content");
	    HttpSession session = req.getSession(false);
	    
	    Person user = (Person) session.getAttribute("user");
	    if (user == null) {
	        req.setAttribute("error", "You must be logged in to post a review.");
	        req.getRequestDispatcher("/UserPostReviews.jsp").forward(req, resp);
	        return;
	    }
	    
	    Movie movie = (Movie) session.getAttribute("movie");
	    if (movie == null) {
	        req.setAttribute("error", "No movie selected.");
	        req.getRequestDispatcher("/UserPostReviews.jsp").forward(req, resp);
	        return;
	    }
	    
	    Reviews review = new Reviews(now, content, user, movie);
	    
	    if (content == null || content.trim().isEmpty()) {
	        req.setAttribute("error", "Review content cannot be empty.");
	        req.getRequestDispatcher("/UserPostReviews.jsp").forward(req, resp);
	        return;
	    }
	    
	    // Save the new review to the database
	    try {
	        reviewsDao.create(review);
	        req.setAttribute("success", "Review posted successfully.");
	        resp.sendRedirect("movie-details?id=" + movie.getMovieId());
	    } catch (SQLException e) {
	        e.printStackTrace();
	        req.setAttribute("error", "Unable to post review. Please try again later.");
	        req.getRequestDispatcher("/UserPostReviews.jsp").forward(req, resp);
	    }
	}
}

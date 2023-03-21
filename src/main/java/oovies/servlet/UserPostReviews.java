package oovies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oovies.dal.ReviewsDao;
import oovies.model.Movie;
import oovies.model.Person;
import oovies.model.Reviews;

@WebServlet("/userpostreviews")
public class UserPostReviews extends HttpServlet {

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
    Person user = (Person) req.getSession().getAttribute("user");
    Movie movie = (Movie) req.getSession().getAttribute("movie");
    Reviews review = new Reviews(now, content, user, movie);

    // Save the new review to the database
    try {
      reviewsDao.create(review);
      req.setAttribute("success", "Review posted successfully.");
      req.getRequestDispatcher("/UserPostReviews.jsp").forward(req, resp);
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("error", "Unable to post review. Please try again later.");
      req.getRequestDispatcher("/UserPostReviews.jsp").forward(req, resp);
    }
  }
}

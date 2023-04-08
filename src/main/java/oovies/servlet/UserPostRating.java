package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/userpostrating")
public class UserPostRating extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PersonDao personDao;
    private MovieDao movieDao;
    private RatingDao ratingDao;
    private int movieId;

    @Override
    public void init() throws ServletException {
    	
        super.init();
        personDao = PersonDao.getInstance();
        movieDao = MovieDao.getInstance();
        ratingDao = RatingDao.getInstance();
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        movieId = Integer.parseInt(req.getParameter("id"));
        
        req.getRequestDispatcher("/UserPostRating.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	Map<String, String[]> map = req.getParameterMap();
     	
        String username = (String) req.getSession().getAttribute("username");
        
        double score = Double.parseDouble(req.getParameter("score"));
        
        Person user;
        Movie movie;
        Rating rating;

        try {
            user = personDao.getPersonByUserName(username);
            movie = movieDao.getMovieById(movieId);

            if (user == null || movie == null) {
                throw new Exception("Invalid user or movie ID.");
            }

            rating = new Rating(score, user, movie);
            ratingDao.create(rating);

            resp.sendRedirect(req.getContextPath() + "/moviedetails?id=" + movieId);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/UserPostRating.jsp").forward(req, resp);
        }
    }
}
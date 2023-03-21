package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/moviedetails")
public class MovieDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MovieDao movieDao;
	private ReviewsDao reviewsDao;
	private ActorDao actorDao;

	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
		actorDao = ActorDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

        String movieId = req.getParameter("id");
        
        Movie movie;
        
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Actor> actors = new ArrayList<Actor>();
        
        try {
			movie = movieDao.getMovieById(Integer.parseInt(movieId));
			reviews = reviewsDao.getReviewsByMovieId(Integer.parseInt(movieId));
			actors = actorDao.getActorsByMovieId(Integer.parseInt(movieId));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		req.setAttribute("movie", movie);
		req.setAttribute("reviews", reviews);
		req.setAttribute("actors", actors);
		req.getRequestDispatcher("/MovieDetails.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

        String movieId = req.getParameter("id");
        
        Movie movie;
        
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Actor> actors = new ArrayList<Actor>();
        
        try {
			movie = movieDao.getMovieById(Integer.parseInt(movieId));
			reviews = reviewsDao.getReviewsByMovieId(Integer.parseInt(movieId));
			actors = actorDao.getActorsByMovieId(Integer.parseInt(movieId));
			messages.put("success", "Displaying details for movie: " + movie.getTitle());
			messages.put("success", "Displaying casts for: " + movie.getTitle());
			messages.put("success", "Displaying reviews for: " + movie.getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		req.setAttribute("movie", movie);
		req.setAttribute("reviews", reviews);
		req.setAttribute("actors", actors);
		req.getRequestDispatcher("/MovieDetails.jsp").forward(req, resp);

    }
}
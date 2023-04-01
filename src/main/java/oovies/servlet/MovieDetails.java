package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    protected PersonDao personDao;
    protected RatingDao ratingDao;
    protected LoveDao loveDao;
    @Override
    public void init() throws ServletException {
        movieDao = MovieDao.getInstance();
        reviewsDao = ReviewsDao.getInstance();
        actorDao = ActorDao.getInstance();
        personDao = PersonDao.getInstance();
        ratingDao = RatingDao.getInstance();
        loveDao = LoveDao.getInstance();
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String movieId = req.getParameter("id");
       
        Movie movie;
        int maxPage = 1;
        int page;
        int loveCount = 0;
      
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Actor> actors = new ArrayList<Actor>();
             
        try {
        	page = req.getParameter("pageIndex") != null ? Integer.parseInt(req.getParameter("pageIndex")) : 1;
            movie = movieDao.getMovieById(Integer.parseInt(movieId));
            reviews = reviewsDao.queryByMovieId(page, Integer.parseInt(movieId));
//            reviews = reviewsDao.getReviewsByMovieId(Integer.parseInt(movieId));
            actors = actorDao.getActorsByMovieId(Integer.parseInt(movieId));
            maxPage = reviewsDao.getMaxPage(5, Integer.parseInt(movieId));
         
                     
            
            
            loveCount = loveDao.getLoveCountByMovieId(Integer.parseInt(movieId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.setAttribute("movie", movie);
        req.setAttribute("reviews", reviews);
        req.setAttribute("actors", actors);
        req.setAttribute("maxPage", maxPage);
        req.setAttribute("pageIndex", page);
        req.setAttribute("likes", loveCount);
        req.getRequestDispatcher("/MovieDetails.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        int movieId = Integer.parseInt(req.getParameter("id"));
        String like = req.getParameter("like");
        String username = req.getParameter("username");
        int loveCount = 0;
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Actor> actors = new ArrayList<Actor>();
        List<Rating> ratings = new ArrayList<Rating>();
        Person user = null;
        
        
        
        Movie movie = null;
        if(like != null) {
        	
        	try {
        	movie = movieDao.getMovieById(movieId);
        	if(username != null) {
            	 user = personDao.getPersonByUserName(username);
            }
        	
        	if(user != null) {
            	List<Love> list = loveDao.getLoveByUserId(user.getUserId());
            	if(list.size() == 0) {
            		loveDao.create(new Love(user, movie));
            	}
            	else {
            		loveDao.delete(list.get(0));
            	}
            	
            }
            
            loveCount = loveDao.getLoveCountByMovieId(movieId);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        	finally {
        		req.setAttribute("movie", movie);
                req.setAttribute("reviews", reviews);
                req.setAttribute("actors", actors);
                req.setAttribute("likes", loveCount);
                resp.sendRedirect("/Oovies/moviedetails?id=" + movieId);
        	}
        }
        
        else {
        	
        
        // Get user ID if logged in
        user = (Person) req.getSession().getAttribute("user");
        int userId = user.getUserId();
        
        // Add post review hyperlink with parameter passing
        String postReviewUrl = "/usercreatereview?movieid=" + movieId;
        postReviewUrl += "&userid=" + userId;
        req.setAttribute("postReviewUrl", postReviewUrl);
        
        Rating rating = null;
        double score = Double.parseDouble(req.getParameter("rating"));

        // Check if user has already rated this movie
        try {
			ratings = ratingDao.getRatingByUserId(userId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        for (Rating r : ratings) {
            if (r.getUser().getUserId() == userId && r.getMovie().getMovieId() == movieId) {
            	req.setAttribute("error", "You have already rated this movie.");
            	return;
            }
    	}
        try {
            movie = movieDao.getMovieById(movieId);
            reviews = reviewsDao.getReviewsByMovieId(movieId);
            actors = actorDao.getActorsByMovieId(movieId);
            
            rating = new Rating(score, user, movie);
            ratingDao.create(rating);
            
            
            resp.sendRedirect("/movieDetails?id=" + movieId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        } catch (Exception e) {
			
			e.printStackTrace();
		}
        
        req.setAttribute("movie", movie);
        req.setAttribute("reviews", reviews);
        req.setAttribute("actors", actors);
        
        req.getRequestDispatcher("/MovieDetails.jsp").forward(req, resp);
        }

    }
}

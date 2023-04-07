package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/userprofile")
public class UserProfile extends HttpServlet {

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

        String username = req.getParameter("username"); 
        System.out.println("username is " + username);
        
        int userId = 0;
		try {
			userId = personDao.getUserIdByUserName(username);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("userId is " + userId);
       
       
        List<Love> loves = new ArrayList<>();
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Rating> ratings = new ArrayList<>();
             
        try {      	
        	loves = loveDao.getLoveByUserId(userId);
            ratings = ratingDao.getRatingByUserId(userId);
            reviews = reviewsDao.getReviewsByUserId(userId);  
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.setAttribute("loves", loves);
        req.setAttribute("reviews", reviews);
        req.setAttribute("ratings", ratings);
 
        req.getRequestDispatcher("/UserProfile.jsp").forward(req, resp);
    }
    
}
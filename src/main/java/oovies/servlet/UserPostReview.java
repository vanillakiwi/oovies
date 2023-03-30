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

@WebServlet("/userpostreview")
public class UserPostReview extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReviewsDao reviewsDao;
    private PersonDao personDao;
    private MovieDao movieDao;
    private int movieId;

    @Override
    public void init() throws ServletException {
    	
        super.init();
        reviewsDao = ReviewsDao.getInstance();
        personDao = PersonDao.getInstance();
        movieDao = MovieDao.getInstance();
       
        
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        movieId = Integer.parseInt(req.getParameter("id"));
        
        req.getRequestDispatcher("/UserPostReview.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	Map<String, String[]> map = req.getParameterMap();
//     	for(String s: map.keySet()) System.out.println(s);
     	
    	
        String username = (String) req.getSession().getAttribute("username");
        
        String content = req.getParameter("reviewContent");

//        System.out.format("%d %s %s", movieId, username, content);
        
        Date now = new Date();
        Person user;
        Movie movie;
        Reviews review;

        try {
            user = personDao.getPersonByUserName(username);
            movie = movieDao.getMovieById(movieId);

            if (user == null || movie == null) {
                throw new Exception("Invalid user or movie ID.");
            }

            review = new Reviews(now, content, user, movie);
            reviewsDao.create(review);

            resp.sendRedirect(req.getContextPath() + "/moviedetails?id=" + movieId);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/UserPostReview.jsp").forward(req, resp);
        }
    }
}
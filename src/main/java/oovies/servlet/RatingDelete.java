package oovies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oovies.dal.RatingDao;
import oovies.model.Rating;

@WebServlet("/ratingdelete")
public class RatingDelete extends HttpServlet {
	
	protected RatingDao ratingDao;
	
	@Override
	public void init() throws ServletException {
		ratingDao = RatingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		messages.put("title", "Delete Rating");
		req.getRequestDispatcher("/RatingDelete.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		
		String ratingId = req.getParameter("ratingid");
		if (ratingId == null) {
			messages.put("title", "Invalid Rating Id");
        	messages.put("disableSubmit", "true");
        } else {
        	Rating rating = new Rating(Integer.valueOf(ratingId));
        	try {
        		Rating currentRating = ratingDao.getRatingById(Integer.valueOf(ratingId));
        		rating = ratingDao.delete(rating);
				if(currentRating == null && rating == null){
					messages.put("title", "Rating does not exist.");
				}
        		else if (rating == null) {
        			messages.put("title", "Successfully deleted " + currentRating.getRatingId());
        			messages.put("disableSubmit", "true");
        		} else {
        			messages.put("title", "Failed to delete " + currentRating.getRatingId());
		        	messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/RatingDelete.jsp").forward(req, resp);
	}
}

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

import oovies.dal.*;
import oovies.model.Reviews;

@WebServlet("/reviewdelete")
public class ReviewDelete extends HttpServlet {
	
	protected ReviewsDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		messages.put("title", "Delete Review");
		req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		
		String reviewId = req.getParameter("reviewid");
		if (reviewId == null) {
			messages.put("title", "Invalid Review Id");
        	messages.put("disableSubmit", "true");
        } else {
        	Reviews review = new Reviews(Integer.valueOf(reviewId));
        	try {
        		Reviews currentReview = reviewDao.getReviewById(Integer.valueOf(reviewId));
        		review = reviewDao.delete(review);
				if(currentReview == null && review == null){
					messages.put("title", "Review does not exist.");
				}
        		else if (review == null) {
        			messages.put("title", "Successfully deleted " + currentReview.getReviewId());
        			messages.put("disableSubmit", "true");
        		} else {
        			messages.put("title", "Failed to delete " + currentReview.getReviewId());
		        	messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
	}
}

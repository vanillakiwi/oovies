package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;
import oovies.model.Movie.Genre;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/movieupdate")
public class MovieUpdate extends HttpServlet {
	
	protected MovieDao movieDao;
	
	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		
		// Retrieve and validate movie title and genre
		String movieId = req.getParameter("movieid");
		if (movieId == null) {
			messages.put("success", "Please enter a valid movie id.");
		} else {
			try {
				Movie movie = movieDao.getMovieById(Integer.valueOf(movieId));
				if (movie == null) {
					messages.put("success", "Movie does not exist.");
				}
				req.setAttribute("movie", movie);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
		}
		req.getRequestDispatcher("/MovieUpdate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        
        String movieId = req.getParameter("movieid");
		if (movieId == null) {
			messages.put("success", "Invalid Movie ID.");
		} else {
			try {
				Movie movie = movieDao.getMovieById(Integer.valueOf(movieId));
				if (movie == null) {
					messages.put("success", "Movie does not exist.");
				} else {
					String newSummary = req.getParameter("summary");
					movie = movieDao.updateSummary(movie, newSummary);
					messages.put("success", "Successfully updated summary for movie " + movie.getTitle());
				}
				req.setAttribute("movie", movie);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }	
		}
		req.getRequestDispatcher("/MovieUpdate.jsp").forward(req, resp);
	}
}
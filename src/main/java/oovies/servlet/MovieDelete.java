package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/moviedelete")
public class MovieDelete extends HttpServlet {

	protected MovieDao movieDao;

	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		messages.put("title", "Delete Movie");
		req.getRequestDispatcher("/MovieDelete.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate movie id
        String movieId = req.getParameter("movieid");
        if (movieId == null) {
        	messages.put("title", "Invalid Movie Id");
        	messages.put("disableSubmit", "true");
        } else {
        	Movie movie = new Movie(Integer.valueOf(movieId));
        	try {
				Movie currentMovie = movieDao.getMovieById(Integer.valueOf(movieId));
        		movie = movieDao.delete(movie);
				if(currentMovie == null && movie == null){
					messages.put("title", "Movie does not exist.");
				}
        		else if (movie == null) {
        			messages.put("title", "Successfully deleted " + currentMovie.getTitle());
        			messages.put("disableSubmit", "true");
        		} else {
        			messages.put("title", "Failed to delete " + currentMovie.getTitle());
		        	messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/MovieDelete.jsp").forward(req, resp);
	}
}
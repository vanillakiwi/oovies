package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FindMovie is the primary entry point into the application.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running oovies.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/Oovies/findmovies.
 */
@WebServlet("/findmovies")
public class FindMovies extends HttpServlet {
	
	protected MovieDao movieDao;
	
	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Movie> movies = new ArrayList<Movie>();
        
        // Retrieve and validate movie title.
        // movie title is retrieved from the URL query string.
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	// Retrieve Movie, and store as a message.
        	try {
            	movies = movieDao.getMovieByTitle(title);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        	messages.put("previousTitle", title);
        }
        req.setAttribute("movies", movies);
        
        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Movie> movies = new ArrayList<Movie>();
        
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	// Retrieve Movies, and store as a message.
        	try {
        		movies = movieDao.getMovieByTitle(title);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        }
        req.setAttribute("movies", movies);
        
        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
    }
}

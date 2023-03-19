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

@WebServlet("/findmovies")
public class FindMovies extends HttpServlet {
	
    private MovieDao movieDao;

    @Override
    public void init() throws ServletException {
        movieDao = MovieDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Movie> movies = new ArrayList<Movie>();
    	
        String title = req.getParameter("title");
        String genreStr = req.getParameter("genre");
        String yearStr = req.getParameter("year");
        String ratingStr = req.getParameter("rating");
        
        Movie.Genre genre = null;
        if (genreStr != null && !genreStr.isEmpty()) {
            genre = Movie.Genre.valueOf(genreStr);
        }
        
        int year = 0;
        if (yearStr != null && !yearStr.isEmpty()) {
            year = Integer.parseInt(yearStr);
        }
        
        double rating = 0.0;
        if (ratingStr != null && !ratingStr.isEmpty()) {
            rating = Double.parseDouble(ratingStr);
        }
       
        if (title == null || title.isEmpty()) {
        	messages.put("success", "Please enter a valid title.");
        	return;
        } else {
            try {
                movies = movieDao.getMovieByTitle(title, genre, year, rating);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
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
        String genreStr = req.getParameter("genre");
        String yearStr = req.getParameter("year");
        String ratingStr = req.getParameter("rating");
        
        if (title == null || title.isEmpty()) {
        	messages.put("success", "Please enter a valid title.");
        	messages.put("disableSubmit", "true");
        }
        
        Movie.Genre genre = null;
        if (genreStr != null && !genreStr.isEmpty()) {
            genre = Movie.Genre.valueOf(genreStr);
        }
        
        int year = 0;
        if (yearStr != null && !yearStr.isEmpty()) {
            year = Integer.parseInt(yearStr);
        }
        
        double rating = 0.0;
        if (ratingStr != null && !ratingStr.isEmpty()) {
            rating = Double.parseDouble(ratingStr);
        } else {
            try {
                movies = movieDao.getMovieByTitle(title, genre, year, rating);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
    }
}

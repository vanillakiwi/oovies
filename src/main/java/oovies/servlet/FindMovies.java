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

@WebServlet("/findmovies")
public class FindMovies extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MovieDao movieDao;

	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<Movie> movies = new ArrayList<Movie>();

	    String title = req.getParameter("title");
	    String genreStr = req.getParameter("genre");
	    String yearStr = req.getParameter("year");
	    String ratingStr = req.getParameter("rating");
	    String pageStr = req.getParameter("pageIndex");
//	    String resultsPerPageStr = req.getParameter("resultsPerPage");

//	    int page = 1;
	    int resultsPerPage = 20;
	    int page = 1;
	    int maxPage;

	    if (pageStr != null && !pageStr.isEmpty()) {
	        page = Integer.parseInt(pageStr);
	    }

//	    if (resultsPerPageStr != null && !resultsPerPageStr.isEmpty()) {
//	        resultsPerPage = Integer.parseInt(resultsPerPageStr);
//	    }
	    
	    int offset = (page - 1) * resultsPerPage;

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

	    try {
	    	movies = movieDao.getMovieByAdvancedSearch(title, genre, year, rating, offset, resultsPerPage);
	    	maxPage = movieDao.getMovieSizeByAdvancedSearch(title, genre, year, rating,resultsPerPage);
	        int totalMovies = movies.size();
	        int totalPages = (int) Math.ceil((double) totalMovies / resultsPerPage);
	        messages.put("success", "Displaying results for: " + title);
	        req.setAttribute("movies", movies);
	        req.setAttribute("maxPage", maxPage);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	    
	    req.setAttribute("pageIndex", page);
	    req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<Movie> movies = new ArrayList<Movie>();

	    String title = req.getParameter("title");
	    String genreStr = req.getParameter("genre");
	    String yearStr = req.getParameter("year");
	    String ratingStr = req.getParameter("rating");

	    // get pagination parameters from request
	    int currentPage = 1;
	    int resultsPerPage = 20;

	    if (req.getParameter("page") != null) {
	        currentPage = Integer.parseInt(req.getParameter("page"));
	    }
	    if (req.getParameter("resultsPerPage") != null) {
	        resultsPerPage = Integer.parseInt(req.getParameter("resultsPerPage"));
	    }

	    // calculate offset based on current page and results per page
	    int offset = (currentPage - 1) * resultsPerPage;
	    int limit = resultsPerPage;

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

	    try {
	        movies = movieDao.getMovieByAdvancedSearch(title, genre, year, rating, offset, limit);
	        int totalMovies = movies.size();
	        int totalPages = (int) Math.ceil((double) totalMovies / resultsPerPage);
	        messages.put("success", "Displaying results for Title: " + title + " Genre: " + genreStr + " Year: " + yearStr
	                + " Rating: " + ratingStr);
	        req.setAttribute("movies", movies);
	        req.setAttribute("currentPage", currentPage);
	        req.setAttribute("resultsPerPage", resultsPerPage);
	        req.setAttribute("totalPages", totalPages);
	        req.setAttribute("totalMovies", totalMovies);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
	   
	    req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
	}

}
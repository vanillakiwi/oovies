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

	    int page = 1;
	    int moviesPerPage = 20;

	    if (pageStr != null && !pageStr.isEmpty()) {
	        page = Integer.parseInt(pageStr);
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
	    }

	    try {
	    	int totalMovies = movieDao.getMovieSizeByAdvancedSearch(title, genre, year, rating, moviesPerPage);
	        int maxPage = (int) Math.ceil((double) totalMovies / moviesPerPage);
	    	
	        if (page < 1) {
	            page = 1;
	        } else if (page > maxPage) {
	            page = maxPage;
	        }

	        int offset = moviesPerPage * (page - 1);

	        movies = movieDao.getMovieByAdvancedSearch(title, genre, year, rating, offset, moviesPerPage);
	        messages.put("success", "Displaying results for Title: " + title + " Genre: " + genreStr + " Year: " + yearStr
	                + " Rating: " + ratingStr);
	        
	        req.setAttribute("movies", movies);
	        req.setAttribute("maxPage", maxPage);
	        req.setAttribute("pageIndex", page);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }

	    req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
	}
}
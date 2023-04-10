package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/topratedmovies")
public class TopRatedMovies extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MovieDao movieDao;

    public void init() {
        movieDao = MovieDao.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	List<Movie> topRatedMovies = new ArrayList<>();
    	
    	try {
    	    topRatedMovies = movieDao.getTopRatedMovies();
    	    for (Movie movie : topRatedMovies) {
    	        System.out.format("%s", movie.getTitle());
    	    }
    	    request.setAttribute("topRatedMovies", topRatedMovies);
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    response.sendError(500, "An unexpected error occurred while retrieving top movies.");
    	}

    	System.out.println("Number of top rated movies: " + topRatedMovies.size());

    	request.getRequestDispatcher("/TopRatedMovies.jsp").forward(request, response);
    }
}

package oovies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oovies.dal.*;
import oovies.model.*;
import oovies.model.Movie.Genre;


@WebServlet("/moviecreate")
public class MovieCreate extends HttpServlet {
	
	protected MovieDao movieDao;
	protected DirectorDao directorDao;
	protected StudioDao studioDao;
	protected Set<Genre> movieGenre = EnumSet.allOf(Genre.class);
	
	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException{
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		req.getRequestDispatcher("/MovieCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		
		// Retrieve and validate movie title and genre
		String title = req.getParameter("title");
		Genre genre = Genre.valueOf(req.getParameter("genre"));
		if (title == null || title.trim().isEmpty()) {
			messages.put("success", "Title cannot be empty.");
		} else if (!movieGenre.contains(genre)){
			messages.put("success", "Input genre does not exist.");
		} else {
			// Retrieve other attributes of movie
			double rating = Double.valueOf(req.getParameter("rating"));
			int duration = Integer.valueOf(req.getParameter("duration"));
			String summary = req.getParameter("summary");
			int directorId = Integer.valueOf(req.getParameter("directorId"));
			int studioId = Integer.valueOf(req.getParameter("studioId"));
			// Release date must be in the format yyyy-mm-dd
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate = req.getParameter("releasedate");
			Date releaseDate = new Date();
			try {
				releaseDate = dateFormat.parse(stringDate);
			} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
			
			// Create the movie
			try {
				Movie movie = new Movie(
						title, releaseDate, rating, duration, summary,
						directorDao.getDirectorByDirectorId(directorId),
						studioDao.getStudioById(studioId), genre);
				movie = movieDao.create(movie);
				messages.put("success", "Successfully created " + title);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
		}
		req.getRequestDispatcher("/MovieCreate.jsp").forward(req, resp);
	}
}
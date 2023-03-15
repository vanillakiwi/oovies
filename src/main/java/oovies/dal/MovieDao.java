package oovies.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oovies.model.*;




public class MovieDao {
	
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
		private static MovieDao instance = null;
		protected  MovieDao() {
			connectionManager = new ConnectionManager();
		}
		public static MovieDao getInstance() {
			if(instance == null) {
				instance = new MovieDao();
			}
			return instance;
		}
		
		
		
		
		/**
		 * Save the Movie instance by storing it in your MySQL instance.
		 * This runs a INSERT statement.
		 */
		
		public Movie create(Movie movie) throws SQLException {
			String insertMovie = "INSERT INTO Movie(Title,ReleaseDate,Rating,Duration,Summary,DirectorId,StudioId,Genre) "
					+ "VALUES(?,?,?,?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertMovie,
						Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, movie.getTitle());
				insertStmt.setDate(2, new Date(movie.getReleaseDate().getTime()));
				insertStmt.setDouble(3, movie.getRating());
				insertStmt.setInt(4, movie.getDuration());
				insertStmt.setString(5, movie.getSummary());
				insertStmt.setInt(6, movie.getDirector().getDirectorId());
				insertStmt.setInt(7, movie.getStudio().getStudioId());
				insertStmt.setString(8, movie.getGenre().name());

	
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int movieId = -1;
				if(resultKey.next()) {
					movieId = resultKey.getInt(1);
				} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
				}
				movie.setMovieId(movieId);
				
				return movie;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(insertStmt != null) {
					insertStmt.close();
				}
			}
		}
		
		
		
		/**
		 * Delete the Movie instance.
		 * This runs a DELETE statement.
		 */
		public Movie delete(Movie movie) throws SQLException {
			String deleteMovie = "DELETE FROM Movie WHERE MovieId=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deleteMovie);
				deleteStmt.setInt(1, movie.getMovieId());
				deleteStmt.executeUpdate();

				
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(deleteStmt != null) {
					deleteStmt.close();
				}
			}
		}
		
		
		
		
		/**
		 * Get the Movie record by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a single Movie instance.
		 */
		public Movie getMovieById(int movieId) throws SQLException {
			String selectMovie = "SELECT MovieId,Title,ReleaseDate,Rating,Duration,Summary,DirectorId,StudioId,Genre "
					+ "FROM Movie WHERE MovieId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectMovie);
				selectStmt.setInt(1, movieId);
			
				results = selectStmt.executeQuery();
				DirectorDao directorDao = DirectorDao.getInstance();
				StudioDao studioDao = StudioDao.getInstance();
				
				if(results.next()) {
					int resultMovieId = results.getInt("MovieId");
					String title = results.getString("Title");
					Date releaseDate = results.getDate("ReleaseDate");
					Double rating = results.getDouble("Rating");
					int duration = results.getInt("Duration");
					String summary = results.getString("Summary");
					
		
					int directorId = results.getInt("DirectorID");
					Director director = directorDao.getDirectorByDirectorId(directorId);
					int studioId = results.getInt("StudioID");
					Studio studio = stuioDao.getStudioById(studioId);
					
					Movie.Genre genre = Movie.Genre.valueOf(results.getString("Genre"));
					
					Movie movie = new Movie (resultMovieId, title, releaseDate, rating, duration, summary, 
							genre, director, studio, genre);
					return movie;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
		}
		
		
		/**
		 * Get the Matching Movie records by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a list of Movie instance.
		 */
		public List<Movie> getMovieByDirectorId(int directorId) throws SQLException {
			List<Movie> movies = new ArrayList<>();
			
			
			String selectMovies = "SELECT MovieId,Title,ReleaseDate,Rating,Duration,Summary,DirectorId,StudioId,Genre "
					+ "FROM Movie WHERE DirectorId=?;";
			
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectMovies);
				selectStmt.setInt(1, directorId);
			
				results = selectStmt.executeQuery();
				DirectorDao directorDao = DirectorDao.getInstance();
				StudioDao studioDao = StudioDao.getInstance();
				
				if(results.next()) {
					int resultMovieId = results.getInt("MovieId");
					String title = results.getString("Title");
					Date releaseDate = results.getDate("ReleaseDate");
					Double rating = results.getDouble("Rating");
					int duration = results.getInt("Duration");
					String summary = results.getString("Summary");
					int studioId = results.getInt("StudioID");
					
					Director director = directorDao.getDirectorByDirectorId(directorId);
	
					Studio studio = studioDao.getStudioById(studioId);
					
					Movie.Genre genre = Movie.Genre.valueOf(results.getString("Genre"));
					
					Movie movie = new Movie (resultMovieId, title, releaseDate, rating, duration, summary, 
							genre, director, studio, genre);
					
					movies.add(movie);
					return movies;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
		}
		
		/**
		 * Get the Matching Movie records by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a list of Movie instance.
		 */
		public List<Movie> getMovieByStudioId(int studioId) throws SQLException {
			List<Movie> movies = new ArrayList<>();
			
			
			String selectMovies = "SELECT MovieId,Title,ReleaseDate,Rating,Duration,Summary,DirectorId,StudioId,Genre "
					+ "FROM Movie WHERE StudioId=?;";
			
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectMovies);
				selectStmt.setInt(1, studioId);
			
				results = selectStmt.executeQuery();
				DirectorDao directorDao = DirectorDao.getInstance();
				StudioDao studioDao = StudioDao.getInstance();
				
				if(results.next()) {
					int resultMovieId = results.getInt("MovieId");
					String title = results.getString("Title");
					Date releaseDate = results.getDate("ReleaseDate");
					Double rating = results.getDouble("Rating");
					int duration = results.getInt("Duration");
					String summary = results.getString("Summary");
					int directorId = results.getInt("DirectorID");
					
					Director director = directorDao.getDirectorByDirectorId(directorId);
	
					Studio studio = studioDao.getStudioById(studioId);
					
					Movie.Genre genre = Movie.Genre.valueOf(results.getString("Genre"));
					
					Movie movie = new Movie (resultMovieId, title, releaseDate, rating, duration, summary, 
							genre, director, studio, genre);
					
					movies.add(movie);
					return movies;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
		}
		
		
		/**
		 * Get the Matching Movie records by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a list of Movie instance.
		 */
		public List<Movie> getMovieByGenre(Movie.Genre genre) throws SQLException {
			List<Movie> movies = new ArrayList<>();
			
			
			String selectMovies = "SELECT MovieId,Title,ReleaseDate,Rating,Duration,Summary,DirectorId,StudioId,Genre "
					+ "FROM Movie WHERE Genre=?;";
			
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectMovies);
				selectStmt.setString(1, genre.name());
			
				results = selectStmt.executeQuery();
				DirectorDao directorDao = DirectorDao.getInstance();
				StudioDao studioDao = StudioDao.getInstance();
				
				if(results.next()) {
					int resultMovieId = results.getInt("MovieId");
					String title = results.getString("Title");
					Date releaseDate = results.getDate("ReleaseDate");
					Double rating = results.getDouble("Rating");
					int duration = results.getInt("Duration");
					String summary = results.getString("Summary");
					int directorId = results.getInt("DirectorID");
					int studioId = results.getInt("StudioID");
					
					Director director = directorDao.getDirectorByDirectorId(directorId);
	
					Studio studio = studioDao.getStudioById(studioId);
					
					Movie.Genre resultGenre = Movie.Genre.valueOf(
							results.getString("Genre"));
					
					Movie movie = new Movie (resultMovieId, title, releaseDate, rating, duration, summary, 
							genre, director, studio, resultGenre);
					
					movies.add(movie);
					return movies;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
		}
		
		
		/**
		 * Update Summary of the Movie instance.
		 * This runs a UPDATE statement.
		 */
		public Movie updateSummary(Movie movie, String newSummary) throws SQLException {
			String updateSummary = "UPDATE Movie SET Summary=? WHERE MovieId=?;";
			Connection connection = null;
			PreparedStatement updateStmt = null;
			try {
				connection = connectionManager.getConnection();
				updateStmt = connection.prepareStatement(updateSummary);
				updateStmt.setString(1, newSummary);
				updateStmt.setInt(2, movie.getMovieId());
				updateStmt.executeUpdate();
				
				
				movie.setSummary(newSummary);
				return movie;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(updateStmt != null) {
					updateStmt.close();
				}
			}
		}
		
		

}

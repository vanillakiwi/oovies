package oovies.dal;

import oovies.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Rating table in your
 * MySQL instance. This is used to store {@link Rating} into your MySQL instance and 
 * retrieve {@link Rating} from MySQL instance.
 */
public class RatingDao {
	protected ConnectionManager connectionManager;

	private static RatingDao instance = null;
	protected RatingDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingDao getInstance() {
		if(instance == null) {
			instance = new RatingDao();
		}
		return instance;
	}
	
	
	/**
	 * Get the Rating record by fetching it from the MySQL instance.
	 */
	public Rating create(Rating rating) throws SQLException {
		String insertRating =
			"INSERT INTO Rating(Score,UserId,MovieId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRating,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDouble(1, rating.getScore());
			insertStmt.setInt(2, rating.getUser().getUserId());
			insertStmt.setInt(3, rating.getMovie().getMovieId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rating.setRatingId(ratingId);
			return rating;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Get the Rating by the given rating id.
	 */
	public Rating getRatingById(int ratingId) throws SQLException {
		String selectRating =
			"SELECT RatingId,Score,UserId,MovieId " +
			"FROM Rating " +
			"WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, ratingId);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			if(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				double score = results.getDouble("Score");
				int userId = results.getInt("UserId");
				int movieId = results.getInt("MovieId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = movieDao.getMovieById(movieId);
				Rating rating = new Rating(resultRatingId, score, user, movie);
				return rating;
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
	 * Get the Rating by the given userId.
	 */
	public List<Rating> getRatingByUserId(int userId) throws SQLException {
		List<Rating> ratings = new ArrayList<Rating>();
		String selectRating = "SELECT RatingId,Score,UserId,MovieId "
				+ "FROM Rating "
				+ "WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				double score = results.getDouble("Score");
				int movieId = results.getInt("MovieId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = movieDao.getMovieById(movieId);
				Rating rating = new Rating(resultRatingId, score, user, movie);
				ratings.add(rating);
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
		return ratings;
	}
	
	/**
	 * Get the Rating by the given movie id.
	 */
	public List<Rating> getRatingByMovieId(int movieId) throws SQLException {
		List<Rating> ratings = new ArrayList<Rating>();
		String selectRating = "SELECT RatingId,Score,UserId,MovieId "
				+ "FROM Rating "
				+ "WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao moviesDao = MovieDao.getInstance();
			
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				double score = results.getDouble("Score");
				int userId = results.getInt("UserId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = moviesDao.getMovieById(movieId);
				Rating rating = new Rating(resultRatingId, score, user, movie);
				ratings.add(rating);
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
		return ratings;
	}
	
	/**
	 * Delete the Rating instance.
	 */
	public Rating delete(Rating rating) throws SQLException {
		String deleteRating = "DELETE FROM Rating WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRating);
			deleteStmt.setInt(1, rating.getRatingId());
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
}
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
 * Data access object (DAO) class to interact with the underlying Reviews table in your
 * MySQL instance. This is used to store {@link Reviews} into your MySQL instance and 
 * retrieve {@link Reviews} from MySQL instance.
 */
public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	
	/**
	 * Get the Reviews record by fetching it from the MySQL instance.
	 */
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(Created,Content,UserId,MovieId) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
			insertStmt.setString(2, review.getContent());
			insertStmt.setInt(3, review.getUser().getUserId());
			insertStmt.setInt(4, review.getMovie().getMovieId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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
	 * Get the Reviews by the given review id.
	 */
	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
			"SELECT ReviewId,Created,Content,UserId,MovieId " +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				int userId = results.getInt("UserId");
				int movieId = results.getInt("MovieId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = movieDao.getMovieById(movieId);
				Reviews review = new Reviews(resultReviewId, created, content, user, movie);
				return review;
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
	 * Get the Reviews list by the given userId.
	 */
	public List<Reviews> getReviewsByUserId(int userId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReview = "SELECT ReviewId,Created,Content,UserId,MovieId "
				+ "FROM Reviews "
				+ "WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				int movieId = results.getInt("MovieId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = movieDao.getMovieById(movieId);
				Reviews review = new Reviews(resultReviewId, created, content, user, movie);
				reviews.add(review);
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
		return reviews;
	}
	
	/**
	 * Get the Reviews list by the given movie id.
	 */
	public List<Reviews> getReviewsByMovieId(int movieId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReview = "SELECT ReviewId,Created,Content,UserId "
				+ "FROM Reviews "
				+ "WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao moviesDao = MovieDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				int userId = results.getInt("UserId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = moviesDao.getMovieById(movieId);
				Reviews review = new Reviews(resultReviewId, created, content, user, movie);
				reviews.add(review);
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
		return reviews;
	}
	
	/**
	 * Pagination
	 */
	public List<Reviews> queryByMovieId(int pageIndex, int movieId) throws Exception{
		int pageSize = 5;
		int start = (pageIndex - 1) * pageSize;
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReview = "SELECT ReviewId,Created,Content,UserId,MovieId "
				+ "FROM Reviews "
				+ "WHERE MovieId=? Limit ?,?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, movieId);
			selectStmt.setInt(2, start);
			selectStmt.setInt(3, pageSize);
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			MovieDao moviesDao = MovieDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				int userId = results.getInt("UserId");

				Person user = personDao.getPersonByUserId(userId);
				Movie movie = moviesDao.getMovieById(movieId);
				Reviews review = new Reviews(resultReviewId, created, content, user, movie);
				reviews.add(review);
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
		return reviews;
		
	}
	
	/**
	 * Get max pages for review pagination
	 */
	public int getMaxPage(int pageSize, int movieId) throws Exception{
		String countReview = "SELECT COUNT(*) AS NUM "
				+ "FROM Reviews "
				+ "WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(countReview);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int num = (results.getInt("NUM") + pageSize - 1)/pageSize;
				
				return num;
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
		return 0;
		
	}



	
	/**
	 * Delete the Reviews instance.
	 */
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.Love;
import oovies.model.Movie;
import oovies.model.Person;

public class LoveDao {
	protected ConnectionManager connectionManager;
	private static LoveDao instance = null;
	
	protected LoveDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LoveDao getInstance() {
		if(instance == null) {
			instance = new LoveDao();
		}
		return instance;
	}
	
	
	public Love create(Love love) throws SQLException {
		String insertLove = 
			"INSERT INTO Love(UserId,MovieId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLove,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, love.getUser().getUserId());
			insertStmt.setInt(2, love.getMovie().getMovieId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int loveId = -1;
			if(resultKey.next()) {
				loveId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			love.setLoveId(loveId);
			
			return love;
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
	 * Get the Love record by the given love id.
	 * This runs a SELECT statement.
	 */
	public Love getLoveById(int loveId) throws SQLException {
		String selectLove = 
			"SELECT LoveId,UserId,MovieId " + 
			"FROM Love " +
			"WHERE LoveId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLove);
			selectStmt.setInt(1, loveId);
			results = selectStmt.executeQuery();
			
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			if(results.next()) {
				int resultLoveId = results.getInt("LoveId");
				int userId = results.getInt("UserId");
				int movieId = results.getInt("MovieId");
				Person user = personDao.getPersonByUserId(userId);
				Movie movie = movieDao.getMovieById(movieId);
				
				return new Love(resultLoveId, user, movie);
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
	 * Get all Love record by the given user id.
	 * This runs a SELECT statement.
	 */
	public List<Love> getLoveByUserId(int userId) throws SQLException {
		List<Love> loves = new ArrayList<>();
		String selectLove = 
			"SELECT LoveId,UserId,MovieId " + 
			"FROM Love " +
			"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLove);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			Person user = personDao.getPersonByUserId(userId);
			while(results.next()) {
				int loveId = results.getInt("LoveId");
				int movieId = results.getInt("MovieId");
				Movie movie = movieDao.getMovieById(movieId);
				
				Love love = new Love(loveId, user, movie);
				loves.add(love);
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
		return loves;
	}
	
	
	/** 
	 * Get all Love record by the given movie id.
	 * This runs a SELECT statement.
	 */
	public List<Love> getLoveByMovieId(int movieId) throws SQLException {
		List<Love> loves = new ArrayList<>();
		String selectLove = 
			"SELECT LoveId,UserId,MovieId " +
			"FROM Love " +
			"WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLove);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			
			PersonDao personDao = PersonDao.getInstance();
			MovieDao movieDao = MovieDao.getInstance();
			
			Movie movie = movieDao.getMovieById(movieId);
			while(results.next()) {
				int loveId = results.getInt("LoveId");
				int userId = results.getInt("UserId");
				Person user = personDao.getPersonByUserId(userId);
				
				Love love = new Love(loveId, user, movie);
				loves.add(love);
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
		return loves;
	}
	
	
	/**
	 * Delete the Love instance.
	 * This runs a DELETE statement.
	 */
	public Love delete(Love love) throws SQLException {
		String deleteLove = 
			"DELETE FROM Love WHERE LoveId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLove);
			deleteStmt.setInt(1, love.getLoveId());
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
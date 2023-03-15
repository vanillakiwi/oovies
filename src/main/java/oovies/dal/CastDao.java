package oovies.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.*;


public class CastDao {
	
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
		private static CastDao instance = null;
		protected  CastDao() {
			connectionManager = new ConnectionManager();
		}
		public static CastDao getInstance() {
			if(instance == null) {
				instance = new CastDao();
			}
			return instance;
		}
		
		
		
		/**
		 * Save the Cast instance by storing it in your MySQL instance.
		 * This runs a INSERT statement.
		 */
		
		public Cast create(Cast cast) throws SQLException {
			String insertCast = "INSERT INTO Cast(MovieId, ActorId) "
					+ "VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertCast,
						Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setInt(1, cast.getMovie().getMovieId());
				insertStmt.setInt(2, cast.getActor().getActorId());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int castId = -1;
				if(resultKey.next()) {
					castId = resultKey.getInt(1);
				} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
				}
				cast.setCastId(castId);
				
				return cast;
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
		 * Delete the Cast instance.
		 * This runs a DELETE statement.
		 */
		public Cast delete(Cast cast) throws SQLException {
			String deleteCast = "DELETE FROM Cast WHERE CastId=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deleteCast);
				deleteStmt.setInt(1, cast.getCastId());
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
		 * Get the Cast record by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a single Cast instance.
		 */
		public Cast getCastById(int castId) throws SQLException {
			String selectCast = "SELECT CastId, MovieId, ActorId FROM Cast WHERE CastId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectCast);
				selectStmt.setInt(1, castId);
			
				results = selectStmt.executeQuery();
				MovieDao movieDao = MovieDao.getInstance();
				ActorDao actorDao = ActorDao.getInstance();
				
				if(results.next()) {
					int resultCastId = results.getInt("CastId");
					
		
					int movieId = results.getInt("MovieID");
					Movie movie = movieDao.getMovieById(movieId);
					int actorId = results.getInt("ActorID");
					Actor actor = actorDao.getActorById(actorId);
					
					Cast cast = new Cast (resultCastId, movie, actor);
					return cast;
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
		 * Get the Cast records by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a list of Cast instances.
		 */
		public List<Cast> getCastByMovieId(int movieId) throws SQLException {
			List<Cast> casts = new ArrayList<>();
			String selectCasts = "SELECT CastId, MovieId, ActorId FROM Cast WHERE MovieId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectCasts);
				selectStmt.setInt(1, movieId);
			
				results = selectStmt.executeQuery();
				MovieDao movieDao = MovieDao.getInstance();
				ActorDao actorDao = ActorDao.getInstance();
				
				if(results.next()) {
					int resultCastId = results.getInt("CastId");
					
		
					
					Movie movie = movieDao.getMovieById(movieId);
					int actorId = results.getInt("ActorID");
					Actor actor = actorDao.getActorById(actorId);
					
					Cast cast = new Cast (resultCastId, movie, actor);
					casts.add(cast);
					return casts;
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
		 * Get the Cast records by fetching it from your MySQL instance.
		 * This runs a SELECT statement and returns a list of Cast instances.
		 */
		public List<Cast> getCastByActorId(int actorId) throws SQLException {
			List<Cast> casts = new ArrayList<>();
			String selectCasts = "SELECT CastId, MovieId, ActorId FROM Cast WHERE ActorId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectCasts);
				selectStmt.setInt(1, actorId);
			
				results = selectStmt.executeQuery();
				MovieDao movieDao = MovieDao.getInstance();
				ActorDao actorDao = ActorDao.getInstance();
				
				if(results.next()) {
					int resultCastId = results.getInt("CastId");
					
					int movieId = results.getInt("MovieId");
					Movie movie = movieDao.getMovieById(movieId);
					
					Actor actor = actorDao.getActorById(actorId);
					
					Cast cast = new Cast (resultCastId, movie, actor);
					casts.add(cast);
					return casts;
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
		

}

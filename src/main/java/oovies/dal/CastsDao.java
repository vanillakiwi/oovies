package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.Actor;
import oovies.model.Casts;
import oovies.model.Movie;

public class CastsDao {

	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static CastsDao instance = null;

	protected CastsDao() {
		connectionManager = new ConnectionManager();
	}

	public static CastsDao getInstance() {
		if (instance == null) {
			instance = new CastsDao();
		}
		return instance;
	}

	/**
	 * Save the Cast instance by storing it in your MySQL instance. This runs a
	 * INSERT statement.
	 */

	public Casts create(Casts cast) throws SQLException {
		String insertCast = "INSERT INTO Casts(MovieId,ActorId) " + "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCast, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, cast.getMovie().getMovieId());
			insertStmt.setInt(2, cast.getActor().getActorId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int castId = -1;
			if (resultKey.next()) {
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
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Delete the Cast instance. This runs a DELETE statement.
	 */
	public Casts delete(Casts cast) throws SQLException {
		String deleteCast = "DELETE FROM Casts WHERE CastId=?;";
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
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	/**
	 * Get the Cast record by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a single Cast instance.
	 */
	public Casts getCastById(int castId) throws SQLException {
		String selectCast = "SELECT CastId, MovieId, ActorId FROM Casts WHERE CastId=?;";
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

			if (results.next()) {
				int resultCastId = results.getInt("CastId");

				int movieId = results.getInt("MovieID");
				Movie movie = movieDao.getMovieById(movieId);
				int actorId = results.getInt("ActorID");
				Actor actor = actorDao.getActorById(actorId);

				Casts cast = new Casts(resultCastId, movie, actor);
				return cast;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the Cast records by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a list of Cast instances.
	 */
	public List<Casts> getCastByMovieId(int movieId) throws SQLException {
		List<Casts> casts = new ArrayList<>();
		String selectCasts = "SELECT CastId, MovieId, ActorId FROM Casts WHERE MovieId=?;";
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

			if (results.next()) {
				int resultCastId = results.getInt("CastId");

				Movie movie = movieDao.getMovieById(movieId);
				int actorId = results.getInt("ActorID");
				Actor actor = actorDao.getActorById(actorId);

				Casts cast = new Casts(resultCastId, movie, actor);
				casts.add(cast);
				return casts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the Cast records by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a list of Cast instances.
	 */
	public List<Casts> getCastByActorId(int actorId) throws SQLException {
		List<Casts> casts = new ArrayList<>();
		String selectCasts = "SELECT CastId, MovieId, ActorId FROM Casts WHERE ActorId=?;";
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

			if (results.next()) {
				int resultCastId = results.getInt("CastId");

				int movieId = results.getInt("MovieId");
				Movie movie = movieDao.getMovieById(movieId);

				Actor actor = actorDao.getActorById(actorId);

				Casts cast = new Casts(resultCastId, movie, actor);
				casts.add(cast);
				return casts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

}
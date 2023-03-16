package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.Actor;
import oovies.model.Follow;
import oovies.model.Person;

public class FollowDao {
	protected ConnectionManager connectionManager;
	private static FollowDao instance = null;

	protected FollowDao() {
		connectionManager = new ConnectionManager();
	}

	public static FollowDao getInstance() {
		if (instance == null) {
			instance = new FollowDao();
		}
		return instance;
	}

	public Follow create(Follow follow) throws SQLException {
		String insertFollow = "INSERT INTO Follow(UserId,ActorId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFollow, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, follow.getUser().getUserId());
			insertStmt.setInt(2, follow.getActor().getActorId());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int followId = -1;
			if (resultKey.next()) {
				followId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			follow.setFollowId(followId);

			return follow;
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
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Get the Follow record by the given follow id. 
	 * This runs a SELECT statement.
	 */
	public Follow getFollowById(int followId) throws SQLException {
		String selectFollow = "SELECT FollowId,UserId,ActorId " + "FROM Follow " + "WHERE FollowId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFollow);
			selectStmt.setInt(1, followId);
			results = selectStmt.executeQuery();

			PersonDao personDao = PersonDao.getInstance();
			ActorDao actorDao = ActorDao.getInstance();

			if (results.next()) {
				int resultFollowId = results.getInt("FollowId");
				int userId = results.getInt("UserId");
				int actorId = results.getInt("ActorId");
				Person user = personDao.getPersonByUserId(userId);
				Actor actor = actorDao.getActorById(actorId);

				return new Follow(resultFollowId, user, actor);
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
	 * Get all Follow record by the given user id. 
	 * This runs a SELECT statement.
	 */
	public List<Follow> getFollowByUserId(int userId) throws SQLException {
		List<Follow> follows = new ArrayList<>();
		String selectFollow = 
			"SELECT FollowId,UserId,ActorId " + 
			"FROM Follow " + 
			"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFollow);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();

			PersonDao personDao = PersonDao.getInstance();
			ActorDao actorDao = ActorDao.getInstance();

			Person user = personDao.getPersonByUserId(userId);
			while (results.next()) {
				int followId = results.getInt("FollowId");
				int actorId = results.getInt("ActorId");
				Actor actor = actorDao.getActorById(actorId);

				Follow follow = new Follow(followId, user, actor);
				follows.add(follow);
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
		return follows;
	}

	/**
	 * Get all Follow record by the given actor id. 
	 * This runs a SELECT statement.
	 */
	public List<Follow> getFollowByActorId(int actorId) throws SQLException {
		List<Follow> follows = new ArrayList<>();
		String selectFollow = 
			"SELECT FollowId,UserId,ActorId " +
			"FROM Follow " +
			"WHERE ActorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFollow);
			selectStmt.setInt(1, actorId);
			results = selectStmt.executeQuery();
			
			PersonDao personDao = PersonDao.getInstance();
			ActorDao actorDao = ActorDao.getInstance();
			
			Actor actor = actorDao.getActorById(actorId);
			while(results.next()) {
				int followId = results.getInt("FollowId");
				int userId = results.getInt("UserId");
				Person user = personDao.getPersonByUserId(userId);
				
				Follow follow = new Follow(followId, user, actor);
				follows.add(follow);
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
		return follows;
	}

	/**
	 * Delete the Follow instance. This runs a DELETE statement.
	 */
	public Follow delete(Follow follow) throws SQLException {
		String deleteFollow = 
				"DELETE FROM Follow WHERE FollowId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
			
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFollow);
			deleteStmt.setInt(1, follow.getFollowId());
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
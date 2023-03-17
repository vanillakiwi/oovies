package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.Actor;

public class ActorDao {

    protected ConnectionManager connectionManager;

    private static ActorDao instance = null;

    protected ActorDao(){
        connectionManager = new ConnectionManager();
    }

    public static ActorDao getInstance(){
        if(instance == null){
            instance = new ActorDao();
        }

        return instance;
    }
    /**
     * Create the Actor instance.
     * This runs a INSERT statement.
     */

    public Actor create(Actor actor) throws SQLException {
		String insertActor = "INSERT INTO Actor(Name,Gender) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertActor,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, actor.getName());
			insertStmt.setString(2, actor.getGender().name());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int actorId = -1;
			if (resultKey.next()) {
				actorId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			actor.setActorId(actorId);
			return actor;
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
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Update name of the Actor instance.
     * 
	 */

    public Actor updateName(Actor actor, String newName) throws SQLException{
        String updateActor = "UPDATE Actor SET Name=? WHERE ActorId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateActor);
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, actor.getActorId());
            updateStmt.executeUpdate();


            actor.setName(newName);
            return actor;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }
    

	/**
	 * Delete the Actor instance.
	 * This runs a DELETE statement.
	 */
	public Actor delete(Actor actor) throws SQLException {
		String deleteActor = "DELETE FROM Actor WHERE Name=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteActor);
			deleteStmt.setString(1, actor.getName());
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
	 * Get the Actors record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Actor instance.
	 */

    public Actor getActorById(int actorId) throws SQLException {
		String selectActor = "SELECT ActorId,Name,Gender FROM Actor WHERE ActorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectActor);
			selectStmt.setInt(1, actorId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultActorId = results.getInt("ActorId");
				String name = results.getString("Name");
				Actor.Gender gender = Actor.Gender.valueOf(
                    results.getString("Gender"));
				Actor actor = new Actor(resultActorId, name, gender);
				return actor;
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
	 * Get the Actor record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of Actor instance.
	 */
    
	public List<Actor> getActorByName(String name) throws SQLException {
		String selectActor = "SELECT ActorId,Name,Gender FROM Actor WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
        List<Actor> actors = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectActor);
			selectStmt.setString(1, name);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
                int actorId = results.getInt("ActorId");
				String resultName = results.getString("Name");
				Actor.Gender gender = Actor.Gender.valueOf(
						results.getString("Gender"));
				Actor actor = new Actor(actorId, resultName, gender);
				actors.add(actor);
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
		return actors;
	}

}
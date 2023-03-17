package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.Studio;

public class StudioDao {

    protected ConnectionManager connectionManager;

    private static StudioDao instance = null;

    protected StudioDao(){
        connectionManager = new ConnectionManager();
    }

    public static StudioDao getInstance(){
        if(instance == null){
            instance = new StudioDao();
        }

        return instance;
    }

    /**
     * Create the Studio instance.
     * This runs a INSERT statement.
     */

    public Studio create(Studio studio) throws SQLException {
		String insertStudio = "INSERT INTO Studio(Name,Location) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertStudio,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, studio.getName());
			insertStmt.setString(2, studio.getLocation());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int studioId = -1;
			if (resultKey.next()) {
				studioId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			studio.setStudioId(studioId);
			return studio;
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
	 * Update Name of the Studio instance.
     * 
	 */

    public Studio updateName(Studio studio, String newName) throws SQLException{
        String updateStudio = "UPDATE Studio SET Name=? WHERE StudioId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStudio);
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, studio.getStudioId());
            updateStmt.executeUpdate();

            studio.setName(newName);
            return studio;
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
	 * Update Location of the Studio instance.
     * 
	 */

     public Studio updateLocation(Studio studio, String newLocation) throws SQLException{
        String updateStudio = "UPDATE Studio SET Location=? WHERE StudioId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStudio);
            updateStmt.setString(1, newLocation);
            updateStmt.setInt(2, studio.getStudioId());
            updateStmt.executeUpdate();
            studio.setLocation(newLocation);
            return studio;

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
	 * Delete the Studio instance.
	 * This runs a DELETE statement.
	 */
	public Studio delete(Studio studio) throws SQLException {
		String deleteStudio = "DELETE FROM Studio WHERE StudioId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteStudio);
			deleteStmt.setInt(1, studio.getStudioId());
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


    public Studio getStudioById(int studioId) throws SQLException {
		String selectStudio = "SELECT StudioId,Name,Location FROM Studio WHERE StudioId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudio);
			selectStmt.setInt(1, studioId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultStudioId = results.getInt("StudioId");
				String name = results.getString("Name");
				String location = results.getString("Location");
				Studio studio = new Studio(resultStudioId, name, location);
				return studio;
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
	 * Get the studio record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of studio instance.
	 */
	public List<Studio> getStudioFromName(String name) throws SQLException {
		String selectStudio = "SELECT StudioId,Name,Location FROM Studio WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
        List<Studio> studios = new ArrayList<Studio>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudio);
			selectStmt.setString(1, name);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
                int studioId = results.getInt("StudioId");
				String resultName = results.getString("Name");
				String location = results.getString("Location");
				Studio studio = new Studio(studioId, resultName, location);
                studios.add(studio);
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
		return studios;
	}

    public List<Studio> getStudioFromLocation(String location) throws SQLException {
		String selectStudio = "SELECT StudioId,Name,Location FROM Studio WHERE Location=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
        List<Studio> studios = new ArrayList<Studio>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudio);
			selectStmt.setString(1, location);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
                int studioId = results.getInt("StudioId");
				String resultName = results.getString("Name");
				String resultlocation = results.getString("Location");
				Studio studio = new Studio(studioId, resultName, resultlocation);
                studios.add(studio);
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
		return studios;
	}
}
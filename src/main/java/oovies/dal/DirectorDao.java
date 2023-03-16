package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oovies.model.Director;


public class DirectorDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static DirectorDao instance = null;

    protected DirectorDao() {
        connectionManager = new ConnectionManager();
    }

    public static DirectorDao getInstance() {
        if (instance == null) {
            instance = new DirectorDao();
        }
        return instance;
    }

    /**
     * Create the Director instance.
     * This runs a INSERT statement.
     */
    public Director create(Director director) throws SQLException {
        String insertDirector = "INSERT INTO Director(Name, Gender) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertDirector,
            		Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, director.getName());
            insertStmt.setString(2, director.getGender().name());

            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int directorId = -1;
            if (resultKey.next()) {
            	directorId = resultKey.getInt(1);
            } else {
            	throw new SQLException("Unable to retrieve auto-generated key.");
            }
            director.setDirectorId(directorId);
            return director;
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
     * Delete the Director instance.
     * This runs a DELETE statement.
     */
    public Director delete(Director director) throws SQLException {
        String deleteDirector = "DELETE FROM Director WHERE Name=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteDirector);
            deleteStmt.setString(1, director.getName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Director instance.
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
     * Get the Director record by fetching them from your MySQL instance.
     * This runs a SELECT statement and returns a single Director instance.
     */
    public Director getDirectorByDirectorId(int directorId) throws SQLException {
        String selectDirector = "SELECT DirectorId, Name, Gender FROM Director WHERE DirectorId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectDirector);
            selectStmt.setInt(1, directorId);

            results = selectStmt.executeQuery();

            if (results.next()) {
                int directId = results.getInt("DirectorId");
                String resultDirectorName = results.getString("Name");
                String gender = results.getString("Gender");
                Director director = new Director(directId, resultDirectorName, oovies.model.Director.Gender.valueOf(gender));
                return director;
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
     * Get the Director records by fetching them from your MySQL instance.
     * This runs a SELECT statement and returns a single Director instance.
     */
    public List<Director> getDirectorByDirectorName(String name) throws SQLException {
        List<Director> directors = new ArrayList<Director>();
        String selectDirector = "SELECT DirectorId, Name, Gender FROM Director WHERE Name=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectDirector);
            selectStmt.setString(1, name);

            results = selectStmt.executeQuery();

            while (results.next()) {
                int directId = results.getInt("DirectorId");
                String resultDirectorName = results.getString("Name");
                String gender = results.getString("Gender");
                Director director = new Director(directId, resultDirectorName, oovies.model.Director.Gender.valueOf(gender));
                directors.add(director);
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
        return directors;
    }





}
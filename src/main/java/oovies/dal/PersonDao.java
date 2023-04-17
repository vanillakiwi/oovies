package oovies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


import oovies.model.Person;

public class PersonDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static PersonDao instance = null;

    protected PersonDao() {
        connectionManager = new ConnectionManager();
    }

    public static PersonDao getInstance() {
        if (instance == null) {
            instance = new PersonDao();
        }
        return instance;
    }

    /**
     * Create the Person instance.
     * This runs a INSERT statement.
     */
    public Person create(Person person) throws SQLException {
        String insertPerson = "INSERT INTO Person(UserName, Password, Email, Role) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPerson,
            		Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, person.getUserName());
            insertStmt.setString(2, person.getPassword());
            insertStmt.setString(3, person.getEmail());
            insertStmt.setString(4, person.getRole().name());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int userId = -1;
            if (resultKey.next()) {
            	userId = resultKey.getInt(1);
            } else {
            	throw new SQLException("Unable to retrieve auto-generated key.");
            }
            person.setUserId(userId);
            return person;
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
     * Get all records by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns Person instances.
     */

    public List<Person> getAllPersons() throws SQLException {
        
    	List<Person> persons = new ArrayList<>();
    	String selectPerson = "SELECT UserId, UserName, Password, Email, Role FROM Person;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            
            results = selectStmt.executeQuery();
            while (results.next()) {
                int userId = results.getInt("UserId");
                String resultPersonName = results.getString("UserName");
                String password = results.getString("Password");
                String email = results.getString("Email");
                String role = results.getString("Role");
                Person person = new Person(userId, resultPersonName, password, email, Person.Role.valueOf(role));
                persons.add(person);
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
        return persons;
    }
    


    /**
     * Delete the Person instance.
     * This runs a DELETE statement.
     */
    public Person delete(Person person) throws SQLException {
        String deletePerson = "DELETE FROM Person WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePerson);
            deleteStmt.setString(1, person.getUserName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Person instance.
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
     * Get the Person record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Person instance.
     */
    public Person getPersonByUserName(String userName) throws SQLException {
        
    	
    	String selectPerson = "SELECT UserId, UserName, Password, Email, Role FROM Person WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int userId = results.getInt("UserId");
                String resultPersonName = results.getString("UserName");
                String password = results.getString("Password");
                String email = results.getString("Email");
                String role = results.getString("Role");
                Person person = new Person(userId, resultPersonName, password, email, Person.Role.valueOf(role));
                return person;
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
     * Get the Person record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Person instance.
     */
    public Person getPersonByUserId(int userId) throws SQLException {
        String selectPerson = "SELECT UserId, UserName, Password, Email, Role FROM Person WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int userId1 = results.getInt("UserId");
                String resultPersonName = results.getString("UserName");
                String password = results.getString("Password");
                String email = results.getString("Email");
                String role = results.getString("Role");
                Person person = new Person(userId, resultPersonName, password, email, Person.Role.valueOf(role));
                return person;
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
     * Get the UserId record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Person instance.
     */
    public int getUserIdByUserName(String userName) throws SQLException {
        return getPersonByUserName(userName).getUserId();

    }



    /**
     * Update the Password of the Person instance.
     * This runs a UPDATE statement.
     */
    public Person updatePassword(Person person, String password) throws SQLException {
        String updatePerson = "UPDATE Person SET Password=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePerson);
            updateStmt.setString(1, password);
            updateStmt.setString(2, person.getUserName());
            updateStmt.executeUpdate();


            person.setPassword(password);
            return person;
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
     * Update the Email of the Person instance.
     * This runs a UPDATE statement.
     */
    public Person updateEmail(Person person, String email) throws SQLException {
        String updatePerson = "UPDATE Person SET Email=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePerson);
            updateStmt.setString(1, email);
            updateStmt.setString(2, person.getUserName());
            updateStmt.executeUpdate();


            person.setEmail(email);
            return person;
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
}
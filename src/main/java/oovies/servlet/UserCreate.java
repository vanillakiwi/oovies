package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	
	protected PersonDao personDao;
	
	@Override
	public void init() throws ServletException {
		personDao = PersonDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    // Retrieve and validate name.
	    String userName = req.getParameter("username");
	    String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
	    if (userName == null || userName.trim().isEmpty()) {
	        messages.put("success", "Invalid UserName");
	        
	    }
	    if (password == null || password.trim().isEmpty() || email == null || email.trim().isEmpty() || role == null || role.trim().isEmpty()) {
	        messages.put("success", "Required field cannot be empty");
	    } else {
	        // Check if the username already exists.
	        try {
	            Person existingUser = personDao.getPersonByUserName(userName);
	            if (existingUser != null) {
	                messages.put("success", "Username already exists");
	                messages.put("disableSubmit", "true");
	            } else {
	                try {
	                    // Exercise: parse the input for Role.
	                    Person user = new Person(userName, password, email, Person.Role.valueOf(role));
	                    user = personDao.create(user);
	                    messages.put("success", "Successfully created user: " + userName);
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    messages.put("success", "Failed to create user: " + e.getMessage());
	                    throw new IOException(e);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            messages.put("success", "Failed to get user: " + e.getMessage());
	            throw new IOException(e);
	        }
	    }

	    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

}

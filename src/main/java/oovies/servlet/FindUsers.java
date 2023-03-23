package oovies.servlet;

import java.io.IOException;
import java.sql.SQLException;



import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import oovies.dal.*;
import oovies.model.*;

@WebServlet("/findusers")
public class FindUsers extends HttpServlet {
	
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

        List<Person> persons = new ArrayList<>();
       
	
        	// Retrieve Users, and store as a message.
        	try {
            	persons = personDao.getAllPersons();
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for all records ");
        
        req.setAttribute("persons", persons);
        
        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}

}

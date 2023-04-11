package oovies.servlet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import oovies.dal.PersonDao;
import oovies.model.Person;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private PersonDao personDao;

    @Override
    public void init() throws ServletException {
        personDao = PersonDao.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Perform login validation
        boolean isValidUser = validateUser(username, password);

        if (isValidUser) {
            // Set session attribute
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("loggedIn", true);

            // Get user's role
            Person person = null;
			try {
				person = personDao.getPersonByUserName(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            String role = person.getRole().name();

            // Redirect to appropriate page based on user's role
            if (role.equals("ADMIN")) {
            	session.setAttribute("role", "ADMIN");
                resp.sendRedirect("/Oovies/AdminProfile.jsp");
            } else {
            	session.setAttribute("role", "USER");
                resp.sendRedirect("/Oovies/UserProfile.jsp");
            }
        } else {
            // Set error message
            req.setAttribute("errorMessage", "Invalid username or password");

            // Forward back to login page
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }


    private boolean validateUser(String username, String password) {
        try {
            Person person = personDao.getPersonByUserName(username);
            if (person == null || !person.getPassword().equals(password)) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            // Log the exception or display an error message
            e.printStackTrace();
            return false;
        }
    }

}

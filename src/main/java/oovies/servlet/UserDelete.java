package oovies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oovies.dal.PersonDao;
import oovies.model.Person;

@WebServlet("/userdelete")
public class UserDelete extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PersonDao personDao;

    @Override
    public void init() throws ServletException {
        personDao = PersonDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        
        List<Person> users = new ArrayList<>();
        
        try {
            users = personDao.getAllPersons();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.setAttribute("users", users);
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user ID to delete.
        int userId = Integer.parseInt(req.getParameter("userId"));

        // Delete the user.
        try {
            personDao.delete(personDao.getPersonByUserId(userId));
            if (personDao.getPersonByUserId(userId) == null) {
                messages.put("title", "Successfully deleted " + userId);
            } else {
                messages.put("title", "Failed to delete " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/userdelete");
    }
}

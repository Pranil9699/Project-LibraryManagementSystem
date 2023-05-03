package com.LibraryManagement.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibraryManagement.Model.User;
import com.LibraryManagement.Service.Service;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    // Invalidate the session if it exists
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    
	    // Redirect the user to the login page
	    response.sendRedirect("index.jsp");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Invalidate all previous sessions
		request.getSession().invalidate();

		// Get username and password from request parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Check if the credentials are valid
		
		User user = Service.getUser((long)1);

		if (username.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword())) {
			// Set the ADMIN attribute in session
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1800); // 30 minutes in seconds
			session.setAttribute("ADMIN", "ADMIN");

			// Forward to home.jsp
			request.setAttribute("msg", "Correct Authentication");
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		} else {
			// Forward to home.jsp with an error message
			request.setAttribute("msg", "Your Are Not ADMIN First Go To Login");
//			request.setAttribute("error", "Wrong Authentication");
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		}
	}

}

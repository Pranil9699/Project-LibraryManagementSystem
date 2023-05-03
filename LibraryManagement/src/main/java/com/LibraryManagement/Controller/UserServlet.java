package com.LibraryManagement.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibraryManagement.Model.*;
import com.LibraryManagement.Service.Service;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object attribute = request.getSession().getAttribute("ADMIN");
		if (attribute instanceof String) {
			String check = (String) attribute;
			if (check.equals("ADMIN")) {
				HttpSession session = request.getSession();
				User user = Service.getUser((long) 1);
//                session.removeAttribute("book");
				session.setAttribute("user", user);
				RequestDispatcher rs = request.getRequestDispatcher("profile.jsp");
				rs.forward(request, response);
			} else {
				request.setAttribute("msg", "Your Are Not ADMIN First Go To Login");
				// request.setAttribute("error", "Wrong Authentication");
				request.getSession().invalidate();
				RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
				rs.forward(request, response);
			}
		} else {
			// handle the case where attribute is not a String
			request.setAttribute("msg", "Please log in as an admin to access this page.");
			request.getSession().invalidate();
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			User user = new User();
			user.setId((long) userId);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);

			Service.updateUser(user);
			User getUser = Service.getUser((long) userId);
			if (getUser.getId().equals((long) userId)) {
				HttpSession session = request.getSession();

				session.removeAttribute("user");
				session.setAttribute("user", user);
				request.setAttribute("msg", "User Successfully Updated");
				RequestDispatcher rs = request.getRequestDispatcher("profile.jsp");
				rs.forward(request, response);
			} else {
				request.setAttribute("msg", "Updation Failed");
				RequestDispatcher rs = request.getRequestDispatcher("profile.jsp");
				rs.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Server Side Error. Please Try Again Later." + e.getMessage());
			e.printStackTrace();
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		}
	}

}

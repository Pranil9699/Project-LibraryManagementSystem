package com.LibraryManagement.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibraryManagement.Model.User;
import com.LibraryManagement.Service.Service;

public class LibraryManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User((long) 1, "Akanshakadam", "admin", "takawanepranil22@gmail.com");
			User getUser=null;
			try {
				getUser = Service.getUser((long) 1);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(getUser==null) {
				Service.saveAdmin(user);
			}
			request.getSession().invalidate();
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}

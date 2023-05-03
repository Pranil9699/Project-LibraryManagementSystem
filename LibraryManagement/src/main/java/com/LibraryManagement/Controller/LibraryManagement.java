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
			User user = new User((long) 1, "PranilTakawane", "Pranil123", "takawanepranil22@gmail.com");
			User getUser = Service.getUser((long) 1);
			if(getUser.getId()==1) {
				
			}else {
				Service.saveAdmin(user);	
			}
			
			System.out.println("done");
			request.getSession().invalidate();
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

package com.LibraryManagement.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibraryManagement.Model.*;
import com.LibraryManagement.Service.Service;

/**
 * Servlet implementation class MembersServlet
 */
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute("ADMIN");
        if (attribute instanceof String) {
            String check = (String) attribute;
            if (check.equals("ADMIN")) {
                HttpSession session = request.getSession();
                List<Member> members = (List<Member>) Service.getMembers();
                session.removeAttribute("member");
                session.setAttribute("member", (List<Member>) members);
                RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
                rs.forward(request, response);
            } else {
                request.setAttribute("msg", "Your Are Not ADMIN First Go To Login");
                // request.setAttribute("error", "Wrong Authentication");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is admin
        Object attribute = request.getSession().getAttribute("ADMIN");
        if (attribute instanceof String) {
            String check = (String) attribute;
            if (!check.equals("ADMIN")) {
                request.setAttribute("msg", "You need to be an admin to access this page.");
                RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
                rs.forward(request, response);
                return;
            }
        } else {
            request.setAttribute("msg", "You need to be logged in as an admin to access this page.");
            request.getSession().invalidate();
            RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
            rs.forward(request, response);
            return;
        }

        // Retrieve input data from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String contactInformation = request.getParameter("contactInformation");

        try {
            // Create new member object
            Member member = new Member();
            member.setName(name);
            member.setEmail(email);
            member.setContactInformation(contactInformation);

            // Save member to database
            Member saveMember = Service.saveMember(member);
            if (saveMember.getName().length()>=0) {
                HttpSession session = request.getSession();
                List<Member> members = Service.getMembers();
                session.removeAttribute("member");
                request.setAttribute("msg", "Member Successfully Added");
                session.setAttribute("member", members);
                RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
                rs.forward(request, response);  
            } else {
                request.setAttribute("msg", "Failed To Add Member");
                RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
                rs.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "Error: " + e.getMessage());
            RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
            rs.forward(request, response);
        }
    }


}

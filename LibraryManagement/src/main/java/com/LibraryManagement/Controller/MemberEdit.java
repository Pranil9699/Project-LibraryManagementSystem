package com.LibraryManagement.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibraryManagement.Model.Member;
import com.LibraryManagement.Service.Service;

/**
 * Servlet implementation class MemberEdit
 */
public class MemberEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if user is admin
		Object attribute = request.getSession().getAttribute("ADMIN");
		if (attribute instanceof String) {
			String check = (String) attribute;
			if (!check.equals("ADMIN")) {
				request.setAttribute("msg", "You need to be an admin to access this page.");
				RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
				rs.forward(request, response);
				return;
			}
		} else {
			request.setAttribute("msg", "You need to be logged in as an admin to access this page.");
			RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
			rs.forward(request, response);
			return;
		}

		// Delete member
		Long memberId = Long.parseLong(request.getParameter("memberId"));
		boolean deleteMember = Service.deleteMember(memberId);
		if (deleteMember) {
			HttpSession session = request.getSession();
			List<Member> members = Service.getMembers();
			session.removeAttribute("member");
			request.setAttribute("msg", "Member Successfully Deleted");
			session.setAttribute("member", members);
			RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
			rs.forward(request, response);
		} else {
//            HttpSession session = request.getSession();
//            List<Member> members = Service.getMembers();
			request.setAttribute("msg", "Failed To Delete Member");
			RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
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
			Long memberId = Long.parseLong(request.getParameter("memberId"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String contactInformation = request.getParameter("contactInformation");

			Member member = new Member();
			member.setId(memberId);
			member.setName(name);
			member.setEmail(email);
			member.setContactInformation(contactInformation);

			Service.updateMember(member);
			Member checkMember = Service.getMember(memberId);

			if (checkMember.getName().equalsIgnoreCase(name) && checkMember.getEmail().equalsIgnoreCase(email)
					&& checkMember.getContactInformation().equalsIgnoreCase(contactInformation)) {
				HttpSession session = request.getSession();
				List<Member> members = (List<Member>) Service.getMembers();
				session.removeAttribute("member");
				request.setAttribute("msg", "Member Successfully Updated");
				session.setAttribute("member", members);
				RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
				rs.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				List<Member> members = (List<Member>) Service.getMembers();
				session.removeAttribute("member");
				request.setAttribute("msg", "Updation Failed");
				session.setAttribute("member", members);
				RequestDispatcher rs = request.getRequestDispatcher("member.jsp");
				rs.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Server Side Error. Please Try Again Later.");
			RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
			rs.forward(request, response);
		}
	}

}

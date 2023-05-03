package com.LibraryManagement.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LibraryManagement.Model.Book;
import com.LibraryManagement.Model.Borrowing;
import com.LibraryManagement.Model.Member;
import com.LibraryManagement.Service.Service;

/**
 * Servlet implementation class BorrowingEdit
 */
public class BorrowingEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowingEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
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
				RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
				rs.forward(request, response);
				return;
			}

			// Delete member
			Long borrowingId = Long.parseLong(request.getParameter("borrowingId"));
			Long bookId = Long.parseLong(request.getParameter("bookId"));

			// Borrowing borrowing= Service.getBorrowed(borrowingId);
			boolean borrowingIdcheck = Service.deleteBorrowed(borrowingId);
			if (borrowingIdcheck) {
				Book updatebook = Service.getBook(bookId);
				updatebook.setAvailable("YES");
				Service.updateBook(updatebook);
				HttpSession session = request.getSession();

				List<Borrowing> borrowedBooks = (List<Borrowing>) Service.getBorrowedBooks();
				List<Book> booksofYES = Service.getBooksALLYES();
				List<Book> booksofNO = Service.getBooksALLNO();
				List<Member> members = Service.getMembers();
				session.removeAttribute("booksY");
				session.removeAttribute("booksN");
				session.removeAttribute("members");
				session.setAttribute("booksY", booksofYES);
				session.setAttribute("booksN", booksofNO);
				session.setAttribute("members", members);
				session.removeAttribute("borrowedBooks");
				session.setAttribute("borrowedBooks", borrowedBooks);
				request.setAttribute("msg", "Borrowing Successfully Deleted");
				RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
				rs.forward(request, response);
			} else {
//            HttpSession session = request.getSession();
//            List<Member> members = Service.getMembers();
				request.setAttribute("msg", "Failed To Delete Borrowing");
				RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
				rs.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Server Error : " + e.getMessage());
			RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
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
			Long borrowingId = Long.parseLong(request.getParameter("borrowingId"));
			Long bookId = Long.parseLong(request.getParameter("bookId"));
			Long memberId = Long.parseLong(request.getParameter("memberId"));
			Boolean returned = Boolean.valueOf(request.getParameter("returned"));
			System.out.println("come here " + returned + " : " + borrowingId);
			if (returned) {
				// If admin selected "Yes", return the book to the library
				Borrowing borrowing = Service.getBorrowed(borrowingId);
				boolean borrowingIdcheck = Service.deleteBorrowed(borrowingId);
				if (borrowingIdcheck) {
					Book updatebook = Service.getBook(bookId);
					updatebook.setAvailable("YES");
					Service.updateBook(updatebook);

					LocalDate borrowedDate = borrowing.getBorrowDate();
					LocalDate currentDate = LocalDate.now();
					long daysBetween = ChronoUnit.DAYS.between(borrowedDate, currentDate);
					System.out.println(" days bet" + daysBetween);
					if ((long) daysBetween > (long) 15) {
						System.out.println("No");
						Member member = Service.getMember(memberId);
						System.out.println("Done");
						int penalty = (int) (((long) daysBetween - (long) 15) * 2); // penalty of $2 per day
						try {
							boolean sendEmail = Service.sendEmail(member, penalty);
							if (sendEmail) {
								System.out.println("Email Sending Successfully");
							}
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("Email Sending Failed");
						}
						request.setAttribute("msg",
								"Borrowing Successfully Returned. Penalty of ₹" + penalty + " applied.");
					} else {
						System.out.println("not happen");
						request.setAttribute("msg", "Borrowing Successfully Returned.");
					}

					HttpSession session = request.getSession();

					List<Borrowing> borrowedBooks = (List<Borrowing>) Service.getBorrowedBooks();
					List<Book> booksofYES = Service.getBooksALLYES();
					List<Book> booksofNO = Service.getBooksALLNO();
					List<Member> members = Service.getMembers();
					session.removeAttribute("booksY");
					session.removeAttribute("booksN");
					session.removeAttribute("members");
					session.setAttribute("booksY", booksofYES);
					session.setAttribute("booksN", booksofNO);
					session.setAttribute("members", members);
					session.removeAttribute("borrowedBooks");

					// Check if member returned book after the due date

					session.setAttribute("borrowedBooks", borrowedBooks);
					// request.setAttribute("msg", "Borrowing Successfully Returned");
					RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
					rs.forward(request, response);
				} else {
					request.setAttribute("msg", "Failed To Returned Back The Borrowing Book");
					RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
					rs.forward(request, response);
				}
			} else {
				// If admin selected "No", do not return the book to the library
				request.setAttribute("msg", "Borrowing Not Returned");
				RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
				rs.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Server Error Pleased Try Later On");
			RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
			rs.forward(request, response);
		}
	}

}

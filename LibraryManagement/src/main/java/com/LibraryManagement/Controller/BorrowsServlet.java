package com.LibraryManagement.Controller;

import java.io.IOException;
import java.time.LocalDate;
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
 * Servlet implementation class BorrowsServlet
 */
public class BorrowsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in as admin
        Object attribute = request.getSession().getAttribute("ADMIN");
        if (attribute instanceof String) {
            String check = (String) attribute;
            if (check.equals("ADMIN")) {
                // Get list of borrowed books from service layer
                List<Borrowing> borrowedBooks =(List<Borrowing>) Service.getBorrowedBooks();
                
                // Set borrowed books as attribute and forward to JSP
                HttpSession session = request.getSession();
                
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
                RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
                rs.forward(request, response);
            } else {
                // User is not logged in as admin
                request.setAttribute("msg", "You are not authorized to access this page.");
                RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
                rs.forward(request, response);
            }
        } else {
            // User is not logged in
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
        String bookId = request.getParameter("bookId");
        String userId = request.getParameter("userId");

        try {
            // Retrieve book and member objects
            Book book = Service.getBook(Long.parseLong(bookId));
            Member member = Service.getMember(Long.parseLong(userId));
            
            // Create new borrowing object
            Borrowing borrowing = new Borrowing();
            borrowing.setBook(book);
            borrowing.setMember(member);
            borrowing.setBorrowDate(LocalDate.now());
            borrowing.setReturnDate(LocalDate.now().plusDays(15)); // Set return date to 15 days after borrow date
            
            // Save borrowing to database
            Borrowing saveBorrowing = Service.saveBorrowing(borrowing);
            System.out.println(saveBorrowing);
            if (saveBorrowing.getId() != null) {
            	Book updatebook = Service.getBook(Long.parseLong(bookId));
            	updatebook.setAvailable("NO");
            	Service.updateBook(updatebook);
                HttpSession session = request.getSession();
                
                List<Borrowing> borrowedBooks =(List<Borrowing>) Service.getBorrowedBooks();
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
				/*
				 * int count=0; for (Borrowing obj : borrowedBooks) { count++;
				 * System.out.println(" Count is "+count); }
				 */
                session.setAttribute("borrowedBooks", borrowedBooks);
                request.setAttribute("msg", "Borrowing Successfully Added");
                RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
                rs.forward(request, response);  
            } else {
                request.setAttribute("msg", "Failed To Add Borrowing");
                RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
                rs.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "Error: " + e.getMessage());
            RequestDispatcher rs = request.getRequestDispatcher("borrowing.jsp");
            rs.forward(request, response);
        }
    }


}

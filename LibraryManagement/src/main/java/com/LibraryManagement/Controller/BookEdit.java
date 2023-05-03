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
 * Servlet implementation class BookEdit
 */
public class BookEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is admin
        Object attribute = request.getSession().getAttribute("ADMIN");
        if (attribute instanceof String) {
            String check = (String) attribute;
            if (!check.equals("ADMIN")) {
                request.setAttribute("msg", "You need to be an admin to access this page.");
                RequestDispatcher rs = request.getRequestDispatcher("book.jsp");
                rs.forward(request, response);
                return;
            }
        } else {
            request.setAttribute("msg", "You need to be logged in as an admin to access this page.");
            RequestDispatcher rs = request.getRequestDispatcher("book.jsp");
            rs.forward(request, response);
            return;
        }
        
        // Delete book
        Long bookId = Long.parseLong(request.getParameter("bookId"));
        boolean deleteBook = Service.deleteBook( bookId);
        if (deleteBook) {
            HttpSession session = request.getSession();
            List<Book> books = Service.getBooks();
            session.removeAttribute("book");
            request.setAttribute("msg", "Book Successfully Deleted");
            session.setAttribute("book", books);
            RequestDispatcher rs = request.getRequestDispatcher("book.jsp");
            rs.forward(request, response);  
        } else {
//            HttpSession session = request.getSession();
//            List<Book> books = Service.getBooks();
            request.setAttribute("msg", "Failed To Delete Book");
            RequestDispatcher rs = request.getRequestDispatcher("book.jsp");
            rs.forward(request, response);
        }
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long bookId = Long.parseLong(request.getParameter("bookId"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            String publisher = request.getParameter("publisher");

            Book book = new Book();
            book.setId(bookId);
            book.setName(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setPublisher(publisher);
            book.setAvailable("YES");

            Service.updateBook(book);
            Book updatedBook = Service.getBook(bookId);
            if (updatedBook.getName().equalsIgnoreCase(title) && updatedBook.getAuthor().equalsIgnoreCase(author)
                    && updatedBook.getIsbn().equalsIgnoreCase(isbn) && updatedBook.getPublisher().equalsIgnoreCase(publisher)) {
                HttpSession session = request.getSession();
                List<Book> books = (List<Book>) Service.getBooks();
                session.removeAttribute("book");
                request.setAttribute("msg", "Book Successfully Updated");
                session.setAttribute("book", books);
                RequestDispatcher rs = request.getRequestDispatcher("book.jsp");
                rs.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                List<Book> books = (List<Book>) Service.getBooks();
                session.removeAttribute("book");
                request.setAttribute("msg", "Updation Failed");
                session.setAttribute("book", books);
                RequestDispatcher rs = request.getRequestDispatcher("book.jsp");
                rs.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "Server Side Error. Please Try Again Later.");
            RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
            rs.forward(request, response);
        }
    }

}

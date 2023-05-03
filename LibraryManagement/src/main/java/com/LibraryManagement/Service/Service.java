package com.LibraryManagement.Service;

import java.time.LocalDate;
import java.util.List;

import com.LibraryManagement.Model.*;
import com.LibraryManagement.Repository.Repository;


// for email 
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
public class Service {

	public static User saveAdmin(User user) {
		return Repository.saveObject(user);

	}

	public static List<Book> getBooks() {
		return Repository.getBooks("YES");
		// TODO Auto-generated method stub

	}

	public static Book updateBook(Book book) {

		return Repository.saveObject(book);
	}

	public static boolean deleteBook(Long bookId) {
		return Repository.deleteObject(Book.class, bookId);

	}

	public static boolean deleteMember(Long memberId) {
		return Repository.deleteObject(Member.class, memberId);

	}

	public static List<Member> getMembers() {
		// TODO Auto-generated method stub
		return Repository.getMembers();
	}

	public static Member updateMember(Member member) {
		// TODO Auto-generated method stub
		return Repository.saveObject(member);
	}

	public static Member saveMember(Member member) {
		// TODO Auto-generated method stub
		return Repository.saveObject(member);
	}

	public static Book savebook(Book book) {
		// TODO Auto-generated method stub
		return Repository.saveObject(book);
	}

	public static List<Borrowing> getBorrowedBooks() {
		// TODO Auto-generated method stub
		return Repository.getBorrowedBooks();
		
	}

	public static List<Book> getBooksALLYES() {
		// TODO Auto-generated method stub
		return Repository.getBooks("YES");
	}

	public static List<Book> getBooksALLNO() {
		// TODO Auto-generated method stub
		return Repository.getBooks("NO");
	}

	public static Book getBook(long parseLong) {
		// TODO Auto-generated method stub
		return Repository.getObject(Book.class, parseLong);
	}

	public static Member getMember(long parseLong) {
		// TODO Auto-generated method stub
		return Repository.getObject(Member.class, parseLong);
	}

	public static Borrowing saveBorrowing(Borrowing borrowing) {
		// TODO Auto-generated method stub
		return Repository.saveObject(borrowing);
	}

	public static boolean deleteBorrowed(Long borrowingId) {
		// TODO Auto-generated method stub
		return Repository.deleteObject(Borrowing.class, borrowingId);
	}

	public static Borrowing getBorrowed(Long borrowingId) {
		// TODO Auto-generated method stub
		return Repository.getObject(Borrowing.class, borrowingId);
	}

	public static boolean sendEmail(Member member, int penalty) {
	    boolean isSent = false;
	    String senderEmail = "takawanepranil22@gmail.com";
	    String recipientEmail = member.getEmail();
	    String subject = "Library Borrowing Return Notification";
	    String messageText = "Dear " + member.getName() + ",\n\n"
	            + "This is to inform you that the book borrowed from our library with ID " + member.getId() 
	            + " has been returned on " + LocalDate.now() + ".\n\n"
	            + "Due to a delay in returning the book, a penalty of Rs." + penalty 
	            + " has been applied to your account.\n\n"
	            + "Thank you for using our library services.\n\n"
	            + "Sincerely,\n"
	            + "Library Management System\n";

	    // SMTP properties
	    Properties smtpProperties = new Properties();
	    smtpProperties.put("mail.smtp.auth", true);
	    smtpProperties.put("mail.smtp.starttls.enable", true);
	    smtpProperties.put("mail.smtp.ssl.enable", true);
	    smtpProperties.put("mail.smtp.port", 465);
	    smtpProperties.put("mail.smtp.host", "smtp.gmail.com");

	    final String username = "takawanepranil22@gmail.com";
	    final String password = "qonifztsuzyswhsb";

	    Session session = Session.getInstance(smtpProperties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
	        message.setFrom(new InternetAddress(senderEmail));
	        message.setSubject(subject);
	        message.setText(messageText);

	        Transport.send(message);

	        isSent = true;
	    } catch (MessagingException e) {
	        System.err.println("Error occurred while sending the email: " + e.getMessage());
	    }

	    return isSent;
	}

	public static User getUser(long userId) {
		// TODO Auto-generated method stub
		return Repository.getObject(User.class, userId);
	}

	public static User updateUser(User user) {
		return Repository.saveObject(user);
	}

}

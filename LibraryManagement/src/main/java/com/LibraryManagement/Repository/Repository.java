package com.LibraryManagement.Repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.LibraryManagement.Helper.factoryProvider;
import com.LibraryManagement.Model.Book;
import com.LibraryManagement.Model.Borrowing;
import com.LibraryManagement.Model.Member;

@SuppressWarnings("deprecation")
public class Repository{

//	public static User saveAdmin(User user) {
//		return saveObject(user);
//	}
	public static <T> T saveObject(T object) {
		try {
	    Session session = factoryProvider.getfactory().openSession();
	    Transaction tx = session.beginTransaction();
	    session.saveOrUpdate(object);
	    tx.commit();
	    session.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return object;
	}

	public static List<Book> getBooks(String string) {
	    List<Book> books = null;
	    try {
	        Session session = factoryProvider.getfactory().openSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "FROM Book b WHERE b.available =:available";
	        @SuppressWarnings({ "unchecked" })
			Query<Book> query = session.createQuery(hql);
	        query.setParameter("available", string);
	        books = query.list();
	        tx.commit();
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}

	public static boolean deleteObject(Class<?> clazz, Long objectId) {
	    try {
	        Session session = factoryProvider.getfactory().openSession();
	        Transaction tx = session.beginTransaction();
	        Object object = session.get(clazz, objectId);
	        session.delete(object);
	        tx.commit();
	        session.close();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static List<Member> getMembers() {
	    List<Member> members = null;
	    try {
	        Session session = factoryProvider.getfactory().openSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "FROM Member";
	        @SuppressWarnings("unchecked")
	        Query<Member> query = session.createQuery(hql);
	        members = query.list();
	        tx.commit();
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return members;
	}

	public static List<Borrowing> getBorrowedBooks() {
		List<Borrowing> borrowings = null;
	    try {
	        Session session = factoryProvider.getfactory().openSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "FROM Borrowing";
	        @SuppressWarnings("unchecked")
	        Query<Borrowing> query = session.createQuery(hql);
	        borrowings = query.list();
	        tx.commit();
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return borrowings;
	}

	public static <T> T getObject(Class<T> clazz, long objectId) {
	    try {
	        Session session = factoryProvider.getfactory().openSession();
	        T object = session.get(clazz, objectId);
	        session.close();
	        return object;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


}

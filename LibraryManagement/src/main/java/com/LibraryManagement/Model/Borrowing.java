package com.LibraryManagement.Model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "borrowings")
public class Borrowing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER) // change fetch strategy to EAGER
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne(fetch = FetchType.EAGER) // change fetch strategy to EAGER
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "borrow_date")
	private LocalDate borrowDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	public Borrowing(Long id, Book book, Member member, LocalDate borrowDate, LocalDate returnDate, String bname,
			String mname) {
		super();
		this.id = id;
		this.book = book;
		this.member = member;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;

		this.bname = bname;
		this.mname = mname;
	}

	@Transient
	private String bname;

	@Transient
	private String mname;

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Borrowing [id=" + id + ", book=" + book + ", member=" + member + ", borrowDate=" + borrowDate
				+ ", returnDate=" + returnDate + ", bname=" + bname + ", mname=" + mname + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Borrowing() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructors, getters, and setters

}

package com.LibraryManagement.Model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name = "isbn", nullable = false)
	private String isbn;

	
	private String available;
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public Book(Long id, String name, String author, String publisher, String isbn, String available) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructors, getters, and setters
	

}

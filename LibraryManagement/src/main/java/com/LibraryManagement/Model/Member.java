package com.LibraryManagement.Model;

import javax.persistence.*;
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_information", nullable = false)
    private String contactInformation;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

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

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Member(Long id, String name, String contactInformation, String email) {
		super();
		this.id = id;
		this.name = name;
		this.contactInformation = contactInformation;
		this.email = email;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Constructors, getters, and setters
}
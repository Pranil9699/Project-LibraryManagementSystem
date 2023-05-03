package com.LibraryManagement.Model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
//	INSERT INTO User (id, username, password, email) VALUES (1, 'PranilTakawane', 'Pranil123', 'takawanepranil22@gmail.com');
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    // Constructors
    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Other methods
}

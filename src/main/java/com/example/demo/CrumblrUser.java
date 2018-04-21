package com.example.demo;

import org.springframework.data.annotation.Id;

public class CrumblrUser {
	
	@Id
	public String id;
	
	public String username;
	public String password;
	public String email;
	public String firstName;
	
	
	public CrumblrUser() {
	}
	
	@Override
	public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', username='%s', password='%s', email='%s']",
                id, firstName, username, password, email);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}

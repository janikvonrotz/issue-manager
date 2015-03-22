package ch.issueman.common;

import javax.persistence.Entity;

@Entity
public class User extends Person {

	private String email;
	private String password;
	private String role;
	
	public User(){}
	
	public User(String name, String email, String password, String role) {
		super(name);
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

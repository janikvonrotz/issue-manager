package ch.issueman.common;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
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
}

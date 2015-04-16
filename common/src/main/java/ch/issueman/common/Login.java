package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * POJO class Login
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
public class Login implements Model {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private Person person;
	@NotNull
	private String passwort;
	@NotNull
	private Rolle rolle;
	
	public Login(){}
	
	public Login(Person person, String passwort, Rolle rolle){
		super();
		this.person = person;
		this.passwort = passwort;
		this.rolle = rolle;
	}
	
	/**
	 * Get username for the login
	 * 
	 * @return E-Mail adress of the associated person
	 */
	public String getUsername(){
		return person.getEmail();
	}
}

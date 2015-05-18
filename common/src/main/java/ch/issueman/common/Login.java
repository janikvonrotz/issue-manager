package ch.issueman.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;

/**
 * class Login
 * 
 * @author Sandro Klarer
 * @version 1.0.1
 * @since 1.0.0
 */
@SuppressWarnings("serial")
@Entity
@Data
public class Login implements Model {

	@Id
	@GeneratedValue
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	@NotNull
	private Person person;
	@NotNull
	private String passwort;
	@ManyToOne(fetch=FetchType.EAGER)
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
	 * Get username for the login.
	 * 
	 * @return E-Mail adress of the associated person
	 */
	@JsonIgnore
	public String getUsername(){
		return person.getEmail();
	}
}

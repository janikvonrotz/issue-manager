package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Abstract class Person
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Person implements Model{
	
	@Id
	@GeneratedValue
    private int id; 
	@NotNull
    private String nachname;
	@NotNull
    private String vorname;
	@NotNull
    private String email;
	
	private boolean archiviert;
	
	public Person(){}
		
	public Person(String nachname, String vorname, String email) {
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
		this.archiviert = false;
	}
	
	/**
	 * Abstract method to get the dynamic display name
	 * 
	 * @return display name
	 */
	public abstract String getDisplayName();
} 
package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Bauherr
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public class Bauherr extends Person {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private Person nachname;
	@NotNull
	private Person vorname;
	@NotNull
	private Person email;
	@OneToOne
	private Unternehmen unternehmen;
	
	
	public Bauherr(){}
	
	public Bauherr(Person nachname, Person vorname, Person email, Unternehmen unternehmen){
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
		this.unternehmen = unternehmen;
	}

	@Override
	public String getDisplayName() {
		return this.nachname + " " + this.vorname;
	}

}

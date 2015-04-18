package ch.issueman.common;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * class Bauleiter
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public class Bauleiter extends Person {
	
	@NotNull
	private Person nachname;
	@NotNull
	private Person vorname;
	@NotNull
	private Person email;
	
	public Bauleiter(){}
	
	public Bauleiter(Person nachname, Person vorname, Person email){
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
	}

	@Override
	public String getDisplayName() {
		return this.nachname + " " + this.vorname;
	}
	
}

package ch.issueman.common;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * class Bauleiter
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Bauleiter extends Person {
	
	public Bauleiter(){}
	
	public Bauleiter(String nachname, String vorname, String email){
		super(nachname, vorname, email);
	}

	@Override
	public String getDisplayName() {
		return this.getNachname() + " " + this.getVorname();
	}
	
}

/**
 * 
 */
package ch.issueman.common;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * class Sachbearbeiter
 * 
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Sachbearbeiter extends Person {
	
	public Sachbearbeiter(){}
	
	public Sachbearbeiter(String nachname, String vorname, String email){
		super(nachname, vorname, email);
	}

	@Override
	public String getDisplayName() {
		return this.getNachname() + " " + this.getVorname();
	}

}

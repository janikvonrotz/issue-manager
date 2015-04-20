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
	
	public Sachbearbeiter(Person nachname, Person vorname, Person email){
		super();
	}

	@Override
	public String getDisplayName() {
		return this.getNachname() + " " + this.getVorname();
	}

}

/**
 * 
 */
package ch.issueman.common;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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

	@NotNull
	private Person nachname;
	@NotNull
	private Person vorname;
	@NotNull
	private Person email;
	
	public Sachbearbeiter(){}
	
	public Sachbearbeiter(Person nachname, Person vorname, Person email){
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

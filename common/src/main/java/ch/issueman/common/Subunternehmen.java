package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;

/**
 * class Subunternehmen
 * 
 * @author Patrick Elsener
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public class Subunternehmen extends Unternehmen {

	@Id
	@GeneratedValue
	private int id;

	public Subunternehmen() {
	}

	public Subunternehmen(String firmenname, Adresse adresse) {
		super(firmenname, adresse);
	}

}

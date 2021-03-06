package ch.issueman.common;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * class Subunternehmen
 * 
 * @author Patrick Elsener
 * @version 1.0.0
 * @since 1.0.0
 */

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Subunternehmen extends Unternehmen {

	public Subunternehmen() {
	}

	public Subunternehmen(String firmenname, Adresse adresse) {
		super(firmenname, adresse);
	}
}

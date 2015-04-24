package ch.issueman.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Unternehmen
 * 
 * @author Patrick Elsener
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public class Unternehmen {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String firmenname;
	@OneToOne(cascade = CascadeType.ALL)
	private Adresse adresse;

	public Unternehmen() {
	}

	public Unternehmen(String firmenname, Adresse adresse) {
		this.firmenname = firmenname;
		this.adresse = adresse;
	}

}

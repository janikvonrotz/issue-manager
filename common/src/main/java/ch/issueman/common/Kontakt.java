package ch.issueman.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * class Kontakt
 * 
 * @author Patrick Elsener
 * @version 1.0.0
 * @since 1.0.0
 */

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Kontakt extends Person {

	@OneToOne
	private List<Projekt> projekte;
	@ManyToOne
	private Subunternehmen subunternehmen;

	public Kontakt() {
	}

	public Kontakt(String nachname, String vorname, String email,
			Subunternehmen subunternehmen, List<Projekt> projekte) {
		super(nachname, vorname, email);
		this.subunternehmen = subunternehmen;
		this.projekte = projekte;
	}

	@Override
	public String getDisplayName() {
		return this.getVorname() + " " + this.getNachname();
	}

}

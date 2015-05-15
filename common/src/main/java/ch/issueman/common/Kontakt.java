package ch.issueman.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(callSuper=true)
public class Kontakt extends Person {

	@OneToMany(fetch=FetchType.EAGER)
	private List<Projekt> projekte;
	@ManyToOne(fetch=FetchType.EAGER)
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

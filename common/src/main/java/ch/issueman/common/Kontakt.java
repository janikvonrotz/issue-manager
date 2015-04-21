package ch.issueman.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * class Kontakt
 * 
 * @author Patrick Elsener
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public abstract class Kontakt extends Person {

	@Id
	@GeneratedValue
	private int id;
	@OneToOne
	private List<Projekt> projekte;
	@ManyToOne
	private Subunternehmen subunternehmen;

	public Kontakt() {
	}

	public Kontakt(Person nachname, Person vorname, Person email,
			Subunternehmen subunternehmen, List<Projekt> projekte) {
		super();
		this.subunternehmen = subunternehmen;
		this.projekte = projekte;
	}

	@Override
	public int getId() {
		return this.id;
	}

}

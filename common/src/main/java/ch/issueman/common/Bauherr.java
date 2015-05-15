package ch.issueman.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * class Bauherr
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Bauherr extends Person {
	
	@OneToOne(cascade=CascadeType.ALL)
	private Unternehmen unternehmen;
	
	public Bauherr(){}
	
	public Bauherr(String nachname, String vorname, String email, Unternehmen unternehmen){
		super(nachname, vorname, email);
		this.unternehmen = unternehmen;
	}

	@Override
	public String getDisplayName() {
		return this.getNachname() + " " + this.getVorname();
	}

}

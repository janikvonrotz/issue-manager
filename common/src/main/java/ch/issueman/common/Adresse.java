package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Adresse
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 *
 */

@Entity
@Data
public class Adresse implements Model {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String strasse;
	@ManyToOne
	@NotNull
	private Ort ort;
	
	public Adresse(){}
	
	public Adresse(String strasse, Ort ort){
		super();
		this.strasse = strasse;
		this.ort = ort;
	}
}
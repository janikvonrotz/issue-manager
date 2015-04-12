package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Beschreibung hier
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 */
@Entity
@Data
public class Ort implements Model {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private int plz;
	@NotNull
	private String ort;
	
	public Ort(){}
	
	public Ort(int plz, String ort) {
		super();
		this.plz = plz;
		this.ort = ort;
	}	
}

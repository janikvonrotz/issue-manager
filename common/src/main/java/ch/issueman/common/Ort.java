package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Klasse Ort
 * 
 * @author Janik von Rotz
 */
@Entity
@Data
public class Ort implements Model {

	@Id
	@GeneratedValue
	private int id;
	private int plz;
	private String ort;
	
	public Ort(){}
	
	public Ort(int plz, String ort) {
		super();
		this.plz = plz;
		this.ort = ort;
	}	
}

package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Rolle
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public class Rolle implements Model {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String bezeichnung;
	
	public Rolle(){}
	
	public Rolle(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
}

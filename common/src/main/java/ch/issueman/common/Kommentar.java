package ch.issueman.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Kommentar
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 *
 */

@Entity
@Data
public class Kommentar implements Model {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@Lob
	private String kommentar;
	@NotNull
	private Login login;
	private Date erstelltam;

	public Kommentar() {
	}

	public Kommentar(String kommentar, Login login) {
		super();
		this.kommentar = kommentar;
		this.login = login;
	}

	/**
	 * Pre persist method to set current date as creation date 
	 */
	@PrePersist
	void erstelltam() {
		this.erstelltam = new Date();
	}
}
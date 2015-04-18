package ch.issueman.common;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Mangel
 *
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
public class Mangel implements Model {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private int referenz;
	@NotNull
	@ManyToOne
	private Person erfasser;
	@ManyToMany
	private List<Kommentar> kommentare;
	@NotNull
	@ManyToOne
	private Mangelstatus mangelstatus;
	@NotNull
	private Date erledigenbis;
	@NotNull
	@ManyToOne
	private Projekt projekt;
	private Date erstelltam;
	
	public Mangel(){}

	public Mangel(int referenz, Person erfasser, List<Kommentar> kommentare,
			Mangelstatus mangelstatus, Date erledigenbis, Projekt projekt,
			Date erstelltam) {
		super();
		this.referenz = referenz;
		this.erfasser = erfasser;
		this.kommentare = kommentare;
		this.mangelstatus = mangelstatus;
		this.erledigenbis = erledigenbis;
		this.projekt = projekt;
		this.erstelltam = erstelltam;
	}
	
	/**
	 * Pre persist method to set current date as creation date 
	 */
	@PrePersist
	void erstelltam() {
		this.erstelltam = new Date();
	}
}
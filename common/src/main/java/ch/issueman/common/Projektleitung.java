package ch.issueman.common;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Projektleitung
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@SuppressWarnings("serial")
@Entity
@Data
public class Projektleitung implements Model{

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private Bauleiter bauleiter;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar beginn;
	@Temporal(TemporalType.DATE)
	private Calendar ende;

	public Projektleitung() {
	}

	public Projektleitung(Bauleiter bauleiter, Calendar beginn, Calendar ende) {
		super();
		this.bauleiter = bauleiter;
		this.beginn = beginn;
		this.ende = ende;
	}

}

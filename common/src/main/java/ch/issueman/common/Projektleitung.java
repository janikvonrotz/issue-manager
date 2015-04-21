package ch.issueman.common;

//import java.util.Calendar;
//import java.util.Date;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Projektleitung
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
public class Projektleitung {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@ManyToOne
	private Bauleiter bauleiter;
	@NotNull
	private GregorianCalendar beginn;
	private GregorianCalendar ende;
	
	public Projektleitung(){}
	
	public Projektleitung(Bauleiter bauleiter, GregorianCalendar beginn, GregorianCalendar ende){
		super();
		this.bauleiter = bauleiter;
		this.beginn = beginn;
		this.ende = ende;
	}

}

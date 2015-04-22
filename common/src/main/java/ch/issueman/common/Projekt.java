package ch.issueman.common;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.jpa.config.Cascade;

import lombok.Data;

/**
 * class Projekt
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
public class Projekt {
	
	@Id
	@GeneratedValue
    private int id; 
	@NotNull
    private String title;
	@NotNull
	@ManyToOne (cascade = CascadeType.ALL)
    private String adresse;
	@NotNull
	@ManyToOne
    private Arbeitstyp arbeitstyp;
	@NotNull
	@ManyToOne
    private Projekttyp projekttyp;
	@NotNull
	@ManyToOne
    private Bauherr bauherr;
	@NotNull
	@OneToMany
    private List<Projektleitung> projektleitungen;
	@NotNull
    private Calendar beginn;
	@NotNull
    private Calendar ende;
	
    private boolean archiviert;
	
	public Projekt(){}
	
	public Projekt(String title, String adresse, Arbeitstyp arbeitstyp, Projekttyp projekttyp,
			Bauherr bauherr, List<Projektleitung> projektleitungen, Calendar beginn, Calendar ende){
		this.title = title;
		this.adresse = adresse;
		this.arbeitstyp = arbeitstyp;
		this.projekttyp = projekttyp;
		this.bauherr = bauherr;
		this.projektleitungen = projektleitungen;
		this.beginn = beginn;
		this.ende = ende;
		this.archiviert = false;
	}
	
	/**
	 * Abstract method to get the dynamic display name
	 * 
	 * @return display name
	 */
	public String getDisplayName(){
		if(("" + id).length() < 4) {
			return "P" + ("000" + id).substring(("" + id).length());
		} else {
			return "P" + ("" + id).substring(("" + id).length()-3);
		}
	}

}

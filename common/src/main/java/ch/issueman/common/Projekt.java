package ch.issueman.common;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Projekt
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
@Entity
@Data
public class Projekt implements Model{
	
	@Id
	@GeneratedValue
    private int id; 
	@NotNull
    private String title;
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
    private Adresse adresse;
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
    private Arbeitstyp arbeitstyp;
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
    private Projekttyp projekttyp;
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
    private Bauherr bauherr;
	@NotNull
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Projektleitung> projektleitungen;
	@NotNull
    private Calendar beginn;
	@NotNull
    private Calendar ende;
	@Basic
	private Character archiviert;
	
	public Projekt(){}
	
	public Projekt(String title, Adresse adresse, Arbeitstyp arbeitstyp,
			Projekttyp projekttyp, Bauherr bauherr,
			List<Projektleitung> projektleitungen, Calendar beginn,
			Calendar ende) {
		super();
		this.title = title;
		this.adresse = adresse;
		this.arbeitstyp = arbeitstyp;
		this.projekttyp = projekttyp;
		this.bauherr = bauherr;
		this.projektleitungen = projektleitungen;
		this.beginn = beginn;
		this.ende = ende;
		this.setArchiviert(false);
	}
	
	public Boolean getArchiviert() {
		if (archiviert == null)
			return null;
		return archiviert == 'Y' ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setArchiviert(Boolean archiviert) {
		if (archiviert == null) {
			this.archiviert = null;
		} else {
			this.archiviert = archiviert == true ? 'Y' : 'N';
		}
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

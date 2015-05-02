package ch.issueman.common;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonIgnore;
<<<<<<< HEAD
=======
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
>>>>>>> a6f777d906c2375f20d4a45cd3e2f5ae39a75d3e
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonTypeResolver;

/**
 * Abstract class Person
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
@Entity
@Data
@Inheritance(strategy=InheritanceType.JOINED)
<<<<<<< HEAD
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
=======
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
//@JsonSubTypes({  
//    @Type(value = Bauleiter.class, name = "Bauleiter"),  
//    @Type(value = Sachbearbeiter.class, name= "Sachbearbeiter"),
//    @Type(value = Kontakt.class, name= "Kontakt"),
//    @Type(value = Bauherr.class, name= "Bauherr")
//}) 
>>>>>>> a6f777d906c2375f20d4a45cd3e2f5ae39a75d3e
public abstract class Person implements Model{
	
	@Id
	@GeneratedValue
    private int id; 
	@NotNull
    private String nachname;
	@NotNull
    private String vorname;
	@NotNull
    private String email;
	@Basic
	private Character archiviert;
	
	public Person(){}
		
	public Person(String nachname, String vorname, String email) {
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
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
	@JsonIgnore
	public abstract String getDisplayName();
} 
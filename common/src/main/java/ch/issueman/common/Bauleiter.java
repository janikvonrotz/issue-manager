package ch.issueman.common;

import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * class Bauleiter
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Bauleiter extends Person {
	
	public Bauleiter(){}
	
	public Bauleiter(String nachname, String vorname, String email){
		super(nachname, vorname, email);
	}

	@Override
	public String getDisplayName() {
		return this.getNachname() + " " + this.getVorname();
	}
	
}

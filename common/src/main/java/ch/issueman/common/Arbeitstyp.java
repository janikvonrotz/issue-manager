package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Arbeitstyp
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
@Entity
@Data
public class Arbeitstyp implements Model {
	
	@Id
	@GeneratedValue
    private int id; 
	@NotNull
    private String arbeitstyp;
	
	public Arbeitstyp(){}
	
	public Arbeitstyp(String arbeitstyp){
		this.arbeitstyp = arbeitstyp;
	}

}

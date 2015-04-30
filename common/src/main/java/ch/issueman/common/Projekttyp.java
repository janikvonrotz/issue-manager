package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * class Projekttyp
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
@Entity
@Data
public class Projekttyp implements Model{
	
	@Id
	@GeneratedValue
    private int id; 
	@NotNull
    private String projekttyp;
	
	public Projekttyp(){}
	
	public Projekttyp(String projekttyp){
		this.projekttyp = projekttyp;
	}

}

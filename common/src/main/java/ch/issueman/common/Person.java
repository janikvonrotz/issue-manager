package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Data
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Model{
	
	@Id
	@GeneratedValue
    private int id; 
    private String name;
	
	public Person(){}
	
	public Person(String name) {
		super();
		this.name = name;
	}
} 
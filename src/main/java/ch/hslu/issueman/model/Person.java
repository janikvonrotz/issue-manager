package ch.hslu.issueman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person implements Model{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private int id; 
	
	@Column(name="name")
    private String name;
	
	public Person() {}

	public Person(String name) {
		setName(name);
	}
	
	public int getId() {
		return id;
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toJson() {
		return "{\"id\": \"" + getId() + "\", \"name\":\"" + getName() + "\"}";
	}

} 
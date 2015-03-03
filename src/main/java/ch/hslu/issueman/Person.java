package ch.hslu.issueman;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person implements Model{
	
	@Id
	@Column(name="id")
    private int id; 
	
	@Column(name="name")
    private String name;
	
	public Person() {}

	public Person(int id, String name) {
		setId(id);
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
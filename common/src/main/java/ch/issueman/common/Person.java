package ch.issueman.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name="person")
public class Person implements Model{
	
	@Id
	@GeneratedValue
	@Column(name="id")
    private int id; 
	
	@Column(name="name")
    private String name;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="person")
	@JsonManagedReference
    private User user;

	@OneToOne(cascade = CascadeType.ALL, mappedBy="person")
	@JsonManagedReference
    private Employee employee;
	
	public Person(){}
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
} 
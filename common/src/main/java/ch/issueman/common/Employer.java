package ch.issueman.common;

import javax.persistence.Entity;

@Entity
public class Employer extends Person {

	private String company;
		
	public Employer(){}
		
	public Employer(String name, String company) {
		super(name);
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}

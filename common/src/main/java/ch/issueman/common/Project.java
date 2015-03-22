package ch.issueman.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Project implements Model {

	@Id
	@GeneratedValue
	private int id;
	private String title;
	@ManyToOne
	private Employer employer;
	@ManyToMany
	private List<Comment> comments;
	
	public Project(){}
	
	public Project(String title, Employer employer) {
		super();
		this.title = title;
		this.employer = employer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
}

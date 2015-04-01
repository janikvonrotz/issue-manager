package ch.issueman.common;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Project implements Model {

	@Id
	@GeneratedValue
	private int id;
	private String title;
	@ManyToOne
	private Employer employer;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;

	public Project() {
	}

	public Project(String title, Employer employer) {
		super();
		this.title = title;
		this.employer = employer;
	}
}

package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Comment implements Model{
	
	@Id
	@GeneratedValue
	private int id;
	@Lob
	private String comment;
	@ManyToOne
	private User user;
	
	public Comment(){}
	
	public Comment(String comment, User user) {
		super();
		this.comment = comment;
		this.user = user;
	}
}

package ch.issueman.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Comment implements Model{
	
	@Id
	@GeneratedValue
	private int id;
	@Lob
	private String comment;
	@OneToOne
	private User user;
	
	public Comment(){}
	
	public Comment(String comment, User user) {
		super();
		this.comment = comment;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

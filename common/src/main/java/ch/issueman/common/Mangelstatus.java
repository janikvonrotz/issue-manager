package ch.issueman.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
*/

@Entity
@Data
public class Mangelstatus {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String status;
	@NotNull
	@ManyToMany
	private List<Rolle> rolle;
	
	public Mangelstatus() {}
	
	public Mangelstatus(String status, List<Rolle> rolle) {
		this.status = status;
		this.rolle = rolle;
	}

}

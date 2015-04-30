package ch.issueman.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@SuppressWarnings("serial")
@Entity
@Data
public class Mangelstatus implements Model {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String status;
	@NotNull
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Rolle> rollen;
	
	public Mangelstatus() {}
	
	public Mangelstatus(String status, List<Rolle> rollen) {
		this.status = status;
		this.rollen = rollen;
	}

}

/**
 * 
 */
package ch.issueman.common;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 * 
 * @author TODO
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Sachbearbeiter extends Person {

	/* (non-Javadoc)
	 * @see ch.issueman.common.Person#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

}

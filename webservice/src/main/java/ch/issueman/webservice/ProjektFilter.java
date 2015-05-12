package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Projekt;

/**
 * Filter Projects by properties and login roles.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProjektFilter extends TypeFilter<Projekt, Integer> {

	public ProjektFilter() {
		super(Projekt.class);
	}

	@Override
	public List<Projekt> getAll() {
		if(this.ifUserHasRole("Sachbearbeiter")){
			return this.getController().getAll();
		}else{
			return this.getController().getAll().stream()
					.filter(p -> p.getArchiviert() == false)
					.collect(Collectors.toList());
		}
	}
}

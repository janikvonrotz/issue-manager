package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Projekt;
import ch.issueman.common.Kontakt;

/**
 * Filter Projects by properties and login roles.
 * 
 * @author Janik von Rotz, Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProjektFilter extends TypeFilter<Projekt, Integer> {

	public ProjektFilter() {
		super(Projekt.class);
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getAll()
	 */
	@Override
	public List<Projekt> getAll() {
		if(this.ifUserHasRole("Sachbearbeiter")){
			return this.getController().getAll();
		}else if(this.getLogin().getPerson() instanceof Kontakt){
			return this.getController().getAll().stream()
					.filter(p -> p.getArchiviert() == false && ((Kontakt) this.getLogin().getPerson()).getProjekte().contains(p))
					.collect(Collectors.toList());	
		}else{
			return this.getController().getAll().stream()
					.filter(p -> p.getArchiviert() == false)
					.collect(Collectors.toList());
		}
	}


	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getAllByProperty(java.lang.String, java.util.List)
	 */
	@Override
	public List<Projekt> getAllByProperty(String propertyname,
			List<String> propertyvalues) throws Exception {
		
		if(this.ifUserHasRole("Sachbearbeiter")){
			return this.getController().getAllByProperty(propertyname, propertyvalues);
		}else if(this.getLogin().getPerson() instanceof Kontakt){
			return this.getController().getAllByProperty(propertyname, propertyvalues).stream()
					.filter(p -> p.getArchiviert() == false && ((Kontakt) this.getLogin().getPerson()).getProjekte().contains(p))
					.collect(Collectors.toList());	
		}else{
			return this.getController().getAllByProperty(propertyname, propertyvalues).stream()
					.filter(p -> p.getArchiviert() == false)
					.collect(Collectors.toList());
		}
	}
}

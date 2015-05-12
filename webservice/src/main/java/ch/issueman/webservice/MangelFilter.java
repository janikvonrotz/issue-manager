package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Mangel;
import ch.issueman.common.Kontakt;

/**
 * Filter Projects by mangel and login roles.
 * 
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */
public class MangelFilter extends TypeFilter<Mangel, Integer> {

	public MangelFilter() {
		super(Mangel.class);
	}

	@Override
	public List<Mangel> getAll() {
		if(this.getLogin().getPerson() instanceof Kontakt){
			return this.getController().getAll().stream()
					.filter(m -> m.getSubunternehmen().equals(((Kontakt) this.getLogin().getPerson()).getSubunternehmen()))
					.collect(Collectors.toList());
		}else{
			return this.getController().getAll();
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getAllByProperty(java.lang.String, java.util.List)
	 */
	@Override
	public List<Mangel> getAllByProperty(String propertyname,List<String> propertyvalues) throws Exception {
		if(this.getLogin().getPerson() instanceof Kontakt){
			return this.getController().getAllByProperty(propertyname, propertyvalues).stream()
					.filter(m -> m.getSubunternehmen().equals(((Kontakt) this.getLogin().getPerson()).getSubunternehmen()))
					.collect(Collectors.toList());
		}else{
			return this.getController().getAllByProperty(propertyname, propertyvalues);
		}
	}
}

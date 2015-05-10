package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Mangel;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Subunternehmen;

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

//	@Override
//	public List<Mangel> getAll() {
//		if(this.ifUserHasRole("Sachbearbeiter")){
//			return this.getController().getAll();
//		}else if(Mangel.getSubunternehmen() == Kontakt.getSubunternehmen()){
//			return this.getController().getAll().stream()
//					.filter(Mangel -> Mangel.equals(this.Mangel))
//					.collect(Collectors.toList());
//		}
//	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getById(java.io.Serializable)
	 */
	@Override
	public Mangel getById(Integer id) throws Exception {
		return super.getById(id);
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#delete(java.lang.Object)
	 */
	@Override
	public void delete(Mangel t) throws Exception {
		if(this.ifUserHasRole("Sachbearbeiter")){
			super.delete(t);
		}else{
			throw new Exception("Not allowed to delete Mangel.");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getAllByProperty(java.lang.String, java.util.List)
	 */
//	@Override
//	public List<Mangel> getAllByProperty(String propertyname,
//			List<String> propertyvalues) throws Exception {
//		if(this.ifUserHasRole("Sachbearbeiter")){
//			return this.getController().getAllByProperty(propertyname, propertyvalues);
//		}else if(this.getMangel().){
//			super.getAllByProperty(propertyname, propertyvalues);
//		}else{
//			throw new Exception("Not allowed to update Login.")
//		}
//		return super.getAllByProperty(propertyname, propertyvalues);
//	}


}

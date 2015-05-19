package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Login;


/**
 * Filter Projects by properties and login roles.
 * 
 * @author Janik von Rotz, Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoginFilter extends TypeFilter<Login, Integer> {

	public LoginFilter() {
		super(Login.class);
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getAll()
	 */
	@Override
	public List<Login> getAll() {
		if(this.ifUserHasRole("Sachbearbeiter")){
			return this.getController().getAll();
		}else{
			return this.getController().getAll().stream()
					.filter(l -> l.equals(this.getLogin()))
					.collect(Collectors.toList());
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getById(java.io.Serializable)
	 */
	@Override
	public Login getById(Integer id) throws Exception {
		if(this.ifUserHasRole("Sachbearbeiter")){
			return this.getController().getById(id);
		}else if(this.getLogin().getId()== id){
			return this.getController().getById(id);
		}else{
			throw new Exception("Not allowed to show Login.");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#update(java.lang.Object)
	 */
	@Override
	public void update(Login t) throws Exception {
		if(this.ifUserHasRole("Sachbearbeiter")){
			this.getController().update(t);
		}else if(this.getLogin().getId()==t.getId()){
			this.getController().update(t);
		}else{
			throw new Exception("Not allowed to update Login.");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#delete(java.lang.Object)
	 */
	@Override
	public void delete(Login t) throws Exception {
		if(this.ifUserHasRole("Sachbearbeiter")){
			this.getController().delete(t);
		}else if(this.getLogin().getId()==t.getId()){
			this.getController().delete(t);
		}else{
			throw new Exception("Not allowed to delete Login.");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#deleteAll()
	 */
	@Override
	public void deleteAll() throws Exception {
		if(this.ifUserHasRole("Sachbearbeiter")){
			this.getController().deleteAll();
		}else{
			throw new Exception("Not allowed to delete Login.");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.TypeFilter#getAllByProperty(java.lang.String, java.util.List)
	 */
	@Override
	public List<Login> getAllByProperty(String propertyname, List<String> propertyvalues) throws Exception {
		if(this.ifUserHasRole("Sachbearbeiter")){
			return this.getController().getAllByProperty(propertyname, propertyvalues);
		}else{
			return this.getController().getAllByProperty(propertyname, propertyvalues).stream()
					.filter(l -> l.equals(this.getLogin()))
					.collect(Collectors.toList());
		}
	}
}

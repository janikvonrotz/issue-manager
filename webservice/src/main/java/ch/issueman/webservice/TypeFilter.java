package ch.issueman.webservice;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import ch.issueman.common.ConfigHelper;
import ch.issueman.common.DAO;
import ch.issueman.common.Login;

/**
 * Filter entities and check access rights by roles and http methods.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
@Data
public class TypeFilter<T, Id extends Serializable> implements DAO<T, Id>  {
	
	private HashMap<String, List<String>> allowedroles = new HashMap<String, List<String>>();
	private Login login;
	private Controller<T, Id> controller;
		
	public TypeFilter(Class<T> clazz){
		this.controller = new Controller<T, Id>(clazz);
		if(ConfigHelper.hasPath("permissions." + clazz.getSimpleName())){
			allowedroles.put("GET", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".GET", new String[]{}));
			allowedroles.put("POST", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".POST", new String[]{}));
			allowedroles.put("PUT", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".PUT", new String[]{}));
			allowedroles.put("DELETE", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".DELETE", new String[]{}));
		}else{
			allowedroles.put("GET", ConfigHelper.getConfig("permissions.Default.GET", new String[]{"Authenticated"}));
			allowedroles.put("POST", ConfigHelper.getConfig("permissions.Default.POST", new String[]{"Authenticated"}));
			allowedroles.put("PUT", ConfigHelper.getConfig("permissions.Default.PUT", new String[]{"Authenticated"}));
			allowedroles.put("DELETE", ConfigHelper.getConfig("permissions.Default.DELETE", new String[]{"Authenticated"}));
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 */
	@Override
	public void persist(T t) throws Exception {
		controller.persist(t);
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 */
	@Override
	public T getById(Id id) throws Exception {
		return controller.getById(id);
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAll()
	 */
	public List<T> getAll() throws Exception {
		return controller.getAll();
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(T t) throws Exception {
		controller.update(t);
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) throws Exception {
		controller.delete(t);
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#deleteAll()
	 */
	@Override
	public void deleteAll() throws Exception {
		controller.deleteAll();
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws Exception {
		// TODO implement
		return null;
	}
	
	/**
	 * Checks whether a login has the roles to access a certain http method for this filter. 
	 * 
	 * @param httpmethod the http method to be executed.
	 * @return boolean true if the user has the right to execute the method.
	 */
	public boolean ifUserHasRoleByMethod(String httpmethod){
		if(allowedroles.get(httpmethod).contains("Anonymous")){
			return true;
		}else if(login != null && allowedroles.get(httpmethod).contains("Authenticated")){
			return true;
		}else if(login != null && allowedroles.get(httpmethod).contains(login.getRolle().getBezeichnung())){
			return true;
		}		
		return false;
	}
	
	/**
	 * Check if login has role.
	 * 
	 * @param rolle to role to check.
	 * @return true if login has role.
	 */
	public boolean ifUserHasRole(String rolle){
		return this.login.getRolle().equals(rolle);
	}
	
	/**
	 * Check if login has one of the roles.
	 * 
	 * @param rollen the role array to check
	 * @return true if login has on of the roles.
	 */
	public boolean ifUserHasRole(String[] rollen){
		return Arrays.asList(rollen).contains(this.login.getRolle());
	}
}

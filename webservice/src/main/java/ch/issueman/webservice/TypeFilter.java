package ch.issueman.webservice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import lombok.Data;
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
	
	private final Config config = ConfigFactory.load();
	private HashMap<String, List<String>> allowedroles = new HashMap<String, List<String>>();
	private Login login;
	private Controller<T, Id> controller;
		
	public TypeFilter(Class<T> clazz){
		this.controller = new Controller<T, Id>(clazz);
		if(config.hasPath("permissions." + clazz.getSimpleName())){
			allowedroles.put("GET", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".GET", new String[]{}));
			allowedroles.put("POST", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".POST", new String[]{}));
			allowedroles.put("PUT", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".PUT", new String[]{}));
			allowedroles.put("DELETE", ConfigHelper.getConfig("permissions." + clazz.getSimpleName() + ".DELETE", new String[]{}));
		}else{
			allowedroles.put("GET", config.getStringList("permissions.Default.GET"));
			allowedroles.put("POST", config.getStringList("permissions.Default.POST"));
			allowedroles.put("PUT", config.getStringList("permissions.Default.PUT"));
			allowedroles.put("DELETE", config.getStringList("permissions.Default.DELETE"));
		}
	}
	
	@Override
	public void persist(T t) throws Exception {
		controller.persist(t);
	}

	@Override
	public T getById(Id id) throws Exception {
		return controller.getById(id);
	}

	public List<T> getAll() throws Exception {
		return controller.getAll();
	}
	
	@Override
	public void update(T t) throws Exception {
		controller.update(t);
	}

	@Override
	public void delete(T t) throws Exception {
		controller.delete(t);
	}

	@Override
	public void deleteAll() throws Exception {
		controller.deleteAll();
	}
	
	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Checks whether a login has the roles to access a certain http method for this filter. 
	 * 
	 * @param login the login having the roles.
	 * @param httpmethod the http method to be executed.
	 * @return boolean true if the user has the right to execute the method.
	 */
	public boolean ifUserHasRoleByMethod(Login login, String httpmethod){
		
		if(allowedroles.get(httpmethod).contains("Anonymous")){
			return true;
		}else if(login != null && allowedroles.get(httpmethod).contains("Authenticated")){
			return true;
		}else if(login != null && allowedroles.get(httpmethod).contains(login.getRolle().getBezeichnung())){
			return true;
		}		
		return false;
	}
}

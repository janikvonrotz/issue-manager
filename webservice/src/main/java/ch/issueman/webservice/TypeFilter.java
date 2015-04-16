package ch.issueman.webservice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import lombok.Data;
import ch.issueman.common.DAO;
import ch.issueman.common.User;

@Data
public class TypeFilter<T, Id extends Serializable> implements DAO<T, Id>  {
	
	private final Config config = ConfigFactory.load();
	private HashMap<String, List<String>> allowedroles = new HashMap<String, List<String>>();
	private User user;
	private Controller<T, Id> controller;
		
	public TypeFilter(Class<T> clazz){
		this.controller = new Controller<T, Id>(clazz);
		allowedroles.put("GET", config.getStringList("permissions." + clazz.getSimpleName() + ".GET"));
		allowedroles.put("POST", config.getStringList("permissions." + clazz.getSimpleName() + ".POST"));
		allowedroles.put("PUT", config.getStringList("permissions." + clazz.getSimpleName() + ".PUT"));
		allowedroles.put("DELETE", config.getStringList("permissions." + clazz.getSimpleName() + ".DELETE"));
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
	
	public boolean UserHasRoleByMethod(User user, String httpmethod){
		
		if(allowedroles.get(httpmethod).contains("Anonymous")){
			return true;
		}else if(user != null && allowedroles.get(httpmethod).contains("Authenticated")){
			return true;
		}else if(user != null && allowedroles.get(httpmethod).contains(user.getRole())){
			return true;
		}		
		
		return false;
	}
}

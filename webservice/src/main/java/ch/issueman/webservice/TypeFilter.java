package ch.issueman.webservice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import lombok.Data;
import ch.issueman.common.User;

@Data
public class TypeFilter<T, Id extends Serializable>  {
	
	private Config config = ConfigFactory.load();
	private HashMap<String, String> allowedroles = new HashMap<String, String>();
	
	public List<T> getAllByUser(Controller<T, Id> controller, User user){
		return controller.getAll();
	};
	
	public TypeFilter(Class<T> clazz){
		// TODO Load roles from config
		allowedroles.put("GET", config.getString("permissions." + clazz.getSimpleName() + ".GET"));
	}

	public boolean UserHasRoleByMethod(User user, String httpmethod){
		// TODO check role, return for aAthenticated and Anonymous user roles		
		return false;
	}
}

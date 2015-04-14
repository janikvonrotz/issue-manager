package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ch.issueman.common.DAO;
import ch.issueman.common.User;
import ch.issueman.webservice.Controller;

public class ResponseFilter<T, Id extends Serializable> implements DAO<T, Id>{
	
	private TypeFilter<T, Id> filter = null;
	private Controller<T, Id> controller = null;
	private final Class<T> clazz;
	private User user;
	private String httpmethod;
	
	@SuppressWarnings("unchecked")
	public ResponseFilter(Class<T> clazz, String httpmethod){
		this.clazz = clazz;
		controller = new Controller<T, Id>(clazz);
		this.httpmethod = httpmethod;
		filter = new TypeFilter<T, Id>(clazz);
		
		try {
			Class<?> filterclazz = Class.forName("ch.issueman.webservice." + clazz.getSimpleName() + "Filter");
			Constructor<?> constructor = filterclazz.getConstructor();
			filter = (TypeFilter<T, Id>) constructor.newInstance(new Object[] {});
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public ResponseFilter(Class<T> clazz, String httpmethod, User user){
		this(clazz, httpmethod);
		this.user = user;
	}

	// TODO build complex responses with Exceptions and role checking

	public void persist(Object t) {
		if(filter.UserHasRoleByMethod(user, httpmethod) != false){
			
		}else{
			
		}
	}

	public T getById(Serializable id) {
		return null;
	}

	public List<T> getAll() {
		System.out.println("Mail: " + user.getEmail());
		return (List<T>) filter.getAllByUser(controller, user);
	}

	public void update(Object t) {
		
	}

	public void delete(Object t) {
		
	}

	public void deleteAll() {
		
	}
}

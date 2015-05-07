package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ch.issueman.common.Login;

/**
 * Applies logic and rules to the basic controller.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
@SuppressWarnings("serial")
@Slf4j
public class BusinessController<T, Id extends Serializable> extends UnicastRemoteObject implements DAORmi<T, Id> {
	
	private TypeFilter<T, Id> filter;
	private Controller<T, Id> controller;
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BusinessController(Class<T> clazz) throws RemoteException{
		controller = new Controller<T, Id>(clazz);
		filter = new TypeFilter<T, Id>(clazz);
		this.clazz = clazz;
		
		try {
			Class<?> filterclazz = Class.forName("ch.issueman.webservice." + clazz.getSimpleName() + "Filter");
			Constructor<?> constructor = filterclazz.getConstructor();
			filter = (TypeFilter<T, Id>) constructor.newInstance(new Object[] {});
			filter.setController(controller);
			log.info("Custom type filter for " + clazz.getSimpleName() + " found.");
			
		} catch (ClassNotFoundException e) {
			// log.info("No custom type filter for " + clazz.getSimpleName() + " found.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}	
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#persist(java.lang.Object)
	 */
	@Override
	public void persist(T t) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod("POST") != false){
			filter.persist(t);
		}else{
			throw new Exception("Required Roles for POST on " + clazz.getSimpleName() + " don't match");
		}		
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#getById(java.lang.Object)
	 */
	@Override
	public T getById(Id id) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod("GET") != false){
			return filter.getById(id);
		}else{
			throw new Exception("Required Roles for GET on " + clazz.getSimpleName() + " don't match");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#getAll()
	 */
	@Override
	public List<T> getAll() throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod("GET") != false){
			return filter.getAll();
		}else{
			throw new Exception("Required Roles for GET on " + clazz.getSimpleName() + " don't match");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#update(java.lang.Object)
	 */
	@Override
	public void update(T t) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod("POST") != false){
			filter.update(t);
		}else{
			throw new Exception("Required Roles for POST on " + clazz.getSimpleName() + " don't match");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod("DELETE") != false){
			filter.delete(t);
		}else{
			throw new Exception("Required Roles for DELETE on " + clazz.getSimpleName() + " don't match");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#deleteAll()
	 */
	@Override
	public void deleteAll() throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod("DELETE") != false){
			filter.deleteAll();
		}else{
			throw new Exception("Required Roles for DELETE on " + clazz.getSimpleName() + " don't match");
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#setLogin(ch.issueman.common.Login)
	 */
	@Override
	public void setLogin(Login login) throws RemoteException, Exception {
	
		List<Login> logins = new ArrayList<Login>();
		try{
			logins = (new Controller<Login, Integer>(Login.class)).getAll().stream()
					.filter(l -> l.getUsername().equals(login.getUsername()))
					.filter(l -> l.getPasswort().equals(login.getPasswort()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}			
	
		if(logins.size() > 0 && logins.get(0) != null){
			filter.setLogin(logins.get(0));
		}else{
			filter.setLogin(null);
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#signin()
	 */
	@Override
	public Login signin() throws RemoteException, Exception{
		return filter.getLogin();
	}
}

package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Login;

public class BusinessController<T, Id extends Serializable> extends UnicastRemoteObject implements DAORmi<T, Id> {
	
	private TypeFilter<T, Id> filter = null;
	private Controller<T, Id> controller = null;
	private Class<T> clazz = null;
	private Login login;
	
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
			filter.setLogin(login);
			
		} catch (ClassNotFoundException e) {
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void persist(T t) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod(login, "POST") != false){
			filter.persist(t);
		}else{
			throw new Exception("Required Roles for POST on " + clazz.getSimpleName() + " don't match");
		}		
	}

	@Override
	public T getById(Id id) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod(login, "GET") != false){
			return filter.getById(id);
		}else{
			throw new Exception("Required Roles for GET on " + clazz.getSimpleName() + " don't match");
		}
	}

	@Override
	public List<T> getAll() throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod(login, "GET") != false){
			return filter.getAll();
		}else{
			throw new Exception("Required Roles for GET on " + clazz.getSimpleName() + " don't match");
		}
	}

	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues)
			throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(T t) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod(login, "POST") != false){
			filter.update(t);
		}else{
			throw new Exception("Required Roles for POST on " + clazz.getSimpleName() + " don't match");
		}
	}

	@Override
	public void delete(T t) throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod(login, "DELETE") != false){
			filter.delete(t);
		}else{
			throw new Exception("Required Roles for DELETE on " + clazz.getSimpleName() + " don't match");
		}
	}

	@Override
	public void deleteAll() throws RemoteException, Exception {
		if(filter.ifUserHasRoleByMethod(login, "DELETE") != false){
			filter.deleteAll();
		}else{
			throw new Exception("Required Roles for DELETE on " + clazz.getSimpleName() + " don't match");
		}
	}

	@Override
	public Login signin() throws RemoteException, Exception {
		List<Login> logins = (new Controller<Login, Integer>(Login.class)).getAll().stream()
				.filter(l -> l.getUsername().equals(login.getUsername()))
				.filter(l -> l.getPasswort().equals(login.getPasswort()))
				.collect(Collectors.toList());
		
		if(logins.size() == 1){
			return logins.get(0);
		}else{
			throw new Exception("Signin failed");
		}
	}

	@Override
	public void setLogin(Login login) throws RemoteException, Exception {
		this.login = login;
	}	
}

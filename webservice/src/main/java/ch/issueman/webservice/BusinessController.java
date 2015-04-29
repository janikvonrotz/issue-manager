package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.ws.rs.core.Response;

import ch.issueman.common.Login;

public class BusinessController<T, Id extends Serializable> extends UnicastRemoteObject implements DAOResponseBuilder<T, Id> {
	
	private TypeFilter<T, Id> filter = null;
	private Controller<T, Id> controller = null;
	private Login login;
	
	@SuppressWarnings("unchecked")
	public BusinessController(Class<T> clazz) throws RemoteException{
		controller = new Controller<T, Id>(clazz);
		filter = new TypeFilter<T, Id>(clazz);
		
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
	public Response persist(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getById(Id id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAllByProperty(String propertyname,
			Object[] propertyvalues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response update(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response delete(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogin(Login login) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Response signin(Login login) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteById(Id id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

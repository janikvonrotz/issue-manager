package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ch.issueman.common.Login;
import ch.issueman.webservice.Controller;

/**
 * Build http compatible response for the route requests.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseBuilder<T, Id extends Serializable> extends UnicastRemoteObject implements DAOResponseBuilder<T, Id>{
	
	private TypeFilter<T, Id> filter = null;
	private Controller<T, Id> controller = null;
	private Login login;
	
	@SuppressWarnings("unchecked")
	public ResponseBuilder(Class<T> clazz) throws RemoteException{
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
	
	public void setUser(Login login) throws RemoteException{
		this.login = login;
		this.filter.setLogin(login);
	}
	
	public ResponseBuilder(Class<T> clazz, String httpmethod, Login login) throws RemoteException{
		this(clazz);
		this.login = login;
	}
	
	public Response persist(T t) throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "POST") != false){
			try {
				filter.persist(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enity added.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for POST don't match")).build();
		}
	}

	public Response getById(Id id) throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "GET") != false){
			try {
				return Response.status(Status.OK).entity(filter.getById(id)).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for GET don't match")).build();
		}
	}

	public Response getAll() throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "GET") != false){
			try {
				return Response.status(Status.OK).entity((List<T>) filter.getAll()).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for GET don't match")).build();
		}
	}

	public Response update(T t) throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "PUT") != false){
			try {
				filter.update(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enity updated.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for PUT don't match")).build();
		}
	}

	public Response delete(T t) throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "DELETE") != false){
			try {
				filter.delete(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enity deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for DELETE don't match")).build();
		}
	}

	public Response deleteAll() throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "DELETE") != false){
			try {
				filter.deleteAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enities deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for DELETE don't match")).build();
		}
	}

	public Response deleteById(Id id) throws RemoteException{
		if(filter.ifUserHasRoleByMethod(login, "DELETE") != false){
			try {
				filter.delete(filter.getById(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enities deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Required Roles for DELETE don't match")).build();
		}
	}

	public Response signin(Login login) throws RemoteException{
		
		List<Login> logins = (new Controller<Login, Integer>(Login.class)).getAll().stream()
				.filter(l -> l.getUsername().equals(login.getUsername()))
				.filter(l -> l.getPasswort().equals(login.getPasswort()))
				.collect(Collectors.toList());
		
		if(logins.size() == 1){
			return Response.status(Status.OK).entity(logins.get(0)).build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity(new Exception("Login failed!")).build();
		}
	}
}

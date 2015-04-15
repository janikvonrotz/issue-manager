package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.Data;
import ch.issueman.common.User;
import ch.issueman.webservice.Controller;

@Data
public class ResponseBuilder<T, Id extends Serializable> implements DAOResponseBuilder<T, Id>{
	
	private TypeFilter<T, Id> filter = null;
	private Controller<T, Id> controller = null;
	private User user;
	
	@SuppressWarnings("unchecked")
	public ResponseBuilder(Class<T> clazz){
		controller = new Controller<T, Id>(clazz);
		filter = new TypeFilter<T, Id>(clazz);
		
		try {
			Class<?> filterclazz = Class.forName("ch.issueman.webservice." + clazz.getSimpleName() + "Filter");
			
			Constructor<?> constructor = filterclazz.getConstructor();
			filter = (TypeFilter<T, Id>) constructor.newInstance(new Object[] {});
			filter.setController(controller);
			filter.setUser(user);
			
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
	
	public void setUser(User user){
		this.user = user;
		this.filter.setUser(user);
	}
	
	public ResponseBuilder(Class<T> clazz, String httpmethod, User user){
		this(clazz);
		this.user = user;
	}
	
	public Response persist(T t) {
		if(filter.UserHasRoleByMethod(user, "POST") != false){
			try {
				filter.persist(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enity added.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for POST don't match").build();
		}
	}

	public Response getById(Id id) {
		if(filter.UserHasRoleByMethod(user, "GET") != false){
			try {
				return Response.status(Status.OK).entity(filter.getById(id)).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for GET don't match").build();
		}
	}

	public Response getAll() {
		if(filter.UserHasRoleByMethod(user, "GET") != false){
			try {
				return Response.status(Status.OK).entity((List<T>) filter.getAll()).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for GET don't match").build();
		}
	}

	public Response update(T t) {
		if(filter.UserHasRoleByMethod(user, "PUT") != false){
			try {
				filter.update(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enity updated.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for PUT don't match").build();
		}
	}

	public Response delete(T t) {
		if(filter.UserHasRoleByMethod(user, "DELETE") != false){
			try {
				filter.delete(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enity deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for DELETE don't match").build();
		}
	}

	public Response deleteAll() {
		if(filter.UserHasRoleByMethod(user, "DELETE") != false){
			try {
				filter.deleteAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enities deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for DELETE don't match").build();
		}
	}

	public Response deleteById(Id id) {
		if(filter.UserHasRoleByMethod(user, "DELETE") != false){
			try {
				filter.delete(filter.getById(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.status(Status.OK).entity("Enities deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for DELETE don't match").build();
		}
	}

	public Response login(User user) {
		List<User> users = (new Controller<User, Integer>(User.class)).getAll().stream()
				.filter(p -> p.getEmail().equals(user.getEmail()))
				.filter(p -> p.getPassword().equals(user.getPassword()))
				.collect(Collectors.toList());
		
		if(users.size() == 1){
			return Response.status(Status.OK).entity(users.get(0)).build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Login failed!").build();
		}
	}
}

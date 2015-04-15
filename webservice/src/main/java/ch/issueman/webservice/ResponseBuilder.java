package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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
			filter.persist(t);
			return Response.status(Status.OK).entity("Enity added.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for POST don't match").build();
		}
	}

	public Response getById(Id id) {
		if(filter.UserHasRoleByMethod(user, "GET") != false){
			return Response.status(Status.OK).entity(filter.getById(id)).build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for GET don't match").build();
		}
	}

	public Response getAll() {
		if(filter.UserHasRoleByMethod(user, "GET") != false){
			return Response.status(Status.OK).entity((List<T>) filter.getAll()).build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for GET don't match").build();
		}
	}

	public Response update(T t) {
		if(filter.UserHasRoleByMethod(user, "PUT") != false){
			filter.update(t);
			return Response.status(Status.OK).entity("Enity updated.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for PUT don't match").build();
		}
	}

	public Response delete(T t) {
		if(filter.UserHasRoleByMethod(user, "DELETE") != false){
			filter.delete(t);
			return Response.status(Status.OK).entity("Enity deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for DELETE don't match").build();
		}
	}

	public Response deleteAll() {
		if(filter.UserHasRoleByMethod(user, "DELETE") != false){
			filter.deleteAll();
			return Response.status(Status.OK).entity("Enities deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for DELETE don't match").build();
		}
	}

	public Response deleteById(Id id) {
		if(filter.UserHasRoleByMethod(user, "DELETE") != false){
			filter.delete(filter.getById(id));
			return Response.status(Status.OK).entity("Enities deleted.").build();
		}else{
			return Response.status(Status.UNAUTHORIZED).entity("Required Roles for DELETE don't match").build();
		}
	}
}

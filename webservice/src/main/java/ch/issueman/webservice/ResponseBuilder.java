package ch.issueman.webservice;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ch.issueman.common.Adresse;
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

@Data
public class ResponseBuilder<T, Id extends Serializable> implements DAOResponseBuilder<T, Id>{
	
	private DAORmi<T, Id> controller;
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public ResponseBuilder(Class<T> clazz){
		this.clazz = clazz;
		try {
			controller = (DAORmi<T, Id>) Naming.lookup("rmi://" + ConfigHelper.getConfig("rmi.host", "localhost") + ":" + ConfigHelper.getConfig("rmi.port", 1099) + "/" + clazz.getSimpleName().toLowerCase());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Response persist(T t){
		try {
			controller.persist(t);
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " added.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response getById(Id id) {
		try {
			return Response.status(Status.OK).entity(controller.getById(id)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(controller.getAll()).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response getAllByProperty(String propertyname, Object[] propertyvalues) {
		try {
			return Response.status(Status.OK).entity(controller.getAllByProperty(propertyname, propertyvalues)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response update(T t) {
		try {
			controller.update(t);
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " updated.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response delete(T t) {
		try {
			controller.delete(t);
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " deleted.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response deleteAll() {
		try {
			controller.deleteAll();;
			return Response.status(Status.OK).entity("All " + clazz.getSimpleName() + " deleted.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public void setLogin(Login login) {
		try {
			controller.setLogin(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Response signin() {
		try{
			return Response.status(Status.OK).entity(controller.signin()).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	@Override
	public Response deleteById(Id id) {
		try {
			Response response = getById(id);
			controller.delete(response.readEntity(clazz));
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " deleted.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}
}

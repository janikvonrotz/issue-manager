package ch.issueman.webservice;

import java.io.Serializable;
import java.rmi.Naming;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.ConfigHelper;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

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
@Slf4j
public class ResponseBuilder<T, Id extends Serializable> implements DAOResponseBuilder<T, Id>{
	
	private DAORmi<T, Id> controller;
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public ResponseBuilder(Class<T> clazz){
		this.clazz = clazz;
		try {
			controller = (DAORmi<T, Id>) Naming.lookup("rmi://" + ConfigHelper.getConfig("rmi.host", "localhost") + ":" + ConfigHelper.getConfig("rmi.port", 1099) + "/" + clazz.getSimpleName().toLowerCase());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#persist(java.lang.Object)
	 */
	@Override
	public Response persist(T t){
		try {
			controller.persist(t);
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " added.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getById(java.lang.Object)
	 */
	@Override
	public Response getById(Id id) {
		try {
			return Response.status(Status.OK).entity(controller.getById(id)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getAll()
	 */
	@Override
	public Response getAll() {
		try {
			List<T> list = (List<T>) controller.getAll();
			GenericEntity<List<T>> genericEntity = new GenericEntity<List<T>>(list){};
			return Response.ok().entity(genericEntity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Response getAllByProperty(String propertyname, List<String> propertyvalues) {
		try {
			return Response.status(Status.OK).entity(controller.getAllByProperty(propertyname, propertyvalues)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#update(java.lang.Object)
	 */
	@Override
	public Response update(T t) {
		try {
			controller.update(t);
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " updated.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#delete(java.lang.Object)
	 */
	@Override
	public Response delete(T t) {
		try {
			controller.delete(t);
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " deleted.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#deleteAll()
	 */
	@Override
	public Response deleteAll() {
		try {
			controller.deleteAll();;
			return Response.status(Status.OK).entity("All " + clazz.getSimpleName() + " deleted.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#setLogin(ch.issueman.common.Login)
	 */
	@Override
	public void setLogin(Login login) {
		try {
			controller.setLogin(login);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#signin()
	 */
	@Override
	public Response signin() {
		try {
			return Response.status(Status.OK).entity(controller.signin()).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(new Exception("Signin failed")).build();
		}
	}

	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#deleteById(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Response deleteById(Id id) {
		try {
			Response response = getById(id);
			controller.delete((T) response.getEntity());
			return Response.status(Status.OK).entity(clazz.getSimpleName() + " deleted.").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getAllBauleiter()
	 */
	public Response getAllBauleiter() {
		try {
			@SuppressWarnings("unchecked")
			List<Bauleiter> list = (List<Bauleiter>) controller.getAll();
			GenericEntity<List<Bauleiter>> genericEntity = new GenericEntity<List<Bauleiter>>(list){};
			return Response.ok().entity(genericEntity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getAllSachbearbeiter()
	 */
	public Response getAllSachbearbeiter() {
		try {
			@SuppressWarnings("unchecked")
			List<Sachbearbeiter> list = (List<Sachbearbeiter>) controller.getAll();
			GenericEntity<List<Sachbearbeiter>> genericEntity = new GenericEntity<List<Sachbearbeiter>>(list){};
			return Response.ok().entity(genericEntity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getAllBauherr()
	 */
	public Response getAllBauherr() {
		try {
			@SuppressWarnings("unchecked")
			List<Bauherr> list = (List<Bauherr>) controller.getAll();
			GenericEntity<List<Bauherr>> genericEntity = new GenericEntity<List<Bauherr>>(list){};
			return Response.ok().entity(genericEntity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAOResponseBuilder#getAllKontakt()
	 */
	public Response getAllKontakt() {
		try {
			@SuppressWarnings("unchecked")
			List<Kontakt> list = (List<Kontakt>) controller.getAll();
			GenericEntity<List<Kontakt>> genericEntity = new GenericEntity<List<Kontakt>>(list){};
			return Response.ok().entity(genericEntity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e).build();
		}
	}
}

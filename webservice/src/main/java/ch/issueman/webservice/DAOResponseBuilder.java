package ch.issueman.webservice;

import java.rmi.Remote;
import javax.ws.rs.core.Response;

import ch.issueman.common.Login;

/**
 * interface Data Access Object for ResponseBuilder.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
public interface DAOResponseBuilder<T, Id> extends Remote{
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 */
	public Response persist(T t);
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 */
	public Response getById(Id id);
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	public Response getAllByProperty(String propertyname, Object[] propertyvalues);
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAll()
	 */
	public Response getAll();
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 */
	public Response update(T t);
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 */
	public Response delete(T t);
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#deleteAll()
	 */
	public Response deleteAll();
	
	/* (non-Javadoc)
	 * @see ch.issueman.webservice.DAORmi#setLogin(ch.issueman.common.Login)
	 */
	public void setLogin(Login login);
	
	/**
	 * @return
	 */
	public Response signin(Login login);
	
	/**
	 * @param id
	 * @return
	 */
	public Response deleteById(Id id);

	public Response getBauleiter();
}

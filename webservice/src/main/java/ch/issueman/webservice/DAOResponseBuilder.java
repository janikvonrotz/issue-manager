package ch.issueman.webservice;

import java.rmi.Remote;
import java.util.List;

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
	
	/**
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 * 
	 * @param t the entity to persist.
	 * @return http response.
	 */
	public Response persist(T t);
	
	/* (non-Javadoc)
	 * 
	 */
	/**
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 * 
	 * @param id the of the entity to get.
	 * @return http response containing the entity.
	 */
	public Response getById(Id id);
	
	/**
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 * 
	 * @param propertyname the name of the entity property.
	 * @param propertyvalues the value of the entity property.
	 * @return http response containing entites that matched the property definition.
	 */
	public Response getAllByProperty(String propertyname, List<String> propertyvalues);
	
	/**
	 * @see ch.issueman.common.DAO#getAll()
	 * 
	 * @return http response containing all entites.
	 */
	public Response getAll();
	
	/**
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 * 
	 * @param t the entity to update.
	 * @return http response.
	 */
	public Response update(T t);
	
	/**
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 * 
	 * @param t the entity to delete.
	 * @return http response.
	 */
	public Response delete(T t);
	
	/**
	 * @see ch.issueman.common.DAO#deleteAll()
	 * 
	 * @return http response.
	 */
	public Response deleteAll();
	
	/**
	 * @see ch.issueman.webservice.DAORmi#signin()
	 * 
	 * @param login the login to store in the server user context.
	 */
	public void setLogin(Login login);
	
	/**
	 * Returns a http response containing the validated login with all attributes.
	 * 
	 * @return http response containing the login object.
	 */
	public Response signin();
	
	/**
	 * Delete an entity by it's id.
	 * 
	 * @param id the id of the entity to delete.
	 * @return http response.
	 */
	public Response deleteById(Id id);

	/**
	 * Return all Bauleiter objects as generic entity in order to perceive the type info.
	 * 
	 * @return http response.
	 */
	public Response getAllBauleiter();

	/**
	 * Return all Sachbearbeiter objects as generic entity in order to perceive the type info.
	 * 
	 * @return http response.
	 */
	public Response getAllSachbearbeiter();

	/**
	 * Return all Kontakt objects as generic entity in order to perceive the type info.
	 * 
	 * @return http response.
	 */
	public Response getAllKontakt();

	/**
	 * Return all Bauherr objects as generic entity in order to perceive the type info.
	 * 
	 * @return http response.
	 */
	public Response getAllBauherr();

	/**
	 * Return all Person objects as generic entity in order to perceive the type info.
	 * 
	 * @return http response.
	 */
	public Response getAllPerson();
}

package ch.issueman.webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.issueman.common.Login;

/**
 * interface Data Access Object for RMI remote classes.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
public interface DAORmi <T, Id> extends Remote{
	
	/**
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 * 
	 * @param t the entity to persist.
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public void persist(T t) throws RemoteException, Exception;
	
	/**
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 * 
	 * @param id the id of the entity to get.
	 * @return entity with matching id.
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public T getById(Id id) throws RemoteException, Exception;
	
	/**
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 * 
	 * @param propertyname the name of the entity property.
	 * @param propertyvalues the value of the entity property.
	 * @return list of matching entities.
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws RemoteException, Exception;
	
	/**
	 * @see ch.issueman.common.DAO#getAll()
	 * 
	 * @return list of all entities.
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public List<T> getAll() throws RemoteException, Exception;
	
	/**
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 * 
	 * @param t the entity to update.
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public void update(T t) throws RemoteException, Exception;
	
	/**
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 * 
	 * @param t the entity do delete
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public void delete(T t) throws RemoteException, Exception;
	
	/**
	 * @see ch.issueman.common.DAO#deleteAll()
	 * 
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public void deleteAll() throws RemoteException, Exception;
	
	/**
	 * Stores login in the server user context.
	 * 
	 * @param login the login to store in the server user context.
	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public void setLogin(Login login) throws RemoteException, Exception;

	/**
	 * Returns a validated login from the server user context.
	 * 
	 * @return valid login or null reference for invalid logins.
 	 * @throws RemoteException if RMI fails to execute.
	 * @throws Exception if error occurs in the layers below.
	 */
	public Login signin() throws RemoteException, Exception;
}

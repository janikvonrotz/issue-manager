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
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 */
	public void persist(T t) throws RemoteException, Exception, Exception;
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 */
	public T getById(Id id) throws RemoteException, Exception;
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws RemoteException, Exception;
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAll()
	 */
	public List<T> getAll() throws RemoteException, Exception;
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 */
	public void update(T t) throws RemoteException, Exception;
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 */
	public void delete(T t) throws RemoteException, Exception;
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#deleteAll()
	 */
	public void deleteAll() throws RemoteException, Exception;
	
	/**
	 * @param login
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setLogin(Login login) throws RemoteException, Exception;
	
	/**
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Login signin() throws RemoteException, Exception;
}

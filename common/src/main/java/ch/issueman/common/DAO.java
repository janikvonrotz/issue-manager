package ch.issueman.common;

import java.io.Serializable;
import java.util.List;

/**
 * interface Generic Data Access Object
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
public interface DAO<T, Id extends Serializable> {
	
	/**
	 * @param t
	 * @throws Exception
	 */
	public void persist(T t) throws Exception;
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T getById(Id id) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<T> getAll() throws Exception;
	
	/**
	 * @param propertyname
	 * @param propertyvalues
	 * @return
	 * @throws Exception
	 */
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws Exception;
	
	/**
	 * @param t
	 * @throws Exception
	 */
	public void update(T t) throws Exception;
	
	/**
	 * @param t
	 * @throws Exception
	 */
	public void delete(T t) throws Exception;
	
	/**
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;
}

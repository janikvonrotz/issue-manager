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
	 * Persist an entity
	 * 
	 * @param t the entity to persist.
	 * @throws Exception if ORM fails to execute.
	 */
	public void persist(T t) throws Exception;
	
	/**
	 * Get entity by it's id.
	 * 
	 * @param id the id of the entity.
	 * @return return entiy by id.
	 * @throws Exception if ORM fails to execute.
	 */
	public T getById(Id id) throws Exception;
	
	/**
	 * Get all entities.
	 * 
	 * @return list of all entities.
	 * @throws Exception if ORM fails to execute.
	 */
	public List<T> getAll() throws Exception;
	
	/**
	 * Get entities by property name and values.
	 * 
	 * @param propertyname the name of the entity property.
	 * @param propertyvalues the value of the entity property.
	 * @return list of all matched entities.
	 * @throws Exception if ORM fails to execute.
	 */
	public List<T> getAllByProperty(String propertyname, List<String> propertyvalues) throws Exception;
	
	/**
	 * Update an entity.
	 * 
	 * @param t the entity to update.
	 * @throws Exception if ORM fails to execute.
	 */
	public void update(T t) throws Exception;
	
	/**
	 * Delete an entity.
	 * 
	 * @param t the entity to delete.
	 * @throws Exception if ORM fails to execute.
	 */
	public void delete(T t) throws Exception;
	
	/**
	 * Delete all entites.
	 * 
	 * @throws Exception if ORM fails to execute.
	 */
	public void deleteAll() throws Exception;
}

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
 * @param <T> Model type
 * @param <Id> Model identity datatype
 */
public interface DAO<T, Id extends Serializable> {
	
	public void persist(T t) throws Exception;
	public T getById(Id id) throws Exception;
	public List<T> getAll() throws Exception;
	public void update(T t) throws Exception;
	public void delete(T t) throws Exception;
	public void deleteAll() throws Exception;
}

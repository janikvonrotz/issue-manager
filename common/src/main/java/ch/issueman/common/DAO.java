package ch.issueman.common;

import java.io.Serializable;
import java.util.List;

/**
 * Generic Data Access Object interface
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> Model type
 * @param <Id> Model identity datatype
 */
public interface DAO<T, Id extends Serializable> {
	
	public void persist(T t);
	public T getById(Id id);
	public List<T> getAll();
	public void update(T t);
	public void delete(T t);
	public void deleteAll();
}

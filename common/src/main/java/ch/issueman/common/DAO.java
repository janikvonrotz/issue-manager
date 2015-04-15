package ch.issueman.common;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
	
	public void persist(T t) throws Exception;
	public T getById(Id id) throws Exception;
	public List<T> getAll() throws Exception;
	public void update(T t) throws Exception;
	public void delete(T t) throws Exception;
	public void deleteAll() throws Exception;
}

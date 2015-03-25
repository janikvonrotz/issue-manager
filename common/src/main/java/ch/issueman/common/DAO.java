package ch.issueman.common;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
	
	public void persist(T t);
	public T getById(Id id);
	public List<T> getAll();
	public void update(T t);
	public void delete(T t);
	public void deleteAll();
}

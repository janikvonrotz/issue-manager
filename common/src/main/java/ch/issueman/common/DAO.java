package ch.issueman.common;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
	
	public void persist(T t);
	public void persistList(List<T> l);
	public T getById(Id id);
	public List<T> getAll();
	public List<T> getByQuery(String JPQLquery);
	public void update(T t);
	public void updateList(List<T> l);
	public void delete(T t);
	public void deleteAll();
	public void deleteList(List<T> l);
}

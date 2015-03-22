package ch.issueman.common.webservice;

import java.io.Serializable;
import java.util.List;

import ch.issueman.common.DAO;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private final Class<T> clazz;
	
	protected Controller(Class<T> clazz) {
        this.clazz = clazz;
    }
		 
	public void persist(T t) {
	}

	public T getById(Id id) {
		return null;
	}

	public List<T> getAll() {
		return null;
	}

	public void update(T t) {
	}

	public void delete(T t) {
	}
	
	public void deleteAll() {
	}
}

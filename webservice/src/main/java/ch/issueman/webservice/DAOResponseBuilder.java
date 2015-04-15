package ch.issueman.webservice;

import javax.ws.rs.core.Response;

public interface DAOResponseBuilder<T, Id> {
	
	public Response persist(T t);
	public Response getById(Id id);
	public Response getAll();
	public Response update(T t);
	public Response delete(T t);
	public Response deleteAll();
}

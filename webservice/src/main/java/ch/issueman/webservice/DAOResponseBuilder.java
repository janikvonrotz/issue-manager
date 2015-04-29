package ch.issueman.webservice;

import java.rmi.Remote;
import javax.ws.rs.core.Response;

import ch.issueman.common.Login;

public interface DAOResponseBuilder<T, Id> extends Remote{
	
	public Response persist(T t);
	public Response getById(Id id);
	public Response getAll();
	public Response getAllByProperty(String propertyname, Object[] propertyvalues);
	public Response update(T t);
	public Response delete(T t);
	public Response deleteAll();
	public void setLogin(Login login);
	public Response signin();
	public Response deleteById(Id id);
}

package ch.issueman.webservice;

import java.rmi.Remote;
import javax.ws.rs.core.Response;

import ch.issueman.common.Login;

public interface DAOResponseBuilder<T, Id> extends Remote{
	
	public Response persist(T t) throws Exception;
	public Response getById(Id id) throws Exception;
	public Response getAll() throws Exception;
	public Response getAllByProperty(String propertyname, Object[] propertyvalues) throws Exception;
	public Response update(T t) throws Exception;
	public Response delete(T t) throws Exception;
	public Response deleteAll() throws Exception;
	public void setLogin(Login login) throws Exception;
	public Response signin(Login login) throws Exception;
	public Response deleteById(Id id) throws Exception;
}

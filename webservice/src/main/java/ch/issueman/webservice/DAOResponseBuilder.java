package ch.issueman.webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ws.rs.core.Response;

import ch.issueman.common.Login;

public interface DAOResponseBuilder<T, Id> extends Remote{
	
	public Response persist(T t) throws RemoteException;
	public Response getById(Id id) throws RemoteException;
	public Response getAll() throws RemoteException;
	public Response update(T t) throws RemoteException;
	public Response delete(T t) throws RemoteException;
	public Response deleteAll() throws RemoteException;
	public void setLogin(Login login) throws RemoteException;
	public Response signin(Login login) throws RemoteException;
	public Response deleteById(Id id) throws RemoteException;
}

package ch.issueman.webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.issueman.common.Login;

public interface DAORmi <T, Id> extends Remote{
	
	public void persist(T t) throws RemoteException, Exception, Exception;
	public T getById(Id id) throws RemoteException, Exception;
	public List<T> getAll() throws RemoteException, Exception;
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws RemoteException, Exception;
	public void update(T t) throws RemoteException, Exception;
	public void delete(T t) throws RemoteException, Exception;
	public void deleteAll() throws RemoteException, Exception;
	public void setLogin(Login login) throws RemoteException, Exception;
	public Login signin() throws RemoteException, Exception;
}

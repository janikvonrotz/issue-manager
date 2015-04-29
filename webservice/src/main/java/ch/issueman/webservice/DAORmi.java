package ch.issueman.webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.issueman.common.Login;

public interface DAORmi <T, Id> extends Remote{
	
	public void persist(T t) throws RemoteException;
	public T getById(Id id) throws RemoteException;
	public List<T> getAll() throws RemoteException;
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws RemoteException;
	public void update(T t) throws RemoteException;
	public void delete(T t) throws RemoteException;
	public void deleteAll() throws RemoteException;
	public void setLogin(Login login) throws RemoteException;
	public Login signin(Login login) throws RemoteException;
	public void deleteById(Id id) throws RemoteException;
}

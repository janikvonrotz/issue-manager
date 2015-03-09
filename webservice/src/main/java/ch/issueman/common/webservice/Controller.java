package ch.issueman.common.webservice;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private final Class<T> clazz;
	private Session currentSession;
	private Transaction currentTransaction;
	
	protected Controller(Class<T> clazz) {
        this.clazz = clazz;
    }
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}	
	public void closeCurrentSession() {
		currentSession.close();
	}
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}
	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}
	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
	 
	public void persist(T t) {
		openCurrentSessionwithTransaction();
		getCurrentSession().save(t);
		closeCurrentSessionwithTransaction();
	}

	public T getById(Id id) {
		openCurrentSession();
		@SuppressWarnings("unchecked")
		T t = (T) getCurrentSession().get(clazz, id);
		closeCurrentSession();
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		openCurrentSession();
		List<T> list = (List<T>) getCurrentSession().createQuery("from " + clazz.getName()).list();
		closeCurrentSession();
		return list;
	}

	public void update(T t) {
		openCurrentSessionwithTransaction();
		getCurrentSession().update(t);
		closeCurrentSessionwithTransaction();
	}

	public void delete(T t) {
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(t);
		closeCurrentSessionwithTransaction();
	}
	
	public void deleteAll() {
		openCurrentSessionwithTransaction();
		List<T> people = getAll();
		for (T t : people) {
			delete(t);
		}
		closeCurrentSessionwithTransaction();
	}
}

/**
 * 
 */
package ch.hslu.issueman;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Person implementation of the DAO interface
 */
public class PersonDAO implements DAO<Person, Integer> {

	private Session currentSession;
	private Transaction currentTransaction;

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
	 
	@Override
	public void persist(Person person) {
		getCurrentSession().save(person);
	}

	@Override
	public Person getById(Integer id) {
		return (Person) getCurrentSession().get(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		return (List<Person>) getCurrentSession().createQuery("from Person").list();
	}

	@Override
	public void update(Person person) {
		getCurrentSession().update(person);
	}

	@Override
	public void delete(Person person) {
		getCurrentSession().delete(person);
	}
	
	public void deleteAll() {
		List<Person> people = getAll();
		for (Person person : people) {
			delete(person);
		}
	}
}

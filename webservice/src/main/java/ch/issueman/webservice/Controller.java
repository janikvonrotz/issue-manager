package ch.issueman.webservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.issueman.common.DAO;

/**
 * Basic server controller class.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private EntityManager em = null;
	private final Class<T> clazz;

	public Controller(Class<T> clazz) {
		this.clazz = clazz;
		em = EclipseLink.getEntityManager();
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 */
	@Override
	public void persist(T t) {
		em = EclipseLink.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 */
	@Override
	public T getById(Id id) {
		em = EclipseLink.getEntityManager();
		return em.find(clazz, id);
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues){
		em = EclipseLink.getEntityManager();
		return (List<T>) ((TypedQuery<T>) em.createQuery("SELECT t FROM " + clazz.getSimpleName() + " t WHERE " + propertyname + " IN :propertyvalues", clazz))
				.setParameter("elements", propertyvalues)
				.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAll()
	 */
	@Override
	public List<T> getAll() {
		em = EclipseLink.getEntityManager();
		return (List<T>) ((TypedQuery<T>) em.createQuery("SELECT t FROM " + clazz.getSimpleName() + " t", clazz)).getResultList();
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		em = EclipseLink.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) {
		em = EclipseLink.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		for(T t : getAll()){
			delete(t);
		}
	}
}
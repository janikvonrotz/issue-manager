package ch.issueman.common.webservice;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Employer;

public class App {

	public static void main(String[] args) {
		
		System.out.println(Persistence.createEntityManagerFactory("issue-manager").isOpen());
		
		Person p = new Person("Kevin");
		User u = new User("Janik", "janik.vonrotz@stud.hslu.ch", "hello123", "Administrator");
		Employer e = new Employer("Aathavan", "Hochschule Luzern");		
		
		EntityManager em = EclipseLink.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(p);
		em.persist(u);
		em.persist(e);
		
		em.getTransaction().commit();
		em.close();
		
	}

}

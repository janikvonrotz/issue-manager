package ch.issueman.common.webservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class EclipseLink {

	private static EntityManagerFactory emf = null;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("issueman");
		} catch (Throwable e) {
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}
}
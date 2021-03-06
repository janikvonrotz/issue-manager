package ch.issueman.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Configure eclipse link with properties from the config file.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class EclipseLink {

	private static EntityManagerFactory emf = null;
	
	static {
		try {
			Config config = ConfigFactory.load();
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.driver", config.getString("javax.persistence.jdbc.driver"));
			properties.put("javax.persistence.jdbc.url", config.getString("javax.persistence.jdbc.url"));
			properties.put("javax.persistence.jdbc.user", config.getString("javax.persistence.jdbc.user"));
			properties.put("javax.persistence.jdbc.password", config.getString("javax.persistence.jdbc.password"));
			properties.put("eclipselink.ddl-generation.output-mode", config.getString("eclipselink.ddl-generation.output-mode"));
			properties.put("eclipselink.logging.level", config.getString("eclipselink.logging.level"));
			properties.put("eclipselink.ddl-generation", config.getString("eclipselink.ddl-generation.value"));
						
			emf = Persistence.createEntityManagerFactory("issue-manager", properties);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
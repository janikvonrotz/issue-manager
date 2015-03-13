package ch.issueman.common.webservice;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
 
public class Hibernate
{
   private static SessionFactory sessionFactory = buildSessionFactory();
 
   private static SessionFactory buildSessionFactory()
   {
      try
      {
         if (sessionFactory == null)
         {
        	Config config = ConfigFactory.load();        	 
			Configuration configuration = new Configuration()
					.addAnnotatedClass(ch.issueman.common.Person.class)
					.addAnnotatedClass(ch.issueman.common.User.class)
					.addAnnotatedClass(ch.issueman.common.Project.class)
					.addAnnotatedClass(ch.issueman.common.Employee.class)
					.setProperty("hibernate.dialect", config.getString("hibernate.dialect"))
					.setProperty("hibernate.connection.driver_class", config.getString("hibernate.connection.driver_class"))
					.setProperty("hibernate.connection.url", config.getString("hibernate.connection.url"))
					.setProperty("hibernate.connection.username", config.getString("hibernate.connection.username"))
					.setProperty("hibernate.connection.password", config.getString("hibernate.connection.password"))
					.setProperty("hibernate.current_session_context_class", config.getString("hibernate.current_session_context_class"));
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         }
         return sessionFactory;
      } catch (Throwable ex)
      {
         System.err.println("Initial SessionFactory creation failed." + ex);
         throw new ExceptionInInitializerError(ex);
      }
   }
 
   public static SessionFactory getSessionFactory()
   {
      return sessionFactory;
   }
 
   public static void shutdown()
   {
      getSessionFactory().close();
   }
}
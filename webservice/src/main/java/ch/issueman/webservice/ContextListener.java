package ch.issueman.webservice;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Runs the rmi endpoints and tinylog configurator.
 * 
 * @author Janik von Rotz
 * @version 2.0.0
 * @since 1.0.0
 */

@WebListener
public class ContextListener implements ServletContextListener {
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Service.runLogConfigurator();
		Service.startRMI();		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Service.destroyRMI();
	}
}

	


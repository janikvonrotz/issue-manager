package ch.issueman.webservice;

import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.labelers.TimestampLabeler;
import org.pmw.tinylog.policies.SizePolicy;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.RollingFileWriter;

import lombok.extern.slf4j.Slf4j;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projektleitung;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Rolle;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * Runs the rmi endpoints and tinylog configurator.
 * 
 * @author Janik von Rotz
 * @version 2.0.0
 * @since 1.0.0
 */
@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {

	private Registry registry;
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		Configurator.defaultConfig()
		   .writer(new RollingFileWriter(ConfigHelper.getConfig("tinylog.location", "log.txt"), 
				   ConfigHelper.getConfig("tinylog.files", 10), 
				   new TimestampLabeler(ConfigHelper.getConfig("tinylog.timestamp", "yyyy-MM-dd_HH-mm-ss")), 
				   new SizePolicy(ConfigHelper.getConfig("tinylog.size", 10240))))
		   .level(Level.valueOf(ConfigHelper.getConfig("tinylog.level", "INFO")))
		   .formatPattern(ConfigHelper.getConfig("tinylog.format", "{level}:\t{date}\t{class}.{method}()\t{message}"))
		   .activate();
		
		if(ConfigHelper.getConfig("tinylog.logtoconsole", "No").equals("Yes")){
			Configurator.currentConfig().addWriter(new ConsoleWriter()).activate();
		}
		
		Map <String, BusinessController<?, Integer>> rbm = new HashMap<String, BusinessController<?, Integer>>();
		
		try{
			rbm.put("adresse", new BusinessController<Adresse, Integer>(Adresse.class));
			rbm.put("arbeitstyp", new BusinessController<Arbeitstyp, Integer>(Arbeitstyp.class));
			rbm.put("bauherr", new BusinessController<Bauherr, Integer>(Bauherr.class));
			rbm.put("bauleiter", new BusinessController<Bauleiter, Integer>(Bauleiter.class));
			rbm.put("kommentar", new BusinessController<Kommentar, Integer>(Kommentar.class));
			rbm.put("kontakt", new BusinessController<Kontakt, Integer>(Kontakt.class));
			rbm.put("login", new BusinessController<Login, Integer>(Login.class));
			rbm.put("mangel", new BusinessController<Mangel, Integer>(Mangel.class));
			rbm.put("mangelstatus", new BusinessController<Mangelstatus, Integer>(Mangelstatus.class));
			rbm.put("ort", new BusinessController<Ort, Integer>(Ort.class));
			rbm.put("person", new BusinessController<Person, Integer>(Person.class));
			rbm.put("projekt", new BusinessController<Projekt, Integer>(Projekt.class));
			rbm.put("projektleitung", new BusinessController<Projektleitung, Integer>(Projektleitung.class));
			rbm.put("projekttyp", new BusinessController<Projekttyp, Integer>(Projekttyp.class));
			rbm.put("rolle", new BusinessController<Rolle, Integer>(Rolle.class));
			rbm.put("sachbearbeiter", new BusinessController<Sachbearbeiter, Integer>(Sachbearbeiter.class));
			rbm.put("subunternehmen", new BusinessController<Subunternehmen, Integer>(Subunternehmen.class));
			rbm.put("unternehmen", new BusinessController<Unternehmen, Integer>(Unternehmen.class));
			
			registry = LocateRegistry.createRegistry(ConfigHelper.getConfig("rmi.port", 1099));
			
			if (registry!= null){
				for(Entry<String, BusinessController<?, Integer>> me : rbm.entrySet()){
					Naming.rebind(me.getKey(), me.getValue());
					log.info(me.getKey() + " bound.");
				}
			}
			
		} catch (Exception e){
			log.error("RMI initialize failed.", e);
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			UnicastRemoteObject.unexportObject(registry, true);
		} catch (NoSuchObjectException e) {
			log.error("RMI destroy failed.", e);
		}
	}
}

	


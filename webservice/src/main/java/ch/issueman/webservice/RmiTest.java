package ch.issueman.webservice;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import ch.issueman.common.Adresse;
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
public class RmiTest {

	private static Map <String, DAOResponseBuilder<?, Integer>> rbm = new HashMap<String, DAOResponseBuilder<?, Integer>>();
	
	@SuppressWarnings({ })
	public static void main(String[] args) {
		(new RmiTest()).run();
	}
		
	@SuppressWarnings("unchecked")
	public void run(){
				
		String url = "rmi://" + ConfigHelper.getConfig("rmi.host", "localhost") + ":" + ConfigHelper.getConfig("rmi.port", 1099) + "/";
		
		try {
			rbm.put("adresse", (DAOResponseBuilder<Adresse, Integer>) Naming.lookup(url + "adresse"));
			rbm.put("arbeitstyp",  (DAOResponseBuilder<Adresse, Integer>) Naming.lookup(url + "arbeitstyp"));
			rbm.put("bauherr", new ResponseBuilder<Bauherr, Integer>(Bauherr.class));
			rbm.put("bauleiter", new ResponseBuilder<Bauleiter, Integer>(Bauleiter.class));
			rbm.put("kommentar", new ResponseBuilder<Kommentar, Integer>(Kommentar.class));
			rbm.put("kontakt", new ResponseBuilder<Kontakt, Integer>(Kontakt.class));
			rbm.put("login", new ResponseBuilder<Login, Integer>(Login.class));
			rbm.put("mangel", new ResponseBuilder<Mangel, Integer>(Mangel.class));
			rbm.put("mangelstatus", new ResponseBuilder<Mangelstatus, Integer>(Mangelstatus.class));
			rbm.put("ort", new ResponseBuilder<Ort, Integer>(Ort.class));
			rbm.put("person", new ResponseBuilder<Person, Integer>(Person.class));
			rbm.put("projekt", new ResponseBuilder<Projekt, Integer>(Projekt.class));
			rbm.put("projektleitung", new ResponseBuilder<Projektleitung, Integer>(Projektleitung.class));
			rbm.put("projekttyp", new ResponseBuilder<Projekttyp, Integer>(Projekttyp.class));
			rbm.put("rolle", new ResponseBuilder<Rolle, Integer>(Rolle.class));
			rbm.put("sachbearbeiter", new ResponseBuilder<Sachbearbeiter, Integer>(Sachbearbeiter.class));
			rbm.put("subunternehmen", new ResponseBuilder<Subunternehmen, Integer>(Subunternehmen.class));
			rbm.put("unternehmen", new ResponseBuilder<Unternehmen, Integer>(Unternehmen.class));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}


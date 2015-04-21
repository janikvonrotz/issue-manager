package ch.issueman.webservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Rolle;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * class Seed
 * 
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

public class Seed {
	
	private final static Config config = ConfigFactory.load();
	
	public static void main(String[] args) {
		Seed s = new Seed();
		s.seed();
	}
	
	private void seed(){
		
		// TODO für alle entities config laden, bzw. im Config file erstellen.
		
		//Liste Ort füllen
		ClassLoader classLoader = getClass().getClassLoader();
		File csv = new File(classLoader.getResource(getConfig("seed.Ort", "Orschaften.csv")).getFile());
		List<Ort> listOrt = new ArrayList<Ort>();
		// CSV auslesen und Liste erstellen
		try {
			FileReader fr = new FileReader(csv);
			CSVParser parser = new CSVParser(fr, CSVFormat.EXCEL);
			 for (CSVRecord r : parser) {
				 listOrt.add(new Ort(Integer.parseInt(r.get("PLZ")), r.get("Ortsbezeichnung")));
			 }
			 parser.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Arbeitstyp> listArbeitstyp = new ArrayList<Arbeitstyp>();
		List<Rolle> listRolle = new ArrayList<Rolle>();
		List<Mangelstatus> listMangelstatus = new ArrayList<Mangelstatus>();
		List<Projekttyp> listProjekttyp = new ArrayList<Projekttyp>();
		List<Adresse> listAdresse = new ArrayList<Adresse>();
		List<Bauherr> listBauherr = new ArrayList<Bauherr>();
		List<Bauleiter> listBauleiter = new ArrayList<Bauleiter>();
		List<Kommentar> listKommentar = new ArrayList<Kommentar>();
		List<Kontakt> listKontakt = new ArrayList<Kontakt>();
		List<Login> listLogin = new ArrayList<Login>();
		List<Mangel> listMangel = new ArrayList<Mangel>();
		List<Projekt> listProjekt = new ArrayList<Projekt>();
		List<Projektleitung> listProjektleitung = new ArrayList<Projektleitung>();
		List<Sachbearbeiter> listSachbearbeiter = new ArrayList<Sachbearbeiter>();
		List<Subunternehmen> listSubunternehmen = new ArrayList<Subunternehmen>();
		List<Unternehmen> listUnternehmen = new ArrayList<Unternehmen>();
		
		
		
		// TODO Alle Controller laden
		
		Controller<Adresse, Integer> adressecontroller = new Controller<Adresse, Integer>(Adresse.class);
		Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
		Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);
		Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
		Controller<Kommentar, Integer> kommentarcontroller = new Controller<Kommentar, Integer>(Kommentar.class);
		Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
		Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
		Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
		Controller<Mangelstatus, Integer> mangelstatuscontroller = new Controller<Mangelstatus, Integer>(Mangelstatus.class);
		Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
		Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
		Controller<Projektleitung, Integer> projektleitungcontroller = new Controller<Projektleitung, Integer>(Projektleitung.class);
		Controller<Projekttyp, Integer> projekttypcontroller = new Controller<Projekttyp, Integer>(Projekttyp.class);
		Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
		Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
		Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
		Controller<Unternehmen, Integer> unternehmencontroller = new Controller<Unternehmen, Integer>(Unternehmen.class);
		
		
		// TODO alle daten löschen
		adressecontroller.deleteAll();
		arbeitstypcontroller.deleteAll();
		bauherrcontroller.deleteAll();
		bauleitercontroller.deleteAll();
		kommentarcontroller.deleteAll();
		kontaktcontroller.deleteAll();
		mangelcontroller.deleteAll();
		mangelstatuscontroller.deleteAll();
		ortcontroller.deleteAll();
		projektcontroller.deleteAll();
		projektleitungcontroller.deleteAll();
		projekttypcontroller.deleteAll();
		rollecontroller.deleteAll();
		sachbearbeitercontroller.deleteAll();
		subunternehmencontroller.deleteAll();
		unternehmencontroller.deleteAll();
		
		// TODO Daten in Listen laden
		// Liste Arbeitstyp füllen
		listArbeitstyp.add(new Arbeitstyp("Neubau"));
		listArbeitstyp.add(new Arbeitstyp("Umbau"));
		listArbeitstyp.add(new Arbeitstyp("Renovation"));
		listArbeitstyp.add(new Arbeitstyp("Teil-Renovation"));
		
		//Liste Rollen, Sachbearbeiter, Login füllen
		listRolle.add(new Rolle("Sachbearbeiter"));
		listSachbearbeiter.add(new Sachbearbeiter("sb","sb","sb@im.ch"));
		listLogin.add(new Login(listSachbearbeiter.get(0), "1", listRolle.get(0)));
		
		listRolle.add(new Rolle("Sachbearbeiter"));
		listSachbearbeiter.add(new Sachbearbeiter("Peter","Lustig","peter.lustig@im.ch"));
		listLogin.add(new Login(listSachbearbeiter.get(1), "lkjsd", listRolle.get(0)));
		
		listRolle.add(new Rolle("Bauleiter"));
		listBauleiter.add(new Bauleiter("bl","bl","bl@im.ch"));
		listLogin.add(new Login(listBauleiter.get(0), "1", listRolle.get(1)));
		
		listRolle.add(new Rolle("Bauleiter"));
		listBauleiter.add(new Bauleiter("Hans","Bruder","hans.bruder@im.ch"));
		listLogin.add(new Login(listBauleiter.get(1), "asdf", listRolle.get(1)));
		
		listRolle.add(new Rolle("Kontaktperson"));
		listKontakt.add(new Kontakt("kt","kt","kt@im.ch"));
		listLogin.add(new Login(listKontakt.get(0), "1", listRolle.get(2)));
		
		listRolle.add(new Rolle("Kontaktperson"));
		listKontakt.add(new Kontakt("Sepp","Blatter","sepp.blatter@im.ch"));
		listLogin.add(new Login(listKontakt.get(1), "asdf", listRolle.get(2)));
		
		listRolle.add(new Rolle("Kontaktadmin"));
		listKontakt.add(new Kontakt("ka","ka","ka@im.ch"));
		listLogin.add(new Login(listKontakt.get(2), "asdf", listRolle.get(3)));
		
		listRolle.add(new Rolle("Kontaktadmin"));
		listKontakt.add(new Kontakt("Sepp","Blatter","sepp.blatter@im.ch"));
		listLogin.add(new Login(listKontakt.get(3), "asdf", listRolle.get(3)));		
		
		//Liste Projekttyp füllen
	    listProjekttyp.add(new Projekttyp("Einfamilienhaus"));
		listProjekttyp.add(new Projekttyp("Mehrfamilienhaus"));
		listProjekttyp.add(new Projekttyp("Wohnung"));
		listProjekttyp.add(new Projekttyp("Garage"));
		listProjekttyp.add(new Projekttyp("Gartenhaus"));
		
		//Liste Adresse füllen String strasse, Ort ort
		listAdresse.add(new Adresse("Zugerstrasse", listOrt.get(0)));
		listAdresse.add(new Adresse("Luzernerstrasse", listOrt.get(1)));
		listAdresse.add(new Adresse("Neubühlstrasse", listOrt.get(2)));
		listAdresse.add(new Adresse("Maurerstrasse", listOrt.get(20)));
		listAdresse.add(new Adresse("Untergrundstrasse", listOrt.get(35)));
		listAdresse.add(new Adresse("Baslerstrasse", listOrt.get(50)));
		
		listAdresse.get(0);
		
		//Liste Bauherr füllen
		listBauherr.add(new Bauherr("Müller", "Hans", "hans.müller@bh.ch", listUnternehmen.get(0)));
		listBauherr.add(new Bauherr("Migros", "Alu", "alu.migros@windowslive.ch", listUnternehmen.get(1)));
		listBauherr.add(new Bauherr("Sommer", "Mirco", "mirco.sommer@gmail.ch", listUnternehmen.get(2)));
		listBauherr.add(new Bauherr("Zwimpfer", "Margrit", "margrit.zwimpfer@hotmail.ch", listUnternehmen.get(3)));
		listBauherr.add(new Bauherr("Fäh", "Linda", "linda.fäh@miss.ch", listUnternehmen.get(4)));
		
		//Liste Bauleiter füllen
		listBauleiter.add(new Bauleiter("Cisco", "Franz", "franc.cisco@cisco.ch"));
		listBauleiter.add(new Bauleiter("Capone", "Don", "don.capone@mafia.ch"));
		listBauleiter.add(new Bauleiter("Wachter", "Hans-Peter", "hp@willibau.ch"));
		listBauleiter.add(new Bauleiter("Malgin", "Igor", "igor.malgin@power.ch"));
		listBauleiter.add(new Bauleiter("Loser", "Bruno", "bruno.loser@grebo.ch"));
		listBauleiter.add(new Bauleiter("Dell", "Walter", "walter.dell@doit.ch"));
		
		//Liste Kommentare füllen String, Login
		
		//Liste Kontakt
		listKontakt.add(new Kontakt("Putzi", "Sonja", "sonja.putzi@habau.ch", listSubunternehmen.get(0), listProjekt.get(0)));
		listKontakt.add(new Kontakt("Henniez", "Rudolf", "rudolf.henniez@gabau".ch, listSubunternehmen.get(1), listProjekt.get(1)));
		listKontakt.add(new Kontakt("Ludolf", "Manfred", "manfred.ludolf@mbau.ch", listSubunternehmen.get(2), listProjekt.get(2)));
		listKontakt.add(new Kontakt("Pereira", "Patrik", "patrik.pereira@gebau.ch", listSubunternehmen.get(3), listProjekt.get(3)));
		listKontakt.add(new Kontakt("Arche", "Noa", "noa.arche@bobau.ch", listSubunternehmen.get(4), listProjekt.get(4)));
		listKontakt.add(new Kontakt("Beutel", "Kurt", "kurt.beutel@dabau.ch", listSubunternehmen.get(5), listProjekt.get(5)));
		/*Liste Mangel (int referenz, Person erfasser, List<Kommentar> kommentare,
		Mangelstatus mangelstatus, Date erledigenbis, Projekt projekt,
		Date erstelltam)*/
		
		//Liste Mangelstatus füllen
		listMangelstatus.add(new Mangelstatus("offen", listRolle));
		listMangelstatus.add(new Mangelstatus("erledigt", listRolle));
		listMangelstatus.add(new Mangelstatus("zur Kontrolle", listRolle));		
				
		/*Liste Projekt Projekt(String title, String adresse, Arbeitstyp arbeitstyp, Projekttyp projekttyp,
			Bauherr bauherr, List<Projektleitung> projektleitungen, Date beginn, Date ende)*/
		
		
		//Liste Projektleitung Bauleiter bauleiter, Date beginn, Date ende
		listProjektleitung.add(new Projektleitung(listBauleiter.get(0), new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(1), new GregorianCalendar(2,2,2015), new GregorianCalendar(1,3,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(2), new GregorianCalendar(5,3,2015), new GregorianCalendar(1,4,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(3), new GregorianCalendar(7,4,2015), new GregorianCalendar(10,5,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(4), new GregorianCalendar(8,2,2015), new GregorianCalendar(7,6,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(5), new GregorianCalendar(12,3,2015), new GregorianCalendar(1,7,2015)));
	
		//Subunternehmen String firmenname, Adresse adresse
		listSubunternehmen.add(new Subunternehmen("Hausbau AG", listAdresse.get(0)));
		listSubunternehmen.add(new Subunternehmen("Gartenbau AG", listAdresse.get(1)));
		listSubunternehmen.add(new Subunternehmen("Mauerbau AG", listAdresse.get(2)));
		listSubunternehmen.add(new Subunternehmen("Gerüstbau AG", listAdresse.get(3)));
		listSubunternehmen.add(new Subunternehmen("Bodenbeläge GMBH", listAdresse.get(4)));
		listSubunternehmen.add(new Subunternehmen("Dachdecker GMBH", listAdresse.get(5)));
		
		//Liste Unternehmen String firmenname, Adresse adresse
		listUnternehmen.add(new Unternehmen("Sanitär Trösch", listAdresse.get(0)));
		listUnternehmen.add(new Unternehmen("Tiefenbohrungen Meier", listAdresse.get(1)));	
		listUnternehmen.add(new Unternehmen("Kaminbau Sutter", listAdresse.get(2)));	
		listUnternehmen.add(new Unternehmen("Türenfabrik AG", listAdresse.get(3)));	
		listUnternehmen.add(new Unternehmen("Keller's Keller", listAdresse.get(4)));	
		listUnternehmen.add(new Unternehmen("Garagenbau GMBH", listAdresse.get(5)));	
		
		// TODO daten seeden
		
		for(Adresse adresse : listAdresse){
			adressecontroller.persist(adresse);
		}
		
		for(Arbeitstyp arbeitstyp : listArbeitstyp){
			arbeitstypcontroller.persist(arbeitstyp);
		}
		
		for(Rolle rolle : listRolle){
			rollecontroller.persist(rolle);
		}
		
		for(Login login : listLogin){
			logincontroller.persist(login);
		}
		for(Mangelstatus mangelstatus : listMangelstatus){
			mangelstatuscontroller.persist(mangelstatus);
		}
		
		for(Projekttyp projekttyp : listProjekttyp){
			projekttypcontroller.persist(projekttyp);
		}
		
		for(Sachbearbeiter sachbearbeiter : listSachbearbeiter){
			sachbearbeitercontroller.persist(sachbearbeiter);
		}
		
		for(Bauherr bauherr : listBauherr){
			bauherrcontroller.persist(bauherr);
		}
		
		for(Bauleiter bauleiter : listBauleiter){
			bauleitercontroller.persist(bauleiter);
		}
		for(Kontakt kontakt : listKontakt){
			kontaktcontroller.persist(kontakt);
		}
		
		for(Mangelstatus mangelstatus : listMangelstatus){
			mangelstatuscontroller.persist(mangelstatus);
		}
		
		for(Mangel mangel : listMangel){
			mangelcontroller.persist(mangel);
		}
		for(Subunternehmen subunternehmen : listSubunternehmen){
			subunternehmencontroller.persist(subunternehmen);
		}
		
		for(Unternehmen unternehmen : listUnternehmen){
			unternehmencontroller.persist(unternehmen);
		}
		
		// TODO Einheitliche daten seeden: Für alle Logins, Beispiel Sachbearbeiter: login{person{sb, sb, sb@im.ch}, "sb", id->Rolle->"Sachbearbeiter"}
		
		// TODO geseedete Daten summary
		
		System.out.println("Seeded: " + anzahlSachbearbeiter + " Sachbearbeiter");
		
		// Beispiele - Dynmaisch Datenabrufen
		
		List<Sachbearbeiter> sachbearbeiter = sachbearbeitercontroller.getAllByProperty("vorname", new String[]{"peter"});
		sachbearbeiter.stream().filter(p -> p.getVorname().equals("peter"));
		List<Rolle> rollen = rollecontroller.getAll();
		Rolle RolleSachbearbeiter = rollen.stream().filter(r -> r.getBezeichnung().equals("Sachbearbeiter")).findFirst().get();		
		
		/* alter code

		Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
		Controller<Employer, Integer> employercontroller = new Controller<Employer, Integer>(Employer.class);
		Controller<Project, Integer> projectcontroller = new Controller<Project, Integer>(Project.class);
		Controller<Comment, Integer> commentcontroller = new Controller<Comment, Integer>(Comment.class);

		projectcontroller.deleteAll();
		commentcontroller.deleteAll();
		usercontroller.deleteAll();
		employercontroller.deleteAll();
		personcontroller.deleteAll();
				
		int i = 0;
		int j = 0;
		
		for (i = 0; i <= 20; i++) {

			Person person = new Person(faker.name().firstName());
			User user = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.letterify("??????"), "Administrator");
			Employer employer = new Employer(faker.name().firstName(), faker.name().lastName());

			personcontroller.persist(person);
			usercontroller.persist(user);
			employercontroller.persist(employer);

			if (i % 4 == 0) {
				
				Project project = new Project("Project: " + faker.name().lastName(), employer);
				
				List<Comment> comments = new ArrayList<Comment>();
				
				for (j = 0; j <= 10; j++) {
					
					Comment comment = new Comment(faker.lorem().paragraph(),user);
					comments.add(comment);
					project.setComments(comments);
				}

				projectcontroller.persist(project);
			}
		}
		
		System.out.println("Seeded: " + i*3 + " people");
		System.out.println("Seeded: " + (i/4+1) + " projects");
		System.out.println("Seeded: " + j*(i/4+1) + " comments");
		
		*/
	}
	
	/**
	 * Checks whether the String config entry exists or not and returns the defined value.
	 * 
	 * @param path the path in the config file.
	 * @param defaultcount the default value to return when the entry doesn't exist.
	 * @return the config value.
	 */
	private static String getConfig(String path, String defaultvalue) {
		if(config.hasPath(path)){
			return config.getString(path);
		}else{
			return defaultvalue;
		}
		
	}
	
	/**
	 * Checks whether the Integer config entry exists or not and returns the defined value.
	 * 
	 * @param path the path in the config file.
	 * @param defaultcount the default value to return when the entry doesn't exist.
	 * @return the config value.
	 */
	private static int getConfig(String path, int defaultvalue) {
		if(config.hasPath(path)){
			return config.getInt(path);
		}else{
			return defaultvalue;
		}
		
	}

	/**
	 * Checks whether the String List config entry exists or not and returns the defined values.
	 * 
	 * @param path the path in the config file.
	 * @param defaultvalues the default values for this config entry.
	 * @return the config String List.
	 */
	private static List<String> getConfig(String path, String[] defaultvalues) {
		if(config.hasPath(path)){
			return config.getStringList(path);
		}else{
			return new ArrayList<String>(Arrays.asList(defaultvalues));
		}
	}
}

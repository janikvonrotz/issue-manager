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
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

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
import ch.issueman.common.Model;
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
 * @author Erwin Willi, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */

@Slf4j
public class Seed {
	
	private final static Config config = ConfigFactory.load();
	
	public static void main(String[] args) {
		Seed s = new Seed();
		s.seed();
	}
	
	/**
	 * Runs the seed task
	 */
	private void seed(){
		
		/* Sort for persist:
		 * 
		 * Arbeitstyp
		 * Ort
		 * Rolle
		 * Projekttyp -> bis hier und testen
		 * 
		 * 
		 * Sort for delete all: Opposite direction
		 */
		
		List<Arbeitstyp> listArbeitstyp = new ArrayList<Arbeitstyp>();
		List<Ort> listOrt = new ArrayList<Ort>();
		List<Rolle> listRolle = new ArrayList<Rolle>();
		List<Projekttyp> listProjekttyp = new ArrayList<Projekttyp>();
				
		Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
		Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
		Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
		Controller<Projekttyp, Integer> projekttypcontroller = new Controller<Projekttyp, Integer>(Projekttyp.class);
				
		projekttypcontroller.deleteAll();
		rollecontroller.deleteAll();
		arbeitstypcontroller.deleteAll();
		ortcontroller.deleteAll();
		
		/**
		 * seed Ort from csv
		 */
		ClassLoader classLoader = getClass().getClassLoader();
		File csv = new File(classLoader.getResource(getConfig("seed.Ort", "Orschaften.csv")).getFile());
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
		for(Ort ort : listOrt){
			ortcontroller.persist(ort);
		}
		logSeed(listOrt);
		
		
		/**
		 * seed Arbeitstyp
		 */
		listArbeitstyp.add(new Arbeitstyp("Neubau"));
		listArbeitstyp.add(new Arbeitstyp("Umbau"));
		listArbeitstyp.add(new Arbeitstyp("Renovation"));
		listArbeitstyp.add(new Arbeitstyp("Teil-Renovation"));
		for(Arbeitstyp arbeitstyp : listArbeitstyp){
			arbeitstypcontroller.persist(arbeitstyp);
		}
		logSeed(listArbeitstyp);
		
		/**
		 * seed Rolle
		 */
		listRolle.add(new Rolle("Sachbearbeiter"));
		listRolle.add(new Rolle("Bauleiter"));
		listRolle.add(new Rolle("Kontaktperson"));
		listRolle.add(new Rolle("Kontaktadmin"));
		for(Rolle rolle : listRolle){
			rollecontroller.persist(rolle);
		}
		logSeed(listRolle);
		
		/**
		 * seed Projekttyp
		 */
		listProjekttyp.add(new Projekttyp("Einfamilienhaus"));
		listProjekttyp.add(new Projekttyp("Mehrfamilienhaus"));
		listProjekttyp.add(new Projekttyp("Wohnung"));
		listProjekttyp.add(new Projekttyp("Garage"));
		listProjekttyp.add(new Projekttyp("Gartenhaus"));
		for(Projekttyp projekttyp : listProjekttyp){
			projekttypcontroller.persist(projekttyp);
		}
		logSeed(listProjekttyp);
		
		/*
		List<Mangelstatus> listMangelstatus = new ArrayList<Mangelstatus>();
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
		
		Controller<Adresse, Integer> adressecontroller = new Controller<Adresse, Integer>(Adresse.class);
		Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);
		Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
		Controller<Kommentar, Integer> kommentarcontroller = new Controller<Kommentar, Integer>(Kommentar.class);
		Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
		Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
		Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
		Controller<Mangelstatus, Integer> mangelstatuscontroller = new Controller<Mangelstatus, Integer>(Mangelstatus.class);
		Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
		Controller<Projektleitung, Integer> projektleitungcontroller = new Controller<Projektleitung, Integer>(Projektleitung.class);
		Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
		Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
		Controller<Unternehmen, Integer> unternehmencontroller = new Controller<Unternehmen, Integer>(Unternehmen.class);
		
		adressecontroller.deleteAll();
		bauherrcontroller.deleteAll();
		bauleitercontroller.deleteAll();
		kommentarcontroller.deleteAll();
		kontaktcontroller.deleteAll();
		mangelcontroller.deleteAll();
		mangelstatuscontroller.deleteAll();
		projektcontroller.deleteAll();
		projektleitungcontroller.deleteAll();
		sachbearbeitercontroller.deleteAll();
		subunternehmencontroller.deleteAll();
		unternehmencontroller.deleteAll();

		listSachbearbeiter.add(new Sachbearbeiter("sb","sb","sb@im.ch"));
		listLogin.add(new Login(listSachbearbeiter.get(0), "1", listRolle.get(0)));
		
		listSachbearbeiter.add(new Sachbearbeiter("Peter","Lustig","peter.lustig@im.ch"));
		listLogin.add(new Login(listSachbearbeiter.get(1), "lkjsd", listRolle.get(0)));
		
		listBauleiter.add(new Bauleiter("bl","bl","bl@im.ch"));
		listLogin.add(new Login(listBauleiter.get(0), "1", listRolle.get(1)));
		
		listBauleiter.add(new Bauleiter("Hans","Bruder","hans.bruder@im.ch"));
		listLogin.add(new Login(listBauleiter.get(1), "asdf", listRolle.get(1)));
		
		List<Projekt> listTemp = listProjekt.stream().filter(p -> p.getId() == 0 || p.getId() == 3).collect(Collectors.toList());
		listKontakt.add(new Kontakt("kt","kt","kt@im.ch", listSubunternehmen.get(0), listTemp));
		listLogin.add(new Login(listKontakt.get(0), "1", listRolle.get(2)));
		
		listTemp = listProjekt.stream().filter(p -> p.getId() == 1 || p.getId() == 3).collect(Collectors.toList());
		listKontakt.add(new Kontakt("Sepp","Blatter","sepp.blatter@im.ch", listSubunternehmen.get(1), listTemp));
		listLogin.add(new Login(listKontakt.get(1), "asdf", listRolle.get(2)));
		
		listTemp = listProjekt.stream().filter(p -> p.getId() == 2 || p.getId() == 4).collect(Collectors.toList());
		listKontakt.add(new Kontakt("ka","ka","ka@im.ch", listSubunternehmen.get(3), listTemp));
		listLogin.add(new Login(listKontakt.get(2), "asdf", listRolle.get(3)));
		
		listTemp = listProjekt.stream().filter(p -> p.getId() == 1 || p.getId() == 2).collect(Collectors.toList());
		listKontakt.add(new Kontakt("Sepp","Blatter","sepp.blatter@im.ch", listSubunternehmen.get(4), listTemp));
		listLogin.add(new Login(listKontakt.get(3), "asdf", listRolle.get(3)));		

		listAdresse.add(new Adresse("Zugerstrasse", listOrt.get(0)));
		listAdresse.add(new Adresse("Luzernerstrasse", listOrt.get(1)));
		listAdresse.add(new Adresse("Neub�hlstrasse", listOrt.get(2)));
		listAdresse.add(new Adresse("Maurerstrasse", listOrt.get(20)));
		listAdresse.add(new Adresse("Untergrundstrasse", listOrt.get(35)));
		listAdresse.add(new Adresse("Baslerstrasse", listOrt.get(50)));
		
		listBauherr.add(new Bauherr("M�ller", "Hans", "hans.m�ller@bh.ch", listUnternehmen.get(0)));
		listBauherr.add(new Bauherr("Migros", "Alu", "alu.migros@windowslive.ch", listUnternehmen.get(1)));
		listBauherr.add(new Bauherr("Sommer", "Mirco", "mirco.sommer@gmail.ch", listUnternehmen.get(2)));
		listBauherr.add(new Bauherr("Zwimpfer", "Margrit", "margrit.zwimpfer@hotmail.ch", listUnternehmen.get(3)));
		listBauherr.add(new Bauherr("F�h", "Linda", "linda.f�h@miss.ch", listUnternehmen.get(4)));

		listBauleiter.add(new Bauleiter("Cisco", "Franz", "franc.cisco@cisco.ch"));
		listBauleiter.add(new Bauleiter("Capone", "Don", "don.capone@mafia.ch"));
		listBauleiter.add(new Bauleiter("Wachter", "Hans-Peter", "hp@willibau.ch"));
		listBauleiter.add(new Bauleiter("Malgin", "Igor", "igor.malgin@power.ch"));
		listBauleiter.add(new Bauleiter("Loser", "Bruno", "bruno.loser@grebo.ch"));
		listBauleiter.add(new Bauleiter("Dell", "Walter", "walter.dell@doit.ch"));

		String kommentar = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
		listKommentar.add(new Kommentar(kommentar, listLogin.get(0)));
		listKommentar.add(new Kommentar(kommentar, listLogin.get(1)));
		listKommentar.add(new Kommentar(kommentar, listLogin.get(2)));
		listKommentar.add(new Kommentar(kommentar, listLogin.get(1)));
		listKommentar.add(new Kommentar(kommentar, listLogin.get(3)));
		listKommentar.add(new Kommentar(kommentar, listLogin.get(2)));
		listKommentar.add(new Kommentar(kommentar, listLogin.get(0)));
		
		String mangel = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
		List<Kommentar> listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(1, listBauleiter.get(0), listTemp4, listMangelstatus.get(0), new GregorianCalendar(1,7,2015), listProjekt.get(0)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(2, listBauleiter.get(0), listTemp4, listMangelstatus.get(0), new GregorianCalendar(1,7,2015), listProjekt.get(0)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(1, listBauleiter.get(1), listTemp4, listMangelstatus.get(1), new GregorianCalendar(1,7,2015), listProjekt.get(1)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(2, listBauleiter.get(1), listTemp4, listMangelstatus.get(1), new GregorianCalendar(1,7,2015), listProjekt.get(1)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(1, listBauleiter.get(2), listTemp4, listMangelstatus.get(2), new GregorianCalendar(1,7,2015), listProjekt.get(2)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(2, listBauleiter.get(2), listTemp4, listMangelstatus.get(2), new GregorianCalendar(1,7,2015), listProjekt.get(2)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(1, listBauleiter.get(3), listTemp4, listMangelstatus.get(3), new GregorianCalendar(1,7,2015), listProjekt.get(3)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(2, listBauleiter.get(3), listTemp4, listMangelstatus.get(3), new GregorianCalendar(1,7,2015), listProjekt.get(3)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(1, listBauleiter.get(4), listTemp4, listMangelstatus.get(4), new GregorianCalendar(1,7,2015), listProjekt.get(4)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(2, listBauleiter.get(4), listTemp4, listMangelstatus.get(4), new GregorianCalendar(1,7,2015), listProjekt.get(4)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(3, listBauleiter.get(2), listTemp4, listMangelstatus.get(5), new GregorianCalendar(1,7,2015), listProjekt.get(2)));
		listTemp4 = filterListIds(listKommentar, new int[]{0,3});
		listMangel.add(new Mangel(4, listBauleiter.get(2), listTemp4, listMangelstatus.get(5), new GregorianCalendar(1,7,2015), listProjekt.get(2)));
						
		List<Projektleitung> listTemp3 = filterListIds(listRolle, new int[]{0,3});
		listProjekt.add(new Projekt("Renovation Turm", listAdresse.get(0), listArbeitstyp.get(2), listProjekttyp.get(1), listBauherr.get(0), listTemp3, new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		listTemp3 = filterListIds(listRolle, new int[]{1,2});
		listProjekt.add(new Projekt("Teil-Renovation Haus", listAdresse.get(1), listArbeitstyp.get(3), listProjekttyp.get(1), listBauherr.get(1), listTemp3, new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		listTemp3 = filterListIds(listRolle, new int[]{2,3});
		listProjekt.add(new Projekt("Umbau Mehrfamilienhaus", listAdresse.get(0), listArbeitstyp.get(1), listProjekttyp.get(1), listBauherr.get(2), listTemp3, new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		listTemp3 = filterListIds(listRolle, new int[]{3,4});
		listProjekt.add(new Projekt("Neubau Garage", listAdresse.get(2), listArbeitstyp.get(0), listProjekttyp.get(3), listBauherr.get(3), listTemp3, new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		listTemp3 = filterListIds(listRolle, new int[]{0,2});
		listProjekt.add(new Projekt("Renovation Gartenhaus", listAdresse.get(3), listArbeitstyp.get(2), listProjekttyp.get(4), listBauherr.get(4), listTemp3, new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		
		listProjektleitung.add(new Projektleitung(listBauleiter.get(0), new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(1), new GregorianCalendar(2,2,2015), new GregorianCalendar(1,3,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(2), new GregorianCalendar(5,3,2015), new GregorianCalendar(1,4,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(3), new GregorianCalendar(7,4,2015), new GregorianCalendar(10,5,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(4), new GregorianCalendar(8,2,2015), new GregorianCalendar(7,6,2015)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(5), new GregorianCalendar(12,3,2015), new GregorianCalendar(1,7,2015)));
	
		listSubunternehmen.add(new Subunternehmen("Hausbau AG", listAdresse.get(0)));
		listSubunternehmen.add(new Subunternehmen("Gartenbau AG", listAdresse.get(1)));
		listSubunternehmen.add(new Subunternehmen("Mauerbau AG", listAdresse.get(2)));
		listSubunternehmen.add(new Subunternehmen("Ger�stbau AG", listAdresse.get(3)));
		listSubunternehmen.add(new Subunternehmen("Bodenbel�ge GMBH", listAdresse.get(4)));
		listSubunternehmen.add(new Subunternehmen("Dachdecker GMBH", listAdresse.get(5)));
		
		listUnternehmen.add(new Unternehmen("Sanit�r Tr�sch", listAdresse.get(0)));
		listUnternehmen.add(new Unternehmen("Tiefenbohrungen Meier", listAdresse.get(1)));	
		listUnternehmen.add(new Unternehmen("Kaminbau Sutter", listAdresse.get(2)));	
		listUnternehmen.add(new Unternehmen("T�renfabrik AG", listAdresse.get(3)));	
		listUnternehmen.add(new Unternehmen("Keller's Keller", listAdresse.get(4)));	
		listUnternehmen.add(new Unternehmen("Garagenbau GMBH", listAdresse.get(5)));	
		
		List<Rolle> listTemp2 = filterListIds(listRolle, new int[]{0,3});
		listMangelstatus.add(new Mangelstatus("beauftragt", listTemp2));
		listTemp2 = filterListIds(listRolle, new int[]{0,2});
		listMangelstatus.add(new Mangelstatus("abzukl�ren", listTemp2));
		listTemp2 = filterListIds(listRolle, new int[]{2,3});
		listMangelstatus.add(new Mangelstatus("behoben", listTemp2));
		listTemp2 = filterListIds(listRolle, new int[]{0,1});
		listMangelstatus.add(new Mangelstatus("abgeschlossen", listTemp2));
		listTemp2 = filterListIds(listRolle, new int[]{1,3});
		listMangelstatus.add(new Mangelstatus("angenommen", listTemp2));
		
		for(Adresse adresse : listAdresse){
			adressecontroller.persist(adresse);
		}
		logSeed(listAdresse);
		
		for(Login login : listLogin){
			logincontroller.persist(login);
		}
		logSeed(listLogin);
		
		for(Mangelstatus mangelstatus : listMangelstatus){
			mangelstatuscontroller.persist(mangelstatus);
		}
		logSeed(listMangelstatus);
		
		for(Projekttyp projekttyp : listProjekttyp){
			projekttypcontroller.persist(projekttyp);
		}
		logSeed(listProjekttyp);
		
		for(Sachbearbeiter sachbearbeiter : listSachbearbeiter){
			sachbearbeitercontroller.persist(sachbearbeiter);
		}
		logSeed(listSachbearbeiter);
		
		for(Bauherr bauherr : listBauherr){
			bauherrcontroller.persist(bauherr);
		}
		logSeed(listBauherr);
		
		for(Bauleiter bauleiter : listBauleiter){
			bauleitercontroller.persist(bauleiter);
		}
		logSeed(listBauleiter);
		
		for(Kontakt kontakt : listKontakt){
			kontaktcontroller.persist(kontakt);
		}
		logSeed(listKontakt);
		
		for(Mangelstatus mangelstatus : listMangelstatus){
			mangelstatuscontroller.persist(mangelstatus);
		}
		logSeed(listMangelstatus);
		
		for(Mangel mangel : listMangel){
			mangelcontroller.persist(mangel);
		}
		logSeed(listMangel);
		
		for(Subunternehmen subunternehmen : listSubunternehmen){
			subunternehmencontroller.persist(subunternehmen);
		}
		logSeed(listSubunternehmen);
		
		for(Unternehmen unternehmen : listUnternehmen){
			unternehmencontroller.persist(unternehmen);
		}
		logSeed(listUnternehmen);
		
		*/
	}
	
	
	
	/**
	 * Log the seed task.
	 * 
	 * @param list the list to be logged.
	 */
	private <T> void logSeed(List<T> list) {
		if(list.size() > 0){
			log.info("Seeded: " + list.size() + list.get(0).getClass().getSimpleName());
		}else{
			log.error("Nothing seeded for list: " + list.toString());
		}		
	}

	/**
	 * Filter a list by an array of index identifiers.
	 * 
	 * @param list the list to filter.
	 * @param ids the collection of index identifiers.
	 * @return the filtered list.
	 */
	private <T> List<T> filterListIds(List<T> list, int[] ids){
		List<T> listtmp = new ArrayList<T>();
		for(int i : ids){
			listtmp.add((T) list.get(i));
		}
		return listtmp;		
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

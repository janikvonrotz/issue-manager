package ch.issueman.webservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.ConfigHelper;
import ch.issueman.common.FilterHelper;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projektleitung;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Rolle;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * Seeds the database with data.
 * 
 * @author Erwin Willi, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class Seed {
	
	public static void main(String[] args) {
		(new Seed()).seed();
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
		 * Projekttyp 
		 * Adresse (no persist, no delete)
		 * Mangelstatus
		 * Sachbearbeiter
		 * Bauleiter
		 * Subunternehmen
		 * Unternehmen (no persist, no delete)
		 * Bauherr
		 * Projektleitung (no persist, no delete)
		 * Projekt
		 * Kontakt
		 * Login
		 * Kommentar
		 * Mangel 
		 * 
		 * Sort for delete all: Opposite direction
		 */	
		
		List<Arbeitstyp> listArbeitstyp = new ArrayList<Arbeitstyp>();
		List<Ort> listOrt = new ArrayList<Ort>();
		List<Rolle> listRolle = new ArrayList<Rolle>();
		List<Projekttyp> listProjekttyp = new ArrayList<Projekttyp>();
		List<Adresse> listAdresse = new ArrayList<Adresse>();
		List<Mangelstatus> listMangelstatus = new ArrayList<Mangelstatus>();
		List<Sachbearbeiter> listSachbearbeiter = new ArrayList<Sachbearbeiter>();
		List<Bauleiter> listBauleiter = new ArrayList<Bauleiter>();
		List<Subunternehmen> listSubunternehmen = new ArrayList<Subunternehmen>();
		List<Unternehmen> listUnternehmen = new ArrayList<Unternehmen>();
		List<Bauherr> listBauherr = new ArrayList<Bauherr>();
		List<Projektleitung> listProjektleitung = new ArrayList<Projektleitung>();
		List<Projekt> listProjekt = new ArrayList<Projekt>();
		List<Kontakt> listKontakt = new ArrayList<Kontakt>();
		List<Mangel> listMangel = new ArrayList<Mangel>();
		List<Login> listLogin = new ArrayList<Login>();
		List<Kommentar> listKommentar = new ArrayList<Kommentar>();
		
		Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
		Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
		Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
		Controller<Projekttyp, Integer> projekttypcontroller = new Controller<Projekttyp, Integer>(Projekttyp.class);
		Controller<Mangelstatus, Integer> mangelstatuscontroller = new Controller<Mangelstatus, Integer>(Mangelstatus.class);
		Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
		Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
		Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
		Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);
		Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
		Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
		Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
		Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
		Controller<Kommentar, Integer> kommentarcontroller = new Controller<Kommentar, Integer>(Kommentar.class);
		
		mangelcontroller.deleteAll();
		kommentarcontroller.deleteAll();
		logincontroller.deleteAll();
		kontaktcontroller.deleteAll();
		projektcontroller.deleteAll();
		bauherrcontroller.deleteAll();
		subunternehmencontroller.deleteAll();
		bauleitercontroller.deleteAll();
		sachbearbeitercontroller.deleteAll();
		mangelstatuscontroller.deleteAll();
		projekttypcontroller.deleteAll();
		rollecontroller.deleteAll();
		ortcontroller.deleteAll();
		arbeitstypcontroller.deleteAll();
		
		/**
		 * seed Ort from csv
		 */
		ClassLoader classLoader = getClass().getClassLoader();
		File csv = new File(classLoader.getResource(ConfigHelper.getConfig("seed.Ort", "Orschaften.csv")).getFile());
		try {
			FileReader fr = new FileReader(csv);
			CSVParser parser = new CSVParser(fr, CSVFormat.EXCEL.withDelimiter(';').withHeader());
			 for (CSVRecord r : parser) {
				 listOrt.add(new Ort(Integer.parseInt(r.get("PLZ")), r.get("Ortsbezeichnung")));
			 }
			 parser.close();
			 persistList(listOrt, ortcontroller);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * seed Arbeitstyp
		 */
		listArbeitstyp.add(new Arbeitstyp("Neubau"));
		listArbeitstyp.add(new Arbeitstyp("Umbau"));
		listArbeitstyp.add(new Arbeitstyp("Renovation"));
		listArbeitstyp.add(new Arbeitstyp("Teil-Renovation"));
		persistList(listArbeitstyp, arbeitstypcontroller);
		
		/**
		 * seed Rolle
		 */
		listRolle.add(new Rolle("Sachbearbeiter"));
		listRolle.add(new Rolle("Bauleiter"));
		listRolle.add(new Rolle("Kontaktperson"));
		listRolle.add(new Rolle("Kontaktadmin"));
		persistList(listRolle, rollecontroller);
		
		/**
		 * seed Projekttyp
		 */
		listProjekttyp.add(new Projekttyp("Einfamilienhaus"));
		listProjekttyp.add(new Projekttyp("Mehrfamilienhaus"));
		listProjekttyp.add(new Projekttyp("Wohnung"));
		listProjekttyp.add(new Projekttyp("Garage"));
		listProjekttyp.add(new Projekttyp("Gartenhaus"));
		persistList(listProjekttyp, projekttypcontroller);
		
		/**
		 * seed Adresse (no persist)
		 */
		listAdresse.add(new Adresse("Zugerstrasse", listOrt.get(0)));
		listAdresse.add(new Adresse("Luzernerstrasse", listOrt.get(1)));
		listAdresse.add(new Adresse("Neubühlstrasse", listOrt.get(2)));
		listAdresse.add(new Adresse("Maurerstrasse", listOrt.get(20)));
		listAdresse.add(new Adresse("Untergrundstrasse", listOrt.get(35)));
		listAdresse.add(new Adresse("Baslerstrasse", listOrt.get(50)));
		
		listAdresse.add(new Adresse("Brüelstrasse", listOrt.get(0)));
		listAdresse.add(new Adresse("Marfurtermarkt", listOrt.get(1)));
		listAdresse.add(new Adresse("Lugisbüel", listOrt.get(2)));
		listAdresse.add(new Adresse("Daubrenntobel", listOrt.get(20)));
		listAdresse.add(new Adresse("Obergrundstrasse", listOrt.get(35)));
		listAdresse.add(new Adresse("Lustigstrasse", listOrt.get(50)));
		
		listAdresse.add(new Adresse("Nichtlustigstrasse", listOrt.get(50)));
		listAdresse.add(new Adresse("Nichtlustigstrasse456", listOrt.get(50)));
		listAdresse.add(new Adresse("Mathisstrasse", listOrt.get(50)));
		listAdresse.add(new Adresse("Burgunderstrasse", listOrt.get(50)));
		listAdresse.add(new Adresse("Sempachstrasse", listOrt.get(50)));
		listAdresse.add(new Adresse("Mobilestrasse", listOrt.get(80)));
		listAdresse.add(new Adresse("Testtest", listOrt.get(90)));
		
		/**
		 *  seed Mangelstatus
		 */
		listMangelstatus.add(new Mangelstatus("beauftragt", FilterHelper.filterListIds(listRolle, new int[]{0,3})));
		listMangelstatus.add(new Mangelstatus("abzuklären", FilterHelper.filterListIds(listRolle, new int[]{0,2})));
		listMangelstatus.add(new Mangelstatus("behoben", FilterHelper.filterListIds(listRolle, new int[]{2,3})));
		listMangelstatus.add(new Mangelstatus("abgeschlossen", FilterHelper.filterListIds(listRolle, new int[]{0,1})));
		listMangelstatus.add(new Mangelstatus("angenommen", FilterHelper.filterListIds(listRolle, new int[]{1,3})));
		persistList(listMangelstatus, mangelstatuscontroller);
		
		/**
		 *  seed Sachbearbeiter
		*/ 
		listSachbearbeiter.add(new Sachbearbeiter("sb","sb","sb@im.ch"));
		listSachbearbeiter.add(new Sachbearbeiter("Peter","Lustig","peter.lustig@im.ch"));
		persistList(listSachbearbeiter, sachbearbeitercontroller);
		
		/**
		 *  seed Bauleiter
		 */
		listBauleiter.add(new Bauleiter("bl","bl","bl@im.ch"));
		listBauleiter.add(new Bauleiter("Hans","Bruder","hans.bruder@im.ch"));
		listBauleiter.add(new Bauleiter("Cisco", "Franz", "franc.cisco@cisco.ch"));
		listBauleiter.add(new Bauleiter("Capone", "Don", "don.capone@mafia.ch"));
		listBauleiter.add(new Bauleiter("Wachter", "Hans-Peter", "hp@willibau.ch"));
		listBauleiter.add(new Bauleiter("Malgin", "Igor", "igor.malgin@power.ch"));
		listBauleiter.add(new Bauleiter("Loser", "Bruno", "bruno.loser@grebo.ch"));
		listBauleiter.add(new Bauleiter("Dell", "Walter", "walter.dell@doit.ch"));
		persistList(listBauleiter, bauleitercontroller);
		
		/**
		 * seed Subunternehmen
		 */
		listSubunternehmen.add(new Subunternehmen("Hausbau AG", listAdresse.get(0)));
		listSubunternehmen.add(new Subunternehmen("Gartenbau AG", listAdresse.get(1)));
		listSubunternehmen.add(new Subunternehmen("Mauerbau AG", listAdresse.get(2)));
//		listSubunternehmen.add(new Subunternehmen("Gerüstbau AG", listAdresse.get(3)));
//		listSubunternehmen.add(new Subunternehmen("Bodenbeläge GMBH", listAdresse.get(4)));
//		listSubunternehmen.add(new Subunternehmen("Dachdecker GMBH", listAdresse.get(5)));
//		listSubunternehmen.add(new Subunternehmen("Duck AG", listAdresse.get(17)));
		persistList(listSubunternehmen, subunternehmencontroller);
		
		/**
		 * seed Unternehmen (no persist)
		 */
		listUnternehmen.add(new Unternehmen("Sanitär Trösch", listAdresse.get(6)));
		listUnternehmen.add(new Unternehmen("Tiefenbohrungen Meier", listAdresse.get(7)));	
		listUnternehmen.add(new Unternehmen("Kaminbau Sutter", listAdresse.get(8)));	
		listUnternehmen.add(new Unternehmen("Türenfabrik AG", listAdresse.get(9)));	
		listUnternehmen.add(new Unternehmen("Keller's Keller", listAdresse.get(10)));	
		listUnternehmen.add(new Unternehmen("Garagenbau GMBH", listAdresse.get(11)));
		
		/**
		 * seed Bauherr
		 */
		listBauherr.add(new Bauherr("Müller", "Hans", "hans.müller@bh.ch", listUnternehmen.get(0)));
		listBauherr.add(new Bauherr("Migros", "Alu", "alu.migros@windowslive.ch", listUnternehmen.get(1)));
		listBauherr.add(new Bauherr("Sommer", "Mirco", "mirco.sommer@gmail.ch", listUnternehmen.get(2)));
		listBauherr.add(new Bauherr("Zwimpfer", "Margrit", "margrit.zwimpfer@hotmail.ch", listUnternehmen.get(3)));
		listBauherr.add(new Bauherr("Fäh", "Linda", "linda.fäh@miss.ch", listUnternehmen.get(4)));
		persistList(listBauherr, bauherrcontroller);
		
		/**
		 * seed Projektleitung (no persist)
		 */
		listProjektleitung.add(new Projektleitung(listBauleiter.get(0), new GregorianCalendar(2015,1,4), new GregorianCalendar(2015,6,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(1), new GregorianCalendar(2015,2,12), new GregorianCalendar(2015,7,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(2), new GregorianCalendar(2015,3,16), new GregorianCalendar(2015,8,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(3), new GregorianCalendar(2015,4,20), new GregorianCalendar(2015,9,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(3), new GregorianCalendar(2015,4,13), new GregorianCalendar(2015,10,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(4), new GregorianCalendar(2015,2,9), new GregorianCalendar(2015,1,5)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(4), new GregorianCalendar(2015,2,10), new GregorianCalendar(2015,7,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(5), new GregorianCalendar(2015,3,11), new GregorianCalendar(2015,8,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(5), new GregorianCalendar(2015,3,12), new GregorianCalendar(2015,5,4)));
		listProjektleitung.add(new Projektleitung(listBauleiter.get(1), new GregorianCalendar(2015,2,13), new GregorianCalendar(2015,6,4)));
		
		/**
		 * seed Projekt
		 */
		listProjekt.add(new Projekt("Renovation Turm", listAdresse.get(12), listArbeitstyp.get(2), listProjekttyp.get(1), listBauherr.get(0), FilterHelper.filterListIds(listProjektleitung, new int[]{0,1}), new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,2)));
		listProjekt.add(new Projekt("Teil-Renovation Haus", listAdresse.get(13), listArbeitstyp.get(3), listProjekttyp.get(1), listBauherr.get(1), FilterHelper.filterListIds(listProjektleitung, new int[]{2,3}), new GregorianCalendar(2015,2,11), new GregorianCalendar(2015,3,12)));
		listProjekt.add(new Projekt("Umbau Mehrfamilienhaus", listAdresse.get(14), listArbeitstyp.get(1), listProjekttyp.get(1), listBauherr.get(2), FilterHelper.filterListIds(listProjektleitung, new int[]{4,5}), new GregorianCalendar(2015,2,14), new GregorianCalendar(2015,5,25)));
		listProjekt.add(new Projekt("Neubau Garage", listAdresse.get(15), listArbeitstyp.get(0), listProjekttyp.get(3), listBauherr.get(3), FilterHelper.filterListIds(listProjektleitung, new int[]{6,7}), new GregorianCalendar(2015,4,15), new GregorianCalendar(2015,6,2)));
		listProjekt.add(new Projekt("Renovation Gartenhaus", listAdresse.get(16), listArbeitstyp.get(2), listProjekttyp.get(4), listBauherr.get(4), FilterHelper.filterListIds(listProjektleitung, new int[]{8,9}), new GregorianCalendar(2015,5,15), new GregorianCalendar(2015,7,22)));
		persistList(listProjekt, projektcontroller);
		
		/**
		 * seed Kontakt
		 */
		listKontakt.add(new Kontakt("kp","kp","kp@im.ch", listSubunternehmen.get(0), FilterHelper.filterListIds(listProjekt, new int[]{0,1})));
		listKontakt.add(new Kontakt("Sub2","sub2","sub2.sub2@im.ch", listSubunternehmen.get(1), FilterHelper.filterListIds(listProjekt, new int[]{3,4})));
		listKontakt.add(new Kontakt("Sepp","Blatter","sepp.blatter@im.ch", listSubunternehmen.get(2), FilterHelper.filterListIds(listProjekt, new int[]{0,2})));
		listKontakt.add(new Kontakt("ka","ka","ka@im.ch", listSubunternehmen.get(0), FilterHelper.filterListIds(listProjekt, new int[]{0,3})));
		listKontakt.add(new Kontakt("test","test","test.test@im.ch", listSubunternehmen.get(1), FilterHelper.filterListIds(listProjekt, new int[]{3,4})));
		listKontakt.add(new Kontakt("duck","duck","duck.duck@im.ch", listSubunternehmen.get(2), FilterHelper.filterListIds(listProjekt, new int[]{3,4})));
		persistList(listKontakt, kontaktcontroller);
		
		/**
		 * seed Login
		 */
		listLogin.add(new Login(listSachbearbeiter.get(0), "1", listRolle.get(0)));
		listLogin.add(new Login(listSachbearbeiter.get(1), "1", listRolle.get(0)));
		listLogin.add(new Login(listBauleiter.get(0), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(1), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(2), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(3), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(4), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(5), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(6), "1", listRolle.get(1)));
		listLogin.add(new Login(listBauleiter.get(7), "1", listRolle.get(1)));
		listLogin.add(new Login(listKontakt.get(0), "1", listRolle.get(2)));
		listLogin.add(new Login(listKontakt.get(1), "1", listRolle.get(2)));
		listLogin.add(new Login(listKontakt.get(2), "1", listRolle.get(2)));
		listLogin.add(new Login(listKontakt.get(3), "1", listRolle.get(3)));
		listLogin.add(new Login(listKontakt.get(4), "1", listRolle.get(3)));
		listLogin.add(new Login(listKontakt.get(5), "1", listRolle.get(3)));
		persistList(listLogin, logincontroller);

		/**
		 * seed Kommentar
		 */
		String kommentartext = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(0)));
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(1)));
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(2)));
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(1)));
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(8)));
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(2)));
		listKommentar.add(new Kommentar(kommentartext, listLogin.get(0)));
		persistList(listKommentar, kommentarcontroller);
		
		/**
		 * seed Mangel
		 */
		listMangel.add(new Mangel(1, "Mangel 1", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(0), listSubunternehmen.get(0), new GregorianCalendar(2015,1,5), listProjekt.get(0)));
		listMangel.add(new Mangel(2, "Mangel 2", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(0), listSubunternehmen.get(1), new GregorianCalendar(2015,2,5), listProjekt.get(0)));
		listMangel.add(new Mangel(3, "Mangel 3", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(1), listSubunternehmen.get(2), new GregorianCalendar(2015,2,4), listProjekt.get(0)));
		listMangel.add(new Mangel(4, "Mangel 4", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(1), listSubunternehmen.get(0), new GregorianCalendar(2015,10,5), listProjekt.get(0)));
		listMangel.add(new Mangel(5, "Mangel 5", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(2), listSubunternehmen.get(1), new GregorianCalendar(2015,3,6), listProjekt.get(0)));
		listMangel.add(new Mangel(6, "Mangel 6", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(2), listSubunternehmen.get(2), new GregorianCalendar(2015,1,7), listProjekt.get(0)));
		listMangel.add(new Mangel(7, "Mangel 7", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(3), listSubunternehmen.get(0), new GregorianCalendar(2015,5,5), listProjekt.get(0)));
		listMangel.add(new Mangel(8, "Mangel 8", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(3), listSubunternehmen.get(1), new GregorianCalendar(2015,29,5), listProjekt.get(0)));
		listMangel.add(new Mangel(9, "Mangel 9", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(4), listSubunternehmen.get(2), new GregorianCalendar(2015,5,3), listProjekt.get(0)));
		listMangel.add(new Mangel(10, "Mangel 10", listBauleiter.get(0), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(4), listSubunternehmen.get(1), new GregorianCalendar(2015,12,4), listProjekt.get(0)));	
		listMangel.add(new Mangel(1, "Mangel 1", listBauleiter.get(1), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(1), listSubunternehmen.get(2), new GregorianCalendar(2015,2,15), listProjekt.get(1)));
		listMangel.add(new Mangel(2, "Mangel 2", listBauleiter.get(1), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(1), listSubunternehmen.get(0), new GregorianCalendar(2015,3,1), listProjekt.get(1)));
		listMangel.add(new Mangel(1, "Mangel 1", listBauleiter.get(2), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(2), listSubunternehmen.get(1), new GregorianCalendar(2015,2,28), listProjekt.get(2)));
		listMangel.add(new Mangel(2, "Mangel 2", listBauleiter.get(2), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(2), listSubunternehmen.get(2), new GregorianCalendar(2015,4,5), listProjekt.get(2)));
		listMangel.add(new Mangel(1, "Mangel 1", listBauleiter.get(3), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(3), listSubunternehmen.get(0), new GregorianCalendar(2015,5,5), listProjekt.get(3)));
		listMangel.add(new Mangel(2, "Mangel 2", listBauleiter.get(3), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(3), listSubunternehmen.get(1), new GregorianCalendar(2015,5,15), listProjekt.get(3)));
		listMangel.add(new Mangel(1, "Mangel 1", listBauleiter.get(4), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(4), listSubunternehmen.get(2), new GregorianCalendar(2015,5,16), listProjekt.get(4)));
		listMangel.add(new Mangel(2, "Mangel 2", listBauleiter.get(4), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(4), listSubunternehmen.get(0), new GregorianCalendar(2015,7,2), listProjekt.get(4)));
		listMangel.add(new Mangel(3, "Mangel 3", listBauleiter.get(2), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(2), listSubunternehmen.get(1), new GregorianCalendar(2015,4,10), listProjekt.get(2)));
		listMangel.add(new Mangel(4, "Mangel 4", listBauleiter.get(2), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(3), listSubunternehmen.get(2), new GregorianCalendar(2015,5,1), listProjekt.get(2)));
		listMangel.add(new Mangel(3, "Mangel 3", listBauleiter.get(2), FilterHelper.filterListIds(listKommentar, new int[]{0,3}), listMangelstatus.get(3), listSubunternehmen.get(0), new GregorianCalendar(2015,2,15), listProjekt.get(4)));
		persistList(listMangel, mangelcontroller);		
	}
	
	/**
	 * Persist a list by using a controller.
	 * 
	 * @param list the list to persist.
	 * @param controller the controller to be used to persist the list.
	 */
	private <T> void persistList(List<T> list, Controller<T, Integer> controller) {
		for(T t : list){
			controller.persist(t);
		}
		logSeed(list);		
	}

	/**
	 * Log the seed task.
	 * 
	 * @param list the list to be logged.
	 */
	private <T> void logSeed(List<T> list) {
		if(list.size() > 0){
			log.info("Seeded: " + list.size() + " " + list.get(0).getClass().getSimpleName());
		}else{
			log.error("Nothing seeded for list: " + list.toString());
		}		
	}
}

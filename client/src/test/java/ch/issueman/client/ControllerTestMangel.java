package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Adresse;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Rolle;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Unternehmen;


public class ControllerTestMangel {
	
	private String kommentartext = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed t acc";
	
//	private Mangel mangel = new Mangel(1, new Bauleiter("TestNachname", "TestVorname","test.test@test.ch"), 
//			new Kommentar(kommentartext, new Login(new Sachbearbeiter("", "", "test@test.ch"), "1", null)), 
//			new Mangelstatus("test", filterListIds(listRolle, new int[]{0,3})), new GregorianCalendar(1,1,2015), 
//			new Projekt("Test Rennovation", new Adresse("Testhausen", new Ort(7000, "Chur")), new Arbeitstyp("Test"), 
//					new Projekttyp("Testbau"), new Bauherr("Test", "Bauherr", "test.bauherr@bauherrtest.ch", 
//					new Unternehmen("Testfirma", new Adresse("Scheiss", new Ort(6666, "Hölle")))), filterListIds(listProjektleitung, new int[]{0,1}), 
//					new GregorianCalendar(1,1,2015), new GregorianCalendar(1,2,2015)));  
	
	private Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
	private List<Mangel> listMangel;
	
	
	@Before
	public void setUp() throws Exception {
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
	}

	@After
	public void tearDown() throws Exception {
		Context.logout();
	}
	
	@Test
	public void testPersist() {
		try {
			mangelcontroller.persist(mangel);
		} catch (Exception e) {
			fail("Persist for Arbeitstyp failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			listMangel = mangelcontroller.getAll();
		} catch (Exception e) {
			fail("Get list of Arbeitstyp failed");
		}	
	}
	
//	@Test
//	public void testGetById() {
//		try {
//			listMangel = mangelcontroller.getAll();
//			arbeitstyp = arbeitstypcontroller.getById(listArbeitstyp.get(listArbeitstyp.size()-1).getId());
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Get by id for Arbeitstyp failed");
//		}
//	}

//	@Test
//	public void testUpdate() {
//		try {
//			arbeitstyp.setArbeitstyp("testArbeitstypUpdated");
//			arbeitstypcontroller.update(arbeitstyp);
//		} catch (Exception e) {
//			fail("Update for Arbeitstyp failed");
//		}
//	}
//
//	@Test
//	public void testDelete() {
//		try {
//			arbeitstypcontroller.persist(arbeitstyp);
//		} catch (Exception e) {
//			fail("Deletion of Arbeitstyp failed");
//		}
//	}
}

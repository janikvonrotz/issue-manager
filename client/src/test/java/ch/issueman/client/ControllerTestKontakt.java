package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import ch.issueman.common.FilterHelper;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

/**
 * Testclass ControllerTestKontakt
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

public class ControllerTestKontakt {
	
	private Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
		
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
			Kontakt kontakt = new Kontakt("Kontakt", "Test2", "test2@kontakt.ch", 
					FilterHelper.filterListIds(new Controller<Subunternehmen, Integer>(Subunternehmen.class).getAll(), new int[]{2}).get(0), null);
			kontaktcontroller.persist(kontakt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Persist for Kontakt failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Kontakt> listKontakt = kontaktcontroller.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get list of Kontakt failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Kontakt> listKontakt = kontaktcontroller.getAll();
			@SuppressWarnings("unused")
			Kontakt kontakt = kontaktcontroller.getById(listKontakt.get(listKontakt.size()-1).getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get by id for Kontakt failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Kontakt> listKontakt = kontaktcontroller.getAll();
			Kontakt kontakt = listKontakt.get(listKontakt.size()-1);
			kontakt.setVorname("NachUpdate");
			kontaktcontroller.update(kontakt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Update for Kontakt failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Kontakt> listKontakt = kontaktcontroller.getAll();
			kontaktcontroller.delete(listKontakt.get(listKontakt.size()-1));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Deletion of Kontakt failed");
		}
	}
}

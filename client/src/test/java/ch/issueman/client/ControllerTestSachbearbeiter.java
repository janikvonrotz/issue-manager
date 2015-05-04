package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

/**
 * Testclass ControllerTestSachbearbeiter
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

public class ControllerTestSachbearbeiter {

	private Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
	
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
			Sachbearbeiter sachbearbeiter = new Sachbearbeiter("John", "Test", "test@john.ch");
			sachbearbeitercontroller.persist(sachbearbeiter);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Persist for Sachbearbeiter failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Sachbearbeiter> listSachbearbeiter = sachbearbeitercontroller.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get list of Sachbearbeiter failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Sachbearbeiter> listSachbearbeiter = sachbearbeitercontroller.getAll();
			@SuppressWarnings("unused")
			Sachbearbeiter sachbearbeiter = sachbearbeitercontroller.getById(listSachbearbeiter.get(listSachbearbeiter.size()-1).getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get by id for Sachbearbeiter failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Sachbearbeiter> listSachbearbeiter = sachbearbeitercontroller.getAll();
			Sachbearbeiter sachbearbeiter = listSachbearbeiter.get(listSachbearbeiter.size()-1);
			sachbearbeiter.setNachname("testSachbearbeiterUpdated");
			sachbearbeitercontroller.update(sachbearbeiter);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Update for Sachbearbeiter failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Sachbearbeiter> listSachbearbeiter = sachbearbeitercontroller.getAll();
			sachbearbeitercontroller.delete(listSachbearbeiter.get(listSachbearbeiter.size()-1));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Deletion of Sachbearbeiter failed");
		}
	}
}

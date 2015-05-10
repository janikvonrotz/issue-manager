package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Bauleiter;

/**
 * Testclass ControllerTestBauleiter
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

public class ControllerTestBauleiter {

	private Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
	
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
			Bauleiter bauleiter = new Bauleiter("John", "Test", "test@john.ch");
			bauleitercontroller.persist(bauleiter);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Persist for Bauleiter failed");
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Bauleiter> listBauleiter = bauleitercontroller.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get list of Bauleiter failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Bauleiter> listBauleiter = bauleitercontroller.getAll();
			@SuppressWarnings("unused")
			Bauleiter bauleiter = bauleitercontroller.getById(listBauleiter.get(listBauleiter.size()-1).getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get by id for Bauleiter failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Bauleiter> listBauleiter = bauleitercontroller.getAll();
			Bauleiter bauleiter = listBauleiter.get(listBauleiter.size()-1);
			bauleiter.setNachname("testBauleiterUpdated2");
			bauleitercontroller.update(bauleiter);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Update for Bauleiter failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Bauleiter> listBauleiter = bauleitercontroller.getAll();
			bauleitercontroller.delete(listBauleiter.get(listBauleiter.size()-1));
		} catch (Exception e) {
				assertEquals("Required Roles for DELETE on Bauleiter don't match", e.getMessage());
		}
	}
}

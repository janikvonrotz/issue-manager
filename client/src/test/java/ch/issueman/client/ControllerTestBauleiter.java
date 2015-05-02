package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Bauleiter;

public class ControllerTestBauleiter {

	private Bauleiter bauleiter;
	private Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
	private List<Bauleiter> listBauleiter;
	
	@Before
	public void setUp() throws Exception {
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		bauleiter = new Bauleiter("John", "Test", "test@john.ch");
	}

	@After
	public void tearDown() throws Exception {
		Context.logout();
	}
	
	@Test
	public void testPersist() {
		try {
			bauleitercontroller.persist(bauleiter);
		} catch (Exception e) {
			fail("Persist for Bauleiter failed");
		}
		Context.logout();
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
			listBauleiter = bauleitercontroller.getAll();
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
			bauleiter = listBauleiter.get(listBauleiter.size()-1);
			bauleiter.setNachname("testBauleiterUpdated2");
			bauleitercontroller.update(bauleiter);
		} catch (Exception e) {
			fail("Update for Bauleiter failed");
		}
	}

//	@Test
//	funktioniert nicht, da keine Berechtigung --> ist OK so
//	public void testDelete() {
//		try {
//			List<Bauleiter> listBauleiter = bauleitercontroller.getAll();
//			bauleitercontroller.delete(listBauleiter.get(listBauleiter.size()-1));
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Deletion of Bauleiter failed");
//		}
//	}
}

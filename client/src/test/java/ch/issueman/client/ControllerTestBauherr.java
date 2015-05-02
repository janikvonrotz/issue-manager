package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Bauherr;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

public class ControllerTestBauherr {

	private Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);

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
			bauherrcontroller.persist(new Bauherr("testName", "testVorname", "test@email.ch", null));
		} catch (Exception e) {
			fail("Persist for Bauherr failed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Bauherr> listBauherr = bauherrcontroller.getAll();
		} catch (Exception e) {
			fail("Get list of Bauherr failed");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Bauherr> listBauherr = bauherrcontroller.getAll();
			@SuppressWarnings("unused")
			Bauherr bauherr = bauherrcontroller.getById(listBauherr.get(listBauherr.size()-1).getId());
		} catch (Exception e) {
			fail("Get by id for Bauherr failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Bauherr> listBauherr = bauherrcontroller.getAll();
			Bauherr bauherr = listBauherr.get(listBauherr.size()-1);
			bauherr.setNachname("testBauherrUpdated");
			bauherrcontroller.update(bauherr);
		} catch (Exception e) {
			fail("Update for Bauherr failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Bauherr> listBauherr = bauherrcontroller.getAll();
			bauherrcontroller.delete(listBauherr.get(listBauherr.size()-1));
		} catch (Exception e) {
			fail("Deletion of Bauherr failed");
			e.printStackTrace();
		}
	}
}


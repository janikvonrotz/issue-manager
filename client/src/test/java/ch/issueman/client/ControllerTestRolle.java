package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Rolle;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

public class ControllerTestRolle {

	private Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);

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
			rollecontroller.persist(new Rolle("testRolle"));
		} catch (Exception e) {
			fail("Persist for Rolle failed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Rolle> listRolle = rollecontroller.getAll();
		} catch (Exception e) {
			fail("Get list of Rolle failed");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Rolle> listRolle = rollecontroller.getAll();
			@SuppressWarnings("unused")
			Rolle rolle = rollecontroller.getById(listRolle.get(listRolle.size()-1).getId());
		} catch (Exception e) {
			fail("Get by id for Rolle failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Rolle> listRolle = rollecontroller.getAll();
			Rolle rolle = listRolle.get(listRolle.size()-1);
			rolle.setBezeichnung("testRolleUpdated");
			rollecontroller.update(rolle);
		} catch (Exception e) {
			fail("Update for Rolle failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Rolle> listRolle = rollecontroller.getAll();
			rollecontroller.delete(listRolle.get(listRolle.size()-1));
		} catch (Exception e) {
			fail("Deletion of Rolle failed");
			e.printStackTrace();
		}
	}
}


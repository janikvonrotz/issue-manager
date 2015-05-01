package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Arbeitstyp;

public class ControllerTestArbeitstyp {

	private Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);

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
			arbeitstypcontroller.persist(new Arbeitstyp("testArbeitstyp"));
		} catch (Exception e) {
			fail("Persist for Arbeitstyp failed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Arbeitstyp> listArbeitstyp = arbeitstypcontroller.getAll();
		} catch (Exception e) {
			fail("Get list of Arbeitstyp failed");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Arbeitstyp> listArbeitstyp = arbeitstypcontroller.getAll();
			@SuppressWarnings("unused")
			Arbeitstyp arbeitstyp = arbeitstypcontroller.getById(listArbeitstyp.get(listArbeitstyp.size()-1).getId());
		} catch (Exception e) {
			fail("Get by id for Arbeitstyp failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Arbeitstyp> listArbeitstyp = arbeitstypcontroller.getAll();
			Arbeitstyp arbeitstyp = listArbeitstyp.get(listArbeitstyp.size()-1);
			arbeitstyp.setArbeitstyp("testArbeitstypUpdated");
			arbeitstypcontroller.update(arbeitstyp);
		} catch (Exception e) {
			fail("Update for Arbeitstyp failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Arbeitstyp> listArbeitstyp = arbeitstypcontroller.getAll();
			arbeitstypcontroller.delete(listArbeitstyp.get(listArbeitstyp.size()-1));
		} catch (Exception e) {
			fail("Deletion of Arbeitstyp failed");
			e.printStackTrace();
		}
	}
}

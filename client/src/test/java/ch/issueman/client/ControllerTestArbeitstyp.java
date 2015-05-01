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

	private Arbeitstyp arbeitstyp = new Arbeitstyp("testArbeitstyp");
	private Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
	private List<Arbeitstyp> listArbeitstyp;
	
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
			arbeitstypcontroller.persist(arbeitstyp);
		} catch (Exception e) {
			fail("Persist for Arbeitstyp failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			listArbeitstyp = arbeitstypcontroller.getAll();
		} catch (Exception e) {
			fail("Get list of Arbeitstyp failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			listArbeitstyp = arbeitstypcontroller.getAll();
			arbeitstyp = arbeitstypcontroller.getById(listArbeitstyp.get(listArbeitstyp.size()-1).getId());
		} catch (Exception e) {
			fail("Get by id for Arbeitstyp failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			arbeitstyp.setArbeitstyp("testArbeitstypUpdated");
			arbeitstypcontroller.update(arbeitstyp);
		} catch (Exception e) {
			fail("Update for Arbeitstyp failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			listArbeitstyp = arbeitstypcontroller.getAll();
			arbeitstyp = listArbeitstyp.get(listArbeitstyp.size()-1);
			System.out.println(arbeitstyp.toString());
			arbeitstypcontroller.delete(arbeitstyp);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Deletion of Arbeitstyp failed");
		}
	}
}

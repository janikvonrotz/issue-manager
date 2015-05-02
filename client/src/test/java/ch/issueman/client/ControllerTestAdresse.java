package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Login;
import ch.issueman.common.Ort;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Adresse;

public class ControllerTestAdresse {

	private Controller<Adresse, Integer> adresseController = new Controller<Adresse, Integer>(Adresse.class);
	private Controller<Ort, Integer> ortController = new Controller<Ort, Integer>(Ort.class);

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
			List<Ort> listOrt = new ArrayList<Ort>();
			listOrt = ortController.getAll();
			adresseController.persist(new Adresse("Musterstrasse 1", listOrt.get(0)));
		} catch (Exception e) {
			fail("Persist for Adresse failed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Adresse> listAdresse = adresseController.getAll();
		} catch (Exception e) {
			fail("Get list of Adresse failed");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Adresse> listAdresse = adresseController.getAll();
			@SuppressWarnings("unused")
			Adresse Adresse = adresseController.getById(listAdresse.get(listAdresse.size()-1).getId());
		} catch (Exception e) {
			fail("Get by id for Adresse failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Adresse> listAdresse = adresseController.getAll();
			Adresse adresse = listAdresse.get(listAdresse.size()-1);
			adresse.setStrasse("Updatedstrasse 19889askjgd7");
			adresseController.update(adresse);
		} catch (Exception e) {
			fail("Update for Adresse failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Adresse> listAdresse = adresseController.getAll();
			adresseController.delete(listAdresse.get(listAdresse.size()-1));
		} catch (Exception e) {
			fail("Deletion of Adresse failed");
			e.printStackTrace();
		}
	}
}

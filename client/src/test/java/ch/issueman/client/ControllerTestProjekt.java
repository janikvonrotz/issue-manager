package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;





import ch.issueman.common.FilterHelper;
import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Ort;
/**
 * Testclass ControllerTestProjekt
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

public class ControllerTestProjekt {
	
	private Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
		
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
			Projekt projekt = new Projekt("Neubau Vor Update",
					new Adresse("Controllerstrasse", FilterHelper.filterListIds(new Controller <Ort, Integer>(Ort.class).getAll(), new int[]{2}).get(0)),
					FilterHelper.filterListIds(new Controller <Arbeitstyp, Integer>(Arbeitstyp.class).getAll(), new int[]{2}).get(0),
					FilterHelper.filterListIds(new Controller <Projekttyp, Integer>(Projekttyp.class).getAll(), new int[]{2}).get(0),
					FilterHelper.filterListIds(new Controller <Bauherr, Integer>(Bauherr.class).getAll(), new int[]{2}).get(0),
					null, new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,1,2));
			projektcontroller.persist(projekt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Persist for Projekt failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Projekt> listProjekt = projektcontroller.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get list of Projekt failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Projekt> listProjekt = projektcontroller.getAll();
			@SuppressWarnings("unused")
			Projekt projekt = projektcontroller.getById(listProjekt.get(listProjekt.size()-1).getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get By Id of Projekt failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Projekt> listProjekt = projektcontroller.getAll();
			Projekt projekt = listProjekt.get(listProjekt.size()-1);
			projekt.setTitle("Renovation nach Update");
			projektcontroller.update(projekt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Update for Projekt failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Projekt> listProjekt = projektcontroller.getAll();
			projektcontroller.delete(listProjekt.get(listProjekt.size()-1));
		} catch (Exception e) {
			assertEquals("Required Roles for DELETE on Projekt don't match", e.getMessage());
		}
	}
}

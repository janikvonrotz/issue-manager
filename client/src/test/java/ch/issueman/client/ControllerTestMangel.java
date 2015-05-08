package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import ch.issueman.common.Bauleiter;
import ch.issueman.common.FilterHelper;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

/**
 * Testclass ControllerTestMangel
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */

public class ControllerTestMangel {
	
	private Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
		
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
			Mangel mangel = new Mangel(1,  "vorUpdate",
					FilterHelper.filterListIds(new Controller<Bauleiter, Integer>(Bauleiter.class).getAll(), new int[]{2}).get(0),
					null,
					FilterHelper.filterListIds(new Controller <Mangelstatus, Integer>(Mangelstatus.class).getAll(), new int[]{2}).get(0),
					FilterHelper.filterListIds(new Controller <Subunternehmen, Integer>(Subunternehmen.class).getAll(), new int[]{2}).get(0),
					new GregorianCalendar(1,1,2015), 
					FilterHelper.filterListIds(new Controller <Projekt, Integer>(Projekt.class).getAll(), new int[]{2}).get(0)
				);
			mangelcontroller.persist(mangel);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Persist for Mangel failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Mangel> listMangel = mangelcontroller.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get list of Mangel failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Mangel> listMangel = mangelcontroller.getAll();
			@SuppressWarnings("unused")
			Mangel mangel = mangelcontroller.getById(listMangel.get(listMangel.size()-1).getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get by id for Mangel failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Mangel> listMangel = mangelcontroller.getAll();
			Mangel mangel = listMangel.get(listMangel.size()-1);
			mangel.setMangel("asdasadfadsfasdffasd");
			mangelcontroller.update(mangel);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Update for Mangel failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Mangel> listMangel = mangelcontroller.getAll();
			mangelcontroller.delete(listMangel.get(listMangel.size()-1));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Deletion of Mangel failed");
		}
	}
}

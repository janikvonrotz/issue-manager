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


public class ControllerTestMangel {
	
	private Mangel mangel;
	private Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
	private List<Mangel> listMangel;
		
	@Before
	public void setUp() throws Exception {
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		mangel = new Mangel(1, 
			FilterHelper.filterListIds(new Controller<Bauleiter, Integer>(Bauleiter.class).getAll(), new int[]{2}).get(0),
			null,
			FilterHelper.filterListIds(new Controller<Mangelstatus, Integer>(Mangelstatus.class).getAll(), new int[]{2}).get(0),
			new GregorianCalendar(1,1,2015), 
			FilterHelper.filterListIds(new Controller <Projekt, Integer>(Projekt.class).getAll(), new int[]{2}).get(0)
		);
	}

	@After
	public void tearDown() throws Exception {
		Context.logout();
	}
	
	@Test
	public void testPersist() {
		try {
			mangelcontroller.persist(mangel);
		} catch (Exception e) {
			fail("Persist for Mangel failed");
		}
		Context.logout();
	}
	
	@Test
	public void testGetAll() {
		try {
			listMangel = mangelcontroller.getAll();
		} catch (Exception e) {
			fail("Get list of Mangel failed");
		}	
	}
	
//	@Test
//	public void testGetById() {
//		try {
//			listMangel = mangelcontroller.getAll();
//			arbeitstyp = arbeitstypcontroller.getById(listArbeitstyp.get(listArbeitstyp.size()-1).getId());
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Get by id for Arbeitstyp failed");
//		}
//	}

//	@Test
//	public void testUpdate() {
//		try {
//			arbeitstyp.setArbeitstyp("testArbeitstypUpdated");
//			arbeitstypcontroller.update(arbeitstyp);
//		} catch (Exception e) {
//			fail("Update for Arbeitstyp failed");
//		}
//	}
//
//	@Test
//	public void testDelete() {
//		try {
//			arbeitstypcontroller.persist(arbeitstyp);
//		} catch (Exception e) {
//			fail("Deletion of Arbeitstyp failed");
//		}
//	}
}

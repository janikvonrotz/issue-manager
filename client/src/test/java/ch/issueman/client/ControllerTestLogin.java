package ch.issueman.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Bauleiter;
import ch.issueman.common.FilterHelper;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Rolle;

/**
 * Testclass ControllerTestLogin
 *
 * @author Erwin Willi
 * @version 1.0.0
 * @since 1.0.0
 */
public class ControllerTestLogin {

	private Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);

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
			logincontroller.persist(new Login(new Bauleiter("Test", "Login", "test.login@ba.ch"), "1",
					FilterHelper.filterListIds(new Controller <Rolle, Integer>(Rolle.class).getAll(), new int[]{2}).get(0)));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Persist for Login failed");
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<Login> listLogin = logincontroller.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get list of Login failed");
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<Login> listLogin = logincontroller.getAll();
			@SuppressWarnings("unused")
			Login bauherr = logincontroller.getById(listLogin.get(listLogin.size()-1).getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Get by id for Login failed");
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<Login> listLogin = logincontroller.getAll();
			Login login = listLogin.get(listLogin.size()-1);
			login.setPasswort("passwort");
			logincontroller.update(login);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Update for Login failed");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Login> listLogin = logincontroller.getAll();
			logincontroller.delete(listLogin.get(listLogin.size()-1));
		} catch (Exception e) {
			assertEquals("Required Roles for DELETE on Login don't match", e.getMessage());
		}
	}
}


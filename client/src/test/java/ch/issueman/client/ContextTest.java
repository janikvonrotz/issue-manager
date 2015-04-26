package ch.issueman.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

public class ContextTest {

	@Before
	public void setUp() throws Exception {
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
	}

	@After
	public void tearDown() throws Exception{
		Context.logout();
	}

	@Test
	public void testLogin() {
		assertTrue("Default user Sachbearbeiter must be able to log in", Context.login());
	}
	
	@Test
	public void testUserHasRole() {
		Context.login();
		assertTrue("User Sachbearbeiter must have role Sachbearbeiter", Context.ifUserHasRole("Sachbearbeiter"));
	}
}

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
		Context.setLogin(new Login(new Sachbearbeiter("", "", "<username>"), "<password>", null));
	}

	@After
	public void tearDown() throws Exception{
		Context.logout();
	}

	@Test
	public void testLogin() {
		assertTrue(Context.login());
		fail("Not yet implemented");
	}
	
	@Test
	public void testUserHasRole() {
		assertTrue(Context.ifUserHasRole("Sachbearbeiter"));
		fail("Not yet implemented");
	}
}

package ch.issueman.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ContextTest.class, ControllerTestAdresse.class,
		ControllerTestArbeitstyp.class, ControllerTestBauherr.class,
		ControllerTestBauleiter.class, ControllerTestKontakt.class,
		ControllerTestMangel.class, ControllerTestRolle.class,
		ControllerTestSachbearbeiter.class })
public class AllTests {

}

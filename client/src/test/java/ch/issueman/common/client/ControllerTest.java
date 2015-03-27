package ch.issueman.common.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ch.issueman.common.Employer;

public class ControllerTest {

	//@Test
	public void test() {

		List<Employer> employers = (new Controller<Employer, Integer>(
				Employer.class)).getAll();

		assertTrue("Expect 20 employers, retrieved: " + employers.size(),
				employers.size() == 20);
	}
}

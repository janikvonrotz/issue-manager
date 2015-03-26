package ch.issueman.common.webservice;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.User;

import com.github.javafaker.Faker;

public class ControllerTest {

	@Test
	public void test() {

		Faker faker = new Faker();
		
		Controller<Person, Integer> pc = new Controller<Person, Integer>(Person.class);
		Controller<User, Integer> uc = new Controller<User, Integer>(User.class);
		Controller<Employer, Integer> ec = new Controller<Employer, Integer>(Employer.class);
		
		pc.deleteAll();
		uc.deleteAll();
		ec.deleteAll();
		
		for(int i = 0; i<20;i++){
			Person p = new Person(faker.name().firstName());
			User u = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.letterify("??????"), "Administrator");
			Employer e = new Employer(faker.name().firstName(), faker.name().lastName());		
					
			pc.persist(p);
			uc.persist(u);
			ec.persist(e);
		}
		
		List<Person> people = pc.getAll();
		
		assertTrue("Expect 60 people, retrieved: " + people.size() , people.size() == 60);		
	}

}
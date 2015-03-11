package ch.issueman.common.client;

import java.util.List;

import ch.issueman.common.Person;

public class HomeController {

	public static void main(String[] args) {
		
		PersonClientController c = new PersonClientController();
		System.out.println(c.getById(2).getName());
		
		List<Person> l = c.getAll();
		
		for (Person p : l) {
			System.out.println(p.getName());
		}
	}

}

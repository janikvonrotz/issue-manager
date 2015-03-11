package ch.issueman.common.webservice;

import java.util.List;

import ch.issueman.common.DAO;
import ch.issueman.common.Person;

public class PersonController implements DAO<Person, Integer>{
	
	private static Controller<Person, Integer> controller;
	
	public PersonController() {
		controller = new Controller<Person, Integer>(Person.class);
	}
	
	public void persist(Person person) {
		controller.persist(person);
	}

	public Person getById(Integer id) {
		Person person = controller.getById(id);
		return person;
	}

	public List<Person> getAll() {
		List<Person> people = controller.getAll();
		return people;
	}

	public void update(Person person) {
		controller.update(person);
	}

	public void deleteById(Integer id) {
		controller.delete(controller.getById(id));
	}

	public void delete(Person person) {
		controller.delete(person);
	}
	
	public void deleteAll() {
		controller.deleteAll();
	}
}

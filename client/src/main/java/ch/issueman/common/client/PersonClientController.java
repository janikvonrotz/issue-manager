package ch.issueman.common.client;

import java.util.List;

import ch.issueman.common.Person;
import ch.issueman.common.webservice.DAO;

public class PersonClientController implements DAO<Person, Integer> {
	
	private static ClientController<Person, Integer> controller;
	
	public PersonClientController() {
		controller = new ClientController<Person, Integer>(Person.class, "http://localhost:8080/webservice/person");
	}
	
	@Override
	public void persist(Person t) {
		controller.persist(t);
	}

	@Override
	public Person getById(Integer id) {
		return controller.getById(id);
	}

	@Override
	public List<Person> getAll() {
		return controller.getAll();
	}

	@Override
	public void update(Person t) {
		controller.update(t);
	}

	@Override
	public void delete(Person t) {
		controller.delete(t);
	}

	@Override
	public void deleteAll() {
	}

}

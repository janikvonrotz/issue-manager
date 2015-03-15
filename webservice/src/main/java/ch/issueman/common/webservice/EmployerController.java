package ch.issueman.common.webservice;

import java.util.List;

import ch.issueman.common.DAO;
import ch.issueman.common.Employer;

public class EmployerController implements DAO<Employer, Integer>{
	
	private static Controller<Employer, Integer> controller;
	
	public EmployerController() {
		controller = new Controller<Employer, Integer>(Employer.class);
	}
	
	public void persist(Employer employer) {
		controller.persist(employer);
	}

	public Employer getById(Integer id) {
		Employer employer = controller.getById(id);
		return employer;
	}

	public List<Employer> getAll() {
		List<Employer> people = controller.getAll();
		return people;
	}

	public void update(Employer employer) {
		controller.update(employer);
	}

	public void deleteById(Integer id) {
		controller.delete(controller.getById(id));
	}

	public void delete(Employer employer) {
		controller.delete(employer);
	}
	
	public void deleteAll() {
		controller.deleteAll();
	}
}

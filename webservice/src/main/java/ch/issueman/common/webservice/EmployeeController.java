package ch.issueman.common.webservice;

import java.util.List;

import ch.issueman.common.DAO;
import ch.issueman.common.Employee;

public class EmployeeController implements DAO<Employee, Integer>{
	
	private static Controller<Employee, Integer> controller;
	
	public EmployeeController() {
		controller = new Controller<Employee, Integer>(Employee.class);
	}
	
	public void persist(Employee employee) {
		controller.persist(employee);
	}

	public Employee getById(Integer id) {
		Employee employee = controller.getById(id);
		return employee;
	}

	public List<Employee> getAll() {
		List<Employee> people = controller.getAll();
		return people;
	}

	public void update(Employee employee) {
		controller.update(employee);
	}

	public void deleteById(Integer id) {
		controller.delete(controller.getById(id));
	}

	public void delete(Employee employee) {
		controller.delete(employee);
	}
	
	public void deleteAll() {
		controller.deleteAll();
	}
}

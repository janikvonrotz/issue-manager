package ch.issueman.common.webservice;

import java.util.List;
import ch.issueman.common.User;

public class UserController implements DAO<User, Integer>{
	
	private static Controller<User, Integer> controller;
	
	public UserController() {
		controller = new Controller<User, Integer>(User.class);
	}
	
	public void persist(User User) {
		controller.persist(User);
	}

	public User getById(Integer id) {
		User User = controller.getById(id);
		return User;
	}

	public List<User> getAll() {
		List<User> people = controller.getAll();
		return people;
	}

	public void update(User User) {
		controller.update(User);
	}

	public void deleteById(Integer id) {
		controller.delete(controller.getById(id));
	}

	public void delete(User User) {
		controller.delete(User);
	}
	
	public void deleteAll() {
		controller.deleteAll();
	}
}

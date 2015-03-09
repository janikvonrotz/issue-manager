package ch.issueman.common.webservice;

import java.util.List;
import ch.issueman.common.Project;

public class ProjectController implements DAO<Project, Integer>{
	
	private static Controller<Project, Integer> controller;
	
	public ProjectController() {
		controller = new Controller<Project, Integer>(Project.class);
	}
	
	public void persist(Project project) {
		controller.persist(project);
	}

	public Project getById(Integer id) {
		Project project = controller.getById(id);
		return project;
	}

	public List<Project> getAll() {
		List<Project> people = controller.getAll();
		return people;
	}

	public void update(Project project) {
		controller.update(project);
	}

	public void deleteById(Integer id) {
		controller.delete(controller.getById(id));
	}

	public void delete(Project project) {
		controller.delete(project);
	}
	
	public void deleteAll() {
		controller.deleteAll();
	}
}

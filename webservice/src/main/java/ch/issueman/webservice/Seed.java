package ch.issueman.webservice;

import java.util.ArrayList;
import java.util.List;

import ch.issueman.common.Comment;
import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.Project;
import ch.issueman.common.User;

import com.github.javafaker.Faker;

public class Seed {

	public static void main(String[] args) {
		
		Faker faker = new Faker();

		Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
		Controller<Employer, Integer> employercontroller = new Controller<Employer, Integer>(Employer.class);
		Controller<Project, Integer> projectcontroller = new Controller<Project, Integer>(Project.class);
		Controller<Comment, Integer> commentcontroller = new Controller<Comment, Integer>(Comment.class);

		projectcontroller.deleteAll();
		commentcontroller.deleteAll();
		usercontroller.deleteAll();
		employercontroller.deleteAll();
		personcontroller.deleteAll();
				
		int i = 0;
		int j = 0;
		
		for (i = 0; i <= 20; i++) {

			Person person = new Person(faker.name().firstName());
			User user = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.letterify("??????"), "Administrator");
			Employer employer = new Employer(faker.name().firstName(), faker.name().lastName());

			personcontroller.persist(person);
			usercontroller.persist(user);
			employercontroller.persist(employer);

			if (i % 4 == 0) {
				
				Project project = new Project("Project: " + faker.name().lastName(), employer);
				
				List<Comment> comments = new ArrayList<Comment>();
				
				for (j = 0; j <= 10; j++) {
					
					Comment comment = new Comment(faker.lorem().paragraph(),user);
					comments.add(comment);
					project.setComments(comments);
				}

				projectcontroller.persist(project);
			}
		}
		
		System.out.println("Seeded: " + i*3 + " people");
		System.out.println("Seeded: " + (i/4+1) + " projects");
		System.out.println("Seeded: " + j*(i/4+1) + " comments");
	}
}

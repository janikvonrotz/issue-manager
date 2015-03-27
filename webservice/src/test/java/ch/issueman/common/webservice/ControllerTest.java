package ch.issueman.common.webservice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.issuema.webservice.Controller;
import ch.issueman.common.Comment;
import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.Project;
import ch.issueman.common.User;

import com.github.javafaker.Faker;

public class ControllerTest {

	@Test
	public void test() {

		Faker faker = new Faker();
		
		Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
		Controller<Employer, Integer> employercontroller = new Controller<Employer, Integer>(Employer.class);
		Controller<Project, Integer> projectcontroller = new Controller<Project, Integer>(Project.class);
		Controller<Comment, Integer> commentcontroller = new Controller<Comment, Integer>(Comment.class);
		
		personcontroller.deleteAll();
		usercontroller.deleteAll();
		employercontroller.deleteAll();
		projectcontroller.deleteAll();
		commentcontroller.deleteAll();
		
		for(int i = 0; i<20;i++){
			
			Person person = new Person(faker.name().firstName());
			User user = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.letterify("??????"), "Administrator");
			Employer employer = new Employer(faker.name().firstName(), faker.name().lastName());		
			
			personcontroller.persist(person);
			usercontroller.persist(user);
			employercontroller.persist(employer);
			
			if(i%4==0){
				Project project = new Project(faker.name().lastName(), employer);
				
				for(int j = 0; j < 10;i++){
					Comment comment = new Comment(faker.lorem().paragraph(), user);
					List<Comment> comments = new ArrayList<Comment>();
					comments.add(comment);
					project.setComments(comments);
					commentcontroller.persist(comment);
				}
				
				projectcontroller.persist(project);
			}
		}
		
		
		List<Person> people = personcontroller.getAll();
		
		assertTrue("Expect 60 people, retrieved: " + people.size() , people.size() == 60);		
	}

}

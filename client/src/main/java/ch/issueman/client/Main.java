package ch.issueman.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.issueman.common.Comment;
import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.Project;
import ch.issueman.common.User;

public class Main {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();
		
		Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
		Controller<Employer, Integer> employercontroller = new Controller<Employer, Integer>(Employer.class);
		Controller<Project, Integer> projectcontroller = new Controller<Project, Integer>(Project.class);
		Controller<Comment, Integer> commentcontroller = new Controller<Comment, Integer>(Comment.class);
		
		List<Project> projects = projectcontroller.getAll();
		for(Project p : projects){
			System.out.println(p.getTitle());
			for(Comment c : p.getComments()){
				System.out.println(c.getUser().getName() + ": " + c.getComment());				
			}
		}	
	}
}

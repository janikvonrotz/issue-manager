package ch.issueman.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.typesafe.config.ConfigFactory;

import ch.issueman.common.Comment;
import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.Project;
import ch.issueman.common.User;

public class Main {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();
		
		Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class, null);
		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class, null);
		Controller<Employer, Integer> employercontroller = new Controller<Employer, Integer>(Employer.class, null);
		Controller<Project, Integer> projectcontroller = new Controller<Project, Integer>(Project.class, null);
		Controller<Comment, Integer> commentcontroller = new Controller<Comment, Integer>(Comment.class, null);
		
//		List<Project> projects = projectcontroller.getAll();
//		for(Project p : projects){
//			System.out.println(p.getTitle());
//			for(Comment c : p.getComments()){
//				System.out.println(c.getUser().getName() + ": " + c.getComment());				
//			}
//		}	
				
		User user = new User("","manuel.labadie@hotmail.com","cinifs", "");
		
		usercontroller = new Controller<User, Integer>(User.class, user);
		if(usercontroller.login()){
			System.out.println("successfull");
			System.out.println(usercontroller.getById(2).getName());
		}else{
			System.out.println("failed");
		}
		
//		List<User> users = usercontroller.getAll().stream()
//				.filter(p -> p.getEmail().equals(user.getEmail()))
//				.filter(p -> p.getPassword().equals(user.getPassword()))
//				.collect(Collectors.toList());
//		
//		System.out.println("users: " + users.size());
//		System.out.println(user.getEmail() + ":" + user.getPassword());
				
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			String url = ConfigFactory.load().getString("webservice.url") + "/login";
			WebTarget target = client.target(url);
			
			System.out.println(url);
			String json = mapper.writeValueAsString(user);
			System.out.println(json);
			
			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json));
			System.out.println("Code: " + response.getStatus());
			
			user = mapper.readValue(response.readEntity(String.class), User.class);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

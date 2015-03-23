package ch.issueman.common.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Project;
import ch.issueman.common.Comment;

@Path("/")
public class Route {
	
	Controller<Person, Integer> personc = new Controller<Person, Integer>(Person.class);
	Controller<User, Integer> userc = new Controller<User, Integer>(User.class);
	Controller<Employer, Integer> employerc = new Controller<Employer, Integer>(Employer.class);
	Controller<Project, Integer> projectc = new Controller<Project, Integer>(Project.class);
	Controller<Comment, Integer> commentc = new Controller<Comment, Integer>(Comment.class);
		
	@GET
	@Path("/person/{id}")
	@Produces("application/json")
	public Person getPersonById(@PathParam("id") int id) {
		return personc.getById(id);
	} 
	@GET
	@Path("/person")
	@Produces("application/json")
	public List<Person> getPerson() {
		return personc.getAll();
	}	
	@DELETE
	@Path("/person")
	@Consumes("application/json")
	public Response deletePerson(Person person) {
		personc.delete(person);
		return Response.status(201).entity("Person deleted").build();
	}
	@PUT
	@Path("/person")
	@Consumes("application/json")
	public Response updatePerson(Person person) {
		personc.update(person);
		return Response.status(201).entity("Person updated").build();
	}
	@POST
	@Path("/person")
	@Consumes("application/json")
	public Response persistPerson(Person person) {
		personc.persist(person);
		return Response.status(201).entity("Person added").build();
	}
	
	@GET
	@Path("/project/{id}")
	@Produces("application/json")
	public Project getProjectById(@PathParam("id") int id) {
		return projectc.getById(id);
	} 
	@GET
	@Path("/project")
	@Produces("application/json")
	public List<Project> getProject() {
		return projectc.getAll();
	}	
	@DELETE
	@Path("/project")
	@Consumes("application/json")
	public Response deleteProject(Project project) {
		projectc.delete(project);
		return Response.status(201).entity("Project deleted").build();
	}
	@PUT
	@Path("/project")
	@Consumes("application/json")
	public Response updateProject(Project project) {
		projectc.update(project);
		return Response.status(201).entity("Project updated").build();
	}
	@POST
	@Path("/project")
	@Consumes("application/json")
	public Response persistProject(Project project) {
		projectc.persist(project);
		return Response.status(201).entity("Project added").build();
	}
	
	@GET
	@Path("/employer/{id}")
	@Produces("application/json")
	public Employer getEmployerById(@PathParam("id") int id) {
		return employerc.getById(id);
	} 
	@GET
	@Path("/employer")
	@Produces("application/json")
	public List<Employer> getEmployer() {
		return employerc.getAll();
	}	
	@DELETE
	@Path("/employer")
	@Consumes("application/json")
	public Response deleteEmployer(Employer employer) {
		employerc.delete(employer);
		return Response.status(201).entity("Employer deleted").build();
	}
	@PUT
	@Path("/employer")
	@Consumes("application/json")
	public Response updateEmployer(Employer employer) {
		employerc.update(employer);
		return Response.status(201).entity("Employer updated").build();
	}
	@POST
	@Path("/employer")
	@Consumes("application/json")
	public Response persistEmployer(Employer employer) {
		employerc.persist(employer);
		return Response.status(201).entity("Employer added").build();
	}
	
	@GET
	@Path("/user/{id}")
	@Produces("application/json")
	public User getUserById(@PathParam("id") int id) {
		return userc.getById(id);
	} 
	@GET
	@Path("/user")
	@Produces("application/json")
	public List<User> getUser() {
		return userc.getAll();
	}	
	@DELETE
	@Path("/user")
	@Consumes("application/json")
	public Response deleteUser(User user) {
		userc.delete(user);
		return Response.status(201).entity("User deleted").build();
	}
	@PUT
	@Path("/user")
	@Consumes("application/json")
	public Response updateUser(User user) {
		userc.update(user);
		return Response.status(201).entity("User updated").build();
	}
	@POST
	@Path("/user")
	@Consumes("application/json")
	public Response persistUser(User user) {
		userc.persist(user);
		return Response.status(201).entity("User added").build();
	}
	
	@GET
	@Path("/comment/{id}")
	@Produces("application/json")
	public Comment getCommentById(@PathParam("id") int id) {
		return commentc.getById(id);
	} 
	@GET
	@Path("/comment")
	@Produces("application/json")
	public List<Comment> getComment() {
		return commentc.getAll();
	}	
	@DELETE
	@Path("/comment")
	@Consumes("application/json")
	public Response deleteComment(Comment comment) {
		commentc.delete(comment);
		return Response.status(201).entity("Comment deleted").build();
	}
	@PUT
	@Path("/comment")
	@Consumes("application/json")
	public Response updateComment(Comment comment) {
		commentc.update(comment);
		return Response.status(201).entity("Comment updated").build();
	}
	@POST
	@Path("/comment")
	@Consumes("application/json")
	public Response persistComment(Comment comment) {
		commentc.persist(comment);
		return Response.status(201).entity("Comment added").build();
	}
}
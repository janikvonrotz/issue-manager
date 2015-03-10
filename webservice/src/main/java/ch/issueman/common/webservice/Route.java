package ch.issueman.common.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.issueman.common.Employee;
import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Project;
 
@Path("/")
public class Route {

	private PersonController personc = new PersonController();
	private EmployeeController employeec = new EmployeeController();
	private ProjectController projectc = new ProjectController();
	private UserController userc = new UserController();
		
	@GET
	@Path("/person/get/{id}")
	@Produces("application/json")
	public Person getPersonById(@PathParam("id") int id) {
		return personc.getById(id);
	} 
	@GET
	@Path("/person/get")
	@Produces("application/json")
	public List<Person> getPerson() {
		return personc.getAll();
	}	
	@DELETE
	@Path("/person/delete")
	@Consumes("application/json")
	public Response deletePerson(Person person) {
		personc.delete(person);
		return Response.status(201).entity("Person deleted").build();
	}
	@POST
	@Path("/person/update")
	@Consumes("application/json")
	public Response updatePerson(Person person) {
		personc.update(person);
		return Response.status(201).entity("Person updated").build();
	}
	@POST
	@Path("/person/persist")
	@Consumes("application/json")
	public Response persistPerson(Person person) {
		personc.persist(person);
		return Response.status(201).entity("Person added").build();
	}
	
	@GET
	@Path("/project/get/{id}")
	@Produces("application/json")
	public Project getProjectById(@PathParam("id") int id) {
		return projectc.getById(id);
	} 
	@GET
	@Path("/project/get")
	@Produces("application/json")
	public List<Project> getProject() {
		return projectc.getAll();
	}	
	@DELETE
	@Path("/project/delete")
	@Consumes("application/json")
	public Response deleteProject(Project project) {
		projectc.delete(project);
		return Response.status(201).entity("Project deleted").build();
	}
	@POST
	@Path("/project/update")
	@Consumes("application/json")
	public Response updateProject(Project project) {
		projectc.update(project);
		return Response.status(201).entity("Project updated").build();
	}
	@POST
	@Path("/project/persist")
	@Consumes("application/json")
	public Response persistProject(Project project) {
		projectc.persist(project);
		return Response.status(201).entity("Project added").build();
	}
	
	@GET
	@Path("/employee/get/{id}")
	@Produces("application/json")
	public Employee getEmployeeById(@PathParam("id") int id) {
		return employeec.getById(id);
	} 
	@GET
	@Path("/employee/get")
	@Produces("application/json")
	public List<Employee> getEmployee() {
		return employeec.getAll();
	}	
	@DELETE
	@Path("/employee/delete")
	@Consumes("application/json")
	public Response deleteEmployee(Employee employee) {
		employeec.delete(employee);
		return Response.status(201).entity("Employee deleted").build();
	}
	@POST
	@Path("/employee/update")
	@Consumes("application/json")
	public Response updateEmployee(Employee employee) {
		employeec.update(employee);
		return Response.status(201).entity("Employee updated").build();
	}
	@POST
	@Path("/employee/persist")
	@Consumes("application/json")
	public Response persistEmployee(Employee employee) {
		employeec.persist(employee);
		return Response.status(201).entity("Employee added").build();
	}
	
	@GET
	@Path("/user/get/{id}")
	@Produces("application/json")
	public User getUserById(@PathParam("id") int id) {
		return userc.getById(id);
	} 
	@GET
	@Path("/user/get")
	@Produces("application/json")
	public List<User> getUser() {
		return userc.getAll();
	}	
	@DELETE
	@Path("/user/delete")
	@Consumes("application/json")
	public Response deleteUser(User user) {
		userc.delete(user);
		return Response.status(201).entity("User deleted").build();
	}
	@POST
	@Path("/user/update")
	@Consumes("application/json")
	public Response updateUser(User user) {
		userc.update(user);
		return Response.status(201).entity("User updated").build();
	}
	@POST
	@Path("/user/persist")
	@Consumes("application/json")
	public Response persistUser(User user) {
		userc.persist(user);
		return Response.status(201).entity("User added").build();
	}
}
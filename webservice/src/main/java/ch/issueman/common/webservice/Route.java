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

import ch.issueman.common.Person;
 
@Path("/")
public class Route {

	private PersonController personc = new PersonController();

	@GET
	@Path("/person/get/{id}")
	@Produces("application/json")
	public Person getPersonById(@PathParam("id") int id) {
		return personc.getById(id);
	} 
	@GET
	@Path("/person/get")
	@Produces("application/json")
	public List<Person> getPersonN() {
		return personc.getAll();
	}	
	@DELETE
	@Path("/person/delete")
	@Consumes("application/json")
	public Response deletePerson(Person person) {
		personc.update(person);
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
}
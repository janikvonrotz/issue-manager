package ch.issueman.common.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ch.issueman.common.Model;
import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Project;
import ch.issueman.common.Comment;
import ch.issueman.common.DAO;

@Path("/")
public class Route{
	
	private Map <String, DAO<?, Integer>> hm = new HashMap<String, DAO<?, Integer>>();

	public Route(){
		hm.put("person", new Controller<Person, Integer>(Person.class));
		hm.put("user", new Controller<User, Integer>(User.class));
		hm.put("employer", new Controller<Employer, Integer>(Employer.class));
		hm.put("project", new Controller<Project, Integer>(Project.class));
		hm.put("comment", new Controller<Comment, Integer>(Comment.class));	
	}
	
	@GET
	@Path("{entity}/{id}")
	@Produces("application/json")
	public Model getEntityById(@PathParam("entity") String entity, @PathParam("id") int id) {
		return (Model) hm.get(entity).getById(id);
	} 
	@SuppressWarnings("unchecked")
	@GET
	@Path("{entity}")
	@Produces("application/json")
	public List<Model> getAll(@PathParam("entity") String entity) {
		return (List<Model>) hm.get(entity).getAll();
	}	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DELETE
	@Path("{entity}/{id}")
	@Consumes("application/json")
	public Response deleteEntity(@PathParam("entity") String entity, @PathParam("id") int id) {
		((DAO) hm.get(entity)).delete(((DAO) hm.get(entity)).getById(id));
		return Response.status(410).entity("Entiy deleted").build();
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PUT
	@Path("{entity}")
	@Consumes("application/json")
	public Response updateEntiy(@PathParam("entity") String entity, Model m) {
		((DAO) hm.get(entity)).update(m);
		return Response.status(201).entity("Entiy updated").build();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("{entity}")
	@Consumes("application/json")
	public Response persistEntity(@PathParam("entity") String entity, Model m) {
		((DAO) hm.get(entity)).persist(m);
		return Response.status(200).entity("Entiy added").build();
	}
}
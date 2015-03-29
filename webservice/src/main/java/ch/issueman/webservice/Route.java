package ch.issueman.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
	@RolesAllowed("Administrator")
	@GET
	@Path("{entity}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Model getEntityById(@PathParam("entity") String entity, @PathParam("id") int id) {
		return (Model) hm.get(entity).getById(id);
	} 
	
	@PermitAll
	@SuppressWarnings("unchecked")
	@GET
	@Path("{entity}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Model> getAll(@PathParam("entity") String entity) {
		return (List<Model>) hm.get(entity).getAll();
	}	
	
	@RolesAllowed("Administrator")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DELETE
	@Path("{entity}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEntity(@PathParam("entity") String entity, @PathParam("id") int id) {
		((DAO) hm.get(entity)).delete(((DAO) hm.get(entity)).getById(id));
		return Response.status(Status.OK).entity("Entiy deleted").build();
		
	}
	
	@RolesAllowed("Administrator")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PUT
	@Path("{entity}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEntiy(@PathParam("entity") String entity, Model m) {
		((DAO) hm.get(entity)).update(m);
		return Response.status(Status.OK).entity("Entiy updated").build();
	}
	
	@RolesAllowed("Administrator")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("{entity}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistEntity(@PathParam("entity") String entity, Model m) {
		((DAO) hm.get(entity)).persist(m);
		return Response.status(Status.OK).entity("Entiy added").build();
	}
	
	@PermitAll
	@SuppressWarnings({ "unchecked" })
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		
		List<User> users = ((List<User>) hm.get("user").getAll()).stream()
				.filter(p -> p.getEmail().equals(user.getEmail()))
				.filter(p -> p.getPassword().equals(user.getPassword()))
				.collect(Collectors.toList());
		
		System.out.println("Size" + users.size());
		
		if((users.get(0) != null) && (users.size() == 1)){
			return Response.status(Status.OK).entity(users.get(0)).build();
		}else{
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
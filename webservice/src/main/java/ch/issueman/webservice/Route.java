package ch.issueman.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Project;
import ch.issueman.common.Comment;
import ch.issueman.common.DAO;

@Path("/")
public class Route{
	
	private Map <String, DAO<?, Integer>> hm = new HashMap<String, DAO<?, Integer>>();
	private Map <String, ResponseBuilder<?, Integer>> rbm = new HashMap<String, ResponseBuilder<?, Integer>>();
	
	public Route(){
		hm.put("person", new Controller<Person, Integer>(Person.class));
		hm.put("user", new Controller<User, Integer>(User.class));
		hm.put("employer", new Controller<Employer, Integer>(Employer.class));
		hm.put("project", new Controller<Project, Integer>(Project.class));
		hm.put("comment", new Controller<Comment, Integer>(Comment.class));	
		
		rbm.put("person", new ResponseBuilder<Person, Integer>(Person.class));
		rbm.put("user", new ResponseBuilder<User, Integer>(User.class));
		rbm.put("employer", new ResponseBuilder<Employer, Integer>(Employer.class));
		rbm.put("project", new ResponseBuilder<Project, Integer>(Project.class));
		rbm.put("comment", new ResponseBuilder<Comment, Integer>(Comment.class));
	}
	
	@GET
	@Path("{entity}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntityById(@PathParam("entity") String entity, @PathParam("id") int id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get(entity).setUser(user);
		return rbm.get(entity).getById(id);
	} 	

	@GET
	@Path("{entity}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("entity") String entity, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get(entity).setUser(user);
		return rbm.get(entity).getAll();
	}	
	
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
		
		if(users.size() == 1){
			return Response.status(Status.OK).entity(users.get(0)).build();
		}else{
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	/**
	 * Person
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("person")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(Person t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("person").setUser(user);
		return ((DAOResponseBuilder) rbm.get("person")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("person")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistPerson(Person t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("person").setUser(user);
		return ((DAOResponseBuilder) rbm.get("person")).persist(t);
	}
	@DELETE
	@Path("person/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("person").setUser(user);
		return rbm.get("person").deleteById(id);
	}	

	/**
	 * Employer
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("employer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployer(Employer t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("employer").setUser(user);
		return ((DAOResponseBuilder) rbm.get("employer")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("employer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistEmployer(Employer t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("employer").setUser(user);
		return ((DAOResponseBuilder) rbm.get("employer")).persist(t);
	}
	@DELETE
	@Path("employer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmployer(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("employer").setUser(user);
		return rbm.get("employer").deleteById(id);
	}
}
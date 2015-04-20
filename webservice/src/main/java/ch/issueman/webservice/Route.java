package ch.issueman.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
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

import org.reflections.Reflections;

import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Project;
import ch.issueman.common.Comment;

@Path("/")
public class Route{
	
	private Map <String, ResponseBuilder<?, Integer>> rbm = new HashMap<String, ResponseBuilder<?, Integer>>();
	
	public Route(){
		
		Reflections reflections = new Reflections("ch.issueman.common");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);

		for (Class<?> model : annotated) {
			
			rbm.put(model.getSimpleName(), new ResponseBuilder<, Integer>(model));
		}
		
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
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		return rbm.get("user").login(user);
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

	/**
	 * User
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return ((DAOResponseBuilder) rbm.get("user")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistUser(User t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return ((DAOResponseBuilder) rbm.get("user")).persist(t);
	}
	@DELETE
	@Path("user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return rbm.get("user").deleteById(id);
	}
	
	/**
	 * Project
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("project")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProject(Project t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return ((DAOResponseBuilder) rbm.get("project")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("project")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistProject(Project t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return ((DAOResponseBuilder) rbm.get("project")).persist(t);
	}
	@DELETE
	@Path("project/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProject(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return rbm.get("project").deleteById(id);
	}
	
	/**
	 * Comment
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateComment(Comment t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return ((DAOResponseBuilder) rbm.get("comment")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistComment(Comment t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return ((DAOResponseBuilder) rbm.get("comment")).persist(t);
	}
	@DELETE
	@Path("comment/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteComment(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		rbm.get("user").setUser(user);
		return rbm.get("comment").deleteById(id);
	}
}
package ch.issueman.webservice;

import java.util.HashMap;
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

import ch.issueman.common.Person;
import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Kommentar;

@Path("/")
public class Route{
	
	private Map <String, ResponseBuilder<?, Integer>> rbm = new HashMap<String, ResponseBuilder<?, Integer>>();
	
	public Route(){
		
		Reflections reflections = new Reflections("ch.issueman.common");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);
		
		/*
		for (Class<?> model : annotated) {
			rbm.put(model.getSimpleName().toLowerCase(), new ResponseBuilder<model, Integer>(model.));
		}
		*/
		
		rbm.put("person", new ResponseBuilder<Person, Integer>(Person.class));
	}
	
	@GET
	@Path("{entity}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntityById(@PathParam("entity") String entity, @PathParam("id") int id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get(entity).setLogin(login);
		return rbm.get(entity).getById(id);
	} 	

	@GET
	@Path("{entity}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("entity") String entity, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get(entity).setLogin(login);
		return rbm.get(entity).getAll();
	}	
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return rbm.get("login").login(login);
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
		Login login = (Login) session.getAttribute("login");
		rbm.get("person").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("person")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("person")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistPerson(Person t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("person").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("person")).persist(t);
	}
	@DELETE
	@Path("person/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("person").setLogin(login);
		return rbm.get("person").deleteById(id);
	}	

	/**
	 * Login
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLogin(Login t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("login")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistLogin(Login t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("login")).persist(t);
	}
	@DELETE
	@Path("login/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLogin(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return rbm.get("login").deleteById(id);
	}
	
	/**
	 * Projekt
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("projekt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProjekt(Projekt t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("projekt")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("projekt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistProjekt(Projekt t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("projekt")).persist(t);
	}
	@DELETE
	@Path("projekt/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProjekt(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return rbm.get("projekt").deleteById(id);
	}
	
	/**
	 * Kommentar
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("kommentar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateKommentar(Kommentar t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("kommentar")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("kommentar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistKommentar(Kommentar t, @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return ((DAOResponseBuilder) rbm.get("kommentar")).persist(t);
	}
	@DELETE
	@Path("kommentar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteKommentar(@PathParam("id") int id,  @Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		rbm.get("login").setLogin(login);
		return rbm.get("kommentar").deleteById(id);
	}
}
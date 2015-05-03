package ch.issueman.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Projektleitung;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Rolle;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * This class is loaded by the JAX-RS servlet and defines the restful http routes and methods.
 * 
 * @author Janik von Rotz, Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 */
@Path("/")
public class Route{
	
	private Map <String, DAOResponseBuilder<?, Integer>> rbm = new HashMap<String, DAOResponseBuilder<?, Integer>>();
	
	public Route(){
		rbm.put("adresse", new ResponseBuilder<Adresse, Integer>(Adresse.class));
		rbm.put("arbeitstyp", new ResponseBuilder<Arbeitstyp, Integer>(Arbeitstyp.class));
		rbm.put("bauherr", new ResponseBuilder<Bauherr, Integer>(Bauherr.class));
		rbm.put("bauleiter", new ResponseBuilder<Bauleiter, Integer>(Bauleiter.class));
		rbm.put("kommentar", new ResponseBuilder<Kommentar, Integer>(Kommentar.class));
		rbm.put("kontakt", new ResponseBuilder<Kontakt, Integer>(Kontakt.class));
		rbm.put("login", new ResponseBuilder<Login, Integer>(Login.class));
		rbm.put("mangel", new ResponseBuilder<Mangel, Integer>(Mangel.class));
		rbm.put("mangelstatus", new ResponseBuilder<Mangelstatus, Integer>(Mangelstatus.class));
		rbm.put("ort", new ResponseBuilder<Ort, Integer>(Ort.class));
		rbm.put("person", new ResponseBuilder<Person, Integer>(Person.class));
		rbm.put("projekt", new ResponseBuilder<Projekt, Integer>(Projekt.class));
		rbm.put("projektleitung", new ResponseBuilder<Projektleitung, Integer>(Projektleitung.class));
		rbm.put("projekttyp", new ResponseBuilder<Projekttyp, Integer>(Projekttyp.class));
		rbm.put("rolle", new ResponseBuilder<Rolle, Integer>(Rolle.class));
		rbm.put("sachbearbeiter", new ResponseBuilder<Sachbearbeiter, Integer>(Sachbearbeiter.class));
		rbm.put("subunternehmen", new ResponseBuilder<Subunternehmen, Integer>(Subunternehmen.class));
		rbm.put("unternehmen", new ResponseBuilder<Unternehmen, Integer>(Unternehmen.class));
	}
	
	/**
	 * Get by id route for all entities.
	 * 
	 * @param entity the path parameter as name of the entity.
	 * @param id the id of the entry to receive.
	 * @param request the request header (passed by the servlet).
	 * @return response containing the entity entry.
	 */
	@GET
	@Path("{entity}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntityById(@PathParam("entity") String entity, @PathParam("id") int id, @Context HttpServletRequest request) {
		rbm.get(entity).setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get(entity).getById(id);
	} 	

	@GET
	@Path("{entity}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("entity") String entity, @Context HttpServletRequest request) {
		rbm.get(entity).setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get(entity).getAll();
	}
	
	@GET
	@Path("signin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request) {
		return rbm.get("login").signin((Login) request.getSession(true).getAttribute("login"));
	}
	
	/**
	 * Get for abstract implementations
	 */
	@GET
	@Path("bauleiter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBauleiter(@Context HttpServletRequest request) {
		rbm.get("bauleiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("bauleiter").getAllBauleiter();
	}
	@GET
	@Path("sachbearbeiter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSachbearbeiter(@Context HttpServletRequest request) {
		rbm.get("sachbearbeiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("sachbearbeiter").getAllSachbearbeiter();
	}
	@GET
	@Path("bauherr")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBauherr(@Context HttpServletRequest request) {
		rbm.get("bauherr").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("bauherr").getAllBauherr();
	}
	@GET
	@Path("kontakt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllKontakt(@Context HttpServletRequest request) {
		rbm.get("kontakt").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("kontakt").getAllKontakt();
	}
	
	/**
	 * Adresse
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("adresse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAdresse(Adresse t, @Context HttpServletRequest request) {
		rbm.get("adresse").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("adresse")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("adresse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistAdresse(Adresse t, @Context HttpServletRequest request) {
		rbm.get("adresse").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("adresse")).persist(t);
	}
	@DELETE
	@Path("adresse/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteAdresse(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("adresse").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("adresse").deleteById(id);
	}	
	
	/**
	 * Arbeitstyp
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("arbeitstyp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateArbeitstyp(Arbeitstyp t, @Context HttpServletRequest request) {
		rbm.get("arbeitstyp").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("arbeitstyp")).update(t);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("arbeitstyp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistArbeitstyp(Arbeitstyp t, @Context HttpServletRequest request) {
		rbm.get("arbeitstyp").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("arbeitstyp")).persist(t);
	}
	@DELETE
	@Path("arbeitstyp/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteArbeitstyp(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("arbeitstyp").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("arbeitstyp").deleteById(id);
	}	
	
	/**deleteById
	 * Bauherr
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("bauherr")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBauherr(Bauherr t, @Context HttpServletRequest request) {
		rbm.get("bauherr").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("bauherr")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("bauherr")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistBauherr(Bauherr t, @Context HttpServletRequest request) {
		rbm.get("bauherr").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("bauherr")).persist(t);
	}
	@DELETE
	@Path("bauherr/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBauherr(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("bauherr").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("bauherr").deleteById(id);
	}	
	
	/**
	 * Bauleiter
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("bauleiter")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBauleiter(Bauleiter t, @Context HttpServletRequest request) {
		rbm.get("bauleiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("bauleiter")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("bauleiter")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistBauleiter(Bauleiter t, @Context HttpServletRequest request) {
		rbm.get("bauleiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("bauleiter")).persist(t);
	}
	@DELETE
	@Path("bauleiter/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBauleiter(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("bauleiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("bauleiter").deleteById(id);
	}	
	
	/**
	 * Kommentar
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("kommentar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateKommentar(Kommentar t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("kommentar")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("kommentar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistKommentar(Kommentar t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("kommentar")).persist(t);
	}
	@DELETE
	@Path("kommentar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteKommentar(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("kommentar").deleteById(id);
	}
	
	/**
	 * Kontakt
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("kontakt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateKontakt(Kontakt t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("kontakt")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("kontakt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistKontakt(Kontakt t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("kontakt")).persist(t);
	}
	@DELETE
	@Path("kontakt/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteKontakt(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("kontakt").deleteById(id);
	}
	
	/**
	 * Login
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLogin(Login t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("login")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistLogin(Login t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("login")).persist(t);
	}
	@DELETE
	@Path("login/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLogin(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("login").deleteById(id);
	}
	
	/**
	 * Mangel
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("mangel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMangel(Mangel t, @Context HttpServletRequest request) {
		rbm.get("mangel").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("mangel")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("mangel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistMangel(Mangel t, @Context HttpServletRequest request) {
		rbm.get("mangel").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("mangel")).persist(t);
	}
	@DELETE
	@Path("mangel/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteMangel(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("mangel").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("mangel").deleteById(id);
	}
	
	/**
	 * Mangelstatus
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("mangelstatus")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMangelstatus(Mangelstatus t, @Context HttpServletRequest request) {
		rbm.get("mangelstatus").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("mangelstatus")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("mangelstatus")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistMangelstatus(Mangelstatus t, @Context HttpServletRequest request) {
		rbm.get("mangelstatus").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("mangelstatus")).persist(t);
	}
	@DELETE
	@Path("mangelstatus/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteMangelstatus(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("mangelstatus").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("mangelstatus").deleteById(id);
	}	
	
	/**
	 * Ort
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("ort")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrt(Ort t, @Context HttpServletRequest request) {
		rbm.get("ort").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("ort")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("ort")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistOrt(Ort t, @Context HttpServletRequest request) {
		rbm.get("ort").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("ort")).persist(t);
	}
	@DELETE
	@Path("ort/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteOrt(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("ort").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("ort").deleteById(id);
	}	
	
	/**
	 * Person
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("person")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(Person t, @Context HttpServletRequest request) {
		rbm.get("person").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("person")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("person")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistPerson(Person t, @Context HttpServletRequest request) {
		rbm.get("person").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("person")).persist(t);
	}
	@DELETE
	@Path("person/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("person").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("person").deleteById(id);
	}	

	/**
	 * Projekt
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("projekt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProjekt(Projekt t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("projekt")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("projekt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistProjekt(Projekt t, @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("projekt")).persist(t);
	}
	@DELETE
	@Path("projekt/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProjekt(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("login").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("projekt").deleteById(id);
	}
	
	/**
	 * Projektleitung
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("projektleitung")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProjektleitung(Projektleitung t, @Context HttpServletRequest request) {
		rbm.get("projektleitung").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("projektleitung")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("projektleitung")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistProjektleitung(Projektleitung t, @Context HttpServletRequest request) {
		rbm.get("projektleitung").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("projektleitung")).persist(t);
	}
	@DELETE
	@Path("projektleitung/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProjektleitung(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("projektleitung").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("projektleitung").deleteById(id);
	}	
	
	/**
	 * Projekttyp
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("projekttyp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProjekttyp(Projekttyp t, @Context HttpServletRequest request) {
		rbm.get("projekttyp").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("projekttyp")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("projekttyp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistProjekttyp(Projekttyp t, @Context HttpServletRequest request) {
		rbm.get("projekttyp").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("projekttyp")).persist(t);
	}
	@DELETE
	@Path("projekttyp/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProjekttyp(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("projekttyp").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("projekttyp").deleteById(id);
	}
	
	/**
	 * Rolle
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("rolle")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRolle(Rolle t, @Context HttpServletRequest request) {
		rbm.get("rolle").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("rolle")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("rolle")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistRolle(Rolle t, @Context HttpServletRequest request) {
		rbm.get("rolle").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("rolle")).persist(t);
	}
	@DELETE
	@Path("rolle/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRolle(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("rolle").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("rolle").deleteById(id);
	}	
	
	/**
	 * Sachbearbeiter
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("sachbearbeiter")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSachbearbeiter(Sachbearbeiter t, @Context HttpServletRequest request) {
		rbm.get("sachbearbeiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("sachbearbeiter")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("sachbearbeiter")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistSachbearbeiter(Sachbearbeiter t, @Context HttpServletRequest request) {
		rbm.get("sachbearbeiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("sachbearbeiter")).persist(t);
	}
	@DELETE
	@Path("sachbearbeiter/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSachbearbeiter(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("sachbearbeiter").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("sachbearbeiter").deleteById(id);
	}	
	
	/**
	 * Subunternehmen
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("subunternehmen")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSubunternehmen(Subunternehmen t, @Context HttpServletRequest request) {
		rbm.get("subunternehmen").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("subunternehmen")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("subunternehmen")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistSubunternehmen(Subunternehmen t, @Context HttpServletRequest request) {
		rbm.get("subunternehmen").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("subunternehmen")).persist(t);
	}
	@DELETE
	@Path("subunternehmen/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSubunternehmen(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("subunternehmen").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("subunternehmen").deleteById(id);
	}
	
	/**
	 * Unternehmen
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PUT
	@Path("unternehmen")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUnternehmen(Unternehmen t, @Context HttpServletRequest request) {
		rbm.get("unternehmen").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("unternehmen")).update(t);
	}		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("unternehmen")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistUnternehmen(Unternehmen t, @Context HttpServletRequest request) {
		rbm.get("unternehmen").setLogin((Login) request.getSession(true).getAttribute("login"));
		return ((DAOResponseBuilder) rbm.get("unternehmen")).persist(t);
	}
	@DELETE
	@Path("unternehmen/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUnternehmen(@PathParam("id") int id,  @Context HttpServletRequest request) {
		rbm.get("unternehmen").setLogin((Login) request.getSession(true).getAttribute("login"));
		return rbm.get("unternehmen").deleteById(id);
	}
}
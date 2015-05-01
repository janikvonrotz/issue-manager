package ch.issueman.client;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;
import ch.issueman.common.Model;

/**
 * Basic client controller class.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 *
 * @param <T> the type of entity.
 * @param <Id> the type of the identifier of the entity.
 */
public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private static ResteasyClient client = Context.getClient();
	private String url;
	private ObjectMapper mapper = new ObjectMapper();
	private final Class<T> clazz;
	
	public Controller(Class<T> clazz) {
		this.clazz = clazz;
		url = ConfigFactory.load().getString("webservice.url") + "/" + clazz.getSimpleName().toLowerCase();
	}
		
	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getById(java.io.Serializable)
	 */
	public T getById(Id id) throws Exception {
		WebTarget target = client.target(url + "/" + id);
		T t = null;			
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if(response.getStatus() == Status.OK.getStatusCode()){
			t = mapper.readValue(response.readEntity(String.class), clazz);
		}else{
			throw mapper.readValue(response.readEntity(String.class), Exception.class);
		}
		response.close();
		return t;
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAll()
	 */
	public List<T> getAll() throws Exception {
		
		WebTarget target = client.target(url);
		TypeFactory t = TypeFactory.defaultInstance();
		List<T> l = null;
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if(response.getStatus() == Status.OK.getStatusCode()){
			l = mapper.readValue(response.readEntity(String.class), t.constructCollectionType(List.class,clazz));
		}else{
			throw mapper.readValue(response.readEntity(String.class), Exception.class);
		}
		response.close();
		return l;
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#persist(java.lang.Object)
	 */
	@Override
	public void persist(T t) throws Exception {
		WebTarget target = client.target(url);		
		Response response =	target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(t)));
		if(response.getStatus() == Status.OK.getStatusCode()){
		}else{
			throw mapper.readValue(response.readEntity(String.class), Exception.class);
		}
		response.close();	
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(T t) throws Exception {
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(mapper.writeValueAsString(t)));
		if(response.getStatus() == Status.OK.getStatusCode()){
		}else{
			throw mapper.readValue(response.readEntity(String.class), Exception.class);
		}
		response.close();
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) throws Exception {
		WebTarget target = client.target(url + "/" + ((Model)t).getId());
		Response response = target.request(MediaType.APPLICATION_JSON).delete();
		if(response.getStatus() == Status.OK.getStatusCode()){
		}else{
			throw mapper.readValue(response.readEntity(String.class), Exception.class);
		}
		response.close();
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
	}

	/* (non-Javadoc)
	 * @see ch.issueman.common.DAO#getAllByProperty(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues) throws Exception {
		return null;
	}
}

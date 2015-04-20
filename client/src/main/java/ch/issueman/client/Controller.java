package ch.issueman.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;
import ch.issueman.common.Model;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private static ResteasyClient client = App.getClient();
	private String url;
	private ObjectMapper mapper = new ObjectMapper();
	private final Class<T> clazz;
	
	public Controller(Class<T> clazz) {
		this.clazz = clazz;
		url = ConfigFactory.load().getString("webservice.url") + "/" + clazz.getSimpleName().toLowerCase();
	}
		
	public T getById(Id id) {
		WebTarget target = client.target(url + "/" + id);
		try {			
			Response response = target.request(MediaType.APPLICATION_JSON).get();
			T t = mapper.readValue(response.readEntity(String.class), clazz);
			response.close();
			return t;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> getAll() {
		
		WebTarget target = client.target(url);
		TypeFactory t = TypeFactory.defaultInstance();
		
		try {
			Response response = target.request(MediaType.APPLICATION_JSON).get();
			List<T> l = mapper.readValue(response.readEntity(String.class), t.constructCollectionType(List.class,clazz));
			response.close();
			return l;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void persist(T t) {
		WebTarget target = client.target(url);
		try {			
			Response response =	target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(t)));
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(T t) {
		try {
			WebTarget target = client.target(url);
			Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(mapper.writeValueAsString(t)));
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(T t) {
		WebTarget target = client.target(url + "/" + ((Model)t).getId());
		Response response = target.request(MediaType.APPLICATION_JSON).delete();
		response.close();
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public List<T> getAllByProperty(String propertyname, Object[] propertyvalues)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

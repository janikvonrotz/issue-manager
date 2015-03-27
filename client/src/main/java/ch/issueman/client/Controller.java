package ch.issueman.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;
import ch.issueman.common.Model;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private Client client = ClientBuilder.newClient();
	private String url;
	private ObjectMapper mapper = new ObjectMapper();
	private final Class<T> clazz;

	public Controller(Class<T> clazz) {
		this.clazz = clazz;
		url = ConfigFactory.load().getString(
				"webservice.url") + "/" + clazz.getSimpleName().toLowerCase();
	}
	
	public T getById(Id id) {
		try {
			WebTarget target = client.target(url + "/" + id);
			return mapper
					.readValue(
							target.request("application/json")
									.get(String.class), clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> getAll() {
		try {
			WebTarget target = client.target(url);
			TypeFactory t = TypeFactory.defaultInstance();
			return mapper.readValue(target.request("application/json").get(String.class), t.constructCollectionType(List.class,clazz));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void persist(T t) {
		try {
			WebTarget target = client.target(url);
			target.request("application/json").post(Entity.json(mapper.writeValueAsString(t)));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(T t) {
		try {
			WebTarget target = client.target(url);
			target.request("application/json").put(Entity.json(mapper.writeValueAsString(t)));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(T t) {
		WebTarget target = client.target(url+"/"+ ((Model)t).getId());
		target.request("application/json").delete();
	}

	@Override
	public void deleteAll() {
	}
}

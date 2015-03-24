package ch.issueman.common.client;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.codehaus.jackson.map.ObjectMapper;
import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;

public class ClientController<T, Id extends Serializable> implements DAO<T, Id> {
		
	private Client client = ClientBuilder.newClient();
	private WebTarget target = client.target(ConfigFactory.load().getString("webservice.url"));
	private ObjectMapper mapper = new ObjectMapper();
	private final Class<T> clazz;
	
	
	public ClientController(Class<T> clazz){
		this.clazz = clazz;
		this.target = client.target(ConfigFactory.load().getString("webservice.url" + "/" + clazz.getSimpleName().toLowerCase()));
	}

	public void persist(T t) {
	}


	public void persistList(List<T> l) {
	}


	public T getById(Id id) {
		return null;
	}

	public List<T> getAll() {
		return null;
	}


	public List<T> getByQuery(String JPQLquery) {
		return null;
	}


	public void update(T t) {
	}


	public void updateList(List<T> l) {
	
	}


	public void delete(T t) {
	}


	public void deleteAll() {
	}


	public void deleteList(List<T> l) {
	}
}

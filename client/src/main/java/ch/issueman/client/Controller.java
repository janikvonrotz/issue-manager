package ch.issueman.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;
import ch.issueman.common.Model;
import ch.issueman.common.User;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private static ResteasyClient client = new ResteasyClientBuilder().build();
	private String url;
	private ObjectMapper mapper = new ObjectMapper();
	private final Class<T> clazz;
	private User user;
	
	public Controller(Class<T> clazz, User user) {
		this.clazz = clazz;
		url = ConfigFactory.load().getString("webservice.url") + "/" + clazz.getSimpleName().toLowerCase();
		this.user = user;
	}
	
	public boolean login(){
		try {
			WebTarget target = client.target(ConfigFactory.load().getString("webservice.url") + "/login");
			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(user)));
			
			if(response.getStatus() == Status.OK.getStatusCode()){
				user = mapper.readValue(response.readEntity(String.class), User.class);
				client = new ResteasyClientBuilder().register(new BasicAuthentication(user.getEmail(), user.getPassword())).build();
				return true;
			}else{
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public T getById(Id id) {
		try {
			WebTarget target = client.target(url + "/" + id);
			return mapper.readValue(target.request(MediaType.APPLICATION_JSON).get(String.class), clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> getAll() {
		try {
			WebTarget target = client.target(url);
			TypeFactory t = TypeFactory.defaultInstance();
			return mapper.readValue(target.request(MediaType.APPLICATION_JSON).get(String.class), t.constructCollectionType(List.class,clazz));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void persist(T t) {
		try {
			WebTarget target = client.target(url);
			target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(t)));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(T t) {
		try {
			WebTarget target = client.target(url);
			target.request(MediaType.APPLICATION_JSON).put(Entity.json(mapper.writeValueAsString(t)));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(T t) {
		WebTarget target = client.target(url+"/"+ ((Model)t).getId());
		target.request(MediaType.APPLICATION_JSON).delete();
	}

	@Override
	public void deleteAll() {
	}
}

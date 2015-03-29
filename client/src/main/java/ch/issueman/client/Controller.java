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
		Boolean status = false;
		try {
			WebTarget target = client.target(ConfigFactory.load().getString("webservice.url") + "/login");
			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(user)));
			if(response.getStatus() == Status.OK.getStatusCode()){
				user = mapper.readValue(response.readEntity(String.class), User.class);
				client = new ResteasyClientBuilder().register(new BasicAuthentication(user.getEmail(), user.getPassword())).build();
				status = true;
			}			
			response.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
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
}

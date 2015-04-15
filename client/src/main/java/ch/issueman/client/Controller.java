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
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;
import ch.issueman.common.Model;
import ch.issueman.common.User;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {

	private ResteasyClient client = new ResteasyClientBuilder().build();
	private String url;
	private ObjectMapper mapper = new ObjectMapper();
	private final Class<T> clazz;
	private User user;
	
	public Controller(Class<T> clazz, User user) {
		this.clazz = clazz;
		url = ConfigFactory.load().getString("webservice.url") + "/" + clazz.getSimpleName().toLowerCase();
		this.user = user;
	}
	
	public boolean login() throws Exception{
		Boolean status = false;
		WebTarget target = client.target(ConfigFactory.load().getString("webservice.url") + "/login");		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(user)));
		if(response.getStatus() == Status.OK.getStatusCode()){
			user = mapper.readValue(response.readEntity(String.class), User.class);
			client = new ResteasyClientBuilder().register(new BasicAuthentication(user.getEmail(), user.getPassword())).build();
			status = true;
		}else{
			
		}		
		response.close();			
		return status;
	}
	
	public T getById(Id id) throws Exception {
		WebTarget target = client.target(url + "/" + id);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		T t = mapper.readValue(response.readEntity(String.class), clazz);
		if(response.getStatus() != Status.OK.getStatusCode()){
			
		}
		response.close();
		return t;
	}

	public List<T> getAll() throws Exception {
		WebTarget target = client.target(url);
		TypeFactory t = TypeFactory.defaultInstance();
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		List<T> l = mapper.readValue(response.readEntity(String.class), t.constructCollectionType(List.class,clazz));
		if(response.getStatus() != Status.OK.getStatusCode()){
			
		}
		response.close();
		return l;
	}

	@Override
	public void persist(T t) throws Exception{
		WebTarget target = client.target(url);
		Response response =	target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(t)));
		if(response.getStatus() != Status.OK.getStatusCode()){
			
		}
		response.close();	
	}

	@Override
	public void update(T t) throws Exception {
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(mapper.writeValueAsString(t)));
		if(response.getStatus() != Status.OK.getStatusCode()){
			
		}
		response.close();
	}

	@Override
	public void delete(T t) throws Exception{
		WebTarget target = client.target(url + "/" + ((Model)t).getId());
		Response response = target.request(MediaType.APPLICATION_JSON).delete();
		if(response.getStatus() != Status.OK.getStatusCode()){
			
		}
		response.close();
	}

	@Override
	public void deleteAll() throws Exception {
	}
}

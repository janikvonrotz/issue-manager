package ch.issueman.common.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.typesafe.config.ConfigFactory;

import ch.issueman.common.DAO;

public class ClientController<T, Id extends Serializable> implements DAO<T, Id> {

	private String url = ConfigFactory.load().getString("webservice.url");
	private ClientRequest request = new ClientRequest(ConfigFactory.load().getString("webservice.url"));
	private ClientResponse<String> response;
	private ObjectMapper mapper = new ObjectMapper();
	private Class<T> clazz;

	public ClientController(Class<T> clazz) {
        this.clazz = clazz;
        url = url + "/" + clazz.getSimpleName().toLowerCase();
	}

	@Override
	public void persist(T t) {
		try {
			request = new ClientRequest(url + "/persist");
			request.accept("application/json");
			request.body("application/json", mapper.writeValueAsString(t));
			response = request.post(String.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	@Override
	public T getById(Id id) {			
		try {
			request = new ClientRequest(url + "/get/" + id);
			request.accept("application/json");
			response = request.get(String.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			return mapper.readValue(response.getEntity().toString(), this.clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<T> getAll() {
		try {
			request = new ClientRequest(url + "/get");
			request.accept("application/json");
			response = request.get(String.class);
			TypeFactory t = TypeFactory.defaultInstance();
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			return mapper.readValue(response.getEntity().toString(), t.constructCollectionType(ArrayList.class,clazz));//new TypeReference<List<T>>(){});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void update(T t) {
		try {
			request = new ClientRequest(url + "/update");
			request.accept("application/json");
			request.body("application/json", mapper.writeValueAsString(t));
			response = request.post(String.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(T t) {
		try {
			request = new ClientRequest(url + "/delete");
			request.accept("application/json");
			request.body("application/json", mapper.writeValueAsString(t));
			response = request.delete(String.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
	}
}

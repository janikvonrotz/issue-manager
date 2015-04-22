package ch.issueman.client;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.Getter;
import lombok.Setter;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import ch.issueman.common.Login;

import com.typesafe.config.ConfigFactory;

public class Context {

	@Getter
	@Setter
	private static Login login = null;
	@Getter
	private static ResteasyClient client = new ResteasyClientBuilder().build();
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static boolean login(){
		Boolean status = false;
		try {
			
			WebTarget target = client.target(ConfigFactory.load().getString("webservice.url") + "/login");
			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(mapper.writeValueAsString(login)));
			
			if(response.getStatus() == Status.OK.getStatusCode()){
				login = mapper.readValue(response.readEntity(String.class), Login.class);
				client = new ResteasyClientBuilder().register(new BasicAuthentication(login.getUsername(), login.getPasswort())).build();
				status = true;
			}			
			response.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean ifUserHasRole(String rolle) {
		
		if(login.getRolle().getBezeichnung().equals(rolle)){
			return true;
		}
		return false;
	}

	public static void logout() {
		login = null;
		client = new ResteasyClientBuilder().build();
	}
}

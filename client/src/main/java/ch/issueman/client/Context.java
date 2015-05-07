package ch.issueman.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

/**
 * Client user context.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
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
			client.register(new BasicAuthentication(login.getUsername(), login.getPasswort()));
			WebTarget target = client.target(ConfigFactory.load().getString("webservice.url") + "/signin");
			Response response = target.request(MediaType.APPLICATION_JSON).get();
			
			if(response.getStatus() == Status.OK.getStatusCode()){
				login = mapper.readValue(response.readEntity(String.class), Login.class);
				status = true;
			}
			response.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean ifUserHasRole(String[] rollen) {
		List<String> listrollen = Arrays.asList(rollen);
		if(listrollen.contains(login.getRolle().getBezeichnung())){
			return true;
		}
		return false;
	}

	public static void logout() {
		login = null;
		client = new ResteasyClientBuilder().build();
	}

	public static void setNewPassword(String newpassword) throws Exception {
		String oldpassword = login.getPasswort();
		login.setPasswort(newpassword);
		try{
			(new Controller<Login, Integer>(Login.class)).persist(login);
		} catch (Exception e){
			login.setPasswort(oldpassword);
			throw e;
		}
	}
}

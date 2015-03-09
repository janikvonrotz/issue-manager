package ch.issueman.common.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import ch.issueman.common.Person;

import com.google.gson.Gson;


public class HomeController {

	public static void main(String[] args) {
		try {
			
			// create client request			
			ClientRequest request = new ClientRequest("http://localhost:8080/webservice/person/get/1");
			request.accept("application/json");
			
			// get person by id
			ClientResponse<String> response = request.get(String.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			Gson gson = new Gson();
			Person p = gson.fromJson(response.getEntity().toString(), Person.class);
			System.out.println(p.getName());
			
			// create person
			p = new Person();
			p.setName("Poo");
			request = new ClientRequest("http://localhost:8080/webservice/person/persist");
			request.accept("application/json");
			request.body("application/json", gson.toJson(p));
			System.out.println(gson.toJson(p));
			response = request.post(String.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

package ch.issueman.common.client;

import ch.issueman.common.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import javax.ws.rs.client.ClientBuilder;

import org.junit.Test;

import javax.ws.rs.core.Response;

import com.typesafe.config.ConfigFactory;

public class ClientControllerTest {

	@Test
	public void test() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(ConfigFactory.load().getString(
				"webservice.url")
				+ "/person");
		ObjectMapper mapper = new ObjectMapper();

		Response response = target.request("application/json").get();

		TypeFactory t = TypeFactory.defaultInstance();
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		List<Person> l;
		try {
			l = mapper.readValue(response.getEntity().toString(),
					t.constructCollectionType(ArrayList.class, Person.class));
			for (Person p : l) {
				System.out.println(p.getName());
			}
		} catch (IOException e) {
		}

	}

}

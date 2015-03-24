package ch.issueman.common.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import ch.issueman.common.Person;

import com.typesafe.config.ConfigFactory;

public class Main {

	public static void main(String[] args) {
		try {

			String url = ConfigFactory.load().getString("webservice.url")
					+ "/person/1";
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(url);
			ObjectMapper mapper = new ObjectMapper();

			String response = target.request("application/json").get(
					String.class);

			System.out.println(url);
			System.out.println(response);

			Person p = mapper.readValue(response, Person.class);
			System.out.println(p.getName());
			
			url = ConfigFactory.load().getString("webservice.url")
					+ "/person";
			TypeFactory t = TypeFactory.defaultInstance();
			target = client.target(url);
			List<Person> l = mapper.readValue(response, t.constructCollectionType(ArrayList.class, Person.class));
			System.out.println(url);
			System.out.println(l.size() + "s");

		} catch (IOException e) {
		}
	}

}

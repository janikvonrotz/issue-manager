package ch.issueman.common.client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.issueman.common.Employer;
import ch.issueman.common.Person;

import com.typesafe.config.ConfigFactory;

public class Main {

	public static void main(String[] args) {
		try {
			String url = ConfigFactory.load().getString("webservice.url")
					+ "/employer/156";
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(url);
			ObjectMapper mapper = new ObjectMapper();

			String response = target.request("application/json").get(
					String.class);

			System.out.println(url);
			System.out.println(response);

			Person p;

			p = mapper.readValue(response, Employer.class);

			System.out.println(p.getName());

			url = ConfigFactory.load().getString("webservice.url") + "/employer";
			System.out.println(url);
			target = client.target(url);
			response = target.request("application/json").get(String.class);
			System.out.println(response);

			List<Employer> l = mapper.readValue(response,
					new TypeReference<List<Employer>>() {
					});

			System.out.println(l.size() + "s");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package ch.issueman.common.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import ch.issueman.common.Employee;
import ch.issueman.common.Person;
import ch.issueman.common.User;
import ch.issueman.common.Project;

public class HomeController {

	public static void main(String[] args) {
		try {
			
			// create client request	
			// get person by id
			ClientRequest request = new ClientRequest("http://localhost:8080/webservice/person/get/1");
			request.accept("application/json");
			ObjectMapper mapper = new ObjectMapper();
			ClientResponse<String> response = request.get(String.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}			
			Person p = mapper.readValue(response.getEntity().toString(), Person.class);			 
			System.out.println(p.getName());
			
			// update person
			p.setName("Neuer Name");
			request = new ClientRequest("http://localhost:8080/webservice/person/update");
			request.accept("application/json");
			request.body("application/json", mapper.writeValueAsString(p));
			System.out.println(mapper.writeValueAsString(p));
			response = request.post(String.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			// add employee			
			Employee e = new Employee("Redhat");
			p = new Person("");
			p.setEmployee(e);			
			request = new ClientRequest("http://localhost:8080/webservice/person/persist");
			request.accept("application/json");
			request.body("application/json", mapper.writeValueAsString(p));
			System.out.println(mapper.writeValueAsString(p));
			response = request.post(String.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			// get employee
			request = new ClientRequest("http://localhost:8080/webservice/employee/get/2");
			request.accept("application/json");
			response = request.get(String.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}			
			e = mapper.readValue(response.getEntity().toString(), Employee.class);	
			
			// add project
			Project j = new Project("Beta");
			j.setEmployee(e);
			request = new ClientRequest("http://localhost:8080/webservice/project/persist");
			request.accept("application/json");
			request.body("application/json", mapper.writeValueAsString(j));
			System.out.println(mapper.writeValueAsString(j));
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

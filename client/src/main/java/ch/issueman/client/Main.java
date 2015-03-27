package ch.issueman.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.issueman.common.Employer;
import ch.issueman.common.Person;
import ch.issueman.common.User;

public class Main {

	public static void main(String[] args) {
		
		Person a = new Person("Janik");
		Employer b = new Employer("Sandro", "HSLU");
		User c = new User("Stefan","yodo","hello123","Admin");
		
		List<Person> l = new ArrayList<Person>();
		
		l.add(a);
		l.add(b);
		l.add(c);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(l);
			System.out.println(json);
			l = null;
			l = mapper.readValue(json, new TypeReference<List<Person>>() {});
			System.out.print(l.get(0));
			System.out.println("Size:" + l.size());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		Controller<Employer, Integer> controller = new Controller<Employer, Integer>(Employer.class);
		Employer e = controller.getById(353);
		System.out.println(e.getName());
		List<Employer> employers = controller.getAll();
		System.out.println("Size:" + l.size());
		System.out.println(employers.get(0).getName());
		//Employer e = employers.get(0).g;
//		for(Employer e : employers){
//			System.out.println(e.getName());
//		}
	}
}

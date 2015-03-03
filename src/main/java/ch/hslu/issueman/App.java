package ch.hslu.issueman;

import java.util.List;

public class App {

	public static void main(String[] args) {
		
		PersonController PersonController = new PersonController();
		//PersonController.deleteAll();
		
		Person person4 = new Person(4, "Fyodor Dostoevsky");
		Person person5 = new Person(5, "Leo Tolstoy");
		Person person6 = new Person(6, "Jane Austen");

		PersonController.persist(person4);
		PersonController.persist(person5);
		PersonController.persist(person6);
		
		//PersonController.deleteById(6);
		
		//person4.setName("Frodo");
		//PersonController.update(person4);
		
		List<Person> people = PersonController.getAll();
		
		for (Person p : people) {
			System.out.println(p.toString());
		}
	}

}

package ch.issueman.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Rolle;

import com.github.javafaker.Faker;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * TODO klasse implementieren
 * 
 * @author jvr
 *
 */
public class Seed {
	
	private final static Config config = ConfigFactory.load();
	
	public static void main(String[] args) {
		
		// TODO Array für standardwerte erstellen
		List<String> defaultrollen = getConfig("seed.Person", null);
		
		// TODO für alle entities config laden, bzw. im Config file erstellen.
		int anzahlSachbearbeiter = getConfig("seed.Sachbearbeiter", 10);
		
		Faker faker = new Faker();

		// TODO Alle Controller laden
		
		Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
		Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
		
		// TODO alle daten löschen
		
		sachbearbeitercontroller.deleteAll();
		rollecontroller.deleteAll();
		
		// TODO standardwerte erstellen
		
		for(String rolle : defaultrollen){
			rollecontroller.persist(new Rolle(rolle));
		}
		
		// TODO daten seeden
		
		for(int i = 1; i <= anzahlSachbearbeiter; i++){
			sachbearbeitercontroller.persist(new Sachbearbeiter(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));
		}		
		
		// TODO Einheitliche daten seeden: Für alle Logins, Beispiel Sachbearbeiter: login{person{sb, sb, sb@im.ch}, "sb", id->Rolle->"Sachbearbeiter"}
		
		// TODO geseedete Daten summary
		
		System.out.println("Seeded: " + anzahlSachbearbeiter + " Sachbearbeiter");
		
		// Beispiele - Dynmaisch Datenabrufen
		
		List<Sachbearbeiter> sachbearbeiter = sachbearbeitercontroller.getAllByProperty("vorname", new String[]{"peter"});
		sachbearbeiter.stream().filter(p -> p.getVorname().equals("peter"));
		List<Rolle> rollen = rollecontroller.getAll();
		Rolle RolleSachbearbeiter = rollen.stream().filter(r -> r.getBezeichnung().equals("Sachbearbeiter")).findFirst().get();		
		
		/* alter code

		Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
		Controller<Employer, Integer> employercontroller = new Controller<Employer, Integer>(Employer.class);
		Controller<Project, Integer> projectcontroller = new Controller<Project, Integer>(Project.class);
		Controller<Comment, Integer> commentcontroller = new Controller<Comment, Integer>(Comment.class);

		projectcontroller.deleteAll();
		commentcontroller.deleteAll();
		usercontroller.deleteAll();
		employercontroller.deleteAll();
		personcontroller.deleteAll();
				
		int i = 0;
		int j = 0;
		
		for (i = 0; i <= 20; i++) {

			Person person = new Person(faker.name().firstName());
			User user = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.letterify("??????"), "Administrator");
			Employer employer = new Employer(faker.name().firstName(), faker.name().lastName());

			personcontroller.persist(person);
			usercontroller.persist(user);
			employercontroller.persist(employer);

			if (i % 4 == 0) {
				
				Project project = new Project("Project: " + faker.name().lastName(), employer);
				
				List<Comment> comments = new ArrayList<Comment>();
				
				for (j = 0; j <= 10; j++) {
					
					Comment comment = new Comment(faker.lorem().paragraph(),user);
					comments.add(comment);
					project.setComments(comments);
				}

				projectcontroller.persist(project);
			}
		}
		
		System.out.println("Seeded: " + i*3 + " people");
		System.out.println("Seeded: " + (i/4+1) + " projects");
		System.out.println("Seeded: " + j*(i/4+1) + " comments");
		
		*/
	}
	
	/**
	 * Checks whether the Integer config entry exists or not and returns the defined value.
	 * 
	 * @param path the path in the config file.
	 * @param defaultcount the default value to return when the entry doesn't exist.
	 * @return the config value.
	 */
	private static int getConfig(String path, int defaultcount) {
		if(config.hasPath(path)){
			return config.getInt(path);
		}else{
			return defaultcount;
		}
		
	}

	/**
	 * Checks whether the String List config entry exists or not and returns the defined values.
	 * 
	 * @param path the path in the config file.
	 * @param defaultvalues the default values for this config entry.
	 * @return the config String List.
	 */
	private static List<String> getConfig(String path, String[] defaultvalues) {
		if(config.hasPath(path)){
			return config.getStringList(path);
		}else{
			return new ArrayList<String>(Arrays.asList(defaultvalues));
		}
		
	}
}

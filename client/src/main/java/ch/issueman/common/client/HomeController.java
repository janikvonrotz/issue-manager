package ch.issueman.common.client;

public class HomeController {

	public static void main(String[] args) {
		
		PersonClientController c = new PersonClientController();
		System.out.println(c.getById(1).getName());
	}

}

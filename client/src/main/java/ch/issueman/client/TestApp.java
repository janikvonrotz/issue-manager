package ch.issueman.client;

import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

public class TestApp {

	public static void main(String[] args){
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
		try {
			Arbeitstyp arbeitstyp = arbeitstypcontroller.getById(3103);
			System.out.println(arbeitstyp.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Context.logout();
	}
	
}

package ch.issueman.webservice;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;

public class RMIServer {
	public static void main(String[] args) {
	
		try {
			ResponseBuilder<Adresse, Integer> adresse = new ResponseBuilder<Adresse, Integer>(Adresse.class);
			ResponseBuilder<Arbeitstyp, Integer> arbeitstyp = new ResponseBuilder<Arbeitstyp, Integer>(Arbeitstyp.class);
			Registry reg = LocateRegistry.createRegistry(ConfigHelper.getConfig("rmi.port", 1099));
			if (reg!= null){
				Naming.rebind("adresse", adresse);
				Naming.rebind("arbeitstyp", arbeitstyp);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}

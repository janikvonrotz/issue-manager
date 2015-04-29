package ch.issueman.webservice;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lombok.extern.slf4j.Slf4j;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;

/**
 * Runs the rmi endpoints.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class RmiService{
	public static void main(String[] args) {
		try {
			BusinessController<Adresse, Integer> adresse = new BusinessController<Adresse, Integer>(Adresse.class);
			BusinessController<Arbeitstyp, Integer> arbeitstyp = new BusinessController<Arbeitstyp, Integer>(Arbeitstyp.class);
			Registry reg = LocateRegistry.createRegistry(ConfigHelper.getConfig("rmi.port", 1099));
			if (reg!= null){
				Naming.rebind("adresse", adresse);
				log.info("adresse bound");
				Naming.rebind("arbeitstyp", arbeitstyp);
				log.info("arbeitstyp bound");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}

package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * List view Passwort
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 */
public class PasswortView implements Viewable<Login, Login> {
	
	private static Controller<Login, Integer> controller = new Controller<Login, Integer>(Login.class);
	private Login login;
	
	@FXML
	private Label lbPerson;
	
	@FXML
	private PasswordField pfNeuesPasswort;	
	
	@FXML
	private PasswordField pfPasswortWiederholen;
	
	@FXML 
	private Button btSpeichern; 
	
	@FXML
	private Button btAbbrechen;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
	}
	
	@Override
	public void Refresh(){} 
		
//			pfNeuesPasswort.setText(login.get);
//			pfPasswortWiederholen.setText();
//		}
//		try {
//			tvEmployer.setItems(FXCollections.observableArrayList(controller.getAll()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	@FXML	
	public void clickSpeichern(){
		
	}
	
	@FXML
	public void clickAbbrechen(){
		
	}

	
	@Override
	public void initData(Login t) {
		login = t;
		Refresh();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDetail(Login t) {
		// TODO Auto-generated method stub
		
	}
	
	
}

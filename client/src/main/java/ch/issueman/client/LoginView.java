package ch.issueman.client;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class LoginView implements Initializable {

	@FXML
	private TextField txBenutzername;
	
	@FXML
	private PasswordField pfPasswort;
	
	@FXML
	private Button btLogin;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void clickLogin(){
		App.showMain();
	}

}

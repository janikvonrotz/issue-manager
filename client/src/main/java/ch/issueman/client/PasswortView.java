package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * List view Passwort
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 */
public class PasswortView implements ViewableDetail<Login> {
	
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
		Refresh();
	}
	
	@Override
	public void Refresh(){
		pfNeuesPasswort.setText("");
		pfPasswortWiederholen.setText("");
	} 
	
	@FXML	
	public void clickSpeichern(){
		try{
		// Passwort vergleichen
			if (pfPasswortWiederholen.getText() != pfNeuesPasswort.getText()){
				throw new Exception("Passwords do not match.");
			}
			Context.setNewPassword(pfPasswortWiederholen.getText());
		}catch (Exception e){
			// Fehler ausgeben
			MainView.showError(e);
			Refresh();
		}
	}
	
	@FXML
	public void clickAbbrechen(){
		
	}

	@Override
	public void initData(Login t) {
	}

	@Override
	public void showList() {
	}	
}

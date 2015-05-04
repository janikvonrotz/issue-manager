package ch.issueman.client;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * List view for Login
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoginView implements Initializable {

	private static Controller<Login, Integer> controller = new Controller<Login, Integer>(Login.class);
	
	@FXML
	private TextField txBenutzername;
	
	@FXML
	private PasswordField pfPasswort;
	
	@FXML
	private Button btLogin;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		txBenutzername.getText();
		pfPasswort.ge
	}
	
	public void refreshPersonTable() {
		try {
			tvEmployer.setItems(FXCollections.observableArrayList(controller.getAll()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	@FXML
	public void clickLogin(){
		App.showMain();
		Login login = new Login(new Sachbearbeiter("", "", txBenutzername.getText()), pfPasswort.getText(), null);
		Context.setLogin(login);
			if(Context.login()){
				pnData.setVisible(true);
				pnLogin.setVisible(false);
			}else{
				txBenutzername.setText("");
				pfPasswort.setText("");
			}
	}
	
	

}

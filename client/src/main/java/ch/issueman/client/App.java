package ch.issueman.client;

import java.io.IOException;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Main class of the client application.
 * 
 * @author Janik von Rotz, Patrick ?
 * @version 1.0.0
 * @since 1.0.0
 */
public class App extends Application {

	@FXML
	private TextField txBenutzername;

	@FXML
	private PasswordField pfPasswort;

	@FXML
	private Button btLogin;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Issue Manager");
		try {
			primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Login.fxml"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.show();
	}

	@FXML
	public void clickLogin(){
		Login login = new Login(new Sachbearbeiter("", "", txBenutzername.getText()), pfPasswort.getText(), null);
		Context.setLogin(login);
		if(Context.login()){
			
			try {
				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Issue Manger");
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			((Stage) txBenutzername.getParent().getScene().getWindow()).close();

		}else{
			txBenutzername.setText("");
			pfPasswort.setText("");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

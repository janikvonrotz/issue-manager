package ch.issueman.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainView implements Initializable {

	@FXML
	private Pane pnTop;

	@FXML
	private Pane pnLeft;

	@FXML
	private Pane pnCenter;
	
	@FXML
	private Pane bpMain;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showLogin();
	}

	public Stage showLogin() {
		
	}
}
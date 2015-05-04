package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
		return null;
		
	}

	public static <T> ViewableDetail<T> showCenterDetailView(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, Filter> Viewable<T, Filter> showCenterView(String string) {
		return null;
		// TODO Auto-generated method stub
		
	}
}

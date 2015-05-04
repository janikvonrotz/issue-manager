package ch.issueman.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import sun.applet.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
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
	private BorderPane bpMain;
	private static BorderPane root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		root = bpMain;
		showCenterView("Subunternehmen");
	}

	public static <T> ViewableDetail<T> showCenterDetailView(String viewname) {
		FXMLLoader loader =  new FXMLLoader();
		try {
			loader.setLocation(MainView.class.getResource(viewname + ".fxml"));
			Pane pane = (Pane) loader.load();
			root.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader.getController();
	}

	public static <T, Filter> Viewable<T, Filter> showCenterView(String viewname) {
		FXMLLoader loader =  new FXMLLoader();
		try {
			loader.setLocation(MainView.class.getResource(viewname + ".fxml"));
			Pane pane = (Pane) loader.load();
			root.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader.getController();
	}
}

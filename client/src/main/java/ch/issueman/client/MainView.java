package ch.issueman.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Main view controller that holds the border pane and handles the view navigation.
 * 
 * @author Janik von Rotz, Patrick Elsener
 * @version 1.0.0
 * @since 1.0.0
 */
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
		showCenterView("Person");
		showTopView("TopBauleiter");
		showLeftView("LeftSachbearbeiterStammdaten");
	}

	/**
	 * Display a detail view in the center section.
	 * 
	 * @param viewname the name of the detail view to display.
	 * @return view controller of this view.
	 */
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

	/**
	 * Display a view in the center section.
	 * 
	 * @param viewname the name of the view to display.
	 * @return view controller of this view.
	 */
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
	
	/**
	 * Display a view in the left section.
	 * 
	 * @param viewname the name of the view to display.
	 * @return view controller of this view.
	 */
	public static <T, Filter> Viewable<T, Filter> showLeftView(String viewname) {
		FXMLLoader loader =  new FXMLLoader();
		try {
			loader.setLocation(MainView.class.getResource(viewname + ".fxml"));
			Pane pane = (Pane) loader.load();
			root.setLeft(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader.getController();
	}
	
	/**
	 * Display a view in the top section.
	 * 
	 * @param viewname the name of the view to display.
	 * @return view controller of this view.
	 */
	public static <T, Filter> Viewable<T, Filter> showTopView(String viewname) {
		FXMLLoader loader =  new FXMLLoader();
		try {
			loader.setLocation(MainView.class.getResource(viewname + ".fxml"));
			Pane pane = (Pane) loader.load();
			root.setTop(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader.getController();
	}
}

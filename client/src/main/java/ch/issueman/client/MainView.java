package ch.issueman.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

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
		showCenterView("Subunternehmen");
		showTopView("TopDefault");
		showLeftView("LeftMangel");
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
			MainView.showError(e);
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
			MainView.showError(e);
		}
		return loader.getController();
	}
	
	/**
	 * Display a view in the left section.
	 * 
	 * @param viewname the name of the view to display.
	 * @return view controller of this view.
	 */
	public static <T> ViewableDetail<T> showLeftView(String viewname) {
		FXMLLoader loader =  new FXMLLoader();
		try {
			loader.setLocation(MainView.class.getResource(viewname + ".fxml"));
			Pane pane = (Pane) loader.load();
			root.setLeft(pane);
		} catch (IOException e) {
			MainView.showError(e);
		}
		return loader.getController();
	}
	
	/**
	 * Display a view in the top section.
	 * 
	 * @param viewname the name of the view to display.
	 * @return view controller of this view.
	 */
	public static <T> ViewableDetail<T> showTopView(String viewname) {
		FXMLLoader loader =  new FXMLLoader();
		try {
			loader.setLocation(MainView.class.getResource(viewname + ".fxml"));
			Pane pane = (Pane) loader.load();
			root.setTop(pane);
		} catch (IOException e) {
			MainView.showError(e);
		}
		return loader.getController();
	}
	
	public static void showError(Exception e) {
//		Alert alert = new Alert(AlertType.ERROR);
//		alert.setTitle("Error");
//		alert.setHeaderText("Exception occurred.");
//		alert.setContentText(e.getMessage());
//			
//		StringWriter sw = new StringWriter();
//		PrintWriter pw = new PrintWriter(sw);
//		e.printStackTrace(pw);
//		String exceptionText = sw.toString();
//
//		Label label = new Label("The exception stacktrace was:");
//
//		TextArea textArea = new TextArea(exceptionText);
//		textArea.setEditable(false);
//		textArea.setWrapText(true);
//
//		textArea.setMaxWidth(Double.MAX_VALUE);
//		textArea.setMaxHeight(Double.MAX_VALUE);
//		GridPane.setVgrow(textArea, Priority.ALWAYS);
//		GridPane.setHgrow(textArea, Priority.ALWAYS);
//
//		GridPane expContent = new GridPane();
//		expContent.setMaxWidth(Double.MAX_VALUE);
//		expContent.add(label, 0, 0);
//		expContent.add(textArea, 0, 1);
//
//		alert.getDialogPane().setExpandableContent(expContent);
//
//		alert.showAndWait();
		e.printStackTrace();
	}
	
}

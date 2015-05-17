package ch.issueman.client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	 * @param <T> the type of the viewable detail view.
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
	 * @param <T> the type of the viewable view.
	 * @param <Filter> the type of the initializable view variable.
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
	 * @param <T> the type of the viewable detail view.
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
	 * @param <T> the type of the viewable detail view.
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
	
	public static void exportData(ArrayList<ArrayList<String>> list){
		try {
			FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);
            
            String content = "";
            for(List<String> r : list){
	        	content += String.join(";", r) + "\n";
	        }
	        FileUtils.writeStringToFile(file, content);
	    } catch (Exception e) {
	    	MainView.showError(e);
	    }
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

	public static void showLogin() {
		
		try {
			Stage stage = new Stage();
			FXMLLoader loader =  new FXMLLoader();
			loader.setLocation(MainView.class.getResource("Login.fxml"));
			stage.setScene(new Scene(loader.load()));
			stage.setTitle("Issue Manger");
			stage.show();
			((Stage) root.getScene().getWindow()).close();
		} catch (IOException e) {
			showError(e);
		}
	}
}

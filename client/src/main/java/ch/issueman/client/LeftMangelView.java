package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;


import ch.issueman.common.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Mangel filter view for left pane.
 * 
 * @author Patrick Elsener, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class LeftMangelView implements ViewableDetail<Object> {

	// Projekte Controller deklarieren
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
	
	
	@FXML
	private ListView<Projekt> lvProjekt;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// anzeigen center mangel view.
		MainView.showCenterView("Mangel");
		Refresh();
	}

	@Override
	public void Refresh() {
		// List View mit Projekten laden
		
		try {
			ObservableList<Projekt> lvData = FXCollections.observableArrayList();
			lvData.addAll(projektcontroller.getAll());
			lvProjekt.setItems(lvData);
			lvProjekt.setCellFactory((list) -> {
			    return new ListCell<Projekt>() {
			        @Override
			        protected void updateItem(Projekt item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			            } else {
			                setText(item.getTitle());
//			                System.out.println(item.getTitle());
			            }
			        }
			    };
			});
			
			lvProjekt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			    System.out.println("ListView Selection Changed (selected: " + newValue.toString() + ")");
			});
		} catch (Exception e) {			
			MainView.showError(e);
		}
		


	}

	@Override
	public void initData(Object t) {
	}

	@Override
	public void showList() {
	}
	
	// egänzen list view click methode mit aufruf der der center Mangel View mit Projekt als parameter
	
	
}

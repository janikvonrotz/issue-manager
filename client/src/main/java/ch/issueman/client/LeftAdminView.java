package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;










import ch.issueman.common.Mangel;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Subunternehmen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Mangel filter view for left pane.
 * 
 * @author Patrick Elsener, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class LeftAdminView implements ViewableDetail<Object> {

	// Projekte Controller deklarieren
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
	
	
	@FXML
	private ImageView ivLogo;
	
	@FXML
	private ListView<Projekt> lvProjekt;
	
	@FXML
	private ListView<Subunternehmen> lvSubunternehmen;
	
	@FXML
	private ListView<Person> lvPerson;
	


//	private Projekt t;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// anzeigen center mangel view.
		
		ivLogo.setImage(new Image ("/IM_Logo4.png"));
			
		MainView.showCenterView("Mangel");
		Refresh();
	}

	@Override
	public void Refresh() {
		// List View mit Projekten laden
		
		try {
			ObservableList<Projekt> lvProjektData = FXCollections.observableArrayList();
			lvProjektData.addAll(projektcontroller.getAll());
			lvProjekt.setItems(lvProjektData);
			lvProjekt.setCellFactory((listProjekt) -> {
			    return new ListCell<Projekt>() {
			        @Override
			        protected void updateItem(Projekt item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			            } else {
			                setText(item.getTitle());
			            }
			        }
			    };
			});
			
			ObservableList<Subunternehmen> lvSubunternehmenData = FXCollections.observableArrayList();
			lvSubunternehmenData.addAll(subunternehmencontroller.getAll());
			lvSubunternehmen.setItems(lvSubunternehmenData);
			lvSubunternehmen.setCellFactory((listSubunternehmen) -> {
			    return new ListCell<Subunternehmen>() {
			        @Override
			        protected void updateItem(Subunternehmen item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			            } else {
			                setText(item.getFirmenname());
			            }
			        }
			    };
			});
			
			ObservableList<Person> lvPersonData = FXCollections.observableArrayList();
			lvPersonData.addAll(personcontroller.getAll());
			lvPerson.setItems(lvPersonData);
			lvPerson.setCellFactory((listPerson) -> {
			    return new ListCell<Person>() {
			        @Override
			        protected void updateItem(Person item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			            } else {
			                setText(item.getVorname() + " " + item.getNachname());
			            }
			        }
			    };
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
	@FXML
	public void clickProjekt() {
		Projekt t = lvProjekt.getSelectionModel().getSelectedItem();
    	ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(t);
//		System.out.println(t);
	}
	
	@FXML
	public void clickSubunternehmen() {
		Subunternehmen t = lvSubunternehmen.getSelectionModel().getSelectedItem();
    	ViewableDetail<Subunternehmen> view = MainView.showCenterDetailView("SubunternehmenDetail");
		view.initData(t);
//		System.out.println(t);
	}
	
	@FXML
	public void clickPerson() {
		Person t = lvPerson.getSelectionModel().getSelectedItem();
    	ViewableDetail<Person> view = MainView.showCenterDetailView("PersonDetail");
		view.initData(t);
//		System.out.println(t);
	}
	

}

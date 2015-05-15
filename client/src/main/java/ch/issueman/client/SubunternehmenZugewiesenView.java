package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * List view for Subunternehmen Zugewiesen
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class SubunternehmenZugewiesenView implements Viewable<Projekt, Projekt> {

	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Person, Integer> controller = new Controller<Person, Integer>(Person.class);
	
	private Projekt projekt;
	
	@FXML
	private TableView<Subunternehmen> tvData; 
	
	@FXML
	private TextField txFilter;
	
	@FXML
	private TableColumn<Subunternehmen, String> tcSubunternehmen; 
	
	@FXML 
	private TableColumn<Person, String> tcPerson;
	
	@FXML 
	private ComboBox cbSubunternehmen;
	
	@FXML 
	private ComboBox cbPerson;	
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tcSubunternehmen.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("Subunternehmen"));
		tcPerson.setCellValueFactory(new PropertyValueFactory<Person, String>("person"));
	
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getSubunternehmen() 
									+ t.getPerson();
							
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});		

		Refresh();
	}

	@Override
	public void Refresh() {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void clickSpeichern(){
		
	}
	
	@FXML
	public void clickAbbrechen(){
		
	}
	@Override
	public void initData(Projekt t) {
		projekt = t;
		Refresh();
	}

	@Override
	public void showDetail(Projekt t) {
		ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(t);
	}

}

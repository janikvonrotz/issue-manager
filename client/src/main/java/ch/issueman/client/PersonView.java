package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Person;
import ch.issueman.common.Sachbearbeiter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonView implements Initializable {
	
	private static Controller<Person, Integer> controller = new Controller<Person, Integer>(Person.class);
	
	@FXML
	private TableView<Person> tvData;
	
	@FXML
	private TableColumn<Person, Integer> tcId;
	
	@FXML
	private TableColumn<Person, String> tcNachname;
	
	@FXML
	private TableColumn<Person, String> tcVorname;
	
	@FXML
	private TableColumn<Person, String> tcEmail;
	
	@FXML
	private TableColumn<Person, String> tcRolle;
	
	@FXML
	private TableColumn<Person, Integer> tcFirma;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		tcId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		tcNachname.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
		tcVorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		tcRolle.setCellValueFactory(new PropertyValueFactory<Person, String>("dtype"));
		tcFirma.setCellValueFactory(new PropertyValueFactory<Person, Integer>("firma"));
		
		Refresh();	
	}
	
	public void Refresh(){
		try {
			tvData.setItems(FXCollections.observableArrayList(controller.getAll()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clickData(){
		
	}
}

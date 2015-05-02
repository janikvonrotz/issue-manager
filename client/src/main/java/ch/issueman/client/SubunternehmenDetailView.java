package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Adresse;
import ch.issueman.common.Login;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

public class SubunternehmenView implements Initializable {
	
	private static Controller<Person, Integer> personController = new Controller<Person, Integer>(Person.class);
	private static Controller<Subunternehmen, Integer> subunternehmenController = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	

	@FXML
	private Label lbSubunternehmen;
	
	@FXML
	private TextField txFirma;
	
	@FXML
	private TextField txStrasse;
	
	@FXML
	private TextField txPlz;
	
	@FXML
	private TextField txOrt;
		
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btSpeichern;
	
	@FXML
	private Label lbPerson;
	
	@FXML
	private Button btAddPerson;
	
	@FXML
	private TableView<Person> tvPerson;
	
	@FXML
	private TableColumn<Person, Integer> tcNachname;
	
	@FXML
	private TableColumn<Person, Integer> tcVorname;
	
	@FXML
	private TableColumn<Person, Integer> tcEmail;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		tcNachname.setCellValueFactory(new PropertyValueFactory<Person, Integer>("nachname"));
		tcVorname.setCellValueFactory(new PropertyValueFactory<Person, Integer>("vorname"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<Person, Integer>("email"));
			
		Refresh();	

	}
	
	public void Refresh(){
		try {
			tvPerson.setItems(FXCollections.observableArrayList(personController.getAll()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showSubunternehmenDetails(Subunternehmen subunternehmen) {
	    if (subunternehmen != null) {
	        
	    	txFirma.setText(subunternehmen.getFirmenname());
	    	txStrasse.setText(subunternehmen.getAdresse().getStrasse());
	    	txPlz.setText("" + subunternehmen.getAdresse().getOrt().getPlz());
	    	txOrt.setText(subunternehmen.getAdresse().getOrt().getOrt());

	    } else {

	    	txFirma.setText("");
	    	txStrasse.setText("");
	    	txPlz.setText("");
	    	txOrt.setText("");
	    }

	@FXML
	public void clickAbbrechen(){
		
	}
	
	@FXML
	public void clickSpeichern(Subunternehmen subunternehmen){
		if (subunternehmen != null) {
	        
			subunternehmen.setFirmenname(txFirma.getText());
			subunternehmen.setAdresse(new Adresse(txStrasse.getText(), new Ort(Integer.parseInt(txPlz.getText()), txOrt.getText())));
			
			try {
				subunternehmenController.update(subunternehmen);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else{
			subunternehmen.setFirmenname(txFirma.getText());
			subunternehmen.setAdresse(new Adresse(txStrasse.getText(), new Ort(Integer.parseInt(txPlz.getText()), txOrt.getText());
			
			try {
				subunternehmenController.persist(subunternehmen);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	}
	
	@FXML
	public void clickAddPerson(){
		
	}
}
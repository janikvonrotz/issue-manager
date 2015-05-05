package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Rolle;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * class PersonDetailView
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
public class PersonDetailView implements ViewableDetail<Person> {
	
	private static Controller<Person, Integer> personController = new Controller<Person, Integer>(Person.class);
	private static Controller<Sachbearbeiter, Integer> sachbearbeiterController = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
	private static Controller<Bauleiter, Integer> bauleiterController = new Controller<Bauleiter, Integer>(Bauleiter.class);
	private static Controller<Kontakt, Integer> kontaktController = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Bauherr, Integer> bauherrController = new Controller<Bauherr, Integer>(Bauherr.class);
	private static Controller<Subunternehmen, Integer> subunternehmenController = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Rolle, Integer> rolleController = new Controller<Rolle, Integer>(Rolle.class);
	
	@FXML
	private Label lbPerson;
	
	@FXML
	private TextField txNachname;
	
	@FXML
	private TextField txVorname;
	
	@FXML
	private TextField txEmail;
	
	@FXML
	private ComboBox<Rolle> cbRolle;
	
	@FXML
	private PasswordField pfPasswort;
	
	@FXML
	private ComboBox<Subunternehmen> cbSubunternehmen;

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
	private Button btPasswort;
	
	@FXML
	private Button btSpeichern;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		cbRolle.setItems(FXCollections.observableArrayList(rolleController.getAll()));
		cbSubunternehmen.setItems(FXCollections.observableArrayList(subunternehmenController.getAll()));
		
		Refresh(person);	

	}
	
	public void Refresh(Person person){
		
		if(person != null){
		
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showPersonDetails(Person person) {
	    if (person != null) {
	        
	    	lbPerson.setText(value);
	    	txNachname.setText(value);
	    	txVorname.setText(value);
	    	txEmail.setText(value);
	    	cbRolle.setValue(arg0);
	    	cbSubunternehmen.setValue(arg0); //nur für Kontaktadmin/Kontaktperson
	    	txFirma.setText(value); //nur für Bauherr
	    	txStrasse.setText(value); //nur für Bauherr
	    	txPlz.setText(value); //nur für Bauherr
	    	txOrt.setText(value); //nur für Bauherr
	    	
	    } else {

	    	lbPerson.setText("");
	    	txNachname.setText("");
	    	txVorname.setText("");
	    	txEmail.setText("");
	    	cbRolle.setValue(null);
	    	pfPasswort.setText(""); //nur wenn neue Person erstellt wird und nicht Bauherr
	    	cbSubunternehmen.setValue(null);
	    	txFirma.setText("");
	    	txStrasse.setText("");
	    	txPlz.setText("");
	    	txOrt.setText("");
	    }
		
	}

	@FXML
	public void clickAbbrechen(){
		
	}
	
	@FXML
	public void clickPasswort(){
		
	}
	
	@FXML
	public void clickSpeichern(Projekt projekt){
		if (projekt != null) {
	        

			try {
				projektController.update(projekt);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else{


			try {
				projektController.persist(projekt);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void Refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showList() {
		// TODO Auto-generated method stub
		
	}

}

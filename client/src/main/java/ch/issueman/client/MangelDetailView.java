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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Adresse;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * class MangelDetailView
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
public class MangelDetailView implements Initializable {
	
	private static Controller<Mangel, Integer> mangelController = new Controller<Mangel, Integer>(Mangel.class);
	private static Controller<Kommentar, Integer> kommentarController = new Controller<Kommentar, Integer>(Kommentar.class);
	private static Controller<Projekt, Integer> projektController = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Subunternehmen, Integer> subunternehmenController = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Mangelstatus, Integer> statusController = new Controller<Mangelstatus, Integer>(Mangelstatus.class);
	
	@FXML
	private Label lbMangel;
	
	@FXML
	private ComboBox<Projekt> cbProjekt;
	
	@FXML
	private Button btShowProjekt;
	
	@FXML
	private TextField txBeschreibung;
	
	@FXML
	private ComboBox<Subunternehmen> cbSubunternehmen;
	
	@FXML
	private Button btShowSubunternehmen;
	
	@FXML
	private ComboBox<Mangelstatus> cbStatus;
	
	@FXML
	private DatePicker dpFrist;
	
	@FXML
	private TableView<Kommentar> tvKommentar;
	
	@FXML
	private TableColumn<Kommentar, Integer> tcKommentar;
	
	@FXML
	private TableColumn<Kommentar, Integer> tcAutor;
	
	@FXML
	private TableColumn<Kommentar, Integer> tcZeit;
	
	@FXML
	private Button btSend;
	
	@FXML
	private TextField txKommentar;
	
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btSpeichern;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		cbProjekt.setItems(FXCollections.observableArrayList(projektController.getAll()));
		cbSubunternehmen.setItems(FXCollections.observableArrayList(subunternehmenController.getAll()));
		cbStatus.setItems(FXCollections.observableArrayList(statusController.getAll()));
		tcKommentar.setCellValueFactory(new PropertyValueFactory<Kommentar, Integer>("kommentar"));
		tcAutor.setCellValueFactory(new PropertyValueFactory<Kommentar, Integer>("login_id"));
		tcZeit.setCellValueFactory(new PropertyValueFactory<Kommentar, Integer>("erstelltam"));
			
		Refresh(mangel);	

	}
	
	public void Refresh(Mangel mangel){
		
		if(mangel != null){
		
			try {
				tvKommentar.setItems(FXCollections.observableArrayList(kommentarController.getAll()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showMangelDetails(Mangel mangel) {
	    if (mangel != null) {
	        
	    	lbMangel.setText(value);
	    	cbProjekt.setValue(arg0);
	    	txBeschreibung.setText(value);
	    	cbSubunternehmen.setValue(value);
	    	cbStatus.setValue(value);
	    	dpFrist.setValue(value);
	    	
	    } else {

	    	lbMangel.setText("");
	    	cbProjekt.setValue(null);
	    	txBeschreibung.setText("");
	    	cbSubunternehmen.setValue(null);
	    	cbStatus.setValue(null);
	    	dpFrist.setValue(null);
	    }
	    
	@FXML
	public void clickShowProject(){
		
	}
	
	@FXML
	public void clickShowSubunternehmen(){
		
	}
	
	@FXML
	public void clickSend(){
		
	}

	@FXML
	public void clickAbbrechen(){
		
	}
	
	@FXML
	public void clickSpeichern(Mangel mangel){
		if (mangel != null) {
	        

			try {
				mangelController.update(mangel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else{


			try {
				mangelController.persist(mangel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	}
	
	@FXML
	public void clickAddKontakt(){
		
	}
}

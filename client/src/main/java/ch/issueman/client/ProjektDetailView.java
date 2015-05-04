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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * class ProjektDetailView
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProjektDetailView implements Initializable {
	
	private static Controller<Projekttyp, Integer> projekttypController = new Controller<Projekttyp, Integer>(Projekttyp.class);
	private static Controller<Bauherr, Integer> bauherrController = new Controller<Bauherr, Integer>(Bauherr.class);
	private static Controller<Projekt, Integer> projektController = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Subunternehmen, Integer> subunternehmenController = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Arbeitstyp, Integer> arbeitstypController = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
	
	@FXML
	private Label lbProjekt;
	
	@FXML
	private TextField txTitel;
	
	@FXML
	private TextField txStrasse;
	
	@FXML
	private TextField txPlz;
	
	@FXML
	private TextField txOrt;
	
	@FXML
	private ComboBox<Arbeitstyp> cbArbeitstyp;
	
	@FXML
	private ComboBox<Projekttyp> cbProjekttyp;
	
	@FXML
	private DatePicker dpBeginn;
	
	@FXML
	private DatePicker dpEnde;
	
	@FXML
	private ComboBox<Bauherr> cbBauherr;
	
	@FXML
	private TextField txProjektleiter;
	
	@FXML
	private TextArea taSubunternehmen;
	
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btArchivieren;
	
	@FXML
	private Button btSpeichern;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		cbArbeitstyp.setItems(FXCollections.observableArrayList(arbeitstypController.getAll()));
		cbProjekttyp.setItems(FXCollections.observableArrayList(projekttypController.getAll()));
		cbBauherr.setItems(FXCollections.observableArrayList(bauherrController.getAll()));
		
		Refresh(projekt);	

	}
	
	public void Refresh(Projekt projekt){
		
		if(projekt != null){
		
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showMangelDetails(Mangel mangel) {
	    if (mangel != null) {
	        
	    	lbProjekt.setText(value);
	    	txTitel.setText(value);
	    	txStrasse.setText(value);
	    	txPlz.setText(value);
	    	txOrt.setText(value);
	    	cbArbeitstyp.setValue(arg0);
	    	cbProjekttyp.setValue(arg0);
	    	dpBeginn.setValue(value);
	    	dpEnde.setValue(value);
	    	cbBauherr.setValue(value);
	    	txProjektleiter.setText(value);
	    	taSubunternehmen.setText(value);
	    	
	    } else {

	    	lbProjekt.setText("");
	    	txTitel.setText("");
	    	txStrasse.setText("");
	    	txPlz.setText("");
	    	txOrt.setText("");
	    	cbArbeitstyp.setValue(null);
	    	cbProjekttyp.setValue(null);
	    	dpBeginn.setValue("");
	    	dpEnde.setValue("");
	    	cbBauherr.setValue("");
	    	txProjektleiter.setText("");
	    	taSubunternehmen.setText("");
	    }
		
	}

	@FXML
	public void clickAbbrechen(){
		
	}
	
	@FXML
	public void clickArchivieren(){
		
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

}

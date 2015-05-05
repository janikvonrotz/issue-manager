package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * List view for Projektleitung
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class ProjektleitungView implements Viewable<Projektleitung, Projektleitung>{

	@FXML
	private TableView tvProjektleitung;
	
	@FXML
	private TableColumn tcBauleiter;
	
	
	@FXML
	private TableColumn tcBeginn;
	
	@FXML
	private TableColumn tcEnde;
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SubunternehmenZugewiesenView implements Initializable {

	@FXML
	private TableView tvSubunternehmenZugewiesen; 
	
	@FXML
	private TableColumn tcSubunternehmen; 
	
	@FMXL 
	private TableColumn tcPerson
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

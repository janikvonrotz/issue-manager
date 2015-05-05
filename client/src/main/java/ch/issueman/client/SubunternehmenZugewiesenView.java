package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Subunternehmen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SubunternehmenZugewiesenView implements Viewable<Subunternehmen, Subunternehmen> {

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

	@Override
	public void Refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData(Subunternehmen t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDetail(Subunternehmen t) {
		// TODO Auto-generated method stub
		
	}

}

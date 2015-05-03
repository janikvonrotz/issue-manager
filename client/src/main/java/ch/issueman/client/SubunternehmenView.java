package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Adresse;
import ch.issueman.common.Login;
import ch.issueman.common.Ort;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

public class SubunternehmenView implements Initializable {
	
	private static Controller<Subunternehmen, Integer> controller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	
	@FXML
	private TableView<Subunternehmen> tvData;
	
	@FXML
	private TableColumn<Subunternehmen, Integer> tcId;
	
	@FXML
	private TableColumn<Subunternehmen, String> tcFirmenname;
	
	@FXML
	private TableColumn<Subunternehmen, Integer> tcStrasse;
	
	@FXML
	private TableColumn<Ort, Integer> tcPlz;
	
	@FXML
	private TableColumn<Ort, String> tcOrt;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		tcId.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("id"));
		tcFirmenname.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("firmenname"));
		tcStrasse.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("adresse_id"));
		
//		tcStrasse.setCellValueFactory(adresseController.getById(tcId.getCellData());
//		tcPlz.setCellValueFactory(new PropertyValueFactory<Ort, Integer>("plz"));
//		tcOrt.setCellValueFactory(new PropertyValueFactory<Ort, String>("ort"));
		
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

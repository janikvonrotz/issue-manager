package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Sachbearbeiter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProjektView implements Initializable {
	
	private static Controller<Projekt, Integer> controller = new Controller<Projekt, Integer>(Projekt.class);
	
	@FXML
	private TableView<Projekt> tvData;
	
	@FXML
	private TableColumn<Projekt, Integer> tcId;
	
	@FXML
	private TableColumn<Projekt, String> tcTitel;
	
	@FXML
	private TableColumn<Projekt, Integer> tcProjekttyp;
	
	@FXML
	private TableColumn<Projekt, Integer> tcArbeitstyp;
	
	@FXML
	private TableColumn<Projekt, Integer> tcBauleiter;
	
	@FXML
	private TableColumn<Projekt, Integer> tcBauherr;
	
	@FXML
	private TableColumn<Projekt, String> tcEnddatum;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		tcId.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("id"));
		tcTitel.setCellValueFactory(new PropertyValueFactory<Projekt, String>("title"));
		tcProjekttyp.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("projekttyp_id"));
		tcArbeitstyp.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("arbeitstyp_id"));
		tcBauleiter.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("bauleiter_id"));
		tcBauherr.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("bauherr_id"));
		tcEnddatum.setCellValueFactory(new PropertyValueFactory<Projekt, String>("ende"));
		
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

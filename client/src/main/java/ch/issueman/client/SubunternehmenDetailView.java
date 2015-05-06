package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Login;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

/**
 * Detail view for Subunternehmen
 * 
 * @author Reno Meyer, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class SubunternehmenDetailView implements ViewableDetail<Subunternehmen> {
	
	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private Subunternehmen subunternehmen;
	
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
	private Label lbKontakt;
	
	@FXML
	private Button btAddKontakt;
	
	@FXML
	private TableView<Kontakt> tvKontakt;
	
	@FXML
	private TableColumn<Kontakt, Integer> tcNachname;
	
	@FXML
	private TableColumn<Kontakt, Integer> tcVorname;
	
	@FXML
	private TableColumn<Kontakt, Integer> tcEmail;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		tcNachname.setCellValueFactory(new PropertyValueFactory<Kontakt, Integer>("nachname"));
		tcVorname.setCellValueFactory(new PropertyValueFactory<Kontakt, Integer>("vorname"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<Kontakt, Integer>("email"));
			
		Refresh();	
	}
	
	public void Refresh(){
		
		if(subunternehmen != null){
			
			txFirma.setText(subunternehmen.getFirmenname());
	    	txStrasse.setText(subunternehmen.getAdresse().getStrasse());
	    	txPlz.setText("" + subunternehmen.getAdresse().getOrt().getPlz());
	    	txOrt.setText(subunternehmen.getAdresse().getOrt().getOrt());
		
			try {
				tvKontakt.setItems(FXCollections.observableArrayList(kontaktcontroller.getAll()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

	    	txFirma.setText("");
	    	txStrasse.setText("");
	    	txPlz.setText("");
	    	txOrt.setText("");
	    }
	}
	
	@FXML
	public void clickSpeichern() {
		
	
//	@FXML
//	public void clickSpeichern(){
//		
//		if (subunternehmen != null) {
//	        
//			subunternehmen.setFirmenname(txFirma.getText());
//			subunternehmen.setAdresse(new Adresse(txStrasse.getText(), new Ort(Integer.parseInt(txPlz.getText()), txOrt.getText())));
//			
//			try {
//				subunternehmenController.update(subunternehmen);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//		}else{
//			subunternehmen.setFirmenname(txFirma.getText());
//			subunternehmen.setAdresse(new Adresse(txStrasse.getText(), new Ort(Integer.parseInt(txPlz.getText()), txOrt.getText());
//			
//			try {
//				subunternehmenController.persist(subunternehmen);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		
//	}
//=======
//>>>>>>> origin/master
	}

//	@FXML
//	public void clickAbbrechen(){
//		
//	}
//	
//	@FXML
//	public void clickSpeichern(){
//		
//		if (subunternehmen != null) {
//	        
//			subunternehmen.setFirmenname(txFirma.getText());
//			subunternehmen.setAdresse(new Adresse(txStrasse.getText(), new Ort(Integer.parseInt(txPlz.getText()), txOrt.getText())));
//			
//			try {
//				subunternehmenController.update(subunternehmen);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//		}else{
//			subunternehmen.setFirmenname(txFirma.getText());
//			subunternehmen.setAdresse(new Adresse(txStrasse.getText(), new Ort(Integer.parseInt(txPlz.getText()), txOrt.getText());
//			
//			try {
//				subunternehmenController.persist(subunternehmen);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		
//	}
//	}
	
	@FXML
	public void clickAddKontakt(){
		
	}

	@Override
	public void initData(Subunternehmen t) {
		subunternehmen = t;
		Refresh();
	}

	@Override
	public void showList() {
		Viewable<Subunternehmen, Subunternehmen> view = MainView.showCenterView("Subunternehmen");
	}
}

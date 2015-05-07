package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Login;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Ort;
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
	private static Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
	private Subunternehmen subunternehmen;
	
	@FXML
	private Label lbSubunternehmen;
	
	@FXML
	private TextField txFirma;
	
	@FXML
	private TextField txStrasse;
	
	@FXML
	private ComboBox<Ort> cbPlz;
	
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
		
		cbPlz.setCellFactory(new Callback<ListView<Ort>,ListCell<Ort>>(){
			@Override
			public ListCell<Ort> call(ListView<Ort> arg0) {		 
				final ListCell<Ort> cell = new ListCell<Ort>(){				 
                    @Override
                    protected void updateItem(Ort t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText("" + t.getPlz());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });

			
		Refresh();	
	}
	
	public void Refresh(){
		
		if(subunternehmen != null){
			
			txFirma.setText(subunternehmen.getFirmenname());
	    	txStrasse.setText(subunternehmen.getAdresse().getStrasse());
	    	cbPlz.setValue(subunternehmen.getAdresse().getOrt());
	    	txOrt.setText(cbPlz.getValue().getOrt());
		
			try {
				tvKontakt.setItems(FXCollections.observableArrayList(kontaktcontroller.getAll()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

	    	txFirma.setText("");
	    	txStrasse.setText("");
	    	cbPlz.setValue(null);
	    	txOrt.setText("");
	    }
	}
	
	@FXML
	public void refreshOrt(){
		txOrt.setText(cbPlz.getValue().getOrt());
	}
	
	@FXML
	public void clickSpeichern(){
		
		if (subunternehmen != null) {
	        
			subunternehmen.setFirmenname(txFirma.getText());
			
			Adresse a = subunternehmen.getAdresse();
			Ort o = subunternehmen.getAdresse().getOrt();
			a.setStrasse(txStrasse.getText());
			o.setId(cbPlz.getValue().getId());
			a.setOrt(o);
			
			try {
				subunternehmencontroller.update(subunternehmen);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else{
			
			try {
				Ort o = ortcontroller.getById(cbPlz.getValue().getId());
				subunternehmencontroller.persist(new Subunternehmen(txFirma.getText(),
						new Adresse(txStrasse.getText(), o)));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@FXML
	public void clickAbbrechen(){
		
	}
	
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

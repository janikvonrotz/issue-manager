package ch.issueman.client;

import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
public class ProjektDetailView implements ViewableDetail<Projekt> {
	
	private static Controller<Projekttyp, Integer> projekttypController = new Controller<Projekttyp, Integer>(Projekttyp.class);
	private static Controller<Bauherr, Integer> bauherrController = new Controller<Bauherr, Integer>(Bauherr.class);
	private static Controller<Projekt, Integer> projektController = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Subunternehmen, Integer> subunternehmenController = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Arbeitstyp, Integer> arbeitstypController = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
	private Projekt projekt;
	
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
	private TextField lbProjektleiter;
	
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
		
		cbArbeitstyp.setCellFactory(new Callback<ListView<Arbeitstyp>,ListCell<Arbeitstyp>>(){
			@Override
			public ListCell<Arbeitstyp> call(ListView<Arbeitstyp> arg0) {		 
				final ListCell<Arbeitstyp> cell = new ListCell<Arbeitstyp>(){				 
                    @Override
                    protected void updateItem(Arbeitstyp t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getArbeitstyp());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbProjekttyp.setCellFactory(new Callback<ListView<Projekttyp>,ListCell<Projekttyp>>(){
			@Override
			public ListCell<Projekttyp> call(ListView<Projekttyp> arg0) {		 
				final ListCell<Projekttyp> cell = new ListCell<Projekttyp>(){				 
                    @Override
                    protected void updateItem(Projekttyp t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getProjekttyp());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });

	
		cbBauherr.setCellFactory(new Callback<ListView<Bauherr>,ListCell<Bauherr>>(){
			@Override
			public ListCell<Bauherr> call(ListView<Bauherr> arg0) {		 
				final ListCell<Bauherr> cell = new ListCell<Bauherr>(){				 
                    @Override
                    protected void updateItem(Bauherr t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getDisplayName());
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
		
		if(projekt != null){
		
			lbProjekt.setText(projekt.getDisplayName());
	    	txTitel.setText(projekt.getTitle());
	    	txStrasse.setText(projekt.getAdresse().getStrasse());
	    	txPlz.setText("" + (projekt.getAdresse().getOrt().getPlz());
	    	txOrt.setText(projekt.getAdresse().getOrt().getOrt());
	    	cbArbeitstyp.setValue(projekt.getArbeitstyp());
	    	cbProjekttyp.setValue(projekt.getProjekttyp());
	    	dpBeginn.setValue(((GregorianCalendar) projekt.getBeginn()).toZonedDateTime().toLocalDate());
	    	dpEnde.setValue(((GregorianCalendar) projekt.getEnde()).toZonedDateTime().toLocalDate());
	    	cbBauherr.setValue(projekt.getBauherr());
	    	lbProjektleiter.setText(projekt.getCurrentProjektleiter().getDisplayName());
	    	taSubunternehmen.setText("abcdefgh");
	    	
			try {
				
				cbArbeitstypItems(FXCollections.observableArrayList(kontaktController.getAll()));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

	    	lbProjekt.setText("");
	    	txTitel.setText("");
	    	txStrasse.setText("");
	    	txPlz.setText("");
	    	txOrt.setText("");
	    	cbArbeitstyp.setValue(null);
	    	cbProjekttyp.setValue(null);
	    	dpBeginn.setValue(null);
	    	dpEnde.setValue(null);
	    	cbBauherr.setValue(null);
	    	lbProjektleiter.setText("");
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
	public void clickSpeichern(){
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
	public void initData(Projekt t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showList() {
		// TODO Auto-generated method stub
		
	}

}

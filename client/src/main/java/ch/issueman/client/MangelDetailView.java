package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.StringConverter;
import ch.issueman.common.Adresse;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Rolle;
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
public class MangelDetailView implements ViewableDetail<Mangel> {
	
	private static Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
	private static Controller<Kommentar, Integer> kommentarcontroller = new Controller<Kommentar, Integer>(Kommentar.class);
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Mangelstatus, Integer> statuscontroller = new Controller<Mangelstatus, Integer>(Mangelstatus.class);
	private static Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
	private Mangel mangel;
	private ObservableList<Mangelstatus> allStatus;
	private ObservableList<Mangelstatus> statusList;
	
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
	private TextArea taKommentar;
	
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btSpeichern;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		cbProjekt.setCellFactory(new Callback<ListView<Projekt>,ListCell<Projekt>>(){
			@Override
			public ListCell<Projekt> call(ListView<Projekt> arg0) {		 
				final ListCell<Projekt> cell = new ListCell<Projekt>(){				 
                    @Override
                    protected void updateItem(Projekt p, boolean bln) {
                        super.updateItem(p, bln);
                         
                        if(p != null){
                            setText(p.getDisplayName());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbProjekt.setConverter(new StringConverter<Projekt>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Projekt fromString(String arg0) {
				return null;
			}

			public String toString(Projekt p) {
               if (p != null) {
                    String str = p.getDisplayName();
                    map.put(str, p);
                    return str;
                } else {
                    return "";
                }
			}
		});

		cbSubunternehmen.setCellFactory(new Callback<ListView<Subunternehmen>,ListCell<Subunternehmen>>(){
			@Override
			public ListCell<Subunternehmen> call(ListView<Subunternehmen> arg0) {		 
				final ListCell<Subunternehmen> cell = new ListCell<Subunternehmen>(){				 
                    @Override
                    protected void updateItem(Subunternehmen s, boolean bln) {
                        super.updateItem(s, bln);
                         
                        if(s != null){
                            setText(s.getFirmenname());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbSubunternehmen.setConverter(new StringConverter<Subunternehmen>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Subunternehmen fromString(String arg0) {
				return null;
			}

			public String toString(Subunternehmen s) {
               if (s != null) {
                    String str = s.getFirmenname();
                    map.put(str, s);
                    return str;
                } else {
                    return "";
                }
			}
		});

		cbStatus.setCellFactory(new Callback<ListView<Mangelstatus>,ListCell<Mangelstatus>>(){
			@Override
			public ListCell<Mangelstatus> call(ListView<Mangelstatus> arg0) {		 
				final ListCell<Mangelstatus> cell = new ListCell<Mangelstatus>(){				 
                    @Override
                    protected void updateItem(Mangelstatus m, boolean bln) {
                        super.updateItem(m, bln);
                         
                        if(m != null){
                            setText(m.getStatus());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbStatus.setConverter(new StringConverter<Mangelstatus>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Mangelstatus fromString(String arg0) {
				return null;
			}

			public String toString(Mangelstatus m) {
               if (m != null) {
                    String str = m.getStatus();
                    map.put(str, m);
                    return str;
                } else {
                    return "";
                }
			}
		});
		
		tcKommentar.setCellValueFactory(new PropertyValueFactory<Kommentar, Integer>("kommentar"));
		tcAutor.setCellValueFactory(new PropertyValueFactory<Kommentar, Integer>("login_id"));
		tcZeit.setCellValueFactory(new PropertyValueFactory<Kommentar, Integer>("erstelltam"));
			
		Refresh();	

	}
	
	@Override
	public void Refresh(){
		
		if(mangel != null){
			
	    	lbMangel.setText(mangel.getProjekt().getDisplayName() + "M" + mangel.getReferenz());
	    	cbProjekt.setValue(mangel.getProjekt());
	    	cbProjekt.setDisable(true);
	    	txBeschreibung.setText(mangel.getMangel());
	    	cbSubunternehmen.setValue(mangel.getSubunternehmen());
//	    	dpFrist.setValue(mangel.getErledigenbis());
		
			try {
				cbProjekt.setItems(FXCollections.observableArrayList(projektcontroller.getAll()));
				cbSubunternehmen.setItems(FXCollections.observableArrayList(subunternehmencontroller.getAll()));
				allStatus = FXCollections.observableArrayList(statuscontroller.getAll());

				tvKommentar.setItems(FXCollections.observableArrayList(kommentarcontroller.getAll()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Context.getLogin().getRolle().getBezeichnung().equals("Sachbearbeiter")){
				statusList.add(mangel.getMangelstatus());
				statusList.add(allStatus.get(0));
				statusList.add(allStatus.get(3));
				cbStatus.getItems().addAll(statusList);
		    	cbStatus.setValue(mangel.getMangelstatus());
			}
			
			if(Context.getLogin().getRolle().getBezeichnung().equals("Bauleiter")){
				statusList.add(mangel.getMangelstatus());
				statusList.add(allStatus.get(0));
				statusList.add(allStatus.get(3));
				cbStatus.getItems().addAll(statusList);
		    	cbStatus.setValue(mangel.getMangelstatus());
			}

			if(Context.getLogin().getRolle().getBezeichnung().contains("Kontakt")){
				statusList.add(mangel.getMangelstatus());
				statusList.add(allStatus.get(1));
				statusList.add(allStatus.get(4));
				statusList.add(allStatus.get(2));
				cbStatus.getItems().addAll(statusList);
		    	cbStatus.setValue(mangel.getMangelstatus());
		    	
		    	txBeschreibung.setDisable(true);
		    	cbSubunternehmen.setDisable(true);
		    	dpFrist.setDisable(true);
			}

		} else {
	    	lbMangel.setText("neuer mangel");
	    	tvKommentar.setVisible(false);
	    	taKommentar.setVisible(false);
	    	btSend.setVisible(false);
		}
	}
	    
	@FXML
	public void clickShowProject(){
		Viewable<Projekt, Projekt> view = MainView.showCenterView("ProjektDetail");
		view.initData(mangel.getProjekt());
	}
	
	@FXML
	public void clickShowSubunternehmen(){
		Viewable<Subunternehmen, Subunternehmen> view = MainView.showCenterView("SubunternehmenDetail");
		view.initData(mangel.getSubunternehmen());
	}
	
	@FXML
	public void clickSend(){
		if (mangel != null) {
			mangel.getKommentare().add(new Kommentar(taKommentar.getText(), Context.getLogin()));
		
			try {
				mangelcontroller.update(mangel);
				tvKommentar.setItems(FXCollections.observableArrayList(kommentarcontroller.getAll()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void clickAbbrechen(){
		showList();
	}
	
	@FXML
	public void clickSpeichern(){
		if (mangel != null) {
	        
			mangel.setProjekt(cbProjekt.getValue());
			mangel.setMangel(txBeschreibung.getText());
			mangel.setSubunternehmen(cbSubunternehmen.getValue());
			mangel.setMangelstatus(cbStatus.getValue());
//			mangel.setErledigenbis(dpFrist);

			try {
				mangelcontroller.update(mangel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else{
			
			// Das Erstellen der Referenz sollte vielleicht in die Mangelklasse unter @PrePersist
			int ref = 0;
			try {
				List<Mangel> mangelList = FXCollections.observableArrayList(mangelcontroller.getAll());
				for(Mangel m : mangelList){
					if (m.getProjekt() == cbProjekt.getValue()){
						if(m.getReferenz() > ref){
							ref = m.getReferenz();
						}
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ref += 1;
						
			mangel = new Mangel(ref, txBeschreibung.getText(), Context.getLogin().getPerson(),
					null, cbStatus.getValue(), cbSubunternehmen.getValue(), null,
					cbProjekt.getValue()); //Datum noch lösen (zweites "null")

			try {
				mangelcontroller.persist(mangel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}

	@Override
	public void initData(Mangel t) {
		mangel = t;
	}

	@Override
	public void showList() {
		Viewable<Projekt, Projekt> view = MainView.showCenterView("Mangel");
		view.initData(mangel.getProjekt());
	}
}

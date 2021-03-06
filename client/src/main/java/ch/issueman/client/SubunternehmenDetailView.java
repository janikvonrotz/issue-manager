package ch.issueman.client;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import ch.issueman.common.Adresse;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Ort;
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
	private List<Kontakt> kList;

	private FilteredList<Kontakt> filteredData = new FilteredList<Kontakt>(FXCollections.observableArrayList(),	t -> true);

	
	@FXML
	private Label lbSubunternehmen;
	
	@FXML
	private TextField txFirma;
	
	@FXML
	private TextField txStrasse;
	
	@FXML
	private ComboBox<Ort> cbOrt;
	
	@FXML
	private TextField txFilter;
		
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btSpeichern;
	
	@FXML
	private Button btAddKontakt;
	
	@FXML
	private TableView<Kontakt> tvKontakt;
	
	@FXML
	private TableColumn<Kontakt, String> tcNachname;
	
	@FXML
	private TableColumn<Kontakt, String> tcVorname;
	
	@FXML
	private TableColumn<Kontakt, String> tcEmail;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tcNachname.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("nachname"));
		tcVorname.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("vorname"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("email"));
		
		cbOrt.setCellFactory(new Callback<ListView<Ort>,ListCell<Ort>>(){
			@Override
			public ListCell<Ort> call(ListView<Ort> arg0) {		 
				final ListCell<Ort> cell = new ListCell<Ort>(){				 
                    @Override
                    protected void updateItem(Ort t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getPlz() + " - " + t.getOrt());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbOrt.setConverter(new StringConverter<Ort>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Ort fromString(String arg0) {
				return null;
			}

			public String toString(Ort o) {
               if (o != null) {
                    String str = o.getPlz() + " - " + o.getOrt();
                    map.put(str, o);
                    return str;
                } else {
                    return "";
                }
			}
		});

		
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}

						String lowerCaseFilter = newValue.toLowerCase();
						String objectvalues = t.getVorname() 
								+ t.getNachname()
								+ t.getEmail();
						
						if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; 
						}

						return false;
					});
		});
		
		Refresh();	
	}
	
	public void Refresh(){
		
		try {
			List<Ort> oList = ortcontroller.getAll();
			cbOrt.setItems(new SortedList<Ort>(FXCollections.observableArrayList(oList)));
		} catch (Exception e) {
			MainView.showError(e);
		}
		
		if(subunternehmen != null){
			lbSubunternehmen.setText(subunternehmen.getFirmenname());
			txFirma.setText(subunternehmen.getFirmenname());
	    	txStrasse.setText(subunternehmen.getAdresse().getStrasse());
	    	cbOrt.setValue(subunternehmen.getAdresse().getOrt());
	    	
			try {
				kList = kontaktcontroller.getAll().stream().filter(k -> k.getSubunternehmen().getFirmenname().
						equals(subunternehmen.getFirmenname())).collect(Collectors.toList());
				filteredData = new FilteredList<Kontakt>(FXCollections.observableArrayList(kList),	t -> true);
				SortedList<Kontakt> sortedData = new SortedList<Kontakt>(filteredData);
				sortedData.comparatorProperty().bind(tvKontakt.comparatorProperty());
				tvKontakt.setItems(sortedData);

			} catch (Exception e) {
				MainView.showError(e);
			}

		
			if (!Context.getLogin().getRolle().getBezeichnung().equals("Sachbearbeiter")){
				txFirma.setDisable(true);
				txStrasse.setDisable(true);
				cbOrt.setDisable(true);
				btSpeichern.setVisible(false);
			}
			
		} else {
	    	lbSubunternehmen.setText("neues subunternehmen");
			txFirma.setText("");
	    	txStrasse.setText("");
	    	cbOrt.setValue(null);   	
	    }
		
	}
	
	@FXML
	public void clickSpeichern(){
		
		if (subunternehmen != null) {
	        
			subunternehmen.setFirmenname(txFirma.getText());
			
			Adresse a = subunternehmen.getAdresse();
			a.setStrasse(txStrasse.getText());
			a.setOrt(cbOrt.getValue());
			
			try {
				subunternehmencontroller.update(subunternehmen);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else{
			
			try {
				subunternehmencontroller.persist(new Subunternehmen(txFirma.getText(),
						new Adresse(txStrasse.getText(), cbOrt.getValue())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		showList();

	}

	@FXML
	public void clickAbbrechen(){
		showList();
	}
	
	@FXML
	public void clickAddKontakt(){
    	ViewableDetail<Kontakt> view = MainView.showCenterDetailView("PersonDetail");
		view.initData(null);
	}
	
	@Override
	public void initData(Subunternehmen t) {
		subunternehmen = t;
		Refresh();
	}

	@Override
	public void showList() {
		Viewable<Subunternehmen, Subunternehmen> view = MainView.showCenterView("Subunternehmen");
		view.initData(subunternehmen);
	}
	
	@FXML
	public void doubleClickData() {
		tvKontakt.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Kontakt t = tvKontakt.getSelectionModel().getSelectedItem();
		        	ViewableDetail<Kontakt> view = MainView.showCenterDetailView("PersonDetail");
		    		view.initData(t);
		        }
		    }
		});
		
	
	}
}

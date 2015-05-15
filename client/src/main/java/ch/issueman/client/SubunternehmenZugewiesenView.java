package ch.issueman.client;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ws.rs.GET;

import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projektleitung;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * List view for Subunternehmen Zugewiesen
 * 
 * @author Sandro Klarer, Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class SubunternehmenZugewiesenView implements Viewable<Projekt, Projekt> {

	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Person, Integer> controller = new Controller<Person, Integer>(Person.class);
	
	private Projekt projekt;
	
	@FXML
	private TableView<Subunternehmen> tvData; 
	
	@FXML
	private TextField txFilter;
	
	@FXML
	private TableColumn<Subunternehmen, String> tcSubunternehmen; 
	
	@FXML 
	private TableColumn<Person, String> tcPerson;
	
	@FXML 
	private ComboBox<Subunternehmen> cbSubunternehmen;
	
	@FXML 
	private ComboBox<Person> cbKontakt;	
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tcSubunternehmen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Firmenname, String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Firmenname, String> param) {
				return new SimpleStringProperty(param.getValue().getSubunternehmen().getDisplayName());
			}  
		});	
				
		tcPerson.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				return new SimpleStringProperty(param.getValue().getKontaktperson().getDisplayName());
				}  
		});	
		
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getSubunternehmen().getDisplayName()
									+ t.getKontaktperson().getDisplayName();
	
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});	
		
		cbSubunternehmen.setCellFactory(new Callback<ListView<Subunternehmen>,ListCell<Subunternehmen>>(){
			@Override
			public ListCell<Subunternehmen> call(ListView<Subunternehmen> arg0) {		 
				final ListCell<Subunternehmen> cell = new ListCell<Subunternehmen>(){				 
                    @Override
                    protected void updateItem(Subunternehmen s, boolean bln) {
                        super.updateItem(s, bln);
                         
                        if(s != null){
                            setText(s.getDisplayName());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbKontakt.setConverter(new StringConverter<Kontakt>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Kontakt fromString(String arg0) {
				return null;
			}

			public String toString(Kontakt s) {
               if (s != null) {
                    String str = s.getDisplayName();
                    map.put(str, s);
                    return str;
                } else {
                    return "";
                }
			}
		});
		
		cbKontakt.setCellFactory(new Callback<ListView<Kontakt>,ListCell<Kontakt>>(){
			@Override
			public ListCell<Kontakt> call(ListView<Kontakt> arg0) {		 
				final ListCell<Kontakt> cell = new ListCell<Kontakt>(){				 
                    @Override
                    protected void updateItem(Kontakt k, boolean bln) {
                        super.updateItem(k, bln);
                         
                        if(k != null){
                            setText(k.getDisplayName());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbKontakt.setConverter(new StringConverter<Kontakt>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Kontakt fromString(String arg0) {
				return null;
			}

			public String toString(Kontakt k) {
               if (k != null) {
                    String str = k.getDisplayName();
                    map.put(str, k);
                    return str;
                } else {
                    return "";
                }
			}
		});
		Refresh();
	}

	@Override
	public void Refresh() {
		try {
			filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(projekt.getSubunternehmen()),	p -> true);
			SortedList<Subunternehmen> sortedData = new SortedList<Subunternehmen>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
			
			cbSubunternehmen.setItems(FXCollections.observableArrayList(subunternehmencontroller.getAll()));
			
			filteredData = new FilteredList<Kontakt>(FXCollections.observableArrayList(projekt.getKontakt()),	p -> true);
			SortedList<Kontakt> sortedData = new SortedList<Kontakt>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
			
			cbKontakt.setItems(FXCollections.observableArrayList(kontaktcontroller.getAll()));
		} catch (Exception e) {
			MainView.showError(e);
		}
	}

	@FXML
	public void clickSpeichern(){
		Projektleitung p = new Projektleitung();
		p.setBauleiter(cbBauleiter.getValue());
		
		projekt.getProjektleitungen().add(p);
		
		try {
			projektcontroller.update(projekt);
		} catch (Exception e) {
			MainView.showError(e);
		}
		
		showDetail(projekt);
	}
	
	@FXML
	public void clickAbbrechen(){
		showDetail(projekt);
	}
	@Override
	public void initData(Projekt t) {
		projekt = t;
		Refresh();
	}

	@Override
	public void showDetail(Projekt t) {
		ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(t);
	}

}

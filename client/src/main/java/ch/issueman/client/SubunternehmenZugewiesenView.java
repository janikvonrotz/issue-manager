package ch.issueman.client;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

	private FilteredList<Kontakt> filteredData = new FilteredList<Kontakt>(FXCollections.observableArrayList(),	p -> true);

	private Projekt projekt;
	private List<Kontakt> kontakte;
	private List<Kontakt> kSelection;
	
	@FXML
	private TableView<Kontakt> tvData; 
	
	@FXML
	private TextField txFilter;
	
	@FXML
	private TableColumn<Kontakt, String> tcSubunternehmen; 
	
	@FXML 
	private TableColumn<Kontakt, String> tcPerson;
	
	@FXML 
	private ComboBox<Subunternehmen> cbSubunternehmen;
	
	@FXML 
	private ComboBox<Kontakt> cbKontakt;	
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tcSubunternehmen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kontakt, String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Kontakt, String> param) {
				return new SimpleStringProperty(param.getValue().getSubunternehmen().getFirmenname());
			}  
		});	
				
		tcPerson.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kontakt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Kontakt, String> param) {
				return new SimpleStringProperty(param.getValue().getDisplayName());
				}  
		});	
		
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getSubunternehmen().getFirmenname()
									+ t.getDisplayName();
	
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
		
		cbSubunternehmen.setOnAction((event) -> {
			refreshCbKontakt();
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
			
			kontakte = kontaktcontroller.getAll().stream().filter(k -> k.getProjekte().contains(projekt)).collect(Collectors.toList());
			
			
			filteredData = new FilteredList<Kontakt>(FXCollections.observableArrayList(kontakte),	p -> true);
			SortedList<Kontakt> sortedData = new SortedList<Kontakt>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
			
			List<Subunternehmen> sSelection = subunternehmencontroller.getAll();
			kontakte.forEach(k -> sSelection.remove(k.getSubunternehmen()));
					
			cbSubunternehmen.setItems(FXCollections.observableArrayList(sSelection));
			
			refreshCbKontakt();
			
		} catch (Exception e) {
			MainView.showError(e);
		}
	}
	
	public void refreshCbKontakt(){
		try {
			kSelection = kontaktcontroller.getAll().stream().filter(k -> k.getSubunternehmen().getFirmenname().
					equals(cbSubunternehmen.getValue().getFirmenname())).collect(Collectors.toList());
			
			cbKontakt.setItems(FXCollections.observableArrayList(kSelection));
		} catch (Exception e) {
			MainView.showError(e);
		}

	}

	@FXML
	public void clickSpeichern(){
		
		Kontakt k = cbKontakt.getValue();
		k.getProjekte().add(projekt);
		
		try {
			kontaktcontroller.update(k);
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

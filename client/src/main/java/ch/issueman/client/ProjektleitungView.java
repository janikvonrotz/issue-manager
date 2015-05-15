package ch.issueman.client;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import ch.issueman.common.Bauleiter;
import ch.issueman.common.ConfigHelper;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projektleitung;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * List view for Projektleitung
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class ProjektleitungView implements Viewable<Projekt, Projekt> {
	
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
	
	private FilteredList<Projektleitung> filteredData = new FilteredList<Projektleitung>(FXCollections.observableArrayList(),	p -> true);
	
	private Projekt projekt;
	
	@FXML
	private TableView<Projektleitung> tvData;
	
	@FXML
	private TextField txFilter; 
	
	@FXML 
	private TableColumn<Projektleitung, String> tcBauleiter;
		
	@FXML
	private TableColumn<Projektleitung, String> tcBeginn;
	
	@FXML
	private TableColumn<Projektleitung, String> tcEnde;
	
	@FXML
	private ComboBox<Bauleiter> cbBauleiter;
	
	@FXML
	private DatePicker dpBeginn;
	
	@FXML
	private DatePicker dpEnde;
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tcBauleiter.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projektleitung,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projektleitung, String> param) {
				return new SimpleStringProperty(param.getValue().getBauleiter().getDisplayName());
			}  
		});	

		
		tcBeginn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projektleitung,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projektleitung, String> param) {
				return new SimpleStringProperty((new SimpleDateFormat(ConfigHelper.getConfig("format.date", "dd.MM.yyyy"))).
						format(param.getValue().getBeginn().getTime()));
			}
		});
		tcEnde.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projektleitung,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projektleitung, String> param) {
				return new SimpleStringProperty((new SimpleDateFormat(ConfigHelper.getConfig("format.date", "dd.MM.yyyy"))).
						format(param.getValue().getEnde().getTime()));
			}
		});
	
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getBauleiter().getDisplayName();
	
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});	
		
		cbBauleiter.setCellFactory(new Callback<ListView<Bauleiter>,ListCell<Bauleiter>>(){
			@Override
			public ListCell<Bauleiter> call(ListView<Bauleiter> arg0) {		 
				final ListCell<Bauleiter> cell = new ListCell<Bauleiter>(){				 
                    @Override
                    protected void updateItem(Bauleiter b, boolean bln) {
                        super.updateItem(b, bln);
                         
                        if(b != null){
                            setText(b.getDisplayName());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbBauleiter.setConverter(new StringConverter<Bauleiter>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Bauleiter fromString(String arg0) {
				return null;
			}

			public String toString(Bauleiter b) {
               if (b != null) {
                    String str = b.getDisplayName();
                    map.put(str, b);
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
			filteredData = new FilteredList<Projektleitung>(FXCollections.observableArrayList(projekt.getProjektleitungen()),	p -> true);
			SortedList<Projektleitung> sortedData = new SortedList<Projektleitung>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
			
			cbBauleiter.setItems(FXCollections.observableArrayList(bauleitercontroller.getAll()));
		} catch (Exception e) {
			MainView.showError(e);
		}
	}

	@FXML
	public void clickSpeichern(){
		Projektleitung p = new Projektleitung();
		p.setBauleiter(cbBauleiter.getValue());
		p.getBeginn().setTime(Date.from(dpBeginn.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		p.getEnde().setTime(Date.from(dpEnde.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
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

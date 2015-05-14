package ch.issueman.client;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import ch.issueman.common.ConfigHelper;
import ch.issueman.common.Projekt;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import ch.issueman.client.MainView;
/**
 * List view for Projekt
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProjektView implements Viewable<Projekt, Projekt> {

	private static Controller<Projekt, Integer> controller = new Controller<Projekt, Integer>(Projekt.class);

	private FilteredList<Projekt> filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Projekt> tvData;

	@FXML
	private TextField txFilter;
	
	@FXML
	private Button btExport;

	@FXML
	private TableColumn<Projekt, Integer> tcId;

	@FXML
	private TableColumn<Projekt, String> tcTitel;

	@FXML
	private TableColumn<Projekt, String> tcProjekttyp;

	@FXML
	private TableColumn<Projekt, String> tcArbeitstyp;

	@FXML
	private TableColumn<Projekt, String> tcBauleiter;

	@FXML
	private TableColumn<Projekt, String> tcBauherr;

	@FXML
	private TableColumn<Projekt, String> tcEnddatum;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
//		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
//		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("id"));
		tcTitel.setCellValueFactory(new PropertyValueFactory<Projekt, String>("title"));
		tcProjekttyp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getProjekttyp().getProjekttyp());
			}  
		});
		tcArbeitstyp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getArbeitstyp().getArbeitstyp());
			}  
		});
//		tcBauleiter.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
//			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
//				return new SimpleStringProperty(param.getValue().getProjektleitungen());
//			}  
//		});
		tcBauherr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getBauherr().getUnternehmen().getFirmenname());
			}  
		});	
		tcEnddatum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty((new SimpleDateFormat(ConfigHelper.getConfig("format.date", "dd.MM.yyyy"))).format(param.getValue().getEnde().getTime()));
			}  
		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getTitle() 
									+ t.getArbeitstyp().getArbeitstyp() 
									+ t.getProjekttyp().getProjekttyp()
									+ t.getBauherr().getUnternehmen().getFirmenname()
									+ t.getId();
							
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});		

		Refresh();
	}

	public void Refresh() {
		try {
			filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(controller.getAll()), p -> true);
			SortedList<Projekt> sortedData = new SortedList<Projekt>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
		} catch (Exception e) {
			MainView.showError(e);
		}
	}

	@FXML
	public void doubleClickData() {
		tvData.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Projekt t = tvData.getSelectionModel().getSelectedItem();
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void export(){
		MainView.exportData(filteredData);
	}
	@Override
	public void initData(Projekt t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDetail(Projekt t) {
		ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(t);
	}
}

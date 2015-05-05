package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ProjektView implements Initializable {

	private static Controller<Projekt, Integer> controller = new Controller<Projekt, Integer>(Projekt.class);

	private FilteredList<Projekt> filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Projekt> tvData;

	@FXML
	private TextField txFilter;

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
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();

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
				return new SimpleStringProperty(param.getValue().getEnde().toString());
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
			filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(),	p -> true);
			SortedList<Projekt> sortedData = new SortedList<Projekt>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clickData() {

	}
}

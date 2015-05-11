package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Person;
import ch.issueman.common.Sachbearbeiter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PersonView implements Viewable<Person, Person> {

	private static Controller<Person, Integer> personController = new Controller<Person, Integer>(Person.class);
	private static Controller<Login, Integer> loginController = new Controller<Login, Integer>(Login.class);
	private static Controller<Kontakt, Integer> kontaktController = new Controller<Kontakt, Integer>(Kontakt.class);
	
	private FilteredList<Person> filteredData = new FilteredList<Person>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Person> tvData;

	@FXML
	private TextField txFilter;

	@FXML
	private TableColumn<Person, Integer> tcId;

	@FXML
	private TableColumn<Person, String> tcNachname;

	@FXML
	private TableColumn<Person, String> tcVorname;

	@FXML
	private TableColumn<Person, String> tcEmail;

	@FXML
	private TableColumn<Login, String> tcRolle;

	@FXML
	private TableColumn<Kontakt, String> tcFirma;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		tcNachname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				return new SimpleStringProperty(param.getValue().getNachname());
			}  
		});
		tcVorname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				return new SimpleStringProperty(param.getValue().getVorname());
			}  
		});
		tcEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				return new SimpleStringProperty(param.getValue().getEmail());
			}  
		});
		tcRolle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(param.getValue().getRolle().getBezeichnung());
			}  
		});
		tcFirma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kontakt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Kontakt, String> param) {
				return new SimpleStringProperty(param.getValue().getSubunternehmen().getFirmenname());
			}  
		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getNachname() 
									+ t.getVorname()
									+ t.getEmail()
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
			filteredData = new FilteredList<Person>(FXCollections.observableArrayList(personController.getAll()),	p -> true);
			SortedList<Person> sortedData = new SortedList<Person>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MainView.showError(e);
		}
	}

	@FXML
	public void clickData() {

	}

	@Override
	public void initData(Person t) {
		System.out.println(t.getClass().getSimpleName());
		
	}

	@Override
	public void showDetail(Person t) {
		// TODO Auto-generated method stub
		
	}
}

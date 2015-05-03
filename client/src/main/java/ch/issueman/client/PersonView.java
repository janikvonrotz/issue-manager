package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Person;
import ch.issueman.common.Sachbearbeiter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonView implements Initializable {

	private static Controller<Person, Integer> controller = new Controller<Person, Integer>(Person.class);

	private ObservableList<Person> masterData = FXCollections.observableArrayList();

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
	private TableColumn<Person, String> tcRolle;

	@FXML
	private TableColumn<Person, Integer> tcFirma;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		tcNachname.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
		tcVorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		tcRolle.setCellValueFactory(new PropertyValueFactory<Person, String>("dtype"));
		tcFirma.setCellValueFactory(new PropertyValueFactory<Person, Integer>("firma"));

		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).
		FilteredList<Person> filteredData = new FilteredList<>(masterData, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		txFilter.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(person -> {
						// If filter text is empty, display all persons.
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							// Compare name with filter text.
							String lowerCaseFilter = newValue.toLowerCase();

							if (person.getNachname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; // Filter matches first name.
							} else if (person.getVorname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; // Filter matches last name.
							}
							return false; // Does not match.
						});
				});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Person> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvData.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tvData.setItems(sortedData);

		Refresh();
	}

	public void Refresh() {
		try {
			tvData.setItems(FXCollections.observableArrayList(controller.getAll()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clickData() {

	}
}

package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
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

public class ProjektView implements Initializable {

	private static Controller<Projekt, Integer> controller = new Controller<Projekt, Integer>(
			Projekt.class);

	private ObservableList<Projekt> masterData = FXCollections
			.observableArrayList();

	private Projekt projekt;

	@FXML
	private TableView<Projekt> tvData;

	@FXML
	private TextField txFilter;

	@FXML
	private TableColumn<Projekt, Integer> tcId;

	@FXML
	private TableColumn<Projekt, String> tcTitel;

	@FXML
	private TableColumn<Projekt, Integer> tcProjekttyp;

	@FXML
	private TableColumn<Projekt, Integer> tcArbeitstyp;

	@FXML
	private TableColumn<Projekt, Integer> tcBauleiter;

	@FXML
	private TableColumn<Projekt, Integer> tcBauherr;

	@FXML
	private TableColumn<Projekt, String> tcEnddatum;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1",
				null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>(
				"id"));
		tcTitel.setCellValueFactory(new PropertyValueFactory<Projekt, String>(
				"title"));
		tcProjekttyp
				.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>(
						"projekttyp_id"));
		tcArbeitstyp
				.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>(
						"arbeitstyp_id"));
		tcBauleiter
				.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>(
						"bauleiter_id"));
		tcBauherr
				.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>(
						"bauherr_id"));
		tcEnddatum
				.setCellValueFactory(new PropertyValueFactory<Projekt, String>(
						"ende"));

		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).
		FilteredList<Projekt> filteredData = new FilteredList<>(masterData,
				p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(person -> {
						// If filter text is empty, display all persons.
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							// Compare name with filter text.
							String lowerCaseFilter = newValue.toLowerCase();

							if (projekt.getTitle().toLowerCase()
									.indexOf(lowerCaseFilter) != -1) {
								return true; // Filter matches first name.
							}
							return false; // Does not match.
						});
				});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Projekt> sortedData = new SortedList<>(filteredData);

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

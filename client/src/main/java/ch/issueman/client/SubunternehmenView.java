package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

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

import ch.issueman.common.Login;
import ch.issueman.common.Ort;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

public class SubunternehmenView implements Initializable {

	private static Controller<Subunternehmen, Integer> controller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);

	private ObservableList<Subunternehmen> masterData = FXCollections.observableArrayList();

	private static Subunternehmen subunternehmen;

	@FXML
	private TableView<Subunternehmen> tvData;

	@FXML
	private TextField txFilter;

	@FXML
	private TableColumn<Subunternehmen, Integer> tcId;

	@FXML
	private TableColumn<Subunternehmen, String> tcFirmenname;

	@FXML
	private TableColumn<Subunternehmen, Integer> tcStrasse;

	@FXML
	private TableColumn<Ort, Integer> tcPlz;

	@FXML
	private TableColumn<Ort, String> tcOrt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1",
				null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("id"));
		tcFirmenname
				.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("firmenname"));
		tcStrasse
				.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("adresse_id"));

		// tcStrasse.setCellValueFactory(adresseController.getById(tcId.getCellData());
		// tcPlz.setCellValueFactory(new PropertyValueFactory<Ort,
		// Integer>("plz"));
		// tcOrt.setCellValueFactory(new PropertyValueFactory<Ort,
		// String>("ort"));

		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).
		FilteredList<Subunternehmen> filteredData = new FilteredList<>(masterData, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		txFilter.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(person -> {
						// If filter text is empty, display all persons.
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							// Compare name with filter text.
							String lowerCaseFilter = newValue.toLowerCase();

							if (subunternehmen.getFirmenname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; // Filter matches first name.
							}
							return false; // Does not match.
						});
				});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Subunternehmen> sortedData = new SortedList<>(filteredData);

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

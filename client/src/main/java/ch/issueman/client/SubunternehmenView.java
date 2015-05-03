package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

public class SubunternehmenView implements Initializable {

	private static Controller<Subunternehmen, Integer> controller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);

	private FilteredList<Subunternehmen> filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(), p -> true);

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
	private TableColumn<Subunternehmen, Integer> tcPlz;

	@FXML
	private TableColumn<Subunternehmen, String> tcOrt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("id"));
		tcFirmenname.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("firmenname"));
		tcStrasse.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("strasse"));
		tcPlz.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("plz"));
		tcOrt.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("ort"));

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();

							if (t.getFirmenname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}
							return false;
						});
				});		

		Refresh();
	}

	public void Refresh() {
		try {
			filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(controller.getAll()),	p -> true);
			SortedList<Subunternehmen> sortedData = new SortedList<Subunternehmen>(filteredData);
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

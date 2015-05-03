package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Sachbearbeiter;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProjektView implements Initializable {

	private static Controller<Projekt, Integer> controller = new Controller<Projekt, Integer>(Projekt.class);

	private FilteredList<Projekt> filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(),	p -> true);

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
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("id"));
		tcTitel.setCellValueFactory(new PropertyValueFactory<Projekt, String>("title"));
		tcProjekttyp.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("projekttyp"));
		tcArbeitstyp.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("arbeitstyp"));
		tcBauleiter.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("bauleiter"));
		tcBauherr.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("bauherr"));
		tcEnddatum.setCellValueFactory(new PropertyValueFactory<Projekt, String>("ende"));	

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getTitle() + t.getArbeitstyp().getArbeitstyp() + t.getId();
							
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
			filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(controller.getAll()),	p -> true);
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

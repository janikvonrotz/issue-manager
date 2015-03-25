package ch.issueman.common.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.issueman.common.Employer;
import ch.issueman.common.Person;

public class HomeView implements Initializable {

	private static Controller<Employer, Integer> controller = new Controller<Employer, Integer>(Employer.class);

	@FXML
	private Button btAdd;

	@FXML
	private Button btUpdate;

	@FXML
	private Button btDelete;

	@FXML
	private TextField txName;
	
	@FXML
	private TextField txCompany;
	
	@FXML
	private TableView<Employer> tvEmployer;

	@FXML
	private TableColumn<Employer, Integer> tcId;

	@FXML
	private TableColumn<Employer, String> tcName;
	
	@FXML
	private TableColumn<Employer, String> tcCompany;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<Employer, Integer>("id"));
		tcName.setCellValueFactory(new PropertyValueFactory<Employer, String>("name"));
		tcCompany.setCellValueFactory(new PropertyValueFactory<Employer, String>("company"));
		refreshPersonTable();
	}

	public void refreshPersonTable() {
		tvEmployer.setItems(FXCollections.observableArrayList(controller.getAll()));
	}

	@FXML
	public void clickTableView() {
		txName.textProperty().set(tvEmployer.getSelectionModel().getSelectedItem().getName());
		txCompany.textProperty().set(tvEmployer.getSelectionModel().getSelectedItem().getCompany());
	}

	@FXML
	public void clickAdd() {
		controller.persist(new Employer(txName.getText(), txCompany.getText()));
		refreshPersonTable();
	}

	@FXML
	public void clickUpdate() {
		Employer t = tvEmployer.getSelectionModel().getSelectedItem();
		
		t.setName(txName.getText());
		t.setCompany(txCompany.getText());
		
		controller.update(t);
		refreshPersonTable();
	}

	@FXML
	public void clickDelete() {
		controller.delete(tvEmployer.getSelectionModel().getSelectedItem());
		refreshPersonTable();
	}
}
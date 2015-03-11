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
import ch.issueman.common.Person;

public class HomeView implements Initializable {

	private static PersonClientController controller = new PersonClientController();

	@FXML
	private Button btAdd;

	@FXML
	private Button btUpdate;

	@FXML
	private Button btDelete;

	@FXML
	private TextField txName;

	@FXML
	private TableView<Person> tvPerson;

	@FXML
	private TableColumn<Person, Integer> tcId;

	@FXML
	private TableColumn<Person, String> tcName;

	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		tcName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		refreshPersonTable();
	}

	public void refreshPersonTable() {
		tvPerson.setItems(FXCollections.observableArrayList(controller.getAll()));
	}

	@FXML
	public void clickTableView() {
		txName.textProperty().set(tvPerson.getSelectionModel().getSelectedItem().getName());
	}

	@FXML
	public void clickAdd() {
		controller.persist(new Person(txName.getText()));
		refreshPersonTable();
	}

	@FXML
	public void clickUpdate() {
		Person p = tvPerson.getSelectionModel().getSelectedItem();
		p.setName(txName.getText());
		controller.update(p);
		refreshPersonTable();
	}

	@FXML
	public void clickDelete() {
		controller.delete(tvPerson.getSelectionModel().getSelectedItem());
		refreshPersonTable();
	}
}
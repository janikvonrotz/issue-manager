package ch.hslu.issueman.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.hslu.issueman.model.Person;

public class PersonController implements DAO<Person, Integer>, Initializable{
	
	private static Controller<Person, Integer> controller;
	
	public PersonController() {
		controller = new Controller<Person, Integer>(Person.class);
	}
	
	public void persist(Person person) {
		controller.persist(person);
	}

	public Person getById(Integer id) {
		Person person = controller.getById(id);
		return person;
	}

	public List<Person> getAll() {
		List<Person> people = controller.getAll();
		return people;
	}

	public void update(Person person) {
		controller.update(person);
	}

	public void deleteById(Integer id) {
		controller.delete(controller.getById(id));
	}

	public void delete(Person person) {
		controller.delete(person);
	}
	
	public void deleteAll() {
		controller.deleteAll();
	}

	public void printToJson(List<Person> people) {
		controller.printToJson(people);	
	}
	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));		
		tcName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		refreshPersonTable();
	}
	
	public void refreshPersonTable(){
		tvPerson.setItems(FXCollections.observableArrayList(getAll()));
	}
	
	@FXML
	public void clickTableView(){
		txName.textProperty().set(tvPerson.getSelectionModel().getSelectedItem().getName());
	}
	
	@FXML
	public void clickAdd(){
		controller.persist(new Person(txName.getText()));
		refreshPersonTable();
	}
	
	@FXML
	public void clickUpdate(){
		Person p = tvPerson.getSelectionModel().getSelectedItem();
		p.setName(txName.getText());
		controller.update(p);
		refreshPersonTable();
	}
	
	@FXML
	public void clickDelete(){
		controller.delete(tvPerson.getSelectionModel().getSelectedItem());
		refreshPersonTable();
	}
}

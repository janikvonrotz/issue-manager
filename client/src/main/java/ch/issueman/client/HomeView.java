package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ch.issueman.common.Employer;
import ch.issueman.common.User;

public class HomeView implements Initializable {

	private static Controller<Employer, Integer> controller = new Controller<Employer, Integer>(Employer.class, null);

	@FXML
	private Button btAdd;

	@FXML
	private Button btUpdate;

	@FXML
	private Button btDelete;

	@FXML
	private Button btLogin;
	
	@FXML
	private Pane pnLogin;
	
	@FXML
	private Pane pnData;
	
	@FXML
	private TextField txUsername;
	
	@FXML
	private TextField txName;
	
	@FXML
	private TextField txCompany;
	
	@FXML
	private PasswordField pfPassword;
	
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
		Employer e = tvEmployer.getSelectionModel().getSelectedItem();
		txName.textProperty().set(e.getName());
		txCompany.textProperty().set(e.getCompany());
	}

	@FXML
	public void clickAdd() {
		controller.persist(new Employer(txName.getText(), txCompany.getText()));
		refreshPersonTable();
	}

	@FXML
	public void clickUpdate() {
		Employer e = tvEmployer.getSelectionModel().getSelectedItem();
		e.setName(txName.getText());
		e.setCompany(txCompany.getText());
		controller.update(e);
		refreshPersonTable();
	}

	@FXML
	public void clickDelete() {
		Employer e = tvEmployer.getSelectionModel().getSelectedItem();
		controller.delete(e);
		refreshPersonTable();
	}
	
	@FXML
	public void clickLogin(){
		User user = new User("", txUsername.getText(), pfPassword.getText(), "");
		controller = new Controller<Employer, Integer>(Employer.class, user);
		if(controller.login()){
			pnData.setVisible(true);
			pnLogin.setVisible(false);
		}else{
			txUsername.setText("");
			pfPassword.setText("");
		}
	}
}
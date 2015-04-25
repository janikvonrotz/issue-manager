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
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Login;

public class HomeView implements Initializable {

	private static Controller<Sachbearbeiter, Integer> controller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);

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
	private TableView<Sachbearbeiter> tvEmployer;

	@FXML
	private TableColumn<Sachbearbeiter, Integer> tcId;

	@FXML
	private TableColumn<Sachbearbeiter, String> tcName;
	
	@FXML
	private TableColumn<Sachbearbeiter, String> tcCompany;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<Sachbearbeiter, Integer>("id"));
		tcName.setCellValueFactory(new PropertyValueFactory<Sachbearbeiter, String>("name"));
		tcCompany.setCellValueFactory(new PropertyValueFactory<Sachbearbeiter, String>("company"));
		refreshPersonTable();
	}

	public void refreshPersonTable() {
		tvEmployer.setItems(FXCollections.observableArrayList(controller.getAll()));
	}

	@FXML
	public void clickTableView() {
		Sachbearbeiter e = tvEmployer.getSelectionModel().getSelectedItem();
		txName.textProperty().set(e.getNachname());
		txCompany.textProperty().set(e.getVorname());
	}

	@FXML
	public void clickAdd() {
		controller.persist(new Sachbearbeiter(txName.getText(), txCompany.getText(), ""));
		refreshPersonTable();
	}

	@FXML
	public void clickUpdate() {
		Sachbearbeiter e = tvEmployer.getSelectionModel().getSelectedItem();
		e.setNachname(txName.getText());
		e.setVorname(txCompany.getText());
		controller.update(e);
		refreshPersonTable();
	}

	@FXML
	public void clickDelete() {
		Sachbearbeiter e = tvEmployer.getSelectionModel().getSelectedItem();
		controller.delete(e);
		refreshPersonTable();
	}
	
	@FXML
	public void clickLogin(){
		Login login = new Login(new Sachbearbeiter("", "", txUsername.getText()), pfPassword.getText(), null);
		Context.setLogin(login);
		if(Context.login()){
			pnData.setVisible(true);
			pnLogin.setVisible(false);
		}else{
			txUsername.setText("");
			pfPassword.setText("");
		}
	}
}
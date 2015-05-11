package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Bauleiter;
import ch.issueman.common.Login;
import ch.issueman.common.Projektleitung;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * List view for Projektleitung
 * 
 * @author Sandro Klarer
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class ProjektleitungView implements Viewable<Projektleitung, Projektleitung>{
	
	private static Controller<Projektleitung, Integer> controller = new Controller<Projektleitung, Integer>(Projektleitung.class);
	
	private FilteredList<Projektleitung> filteredData = new FilteredList<Projektleitung>(FXCollections.observableArrayList(),	p -> true);
	
	@FXML
	private TableView<Projektleitung> tvData;
	
	@FXML
	private TextField txFilter; 
	
	@FXML
	private TableColumn<Projektleitung, Integer> tcId;
	
	@FXML 
	private TableColumn<Projektleitung, String> tcBauleiter;
		
	@FXML
	private TableColumn<Projektleitung, String> tcBeginn;
	
	@FXML
	private TableColumn<Projektleitung, String> tcEnde;
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
//		Context.login();
		
		tcId.setCellValueFactory(new PropertyValueFactory<Projektleitung, Integer>("id"));
		tcBauleiter.setCellValueFactory(new PropertyValueFactory<Projektleitung, String>("bauleiter"));
		tcBeginn.setCellValueFactory(new PropertyValueFactory<Projektleitung, String>("beginn"));
		tcEnde.setCellValueFactory(new PropertyValueFactory<Projektleitung, String>("ende"));
		
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getBauleiter() 
									+ t.getBeginn()
									+ t.getEnde() 
									+ t.getId();
							
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});		

		Refresh();
	}

	@Override
	public void Refresh() {
		try {
			filteredData = new FilteredList<Projektleitung>(FXCollections.observableArrayList(controller.getAll()),	p -> true);
			SortedList<Projektleitung> sortedData = new SortedList<Projektleitung>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
		} catch (Exception e) {
			MainView.showError(e);
		}
	}

	@FXML
	public void doubleClickData() {
		tvData.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Projektleitung t = tvData.getSelectionModel().getSelectedItem();
		        	showDetail(t);
		        }
		    }
		});
	}
	
	
		
	@Override
	public void showDetail(Projektleitung t) {
		ViewableDetail<Subunternehmen> view = MainView.showCenterDetailView("SubunternehmenDetail");
		view.initData(t);
	}

	@Override
	public void initData(Projektleitung t) {
		// TODO Auto-generated method stub
	}
}

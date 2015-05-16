package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import ch.issueman.common.Subunternehmen;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * List view for Subunternehmen
 * 
 * @author Janik von Rotz, Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */
public class SubunternehmenView implements Viewable<Subunternehmen, Subunternehmen> {

	private static Controller<Subunternehmen, Integer> controller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);

	private FilteredList<Subunternehmen> filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Subunternehmen> tvData;

	@FXML
	private TextField txFilter;
	
	@FXML
	private Button btAddSubunternehmen;

	@FXML
	private TableColumn<Subunternehmen, Integer> tcId;

	@FXML
	private TableColumn<Subunternehmen, String> tcFirmenname;

	@FXML
	private TableColumn<Subunternehmen, String> tcStrasse;

	@FXML
	private TableColumn<Subunternehmen, String> tcPlz;

	@FXML
	private TableColumn<Subunternehmen, String> tcOrt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
//		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
//		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("id"));
		tcFirmenname.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("firmenname"));
		
		tcStrasse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Subunternehmen,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Subunternehmen, String> param) {
				return new SimpleStringProperty(param.getValue().getAdresse().getStrasse());
			}  
		});

		tcPlz.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Subunternehmen,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Subunternehmen, String> param) {
				return new SimpleStringProperty(Integer.toString(param.getValue().getAdresse().getOrt().getPlz()));
			}  
		});

		tcOrt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Subunternehmen,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Subunternehmen, String> param) {
				return new SimpleStringProperty(param.getValue().getAdresse().getOrt().getOrt());
			}  
		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getFirmenname() 
									+ t.getAdresse().getStrasse()
									+ t.getAdresse().getOrt().getPlz()
									+ t.getAdresse().getOrt().getOrt() 
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
		if(Context.getLogin().getRolle().getBezeichnung().equals("Bauleiter")){
			btAddSubunternehmen.setVisible(false);
		} else if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktperson")){
			btAddSubunternehmen.setVisible(false);
		} else if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktadmin")){
			btAddSubunternehmen.setVisible(false);
		}
		
		try {
			filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(controller.getAll()),	p -> true);
			SortedList<Subunternehmen> sortedData = new SortedList<Subunternehmen>(filteredData);
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
		        	Subunternehmen t = tvData.getSelectionModel().getSelectedItem();
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void clickNeu() {
		 MainView.showCenterDetailView("SubunternehmenDetail");
	}
	
	@Override
	public void showDetail(Subunternehmen t) {
		ViewableDetail<Subunternehmen> view = MainView.showCenterDetailView("SubunternehmenDetail");
		view.initData(t);
	}

	@Override
	public void initData(Subunternehmen t) {
		// TODO Auto-generated method stub
	}
}
